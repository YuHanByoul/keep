package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndDao;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndVo;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 마이페이지 > 전문가 요청 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyLctrDmndServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyLctrDmndServiceImpl
 * @Description : 마이페이지 > 전문가 요청 관리 서비스 구현 클래스
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class MyLctrDmndServiceImpl extends PlumAbstractServiceImpl implements MyLctrDmndService {

    @Autowired
    private MyLctrDmndDao myLctrDmndDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private NtcnDao ntcnDao;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource(name = "MailNhnService")
    private MailService mailService;

    @Autowired
    private AlimtalkNhnService alimtalkService;

    /**
     * 강의 요청서 목록 조회
     *
     * @param myLctrDmndVo
     * @return list
     * @throws Exception
     * @Title : selectLctrDmndList
     * @Description : 강의 요청서 목록 조회
     */
    @Override
    public List<MyLctrDmndVo> selectLctrDmndList(MyLctrDmndVo myLctrDmndVo) throws Exception {
        return myLctrDmndDao.selectLctrDmndList(myLctrDmndVo);
    }

    /**
     * 강의 요청서 상세 조회
     *
     * @param myLctrDmndVo
     * @return my lctr dmnd vo
     * @throws Exception
     * @Title : selectLctrDmnd
     * @Description : 강의 요청서 상세 조회
     */
    @Override
    public MyLctrDmndVo selectLctrDmnd(MyLctrDmndVo myLctrDmndVo) throws Exception {
        MyLctrDmndVo lctrDmnd = myLctrDmndDao.selectLctrDmnd(myLctrDmndVo);

        if (lctrDmnd != null && !lctrDmnd.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(lctrDmnd.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            lctrDmnd.setFileList(fileList);
        }

        return lctrDmnd;
    }

    /**
     * 강의 요청서 상태 변경
     *
     * @param myLctrDmndVo
     * @return int
     * @throws Exception
     * @Title : updateStatus
     * @Description : 강의 요청서 상태 변경
     */
    @Override
    @Transactional
    public int updateStatus(MyLctrDmndVo myLctrDmndVo) throws Exception {
        int retVal = 0;

        MyLctrDmndVo myLctrDmnd = myLctrDmndDao.selectLctrDmnd(myLctrDmndVo);
        Map<String,Object> member = myLctrDmndDao.selectMember(myLctrDmnd.getUserid());

        if ("139104".equals(myLctrDmndVo.getDmndSttsCd()) || "139103".equals(myLctrDmndVo.getDmndSttsCd())) {
            // 대상 : 전문가 섭외 신청자
            String dmndSttsCd = myLctrDmndVo.getDmndSttsCd();
            // 알림 전송
            retVal += sendNtcn(myLctrDmnd, dmndSttsCd);
            // 메일 전송
            sendEmail(member, dmndSttsCd);
            // 알림톡 전송
            sendAlimtalk(member, dmndSttsCd);
        }

        retVal += myLctrDmndDao.updateStatus(myLctrDmndVo);
        return retVal;
    }

    private void sendEmail(Map<String,Object> member, String dmndSttsCd) throws Exception {
        String dmndSttsCdNm = "139104".equals(dmndSttsCd) ? "수락" : "거절";

        StringBuilder contents = new StringBuilder();
        contents.append("<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif; font-size:16px;font-weight:400;font-stretch:normal;" +
                "font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">");
        contents.append("보내신 환경교육 전문가 섭외 요청이 ").append(dmndSttsCdNm).append("되었습니다. <br /><br />자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해 주십시오.");
        contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
        Context context = new Context();
        context.setVariable("title", "[환경보전협회] 보내신 환경교육 전문가 섭외 요청이 " + dmndSttsCdNm + "되었습니다.");
        context.setVariable("content", contents.toString());
        context.setVariable("portalUrl", CommonUtil.portalUrl);
        String mailContents = templateEngine.process("mail/mail_basic_template", context);
        String mailTitle = "[환경보전협회] 보내신 환경교육 전문가 섭외 요청이 " + dmndSttsCdNm + "되었습니다.";

        MailVo mailVo = new MailVo();
        mailVo.setRcptnEmail((String)member.get("EML"));
        mailVo.setTitle(mailTitle);
        mailVo.setCntnts(mailContents);
        mailVo.setRcptnUserid((Integer)member.get("USERID"));
        mailVo.setSndngSeCd("U");

        mailService.sendMail(mailVo); // 이메일 발송
    }

    private int sendNtcn(MyLctrDmndVo myLctrDmnd, String dmndSttsCd) throws Exception {
        String dmndSttsCdNm = "139104".equals(dmndSttsCd) ? "수락" : "거절";
        StringBuilder sb = new StringBuilder();
        NtcnVo ntcnVo = new NtcnVo();

        sb.append("환경교육 전문가 섭외 요청이 ").append(dmndSttsCdNm).append("되었습니다.\r\n");

        if ("139104".equals(dmndSttsCd)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            String lctrBgngYmd = simpleDateFormat.format(myLctrDmnd.getLctrBgngDt());
            sb.append(lctrBgngYmd).append("에 환경교육이 진행됩니다.\r\n");
        }
        sb.append("자세한 내용은 전문가 섭외 관리 메뉴를 확인해 주십시오.");

        ntcnVo.setUserid(myLctrDmnd.getUserid());
        ntcnVo.setTtl("환경교육 전문가 섭외 요청 " + dmndSttsCdNm);
        ntcnVo.setCn(sb.toString());
        ntcnVo.setInqYn("N");
        ntcnVo.setKndCd("245101");
        ntcnVo.setMvmnurl("/front/mypage/exprtPool/relationList.html");

        int retVal = ntcnDao.insertNtcn(ntcnVo);
        return retVal;
    }

    private void sendAlimtalk(Map<String,Object> member, String dmndSttsCd) throws Exception {
        String templateCode = "139104".equals(dmndSttsCd) ? "keep-010" : "keep-011";
        String recipientList = "[{\"recipientNo\": \"" + (String) member.get("MOBLPHON") + "\",\"templateParameter\": {\"nm\":\"" + (String)member.get("NM") + "\"} }]";

        alimtalkService.sendAlimtalk(templateCode, "", recipientList);
    }
}
