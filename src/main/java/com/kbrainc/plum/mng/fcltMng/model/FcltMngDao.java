package com.kbrainc.plum.mng.fcltMng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.fcltMng.model
* - FcltMngDao.java
* </pre>
*
* @ClassName : FcltMngDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface FcltMngDao {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectFcltMngList
    * @Description : 시설 목록 조회
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return List<FcltMngVo>
    */
    public List<FcltMngVo> selectFcltMngList(FcltMngVo fcltMngVo) throws Exception;
    
    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 조회.
     * @param fcltMngVo
     * @return FcltMngVo
     * @throws Exception 예외
     */
     public FcltMngVo selectFcltNo(FcltMngVo fcltMngVo) throws Exception;
     
     /**
      * 시설명 중복 조회.
      *
      * @Title : checkDuplicateFcltNm
      * @Description : 시설명 중복 조회.
      * @param fcltMngVo
      * @return FcltMngVo
      * @throws Exception 예외
      */
     public int checkDuplicateFcltNm(FcltMngVo fcltMngVo) throws Exception;
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return FcltMngVo
      * @throws Exception 예외
      */
     public FcltMngVo selectUserInst(Integer instid) throws Exception;
     
    /**
    * 시설 등록
    *
    * @Title : insertFcltMng
    * @Description : 시설 등록
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertFcltMng(FcltMngVo fcltMngVo) throws Exception;
    
    /**
    * 시설 상세정보 조회
    *
    * @Title : selectFcltMngInfo
    * @Description : 시설 상세정보
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return FcltMngVo
    */
    public FcltMngVo selectFcltMngInfo(FcltMngVo fcltMngVo) throws Exception;
    
    /**
    * 시설 수정
    *
    * @Title : updateFcltMng
    * @Description : 시설 게시글 수정
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateFcltMng(FcltMngVo fcltMngVo) throws Exception;
    
    /**
    * 시설 삭제
    *
    * @Title : deleteFcltMng
    * @Description : 시설 삭제
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteFcltMng(FcltMngVo fcltMngVo) throws Exception;
    
    /**
     * 공간 목록 조회
     *
     * @Title : selectFcltMngSpceList
     * @Description : 공간 목록 조회
     * @param fcltMngVo 시설 객체
     * @throws Exception 예외
     * @return List<FcltMngVo>
     */
     public List<FcltMngVo> selectFcltMngSpceList(FcltMngVo fcltMngVo) throws Exception;
}
