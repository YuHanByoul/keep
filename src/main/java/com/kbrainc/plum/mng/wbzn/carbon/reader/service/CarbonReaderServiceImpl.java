package com.kbrainc.plum.mng.wbzn.carbon.reader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.wbzn.carbon.reader.model.CarbonReaderDao;
import com.kbrainc.plum.mng.wbzn.carbon.reader.model.CarbonReaderVo;
import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderDao;
import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 탄소중립환경교육 -> 구독자 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.reader.service
* - ReaderServiceImpl.java
* </pre>
*
* @ClassName : CarbonReaderServiceImpl
* @Description : 탄소중립환경교육 -> 구독자 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CarbonReaderServiceImpl extends PlumAbstractServiceImpl implements CarbonReaderService{
    
    @Autowired
    private CarbonReaderDao readerDao;
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonReaderVo> selectReaderList(CarbonReaderVo readerVo) throws Exception {
        return readerDao.selectReaderList(readerVo);
    }
}
