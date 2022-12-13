package com.kbrainc.plum.mng.wbzn.now.envedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.wbzn.now.envedu.model.EnveduDao;
import com.kbrainc.plum.mng.wbzn.now.envedu.model.EnveduVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 환경교육NOW -> 환경교육관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.service
* - EnveduServiceImpl.java
* </pre>
*
* @ClassName : EnveduServiceImpl
* @Description : 환경교육NOW -> 환경교육관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class EnveduServiceImpl extends PlumAbstractServiceImpl implements EnveduService{
    
    @Autowired
    private EnveduDao envprgrmDao;
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception {
        return envprgrmDao.selectEnveduList(enveduVo);
    }
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 게시글 등록
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEnvedu(EnveduVo enveduVo) throws Exception{
        return envprgrmDao.insertEnvedu(enveduVo);
    }
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EnveduVo
    */
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception{
        return envprgrmDao.selectEnveduInfo(enveduVo);
    }
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEnvedu
    * @Description : 환경교육관리 게시글 수정
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEnvedu(EnveduVo enveduVo) throws Exception{
        return envprgrmDao.updateEnvedu(enveduVo);
    }
}
