package com.kbrainc.plum.mng.prtpn.infntAply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.infntAply.model.InfntAplyDao;
import com.kbrainc.plum.mng.prtpn.infntAply.model.InfntAplyVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육신청관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.service
* - InfntAplyServiceImpl.java
* </pre>
**
@ClassName : InfntAplyServiceImpl
* @Description : 유아환경교육 -> 교육신청관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class InfntAplyServiceImpl extends PlumAbstractServiceImpl implements InfntAplyService{
    
    @Autowired
    private InfntAplyDao infntAplyDao;
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyList(infntAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 게시글 등록
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAply(InfntAplyVo infntAplyVo) throws Exception{
        int retVal = 0;
        retVal += infntAplyDao.insertInfntAply(infntAplyVo);
        
        infntAplyDao.deleteTrgtCd(infntAplyVo);
        infntAplyDao.deleteClsfCd(infntAplyVo);
        
        if(infntAplyVo.getTrgtCds()!=null & infntAplyVo.getTrgtCds().length > 0) {
            retVal += infntAplyDao.insertTrgtCd(infntAplyVo);
        }
        if(infntAplyVo.getClsfCds()!=null & infntAplyVo.getClsfCds().length > 0) {
            retVal += infntAplyDao.insertClsfCd(infntAplyVo);
        }
        //retVal += infntAplyDao.insertInfntAplyTme(infntAplyVo);
        
        return retVal;
    }

    @Override
    @Transactional
    public int insertInfntAplyCopy(String[] copyAplyIds, UserVo userVo) throws Exception {
        int retVal = 0;
        InfntAplyVo infntAplyVo = new InfntAplyVo();
//        infntAplyVo.setCopyAplyIds(copyAplyIds);
        infntAplyVo.setUser(userVo);
        for(String copyAplyId : copyAplyIds) {
          infntAplyVo.setCopyAplyId(copyAplyId);
          retVal += infntAplyDao.insertInfntAplyCopy(infntAplyVo);
          retVal += infntAplyDao.insertInfntAplyClsfMapngCopy(infntAplyVo);
          retVal += infntAplyDao.insertInfntAplyTrgtMapngCopy(infntAplyVo);
        }
        
        
        return retVal;
    }

    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectInfntAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return InfntAplyVo
    */
    public InfntAplyVo selectInfntAplyInfo(InfntAplyVo infntAplyVo) throws Exception{
        return infntAplyDao.selectInfntAplyInfo(infntAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 게시글 수정
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAply(InfntAplyVo infntAplyVo) throws Exception{
        return infntAplyDao.updateInfntAply(infntAplyVo);
    }
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 회차 등록
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAplyTme(InfntAplyVo infntAplyVo) throws Exception{
        return infntAplyDao.insertInfntAplyTme(infntAplyVo);
    }
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 회차 수정
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAplyTme(InfntAplyVo infntAplyVo) throws Exception{
        return infntAplyDao.updateInfntAplyTme(infntAplyVo);
    }    
    
    /**
    * 교육신청관리 회차 조회
    *
    * @Title : selectInfntAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyTmeList(InfntAplyVo infntAplyVo) throws Exception {
        return infntAplyDao.selectInfntAplyTmeList(infntAplyVo);
    }    
}
