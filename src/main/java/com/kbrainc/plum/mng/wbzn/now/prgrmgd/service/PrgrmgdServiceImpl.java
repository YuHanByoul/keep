package com.kbrainc.plum.mng.wbzn.now.prgrmgd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.wbzn.now.prgrmgd.model.PrgrmgdDao;
import com.kbrainc.plum.mng.wbzn.now.prgrmgd.model.PrgrmgdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육NOW -> 환경교육관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.service
* - EnveduServiceImpl.java
* </pre>
*
* @ClassName : EnveduServiceImpl
* @Description : 환경교육NOW -> 환경교육관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class PrgrmgdServiceImpl extends PlumAbstractServiceImpl implements PrgrmgdService{
    
    @Autowired
    private PrgrmgdDao prgrmgdDao;
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception {
        return prgrmgdDao.selectPrgrmgdList(prgrmgdVo);
    }
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 게시글 등록
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertPrgrmgd(PrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.insertPrgrmgd(prgrmgdVo);
    }
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EnveduVo
    */
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.selectPrgrmgdInfo(prgrmgdVo);
    }
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEnvedu
    * @Description : 환경교육관리 게시글 수정
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updatePrgrmgd(PrgrmgdVo prgrmgdVo) throws Exception{
        return prgrmgdDao.updatePrgrmgd(prgrmgdVo);
    }
}
