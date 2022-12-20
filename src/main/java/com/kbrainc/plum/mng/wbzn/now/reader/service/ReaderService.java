package com.kbrainc.plum.mng.wbzn.now.reader.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderVo;

/**
* 환경교육NOW -> 구독자 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.reader.service
* - ReaderService.java
* </pre>
*
* @ClassName : ReaderService
* @Description : 환경교육NOW -> 구독자 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ReaderService {
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<ReaderVo> selectReaderList(ReaderVo readerVo) throws Exception;
}
