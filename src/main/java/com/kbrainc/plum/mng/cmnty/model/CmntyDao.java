package com.kbrainc.plum.mng.cmnty.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.qestnr.model.QitemVo;

/**
 * 
 * 커뮤니티 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.model
 * - CmntyDao.java
 * </pre> 
 *
 * @ClassName : CmntyDao
 * @Description : 커뮤니티 관리 DAO 
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CmntyDao {
    
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
     * 커뮤니티 게시판 템플릿 목록 조회
     *
     * @Title : selectCmntyCtgryList
     * @Description : 커뮤니티 게시판 템플릿 목록 조회
     * @param cmntyCtgryVo CmntyCtgryVo 객체
     * @return List<CmntyCtgryVo> 커뮤니티 게시판 템플릿 목록
     * @throws Exception 예외
     */
    public List<CmntyCtgryVo> selectCmntyCtgryList(CmntyCtgryVo cmntyCtgryVo) throws Exception;
    
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
    
    /**
     * 커뮤니티 게시글 정보 조회
     *
     * @Title : selectCmntyPstInfo
     * @Description : 커뮤니티 게시글 정보 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return CmntyPstVo CmntyPstVo 객체
     * @throws Exception 예외
     */
    public CmntyPstVo selectCmntyPstInfo(CmntyPstVo cmntyPstVo) throws Exception;
    
    /**
     * 커뮤니티 게시글 삭제
     *
     * @Title : deleteCmntyPst
     * @Description : 커뮤니티 게시글 삭제
     * @param cmntyPstVo CmntyPstVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteCmntyPst(CmntyPstVo cmntyPstVo) throws Exception;
    
    /**
     * 커뮤니티 댓글 목록 조회
     *
     * @Title : selectCmntyCmntList
     * @Description : 커뮤니티 댓글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return List<CmntyCmntVo> 커뮤니티 댓글 목록
     * @throws Exception 예외
     */
    public List<CmntyCmntVo> selectCmntyCmntList(CmntyPstVo cmntyPstVo) throws Exception;
    
    /**
     * 커뮤니티 댓글 삭제
     *
     * @Title : deleteCmntyCmnt
     * @Description : 커뮤니티 댓글 삭제
     * @param cmntyCmntVo CmntyCmntVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    public int deleteCmntyCmnt(CmntyCmntVo cmntyCmntVo) throws Exception;
    
}