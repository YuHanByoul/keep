package com.kbrainc.plum.mng.refndMng.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.cmm.service.SmsNhnServiceImpl;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.mng.refndMng.model.RefndMngDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.service
* - RefndMngServiceImpl.java
* </pre>
*
* @ClassName : RefndMngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Slf4j
@Service
public class RefndMngServiceImpl extends PlumAbstractServiceImpl implements RefndMngService { 
    
    @Autowired
    private RefndMngDao refndMngDao;
    
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
    * 환불 신청 목록 조회 
    *
    * @Title : selectRefndMngList
    * @Description : 환불 신청 목록 조회
    * @param ResveReqstVo  객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<ResveReqstVo> selectRefndMngList(ResveReqstVo resveReqstVo) throws Exception{
        return refndMngDao.selectRefndMngList(resveReqstVo);
    }
    
    /**
     * 환불 완료 목록 조회
     *
     * @Title : selectRefndMngCompleteList
     * @Description : 환불 완료 목록 조회
     * @param ResveReqstVo 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<ResveReqstVo> selectRefndMngCompleteList(ResveReqstVo resveReqstVo) throws Exception{
         return refndMngDao.selectRefndMngList(resveReqstVo);
     }

    /**
     * 환불 완료 처리 
     *
     * @Title : updateRefndComplete
     * @Description : 환불 완료 처리
     * @param ResveReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndComplete(ResveReqstVo resveReqstVo) throws Exception{
        
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndComplete(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        
        sendMsgForRefnd(resveReqstVo);
        
        return resInt;
    }

    /**
     * 환불 요청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 환불 요청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndCancel(ResveReqstVo resveReqstVo) throws Exception{
        
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndCancel(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyids(resveReqstVo.getAplyids());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 환불완료취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Transactional
    public int updateRefndRollback(ResveReqstVo resveReqstVo) throws Exception{
        int resInt = 0 ;
        resInt += refndMngDao.updateRefndRollback(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyids(resveReqstVo.getAplyids());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }
    /**
     * 환불 요청 취소 처리(재승인)전 기예약 없는(재승인 가능한) 신청건 체크 및 호출    
     *
     * @Title : selectSuitableAplyids
     * @Description : 환불 요청 취소 처리(재승인)전 기예약 없는(재승인 가능한) 신청건 체크 및 호출
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public String[] selectSuitableAplyids(ResveReqstVo resveReqstVo) throws Exception{
        return refndMngDao.selectSuitableAplyids(resveReqstVo);
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
    public int sendMsgForRefnd(ResveReqstVo resveReqstVo) throws Exception{
        int resInt = 0;
        try {
            
            ResveReqstVo resultVo = resveReqstDao.selectResveReqstInfo(resveReqstVo);
            //알림 저장
            if(StringUtil.isNotNull(resultVo.getAplcntid()) ) {
                
                NtcnVo ntcnVo = new NtcnVo();
                ntcnVo.setUserid(Integer.parseInt(resultVo.getAplcntid()));
                ntcnVo.setTtl("환경교육 시설 이용료 환불 처리 완료");
                
                ntcnVo.setCn(
                        "다음 시설의 이용료가 환불처리되었습니다."
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
                contents.append("님 다음 시설의 이용료가 환불처리되었습니다." );
                contents.append("<br /><br />시설명 : ");
                contents.append(resultVo.getFcltNm());
                contents.append("<br /><br />문의사항은 국가환경교육 통합플랫폼의 1:1 문의 게시판에 남겨주시거나, 관리자에게 문의를 부탁드립니다.<br /><br />" );
                contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
                Context context = new Context();
                context.setVariable("title",resultVo.getFcltNm()+"'의 이용료가 환불 처리되었습니다."); 
                context.setVariable("content", contents.toString());
                context.setVariable("portalUrl", CommonUtil.portalUrl);
                String mailContents = templateEngine.process("mail/mail_basic_template", context);
                String mailTitle ="[환경보전협회] "+resultVo.getFcltNm()+"의 이용료가 환불 처리되었습니다.";
                MailVo mailVo = new MailVo(null, resultVo.getAplcntEml(), mailTitle, mailContents, Integer.valueOf(resveReqstVo.getUser().getUserid()), "U", Integer.valueOf(resultVo.getAplcntid()));
                mailService.sendMail(mailVo); // 이메일 발송
            }
            
            String phone = resultVo.getAplcntMoblphon();
            if(!"".equals(StringUtil.nvl(phone))) {
                //SMS 발송 
                String[] phonlist = new String[]{phone};
                String msg = "[환경보전협회] 시설 이용료 환불 처리가 완료되었습니다.";
                Map<String, Object> resMap = smsNhnService.sendSms(msg,phonlist,""); // sms 발송
                String result = (String)((Map<String, Object>)(resMap.get("header"))).get("resultMessage");
                
                //알림톡 발송
                String recipientList = "[{\"recipientNo\": \""+phone+"\",\"templateParameter\": {\"nm\":\""+resultVo.getAplcntNm()+"\",\"fcltNm\":\""+resultVo.getFcltNm()+"\"}}]"; 
                alimtalkService.sendAlimtalk("keep-026","",recipientList);
            }
        }catch (RuntimeException e) {
            log.error("sendMsgForRefnd");
        }catch (Exception e) {
            log.error("sendMsgForRefnd");
        }
        return resInt;   
    }
}
