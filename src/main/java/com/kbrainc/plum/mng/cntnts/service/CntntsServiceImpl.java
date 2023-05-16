package com.kbrainc.plum.mng.cntnts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import com.kbrainc.plum.mng.cntnts.model.CntntsDao;
import com.kbrainc.plum.mng.cntnts.model.CntntsEduSbjctVo;
import com.kbrainc.plum.mng.cntnts.model.CntntsEduTrgtVo;
import com.kbrainc.plum.mng.cntnts.model.CntntsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 컨텐츠 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.service
* - CntntsServiceImpl.java
* </pre>
*
* @ClassName : CntntsServiceImpl
* @Description : 컨텐츠 관리 서비스 구현 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CntntsServiceImpl extends PlumAbstractServiceImpl implements CntntsService{

    @Autowired
    private CntntsDao cntntsDao;
    
    @Autowired
    private CmmCntntsService cmmCntntsService;
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회 
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    @Override
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsList(cntntsVo);
    };
    
    /**
    * 컨텐츠 관리 게시글 상세조회
    *
    * @Title : selectCntntsInfo
    * @Description : 컨텐츠 관리 게시글 상세조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return CntntsVo
    */
    @Override
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsInfo(cntntsVo);
    }
    
    /**
    * 컨텐츠 관리 게시글 등록
    *
    * @Title : insertCntnts
    * @Description : 컨텐츠 관리 게시글 등록
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int insertCntnts(CntntsVo cntntsVo, String[] eduSbjctCds, String[] eduTrgt, String ceckid) throws Exception {
        int retVal = 0;
        
        retVal += cntntsDao.insertCntnts(cntntsVo);
        retVal += cntntsDao.insertEduSbjct(cntntsVo.getCntntsid(), eduSbjctCds, cntntsVo.getUser());
        retVal += cntntsDao.insertEduTrgt(cntntsVo.getCntntsid(), eduTrgt, cntntsVo.getUser());
        
        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(cntntsVo.getUser());
        cmmCntntsVo.setEvntCd(cntntsVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(cntntsVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(cntntsVo.getCntntsid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);
        
        return retVal;
    }
    
    /**
    * 컨텐츠 관리 게시글 수정
    *
    * @Title : updateCntnts
    * @Description : 컨텐츠 관리 게시글 수정
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int updateCntnts(CntntsVo cntntsVo, String[] eduSbjctCds, String[] eduTrgt) throws Exception {
        int retVal = 0;
        retVal += cntntsDao.deleteEduSbjct(cntntsVo);
        retVal += cntntsDao.deleteEduTrgt(cntntsVo);
        retVal += cntntsDao.updateCntnts(cntntsVo);
        retVal += cntntsDao.insertEduSbjct(cntntsVo.getCntntsid(), eduSbjctCds, cntntsVo.getUser());
        retVal += cntntsDao.insertEduTrgt(cntntsVo.getCntntsid(), eduTrgt, cntntsVo.getUser());
        
        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(cntntsVo.getUser());
        cmmCntntsVo.setEvntCd(cntntsVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(cntntsVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(cntntsVo.getCntntsid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);
        
        return retVal;
    }
    
    /**
    * 컨텐츠 괸리 게시글 삭제
    *
    * @Title : deleteCntnts
    * @Description : 컨텐츠 괸리 게시글 삭제
    * @param cntntsids 컨텐츠아이디
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int deleteCntnts(CntntsVo cntntsVo, String[] cntntsids) throws Exception {
        return cntntsDao.deleteCntnts(cntntsVo.getUser(), cntntsids);
    }
    
    /**
    * 교육주제 코드 리스트 조회
    *
    * @Title : selectCntntsCdList
    * @Description : 교육주제 코드 리스트 조회
    * @param map
    * @return
    * @throws Exception
    * @return List<Map<String,String>>
    */
    @Override
    public List<Map<String,String>> selectCntntsCdList(Map<String,String> map) throws Exception {
        return cntntsDao.selectCntntsCdList(map);
    }

    /**
    * 콘텐츠 교육 주제 코드 맵핑 리스트 호출 
    *
    * @Title : selectCntntsEduSbjctList
    * @Description : selectCntntsCdList
    * @param cntntsEduSbjctVo
    * @return
    * @throws Exception
    * @return List<CntntsEduSbjctVo>
    */
    @Override
    public List<CntntsEduSbjctVo> selectCntntsEduSbjctList(CntntsEduSbjctVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsEduSbjctList(cntntsVo);
    }

    /**
    * 콘텐츠 교육 대상 코드 리스트 호출
    *
    * @Title : selectCntntsEduTrgtList
    * @Description : 콘텐츠 교육 대상 코드 리스트 호출
    * @param cntntsVo
    * @return
    * @throws Exception
    * @return List<CntntsEduTrgtVo>
    */
    @Override
    public List<CntntsEduTrgtVo> selectCntntsEduTrgtList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsEduTrgtList(cntntsVo);
    }
}
