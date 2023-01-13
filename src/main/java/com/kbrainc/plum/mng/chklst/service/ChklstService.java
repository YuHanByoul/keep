package com.kbrainc.plum.mng.chklst.service;

import java.util.List;

import com.kbrainc.plum.mng.chklst.model.ChklstQitemMapngVo;
import com.kbrainc.plum.mng.chklst.model.ChklstQitemVo;
import com.kbrainc.plum.mng.chklst.model.ChklstVo;
import com.kbrainc.plum.mng.code.model.CodeVo;

/**
 * 
 * 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.service
 * - QestnrService.java
 * </pre> 
 *
 * @ClassName : ChklstService
 * @Description : 체크리스트관리 서비스 인터페이스 
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface ChklstService {
    
    /**
     * 체크리스트 문항 등록
     *
     * @Title : insertChklstQitem 
     * @Description : 체크리스트 문항 등록
     * @param chklstQitemVo ChklstQitemVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception;
    
    /**
     * 체크리스트 문항 구분코드 목록 조회
     *
     * @Title : selectChklstQitemCdList 
     * @Description : 체크리스트 문항 구분코드 목록 조회
     * @param codeVo CodeVo객체
     * @return List<CodeVo> 체크리스트 문항 구분코드 목록
     * @throws Exception 예외
     */
    public List<CodeVo> selectChklstQitemCdList(CodeVo codeVo) throws Exception;
    
    /**
     * 체크리스트 문항 목록 조회
     *
     * @Title : selectChklstQitemList 
     * @Description : 체크리스트 문항 목록 조회
     * @param chklstQitemVo ChklstQitemVo객체
     * @return List<ChklstQitemVo> 체크리스트 문항 목록
     * @throws Exception 예외
     */
    public List<ChklstQitemVo> selectChklstQitemList(ChklstQitemVo chklstQitemVo) throws Exception;
     
    /**
     * 체크리스트 문항 정보 조회
     *
     * @Title : selectChklstQitemInfo
     * @Description : 체크리스트 문항 정보 조회
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return ChklstQitemVo ChklstQitemVo 객체
     * @throws Exception 예외
     */
    public ChklstQitemVo selectChklstQitemInfo(ChklstQitemVo chklstQitemVo) throws Exception;
     
    /**
     * 체크리스트 문항 업데이트
     *
     * @Title : updateChklstQitem
     * @Description : 체크리스트 문항 업데이트
     * @param chklstQitemVo ChklstQitemVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateChklstQitem(ChklstQitemVo chklstQitemVo) throws Exception;
    
    /**
     * 체크리스트 등록
     *
     * @Title : insertChklst 
     * @Description : 체크리스트 등록
     * @param chklstVo ChklstVo객체
     * @return int insert 로우수
     * @throws Exception 예외
     */
    public int insertChklst(ChklstVo chklstVo) throws Exception;
    
    /**
     * 체크리스트 목록 조회
     *
     * @Title : selectChklstList 
     * @Description : 체크리스트 목록 조회
     * @param chklstVo ChklstVo객체
     * @return List<ChklstVo> 체크리스트 목록
     * @throws Exception 예외
     */
    public List<ChklstVo> selectChklstList(ChklstVo chklstVo) throws Exception;
    
    /**
     * 체크리스트 정보 조회
     *
     * @Title : selectChklstInfo
     * @Description : 체크리스트 정보 조회
     * @param chklstVo ChklstVo 객체
     * @return chklstVo ChklstVo 객체
     * @throws Exception 예외
     */
    public ChklstVo selectChklstInfo(ChklstVo chklstVo) throws Exception;
    
    /**
     * 사용중인 체크리스트 여부 확인
     *
     * @Title : isUseChklst 
     * @Description : 사용중인 체크리스트 여부 확인
     * @param chklstVo ChklstVo객체
     * @return ChklstVo ChklstVo 객체
     * @throws Exception 예외
     */
    public ChklstVo isUseChklst(ChklstVo chklstVo) throws Exception;
    
    /**
     * 체크리스트 업데이트
     *
     * @Title : updateChklst
     * @Description : 체크리스트 문항 업데이트
     * @param chklstVo ChklstVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateChklst(ChklstVo chklstVo) throws Exception;
    
    /**
     * 체크리스트 문항구성 목록 조회
     *
     * @Title : selectChklstQitemMapngList 
     * @Description : 체크리스트 문항구성 목록 조회
     * @param chklstQitemMapngVo ChklstQitemMapngVo객체
     * @return List<ChklstQitemMapngVo> 체크리스트 문항 구성 목록
     * @throws Exception 예외
     */
    public List<ChklstQitemMapngVo> selectChklstQitemMapngList(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception;
    
    /**
     * 체크리스트 문항구성 업데이트
     *
     * @Title : updateChklstQitemMapng
     * @Description : 체크리스트 문항 업데이트
     * @param chklstQitemMapngVo ChklstQitemMapngVo 객체
     * @return int update 로우수
     * @throws Exception 예외
     */
    public int updateChklstQitemMapng(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception;
     
}