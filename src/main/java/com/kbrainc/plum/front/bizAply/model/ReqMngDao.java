/**
 * 
 */
package com.kbrainc.plum.front.bizAply.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.code.model.CodeVo;

/**
* 공모신청 DAO 인터페이스. 
*
* <pre>
* com.kbrainc.plum.front.bizAply.model
* - PublicContestAplyDao.java
* </pre> 
*
* @ClassName : PublicContestAplyDao
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.reqMngDao")
public interface ReqMngDao {
    
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
    * 조회수 증가. 
    *
    * @Title : updateHits
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateHits(ReqMngVo reqMngVo) throws Exception;
    
    /**
    * 신청여부조회. 
    *
    * @Title : getAplyCnt
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return ReqUserVo
     */
    ReqUserVo getAplyCnt(ReqMngVo reqMngVo) throws Exception;
    
    /**
    * 신청자(기관) 기본정보 조회 
    *
    * @Title : userBaseInfoDetail
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return ReqUserVo
     */
    ReqUserVo userBaseInfoDetail(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 신청정보 조회. 
    *
    * @Title : detailReqUser
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return ReqUserVo
     */
    ReqUserVo detailReqUser(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 기본정보 등록. 
    *
    * @Title : insertBaseInfo
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBaseInfo(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 기본정보 수정. 
    *
    * @Title : updateBaseInfo
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateBaseInfo(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 기관역량 조회. 
    *
    * @Title : detailBizAble
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return List<ReqUserVo>
     */
    CapabilityVo detailBizAble(CapabilityVo capabilityVo) throws Exception;

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
    * @param operationResultVo
    * @return
    * @throws Exception
    * @return List<CapabilityResultVo>
     */
    List<CapabilityResultVo> selectInstOperRsltList(CapabilityResultVo capabilityVo) throws Exception;
    
    /**
    * 기관역량 등록. 
    *
    * @Title : insertBizAble
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBizAble(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 전년도 지원사업 프로그램 주제 등록. 
    *
    * @Title : insertBizAbleBefore
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBizAbleBefore(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 전년도 지원사업 프로그램 주제 삭제. 
    *
    * @Title : deleteBizAbleBefore
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteBizAbleBefore(CapabilityVo capabilityVo) throws Exception;
    
    /**
    * 환경교육 운영성과 저장. 
    *
    * @Title : insertBizAbleOperRslt
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBizAbleOperRslt(CapabilityResultVo capabilityVo) throws Exception;
    
    /**
    * 환경교육 운영성과 삭제. 
    *
    * @Title : deleteBizAbleOperRslt
    * @Description : TODO
    * @param capabilityVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteBizAbleOperRslt(CapabilityVo capabilityVo) throws Exception;
    
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
     List<PrgrmInfoOutlineVo> selectPrgrmList(ProgramInfoVo programInfoVo) throws Exception;
     
     /**
     * 프로그램 개요 등록. 
     *
     * @Title : insertPrgrmOutline
     * @Description : TODO
     * @param programInfoVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertPrgrmOutline(PrgrmInfoOutlineVo programInfoVo) throws Exception;
     
     /**
     * 프로그램 개요 삭제. 
     *
     * @Title : deletePrgrmOutline
     * @Description : TODO
     * @param programInfoVo
     * @return
     * @throws Exception
     * @return int
      */
     int deletePrgrmOutline(ProgramInfoVo programInfoVo) throws Exception;
     
     /**
     * 총괄 지도자 정보 조회. 
     *
     * @Title : detailSmrLeader
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return SmrLeaderVo
      */
     SmrLeaderVo detailSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
      * 총괄 지도자 관리방안 수정. 
      *
      * @Title : updateSmrLeaderMgt
      * @Description : TODO
      * @param smrLeaderVo
      * @return
      * @throws Exception
      * @return int
      */
     int updateSmrLeaderMgt(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 정보 수정. 
     *
     * @Title : insertSmrLeaderInfo
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertSmrLeaderInfo(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
      * 총괄 지도자 정보 수정. 
      *
      * @Title : updateSmrLeaderInfo
      * @Description : TODO
      * @param smrLeaderVo
      * @return
      * @throws Exception
      * @return int
      */
     int updateSmrLeaderInfo(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 전문인력 보유현황 및 운영계획. 
     *
     * @Title : selectLeaderPlanList
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return List<SmrLeaderJobVo>
      */
     List<SmrLeaderJobVo> selectLeaderPlanList(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 전문인력 보유현황 및 운영계획 삭제. 
     *
     * @Title : deleteLeaderPlan
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return int
      */
     int deleteLeaderPlan(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 전문인력 보유현황 및 운영계획 등록 
     *
     * @Title : insertLeaderPlan
     * @Description : TODO
     * @param smrLeaderJobVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertLeaderPlan(SmrLeaderJobVo smrLeaderJobVo) throws Exception;
     
     /**
     * 총괄 지도자 학력 조회. 
     *
     * @Title : selectLeaderAbilList
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return List<SmrLeaderAcbgVo>
      */
     List<SmrLeaderAcbgVo> selectLeaderAbilList(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 학력 삭제. 
     *
     * @Title : deleteLeaderAbil
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return int
      */
     int deleteLeaderAbil(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 학력 등록. 
     *
     * @Title : insertLeaderAbil
     * @Description : TODO
     * @param smrLeaderAcbgVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertLeaderAbil(SmrLeaderAcbgVo smrLeaderAcbgVo) throws Exception;
     
     /**
     * 총괄 지도자 자격 조회
     *
     * @Title : selectLeaderLicList
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return List<SmrLeaderLicVo>
      */
     List<SmrLeaderLicVo> selectLeaderLicList(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 자격 삭제. 
     *
     * @Title : deleteLeaderLic
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return int
      */
     int deleteLeaderLic(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 자격 등록. 
     *
     * @Title : insertLeaderLic
     * @Description : TODO
     * @param smrLeaderLicVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertLeaderLic(SmrLeaderLicVo smrLeaderLicVo) throws Exception;
     
     /**
     * 총괄 지도자 경력 조회. 
     *
     * @Title : selectLeaderCarrList
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return List<SmrLeaderCarrVo>
      */
     List<SmrLeaderCarrVo> selectLeaderCarrList(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 경력 삭제. 
     *
     * @Title : deleteLeaderCarr
     * @Description : TODO
     * @param smrLeaderVo
     * @return
     * @throws Exception
     * @return int
      */
     int deleteLeaderCarr(SmrLeaderVo smrLeaderVo) throws Exception;
     
     /**
     * 총괄 지도자 경력 등록. 
     *
     * @Title : insertLeaderCarr
     * @Description : TODO
     * @param smrLeaderCarrVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertLeaderCarr(SmrLeaderCarrVo smrLeaderCarrVo) throws Exception;
     
     /**
     * 안전관리 조회. 
     *
     * @Title : detailSafetyMng
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return SafetyMngVo
      */
     SafetyMngVo detailSafetyMng(SafetyMngVo safetyMngVo) throws Exception;
     
     /**
     * 평가방식 조회. 
     *
     * @Title : detailPrgrmEvl
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return SafetyMngVo
      */
     PrgrmEvlVo detailPrgrmEvl(SafetyMngVo safetyMngVo) throws Exception;
     
     /**
     * 안전관리 수정. 
     *
     * @Title : updateSafetyMng
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertSafetyMng(SafetyMngVo safetyMngVo) throws Exception;
     
     /**
     * 평가방식 수정. 
     *
     * @Title : updatePrgrmEvl
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertPrgrmEvl(SafetyMngVo safetyMngVo) throws Exception;
     
     /**
     * 소요예산 탭 조회. 
     *
     * @Title : selectBudgetList
     * @Description : TODO
     * @param budgetVo
     * @return
     * @throws Exception
     * @return List<BudgetVo>
      */
     List<BudgetVo> selectBudgetList(BudgetVo budgetVo) throws Exception;
     
     /**
     * 소요예산 등록. 
     *
     * @Title : insertBudget
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return int
      */
     int insertBudget(BudgetVo safetyMngVo) throws Exception;

     /**
     * 소요예산 수정. 
     *
     * @Title : updateBudget
     * @Description : TODO
     * @param safetyMngVo
     * @return
     * @throws Exception
     * @return int
      */
     int updateBudget(BudgetVo safetyMngVo) throws Exception;
     
     /**
     * 운영개요 탭 조회. 
     *
     * @Title : detailOper
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return OperVo
      */
     OperVo detailOper(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 교육주제 목록 조회. 
     *
     * @Title : selectOperSubjectList
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return List<OperVo>
      */
     List<OperVo> selectOperSubjectList(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 운영차시 목록 조회 
     *
     * @Title : selectOperSubjectRndList
     * @Description : TODO
     * @param operRndVo
     * @return
     * @throws Exception
     * @return List<OperRndVo>
      */
     List<OperRndVo> selectOperSubjectRndList(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 수정. 
     *
     * @Title : updateOper
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return int
      */
    int updateOper(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 교육주제 목록 삭제. 
     *
     * @Title : deleteOperSubject
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return int
      */
    int deleteOperSubject(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 교육주제 등록. 
     *
     * @Title : insertOperSubject
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return int
      */
    int insertOperSubject(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 교육차시 목록 삭제. 
     *
     * @Title : deleteOperSubjectRnd
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return int
      */
    int deleteOperSubjectRnd(OperVo operVo) throws Exception;
     
     /**
     * 운영개요 탭 교육차시 등록. 
     *
     * @Title : insertOperSubjectRnd
     * @Description : TODO
     * @param operVo
     * @return
     * @throws Exception
     * @return int
      */
    int insertOperSubjectRnd(OperRndVo operVo) throws Exception;
    
    /**
    * 신청상태 업데이트. 
    *
    * @Title : updateStatus
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateStatus(ReqUserVo reqUserVo) throws Exception;
    
    /**
    * 소요예산 항목 조회. 
    *
    * @Title : selectCodeList
    * @Description : TODO
    * @param code
    * @return
    * @throws Exception
    * @return List<CodeVo>
     */
    List<CodeVo> selectCodeList(CodeVo code) throws Exception;
}
