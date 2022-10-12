package com.kbrainc.plum.front.schl.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 학교정보 DAO 클래스
*
* <pre>
* com.kbrainc.plum.front.member.model
* - MemberDao.java
* </pre> 
*
* @ClassName : SchlDao
* @Description : 학교정보 DAO 클래스
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Mapper("front.schlDao")
public interface SchlDao {

    /**
     *
     * 학교리스트 호출 
     *
     * @Title : selectSchlList
     * @Description : 
     * @param shclVo ShclVo객체
     * @return List<ShclVo> 
     * @throws Exception 예외
     */
    public List<SchlVo> selectSchlList(SchlVo shclVo) throws Exception;
    
}