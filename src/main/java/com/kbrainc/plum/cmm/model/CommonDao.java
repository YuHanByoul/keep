package com.kbrainc.plum.cmm.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.site.model.SiteVo;

/**
 * 
 * 어플리케이션 전체의 공통 요청을 처리하기 위한 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.model
 * - CommonDao.java
 * </pre> 
 *
 * @ClassName : CommonDao
 * @Description : 어플리케이션 전체의 공통 요청을 처리하기 위한 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CommonDao {
    
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
}
