package com.kbrainc.plum.mng.refndMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.model
* - RefndMngDao.java
* </pre>
*
* @ClassName : RefndMngDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface RefndMngDao {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectRefndMngList
    * @Description : 시설 목록 조회
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    public List<RefndMngVo> selectRefndMngList(RefndMngVo refndMngVo) throws Exception;
    
    /**
     * 마지막 시설번호 조회.
     *
     * @Title : selectFcltNo
     * @Description : 마지막 시설번호 조회.
     * @param refndMngVo
     * @return RefndMngVo
     * @throws Exception 예외
     */
     public RefndMngVo selectFcltNo(RefndMngVo refndMngVo) throws Exception;
     
     /**
      * 시설명 중복 조회.
      *
      * @Title : checkDuplicateFcltNm
      * @Description : 시설명 중복 조회.
      * @param refndMngVo
      * @return RefndMngVo
      * @throws Exception 예외
      */
     public int checkDuplicateFcltNm(RefndMngVo refndMngVo) throws Exception;
     
     /**
      * 회원 기관데이터 조회.
      *
      * @Title : selectUserInst
      * @Description : 회원 기관데이터 조회.
      * @param instid
      * @return RefndMngVo
      * @throws Exception 예외
      */
     public RefndMngVo selectUserInst(Integer instid) throws Exception;
     
    /**
    * 시설 등록
    *
    * @Title : insertRefndMng
    * @Description : 시설 등록
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertRefndMng(RefndMngVo refndMngVo) throws Exception;
    
    /**
    * 시설 상세정보 조회
    *
    * @Title : selectRefndMngInfo
    * @Description : 시설 상세정보
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return RefndMngVo
    */
    public RefndMngVo selectRefndMngInfo(RefndMngVo refndMngVo) throws Exception;
    
    /**
    * 시설 수정
    *
    * @Title : updateRefndMng
    * @Description : 시설 게시글 수정
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateRefndMng(RefndMngVo refndMngVo) throws Exception;
    
    /**
    * 시설 삭제
    *
    * @Title : deleteRefndMng
    * @Description : 시설 삭제
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteRefndMng(RefndMngVo refndMngVo) throws Exception;
    
    /**
     * 공간 목록 조회
     *
     * @Title : selectRefndMngSpceList
     * @Description : 공간 목록 조회
     * @param refndMngVo 시설 객체
     * @throws Exception 예외
     * @return List<RefndMngVo>
     */
     public List<RefndMngVo> selectRefndMngSpceList(RefndMngVo refndMngVo) throws Exception;
}
