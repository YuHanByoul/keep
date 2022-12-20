package com.kbrainc.plum.mng.wbzn.carbon.opnn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.wbzn.carbon.opnn.model.CarbonOpnnDao;
import com.kbrainc.plum.mng.wbzn.carbon.opnn.model.CarbonOpnnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 탄소중립환경교육 -> 독자소리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.opnn.service
* - OpnnServiceImpl.java
* </pre>
*
* @ClassName : CarbonOpnnServiceImpl
* @Description : 탄소중립환경교육 -> 독자소리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CarbonOpnnServiceImpl extends PlumAbstractServiceImpl implements CarbonOpnnService{
    
    @Autowired
    private CarbonOpnnDao prgrmgdDao;
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param CarbonOpnnVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonOpnnVo> selectOpnnList(CarbonOpnnVo opnnVo) throws Exception {
        return prgrmgdDao.selectOpnnList(opnnVo);
    }
}
