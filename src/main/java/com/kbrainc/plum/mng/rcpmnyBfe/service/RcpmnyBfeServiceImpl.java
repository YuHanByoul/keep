package com.kbrainc.plum.mng.rcpmnyBfe.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.cmm.service.SmsNhnServiceImpl;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeDao;
import com.kbrainc.plum.mng.refndMng.service.RefndMngServiceImpl;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

/**
* 입금 전 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyBfe.service
* - RcpmnyBfeServiceImpl.java
* </pre>
*
* @ClassName : RcpmnyBfeServiceImpl
* @Description : 입금 전 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Slf4j
@Service
public class RcpmnyBfeServiceImpl extends PlumAbstractServiceImpl implements RcpmnyBfeService {
    
    @Autowired
    private RcpmnyBfeDao rcpmnyBfeDao;
    
    @Autowired
    private ResveReqstDao resveReqstDao;
    
    @Autowired
    private NtcnDao ntcnDao;
    
    @Autowired @Qualifier("MailNhnService")
    private MailService mailService;
    
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SmsNhnServiceImpl smsNhnService;
    
    @Autowired
    private AlimtalkNhnService alimtalkService;
    
    /**
    * 입금 전 목록 조회
    *
    * @Title : selectRcpmnyBfeList
    * @Description : 입금 전 목록 조회
    * @param rcpmnyBfeVo 입금 전정보 객체
    * @throws Exception 예외
    * @return List<RcpmnyBfeVo>
    */
    @Override
    public List<ResveReqstVo> selectRcpmnyBfeList(ResveReqstVo resveReqstVo) throws Exception{
        return rcpmnyBfeDao.selectRcpmnyBfeList(resveReqstVo);
    }

    /**
    * 입금 전 상세정보
    *
    * @Title : selectRcpmnyBfeInfo
    * @Description : 입금 전 상세정보
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    @Override
    public ResveReqstVo selectRcpmnyBfeInfo(ResveReqstVo resveReqstVo) throws Exception {
        return rcpmnyBfeDao.selectRcpmnyBfeInfo(resveReqstVo);
    }

    /**
    * 입금 확인 전 상세정보
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
    * @param rcpmnyBfeVo 입금 확인 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    @Override
    public ResveReqstVo selectDsptCheckInfo(ResveReqstVo resveReqstVo) throws Exception {
        return rcpmnyBfeDao.selectDsptCheckInfo(resveReqstVo);
    }

    /**
     * 입금 확인 처리
     *
     * @Title : updateDsptCheck
     * @Description : 입금 확인 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional
    public int updateDsptCheck(ResveReqstVo resveReqstVo) throws Exception {
        
        int resInt = 0 ;
        resInt += rcpmnyBfeDao.updateDsptCheck(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        
        sendMsgForRevse(resveReqstVo);
        
        return resInt; 
    }

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateResveCancel
     * @Description : 예약 신청 취소 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional
    public int updateResveCancel(ResveReqstVo resveReqstVo) throws Exception {
        
        int resInt = 0 ;
        resInt += rcpmnyBfeDao.updateResveCancel(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyids(resveReqstVo.getAplyids());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt; 
    }
    /**
     * 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     *
     * @Title : isThereResveNow
     * @Description : 현재 해당 신청건의 예약 일정중 진행중인 예약이 있는지 확인
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public String isThereResveNow(ResveReqstVo resveReqstVo) throws Exception{
        return rcpmnyBfeDao.isThereResveNow(resveReqstVo);
    }
    
    /**
     * 입금 확인등 알림등 발송  
     *
     * @Title       : updateCnsltngInfo 
     * @Description : 컨설팅 상태 정보 수정
     * @param CnsltngVo CnsltngVo객체
     * @return int
     * @throws Exception 예외
     */
    @Async
    public int sendMsgForRevse(ResveReqstVo resveReqstVo) throws Exception{
        
        int resInt = 0;
        try {
            ResveReqstVo resultVo = resveReqstDao.selectResveReqstInfo(resveReqstVo);
            //알림 저장
            if(StringUtil.isNotNull(resultVo.getAplcntid()) ) {
                
                NtcnVo ntcnVo = new NtcnVo();
                ntcnVo.setUserid(Integer.parseInt(resultVo.getAplcntid()));
                ntcnVo.setTtl("환경교육 시설 예약 신청 승인");
                ntcnVo.setCn(
                        "다음 시설의 예약 신청이 승인되었습니다."
                                +"<br/>이용일시 : "+resultVo.getDe()
                                +"<br/>시설명 : "+ resultVo.getFcltNm()
                                +"<br/>문의사항은 1:1문의 게시판에 남겨주시거나, 관리자에게 문의를 부탁드립니다." );
                ntcnVo.setInqYn("N");
                ntcnVo.setKndCd("156102");
                ntcnVo.setMvmnurl("/front/mypage/mypageEnvReqst/mypageEnvReqstList.html");
                ntcnDao.insertNtcn(ntcnVo);
            }
            
            if(!"".equals(StringUtil.nvl(resultVo.getAplcntEml()))) {
                //메일 발송
                StringBuilder contents = new StringBuilder();
                contents.append("<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">");
                contents.append(resultVo.getAplcntNm());
                contents.append("님  다음 시설의 예약 신청이 승인되었습니다." );
                contents.append("<br /><br />이용 일시 : ");
                contents.append(resultVo.getDe());
                contents.append("<br />시설명 : ");
                contents.append(resultVo.getFcltNm());
                contents.append("<br /><br />문의사항은 국가환경교육 통합플랫폼의 1:1 문의 게시판에 남겨주시거나, 관리자에게 문의를 부탁드립니다.<br /><br />" );
                contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
                Context context = new Context();
                context.setVariable("title", "[환경보전협회] '"+resultVo.getFcltNm()+"'의 예약 신청이 승인 되었습니다."); 
                context.setVariable("content", contents.toString());
                context.setVariable("portalUrl", CommonUtil.portalUrl);
                String mailContents = templateEngine.process("mail/mail_basic_template", context);
                String mailTitle ="[환경보전협회] "+resultVo.getFcltNm()+"의 예약 신청이 승인 되었습니다.";
                MailVo mailVo = new MailVo(null, resultVo.getAplcntEml(), mailTitle, mailContents, Integer.valueOf(resveReqstVo.getUser().getUserid()), "U", Integer.valueOf(resultVo.getAplcntid()));
                mailService.sendMail(mailVo); // 이메일 발송
            }
            
            String phone = resultVo.getAplcntMoblphon();
            if(!"".equals(StringUtil.nvl(phone))) {
                //SMS 발송 
                String[] phonlist = new String[]{phone};
                String msg = "[환경보전협회]시설 예약 신청 승인. 국가환경교육 통합플랫폼을 확인해주세요.";
                Map<String, Object> resMap = smsNhnService.sendSms(msg,phonlist,""); // sms 발송
                String result = (String)((Map<String, Object>)(resMap.get("header"))).get("resultMessage");
                //알림톡 발송
                //String recipientList = "[{\"recipientNo\": \""+phone+"\",\"templateParameter\": {\"nm\":\""+resultVo.getAplcntNm()+"\",\"bgngDt\":\""+resultVo.getDe()+" \"endDt\":\"''\"}}]"; 
                String recipientList = "[{\"recipientNo\": \""+phone+"\",\"templateParameter\": {\"nm\":\""+resultVo.getAplcntNm()+"\",\"bgngDt\":\""+resultVo.getDe()+"\",\"endDt\":\"\", \"fcltNm\":\""+resultVo.getFcltNm()+"\"}}]"; 
                alimtalkService.sendAlimtalk("keep-023","",recipientList);
            }
        }catch (RuntimeException e) {
            log.error("sendMsgForRevse");
        }catch (Exception e) {
            log.error("sendMsgForRevse");
        }
        return resInt;   
    }
}
