package com.kbrainc.plum.cmm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.model.CommonDao;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하는 서비스 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - CommonServiceImpl.java
 * </pre> 
 *
 * @ClassName : CommonServiceImpl
 * @Description : 어플리케이션 전체의 공통 요청을 처리하는 서비스 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class CommonServiceImpl extends PlumAbstractServiceImpl implements CommonService {

    @Autowired
    private CommonDao commonDao;

    /**
    * 사이트 리스트를 반환한다.
    *
    * @Title       : selectSiteList 
    * @Description : 사이트 리스트를 반환한다.
    * @param site SiteVo객체
    * @return List<SiteVo> 사이트정보 목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectSiteList(SiteVo site) throws Exception {
        return commonDao.selectSiteList(site);
    }
}
