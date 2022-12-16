package com.kbrainc.plum.mng.cmnty.service;

import java.util.List;

import com.kbrainc.plum.mng.cmnty.model.CmntyMbrVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyPstVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;

/**
 * 
 * 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.service
 * - CmntyService.java
 * </pre> 
 *
 * @ClassName : CmntyService
 * @Description : 커뮤니티 관리 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface CmntyService {
    
    /**
     * 커뮤니티 목록 조회
     *
     * @Title : selectCmntyList
     * @Description : 커뮤니티 목록 조회
     * @param cmntyVo CmntyVo 객체
     * @return List<CmntyVo> 커뮤니티 목록
     * @throws Exception 예외
     */
    public List<CmntyVo> selectCmntyList(CmntyVo cmntyVo) throws Exception;
    
    /**
     * 커뮤니티 정보 조회
     *
     * @Title : selectCmntyInfo
     * @Description : 커뮤니티 정보 조회
     * @param cmntyVo CmntyVo 객체
     * @return CmntyVo CmntyVo 객체
     * @throws Exception 예외
     */
    public CmntyVo selectCmntyInfo(CmntyVo cmntyVo) throws Exception;
    
    /**
     * 커뮤니티 회원 목록 조회
     *
     * @Title : selectCmntyMbrList
     * @Description : 커뮤니티 회원 목록 조회
     * @param cmntyMbrVo CmntyMbrVo 객체
     * @return List<CmntyMbrVo> 커뮤니티 회원 목록
     * @throws Exception 예외
     */
    public List<CmntyMbrVo> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo) throws Exception;
    
    /**
     * 커뮤니티 게시글 목록 조회
     *
     * @Title : selectCmntyPstList
     * @Description : 커뮤니티 게시글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return List<CmntyPstVo> 커뮤니티 게시글 목록
     * @throws Exception 예외
     */
    public List<CmntyPstVo> selectCmntyPstList(CmntyPstVo cmntyPstVo) throws Exception;
    
}