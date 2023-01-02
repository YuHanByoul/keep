package com.kbrainc.plum.mng.cnsltng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.cnsltng.model.CnsltngDao;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 사이트관리를 위한 서비스 구현.
 *
 * <pre>
 * com.kbrainc.plum.mng.site.service
 * - SiteServiceImpl.java
 * </pre> 
 *
 * @ClassName : SiteServiceImpl
 * @Description : 사이트관리를
 * @author : KBRAINC
 * @date : 2021. 3. 16.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class CnsltngServiceImpl extends PlumAbstractServiceImpl implements CnsltngService {
    
    @Autowired
    private CnsltngDao cnsltngDao;

    /**
    * 컨설팅 리스트 호출 
    *
    * @Title       : selectConsultList 
    * @Description : 컨설팅 리스트 호출 
    * @param ConsultVo consultVo객체
    * @return List<ConsultVo>
    * @throws Exception 예외
    */
    public List<CnsltngVo> selectCnsltngList(CnsltngVo consultVo) throws Exception{
        return cnsltngDao.selectCnsltngList(consultVo);
    }
    
    /**
     * 컨설팅 상세정보 호출 
     *
     * @Title       : selectSiteApplyInfo 
     * @Description : 컨설팅 상세정보 호출
     * @param ConsultVo ConsultVo객체
     * @return ConsultVo
     * @throws Exception 예외
     */
    public CnsltngVo selectCnsltngtInfo(CnsltngVo consultVo) throws Exception{
        return cnsltngDao.selectCnsltngtInfo(consultVo);
    }
    /**
     * 컨설팅 신청 상태 수정 
     *
     * @Title       : updateConsultStatus 
     * @Description : 사이트 신청 상태 수정
     * @param ConsultVo ConsultVo객체
     * @return int
     * @throws Exception 예외
     */
    public int updateConsultStatus(CnsltngVo cnsltngVo) throws Exception{
        return cnsltngDao.updateConsultStatus(cnsltngVo);
    }


}
