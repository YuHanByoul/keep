package com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmDao;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service
* - MvmnPrgrmServiceImpl.java
* </pre>
**
@ClassName : MvmnPrgrmServiceImpl
* @Description : 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MvmnPrgrmServiceImpl extends PlumAbstractServiceImpl implements MvmnPrgrmService{
    
    @Autowired
    private MvmnPrgrmDao mvmnPrgrmDao;
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        return mvmnPrgrmDao.selectMvmnPrgrmList(mvmnPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += mvmnPrgrmDao.insertMvmnPrgrm(mvmnPrgrmVo);
        
        //mvmnPrgrmDao.deleteTrgtCd(mvmnPrgrmVo);
        //mvmnPrgrmDao.deleteClsfCd(mvmnPrgrmVo);
        
        if(mvmnPrgrmVo.getTrgtCds()!=null & mvmnPrgrmVo.getTrgtCds().length > 0) {
            retVal += mvmnPrgrmDao.insertTrgtCd(mvmnPrgrmVo);
        }
        if(mvmnPrgrmVo.getClsfCds()!=null & mvmnPrgrmVo.getClsfCds().length > 0) {
            retVal += mvmnPrgrmDao.insertClsfCd(mvmnPrgrmVo);
        }
        //retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo);
        
        return retVal;
    }

    @Override
    @Transactional
    public int insertMvmnPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception {
        int retVal = 0;
        MvmnPrgrmVo mvmnPrgrmVo = new MvmnPrgrmVo();
//        mvmnPrgrmVo.setCopyPrgrmIds(copyPrgrmIds);
        mvmnPrgrmVo.setUser(userVo);
        for(String copyPrgrmId : copyPrgrmIds) {
          mvmnPrgrmVo.setCopyPrgrmId(copyPrgrmId);
          retVal += mvmnPrgrmDao.insertMvmnPrgrmCopy(mvmnPrgrmVo);
          retVal += mvmnPrgrmDao.insertMvmnPrgrmClsfMapngCopy(mvmnPrgrmVo);
          retVal += mvmnPrgrmDao.insertMvmnPrgrmTrgtMapngCopy(mvmnPrgrmVo);
        }
        
        
        return retVal;
    }

    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectMvmnPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return MvmnPrgrmVo
    */
    public MvmnPrgrmVo selectMvmnPrgrmInfo(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        return mvmnPrgrmDao.selectMvmnPrgrmInfo(mvmnPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += mvmnPrgrmDao.updateMvmnPrgrm(mvmnPrgrmVo);
        
        mvmnPrgrmDao.deleteTrgtCd(mvmnPrgrmVo);
        mvmnPrgrmDao.deleteClsfCd(mvmnPrgrmVo);
        
        if(mvmnPrgrmVo.getTrgtCds()!=null & mvmnPrgrmVo.getTrgtCds().length > 0) {
            retVal += mvmnPrgrmDao.insertTrgtCd(mvmnPrgrmVo);
        }
        if(mvmnPrgrmVo.getClsfCds()!=null & mvmnPrgrmVo.getClsfCds().length > 0) {
            retVal += mvmnPrgrmDao.insertClsfCd(mvmnPrgrmVo);
        }
        //retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo);
        
        return retVal;        
    }
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        int retVal = 0;
        MvmnPrgrmVo delMvmnPrgrmVo =  new MvmnPrgrmVo();
        
        if(mvmnPrgrmVo.getMvmnPrgrmVoList() != null) {
            retVal += mvmnPrgrmDao.insertMvmnPrgrmTme(mvmnPrgrmVo);            
            retVal += mvmnPrgrmDao.updateMvmnPrgrmTme(mvmnPrgrmVo);
            for (int i=0; i<mvmnPrgrmVo.getMvmnPrgrmVoList().size();i++){
                if("Y".equals(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getDelFlag())) {
                    delMvmnPrgrmVo.setTmeId(mvmnPrgrmVo.getMvmnPrgrmVoList().get(i).getTmeId());
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAplyEduTrgt(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmAply(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTmeSchdl(delMvmnPrgrmVo);
                    retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(delMvmnPrgrmVo);
                }
            }
            
            //retVal += mvmnPrgrmDao.deleteMvmnPrgrmTme(mvmnPrgrmVo);
        }
        return retVal;
    }
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception{
        return mvmnPrgrmDao.updateMvmnPrgrmTme(mvmnPrgrmVo);
    }    
    
    /**
    * 교육프로그램관리 회차 조회
    *
    * @Title : selectMvmnPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmTmeList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception {
        return mvmnPrgrmDao.selectMvmnPrgrmTmeList(mvmnPrgrmVo);
    }    

    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectPrgrmSettingList(String operFomCd) throws Exception {
        return mvmnPrgrmDao.selectPrgrmSettingList(operFomCd);
    }    
}
