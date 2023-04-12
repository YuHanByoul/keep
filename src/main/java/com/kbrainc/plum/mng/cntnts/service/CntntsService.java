package com.kbrainc.plum.mng.cntnts.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kbrainc.plum.mng.cntnts.model.CntntsEduSbjctVo;
import com.kbrainc.plum.mng.cntnts.model.CntntsEduTrgtVo;
import com.kbrainc.plum.mng.cntnts.model.CntntsVo;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.mng.pack.model.PackageEduSbjctVo;
import com.kbrainc.plum.mng.pack.model.PackageEduTrgtVo;

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
    
    /**
    * 컨텐츠 관리 게시글 등록
    *
    * @Title : insertCntnts
    * @Description : 컨텐츠 관리 게시글 등록
    * @param cntntsVo 객체
     * @param eduTrgt 
     * @param eduSbjctCds 
    * @throws Exception 예외
    * @return int
    */
    public int insertCntnts(CntntsVo cntntsVo, String[] eduSbjctCds, String[] eduTrgt) throws Exception;
    
    /**
    * 컨텐츠 관리 게시글 수정
    *
    * @Title : updateCntnts
    * @Description : 컨텐츠 관리 게시글 수정
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateCntnts(CntntsVo cntntsVo, String[] eduSbjctCds, String[] eduTrgt) throws Exception;
    
    /**
    * 컨텐츠 괸리 게시글 삭제
     * @param cntntsVo 
    *
    * @Title : deleteCntnts
    * @Description : 컨텐츠 괸리 게시글 삭제
    * @param cntntsids 컨텐츠아이디
    * @throws Exception 예외
    * @return int
    */
    public int deleteCntnts(CntntsVo cntntsVo, String[] cntntsids) throws Exception;

    public List<Map<String,String>> selectCntntsCdList(Map<String,String> map) throws Exception;

    public List<CntntsEduSbjctVo> selectCntntsEduSbjctList(CntntsEduSbjctVo cntntsEduSbjctVo) throws Exception;

    public List<CntntsEduTrgtVo> selectCntntsEduTrgtList(CntntsVo cntntsVo) throws Exception;
}
