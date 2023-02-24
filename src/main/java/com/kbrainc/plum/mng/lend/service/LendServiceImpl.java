package com.kbrainc.plum.mng.lend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.lend.model.LendDao;
import com.kbrainc.plum.mng.lend.model.LendRndPackageindvdVo;
import com.kbrainc.plum.mng.lend.model.LendRndVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 교구관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.service
 * - TchaidServiceImpl.java
 * </pre> 
 *
 * @ClassName : LendServiceImpl
 * @Description : 교구 대여 관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 20
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class LendServiceImpl extends PlumAbstractServiceImpl implements LendService { 

    @Autowired
    private LendDao lendDao;
    
    /**
     * 교구 대여 모집 목록 호출 
     *
     * @Title       : selectLendList 
     * @Description : 교구 대여 모집 목록 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public List<LendVo> selectLendList(LendVo lendVo) throws Exception{
        return lendDao.selectLendList(lendVo);
    }
    
    /**
     * 교구 대여 모집 등록 
     *
     * @Title       : insertLend 
     * @Description : 교구 대여 모집 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int insertLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        resInt +=lendDao.insertLend(lendVo);
        
        List<LendRndVo> list = lendVo.getLendRndList();
        for(LendRndVo vo :list ) {
            vo.setUser(lendVo.getUser());
            vo.setRcritid(lendVo.getRcritid());
            resInt +=lendDao.insertLendRnd(vo);
            resInt +=lendDao.insertRndPackageindvd(vo); 
        }
        return resInt;
    }
    
    /**
     * 교구 대여 차시 등록 
     *
     * @Title       : insertLendRnd 
     * @Description : 교구 대여 차시 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertLendRnd(LendRndVo lendRndVo) throws Exception{
        return lendDao.insertLendRnd(lendRndVo);
    }
    /**
     * 교구 대여 차시 꾸러미 등록 
     *
     * @Title       : insertRndPackageindvd 
     * @Description : 교구 대여 차시 등록 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int insertRndPackageindvd(LendRndVo lendRndVo) throws Exception{
        return lendDao.insertRndPackageindvd(lendRndVo);
    }
    /**
     * 교구 대여 수정 
     *
     * @Title       : udateLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int udateLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        resInt +=lendDao.udateLend(lendVo);
        LendRndVo lendRndVo = new LendRndVo();
        
        List<Integer> delIds = new ArrayList();
        delIds.add(lendVo.getRcritid());
        lendRndVo.setDeleteIds(delIds);
        resInt +=lendDao.deleteLendRndPackageindvd(lendRndVo);
        resInt +=lendDao.deleteLendRnd(lendRndVo);
        
        List<LendRndVo> list = lendVo.getLendRndList();
        for(LendRndVo vo :list ) {
            vo.setUser(lendVo.getUser());
            vo.setRcritid(lendVo.getRcritid());
            resInt +=lendDao.insertLendRnd(vo);
            resInt +=lendDao.insertRndPackageindvd(vo); 
        }
        return resInt;
    }
    /**
     * 교구 대여 모집 삭제
     *
     * @Title       : deleteLend 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    @Transactional
    public int deleteLend(LendVo lendVo) throws Exception{
        
        int resInt = 0;
        
        LendRndVo lendRndVo = new LendRndVo();
        lendRndVo.setDeleteIds(lendVo.getDeleteIds());
        
        resInt +=lendDao.deleteLendRndPackageindvd(lendRndVo);
        resInt +=lendDao.deleteLendRnd(lendRndVo);
        resInt +=lendDao.deleteLend(lendVo);
        
        return resInt;
    }
    /**
     * 교구 대여 차시 삭제
     *
     * @Title       : deleteLendRnd 
     * @Description : 교구 대여 수정 
     * @param LendVo 객체
     * @return int
     * @throws Exception 예외
     */
    public int deleteLendRnd(LendRndVo lendRndVo) throws Exception{
        return lendDao.deleteLendRnd(lendRndVo);
    }
    /**
     * 교구 대여 모집 상세 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendVo>  목록
     * @throws Exception 예외
     */
    public LendVo selectLend(LendVo lendVo) throws Exception{
        return lendDao.selectLend(lendVo);
    }
    /**
     * 교구 대여 차시 목록 호출 
     *
     * @Title       : selectLend 
     * @Description : 교구 대여 모집 상세 호출 
     * @param LendVo 객체
     * @return List<LendRndVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndVo> selectLendRndList(LendVo lendVo) throws Exception{
        return lendDao.selectLendRndList(lendVo);
    }
    /**
     * 교구 대여 차시 꾸러마 목록 호출 
     *
     * @Title       : selectLendRndPackageindvdList 
     * @Description : 교구 대여 차시 꾸러마 목록 호출 
     * @param LendVo 객체
     * @return List<LendRndPackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<LendRndPackageindvdVo> selectLendRndPackageindvdList(LendRndVo lendRndVo) throws Exception{
        return lendDao.selectLendRndPackageindvdList(lendRndVo);
    }
    /**
     * 검색용 꾸러미 개체 호출 
     *
     * @Title       : selectPackageindvdList 
     * @Description : 검색용 꾸러미 개체 호출 
     * @param LendVo 객체
     * @return List<PackageindvdVo>  목록
     * @throws Exception 예외
     */
    public List<PackageindvdVo> selectPackageindvdList(PackageindvdVo packageindvdVo) throws Exception{
        return lendDao.selectPackageindvdList(packageindvdVo);
    }
    /**
     * 꾸러미 개체 대여 신청여부 확인  
     *
     * @Title       : selectLendApplyYn 
     * @Description : 꾸러미 개체 대여 신청여부 확인  
     * @param LendVo 객체
     * @return String [Y/N]
     * @throws Exception 예외
     */
    public String selectLendApplyYn(LendVo lendVo) throws Exception{
        return lendDao.selectLendApplyYn(lendVo);
    }
    
}
