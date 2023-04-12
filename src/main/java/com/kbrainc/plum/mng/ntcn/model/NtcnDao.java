package com.kbrainc.plum.mng.ntcn.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;

/**
 * 
 * 알림  DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.model
 * - NtcnDao.java
 * </pre>        
 *
 * @ClassName : NtcnDao
 * @Description : 알림 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2023. 04. 12
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface NtcnDao {
    
    /**
    * 알림 등록
    *
    * @Title       : insertNtcn 
    * @Description : 단일 푸쉬 발송(비동기).
    * @param  Map
    * @return void 리턴값없음
    * @throws Exception 예외
    */
    public int insertNtcn (NtcnVo ntcnVo) throws Exception;
}