package com.kbrainc.plum.mng.prtpn.eduClssRm.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;

/**
* 유아환경교육 -> 교육관관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduClssRm.model
* - EduClssRmDao.java
* </pre>
**
@ClassName : EduClssRmDao
* @Description : 유아환경교육 -> 교육관관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface EduClssRmDao {
    
    /**
    * 교육관관리 게시글 목록 조회
    *
    * @Title : selectEduClssRmList
    * @Description : 교육관관리 게시글 목록 조회
    * @param eduClssRmVo 교육관관리 객체
    * @throws Exception 예외
    * @return List<EduClssRmVo>
    */
    public List<EduClssRmVo> selectEduClssRmList(EduClssRmVo eduClssRmVo) throws Exception;
    
    /**
    * 교육관관리 게시글 등록
    *
    * @Title : insertEduClssRm
    * @Description : 교육관관리 게시글 등록
    * @param eduClssRmVo 교육관관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEduClssRm(EduClssRmVo eduClssRmVo) throws Exception;
    
    /**
    * 교육관관리 게시글 상세조회
    *
    * @Title : selectEduClssRmInfo
    * @Description : 교육관관리 게시글 상세조회
    * @param eduClssRmVo 교육관관리 객체
    * @throws Exception 예외
    * @return EduClssRmVo
    */
    public EduClssRmVo selectEduClssRmInfo(EduClssRmVo eduClssRmVo) throws Exception;
    
    /**
    * 교육관관리 게시글 수정
    *
    * @Title : updateEduClssRm
    * @Description : 교육관관리 게시글 수정
    * @param eduClssRmVo 교육관관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEduClssRm(EduClssRmVo eduClssRmVo) throws Exception;
}
