/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.bizAply.req.model.CapabilityVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProcPlanVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProgramInfoVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 서비스 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.service
* - ReqMngService.java
* </pre> 
*
* @ClassName : ReqMngService
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ReqMngService {
    
    /**
    * 신청관리 리스트 조회. 
    *
    * @Title : selectReqMngList
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return List<ReqMngVo>
     */
    List<ReqMngVo> selectReqMngList(ReqMngVo reqMngVo) throws Exception;

    /**
    * 신청관리 목록 엑셀 다운로드. 
    *
    * @Title : reqMngListExcelDownload
    * @Description : TODO
    * @param reqMngVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void reqMngListExcelDownload(ReqMngVo reqMngVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * 신청자 목록 조회. 
    *
    * @Title : selectReqUserList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return List<ReqUserVo>
     */
    List<ReqUserVo> selectReqUserList(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 신청자 목록 엑셀 다운로드. 
    *
    * @Title : reqUserListExcelDownload
    * @Description : TODO
    * @param reqUserVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void reqUserListExcelDownload(ReqUserVo reqUserVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * 담당자 목록 조회. 
    *
    * @Title : selectUserList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return List<ReqUserVo>
     */
    List<ReqUserVo> selectUserList(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 선정여부 상태값 변경. 
    *
    * @Title : updateSlctnSttsCd
    * @Description : TODO
    * @param aplyids
    * @return
    * @throws Exception
    * @return int
     */
    int updateSlctnSttsCd(String[] aplyids) throws Exception;
    
    /**
    * 신청정보 수정. 
    *
    * @Title : updateReqInfo
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateReqInfo(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectSplmntList
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return List<SupplementVo>
     */
    List<SupplementVo> selectSplmntList(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청. 
    *
    * @Title : insertSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertSplmnt(SupplementVo supplementVo) throws Exception;
    
    /**
     * 보완요청 수정. 
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
    * 기관역량 조회. 
    *
    * @Title : detailInst
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return List<ReqUserVo>
     */
    CapabilityVo detailInst(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 기관역량 저장. 
    *
    * @Title : insertInst
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertInst(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 기관역량 수정. 
    *
    * @Title : updateInst
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateInst(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 전년도 지원사업 프로그램 주제 목록 조회. 
    *
    * @Title : selectInstPrgrmList
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return List<CapabilityVo>
     */
    List<CapabilityVo> selectInstPrgrmList(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 환경교육 운영성과 목록 조회. 
    *
    * @Title : selectInstOperRsltList
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return List<CapabilityVo>
     */
    List<CapabilityVo> selectInstOperRsltList(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 사업수행계획 조회. 
    *
    * @Title : detailPlan
    * @Description : TODO
    * @param procPlanVo
    * @return
    * @throws Exception
    * @return ProcPlanVo
     */
    ProcPlanVo detailPlan(ProcPlanVo procPlanVo) throws Exception;
    
    /**
    * 사업수행계획 등록. 
    *
    * @Title : insertPlan
    * @Description : TODO
    * @param procPlanVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertPlan(ProcPlanVo procPlanVo) throws Exception;
    
    /**
    * 사업수행계획 수정. 
    *
    * @Title : updatePlan
    * @Description : TODO
    * @param procPlanVo
    * @return
    * @throws Exception
    * @return int
     */
    int updatePlan(ProcPlanVo procPlanVo) throws Exception;
    
    /**
    * 프로그램정보 조회. 
    *
    * @Title : detailPrgrmInfo
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return ProgramInfoVo
     */
    ProgramInfoVo detailPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception;
    
    /**
    * 프로그램정보 등록. 
    *
    * @Title : insertPrgrmInfo
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception;
    
    /**
    * 프로그램정보 수정. 
    *
    * @Title : updatePrgrmInfo
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return int
     */
    int updatePrgrmInfo(ProgramInfoVo programInfoVo) throws Exception;
    
    /**
    * 프로그램 개요 목록 조회. 
    *
    * @Title : selectPrgrmList
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return List<ProgramInfoVo>
     */
    List<ProgramInfoVo> selectPrgrmList(ProgramInfoVo programInfoVo) throws Exception;
    
    /**
    * 프로그램 개요 등록. 
    *
    * @Title : insertPrgrm
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertPrgrm(ProgramInfoVo programInfoVo) throws Exception;
    
    /**
    * 프로그램 개요 삭제. 
    *
    * @Title : deletePrgrm
    * @Description : TODO
    * @param programInfoVo
    * @return
    * @throws Exception
    * @return int
     */
    int deletePrgrm(ProgramInfoVo programInfoVo) throws Exception;
}
