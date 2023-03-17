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
	                      + "        휴면계정 전환 안내 문구 전달받지 못횄습니다.<br>"
	                      + "        ID : " + emailSendUser.get("ACNT") + "<br>"
	                      + "        이름 : " + emailSendUser.get("NM")
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
	    List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectInfntEnveduSmsSendList();
	    
	    if(alimTalkSendUserList.size() > 0) {
	        ObjectMapper mapper = new ObjectMapper();
	        
	        List<Object> recipientList = new ArrayList<>(); 
	        for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
	            map.put("recipientTime", alimTalkSendUser.get("SENDRSVTIME"));
	  
	            Map<String, Object> templateParameter = new HashMap<String, Object>();
	            templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
	            templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
	            templateParameter.put("tcherNope", alimTalkSendUser.get("TCHER_NOPE"));
	            templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
	            templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
	  
	            map.put("templateParameter", templateParameter);
	  
	            recipientList.add(map); 
	        }
	          
	        String recipientListStr = mapper.writeValueAsString(recipientList);
	        
	        //발송 - 아직 탬플릿 없음
	        //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr);
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
        List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectMvnEnveduSmsSendList();
        
        if(alimTalkSendUserList.size() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            
            List<Object> recipientList = new ArrayList<>(); 
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                map.put("recipientTime", alimTalkSendUser.get("SENDRSVTIME"));
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
      
                map.put("templateParameter", templateParameter);
      
                recipientList.add(map); 
            }
              
            String recipientListStr = mapper.writeValueAsString(recipientList);
            
            //발송 - 아직 탬플릿 없음
            //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr);
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
                 MailRcptnVo mailRcptnVo = new MailRcptnVo();
                 mailRcptnVo.setRcptnEmail((String) emailSendUser.get("EML"));
                 mailRcptnVo.setRcptnUserid((Integer) emailSendUser.get("USERID"));
                 
                 mailToArray.add(mailRcptnVo);
             }
             
             Context context = new Context();
             context.setVariable("title", "휴면계정 전환 사전 안내입니다.");
             context.setVariable("portalUrl", CommonUtil.portalUrl); // 필수값
             String content = "<tr>"
                     + "    <td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">"
                     + "        휴면계정 전환 사전 안내 문구 전달받지 못횄습니다."
                     + "    </td>"
                     + "</tr>"
                     + "<tr>"
                     + "    <td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td>"
                     + "</tr>";
             context.setVariable("content", content);
             String contents = templateEngine.process("mail/mail_basic_template", context);
             MailVo mailVo = new MailVo();
             mailVo.setTitle("휴면계정 전환 사전 안내입니다.");
             mailVo.setCntnts(contents);
             mailVo.setSndngUserid(0);
             mailVo.setSndngSeCd("J");
             mailVo.setRcptnUserid(0);
             
             mailService.sendMultiMail(mailToArray, mailVo);
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
         List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectMvnEnveduSmsSendList();
       
         if(alimTalkSendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
           
             List<Object> recipientList = new ArrayList<>(); 
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                 map.put("recipientTime", alimTalkSendUser.get("SENDRSVTIME"));
     
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                 templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
                 templateParameter.put("dgstfnUrl", "만족도 조사 URL");
     
                 map.put("templateParameter", templateParameter);
     
                 recipientList.add(map); 
             }
             
             String recipientListStr = mapper.writeValueAsString(recipientList);
           
             //발송 - 아직 탬플릿 없음
             //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr);
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
         List<Map<String, Object>> alimTalkSendUserList = batchJobDao.selectInfntEnveduSmsSendList();
           
         if(alimTalkSendUserList.size() > 0) {
             ObjectMapper mapper = new ObjectMapper();
             List<Object> recipientList = new ArrayList<>(); 
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                 map.put("recipientTime", alimTalkSendUser.get("SENDRSVTIME"));
         
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("USER_NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("tcherNope", alimTalkSendUser.get("TCHER_NOPE"));
                 templateParameter.put("prgrmNm", alimTalkSendUser.get("PRGRM_NM"));
                 templateParameter.put("startTime", alimTalkSendUser.get("START_TIME"));
                 templateParameter.put("dgstfnUrl", "만족도 조사 URL");
         
                 map.put("templateParameter", templateParameter);
         
                 recipientList.add(map); 
            }
                 
            String recipientListStr = mapper.writeValueAsString(recipientList);
               
            //발송 - 아직 탬플릿 없음
            //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr);
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
            List<Object> recipientList1 = new ArrayList<>(); 
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("ASS_MOBLPHON")); 
                map.put("recipientTime", alimTalkSendUser.get("BFR_SEND_DT"));
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("ASS_NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("startTime", alimTalkSendUser.get("LCTR_BGNG_DT"));
      
                map.put("templateParameter", templateParameter);
          
                recipientList1.add(map); 
            }
                  
            String recipientListStr1 = mapper.writeValueAsString(recipientList1);
            //발송 - 아직 탬플릿 없음
            //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr1);
                
            // 전문가 섭외자 대상 메세지 발송
            List<Object> recipientList2 = new ArrayList<>(); 
            for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                map.put("recipientTime", alimTalkSendUser.get("BFR_SEND_DT"));
      
                Map<String, Object> templateParameter = new HashMap<String, Object>();
                templateParameter.put("nm", alimTalkSendUser.get("NM"));
                templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                templateParameter.put("startTime", alimTalkSendUser.get("LCTR_BGNG_DT"));
          
                map.put("templateParameter", templateParameter);
          
                recipientList2.add(map); 
            }
                  
            String recipientListStr2 = mapper.writeValueAsString(recipientList2);
            //발송 - 아직 탬플릿 없음
            //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr2);
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
             List<Object> recipientList = new ArrayList<>(); 
             for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                 Map<String, Object> map = new HashMap<String, Object>();
                 map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                 map.put("recipientTime", alimTalkSendUser.get("AFTR_SEND_DT"));
       
                 Map<String, Object> templateParameter = new HashMap<String, Object>();
                 templateParameter.put("nm", alimTalkSendUser.get("NM"));
                 templateParameter.put("eduNope", alimTalkSendUser.get("EDU_NOPE"));
                 templateParameter.put("startTime", alimTalkSendUser.get("LCTR_END_DT"));
       
                 map.put("templateParameter", templateParameter);
           
                 recipientList.add(map); 
             }
                   
             String recipientListStr = mapper.writeValueAsString(recipientList);
             //발송 - 아직 탬플릿 없음
             //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr);
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
                           + "        시설예약 취소 안내 문구 전달받지 못횄습니다.<br>"
                           + "        예약자 : " + flctRsvCancleUser.get("APLCNT_NM") + "<br>"
                           + "        예약장소 : " + flctRsvCancleUser.get("FCLT_NM") + " " + flctRsvCancleUser.get("SPCE_NM") + "<br>"
                           + "        예약일시 : " + flctRsvCancleUser.get("BGNG_DT_STR") + "<br>"
                           + "        신청일시 : " + flctRsvCancleUser.get("APLY_DT_STR")
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
               
               // 전문가 섭외자 대상 메세지 발송
              List<Object> recipientList = new ArrayList<>(); 
              for(Map<String, Object> alimTalkSendUser : alimTalkSendUserList) {
                  Map<String, Object> map = new HashMap<String, Object>();
                  map.put("recipientNo", alimTalkSendUser.get("MOBLPHON")); 
                  map.put("recipientTime", alimTalkSendUser.get("END_DT_STR"));
        
                  Map<String, Object> templateParameter = new HashMap<String, Object>();
                  templateParameter.put("nm", alimTalkSendUser.get("NM"));
                  templateParameter.put("fcltNm", alimTalkSendUser.get("FCLT_NM") + " " + alimTalkSendUser.get("SPCE_NM"));
        
                  map.put("templateParameter", templateParameter);
            
                  recipientList.add(map); 
              }
                     
              String recipientListStr = mapper.writeValueAsString(recipientList);
              //발송 - 아직 탬플릿 없음
              //alimtalkNhnService.sendAlimtalk("TEST002", recipientListStr2);
       }
    }
}