package com.kbrainc.plum.cmm.scheduling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.cmm.scheduling.model.BatchJobDao;
import com.kbrainc.plum.cmm.service.AlimtalkNhnService;
import com.kbrainc.plum.cmm.service.SmsNhnServiceImpl;
import com.kbrainc.plum.rte.scheduling.annotation.SchedulingHistory;
import com.kbrainc.plum.rte.scheduling.annotation.Triggerid;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

/**
* 스케줄링 배치잡서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - BatchJobServiceImpl.java
* </pre>
*
* @ClassName   : BatchJobServiceImpl 
* @Description : 스케줄링 배치잡서비스 구현 클래스. 
* @author      : KBRAINC
* @date        : 2021. 3. 8.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Service
public class BatchJobServiceImpl implements BatchJobService {

	@Autowired
    private BatchJobDao batchJobDao;
	
	@Autowired
    private SmsNhnServiceImpl smsService;
	
	@Autowired
	private AlimtalkNhnService alimtalkNhnService;
	
	@Autowired
    private TemplateEngine templateEngine;
	
	@Autowired @Qualifier("MailNhnService")
    private MailService mailService;
	
	/**
	* 휴면계정처리
	*
	* @Title       : userDrmncyProcess 
	* @Description : 휴면계정처리
	* @param triggerid
	* @return void 리턴값없음
	* @throws Exception 예외
	*/
	@SchedulingHistory
	@Transactional
	public void userDrmncyProcess(@Triggerid int triggerid) throws Exception {
	    //휴면처리 대상자 리스트
	    List<Map<String, Object>> emailSendUserList = batchJobDao.selectDrmncyUserList();
	    
	    if(emailSendUserList.size() > 0) {
	        for(Map<String, Object> emailSendUser : emailSendUserList) {
	            //휴면계정 테이블로 이관
	              batchJobDao.insertUserdrmncyInfo((int) emailSendUser.get("USERID"));
	              //회원정보 테이블에서 개인정보 삭제 및 휴면상태 전환
	              batchJobDao.updateUserInfo((int) emailSendUser.get("USERID"));
	              
	              
	              if(emailSendUser.get("EML") != null ) {
	                  
	                  Context context = new Context();
	                  context.setVariable("title", "휴면계정 전환 안내입니다.");
	                  context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
	                  String content = "<tr>"
	                      + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
	                      + "        안녕하세요, 국가환경교육 통합플랫폼입니다.<br>"
	                      + "        <br>"
	                      + "        회원님의 소중한 개인정보 보호를 위해, 장기간 로그인 이력이 없는 다음 계정을 휴면계정으로 전환했음을 알려드립니다.<br>"
	                      + "        <br>"
	                      + "        대상계정 : " + emailSendUser.get("ACNT") + "<br>"
	                      + "        <br>"
                          + "        회원가입 시 입력해주신 개인정보는 안전하게 분리 보관되며, 국가환경교육 통합플랫폼 홈페이지에서 휴면해제를 해주시면 계정 복구 및 재이용이 가능합니다.<br>"
                          + "        <br>"
                          + "        본 메일은 회원님의 이메일 수신동의 여부와 관계없이 법률에 의거하여 전 회원 대상으로 발송되는 메일입니다."
	                      + "    </td>"
	                      + "</tr>"
	                      + "<tr>"
	                      + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
	                      + "</tr>";
	                  context.setVariable("content", content);
	                  String contents = templateEngine.process("mail/mail_basic_template", context);
	                  
	                  MailVo mailVo = new MailVo();
	                  mailVo.setRcptnEmail((String) emailSendUser.get("EML"));
	                  mailVo.setTitle("휴면계정 전환 안내입니다.");
	                  mailVo.setCntnts(contents);
	                  
	                  Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
	              }

	          }
	    }
	    
	}
	
	
	/**
    * 유아환경교육 안내 알림톡 발송
    *
    * @Title       : infntEnveduMsgNoticeMsgSend 
    * @Description : 유아환경교육 안내 알림톡 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
	@SchedulingHistory
    public void infntEnveduMsgNoticeMsgSend(@Triggerid int triggerid) throws Exception {
        
	    //발송대상자 확인
	    List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectInfntEnveduSmsSendList(1);
	    
	    if(alimTalkSendUserList.size() > 0) {
	        ObjectMapper mapper = new ObjectMapper();
	        
	        for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
	            List<Object> recipientList = new ArrayList<>(); 
	            
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
	  
	            Map<String, Object> templateParameter = new HashMap<String, Object>();
	            templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
	            templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
	            templateParameter.put("tcherNope", alimTalkSendUser.get("TCHER_NOPE"));
	            templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
	            templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
	  
	            map.put("templateParameter", templateParameter);
	            
	            recipientList.add(map); 
	            
	            String recipientListStr = mapper.writeValueAsString(recipientList);
	            
	            //탬플릿 없음
	            alimtalkNhnService.sendAlimtalk("infntEnveduMsg", (String) alimTalkSendUser.get("SENDRSVTIME"), recipientListStr);
	        }

	    }
	    
    }


	/**
    * 푸름이 이동 환경교육 안내 알림톡 발송
    *
    * @Title       : mvnEnveduMsgNoticeMsgSend 
    * @Description : 푸름이 이동 환경교육 안내 알림톡 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @SchedulingHistory
    public void mvnEnveduMsgNoticeMsgSend(@Triggerid int triggerid) throws Exception {
      //발송대상자 확인
        List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectMvnEnveduSmsSendList(1);
        
        if(alimTalkSendUserList.size() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                List<Object> recipientList = new ArrayList<>(); 
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                //map.put("recipientTime", alimTalkSendUser.get("SENDRSVTIME"));
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
      
                map.put("templateParameter", templateParameter);
      
                recipientList.add(map); 
                
                String recipientListStr = mapper.writeValueAsString(recipientList);
                
                
                alimtalkNhnService.sendAlimtalk("infntEnveduMsg", (String) alimTalkSendUser.get("SENDRSVTIME"), recipientListStr);
            }
            
        }
        
    }
    
    /**
     * 휴면계정 전환 사전 안내 메일 발송
     *
     * @Title       : userDrmncyNtcMailSend 
     * @Description : 휴면계정 전환 사전 안내 메일 발송
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     @SchedulingHistory
     public void userDrmncyNtcMailSend(@Triggerid int triggerid) throws Exception {
         //발송대상자 확인
         List<Map<String, Object>> emailSendUserList = batchJobDao.selectDrmncyNtcMailSendUserList();
         
         if(emailSendUserList.size() > 0) {
             List<MailRcptnVo> mailToArray = new ArrayList<>();
             for(Map<String, Object> emailSendUser : emailSendUserList) {
                 
                 if(emailSendUser.get("EML") != null ) {
                     Context context = new Context();
                     context.setVariable("title", "휴면계정 전환 사전 안내입니다.");
                     context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                     String content = "<tr>"
                             + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                             + "        안녕하세요, 국가환경교육 통합플랫폼입니다.<br>"
                             + "        <br>"
                             + "        소중한 개인정보 보호를 위해, 장기간 로그인 이력이 없는 회원님들의 개인정보를 안전하게 분리 보관하고 ID를 휴면 상태로 전환할 예정입니다.<br>"
                             + "        <br>"
                             + "        대상 계정 : " + emailSendUser.get("ACNT") + "<br>"
                             + "        전환 예정일 : " + emailSendUser.get("PRNMNT_DT") + "<br>"
                             + "        <br>"
                             + "        전환을 원치 않으시는 경우, 휴면계정 전환 예정일이 되기 전까지 국가환경교육 통합플랫폼 홈페이지에 로그인을 해주시기 바랍니다.<br>"
                             + "        <br>"
                             + "        본 메일은 회원님의 이메일 수신동의 여부와 관계없이 법률에 의거하여 전 회원 대상으로 발송되는 메일입니다."
                             + "    </td>"
                             + "</tr>"
                             + "<tr>"
                             + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                             + "</tr>";
                     context.setVariable("content", content);
                     
                     String contents = templateEngine.process("mail/mail_basic_template", context);
                     
                     MailVo mailVo = new MailVo();
                     mailVo.setRcptnEmail((String) emailSendUser.get("EML"));
                     mailVo.setTitle("휴면계정 전환 사전 안내입니다.");
                     mailVo.setCntnts(contents);
                     
                     Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                 }
             }
         }
     }


    /**
    * 3개월 이상된 알림메시지 삭제
    *
    * @Title       : deleteOldNtcMsg 
    * @Description : 3개월 이상된 알림메시지 삭제
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
     @SchedulingHistory
     @Transactional
     public void deleteOldNtcMsg(@Triggerid int triggerid) throws Exception {
         batchJobDao.deleteOldNtcMsg();
     }
     
     /**
     * 푸름이 이동환경교실 만족도 조사 안내 메시지 발송
     *
     * @Title       : mvnEnveduDgstfnMsgSend 
     * @Description : 푸름이 이동환경교실 만족도 조사 안내 메시지 발송
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     @SchedulingHistory
     public void mvnEnveduDgstfnMsgSend(@Triggerid int triggerid) throws Exception {
         //발송대상자 확인
         List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectMvnEnveduSmsSendList(2);
       
         if(alimTalkSendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
           
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 List<Object> recipientList = new ArrayList<>(); 

                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
     
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                 templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
                 templateParameter.put("dgstfnUrl", "만족도 조사 URL");
     
                 map.put("templateParameter", templateParameter);
     
                 recipientList.add(map); 
                 
                 String recipientListStr = mapper.writeValueAsString(recipientList);
                 
                 
                 alimtalkNhnService.sendAlimtalk("enveduAfterMsg", (String) alimTalkSendUser.get("SENDRSVTIME"), recipientListStr);
             }
             
             
           
             
         }
          
     }
      
    /**
    * 유아환경교육 만족도 조사 안내 메시지 발송
    *
    * @Title       : infntEnveduDgstfnMsgSend 
    * @Description : 유아환경교육 만족도 조사 안내 메시지 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    @SchedulingHistory
    public void infntEnveduDgstfnMsgSend(@Triggerid int triggerid) throws Exception {
     
        //발송대상자 확인
        List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectInfntEnveduSmsSendList(2);
       
        if(alimTalkSendUserList.size() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 List<Object> recipientList = new ArrayList<>(); 
                 
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
         
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("tcherNope", alimTalkSendUser.get("TCHER_NOPE"));
                 templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                 templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
         
                 map.put("templateParameter", templateParameter);
         
                 recipientList.add(map); 
                 
                 String recipientListStr = mapper.writeValueAsString(recipientList);
                 
                 
                 alimtalkNhnService.sendAlimtalk("enveduAfterMsg", (String) alimTalkSendUser.get("SENDRSVTIME"), recipientListStr);
            }
                 
        }
           
     }
       
     /**
     * 전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송
     *
     * @Title       : exprtEduBfrMsgSend 
     * @Description : 전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     @SchedulingHistory
     public void exprtEduBfrMsgSend(@Triggerid int triggerid) throws Exception {
            
         //발송대상자 확인
         List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectExprtEduMsgSendUser(1);
            
         if(alimTalkSendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
                
            // 전문가 대상 메세지 발송
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                List<Object> recipientList1 = new ArrayList<>(); 
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("ASS_MOBLPHON")); 
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("ASS_NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("startTime", alimTalkSendUser.get("LCTR_BGNG_DT"));
      
                map.put("templateParameter", templateParameter);
          
                recipientList1.add(map); 
                
                String recipientListStr1 = mapper.writeValueAsString(recipientList1);
                
                alimtalkNhnService.sendAlimtalk("exprtEduBeforeMsg", (String) alimTalkSendUser.get("BFR_SEND_DT"), recipientListStr1);
            }
                  
            
                
            // 전문가 섭외자 대상 메세지 발송
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                List<Object> recipientList2 = new ArrayList<>(); 
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("startTime", alimTalkSendUser.get("LCTR_BGNG_DT"));
          
                map.put("templateParameter", templateParameter);
          
                recipientList2.add(map); 
                
                String recipientListStr2 = mapper.writeValueAsString(recipientList2);
                
                alimtalkNhnService.sendAlimtalk("exprtEduBeforeMsg", (String) alimTalkSendUser.get("BFR_SEND_DT"), recipientListStr2);
            }
                  
        }
            
    }
        
    /**
     * 전문가 섭외자 대상 만족도 평가 안내 메시지 발송
     *
     * @Title       : exprtEduAftrMsgSend 
     * @Description : 전문가 섭외자 대상 만족도 평가 안내 메시지 발송
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     @SchedulingHistory
     public void exprtEduAftrMsgSend(@Triggerid int triggerid) throws Exception {
             
         //발송대상자 확인
         List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectExprtEduMsgSendUser(2);
             
         if(alimTalkSendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
             
             // 전문가 섭외자 대상 메세지 발송
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 List<Object> recipientList = new ArrayList<>(); 
                 
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
       
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("startTime", alimTalkSendUser.get("LCTR_BGNG_DT"));
       
                 map.put("templateParameter", templateParameter);
           
                 recipientList.add(map); 
                 
                 String recipientListStr = mapper.writeValueAsString(recipientList);
                 
                 alimtalkNhnService.sendAlimtalk("exprtEduAfterMsg", (String) alimTalkSendUser.get("AFTR_SEND_DT"), recipientListStr);
             }
             
         }
             
     }
         
     /**
      * 미입금 시설예약 취소처리
      *
      * @Title       : flctRsvCancle 
      * @Description : 미입금 시설예약 취소처리
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
      @SchedulingHistory
      @Transactional
      public void flctRsvCancle(@Triggerid int triggerid) throws Exception {
          //휴면처리 대상자 리스트
          List<Map<String, Object>> flctRsvCancleUserList = batchJobDao.selectFlctRsvCancleUserList();
              
          if(flctRsvCancleUserList.size() > 0) {
              for(Map<String, Object> flctRsvCancleUser : flctRsvCancleUserList) {
                  // 시설예약 취소처리
                  batchJobDao.updateFlctRsvCancle((int) flctRsvCancleUser.get("APLYID"));
                    
                  if(flctRsvCancleUser.get("APLCNT_EML") != null ) {
                          
                       Context context = new Context();
                       context.setVariable("title", "시설예약 취소 안내입니다.");
                       context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                       String content = "<tr>"
                           + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                           + "        안녕하세요, 국가환경교육 통합플랫폼입니다.<br>"
                           + "        시설 이용료 입금이 확인되지 않아 다음 시설의 예약 신청이 취소 처리되었습니다.<br>"
                           + "        <br>"
                           + "        이용예정일시 : " + flctRsvCancleUser.get("BGNG_DT_STR") + "<br>"
                           + "        시설명 : " + flctRsvCancleUser.get("FCLT_NM") + " " + flctRsvCancleUser.get("SPCE_NM") + "<br>"
                           + "        <br>"
                           + "        시설 이용을 원하시는 경우 예약을 다시 진행해주시기 바랍니다. 문의사항은 마이페이지의 1:1 문의 게시판에 남겨주시거나 담당자에게 문의해주세요."
                           + "    </td>"
                           + "</tr>"
                           + "<tr>"
                           + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                           + "</tr>";
                       context.setVariable("content", content);
                       String contents = templateEngine.process("mail/mail_basic_template", context);
                           
                       MailVo mailVo = new MailVo();
                       mailVo.setRcptnEmail((String) flctRsvCancleUser.get("APLCNT_EML"));
                       mailVo.setTitle("시설예약 취소 안내입니다.");
                       mailVo.setCntnts(contents);
                        
                       Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                  }
              }
          }
      }
      
      /**
     * 시설 이용자 대상 만족도 평가 안내 메시지 발송
     *
     * @Title       : flctRsvDgstfnNtcMsgSend 
     * @Description : 시설 이용자 대상 만족도 평가 안내 메시지 발송
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     @SchedulingHistory
     public void flctRsvDgstfnNtcMsgSend(@Triggerid int triggerid) throws Exception {
               
        //발송대상자 확인
        List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectFlctRsvDgstfnNtcMsgSend();
               
           if(alimTalkSendUserList.size() > 0) {
               ObjectMapper mapper = new ObjectMapper();
               
               // 시설 이용자 대상 만족도 평가 안내 메시지 발송
              for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                  List<Object> recipientList = new ArrayList<>(); 
                  
                  Map<String, Object> map = new HashMap<String, Object>();
                  map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
        
                  Map<String, Object> templateParameter = new HashMap<String, Object>();
                  templateParameter.put("nm", alimTalkSendUser.get("NM"));
                  templateParameter.put("fcltNm", alimTalkSendUser.get("FCLT_NM") + " " + alimTalkSendUser.get("SPCE_NM"));
        
                  map.put("templateParameter", templateParameter);
            
                  recipientList.add(map); 
                  
                  String recipientListStr = mapper.writeValueAsString(recipientList);
                  
                  alimtalkNhnService.sendAlimtalk("fcltDgstfnMsg", (String) alimTalkSendUser.get("END_DT_STR"), recipientListStr);
              }
           }
     }
     
     /**
      * 유아환경교육관, 푸름이 이동환경교실 교육 3일 전 안내 메시지 발송
      *
      * @Title       : infntMvnEnveduBfrMsgSend 
      * @Description : 유아환경교육관, 푸름이 이동환경교실 교육 3일 전 안내 메시지 발송
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
     @SchedulingHistory
     public void infntMvnEnveduBfrMsgSend(@Triggerid int triggerid) throws Exception {
         //발송대상자 확인
         List<Map<String, Object>> sendUserList = batchJobDao.selectEnvEduMsgSendUserList(1);
         
         if(sendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
             
             for(Map<String, Object> sendUser : sendUserList) {
                 if(sendUser.get("MOBLPHON") != null && !"".equals(sendUser.get("MOBLPHON"))) {
                     List<Object> recipientList = new ArrayList<>(); 
                     
                     Map<String, Object> map = new HashMap<String, Object>();
                     map.put("recipientNo", sendUser.get("MOBLPHON")); 
           
                     Map<String, Object> templateParameter = new HashMap<String, Object>();
                     templateParameter.put("nm", sendUser.get("USER_NM"));
                     templateParameter.put("prgrmNm", sendUser.get("PRGRM_NM"));
                     templateParameter.put("eduDt", sendUser.get("START_TIME"));
           
                     map.put("templateParameter", templateParameter);
           
                     recipientList.add(map); 
                     
                     String recipientListStr = mapper.writeValueAsString(recipientList);
                     
                     
                     alimtalkNhnService.sendAlimtalk("keep-012", (String) sendUser.get("SENDRSVTIME"), recipientListStr);
                 }
                 if(sendUser.get("EML") != null && !"".equals(sendUser.get("EML"))) {
                     Context context = new Context();
                     context.setVariable("title", "[환경보전협회] " + sendUser.get("START_TIME").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                     String content = "<tr>"
                         + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                         + "        " + sendUser.get("USER_NM") + "님. 진행 예정인 환경교육이 있습니다.<br>"
                         + "        교육 일정을 확인해주세요<br>"
                         + "        <br>"
                         + "        교육일자 : " + sendUser.get("START_TIME").toString().substring(0, 10) + "<br>"
                         + "        <br>"
                         + "        자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해주세요."
                         + "    </td>"
                         + "</tr>"
                         + "<tr>"
                         + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                         + "</tr>";
                     context.setVariable("content", content);
                     String contents = templateEngine.process("mail/mail_basic_template", context);
                         
                     MailVo mailVo = new MailVo();
                     mailVo.setRcptnEmail((String) sendUser.get("EML"));
                     mailVo.setTitle("[환경보전협회] " + sendUser.get("START_TIME").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     mailVo.setCntnts(contents);
                      
                     Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                 }
             }
         }
     }
     
     /**
      * 유아환경교육관, 푸름이 이동환경교실 교육 3일 후 만족도 평가 요청 안내 메시지 발송
      *
      * @Title       : infntMvnEnveduAftrMsgSend 
      * @Description : 유아환경교육관, 푸름이 이동환경교실 교육 3일 후 만족도 평가 요청 안내 메시지 발송
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
     @SchedulingHistory
     public void infntMvnEnveduAftrMsgSend(@Triggerid int triggerid) throws Exception {
         //발송대상자 확인
         List<Map<String, Object>> sendUserList = batchJobDao.selectEnvEduMsgSendUserList(2);
         
         if(sendUserList.size() > 0) {
             for(Map<String, Object> sendUser : sendUserList) {
                 ObjectMapper mapper = new ObjectMapper();
                 
                 if(sendUser.get("MOBLPHON") != null && !"".equals(sendUser.get("MOBLPHON"))) {
                     List<Object> recipientList = new ArrayList<>(); 
                     
                     Map<String, Object> map = new HashMap<String, Object>();
                     map.put("recipientNo", sendUser.get("MOBLPHON")); 
           
                     Map<String, Object> templateParameter = new HashMap<String, Object>();
                     templateParameter.put("nm", sendUser.get("USER_NM"));
                     templateParameter.put("prgrmNm", sendUser.get("PRGRM_NM"));
                     templateParameter.put("eduDt", sendUser.get("START_TIME"));
           
                     map.put("templateParameter", templateParameter);
           
                     recipientList.add(map); 
                     
                     String recipientListStr = mapper.writeValueAsString(recipientList);
                     
                     
                     alimtalkNhnService.sendAlimtalk("keep-013", (String) sendUser.get("SENDRSVTIME"), recipientListStr);
                 }
                 if(sendUser.get("EML") != null && !"".equals(sendUser.get("EML"))) {
                     Context context = new Context();
                     context.setVariable("title", "[환경보전협회] 수강하신 교육의 후기를 남겨주세요.");
                     context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                     String content = "<tr>"
                         + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                         + "        " + sendUser.get("USER_NM") + "님, 수강하신 교육은 어떠셨나요?<br>"
                         + "        " + sendUser.get("PRGRM_NM") +"에 대한 후기를 남겨주세요.<br>"
                         + "        자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해주세요."
                         + "    </td>"
                         + "</tr>"
                         + "<tr>"
                         + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                         + "</tr>";
                     context.setVariable("content", content);
                     String contents = templateEngine.process("mail/mail_basic_template", context);
                         
                     MailVo mailVo = new MailVo();
                     mailVo.setRcptnEmail((String) sendUser.get("EML"));
                     mailVo.setTitle("[환경보전협회] 수강하신 교육의 후기를 남겨주세요.");
                     mailVo.setCntnts(contents);
                      
                     Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                 }
             }
         }
     }
     
     /**
      * 전문가 섭외자와 대상 전문가 대상 3일전 교육 알림 메시지 발송
      *
      * @Title       : exprtBfrMsgSend 
      * @Description : 전문가 섭외자와 대상 전문가 대상 3일전 교육 알림 메시지 발송
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
      @SchedulingHistory
      public void exprtBfrMsgSend(@Triggerid int triggerid) throws Exception {
             
          //발송대상자 확인
          List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectExprtMsgSendUser(1);
             
          if(alimTalkSendUserList.size() > 0) {
              ObjectMapper mapper = new ObjectMapper();
                 
             // 전문가 대상 메세지 발송
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 if(alimTalkSendUser.get("ASS_MOBLPHON") != null && !"".equals(alimTalkSendUser.get("ASS_MOBLPHON"))) {
                     List<Object> recipientList1 = new ArrayList<>(); 
                     
                     Map<String, Object> map = new HashMap<String, Object>();
                     map.put("recipientNo", alimTalkSendUser.get("ASS_MOBLPHON")); 
           
                     Map<String, Object> templateParameter = new HashMap<String, Object>();
                     templateParameter.put("nm", alimTalkSendUser.get("ASS_NM"));
                     templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                     templateParameter.put("eduDt", alimTalkSendUser.get("LCTR_BGNG_DT"));
           
                     map.put("templateParameter", templateParameter);
               
                     recipientList1.add(map); 
                     
                     String recipientListStr1 = mapper.writeValueAsString(recipientList1);
                     
                     alimtalkNhnService.sendAlimtalk("keep-012", (String) alimTalkSendUser.get("BFR_SEND_DT"), recipientListStr1);
                 }
                 if(alimTalkSendUser.get("ASS_EML") != null && !"".equals(alimTalkSendUser.get("ASS_EML"))) {
                     Context context = new Context();
                     context.setVariable("title", "[환경보전협회] " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                     String content = "<tr>"
                         + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                         + "        " + alimTalkSendUser.get("ASS_NM") + "님. 진행 예정인 환경교육이 있습니다.<br>"
                         + "        교육 일정을 확인해주세요<br>"
                         + "        <br>"
                         + "        교육일자 : " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + "<br>"
                         + "        <br>"
                         + "        자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해주세요."
                         + "    </td>"
                         + "</tr>"
                         + "<tr>"
                         + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                         + "</tr>";
                     context.setVariable("content", content);
                     String contents = templateEngine.process("mail/mail_basic_template", context);
                         
                     MailVo mailVo = new MailVo();
                     mailVo.setRcptnEmail((String) alimTalkSendUser.get("ASS_EML"));
                     mailVo.setTitle("[환경보전협회] " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     mailVo.setCntnts(contents);
                      
                     Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                 }
             }
             
             // 전문가 섭외자 대상 메세지 발송
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 if(alimTalkSendUser.get("MOBLPHON") != null && !"".equals(alimTalkSendUser.get("MOBLPHON"))) {
                     List<Object> recipientList2 = new ArrayList<>(); 
                     
                     Map<String, Object> map = new HashMap<String, Object>();
                     map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
           
                     Map<String, Object> templateParameter = new HashMap<String, Object>();
                     templateParameter.put("nm", alimTalkSendUser.get("NM"));
                     templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                     templateParameter.put("eduDt", alimTalkSendUser.get("LCTR_BGNG_DT"));
               
                     map.put("templateParameter", templateParameter);
               
                     recipientList2.add(map); 
                     
                     String recipientListStr2 = mapper.writeValueAsString(recipientList2);
                     
                     alimtalkNhnService.sendAlimtalk("keep-012", (String) alimTalkSendUser.get("BFR_SEND_DT"), recipientListStr2);
                 }
                 if(alimTalkSendUser.get("EML") != null && !"".equals(alimTalkSendUser.get("EML"))) {
                     Context context = new Context();
                     context.setVariable("title", "[환경보전협회] " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                     String content = "<tr>"
                         + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                         + "        " + alimTalkSendUser.get("NM") + "님. 진행 예정인 환경교육이 있습니다.<br>"
                         + "        교육 일정을 확인해주세요<br>"
                         + "        <br>"
                         + "        교육일자 : " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + "<br>"
                         + "        <br>"
                         + "        자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해주세요."
                         + "    </td>"
                         + "</tr>"
                         + "<tr>"
                         + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                         + "</tr>";
                     context.setVariable("content", content);
                     String contents = templateEngine.process("mail/mail_basic_template", context);
                         
                     MailVo mailVo = new MailVo();
                     mailVo.setRcptnEmail((String) alimTalkSendUser.get("EML"));
                     mailVo.setTitle("[환경보전협회] " + alimTalkSendUser.get("LCTR_BGNG_DT").toString().substring(0, 10) + " 진행 예정인 환경교육이 있습니다.");
                     mailVo.setCntnts(contents);
                      
                     Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                 }
                 
             }
                   
         }
             
     }
         
     /**
      * 전문가 섭외자 대상 3일 후 만족도 평가 안내 메시지 발송
      *
      * @Title       : exprtAftrMsgSend 
      * @Description : 전문가 섭외자 대상 3일 후 만족도 평가 안내 메시지 발송
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
      @SchedulingHistory
      public void exprtAftrMsgSend(@Triggerid int triggerid) throws Exception {
              
          //발송대상자 확인
          List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectExprtMsgSendUser(2);
              
          if(alimTalkSendUserList.size() > 0) {
              ObjectMapper mapper = new ObjectMapper();
              
              // 전문가 섭외자 대상 메세지 발송
              for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                  if(alimTalkSendUser.get("MOBLPHON") != null && !"".equals(alimTalkSendUser.get("MOBLPHON"))) {
                      List<Object> recipientList = new ArrayList<>(); 
                      
                      Map<String, Object> map = new HashMap<String, Object>();
                      map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
            
                      Map<String, Object> templateParameter = new HashMap<String, Object>();
                      templateParameter.put("nm", alimTalkSendUser.get("NM"));
                      templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                      templateParameter.put("eduDt", alimTalkSendUser.get("LCTR_END_DT"));
            
                      map.put("templateParameter", templateParameter);
                
                      recipientList.add(map); 
                      
                      String recipientListStr = mapper.writeValueAsString(recipientList);
                      
                      alimtalkNhnService.sendAlimtalk("keep-013", (String) alimTalkSendUser.get("AFTR_SEND_DT"), recipientListStr);
                  }
                  if(alimTalkSendUser.get("EML") != null && !"".equals(alimTalkSendUser.get("EML"))) {
                      Context context = new Context();
                      context.setVariable("title", "[환경보전협회] 수강하신 교육의 후기를 남겨주세요.");
                      context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                      String content = "<tr>"
                          + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                          + "        " + alimTalkSendUser.get("NM") + "님, 수강하신 교육은 어떠셨나요?<br>"
                          + "        " + alimTalkSendUser.get("LCTR_END_DT").toString().substring(0, 10) +" 진행된 환경교육의 후기를 남겨주세요.<br>"
                          + "        자세한 내용은 국가환경교육 통합플랫폼의 마이페이지를 확인해주세요."
                          + "    </td>"
                          + "</tr>"
                          + "<tr>"
                          + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                          + "</tr>";
                      context.setVariable("content", content);
                      String contents = templateEngine.process("mail/mail_basic_template", context);
                          
                      MailVo mailVo = new MailVo();
                      mailVo.setRcptnEmail((String) alimTalkSendUser.get("EML"));
                      mailVo.setTitle("[환경보전협회] 수강하신 교육의 후기를 남겨주세요.");
                      mailVo.setCntnts(contents);
                       
                      Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                  }
              }
              
          }
              
      }
      
      /**
       * 공동구매 진행 마감일 안내 메시지 발송
       *
       * @Title       : jntpurchsEndMsgSend 
       * @Description : 공동구매 진행 마감일 안내 메시지 발송
       * @param triggerid
       * @return void 리턴값없음
       * @throws Exception 예외
       */
       @SchedulingHistory
       public void jntpurchsEndMsgSend(@Triggerid int triggerid) throws Exception {
               
           //발송대상자 확인
           List<Map<String, Object>> sendUserList = batchJobDao.selectJntPurchsSendMsgUserList(1);
               
           if(sendUserList.size() > 0) {
               ObjectMapper mapper = new ObjectMapper();
               
               // 공동구매 진행 마감일 안내 메시지 발송
               for(Map<String, Object> sendUser : sendUserList) {
                   if(sendUser.get("MOBLPHON") != null && !"".equals(sendUser.get("MOBLPHON"))) {
                       List<Object> recipientList = new ArrayList<>(); 
                       
                       Map<String, Object> map = new HashMap<String, Object>();
                       map.put("recipientNo", sendUser.get("MOBLPHON")); 
             
                       Map<String, Object> templateParameter = new HashMap<String, Object>();
                       templateParameter.put("nm", sendUser.get("NM"));
                       templateParameter.put("jntpurchsNm", sendUser.get("JNTPURCHS_NM"));
                       templateParameter.put("qnty", sendUser.get("QNTY"));
                       templateParameter.put("amt", sendUser.get("AMT"));
             
                       map.put("templateParameter", templateParameter);
                 
                       recipientList.add(map); 
                       
                       String recipientListStr = mapper.writeValueAsString(recipientList);
                       
                       alimtalkNhnService.sendAlimtalk("keep-020", (String) sendUser.get("END_DT"), recipientListStr);
                   }
                   if(sendUser.get("EML") != null && !"".equals(sendUser.get("EML"))) {
                       Context context = new Context();
                       context.setVariable("title", "[환경보전협회] " + sendUser.get("JNTPURCHS_NM") + " 공동구매 대금을 입금해주세요.");
                       context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                       String content = "<tr>"
                           + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                           + "        " + sendUser.get("NM") + "님, 다음 공동구매가 진행될 예정입니다.<br>"
                           + "        <br>"
                           + "        공동구매명 : " + sendUser.get("JNTPURCHS_NM") +"<br>"
                           + "        구매수량 : " + sendUser.get("QNTY") +"<br>"
                           + "        결제 예상금액 : " + sendUser.get("AMT") +"<br>"
                           + "        <br>"
                           + "        구매내역은 국가환경교육 통합플랫폼의 마이페이지에서 확인하실 수 있습니다.<br>"
                           + "        입금, 배송 등 교구 공동구매와 관련된 문의는 공동구매를 진행한 제휴사에 문의해주시기 바랍니다."
                           + "    </td>"
                           + "</tr>"
                           + "<tr>"
                           + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                           + "</tr>";
                       context.setVariable("content", content);
                       String contents = templateEngine.process("mail/mail_basic_template", context);
                           
                       MailVo mailVo = new MailVo();
                       mailVo.setRcptnEmail((String) sendUser.get("EML"));
                       mailVo.setTitle("[환경보전협회] " + sendUser.get("JNTPURCHS_NM") + " 공동구매 대금을 입금해주세요.");
                       mailVo.setCntnts(contents);
                        
                       Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                   }
               }
           }
       }
       
       /**
        * 공동구매 진행 마감일 30일 후 안내 메시지 발송
        *
        * @Title       : jntpurchsEndAftrMsgSend 
        * @Description : 공동구매 진행 마감일 30일 후 안내 메시지 발송
        * @param triggerid
        * @return void 리턴값없음
        * @throws Exception 예외
        */
        @SchedulingHistory
        public void jntpurchsEndAftrMsgSend(@Triggerid int triggerid) throws Exception {
                
            //발송대상자 확인
            List<Map<String, Object>> sendUserList = batchJobDao.selectJntPurchsSendMsgUserList(2);
                
            if(sendUserList.size() > 0) {
                ObjectMapper mapper = new ObjectMapper();
                
                // 공동구매 진행 마감일 안내 메시지 발송
                for(Map<String, Object> sendUser : sendUserList) {
                    if(sendUser.get("MOBLPHON") != null && !"".equals(sendUser.get("MOBLPHON"))) {
                        List<Object> recipientList = new ArrayList<>(); 
                        
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("recipientNo", sendUser.get("MOBLPHON")); 
              
                        Map<String, Object> templateParameter = new HashMap<String, Object>();
                        templateParameter.put("nm", sendUser.get("NM"));
                        templateParameter.put("jntpurchsNm", sendUser.get("JNTPURCHS_NM"));
                        templateParameter.put("qnty", sendUser.get("QNTY"));
                        templateParameter.put("amt", sendUser.get("AMT"));
              
                        map.put("templateParameter", templateParameter);
                  
                        recipientList.add(map); 
                        
                        String recipientListStr = mapper.writeValueAsString(recipientList);
                        
                        alimtalkNhnService.sendAlimtalk("keep-021", (String) sendUser.get("SEND_DT"), recipientListStr);
                    }
                    if(sendUser.get("EML") != null && !"".equals(sendUser.get("EML"))) {
                        Context context = new Context();
                        context.setVariable("title", "[환경보전협회] " + sendUser.get("JNTPURCHS_NM") + " 공동구매 후기를 남겨주세요. ");
                        context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                        String content = "<tr>"
                            + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                            + "        " + sendUser.get("NM") + "님, 참여하신 교구 공동구매는 어떠셨나요?<br>"
                            + "        교구를 받아보셨다면 " + sendUser.get("JNTPURCHS_NM") +" 공동구매에 대한 후기 작성을 부탁드립니다.<br>"
                            + "        자세한 내용은 국가환경교육 통합플랫폼 로그인 후 마이페이지에서 확인해주세요."
                            + "    </td>"
                            + "</tr>"
                            + "<tr>"
                            + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                            + "</tr>";
                        context.setVariable("content", content);
                        String contents = templateEngine.process("mail/mail_basic_template", context);
                            
                        MailVo mailVo = new MailVo();
                        mailVo.setRcptnEmail((String) sendUser.get("EML"));
                        mailVo.setTitle("[환경보전협회] " + sendUser.get("JNTPURCHS_NM") + " 공동구매 후기를 남겨주세요. ");
                        mailVo.setCntnts(contents);
                         
                        Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                    }
                }
            }
        }
    
        /**
         * 교구 대여 반납후 이용후기 안내 메시지 발송
         *
         * @Title       : lendAplyDgstfnMsgSend 
         * @Description : 교구 대여 반납후 이용후기 안내 메시지 발송
         * @param triggerid
         * @return void 리턴값없음
         * @throws Exception 예외
         */
         @SchedulingHistory
         public void lendAplyDgstfnMsgSend(@Triggerid int triggerid) throws Exception {
                 
             //발송대상자 확인
             List<Map<String, Object>> sendUserList = batchJobDao.selectLendAplyDgstfnMsgSendUserList();
                 
             if(sendUserList.size() > 0) {
                 ObjectMapper mapper = new ObjectMapper();
                 
                 List<String> smsSendList =  new ArrayList<String>();
                 
                 // 공동구매 진행 마감일 안내 메시지 발송
                 for(Map<String, Object> sendUser : sendUserList) {
                     if(sendUser.get("TELNO") != null && !"".equals(sendUser.get("TELNO"))) {
                         
                         List<Object> recipientList = new ArrayList<>(); 
                         
                         Map<String, Object> map = new HashMap<String, Object>();
                         map.put("recipientNo", sendUser.get("TELNO")); 
                         
                         smsSendList.add((String) sendUser.get("TELNO"));
                         
                         Map<String, Object> templateParameter = new HashMap<String, Object>();
                         templateParameter.put("nm", sendUser.get("NM"));
                         templateParameter.put("tchaidNm", sendUser.get("PACKAGE_NM"));
               
                         map.put("templateParameter", templateParameter);
                   
                         recipientList.add(map); 
                         
                         String recipientListStr = mapper.writeValueAsString(recipientList);
                         
                         alimtalkNhnService.sendAlimtalk("keep-019", (String) sendUser.get("SEND_DT"), recipientListStr);
                         
                     }
                     
                     if(sendUser.get("EML") != null && !"".equals(sendUser.get("EML"))) {
                         Context context = new Context();
                         context.setVariable("title", "[환경보전협회] 대여하신 " + sendUser.get("PACKAGE_NM") + " 의 후기를 남겨주세요.");
                         context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
                         String content = "<tr>"
                             + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                             + "        " + sendUser.get("NM") + "님, 이용하신 교구 대여 서비스는 어떠셨나요?<br>"
                             + "        대여하신 " + sendUser.get("PACKAGE_NM") +"에 대한 후기를 남겨주세요.<br>"
                             + "        자세한 내용은 국가환경교육 통합플랫폼 로그인 후 마이페이지에서 확인해주세요."
                             + "    </td>"
                             + "</tr>"
                             + "<tr>"
                             + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                             + "</tr>";
                         context.setVariable("content", content);
                         String contents = templateEngine.process("mail/mail_basic_template", context);
                             
                         MailVo mailVo = new MailVo();
                         mailVo.setRcptnEmail((String) sendUser.get("EML"));
                         mailVo.setTitle("[환경보전협회] 대여하신 " + sendUser.get("PACKAGE_NM") + " 의 후기를 남겨주세요. ");
                         mailVo.setCntnts(contents);
                          
                         Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
                     }
                 }
                 
                 if(smsSendList.size() > 0) {
                     String smsSendDt = (String) sendUserList.get(0).get("SEND_DT");
                     String smsMsg = "[환경보전협회] 국가환경교육 통합플랫폼에서 교구 대여 만족도를 평가해주세요.";
                     String[] phoneList = smsSendList.toArray(new String[smsSendList.size()]);
                     
                     smsService.sendSms(smsMsg, phoneList, smsSendDt);
                 }
                 
                 
             }
         }
}