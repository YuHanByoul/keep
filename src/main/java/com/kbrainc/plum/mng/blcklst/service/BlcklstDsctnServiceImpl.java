package com.kbrainc.plum.mng.blcklst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.blcklst.model.BlcklstDsctnDao;
import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 블랙리스트 내역 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.blcklst.service
* - BlcklstDsctnServiceImpl.java
* </pre>
*
* @ClassName : BlcklstDsctnServiceImpl
* @Description : 블랙리스트 내역 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BlcklstDsctnServiceImpl extends PlumAbstractServiceImpl implements BlcklstDsctnService{
    
    @Autowired
    private BlcklstDsctnDao blcklstDsctnDao;
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<BlcklstDsctnVo> selectBlcklstDsctnList(BlcklstDsctnVo readerVo) throws Exception {
        return blcklstDsctnDao.selectBlcklstDsctnList(readerVo);
    }
}
