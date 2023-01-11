package com.kbrainc.plum.mng.pvtEnveduGrp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;

/**
* 민간 환경교육단체 현황 서비스 클래스
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.service
* - PvtEnveduGrpService.java
* </pre>
*
* @ClassName : PvtEnveduGrpService
* @Description : 민간 환경교육단체 현황 서비스 클래스
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface PvtEnveduGrpService {
    
    /**
    * 민간 환경교육단체 목록 조회
    *
    * @Title : selectPvtEnveduGrpList
    * @Description : 민간 환경교육단체 목록 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectAddrCtpvnList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    /**
    * 엑셀다운로드
    *
    * @Title : selectPvtEnveduGrpExcelDownload
    * @Description : 엑셀다운로드
    * @param pvtEnvEduGrpVo 객체
    * @param response 객체
    * @param request 객체
    * @throws Exception 예외
    * @return void
    */
    public void selectPvtEnveduGrpExcelDownload(PvtEnvEduGrpVo pvtEnvEduGrpVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
}
