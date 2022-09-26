package com.kbrainc.plum.rte.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.menu.MenuItem;

/**
 * 
 * 메뉴정보를 메모리에 적재하기 위한 DAO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - ResMenuDao.java
 * </pre> 
 *
 * @ClassName : ResMenuDao
 * @Description : 메뉴정보를 메모리에 적재하기 위한 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ResMenuDao {

    /**
     * @Title : selectSiteMenuList
     * @Description : 특정 사이트의 메뉴목록을 돌려준다
     * @param param Map타입의 인자
     * @throws SQLException
     * @return List<MenuItem> 특정 사이트의 메뉴 목록
     */
    @SuppressWarnings("unchecked")
    public List<MenuItem> selectSiteMenuList(Map param) throws SQLException;

    /**
     * @Title : selectSitecdListBySitegrp
     * @Description : 특정 사이트의 메뉴목록을 돌려준다
     * @param param Map타입의 인자
     * @throws SQLException
     * @return List<String> 특정 사이트의 메뉴 목록
     */
    @SuppressWarnings("unchecked")
    public List<String> selectSitecdListBySitegrp(Map param) throws SQLException;
}