package com.kbrainc.plum.mng.code.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
 * 
 * 코드 관리 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.code.model
 * - CodeDao.java
 * </pre> 
 *
 * @ClassName : CodeDao
 * @Description : 코드 관리 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface CodeDao {

    /**
     * 코드그룹 목록을 가져온다.
     * 
     * @Title : selectCodeGrpList
     * @Description : 코드그룹 목록을 가져온다.
     * @param codeGrpVO :
     * @throws Exception :
     * @return List<CodeGrpVO> 코드그룹 목록
     */
    public List<CodeGrpVo> selectCodeGrpList(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드 목록을 가져온다.
     * 
     * @Title : selectCodeList
     * @Description : 코드 목록을 가져온다.
     * @param codeVo :
     * @throws Exception :
     * @return List<CodeVo> 코드 목록
     */
    public List<CodeVo> selectCodeList(CodeVo codeVo) throws Exception;

    /**
     * 코드그룹 정보를 가져온다.
     * 
     * @Title : selectCodeGrpInfo
     * @Description : 코드그룹 정보를 가져온다.
     * @param codeGrpVO :
     * @throws Exception :
     * @return CodeGrpVO 코드그룹 정보
     */
    public CodeGrpVo selectCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드그룹 정보 등록.
     * 
     * @Title : insertCodeGrpInfo
     * @Description : 코드그룹 정보 등록
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return int 저장로우수
     */
    public int insertCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드그룹 정보 수정.
     * 
     * @Title : updateCodeGrpInfo
     * @Description : 코드그룹 정보 수정
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return int 수정로우수
     */
    public int updateCodeGrpInfo(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드 트리.
     * 
     * @Title : selectCodeTreeList
     * @Description : 코드 트리
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return List<TreeItem> 코드 트리 목록
     */
    public List<TreeItem> selectCodeTreeList(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드 정보를 가져온다.
     * 
     * @Title : selectCodeInfo
     * @Description : 코드 정보를 가져온다.
     * @param cd 코드 :
     * @throws Exception :
     * @return CodeVo 코드 정보
     */
    public CodeVo selectCodeInfo(String cd) throws Exception;

    /**
     * 코드 순서재조정 자리세팅.
     * 
     * @Title : updateCodeReOrder
     * @Description : 코드 순서재조정 자리세팅
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     */
    public void updateCodeReOrder(CodeVo codeVo) throws Exception;

    /**
     * 코드 순서재조정 자리세팅.
     * 
     * @Title : updateCodeReOrderPrnDiff
     * @Description : 코드 순서재조정 자리세팅
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     */
    public void updateCodeReOrderPrnDiff(CodeVo codeVo) throws Exception;

    /**
     * 코드 순서재조정 자리세팅.
     * 
     * @Title : updateCodeReOrderPrnDiffOrgin
     * @Description : 코드 순서재조정 자리세팅
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     */
    public void updateCodeReOrderPrnDiffOrgin(CodeVo codeVo) throws Exception;

    /**
     * 트리저장시 코드 정보 수정.
     * 
     * @Title : updateCodeTreeInfo
     * @Description : 트리저장시 코드 정보 수정
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return int update로우수
     */
    public int updateCodeTreeInfo(CodeVo codeVo) throws Exception;

    /**
     * 코드 정보 등록.
     * 
     * @Title : insertCodeInfo
     * @Description : 코드 정보 등록
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return int 저장로우수
     */
    public int insertCodeInfo(CodeVo codeVo) throws Exception;

    /**
     * 코드 정보 수정.
     * 
     * @Title : updateCodeInfo
     * @Description : 코드 정보 수정
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return int 수정로우수
     */
    public int updateCodeInfo(CodeVo codeVo) throws Exception;
    
    /**
     * 자격증 코드 리스트.
     *
     * @Title       : selectCertCodeList 
     * @Description : 자격증 코드 리스트.
     * @param codeVo
     * @return List<CodeVo>
     * @throws Exception
     */
    public List<CodeVo> selectCertCodeList(CodeVo codeVo) throws Exception;
    
}