package com.kbrainc.plum.front.mmnws.service;

import java.util.List;

import com.kbrainc.plum.front.mmnws.model.MmnwsVo;

/**
* 언론보도관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.front.mmnws.service
* - MmnwsService.java
* </pre>
*
* @ClassName : MmnwsService
* @Description : 언론보도관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MmnwsService {
    
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
    * 언론보도관리 게시글 상세정보
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
