package com.kbrainc.plum.mng.envtcherTrnngInst.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* 환경교육사 양성기관 현황 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.model
* - EnvtcherTrnngInstDao.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstDao
* @Description : 환경교육사 양성기관 현황 Dao 클래스
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface EnvtcherTrnngInstDao {

    /**
    * 환경교육사 양성기관 등록 기능
    *
    * @Title : insertEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 등록 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 환경교육사 양성기관 수정 기능
    *
    * @Title : updateEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 수정 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 환경교육사 양성기관 목록 조회
    *
    * @Title : selectEnvtcherTrnngInstList
    * @Description : 환경교육사 양성기관 목록 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;
    
    /**
    * 환경교육사 양성기관 상세정보 조회
    *
    * @Title : selectEnvtcherTrnngInstInfo
    * @Description : 환경교육사 양성기관 상세정보 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return EnvtcherTrnngInstVo
    */
    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 엑셀다운로드 목록 조회
    *
    * @Title : selectEnvtcherTrnngInstExcelDownload
    * @Description : 엑셀다운로드 목록 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstExcelDownload(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;;
    
    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectAddrCtpvnList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;
}
