package com.kbrainc.plum.mng.mmnws.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 언론보도관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.model
* - MmnwsDao.java
* </pre>
*
* @ClassName : MmnwsDao
* @Description : 언론보도관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MmnwsDao {
    
    /**
    * 언론보도관리 게시글 목록 조회
    *
    * @Title : selectMmnwsList
    * @Description : 언론보도관리 게시글 목록 조회
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return List<MmnwsVo>
    */
    public List<MmnwsVo> selectMmnwsList(MmnwsVo mmnwsVo) throws Exception;
    
    /**
    * 언론보도관리 게시글 등록
    *
    * @Title : insertMmnws
    * @Description : 언론보도관리 게시글 등록
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertMmnws(MmnwsVo mmnwsVo) throws Exception;
    
    /**
    * 언론보도관리 게시글 상세정보 조회
    *
    * @Title : selectMmnwsInfo
    * @Description : 언론보도관리 게시글 상세정보
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return MmnwsVo
    */
    public MmnwsVo selectMmnwsInfo(MmnwsVo mmnwsVo) throws Exception;
    
    /**
    * 언론보도관리 게시글 수정
    *
    * @Title : updateMmnws
    * @Description : 언론보도관리 게시글 수정
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMmnws(MmnwsVo mmnwsVo) throws Exception;
    
    /**
    * 언론보도관리 게시글 삭제
    *
    * @Title : deleteMmnws
    * @Description : 언론보도관리 게시글 삭제
    * @param nscvrgids 게시글 id값
    * @throws Exception 예외
    * @return int
    */
    public int deleteMmnws(String[] nscvrgids) throws Exception;
}
