package com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.carbon.prgrmgd.model.CarbonPrgrmgdVo;

/**
* 탄소중립환경교육 -> 프로그램안내관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.service
* - PrgrmgdService.java
* </pre>
*
* @ClassName : PrgrmgdService
* @Description : 환경교육NOW -> 프로그램안내관리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CarbonPrgrmgdService {
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonPrgrmgdVo> selectPrgrmgdList(CarbonPrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 등록
    *
    * @Title : insertPrgrmgd
    * @Description : 프로그램안내관리 게시글 등록
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertPrgrmgd(CarbonPrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 상세조회
    *
    * @Title : selectPrgrmgdInfo
    * @Description : 프로그램안내관리 게시글 상세조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return PrgrmgdVo
    */
    public CarbonPrgrmgdVo selectPrgrmgdInfo(CarbonPrgrmgdVo prgrmgdVo) throws Exception;
    
    /**
    * 프로그램안내관리 게시글 수정
    *
    * @Title : updatePrgrmgd
    * @Description : 프로그램안내관리 게시글 수정
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updatePrgrmgd(CarbonPrgrmgdVo prgrmgdVo) throws Exception;
}
