package com.kbrainc.plum.cmm.scheduling.model;

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
	* 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	*
	* @Title       : callSpUserDrmncy 
	* @Description : 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	* @return void 리턴값없음
	* @throws Exception 예외
	*/
	public void callSpUserDrmncy() throws Exception;
}