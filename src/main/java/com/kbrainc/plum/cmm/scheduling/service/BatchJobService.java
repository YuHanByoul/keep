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
	* 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	*
	* @Title       : callSpUserDrmncy 
	* @Description : 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	* @param triggerid
	* @return void 리턴값없음
	* @throws Exception 예외
	*/
	public void callSpUserDrmncy(int triggerid) throws Exception;
}