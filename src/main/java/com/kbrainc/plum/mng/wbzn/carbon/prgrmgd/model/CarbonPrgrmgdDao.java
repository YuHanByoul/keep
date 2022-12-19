package com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;


/**
* 탄소중립환경교육 -> 프로그램안내관리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.model
* - PrgrmgdDao.java
* </pre>
*
* @ClassName : PrgrmgdDao
* @Description : 환경교육NOW -> 프로그램안내관리 Dao 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CarbonPrgrmgdDao {
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param carbonPrgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 등록
    *
    * @Title : insertPrgrmgd
    * @Description : 프로그램안내관리 게시글 등록
    * @param carbonPrgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertPrgrmgd(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 상세조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 프로그램안내관리 게시글 상세조회
    * @param carbonPrgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return PrgrmgdVo
    */
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 수정
    *
    * @Title : updatePrgrmgd
    * @Description : 프로그램안내관리 게시글 수정
    * @param carbonPrgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updatePrgrmgd(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception;
}
