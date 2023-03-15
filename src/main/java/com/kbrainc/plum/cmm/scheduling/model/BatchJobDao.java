package com.kbrainc.plum.cmm.scheduling.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 스케줄링 배치잡dao 맵퍼 인터페이스.
*
* <pre>
* com.kbrainc.plum.cmm.scheduling.model
* - BatchJobDao.java
* </pre>
*
* @ClassName   : BatchJobDao 
* @Description : 스케줄링잡dao 맵퍼 인터페이스. 
* @author      : KBRAINC
* @date        : 2021. 3. 8.
* @Version     : 1.0 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Mapper
public interface BatchJobDao {
    
	/**
	* 휴면계정처리 대상자 리스트
	*
	* @Title       : selectDrmncyMailSendUserList 
	* @Description : 휴면계정처리 대상자 리스트
	* @return List<Map<String, Object>>
	* @throws Exception 예외
	*/
	public List<Map<String, Object>> selectDrmncyUserList() throws Exception;
	
	/**
    * 유아환경교육 안내 알림톡 발송 대상자 리스트
    *
    * @Title       : selectInfntEnveduSmsSendList 
    * @Description : 유아환경교육 안내 알림톡 발송 대상자 리스트
    * @return List<Map<String, Object>>
    * @throws Exception 예외
    */
	public List<Map<String, Object>> selectInfntEnveduSmsSendList() throws Exception;
	
	/**
    * 유아환경교육 안내 알림톡 발송 대상자 리스트
    *
    * @Title       : selectInfntEnveduSmsSendList 
    * @Description : 유아환경교육 안내 알림톡 발송 대상자 리스트
    * @return List<Map<String, Object>>
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectMvnEnveduSmsSendList() throws Exception;
    
    /**
     * 휴면계정 전환 안내 메일 발송 대상자 리스트
     *
     * @Title       : selectDrmncyNtcMailSendUserList 
     * @Description : 휴면계정 전환 안내 메일 발송 대상자 리스트
     * @return List<Map<String, Object>>
     * @throws Exception 예외
     */
     public List<Map<String, Object>> selectDrmncyNtcMailSendUserList() throws Exception;
     
     /**
      * 휴면계정 테이블로 이관
      *
      * @Title       : insertUserdrmncyInfo 
      * @Description : 휴면계정 테이블로 이관
      * @return void
      * @throws Exception 예외
      */
      public void insertUserdrmncyInfo(int userid) throws Exception;
      
      /**
       * 회원정보 테이블에서 개인정보 삭제 및 휴면계정 전환
       *
       * @Title       : deleteUserInfo 
       * @Description : 회원정보 테이블에서 개인정보 삭제 및 휴면계정 전환
       * @return void
       * @throws Exception 예외
       */
       public void updateUserInfo(int userid) throws Exception;
       
       /**
        * 3개월 이상된 알림메시지 삭제
        *
        * @Title       : deleteUserInfo 
        * @Description : 3개월 이상된 알림메시지 삭제
        * @return void
        * @throws Exception 예외
        */
       public void deleteOldNtcMsg() throws Exception;
}