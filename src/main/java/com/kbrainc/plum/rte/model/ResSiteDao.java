package com.kbrainc.plum.rte.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 
* 사이트정보를 메모리에 적재하기 위한 DAO 클래스.
*
* <pre>
* com.kbrainc.plum.rte.model
* - ResSiteDao.java
* </pre> 
*
* @ClassName : ResSiteDao
* @Description : 사이트정보를 메모리에 적재하기 위한 DAO 클래스
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ResSiteDao {

    /**
    * @Title : selectSiteInfoList
    * @Description : 특정 사이트정보을 돌려준다
    * @param map Map타입의 인자
    * @throws SQLException
    * @return SiteInfo 특정 사이트 정보
    */
    public SiteInfoVo selectSiteInfo(Map map) throws SQLException;

    /**
    * 사용중인 사이트아이디 목록을 가져온다.
    *
    * @Title       : getSiteidList 
    * @Description : 사용중인 사이트아이디 목록을 가져온다.
    * @return List<SiteInfoVo> SiteInfoVo 목록
    * @throws Exception 예외
    */
    public List<SiteInfoVo> selectSiteidList() throws Exception;
}