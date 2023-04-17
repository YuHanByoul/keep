package com.kbrainc.plum.front.cntnts.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.cntnts.model.CntntsDao;
import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 컨텐츠 관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.service
* - CntntsServiceImpl.java
* </pre>
*
* @ClassName : CntntsServiceImpl
* @Description : 컨텐츠 관리 서비스 구현 클래스
* @author : JD
* @date : 2023. 2. 2.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.cntntsServiceImpl")
@Alias("front.cntntsServiceImpl")
public class CntntsServiceImpl extends PlumAbstractServiceImpl implements CntntsService{

    @Resource(name = "front.cntntsDao")
    private CntntsDao cntntsDao;
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    @Override
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsList(cntntsVo);
    };
    
    /**
    * 컨텐츠 관리 게시글 상세조회
    *
    * @Title : selectCntntsInfo
    * @Description : 컨텐츠 게시글 상세조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return CntntsVo
    */
    @Override
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsInfo(cntntsVo);
    }
    
    /**
    * 조회수 증가
    *
    * @Title : updateCntntsHits
    * @Description : 조회수 증가
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional
    public int updateCntntsHits(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.updateCntntsHits(cntntsVo);
    };
    
    /**
    * 콘텐츠 썸네일 파일리스트
    *
    * @Title : selectCntntsFileList
    * @Description : 콘텐츠 썸네일 파일리스트
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    @Override
    public List<CntntsVo> selectCntntsFileList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsFileList(cntntsVo);
    };
}
