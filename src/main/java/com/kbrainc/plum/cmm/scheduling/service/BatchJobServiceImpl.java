package com.kbrainc.plum.cmm.scheduling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.scheduling.model.BatchJobDao;
import com.kbrainc.plum.rte.scheduling.annotation.SchedulingHistory;
import com.kbrainc.plum.rte.scheduling.annotation.Triggerid;

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
	
	/**
	* 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	*
	* @Title       : callSpUserDrmncy 
	* @Description : 휴면계정처리를 위해 sp_userDrmncy프로시져를 호출한다.
	* @param triggerid
	* @return void 리턴값없음
	* @throws Exception 예외
	*/
	@SchedulingHistory
	public void callSpUserDrmncy(@Triggerid int triggerid) throws Exception {
		batchJobDao.callSpUserDrmncy();
	}
}