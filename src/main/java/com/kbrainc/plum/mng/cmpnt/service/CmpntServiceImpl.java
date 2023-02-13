package com.kbrainc.plum.mng.cmpnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.cmpnt.model.CmpntDao;
import com.kbrainc.plum.mng.cmpnt.model.CmpntVo;
import com.kbrainc.plum.mng.cmpnt.model.CmpntWrhousngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 구성품 관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.tchaid.service
 * - TchaidServiceImpl.java
 * </pre> 
 *
 * @ClassName : TchaidServiceImpl
 * @Description : 구성품 관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 02
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class CmpntServiceImpl extends PlumAbstractServiceImpl implements CmpntService { 

    @Autowired
    private CmpntDao cmpntDao;
    
    /**
     * 구성품 목록 호출 
     *
     * @Title       : selectCmpntList 
     * @Description : 구성품 목록 호출
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public List<CmpntVo> selectCmpntList(CmpntVo cmpntVo) throws Exception{
        return cmpntDao.selectCmpntList(cmpntVo);
    }
    /**
     * 구성품 입고 내역 호출 
     *
     * @Title       : selectCmpntWrhousngList 
     * @Description : 구성품 목록 호출
     * @param CmpntWrhousngVo 객체
     * @return List<CmpntWrhousngVo> 목록
     * @throws Exception 예외
     */
    public List<CmpntWrhousngVo> selectCmpntWrhousngList(CmpntWrhousngVo cmpntWrhousngVo) throws Exception{
        return cmpntDao.selectCmpntWrhousngList(cmpntWrhousngVo);
    }
    /**
     * 구성품 등록 
     *
     * @Title       : insertCmpnt 
     * @Description : 구성품 등록 
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public int insertCmpnt(CmpntVo cmpntVo) throws Exception{
        return cmpntDao.insertCmpnt(cmpntVo);
    }
    /**
     * 구성품 입고 처리 등록 
     *
     * @Title       : insertCmpntWrhousng 
     * @Description : 구성품 입고 처리 등록 
     * @param CmpntWrhousngVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    @Transactional
    public int insertCmpntWrhousng(CmpntWrhousngVo cmpntWrhousngVo) throws Exception{
        
        int resInt = 0; 
        resInt = cmpntDao.insertCmpntWrhousng(cmpntWrhousngVo);
        
        CmpntVo cmpntVo = new CmpntVo();
        cmpntVo.setUser(cmpntWrhousngVo.getUser());
        cmpntVo.setPlusQnty(cmpntWrhousngVo.getQnty());
        cmpntVo.setCmpntid(cmpntWrhousngVo.getCmpntid());
        resInt += cmpntDao.updateCmpnt(cmpntVo);
        
        return resInt;
    }
    /**
     * 구성품 수정 
     *
     * @Title       : updateCmpnt 
     * @Description : 구성품 수정
     * @param CmpntVo 객체
     * @return List<CmpntVo>  목록
     * @throws Exception 예외
     */
    public int updateCmpnt(CmpntVo cmpntVo) throws Exception{
        return cmpntDao.updateCmpnt(cmpntVo);
    }
    /**
     * 구성품 상세 호출 
     *
     * @Title       : selectCmpnt 
     * @Description : 구성품 상세 호출
     * @param CmpntVo 객체
     * @return CmpntVo 목록
     * @throws Exception 예외
     */
    public CmpntVo selectCmpnt(CmpntVo cmpntVo) throws Exception{
        return cmpntDao.selectCmpnt(cmpntVo);
    }
    
}