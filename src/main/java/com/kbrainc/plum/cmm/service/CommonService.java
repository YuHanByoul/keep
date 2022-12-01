package com.kbrainc.plum.cmm.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.site.model.SiteVo;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하는 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.cmm.service
 * - CommonService.java
 * </pre> 
 *
 * @ClassName : CommonService
 * @Description : 어플리케이션 전체의 공통 요청을 처리하는 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface CommonService {

    /**
    * 사이트 리스트를 반환한다.
    *
    * @Title       : selectSiteList 
    * @Description : 사이트 리스트를 반환한다.
    * @param site SiteVo객체
    * @return List<SiteVo> 사이트정보 목록
    * @throws Exception 예외
    */
    public List<SiteVo> selectSiteList(SiteVo site) throws Exception;
    
    /**
    * 현재 사용자의 접근가능한 기관목록을 반환한다.
    *
    * @Title : selectAlowedInstList
    * @Description : 현재 사용자의 접근가능한 기관목록을 반환한다.
    * @return List<Map<String,Object>> 기관목록
    * @throws Exception 예외
    */
    public List<Map<String, Object>> selectAlowedInstList() throws Exception;
}
