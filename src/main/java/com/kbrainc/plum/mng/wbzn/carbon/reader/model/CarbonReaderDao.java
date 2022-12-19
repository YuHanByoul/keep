package com.kbrainc.plum.mng.wbzn.carbon.reader.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 탄소중립환경교육 -> 구독자 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.reader.model
* - ReaderDao.java
* </pre>
*
* @ClassName : CarbonReaderDao
* @Description : 탄소중립환경교육 -> 구독자 Dao 클래스
* @author : JD
* @date : 2022. 12. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CarbonReaderDao {
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param carbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonReaderVo> selectReaderList(CarbonReaderVo carbonReaderVo) throws Exception;
}
