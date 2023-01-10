package com.kbrainc.plum.mng.pvtEnveduGrp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;

/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.service
* - PvtEnveduGrpService.java
* </pre>
*
* @ClassName : PvtEnveduGrpService
* @Description : TODO
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface PvtEnveduGrpService {

    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    public List<PvtEnvEduGrpVo> selectAddrCtpvnList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception;
    
    public void selectPvtEnveduGrpExcelDownload(PvtEnvEduGrpVo pvtEnvEduGrpVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
}
