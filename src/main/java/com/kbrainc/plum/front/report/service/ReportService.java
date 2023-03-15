/**
 * 
 */
package com.kbrainc.plum.front.report.service;

import java.util.List;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.report.model.ReportOperVo;
import com.kbrainc.plum.front.report.model.ReportVo;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.front.report.service
* - ReportService.java
* </pre> 
*
* @ClassName : ReportService
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ReportService {

    /**
    * 사업보고관리 목록 조회. 
    *
    * @Title : selectDelvryAplyList
    * @Description : TODO
    * @param reportVo
    * @return
    * @throws Exception
    * @return List<ReportVo>
     */
    List<ReportVo> selectReportList(ReportVo reportVo) throws Exception;
     
    /**
     * 
    * 사업보고관리 저장. 
    *
    * @Title : saveReport
    * @Description : TODO
    * @param reportVo
    * @return
    * @throws Exception
    * @return int
     */
    int saveReport(ReportVo reportVo) throws Exception;

    /**
    * 사업보고관리 교육운영정보 목록 조회. 
    *
    * @Title : selectReportOperList
    * @Description : TODO
    * @param reportVo
    * @return
    * @throws Exception
    * @return List<ReportOperVo>
     */
    List<ReportOperVo> selectReportOperList(ReportOperVo reportVo) throws Exception;
    
    /**
    * 보완요청 상세조회 
    *
    * @Title : selectSplmntInfo
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return SupplementVo
     */
    SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청 답변 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateSplmnt(SupplementVo supplementVo) throws Exception;

    /**
    * 컨설팅 정보 조회. 
    *
    * @Title : selectConsulting
    * @Description : TODO
    * @param reportVo
    * @return
    * @throws Exception
    * @return ReportVo
     */
    ReportVo selectConsulting(ReportVo reportVo) throws Exception;
}
