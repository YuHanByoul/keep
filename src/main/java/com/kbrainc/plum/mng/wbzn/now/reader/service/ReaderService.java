package com.kbrainc.plum.mng.wbzn.now.reader.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderVo;

/**
* 환경교육NOW -> 프로그램안내관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.service
* - PrgrmgdService.java
* </pre>
*
* @ClassName : PrgrmgdService
* @Description : 환경교육NOW -> 프로그램안내관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ReaderService {
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<ReaderVo> selectReaderList(ReaderVo readerVo) throws Exception;
}
