package com.kbrainc.plum.mng.delvry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.delvry.model.DelvryAplyComputVo;
import com.kbrainc.plum.mng.delvry.model.DelvryAplySplmntVo;
import com.kbrainc.plum.mng.delvry.model.DelvryAplyVo;
import com.kbrainc.plum.mng.delvry.model.DelvryDao;
import com.kbrainc.plum.mng.delvry.model.PcntstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교부관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.service
 * - DelvryServiceImpl.java
 * </pre> 
 *
 * @ClassName : DelvryServiceImpl
 * @Description : 교부관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class DelvryServiceImpl extends PlumAbstractServiceImpl implements DelvryService {
    
    @Autowired
    private DelvryDao delvryDao;
    
    /**
    * 공모 목록 조회
    *
    * @Title : selectPcntstList
    * @Description : 공모 목록 조회
    * @param pcntstVo PcntstVo 객체
    * @return List<PcntstVo> 공모 목록
    * @throws Exception 예외
    */
    @Override
    public List<PcntstVo> selectPcntstList(PcntstVo pcntstVo) throws Exception {
        return delvryDao.selectPcntstList(pcntstVo);
    }
    
    /**
    * 공모 정보 조회
    *
    * @Title : selectPcntstInfo
    * @Description : 공모 정보 조회
    * @param pcntstVo PcntstVo 객체
    * @return PcntstVo 공모 정보
    * @throws Exception 예외
    */
    @Override
    public PcntstVo selectPcntstInfo(PcntstVo pcntstVo) throws Exception {
        return delvryDao.selectPcntstInfo(pcntstVo);
    }
    
    /**
    * 교부 신청 목록 조회
    *
    * @Title : selectDelvryAplyList
    * @Description : 교부 신청 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyVo> 교부 신청 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplyVo> selectDelvryAplyList(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyList(delvryAplyVo);
    }
    
    /**
    * 교부 상태 업데이트
    *
    * @Title : updateDelvryStts
    * @Description : 교부 상태 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int updateDelvryStts(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.updateDelvryStts(delvryAplyVo);
    }
    
    /**
    * 교부 신청 정보 조회
    *
    * @Title : selectDelvryAplyInfo
    * @Description : 교부 신청 정보 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return DelvryAplyVo 교부 신청 정보
    * @throws Exception 예외
    */
    @Override
    public DelvryAplyVo selectDelvryAplyInfo(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyInfo(delvryAplyVo);
    }
    
    /**
    * 교부 신청 산출내역 목록 조회
    *
    * @Title : selectDelvryAplyComputList
    * @Description : 교부 신청 산출내역 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return List<DelvryAplyComputVo> 교부 신청 산출내역 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplyComputVo> selectDelvryAplyComputList(DelvryAplyVo delvryAplyVo) throws Exception {
        return delvryDao.selectDelvryAplyComputList(delvryAplyVo);
    }
    
    /**
    * 교부 신청 업데이트
    *
    * @Title : updateDelvryAply
    * @Description : 교부 신청 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return int insert 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int updateDelvryAply(DelvryAplyVo delvryAplyVo) throws Exception {
        int retVal = 0;
        // 1. 신청정보 업데이트
        retVal += delvryDao.updateDelvryAply(delvryAplyVo);
        
        // 2. 산출내역 등록/업데이트
        List<DelvryAplyComputVo> computList = delvryAplyVo.getComputList();
        int delvryAplyid = delvryAplyVo.getDelvryAplyid();
        if(computList != null && computList.size() > 0) {
            for(int i = 0; i < computList.size(); i++) {
                DelvryAplyComputVo computVo = computList.get(i);
                computVo.setUser(delvryAplyVo.getUser());
                if(computVo.getComputid() == 0) { // 산출내역 등록
                    retVal += delvryDao.insertDelvryAplyComput(computVo);
                } else { // 업데이트
                    retVal += delvryDao.updateDelvryAplyComput(computVo);
                }
            }
        }
        
        return retVal;
    }
    
    /**
    * 교부 신청 보완요청 목록 조회
    *
    * @Title : selectDelvryAplySplmntList
    * @Description : 교부 신청 보완요청 목록 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return List<DelvryAplySplmntVo> 교부 신청 보완요청 목록
    * @throws Exception 예외
    */
    @Override
    public List<DelvryAplySplmntVo> selectDelvryAplySplmntList(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        return delvryDao.selectDelvryAplySplmntList(delvryAplySplmntVo);
    }
    
    /**
    * 교부 신청 보완요청 정보 조회
    *
    * @Title : selectDelvryAplySplmntInfo
    * @Description : 교부 신청 보완요청 정보 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return DelvryAplySplmntVo 교부 신청 보완요청 정보
    * @throws Exception 예외
    */
    @Override
    public DelvryAplySplmntVo selectDelvryAplySplmntInfo(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        return delvryDao.selectDelvryAplySplmntInfo(delvryAplySplmntVo);
    }
    
    /**
    * 교부 신청 보완요청 등록
    *
    * @Title : insertDelvrySplmntAply
    * @Description : 교부 신청 보완요청 등록
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int insertDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        int retVal = 0;
        retVal = delvryDao.insertDelvryAplySplmnt(delvryAplySplmntVo);
        
        return retVal;
    }
    
    /**
    * 교부 신청 보완요청 업데이트
    *
    * @Title : updateDelvrySplmntAply
    * @Description : 교부 신청 보완요청 업데이트
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return int update 로우수
    * @throws Exception 예외
    */
    @Override
    public int updateDelvryAplySplmnt(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        int retVal = 0;
        retVal = delvryDao.updateDelvryAplySplmnt(delvryAplySplmntVo);
        
        return retVal;
    }
    
}