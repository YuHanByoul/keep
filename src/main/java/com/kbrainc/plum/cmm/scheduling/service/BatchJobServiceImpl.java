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
                
                //발송 - 아직 탬플릿 없음
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
                 
                 //발송 - 아직 탬플릿 없음
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
                 
                 //발송 - 아직 탬플릿 없음
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
                //발송 - 아직 탬플릿 없음
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
                //발송 - 아직 탬플릿 없음
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
                 //발송 - 아직 탬플릿 없음
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
                  //발송 - 아직 탬플릿 없음
                  alimtalkNhnService.sendAlimtalk("fcltDgstfnMsg", (String) alimTalkSendUser.get("END_DT_STR"), recipientListStr);
              }

           }
    }
}