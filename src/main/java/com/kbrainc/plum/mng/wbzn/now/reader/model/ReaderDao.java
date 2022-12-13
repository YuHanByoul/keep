package com.kbrainc.plum.mng.wbzn.now.reader.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 환경교육NOW -> 구독자 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.reader.model
* - ReaderDao.java
* </pre>
*
* @ClassName : ReaderDao
* @Description : 환경교육NOW -> 구독자 Dao 클래스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ReaderDao {
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param readerVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<ReaderVo> selectReaderList(ReaderVo readerVo) throws Exception;
}
