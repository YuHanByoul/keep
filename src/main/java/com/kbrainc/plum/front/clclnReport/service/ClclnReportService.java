/**
 * 
 */
package com.kbrainc.plum.front.clclnReport.service;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;

/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산보고관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.front.clclnReport.service
* - ClclnReportService.java
* </pre> 
*
* @ClassName : ClclnReportService
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ClclnReportService {

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
    * 정산보고서 제출. 
    *
    * @Title : updateClclnReport
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateClclnReport(SupplementVo supplementVo) throws Exception;
}
