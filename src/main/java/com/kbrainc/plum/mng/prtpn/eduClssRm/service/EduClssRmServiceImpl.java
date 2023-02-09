package com.kbrainc.plum.mng.prtpn.eduClssRm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육관관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduClssRm.service
* - EduClssRmServiceImpl.java
* </pre>
**
@ClassName : EduClssRmServiceImpl
* @Description : 유아환경교육 -> 교육관관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class EduClssRmServiceImpl extends PlumAbstractServiceImpl implements EduClssRmService{
    
    @Autowired
    private EduClssRmDao eduClssRmDao;
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEduClssRmList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param eduClssRmVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EduClssRmVo>
    */
    public List<EduClssRmVo> selectEduClssRmList(EduClssRmVo eduClssRmVo) throws Exception {
        return eduClssRmDao.selectEduClssRmList(eduClssRmVo);
    }
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEduClssRm
    * @Description : 환경교육관리 게시글 등록
    * @param eduClssRmVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEduClssRm(EduClssRmVo eduClssRmVo) throws Exception{
        return eduClssRmDao.insertEduClssRm(eduClssRmVo);
    }
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEduClssRmInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param eduClssRmVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EduClssRmVo
    */
    public EduClssRmVo selectEduClssRmInfo(EduClssRmVo eduClssRmVo) throws Exception{
        return eduClssRmDao.selectEduClssRmInfo(eduClssRmVo);
    }
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEduClssRm
    * @Description : 환경교육관리 게시글 수정
    * @param eduClssRmVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEduClssRm(EduClssRmVo eduClssRmVo) throws Exception{
        return eduClssRmDao.updateEduClssRm(eduClssRmVo);
    }
    
    /**
    * 교육관_교육유형 조회.
    *
    * @Title : updateEduClssRm
    * @Description : 교육관_교육유형 조회.
    * @param eduClssRmVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public EduClssRmVo selectClssrmEduTypeCd(String clssrmId) throws Exception{
        return eduClssRmDao.selectClssrmEduTypeCd(clssrmId);        
    }
}
