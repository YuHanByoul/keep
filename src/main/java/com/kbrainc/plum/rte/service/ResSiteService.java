package com.kbrainc.plum.rte.service;

import java.util.List;

import com.kbrainc.plum.rte.model.SiteInfoVo;

/**
* 
* 사이트정보를 조회하는 서비스 인터페이스.
*
* <pre>
* com.kbrainc.plum.rte.service
* - ResSiteService.java
* </pre> 
*
* @ClassName : ResSiteService
* @Description : 사이트정보를 조회하는 서비스 인터페이스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved
*/
public interface ResSiteService {

    /**
    * @Title : getSiteInfo
    * @Description : url로 현재 사이트정보를 돌려줌
    * @param dmn 도메인
    * @return SiteInfo 사이트정보
    * @throws Exception 예외
    */
    public SiteInfoVo getSiteInfo(String dmn) throws Exception;
    
    /**
    * 사용중인 사이트아이디 목록을 가져온다.
    *
    * @Title       : getSiteidList 
    * @Description : 사용중인 사이트아이디 목록을 가져온다.
    * @return List<SiteInfoVo> SiteInfoVo 목록
    * @throws Exception 예외
    */
    public List<SiteInfoVo> getSiteidList() throws Exception;
}