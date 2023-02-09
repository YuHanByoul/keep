package com.kbrainc.plum.mng.prtpn.mvmnAply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyDao;
import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육신청관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnAply.service
* - MvmnAplyServiceImpl.java
* </pre>
**
@ClassName : MvmnAplyServiceImpl
* @Description : 유아환경교육 -> 교육신청관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MvmnAplyServiceImpl extends PlumAbstractServiceImpl implements MvmnAplyService{
    
    @Autowired
    private MvmnAplyDao mvmnAplyDao;
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyList(mvmnAplyVo);
    }

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectMvmnAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyDetailList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyDetailList(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 게시글 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyDao.insertMvmnAply(mvmnAplyVo);

        if(mvmnAplyVo.getTrgtCds()!=null & mvmnAplyVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyDao.insertTrgtCd(mvmnAplyVo);
        }
        
        //retVal += mvmnAplyDao.insertMvmnAplyTme(mvmnAplyVo);
        
        return retVal;
    }

    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectMvmnAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.selectMvmnAplyInfo(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 게시글 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyDao.updateMvmnAply(mvmnAplyVo);
        
        mvmnAplyDao.deleteTrgtCd(mvmnAplyVo);

        if(mvmnAplyVo.getTrgtCds()!=null & mvmnAplyVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyDao.insertTrgtCd(mvmnAplyVo);
        }

        return retVal;
    }
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 회차 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.insertMvmnAplyTme(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 회차 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.updateMvmnAplyTme(mvmnAplyVo);
    }    
    
    /**
    * 교육신청관리 회차 조회
    *
    * @Title : selectMvmnAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyTmeList(mvmnAplyVo);
    }    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectMvmnAplyTmeList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectTmeSchdlList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectTmeSchdlList(mvmnAplyVo);
    }
    
    /**
     * 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param MvmnAplyVo mvmnAply 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MvmnAplyVo> selectMemberList(MvmnAplyVo mvmnAply) throws Exception{
        return mvmnAplyDao.selectMemberList(mvmnAply);
    }
    
}
