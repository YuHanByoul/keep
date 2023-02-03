package com.kbrainc.plum.front.cntnts.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

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
* @date : 2023. 1. 12.
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
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsList(cntntsVo);
    };
    
    /**
    * 컨텐츠 관리 게시글 상세조회
    *
    * @Title : selectCntntsInfo
    * @Description : 컨텐츠 관리 게시글 상세조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return CntntsVo
    */
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception {
        return cntntsDao.selectCntntsInfo(cntntsVo);
    }
}
