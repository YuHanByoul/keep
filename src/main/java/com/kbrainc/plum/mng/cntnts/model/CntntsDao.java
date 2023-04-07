package com.kbrainc.plum.mng.cntnts.model;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 컨텐츠 관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.model
* - CntntsDao.java
* </pre>
*
* @ClassName : CntntsDao
* @Description : 컨텐츠 관리 Dao 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CntntsDao {
    
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
    * @throws Exception 예외
    * @return int
    */
    public int insertCntnts(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 컨텐츠 관리 게시글 수정
    *
    * @Title : updateCntnts
    * @Description : 컨텐츠 관리 게시글 수정
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateCntnts(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 컨텐츠 괸리 게시글 삭제
    *
    * @Title : deleteCntnts
    * @Description : 컨텐츠 괸리 게시글 삭제
    * @param cntntsids 컨텐츠아이디
    * @throws Exception 예외
    * @return int
    */
    public int deleteCntnts(@Param("user") UserVo user, @Param("cntntsids") String[] cntntsids) throws Exception;

    public List<Map<String, String>> selectCntntsCdList(Map<String, String> map) throws Exception;

    public int insertEduSbjct(@Param("cntntsid") int cntntsid, @Param("eduSbjctCds") String[] eduSbjctCds, @Param("user") UserVo user) throws Exception;
    
    public int insertEduTrgt(@Param("cntntsid") int cntntsid, @Param("eduTrgt") String[] eduTrgt, @Param("user") UserVo user) throws Exception;

    public List<CntntsEduSbjctVo> selectCntntsEduSbjctList(CntntsVo cntntsVo) throws Exception;

    public List<CntntsEduTrgtVo> selectCntntsEduTrgtList(CntntsVo cntntsVo) throws Exception;

    public int deleteEduSbjct(CntntsVo cntntsVo) throws Exception;

    public int deleteEduTrgt(CntntsVo cntntsVo) throws Exception;

}
