package com.kbrainc.plum.mng.pvtEnveduGrp.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.model
* - PvtEnveduGrpDao.java
* </pre>
*
* @ClassName : PvtEnveduGrpDao
* @Description : TODO
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface PvtEnveduGrpDao {
    
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    public List<PvtEnvEduGrpVo> selectAddrCtpvnList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpExcelDownload(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
}
