package com.kbrainc.plum.mng.wbzn.now.opnn.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 환경교육NOW -> 독자소리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.opnn.model
* - OpnnDao.java
* </pre>
*
* @ClassName : OpnnDao
* @Description : 환경교육NOW -> 독자소리 Dao 클래스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface OpnnDao {
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param opnnVo 독자소리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<OpnnVo> selectOpnnList(OpnnVo opnnVo) throws Exception;
}
