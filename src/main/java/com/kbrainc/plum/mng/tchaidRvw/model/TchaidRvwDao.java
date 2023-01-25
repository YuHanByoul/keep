package com.kbrainc.plum.mng.tchaidRvw.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 교구후기 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.model
* - TchaidRvwDao.java
* </pre>
*
* @ClassName : TchaidRvwDao
* @Description : 교구후기 관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface TchaidRvwDao {
    
    /**
    * 교구후기 관리 게시글 목록 조회
    *
    * @Title : selectTchaidRvwList
    * @Description : 교구후기 관리 게시글 목록 조회
    * @param tchaidRvwVo 교구후기 관리 객체
    * @throws Exception 예외
    * @return List<TchaidRvwVo>
    */
    public List<TchaidRvwVo> selectTchaidRvwList(TchaidRvwVo tchaidRvwVo) throws Exception;
    
    /**
    * 교구후기 관리 게시글 상세정보 조회
    *
    * @Title : selectTchaidRvwInfo
    * @Description : 교구후기 관리 게시글 상세정보
    * @param tchaidRvwVo 교구후기 관리 객체
    * @throws Exception 예외
    * @return TchaidRvwVo
    */
    public TchaidRvwVo selectTchaidRvwInfo(TchaidRvwVo tchaidRvwVo) throws Exception;
    
    /**
    * 교구후기 관리 게시글 수정
    *
    * @Title : updateTchaidRvw
    * @Description : 교구후기 관리 게시글 수정
    * @param tchaidRvwVo 교구후기 관리 객체
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
