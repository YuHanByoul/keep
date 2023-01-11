package com.kbrainc.plum.mng.envtcherTrnngInst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;

/**
* 환경교육사 양성기관 현황 서비스 클래스
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.service
* - EnvtcherTrnngInstService.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstService
* @Description : 환경교육사 양성기관 현황 서비스 클래스
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EnvtcherTrnngInstService {
    
    /**
    * 환경교육사 양성기관 등록 기능
    *
    * @Title : insertEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 등록 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 환경교육사 양성기관 수정 기능
    *
    * @Title : updateEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 수정 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 환경교육사 양성기관 목록 조회
    *
    * @Title : selectEnvtcherTrnngInstList
    * @Description : 환경교육사 양성기관 목록 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 환경교육사 양성기관 상세정보 조회
    *
    * @Title : selectEnvtcherTrnngInstInfo
    * @Description : 환경교육사 양성기관 상세정보 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return EnvtcherTrnngInstVo
    */
    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 엑셀다운로드
    *
    * @Title : envtcherTrnngInstExcelDownload
    * @Description : 엑셀다운로드
    * @param request 객체
    * @param response 객체
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return void
    */
    public void envtcherTrnngInstExcelDownload(HttpServletRequest request, HttpServletResponse response, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectAddrCtpvnList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception;

}
