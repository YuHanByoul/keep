package com.kbrainc.plum.mng.cntnts.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
    * 교육주제 코드 리스트 조회
    *
    * @Title : selectCntntsCdList
    * @Description : 교육주제 코드 리스트 조회
    * @param map
    * @return
    * @throws Exception
    * @return List<Map<String,String>>
    */
    public List<Map<String, String>> selectCntntsCdList(Map<String, String> map) throws Exception;

    /**
    * 교육주제 등록
    *
    * @Title : insertEduSbjct
    * @Description : 교육주제 등록
    * @param cntntsid
    * @param eduSbjctCds
    * @param user
    * @return
    * @throws Exception
    * @return int
    */
    public int insertEduSbjct(@Param("cntntsid") int cntntsid, @Param("eduSbjctCds") String[] eduSbjctCds, @Param("user") UserVo user) throws Exception;
    
    /**
    * 교육대상 등록
    *
    * @Title : insertEduTrgt
    * @Description : 교육대상 등록
    * @param cntntsid
    * @param eduTrgt
    * @param user
    * @return
    * @throws Exception
    * @return int
    */
    public int insertEduTrgt(@Param("cntntsid") int cntntsid, @Param("eduTrgt") String[] eduTrgt, @Param("user") UserVo user) throws Exception;

    /**
    * 콘텐츠 교육 주제 코드 맵핑 리스트 호출 
    *
    * @Title : selectCntntsEduSbjctList
    * @Description : selectCntntsCdList
    * @param cntntsEduSbjctVo
    * @return
    * @throws Exception
    * @return List<CntntsEduSbjctVo>
    */
    public List<CntntsEduSbjctVo> selectCntntsEduSbjctList(CntntsEduSbjctVo cntntsVo) throws Exception;

    /**
    * 콘텐츠 교육 대상 코드 리스트 호출
    *
    * @Title : selectCntntsEduTrgtList
    * @Description : 콘텐츠 교육 대상 코드 리스트 호출
    * @param cntntsVo
    * @return
    * @throws Exception
    * @return List<CntntsEduTrgtVo>
    */
    public List<CntntsEduTrgtVo> selectCntntsEduTrgtList(CntntsVo cntntsVo) throws Exception;
    
    /**
    * 교육주제 삭제
    *
    * @Title : deleteEduSbjct
    * @Description : 교육주제 삭제
    * @param cntntsVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteEduSbjct(CntntsVo cntntsVo) throws Exception;

    /**
    * 교육대상 삭제
    *
    * @Title : deleteEduTrgt
    * @Description : 교육대상 삭제
    * @param cntntsVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteEduTrgt(CntntsVo cntntsVo) throws Exception;
}
