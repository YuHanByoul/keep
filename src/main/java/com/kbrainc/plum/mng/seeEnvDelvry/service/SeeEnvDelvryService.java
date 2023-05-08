/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvDelvry.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.seeEnvDelvry.model.SeeEnvDelvryVo;

/**
* 사회환경교육기관 지정 > 교부관리 서비스
*
* <pre>
* com.kbrainc.plum.mng.seeEnvDelvry.service
* - SeeEnvDelvryService.java
* </pre> 
*
* @ClassName : SeeEnvDelvryService
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface SeeEnvDelvryService {

    /**
    * 교부관리 목록 조회 
    *
    * @Title : selectDelvryList
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @return
    * @throws Exception
    * @return List<SeeEnvDelvryVo>
     */
    List<SeeEnvDelvryVo> selectDelvryList(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception;
    
    /**
    * 발급 / 재발급 
    *
    * @Title : updateIssue
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateIssue(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception;
    
    /**
    * 교부대장 다운로드
    *
    * @Title : seeEnvDelvryListExcelDownload
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void seeEnvDelvryListExcelDownload(SeeEnvDelvryVo seeEnvDelvryVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
}
