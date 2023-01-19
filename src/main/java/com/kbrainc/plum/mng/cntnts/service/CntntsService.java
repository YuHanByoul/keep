package com.kbrainc.plum.mng.cntnts.service;

import java.util.List;

import javax.validation.Valid;

import com.kbrainc.plum.mng.cntnts.model.CntntsVo;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;

/**
* 컨텐츠 관리 서비스 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.service
* - CntntsService.java
* </pre>
*
* @ClassName : CntntsService
* @Description : 컨텐츠 관리 서비스 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CntntsService {
    
    public List<CntntsVo> selectCntntsList(CntntsVo cntntsVo) throws Exception;

    public int insertCntnts(CntntsVo cntntsVo) throws Exception;

    public CntntsVo selectCntntsInfo(CntntsVo cntntsVo) throws Exception;

    public int updateCntnts(CntntsVo cntntsVo) throws Exception;

    public int deleteCntnts(String[] cntntsids) throws Exception;

}
