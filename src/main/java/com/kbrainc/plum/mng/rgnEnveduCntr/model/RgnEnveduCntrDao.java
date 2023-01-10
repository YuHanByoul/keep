package com.kbrainc.plum.mng.rgnEnveduCntr.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
* 지역환경교육센터 Dao
*
* <pre>
* com.kbrainc.plum.mng.rgnEnveduCntr.model
* - RgnEnveduCntrDao.java
* </pre>
*
* @ClassName : RgnEnveduCntrDao
* @Description : 지역환경교육센터 Dao
* @author : JD
* @date : 2022. 12. 30.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface RgnEnveduCntrDao {
    
    /**
    * 지역환경교육센터 목록 조회
    *
    * @Title : selectRgnEnveduCntrList
    * @Description : 지역환경교육센터 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    public List<RgnEnveduCntrVo> selectRgnEnveduCntrList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
    /**
    * 구분코드(공통코드) 목록 조회
    *
    * @Title : selectCmmCdList
    * @Description : 구분 코드(공통코드) 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    public List<RgnEnveduCntrVo> selectCmmCdList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
    /**
    * 지역코드 목록 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 지역코드 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    public List<RgnEnveduCntrVo> selectAddrCtpvnList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
    /**
    * 지역환경교육센터 엑셀다운로드 대상 목록 조회
    *
    * @Title : selectRgnEnveduCntrExcelDownload
    * @Description : 지역환경교육센터 엑셀다운로드 대상 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    public List<RgnEnveduCntrVo> selectRgnEnveduCntrExcelDownload(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
    /**
    * 지역환경교육센터 등록
    *
    * @Title : insertRgnEnveduCntr
    * @Description : 지역환경교육센터 등록
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 에외
    * @return int
    */
    public int insertRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;

    /**
    * 지역환경교육센터 상세조회
    *
    * @Title : selectRgnEnveduCntrInfo
    * @Description : 지역환경교육센터 상세조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return EnveduCntrVo
    */
    public RgnEnveduCntrVo selectRgnEnveduCntrInfo(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
    /**
    * 지역환경교육센터 삭제
    *
    * @Title : deleteRgnEnveduCntr
    * @Description : 지역환경교육센터 삭제
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;

    /**
    * 지역환경교육센터 수정
    *
    * @Title : updateRgnEnveduCntr
    * @Description : 지역환경교육센터 수정
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception;
    
}
