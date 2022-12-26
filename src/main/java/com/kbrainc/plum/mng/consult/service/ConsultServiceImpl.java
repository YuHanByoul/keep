package com.kbrainc.plum.mng.consult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.consult.model.ConsultDao;
import com.kbrainc.plum.mng.consult.model.ConsultVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyMenuVo;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyVo;
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
public class ConsultServiceImpl extends PlumAbstractServiceImpl implements ConsultService {
    
    @Autowired
    private ConsultDao consultDao;

    /**
    * 컨설팅 리스트 호출 
    *
    * @Title       : selectConsultList 
    * @Description : 컨설팅 리스트 호출 
    * @param ConsultVo consultVo객체
    * @return List<ConsultVo>
    * @throws Exception 예외
    */
    public List<ConsultVo> selectConsultList(ConsultVo consultVo) throws Exception{
        return consultDao.selectConsultList(consultVo);
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
    public ConsultVo selectConsultInfo(ConsultVo consultVo) throws Exception{
        return consultDao.selectConsultInfo(consultVo);
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
    public int updateConsultStatus(ConsultVo consultVo) throws Exception{
        return consultDao.updateConsultStatus(consultVo);
    }


}
