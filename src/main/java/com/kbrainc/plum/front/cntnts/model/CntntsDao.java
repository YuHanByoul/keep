package com.kbrainc.plum.front.cntnts.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 콘텐츠 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.model
* - CntntsDao.java
* </pre>
*
* @ClassName : CntntsDao
* @Description : 콘텐츠 Dao 클래스
* @author : JD
* @date : 2023. 2. 2.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.cntntsDao")
public interface CntntsDao {
    
    /**
    * 컨텐츠 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 컨텐츠 게시글 상세조회
    *
    * @Title : selectCntntsInfo
    * @Description : 컨텐츠 관리 게시글 상세조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return CntntsVo
    */
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception;

    /**
    * 조회수 증가
    *
    * @Title : updateCntntsHits
    * @Description : 조회수 증가
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateCntntsHits(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 콘텐츠 썸네일 파일리스트
    *
    * @Title : selectCntntsFileList
    * @Description : 콘텐츠 썸네일 파일리스트
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    public List<CntntsVo> selectCntntsFileList(CntntsVo cntntsVo) throws Exception;
}
