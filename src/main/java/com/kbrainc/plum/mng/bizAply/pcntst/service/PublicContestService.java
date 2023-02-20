/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

import com.kbrainc.plum.mng.bizAply.pcntst.model.GradeVo;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestMngGrpVo;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestVo;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 서비스 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.sprtBizPcntst.service
* - PublicContestService.java
* </pre> 
*
* @ClassName : PublicContestService
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface PublicContestService {
    
    /**
    * 공모관리 리스트 조회. 
    *
    * @Title : selectContestList
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return List<PublicContestVo>
     */
    List<PublicContestVo> selectContestList(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 담당자 목록 조회. 
    *
    * @Title : selectMngList
    * @Description : TODO
    * @param publicContestMngGrpVo
    * @return
    * @throws Exception
    * @return List<PublicContestMngGrpVo>
     */
    List<PublicContestMngGrpVo> selectMngList(PublicContestMngGrpVo publicContestMngGrpVo) throws Exception;
    
    /**
    * 심사표 목록 조회. 
    *
    * @Title : selectEvalSheetList
    * @Description : TODO
    * @param PublicContestVo
    * @return
    * @throws Exception
    * @return List<EgovMap>
     */
    List<EgovMap> selectEvalSheetList(PublicContestVo publicContestVo) throws Exception;
    
    /**
     * 교부 정보 조회. 
     *
     * @Title : selectDelevery
     * @Description : TODO
     * @param publicContestVo
     * @return
     * @throws Exception
     * @return List<PublicContestVo>
      */
     List<PublicContestVo> selectDelevery(PublicContestVo publicContestVo) throws Exception;
     
    /**
    * 공모관리 등록. 
    *
    * @Title : insertContest
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertContest(PublicContestVo publicContestVo) throws Exception;
    
    /**
    * 공모관리 수정. 
    *
    * @Title : updateContest
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateContest(PublicContestVo publicContestVo) throws Exception;
    
    /**
     * 심사배분율 조회. 
     *
     * @Title : selectDelevery
     * @Description : TODO
     * @param gradeVo
     * @return
     * @throws Exception
     * @return List<GradeVo>
      */
     List<GradeVo> selectGradeList(GradeVo gradeVo) throws Exception;
     
    /**
    * 공모관리 목록 엑셀 다운로드. 
    *
    * @Title : publicContestListExcelDownload
    * @Description : TODO
    * @param publicContestVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void publicContestListExcelDownload(PublicContestVo publicContestVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
     * 공모관리 전문가 그룹 리스트 조회. 
     *
     * @Title : selectMngGrpList
     * @Description : TODO
     * @param publicContestVo
     * @return
     * @throws Exception
     * @return List<PublicContestMngGrpVo>
      */
    List<PublicContestMngGrpVo> selectMngGrpList(PublicContestMngGrpVo publicContestVo) throws Exception;
}
