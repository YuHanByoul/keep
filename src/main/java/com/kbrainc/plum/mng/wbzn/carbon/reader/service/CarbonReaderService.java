package com.kbrainc.plum.mng.wbzn.carbon.reader.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.carbon.reader.model.CarbonReaderVo;

/**
* 탄소중립환경교육 -> 구독자 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.reader.service
* - ReaderService.java
* </pre>
*
* @ClassName : CarbonReaderService
* @Description : 탄소중립환경교육 -> 구독자 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CarbonReaderService {
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonReaderVo> selectReaderList(CarbonReaderVo readerVo) throws Exception;
}
