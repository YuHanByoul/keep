/**
 * 
 */
package com.kbrainc.plum.front.bizAply.service;

import java.util.List;

import com.kbrainc.plum.front.bizAply.model.ReqMngVo;
import com.kbrainc.plum.front.bizAply.model.ReqUserVo;
import com.kbrainc.plum.front.bizAply.model.BudgetVo;
import com.kbrainc.plum.front.bizAply.model.CapabilityResultVo;
import com.kbrainc.plum.front.bizAply.model.OperRndVo;
import com.kbrainc.plum.front.bizAply.model.OperVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmEvlVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmInfoOutlineVo;
import com.kbrainc.plum.front.bizAply.model.ProcPlanVo;
import com.kbrainc.plum.front.bizAply.model.ProgramInfoVo;
import com.kbrainc.plum.front.bizAply.model.SafetyMngVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderAcbgVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderCarrVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderJobVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderLicVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderVo;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.front.bizAply.model.CapabilityVo;

/**
* 공모신청 서비스 인터페이스 
*
* <pre>
* com.kbrainc.plum.front.bizAply.service
* - PublicContestAplyService.java
* </pre> 
*
* @ClassName : PublicContestAplyService
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 23.
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
    * 전문강사확보 탭 수정. 
    *
    * @Title : updateSmrLeader
    * @Description : TODO
    * @param smrLeaderVo
    * @return
    * @throws Exception
    * @return int
    */
    int insertSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception;
       
    /**
    * 안전관리 적정성 탭 조회. 
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
    * 안전관리 적정성 탭 수정. 
    *
    * @Title : updateSafetyMng
    * @Description : TODO
    * @param safetyMngVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertSafeMng(SafetyMngVo safetyMngVo) throws Exception;
       
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
    * 소요예산 수정. 
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
