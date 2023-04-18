package com.kbrainc.plum.mng.prtpn.infntSchdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.prtpn.infntSchdl.model.InfntSchdlDao;
import com.kbrainc.plum.mng.prtpn.infntSchdl.model.InfntSchdlVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 유아환경교육 -> 교육일정관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntSchdl.service
* - InfntSchdlServiceImpl.java
* </pre>
**
@ClassName : InfntSchdlServiceImpl
* @Description : 유아환경교육 -> 교육일정관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class InfntSchdlServiceImpl extends PlumAbstractServiceImpl implements InfntSchdlService{
    
    @Autowired
    private InfntSchdlDao infntSchdlDao;
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectInfntSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return List<InfntSchdlVo>
    */
    @Override
    public List<InfntSchdlVo> selectInfntSchdlList(InfntSchdlVo infntSchdlVo) throws Exception {
        return infntSchdlDao.selectInfntSchdlList(infntSchdlVo);
    }
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertInfntSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception{
        int retVal = 0;
        retVal += infntSchdlDao.insertInfntSchdl(infntSchdlVo);
        
        infntSchdlDao.deleteDeSttId(infntSchdlVo);
        infntSchdlDao.deletePrgrmSttId(infntSchdlVo);
        
        if(infntSchdlVo.getDeSttIds()!=null & infntSchdlVo.getDeSttIds().length > 0) {
            retVal += infntSchdlDao.insertDeSttId(infntSchdlVo);
        }
        if(infntSchdlVo.getPrgrmSttIds()!=null & infntSchdlVo.getPrgrmSttIds().length > 0) {
            retVal += infntSchdlDao.insertPrgrmSttId(infntSchdlVo);
        }
       //유아_프로그램_일정_프로그램_회차 저장 
       retVal += infntSchdlDao.insertPrgrmTmeSchdl(infntSchdlVo);
        
        return retVal;
    }
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectInfntSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return InfntSchdlVo
    */
    @Override
    public InfntSchdlVo selectInfntSchdlInfo(InfntSchdlVo infntSchdlVo) throws Exception{
        return infntSchdlDao.selectInfntSchdlInfo(infntSchdlVo);
    }
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateInfntSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception{
        int retVal = 0;
        retVal += infntSchdlDao.updateInfntSchdl(infntSchdlVo);
        
        infntSchdlDao.deleteDeSttId(infntSchdlVo);
        infntSchdlDao.deletePrgrmSttId(infntSchdlVo);
        
        if(infntSchdlVo.getDeSttIds()!=null & infntSchdlVo.getDeSttIds().length > 0) {
            retVal += infntSchdlDao.insertDeSttId(infntSchdlVo);
        }
        if(infntSchdlVo.getPrgrmSttIds()!=null & infntSchdlVo.getPrgrmSttIds().length > 0) {
            retVal += infntSchdlDao.insertPrgrmSttId(infntSchdlVo);
        }
        //유아_프로그램_일정_프로그램_회차 삭제후 저장        
        infntSchdlDao.deletePrgrmTmeSchdl(infntSchdlVo);
        retVal += infntSchdlDao.insertPrgrmTmeSchdl(infntSchdlVo);
        
        return retVal;        
    }

    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : updateInfntSchdl
     * @Description : 교육일정관리 게시글 수정
     * @param infntSchdlVo 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional    
    public int deleteInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception{
        int retVal = 0;

        retVal += infntSchdlDao.deleteDeSttId(infntSchdlVo);
        retVal += infntSchdlDao.deletePrgrmSttId(infntSchdlVo);

        retVal += infntSchdlDao.deleteInfntSchdl(infntSchdlVo);
        
        return retVal;        
    }
 
    /**
     * 교육일정관리 교육일정 리스트 조회
     *
     * @Title : selectInfntSchdlIdList
     * @Description : 교육일정관리 교육일정 리스트 조회
     * @param clssrmId
     * @throws Exception 예외
     * @return List<InfntSchdlVo>
     */
    @Override
    public List<InfntSchdlVo> selectInfntSchdlIdList(String clssrmId) throws Exception{
        return infntSchdlDao.selectInfntSchdlIdList(clssrmId);   
    }
    
    /**
     * 교육일정관리 신청 카운트 조회
     * @Title : selectInfntAplyCnt
     * @Description : 교육일정관리 신청 카운트 조회
     * @param infntSchdlVo
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int selectInfntAplyCnt(InfntSchdlVo infntSchdlVo) throws Exception{
        return infntSchdlDao.selectInfntAplyCnt(infntSchdlVo);        
    }        
}
