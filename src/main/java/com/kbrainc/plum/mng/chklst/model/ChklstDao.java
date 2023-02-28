package com.kbrainc.plum.mng.chklst.model;

import com.kbrainc.plum.mng.code.model.CodeVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 
 * 체크리스트관리 DAO
 *
 * <pre>
 * com.kbrainc.plum.mng.chklst.model
 * - ChklstDao.java
 * </pre> 
 *
 * @ClassName : ChklstDao
 * @Description : 체크리스트관리 DAO 
 * @author : KBRAINC
 * @date : 2023. 01. 04.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ChklstDao {
    
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
     * @param codeVo CodeVo 객체
     * @return List<CodeVo> 체크리스트 문항 구분코드 목록
     * @throws Exception 예외
     */
    public List<CodeVo> selectChklstQitemCdList(CodeVo codeVo) throws Exception;
    
    /**
     * 체크리스트 문항 목록 조회
     *
     * @Title : selectChklstQitemList
     * @Description : 체크리스트 문항 목록 조회
     * @param chklstQitemVo ChklstQitemVo 객체
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
     * @param chklstVo  chklstVo 객체
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
     * @return ChklstVo ChklstVo 객체
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
     * @Description : updateChklst 업데이트
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
     * @param chklstQitemMapngVo  ChklstQitemMapngVo 객체
     * @return List<ChklstQitemMapngVo> 체크리스트 문항구성 목록
     * @throws Exception 예외
     */
    public List<ChklstQitemMapngVo> selectChklstQitemMapngList(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception;

    /**
     * 체크리스트 1,2뎁스 순서 삭제 전 있는지 체크
     * Title : selectChklstQitemOrdr
     * Description : 체크리스트 1,2뎁스 순서 삭제 전 있는지 체크
     *
     * @param chklstQitemMapngVo
     * @return boolean
     */
    int selectChklstQitemOrdr(ChklstQitemMapngVo chklstQitemMapngVo);

    /**
     * 체크리스트 1,2뎁스 순서 삭제
     * Title : deleteChklstSeCdOrdr
     * Description : 체크리스트 1,2뎁스 순서 삭제
     *
     * @param chklstQitemMapngVo
     * @return boolean
     */
    boolean deleteChklstSeCdOrdr(ChklstQitemMapngVo chklstQitemMapngVo);

    /**
     * 체크리스트 1,2뎁스 순서 등록
     * Title : insertChklstSeCdOrdr
     * Description : 체크리스트 1,2뎁스 순서 등록
     *
     * @param chklstQitemMapngVo
     * @return boolean
     */
    boolean insertChklstSeCdOrdr(ChklstQitemMapngVo chklstQitemMapngVo);

    /**
     * 체크리스트 문항 매핑 삭제 전 있는지 체크
     * Title : selectChklstQitemMang
     * Description : 체크리스트 문항 매핑 삭제 전 있는지 체크
     *
     * @param chklstQitemMapngVo
     * @return boolean
     */
    int selectChklstQitemMang(ChklstQitemMapngVo chklstQitemMapngVo);

    /**
     * 체크리스트 문항 매핑 삭제
     * Title : deleteChklstQitem
     * Description : 체크리스트 문항 매핑 삭제
     *
     * @param chklstQitemMapngVo
     * @return boolean
     */
    boolean deleteChklstQitem(ChklstQitemMapngVo chklstQitemMapngVo);

    /**
     * 체크리스트 문항구성 등록
     * Title : insertChklstQitemMapng
     * Description : 체크리스트 문항구성 등록
     *
     * @param chklstQitemMapngVo
     * @return boolean
     * @throws Exception
     */
    boolean insertChklstQitemMapng(ChklstQitemMapngVo chklstQitemMapngVo) throws Exception;
}