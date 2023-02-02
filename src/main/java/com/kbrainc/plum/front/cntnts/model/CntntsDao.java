package com.kbrainc.plum.front.cntnts.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 컨텐츠 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.model
* - CntntsDao.java
* </pre>
*
* @ClassName : CntntsDao
* @Description : 컨텐츠 관리 Dao 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.cntntsDao")
public interface CntntsDao {
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception;
}
