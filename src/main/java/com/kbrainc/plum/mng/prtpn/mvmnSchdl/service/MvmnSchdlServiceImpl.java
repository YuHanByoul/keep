package com.kbrainc.plum.mng.prtpn.mvmnSchdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.prtpn.mvmnSchdl.model.MvmnSchdlVo;
import com.kbrainc.plum.mng.prtpn.mvmnSchdl.model.MvmnSchdlDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 푸름이환경이동교실 -> 교육일정관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnSchdl.service
* - MvmnSchdlServiceImpl.java
* </pre>
**
@ClassName : MvmnSchdlServiceImpl
* @Description : 푸름이환경이동교실 -> 교육일정관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MvmnSchdlServiceImpl extends PlumAbstractServiceImpl implements MvmnSchdlService{
    
    @Autowired
    private MvmnSchdlDao mvmnSchdlDao;
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectMvmnSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return List<MvmnSchdlVo>
    */
    public List<MvmnSchdlVo> selectMvmnSchdlList(MvmnSchdlVo mvmnSchdlVo) throws Exception {
        return mvmnSchdlDao.selectMvmnSchdlList(mvmnSchdlVo);
    }
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertMvmnSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception{
        int retVal = 0;
        retVal += mvmnSchdlDao.insertMvmnSchdl(mvmnSchdlVo);
        
        mvmnSchdlDao.deleteDeSttId(mvmnSchdlVo);
        mvmnSchdlDao.deletePrgrmSttId(mvmnSchdlVo);
        
        if(mvmnSchdlVo.getDeSttIds()!=null & mvmnSchdlVo.getDeSttIds().length > 0) {
            retVal += mvmnSchdlDao.insertDeSttId(mvmnSchdlVo);
        }
        if(mvmnSchdlVo.getPrgrmSttIds()!=null & mvmnSchdlVo.getPrgrmSttIds().length > 0) {
            retVal += mvmnSchdlDao.insertPrgrmSttId(mvmnSchdlVo);
        }
       //푸름이환경이동교실_프로그램_일정_프로그램_회차 저장 
       retVal += mvmnSchdlDao.insertPrgrmTmeSchdl(mvmnSchdlVo);
        
        return retVal;
    }
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectMvmnSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return MvmnSchdlVo
    */
    public MvmnSchdlVo selectMvmnSchdlInfo(MvmnSchdlVo mvmnSchdlVo) throws Exception{
        return mvmnSchdlDao.selectMvmnSchdlInfo(mvmnSchdlVo);
    }
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateMvmnSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception{
        int retVal = 0;
        retVal += mvmnSchdlDao.updateMvmnSchdl(mvmnSchdlVo);
        
        mvmnSchdlDao.deleteDeSttId(mvmnSchdlVo);
        mvmnSchdlDao.deletePrgrmSttId(mvmnSchdlVo);
        
        if(mvmnSchdlVo.getDeSttIds()!=null & mvmnSchdlVo.getDeSttIds().length > 0) {
            retVal += mvmnSchdlDao.insertDeSttId(mvmnSchdlVo);
        }
        if(mvmnSchdlVo.getPrgrmSttIds()!=null & mvmnSchdlVo.getPrgrmSttIds().length > 0) {
            retVal += mvmnSchdlDao.insertPrgrmSttId(mvmnSchdlVo);
        }
        //푸름이환경이동교실_프로그램_일정_프로그램_회차 삭제후 저장 
        mvmnSchdlDao.deletePrgrmTmeSchdl(mvmnSchdlVo);
        retVal += mvmnSchdlDao.insertPrgrmTmeSchdl(mvmnSchdlVo);
        
        return retVal;        
    }

    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : updateMvmnSchdl
     * @Description : 교육일정관리 게시글 수정
     * @param mvmnSchdlVo 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception{
        int retVal = 0;

        retVal += mvmnSchdlDao.deleteDeSttId(mvmnSchdlVo);
        retVal += mvmnSchdlDao.deletePrgrmSttId(mvmnSchdlVo);

        retVal += mvmnSchdlDao.deleteMvmnSchdl(mvmnSchdlVo);
        
        return retVal;        
    }
 
    /**
     * 교육일정관리 교육일정 리스트 조회
     *
     * @Title : updateMvmnSchdl
     * @Description : 교육일정관리 교육일정 리스트 조회
     * @param sareaId
     * @throws Exception 예외
     * @return int
     */
    public List<MvmnSchdlVo> selectMvmnSchdlIdList(String sareaId) throws Exception{
        return mvmnSchdlDao.selectMvmnSchdlIdList(sareaId);   
    }
}
