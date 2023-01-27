package com.kbrainc.plum.mng.prtpn.eduClssRm.service;

import java.util.List;

import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;



/**
* 유아환경교육 -> 교육관관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduClssRm.service
* - EduClssRmService.java
* </pre>
**
@ClassName : EduClssRmService
* @Description : 유아환경교육 -> 교육관관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EduClssRmService {
    
    /**
    * 교육관관리 게시글 목록 조회
    *
    * @Title : selectEduClssRmList
    * @Description : 교육관관리 게시글 목록 조회
    * @param eduClssRm 교육관관리 객체
    * @throws Exception 예외
    * @return List<EduClssRmVo>
    */
    public List<EduClssRmVo> selectEduClssRmList(EduClssRmVo eduClssRm) throws Exception;
    
    /**
    * 교육관관리 게시글 등록
    *
    * @Title : insertEduClssRm
    * @Description : 교육관관리 게시글 등록
    * @param eduClssRm 교육관관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEduClssRm(EduClssRmVo eduClssRm) throws Exception;
    
    /**
    * 교육관관리 게시글 상세조회
    *
    * @Title : selectEduClssRmInfo
    * @Description : 교육관관리 게시글 상세조회
    * @param eduClssRm 교육관관리 객체
    * @throws Exception 예외
    * @return EduClssRmVo
    */
    public EduClssRmVo selectEduClssRmInfo(EduClssRmVo eduClssRm) throws Exception;
    
    /**
    * 교육관관리 게시글 수정
    *
    * @Title : updateEduClssRm
    * @Description : 교육관관리 게시글 수정
    * @param eduClssRm 교육관관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEduClssRm(EduClssRmVo eduClssRm) throws Exception;
    
    /**
    * 교육관_교육유형 조회.
    **
    @Title : selectClssrmEduTypeCd
    * @Description : 교육관_교육유형 조회.
    * @param clssrmId
    * @return
    * @throws Exception
    * @return String
    */
    public EduClssRmVo selectClssrmEduTypeCd(String clssrmId) throws Exception;
}
