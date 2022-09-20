package com.kbrainc.plum.mng.code.service;

import java.util.List;

import com.kbrainc.plum.mng.code.model.CodeGrpVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.rte.lib.tree.TreeItem;

/**
 * 
 * 코드 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.code.service
 * - CodeService.java
 * </pre> 
 *
 * @ClassName : CodeService
 * @Description : 코드 관리 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public interface CodeService {

    /**
     * 코드그룹 리스트 가져옴.
     * 
     * @Title : selectCodeGrpList
     * @Description : 코드그룹 리스트 가져옴
     * @param codeGrpVO 코드그룹VO 클래스
     * @throws Exception :
     * @return List<CodeGrpVO> 코드그룹 목록
     */
    public List<CodeGrpVo> selectCodeGrpList(CodeGrpVo codeGrpVO) throws Exception;

    /**
     * 코드 리스트 가져옴.
     * 
     * @Title : selectCodeList
     * @Description : 코드 리스트 가져옴
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return List<CodeVo> 코드 목록
     */
    public List<CodeVo> selectCodeList(CodeVo codeVo) throws Exception;

    /**
     * 코드그룹 정보 가져옴.
     * 
     * @Title : selectCodeGrpInfo
     * @Description : 코드그룹 정보 가져옴
     * @param codeGrpVO 코드그룹VO 클래스
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
     * 코드 정보 가져옴.
     * 
     * @Title : selectCodeInfo
     * @Description : 코드 정보 가져옴
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return CodeVo 코드 정보
     */
    public CodeVo selectCodeInfo(CodeVo codeVo) throws Exception;

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
     * 코드의 순서.
     * 
     * @Title : updateCodeTreeReorder
     * @Description : 코드의 순서
     * @param codeVo 코드VO 클래스
     * @throws Exception :
     * @return int 처리성공값
     */
    public int updateCodeTreeReorder(CodeVo codeVo) throws Exception;

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
    
}