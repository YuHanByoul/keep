package com.kbrainc.plum.mng.pvtEnveduGrp.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;

/**
* 민간 환경교육단체 현황 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.model
* - PvtEnveduGrpDao.java
* </pre>
*
* @ClassName : PvtEnveduGrpDao
* @Description : 민간 환경교육단체 현황 Dao 클래스
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface PvtEnveduGrpDao {
    
    /**
    * 민간 환경교육단체 목록 조회
    *
    * @Title : selectPvtEnveduGrpList
    * @Description : 민간 환경교육단체 목록 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectAddrCtpvnList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    /**
    * 엑셀다운로드 목록 조회
    *
    * @Title : selectPvtEnveduGrpExcelDownload
    * @Description : 엑셀다운로드 목록 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpExcelDownload(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
}
