package com.kbrainc.plum.mng.wbzn.now.prgrmgd.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;

/**
* 환경교육NOW -> 환경교육관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.envedu.model
* - EnveduDao.java
* </pre>
*
* @ClassName : EnveduDao
* @Description : 환경교육NOW -> 환경교육관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface PrgrmgdDao {
    
    /**
    * 환경교육관리 게시글 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육관리 게시글 목록 조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<PrgrmgdVo> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 등록
    *
    * @Title : insertEnvedu
    * @Description : 환경교육관리 게시글 등록
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertPrgrmgd(PrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 상세조회
    *
    * @Title : selectEnveduInfo
    * @Description : 환경교육관리 게시글 상세조회
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return EnveduVo
    */
    public PrgrmgdVo selectPrgrmgdInfo(PrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 환경교육관리 게시글 수정
    *
    * @Title : updateEnvedu
    * @Description : 환경교육관리 게시글 수정
    * @param enveduVo 환경교육관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updatePrgrmgd(PrgrmgdVo prgrmgdVo) throws Exception;
}
