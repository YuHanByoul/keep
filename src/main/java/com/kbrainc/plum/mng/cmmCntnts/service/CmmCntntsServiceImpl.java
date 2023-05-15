package com.kbrainc.plum.mng.cmmCntnts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsDao;
import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 콘텐츠 품질관리 체크리스트 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.cmmCntnts.service
* - CmmCntntsServiceImpl.java
* </pre>
**
@ClassName : CmmCntntsServiceImpl
* @Description : 콘텐츠 품질관리 체크리스트 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 4. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class CmmCntntsServiceImpl extends PlumAbstractServiceImpl implements CmmCntntsService{
    
    @Autowired
    private CmmCntntsDao cmmCntntsDao;
    
    /**
    * 콘텐츠 품질관리 체크리스트 체크ID 조회
    *
    * @Title : selectCmmCntntsQlityChkId
    * @Description : 콘텐츠 품질관리 체크리스트 체크 조회
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public Integer selectCmmCntntsQlityChkId(CmmCntntsVo cmmCntnts) throws Exception{
        return cmmCntntsDao.selectCmmCntntsQlityChkId(cmmCntnts);
    }
    
    /**
    * 콘텐츠 품질관리 체크리스트 목록 조회
    *
    * @Title : selectCmmCntntsList
    * @Description : 콘텐츠 품질관리 체크리스트 목록 조회
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return List<CmmCntntsVo>
    */
    @Override
    public List<CmmCntntsVo> selectCmmCntntsQlityChklstList(CmmCntntsVo cmmCntnts) throws Exception{
        return cmmCntntsDao.selectCmmCntntsQlityChklstList(cmmCntnts);
    }
    /**
    * 콘텐츠 품질관리 체크리스트 등록
    *
    * @Title : insertCmmCntnts
    * @Description : 콘텐츠 품질관리 체크리스트 등록
    * @param cmmCntntsVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertCmmCntnts(CmmCntntsVo cmmCntntsVo) throws Exception{
        int retVal = 0;
        retVal += cmmCntntsDao.insertCmmCntntsQlityCeck(cmmCntntsVo);
        retVal += cmmCntntsDao.insertCmmCntntsQlityCeckArtcl(cmmCntntsVo);
        
        return retVal;
    }
    
}
