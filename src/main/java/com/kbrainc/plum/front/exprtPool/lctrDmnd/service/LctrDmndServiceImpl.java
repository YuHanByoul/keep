package com.kbrainc.plum.front.exprtPool.lctrDmnd.service;

import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.*;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;
import com.penta.scpdb.ScpDbAgent;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 환경교육 전문가 풀 > 섭외 요청 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.service
 * - LctrDmndServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndServiceImpl
 * @Description : 환경교육 전문가 풀 > 섭외 요청 서비스 구현 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.lctrDmndService")
@Alias("front.lctrDmndService")
public class LctrDmndServiceImpl extends PlumAbstractServiceImpl implements LctrDmndService {

    @Resource(name = "front.lctrDmndDao")
    private LctrDmndDao lctrDmndDao;

    @Autowired
    private NtcnDao ntcnDao;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource(name = "MailNhnService")
    private MailService mailService;

    @Autowired
    private AlimtalkNhnService alimtalkService;

    /**
     * 전문가 목록 조회
     *
     * @param searchVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    @Override
    public List<ExprtVo> selectExprtList(ExprtVo searchVo) throws Exception {
        List<ExprtVo> exprts = lctrDmndDao.selectExprtList(searchVo);
        ScpDbAgent agt = new ScpDbAgent();
        String decStr = "";
        
        if (System.getenv("PC_KIND") == null) {
            for (ExprtVo exprt : exprts) {
                decStr = agt.ScpDecStr(CommonUtil.damoScpIniFilePath, "KEY1", exprt.getGndr());
                exprt.setGndr(decStr);
            }
        } else {
            for (ExprtVo exprt : exprts) {
                decStr = "M"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
                exprt.setGndr(decStr);
            }
        }

        return exprts;
    }

    /**
     * 전문가 상세 조회
     *
     * @param exprtVo
     * @return exprt vo
     * @throws Exception
     * @Title : selectExprt
     * @Description : 전문가 상세 조회
     */
    @Override
    public ExprtVo selectExprt(ExprtVo exprtVo) throws Exception {
        ExprtVo exprt = lctrDmndDao.selectExprt(exprtVo);

        ScpDbAgent agt = new ScpDbAgent();
        String decStr = "";
        if (System.getenv("PC_KIND") == null) {
            decStr = agt.ScpDecStr(CommonUtil.damoScpIniFilePath, "KEY1", exprt.getGndr());
        } else {
            decStr = "M"; // 암호화 모듈을 사용할수 없는 MAC인경우 무조건 남자로 설정.
        }
        
        exprt.setGndr(decStr);

        if(exprt.getQlfcRlsYn().equals("Y")) {
            List<ExprtCrtfctVo> exprtCrtfctList = lctrDmndDao.selectExprtCrtfctList(exprtVo);
            exprt.setExprtCrtfctList(exprtCrtfctList);
        }

        if(exprt.getHdofRlsYn().equals("Y")) {
            List<ExprtHdofVo> exprtHdofList = lctrDmndDao.selectExprtHdofList(exprtVo);
            exprt.setExprtHdofList(exprtHdofList);
        }

        if(exprt.getCareerRlsYn().equals("Y")) {
            List<ExprtCareerVo> exprtCareerList = lctrDmndDao.selectExprtCareerList(exprtVo);
            exprt.setExprtCareerList(exprtCareerList);
        }

        return exprt;
    }

    @Override
    @Transactional
    public int insertLctrDmnd(LctrDmndVo lctrDmndVo) throws Exception {
        int retVal = 0;
        // 대상: 강의요청 수신한 전문가 가입자
        MemberVo member = lctrDmndDao.selectMember(lctrDmndVo);

        // 알림 발송
        retVal += sendNtcn(member);
        // 메일 발송
        sendEmail(member);
        //알림톡 발송
        sendAlimtalk(member);

        retVal += lctrDmndDao.insertLctrDmnd(lctrDmndVo);
        return retVal;
    }

    private void sendAlimtalk(MemberVo member) throws Exception {
        String recipientList =  "[{\"recipientNo\": \""+ member.getMoblphon()+"\",\"templateParameter\": {\"nm\":\""+ member.getNm()+"\"} }]";
        alimtalkService.sendAlimtalk("keep-008","", recipientList);
    }

    private int sendNtcn(MemberVo member) throws Exception {
        NtcnVo ntcnVo = new NtcnVo();
        ntcnVo.setUserid(member.getUserid());
        ntcnVo.setTtl("환경교육 전문가 섭외 요청 완료");
        ntcnVo.setCn("환경교육 전문가 섭외 요청이 있습니다.\r\n"
                + "자세한 내용은 전문가 요청 관리 메뉴를 확인해 주십시오.");
        ntcnVo.setInqYn("N");
        ntcnVo.setKndCd("245101");
        ntcnVo.setMvmnurl("/front/mypage/exprtPool/lctrDmndList.html");

        int retVal = ntcnDao.insertNtcn(ntcnVo);
        return retVal;
    }

    private void sendEmail(MemberVo member) throws Exception {
        StringBuilder contents = new StringBuilder();
        contents.append("<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">");
        contents.append(member.getNm());
        contents.append("님, " );
        contents.append("새로운 환경교육 전문가 섭외 요청이 있습니다.<br/> <br/>" );
        contents.append("자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해 주십시오.<br/> <br/>" );
        contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");

        Context context = new Context();
        context.setVariable("title", "[환경보전협회] 환경교육 전문가 섭외 요청이 있습니다.");
        context.setVariable("content", contents.toString());
        context.setVariable("portalUrl", CommonUtil.portalUrl);
        String mailContents = templateEngine.process("mail/mail_basic_template", context);
        String mailTitle = "[환경보전협회] 환경교육 전문가 섭외 요청이 있습니다.";

        MailVo mailVo = new MailVo();
        mailVo.setRcptnEmail(member.getEml());
        mailVo.setTitle(mailTitle);
        mailVo.setCntnts(mailContents);
        mailVo.setRcptnUserid(member.getUserid());
        mailVo.setSndngSeCd("U");

        mailService.sendMail(mailVo); // 이메일 발송
    }

    /**
     * 관심인력 등록
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : insertLtrstExprt
     * @Description : 관심인력 등록
     */
    @Override
    @Transactional
    public int insertItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.insertItrstExprt(exprtVo);
    }

    /**
     * 이미 등록된 관심인력인지 확인
     *
     * @param exprtVo
     * @return boolean
     * @throws Exception
     * @Title : checkAlreadyRegistedInrstExprt
     * @Description : 이미 등록된 관심인력인지 확인
     */
    @Override
    public boolean checkAlreadyRegistedItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.checkAlreadyRegistedItrstExprt(exprtVo) > 0 ;
    }

    /**
     * 관심인력 삭제
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : deleteItrstExprt
     * @Description : 관심인력 삭제
     */
    @Override
    @Transactional
    public int deleteItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.deleteItrstExprt(exprtVo);
    }
}
