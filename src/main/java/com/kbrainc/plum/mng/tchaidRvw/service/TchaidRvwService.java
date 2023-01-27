package com.kbrainc.plum.mng.tchaidRvw.service;

import java.util.List;

import com.kbrainc.plum.mng.tchaidRvw.model.TchaidRvwVo;

/**
* 교구후기관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.tchaidRvw.service
* - TchaidRvwService.java
* </pre>
*
* @ClassName : TchaidRvwService
* @Description : 교구후기관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface TchaidRvwService {
    
    /**
    * 교구후기관리 게시글 목록 조회
    *
    * @Title : selectTchaidRvwList
    * @Description : 교구후기관리 게시글 목록 조회
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return List<TchaidRvwVo>
    */
    public List<TchaidRvwVo> selectTchaidRvwList(TchaidRvwVo tchaidRvwVo) throws Exception;

    /**
    * 교구후기관리 게시글 상세정보
    *
    * @Title : selectTchaidRvwInfo
    * @Description : 교구후기관리 게시글 상세정보
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return TchaidRvwVo
    */
    public TchaidRvwVo selectTchaidRvwInfo(TchaidRvwVo tchaidRvwVo) throws Exception;

    /**
    * 교구후기관리 게시글 수정
    *
    * @Title : updateTchaidRvw
    * @Description : 교구후기관리 게시글 수정
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateTchaidRvw(TchaidRvwVo tchaidRvwVo) throws Exception;
    
    /**
    * 교구후기관리 게시글 삭제
    *
    * @Title : deleteTchaidRvw
    * @Description : 교구후기관리 게시글 삭제
    * @param nscvrgids 게시글 id값
    * @throws Exception 예외
    * @return int
    */
    public int deleteTchaidRvw(String[] nscvrgids) throws Exception;
}
