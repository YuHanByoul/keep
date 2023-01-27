package com.kbrainc.plum.mng.prtpn.infntPrgrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmDao;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.service
* - InfntPrgrmServiceImpl.java
* </pre>
**
@ClassName : InfntPrgrmServiceImpl
* @Description : 유아환경교육 -> 교육프로그램관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class InfntPrgrmServiceImpl extends PlumAbstractServiceImpl implements InfntPrgrmService{
    
    @Autowired
    private InfntPrgrmDao infntPrgrmDao;
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        return infntPrgrmDao.selectInfntPrgrmList(infntPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += infntPrgrmDao.insertInfntPrgrm(infntPrgrmVo);
        
        //infntPrgrmDao.deleteTrgtCd(infntPrgrmVo);
        //infntPrgrmDao.deleteClsfCd(infntPrgrmVo);
        
        if(infntPrgrmVo.getTrgtCds()!=null & infntPrgrmVo.getTrgtCds().length > 0) {
            retVal += infntPrgrmDao.insertTrgtCd(infntPrgrmVo);
        }
        if(infntPrgrmVo.getClsfCds()!=null & infntPrgrmVo.getClsfCds().length > 0) {
            retVal += infntPrgrmDao.insertClsfCd(infntPrgrmVo);
        }
        //retVal += infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);
        
        return retVal;
    }

    @Override
    @Transactional
    public int insertInfntPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception {
        int retVal = 0;
        InfntPrgrmVo infntPrgrmVo = new InfntPrgrmVo();
//        infntPrgrmVo.setCopyPrgrmIds(copyPrgrmIds);
        infntPrgrmVo.setUser(userVo);
        for(String copyPrgrmId : copyPrgrmIds) {
          infntPrgrmVo.setCopyPrgrmId(copyPrgrmId);
          retVal += infntPrgrmDao.insertInfntPrgrmCopy(infntPrgrmVo);
          retVal += infntPrgrmDao.insertInfntPrgrmClsfMapngCopy(infntPrgrmVo);
          retVal += infntPrgrmDao.insertInfntPrgrmTrgtMapngCopy(infntPrgrmVo);
        }
        
        
        return retVal;
    }

    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectInfntPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return InfntPrgrmVo
    */
    public InfntPrgrmVo selectInfntPrgrmInfo(InfntPrgrmVo infntPrgrmVo) throws Exception{
        return infntPrgrmDao.selectInfntPrgrmInfo(infntPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception{
        int retVal = 0;
        retVal += infntPrgrmDao.updateInfntPrgrm(infntPrgrmVo);
        
        infntPrgrmDao.deleteTrgtCd(infntPrgrmVo);
        infntPrgrmDao.deleteClsfCd(infntPrgrmVo);
        
        if(infntPrgrmVo.getTrgtCds()!=null & infntPrgrmVo.getTrgtCds().length > 0) {
            retVal += infntPrgrmDao.insertTrgtCd(infntPrgrmVo);
        }
        if(infntPrgrmVo.getClsfCds()!=null & infntPrgrmVo.getClsfCds().length > 0) {
            retVal += infntPrgrmDao.insertClsfCd(infntPrgrmVo);
        }
        //retVal += infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);
        
        return retVal;        
    }
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception{
        return infntPrgrmDao.insertInfntPrgrmTme(infntPrgrmVo);
    }
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception{
        return infntPrgrmDao.updateInfntPrgrmTme(infntPrgrmVo);
    }    
    
    /**
    * 교육프로그램관리 회차 조회
    *
    * @Title : selectInfntPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrmVo) throws Exception {
        return infntPrgrmDao.selectInfntPrgrmTmeList(infntPrgrmVo);
    }    

    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    *
    * @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectPrgrmSettingList(String rcptMthdCd) throws Exception {
        return infntPrgrmDao.selectPrgrmSettingList(rcptMthdCd);
    }    
}
