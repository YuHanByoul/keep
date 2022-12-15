package com.kbrainc.plum.mng.cmnty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.cmnty.model.CmntyDao;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 커뮤니티 관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.service
 * - QestnrServiceImpl.java
 * </pre> 
 *
 * @ClassName : CmntyServiceImpl
 * @Description : 커뮤니티 관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class CmntyServiceImpl extends PlumAbstractServiceImpl implements CmntyService {
    
    @Autowired
    private CmntyDao cmntyDao;
    
    /**
     * 커뮤니티 목록 조회
     *
     * @Title : selectCmntyrList
     * @Description : 커뮤니티 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return List<CmntyVo> 커뮤니티 목록
     * @throws Exception 예외
     */
    @Override
    public List<CmntyVo> selectCmntyList(CmntyVo cmntyVo) throws Exception {
        return cmntyDao.selectCmntyList(cmntyVo);
    }
    
    /**
     * 커뮤니티 정보 조회
     *
     * @Title : selectCmntyInfo
     * @Description : 커뮤니티 정보 조회
     * @param cmntyVo CmntyVo 객체
     * @return CmntyVo CmntyVo 객체
     * @throws Exception 예외
     */
    @Override
    public CmntyVo selectCmntyInfo(CmntyVo cmntyVo) throws Exception {
        return cmntyDao.selectCmntyInfo(cmntyVo);
    }
     
}