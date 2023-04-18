package com.kbrainc.plum.mng.wbzn.now.opnn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.wbzn.now.opnn.model.OpnnDao;
import com.kbrainc.plum.mng.wbzn.now.opnn.model.OpnnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육NOW -> 독자소리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.opnn.service
* - OpnnServiceImpl.java
* </pre>
*
* @ClassName : OpnnServiceImpl
* @Description : 환경교육NOW -> 독자소리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class OpnnServiceImpl extends PlumAbstractServiceImpl implements OpnnService{
    
    @Autowired
    private OpnnDao prgrmgdDao;
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param CarbonOpnnVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    @Override
    public List<OpnnVo> selectOpnnList(OpnnVo opnnVo) throws Exception {
        return prgrmgdDao.selectOpnnList(opnnVo);
    }
}
