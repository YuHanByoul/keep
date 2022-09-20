package com.kbrainc.plum.rte.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.model.ResSiteDao;
import com.kbrainc.plum.rte.model.SiteInfoVo;

/**
 * 
 * 사이트정보를 조회하는 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.service
 * - ResSiteServiceImpl.java
 * </pre> 
 *
 * @ClassName : ResSiteServiceImpl
 * @Description : 사이트정보를 조회하는 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Service("cmm.resSiteService")
public class ResSiteServiceImpl extends PlumAbstractServiceImpl implements ResSiteService {

    protected Logger logger = LoggerFactory.getLogger(ResSiteServiceImpl.class);

    @Autowired
    private ResSiteDao resSiteDao;

    /**
    * @Title : getSiteInfo
    * @Description : url로 현재 사이트정보를 돌려줌
    * @param dmn 도메인
    * @return SiteInfo 사이트정보
    * @throws Exception 예외
    */
    public SiteInfoVo getSiteInfo(String dmn) throws Exception {
        Map map = new HashMap();
        map.put("p_dmn", dmn);
        return resSiteDao.selectSiteInfo(map);
    }
    
    /**
    * 사용중인 사이트아이디 목록을 가져온다.
    *
    * @Title       : getSiteidList 
    * @Description : 사용중인 사이트아이디 목록을 가져온다.
    * @return List<SiteInfoVo> SiteInfoVo 목록
    * @throws Exception 예외
    */
    public List<SiteInfoVo> getSiteidList() throws Exception {
    	return resSiteDao.selectSiteidList();
    }
}