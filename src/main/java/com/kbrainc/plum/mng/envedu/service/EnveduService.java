package com.kbrainc.plum.mng.envedu.service;

import java.util.List;

import com.kbrainc.plum.mng.envedu.model.EnveduVo;

/**
* 환경교육NOW -> 환경교육관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.envedu.service
* - EnveduService.java
* </pre>
*
* @ClassName : EnveduService
* @Description : 환경교육NOW -> 환경교육관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EnveduService {
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<EnveduVo> selectEnveduList(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 게시글 등록
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertEnvedu(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EnveduVo
    */
    public EnveduVo selectEnveduInfo(EnveduVo enveduVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEnvedu
    * @Description : 환경교육관리 게시글 수정
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEnvedu(EnveduVo enveduVo) throws Exception;
}
