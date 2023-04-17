package com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model.CarbonPrgrmgdDao;
import com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model.CarbonPrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 탄소중립환경교육 -> 프로그램안내관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.service
* - PrgrmgdServiceImpl.java
* </pre>
*
* @ClassName : PrgrmgdServiceImpl
* @Description : 환경교육NOW -> 프로그램안내관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CarbonPrgrmgdServiceImpl extends PlumAbstractServiceImpl implements CarbonPrgrmgdService{
    
    @Autowired
    private CarbonPrgrmgdDao prgrmgdDao;
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    @Override
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo prgrmgdVo) throws Exception {
        return prgrmgdDao.selectPrgrmgdList(prgrmgdVo);
    }
    
    /**
    * 프로그램안내관리 게시글 등록
    *
    * @Title : insertPrgrmgd
    * @Description : 프로그램안내관리 게시글 등록
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional
    public int insertPrgrmgd(CarbonPrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.insertPrgrmgd(prgrmgdVo);
    }
    
    /**
    * 프로그램안내관리 게시글 상세조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 프로그램안내관리 게시글 상세조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return PrgrmgdVo
    */
    @Override
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.selectPrgrmgdInfo(prgrmgdVo);
    }
    
    /**
    * 프로그램안내관리 게시글 수정
    *
    * @Title : updatePrgrmgd
    * @Description : 프로그램안내관리 게시글 수정
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int updatePrgrmgd(CarbonPrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.updatePrgrmgd(prgrmgdVo);
    }
}
