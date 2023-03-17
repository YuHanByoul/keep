package com.kbrainc.plum.cmm.scheduling.service;

/**
* 스케줄링 배치잡 인터페이스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.service
* - BatchJobService.java
* </pre>
*
* @ClassName   : BatchJobService 
* @Description : 스케줄링 배치잡 인터페이스. 
* @author      : KBRAINC
* @date        : 2021. 3. 8.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
public interface BatchJobService {

	/**
	* 휴면계정처리
	*
	* @Title       : userDrmncyProcess 
	* @Description : 휴면계정처리
	* @param triggerid
	* @return void 리턴값없음
	* @throws Exception 예외
	*/
    public void userDrmncyProcess(int triggerid) throws Exception;
	
	/**
    * 유아환경교육 안내 알림톡 발송
    *
    * @Title       : infntEnveduMsgNoticeMsgSend 
    * @Description : 유아환경교육 안내 알림톡 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
	public void infntEnveduMsgNoticeMsgSend(int triggerid) throws Exception;
	
	/**
    * 푸름이 이동 환경교육 안내 알림톡 발송
    *
    * @Title       : mvnEnveduMsgNoticeMsgSend 
    * @Description : 푸름이 이동 환경교육 안내 알림톡 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
	public void mvnEnveduMsgNoticeMsgSend(int triggerid) throws Exception;
	
	/**
    * 휴면계정 전환 안내 메일 발송
    *
    * @Title       : userDrmncyNtcMailSend 
    * @Description : 휴면계정 전환 안내 메일 발송
    * @param triggerid
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public void userDrmncyNtcMailSend(int triggerid) throws Exception;
    
    /**
     * 3개월 이상된 알림메시지 삭제
     *
     * @Title       : deleteOldNtcMsg 
     * @Description : 3개월 이상된 알림메시지 삭제
     * @param triggerid
     * @return void 리턴값없음
     * @throws Exception 예외
     */
     public void deleteOldNtcMsg(int triggerid) throws Exception;
     
     /**
      * 푸름이 이동환경교실 만족도 조사 안내 메시지 발송
      *
      * @Title       : mvnEnveduDgstfnMsgSend 
      * @Description : 푸름이 이동환경교실 만족도 조사 안내 메시지 발송
      * @param triggerid
      * @return void 리턴값없음
      * @throws Exception 예외
      */
      public void mvnEnveduDgstfnMsgSend(int triggerid) throws Exception;
      
      /**
       * 유아환경교육 만족도 조사 안내 메시지 발송
       *
       * @Title       : infntEnveduDgstfnMsgSend 
       * @Description : 유아환경교육 만족도 조사 안내 메시지 발송
       * @param triggerid
       * @return void 리턴값없음
       * @throws Exception 예외
       */
       public void infntEnveduDgstfnMsgSend(int triggerid) throws Exception;
       
       /**
        * 전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송
        *
        * @Title       : exprtEduBfrMsgSend 
        * @Description : 전문가 섭외자와 대상 전문가 대상 교육 알림 메시지 발송
        * @param triggerid
        * @return void 리턴값없음
        * @throws Exception 예외
        */
        public void exprtEduBfrMsgSend(int triggerid) throws Exception;
        
        /**
         * 전문가 섭외자 대상 만족도 평가 안내 메시지 발송
         *
         * @Title       : exprtEduAftrMsgSend 
         * @Description : 전문가 섭외자 대상 만족도 평가 안내 메시지 발송
         * @param triggerid
         * @return void 리턴값없음
         * @throws Exception 예외
         */
         public void exprtEduAftrMsgSend(int triggerid) throws Exception;
         
         /**
          * 미입금 시설예약 취소처리
          *
          * @Title       : flctRsvCancle 
          * @Description : 미입금 시설예약 취소처리
          * @param triggerid
          * @return void 리턴값없음
          * @throws Exception 예외
          */
          public void flctRsvCancle(int triggerid) throws Exception;
          
          /**
           * 시설 이용자 대상 만족도 평가 안내 메시지 발송
           *
           * @Title       : flctRsvDgstfnNtcMsgSend 
           * @Description : 시설 이용자 대상 만족도 평가 안내 메시지 발송
           * @param triggerid
           * @return void 리턴값없음
           * @throws Exception 예외
           */
           public void flctRsvDgstfnNtcMsgSend(int triggerid) throws Exception;
}