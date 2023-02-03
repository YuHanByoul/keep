package com.kbrainc.plum.front.cntnts.service;

import java.util.List;

import com.kbrainc.plum.front.cntnts.model.CntntsVo;



/**
* 컨텐츠 관리 서비스 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.service
* - CntntsService.java
* </pre>
*
* @ClassName : CntntsService
* @Description : 컨텐츠 관리 서비스 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CntntsService {
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return List<CntntsVo>
    */
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 컨텐츠 관리 게시글 상세조회
    *
    * @Title : selectCntntsInfo
    * @Description : 컨텐츠 관리 게시글 상세조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return CntntsVo
    */
    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception;

    public int updateCntntsHits(CntntsVo cntntsVo) throws Exception;
}
