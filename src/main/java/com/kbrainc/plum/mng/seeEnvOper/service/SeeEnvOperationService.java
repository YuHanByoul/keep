/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvOper.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.seeEnvOper.model.SeeEnvOperationVo;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.seeEnvOper.service
* - SeeEnvOperationService.java
* </pre> 
*
* @ClassName : SeeEnvOperationService
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface SeeEnvOperationService {

    /**
    * 운영관리 목록 조회 
    *
    * @Title : selectOperationList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return List<SeeEnvOperationVo>
     */
    List<SeeEnvOperationVo> selectOperationList(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 운영관리 목록 엑셀다운로드 
    *
    * @Title : seeEnvOperationListExcelDownload
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void seeEnvOperationListExcelDownload(SeeEnvOperationVo seeEnvOperationVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * 지정내역 조회
    *
    * @Title : selectDsgnList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return List<SeeEnvOperationVo>
     */
    List<SeeEnvOperationVo> selectDsgnList(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 변경신청 목록 조회 
    *
    * @Title : selectChangeList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return List<SeeEnvOperationVo>
     */
    List<SeeEnvOperationVo> selectChangeList(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 담당자 목록 조회. 
    *
    * @Title : selectUserList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return List<SeeEnvOperationVo>
     */
    List<SeeEnvOperationVo> selectUserList(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 지정정보 수정 
    *
    * @Title : updateInfo
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateInfo(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 지정정보변경 
    *
    * @Title : insertDsgn
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return int
     */
    int saveDsgn(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 지정상태 조회 
    *
    * @Title : selectStts
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return SeeEnvOperationVo
     */
    SeeEnvOperationVo selectStts(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 지정번호 중복 조회 
    *
    * @Title : selectDsgnno
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return int
     */
    int selectDsgnno(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
    
    /**
    * 변경신청 승인/불가 처리 
    *
    * @Title : updateChange
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateChange(SeeEnvOperationVo seeEnvOperationVo) throws Exception;
}
