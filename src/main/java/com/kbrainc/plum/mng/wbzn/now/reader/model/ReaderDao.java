package com.kbrainc.plum.mng.wbzn.now.reader.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 환경교육NOW -> 프로그램안내관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.model
* - PrgrmgdDao.java
* </pre>
*
* @ClassName : PrgrmgdDao
* @Description : 환경교육NOW -> 프로그램안내관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ReaderDao {
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param readerVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<ReaderVo> selectReaderList(ReaderVo readerVo) throws Exception;
}
