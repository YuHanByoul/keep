package com.kbrainc.plum.mng.wbzn.now.reader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderDao;
import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육NOW -> 구독자 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.reader.service
* - ReaderServiceImpl.java
* </pre>
*
* @ClassName : ReaderServiceImpl
* @Description : 환경교육NOW -> 구독자 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ReaderServiceImpl extends PlumAbstractServiceImpl implements ReaderService{
    
    @Autowired
    private ReaderDao readerDao;
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<ReaderVo> selectReaderList(ReaderVo readerVo) throws Exception {
        return readerDao.selectReaderList(readerVo);
    }
}
