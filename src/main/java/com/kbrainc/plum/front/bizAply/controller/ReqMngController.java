/**
 * 
 */
package com.kbrainc.plum.front.bizAply.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.bizAply.model.BudgetVo;
import com.kbrainc.plum.front.bizAply.model.CapabilityVo;
import com.kbrainc.plum.front.bizAply.model.OperVo;
import com.kbrainc.plum.front.bizAply.model.ProcPlanVo;
import com.kbrainc.plum.front.bizAply.model.ProgramInfoVo;
import com.kbrainc.plum.front.bizAply.model.ReqMngVo;
import com.kbrainc.plum.front.bizAply.model.ReqUserVo;
import com.kbrainc.plum.front.bizAply.model.SafetyMngVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderVo;
import com.kbrainc.plum.front.bizAply.service.ReqMngService;
import com.kbrainc.plum.front.bizAply.model.OperRndVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmEvlVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderAcbgVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderCarrVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderJobVo;
import com.kbrainc.plum.front.bizAply.model.SmrLeaderLicVo;
import com.kbrainc.plum.front.bizAply.model.PrgrmInfoOutlineVo;
import com.kbrainc.plum.front.bizAply.model.CapabilityResultVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 공모신청 controller 클래스. 
*
* <pre>
* com.kbrainc.plum.front.bizAply.controller
* - PublicContestAplyController.java
* </pre> 
*
* @ClassName : PublicContestAplyController
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.reqMngController")
@Alias("front.reqMngController")
public class ReqMngController {

    @Resource(name = "front.reqMngService")
    private ReqMngService reqMngService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 공모신청 화면으로 이동. 
    *
    * @Title : reqMngListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/front/bizAply/reqMngListForm.html")
    public String reqMngListForm() throws Exception {
        return "front/reqMng/reqMngList";
    }
    
    /**
    * 공모신청 리스트 조회. 
    *
    * @Title : selectReqMngList
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/front/bizAply/selectReqMngList.do")
    @ResponseBody
    public Map<String, Object> selectReqMngList(ReqMngVo reqMngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 공모신청 상세 조회. 
     *
     * @Title : reqMngDetailForm
     * @Description : TODO
     * @param ReqMngVo
     * @param model
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/reqMngDetailForm.html")
     public String reqMngDetailForm(ReqMngVo reqMngVo, Model model, @UserInfo UserVo user) throws Exception {
         
         ReqMngVo detail = new ReqMngVo();

         if (reqMngVo != null) {
             if (reqMngVo.getPcntstid() > 0) {
                 // 조회수 증가
                 reqMngService.updateHits(reqMngVo);
                 
                 List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);                         
                 if (CollectionUtils.isNotEmpty(result)) {
                     detail = result.get(0);
                     
                     if (!StringUtil.nvl(detail.getFilegrpid()).equals("") && !StringUtil.nvl(detail.getFilegrpid()).equals(0)) {
                         FileVo fileVo = new FileVo();
                         fileVo.setFilegrpid(detail.getFilegrpid());
                         model.addAttribute("fileList", fileService.getFileList(fileVo));    //파일목록
                     } else {
                         model.addAttribute("fileList", Collections.emptyList());
                     }
                 }
             }
         }
         
         model.addAttribute("detail", detail);
         model.addAttribute("userType", user.getLoginUserType());
         
         return "front/reqMng/reqMngDetail";
     }
     
     /**
     * 신청여부 조회. 
     *
     * @Title : getAplyCnt
     * @Description : TODO
     * @param reqMngVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/getAplyCnt.do")
     @ResponseBody
     public Map<String, Object> getAplyCnt(ReqMngVo reqMngVo, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         reqMngVo.setUser(user);
         
         ReqUserVo reqUserVo = this.reqMngService.getAplyCnt(reqMngVo);
         
         resultMap.put("reqUserVo", reqUserVo);
         
         return resultMap;
     }
     
     /**
     * [약관 동의 화면]. 
     *
     * @Title : termsForm
     * @Description : TODO
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/termsForm.html")
     public String termsForm(ReqMngVo reqMngVo, Model model, @UserInfo UserVo user) throws Exception {
         model.addAttribute("pcntstid", 0);
         model.addAttribute("fldCd", "");
         model.addAttribute("pcntstNm", "");
         if (reqMngVo != null) {
             model.addAttribute("pcntstid", reqMngVo.getPcntstid());
             model.addAttribute("fldCd", reqMngVo.getFldCd());
             model.addAttribute("pcntstNm", reqMngVo.getPcntstNm());
         }
         return "front/reqMng/terms";
     }
     
     /**
     * [기본정보 입력 화면]. 
     *
     * @Title : baseInfoForm
     * @Description : TODO
     * @param reqMngVo
     * @param model
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/baseInfoForm.html")
     public String baseInfoForm(ReqUserVo reqUserVo, Model model, @UserInfo UserVo user) throws Exception {
         String returnPage = "front/reqMng/baseInfo";
         if (reqUserVo != null) {
             if ("173106".equals(reqUserVo.getFldCd())) {
                 returnPage = "front/reqMng/baseInfo2";
             } else if ("173105".equals(reqUserVo.getFldCd())) {
                 returnPage = "front/reqMng/baseInfo3";
             }
             
             ReqUserVo detail = null;
             if ("U".equals(reqUserVo.getMode())) {
                 detail = reqMngService.detailReqUser(reqUserVo);
                 
                 if (!StringUtil.nvl(detail.getFilegrpid1()).equals("") && !StringUtil.nvl(detail.getFilegrpid1()).equals(0)) {
                     FileVo fileVo = new FileVo();
                     fileVo.setFilegrpid(detail.getFilegrpid1());

                     model.addAttribute("fileList1", fileService.getFileList(fileVo));    // 증빙서류1

                 } else {
                     model.addAttribute("fileList1", Collections.emptyList());
                 }
                 if (!StringUtil.nvl(detail.getFilegrpid2()).equals("") && !StringUtil.nvl(detail.getFilegrpid2()).equals(0)) {
                     FileVo fileVo = new FileVo();
                     fileVo.setFilegrpid(detail.getFilegrpid2());
                     
                     model.addAttribute("fileList2", fileService.getFileList(fileVo));    // 증빙서류2
                     
                 } else {
                     model.addAttribute("fileList2", Collections.emptyList());
                 }
                 if (!StringUtil.nvl(detail.getFilegrpid3()).equals("") && !StringUtil.nvl(detail.getFilegrpid3()).equals(0)) {
                     FileVo fileVo = new FileVo();
                     fileVo.setFilegrpid(detail.getFilegrpid3());
                     
                     model.addAttribute("fileList3", fileService.getFileList(fileVo));    // 증빙서류3
                     
                 } else {
                     model.addAttribute("fileList3", Collections.emptyList());
                 }
             } else {
                 reqUserVo.setUser(user);
                 detail = reqMngService.userBaseInfoDetail(reqUserVo);                 
             }
             
             model.addAttribute("mode", reqUserVo.getMode());
             model.addAttribute("pcntstid", reqUserVo.getPcntstid());
             model.addAttribute("pcntstNm", StringUtils.isEmpty(reqUserVo.getPcntstNm()) ? detail.getPcntstNm() : reqUserVo.getPcntstNm());
             model.addAttribute("fldCd", reqUserVo.getFldCd());
             model.addAttribute("detail", detail);
         }
         return returnPage;
     }
     
     /**
     * [기본정보 저장]. 
     *
     * @Title : insertBaseInfo
     * @Description : TODO
     * @param reqMngVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertBaseInfo.do")
     @ResponseBody
     public Map<String, Object> insertBaseInfo(@Valid ReqUserVo reqUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         reqUserVo.setUser(user);
         reqUserVo.setAplySttsCd("192101");
         int retVal = this.reqMngService.insertBaseInfo(reqUserVo);
         
         if (retVal > 0) {
             resultMap.put("aplyid", reqUserVo.getAplyid());
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * [기관역량 입력 화면]. 
     *
     * @Title : bizAbleForm
     * @Description : TODO
     * @param reqUserVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/bizAbleForm.html")
     public String bizAbleForm(CapabilityVo capabilityVo, Model model, @UserInfo UserVo user) throws Exception {
         CapabilityVo detail = null;
         List<CapabilityVo> prgrmList = null; 
         List<CapabilityResultVo> eduList = null; 
         List<CapabilityResultVo> etcList = null; 
         
         if (capabilityVo != null) {
             capabilityVo.setUser(user);

             if (capabilityVo.getAplyid() > 0) {
                 // 기관역량 상세 조회
                 detail = this.reqMngService.detailBizAble(capabilityVo);
                 // 전년도 지원사업 프로그램 주제
                 prgrmList = this.reqMngService.selectInstPrgrmList(capabilityVo);
                 
                 // 환경교육 운영성과(환경교육사업)
                 CapabilityResultVo capabilityResultVo = new CapabilityResultVo();
                 capabilityResultVo.setAplyid(capabilityVo.getAplyid());
                 capabilityResultVo.setSeCd("213101");
                 eduList = this.reqMngService.selectInstOperRsltList(capabilityResultVo);
                 
                 // 환경교육 운영성과(기타공익사업)
                 capabilityResultVo.setSeCd("213102");
                 etcList = this.reqMngService.selectInstOperRsltList(capabilityResultVo);
             }
         }
         
         if (detail == null)
             detail = new CapabilityVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("prgrmList", prgrmList);
         model.addAttribute("eduList", eduList);
         model.addAttribute("etcList", etcList);
         model.addAttribute("pcntstid", capabilityVo.getPcntstid());
         model.addAttribute("aplyid", capabilityVo.getAplyid());
         model.addAttribute("fldCd", capabilityVo.getFldCd());
         
         return "front/reqMng/bizAble";
     }
     
     /**
     * 기관역량 등록. 
     *
     * @Title : insertbizAble
     * @Description : TODO
     * @param capabilityVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertbizAble.do")
     @ResponseBody
     public Map<String, Object> insertbizAble(@Valid CapabilityVo capabilityVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         capabilityVo.setUser(user);
         
         int retVal = this.reqMngService.insertBizAble(capabilityVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 사업수행계획 등록 화면. 
     *
     * @Title : planForm
     * @Description : TODO
     * @param reqUserVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/planForm.html")
     public String planForm(ProcPlanVo procPlanVo, Model model, @UserInfo UserVo user) throws Exception {
         ProcPlanVo detail = null;
         
         if (procPlanVo != null) {
             if (procPlanVo.getAplyid() > 0) {
                 detail = this.reqMngService.detailPlan(procPlanVo);
             }
         }
         
         if (detail == null)
             detail = new ProcPlanVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("pcntstid", procPlanVo.getPcntstid());
         model.addAttribute("aplyid", procPlanVo.getAplyid());
         model.addAttribute("fldCd", procPlanVo.getFldCd());
         
         return "front/reqMng/plan";
     }
     
     /**
     * 사업수행계획 등록. 
     *
     * @Title : insertPlan
     * @Description : TODO
     * @param procPlanVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertPlan.do")
     @ResponseBody
     public Map<String, Object> insertPlan(@Valid ProcPlanVo procPlanVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         procPlanVo.setUser(user);
         
         int retVal = this.reqMngService.insertPlan(procPlanVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 프로그램정보 등록 화면. 
     *
     * @Title : prgrmInfoForm
     * @Description : TODO
     * @param reqUserVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/prgrmInfoForm.html")
     public String prgrmInfoForm(ProgramInfoVo programInfoVo, Model model, @UserInfo UserVo user) throws Exception {
         ProgramInfoVo detail = null;
         List<PrgrmInfoOutlineVo> outlineList = null; 
         
         if (programInfoVo != null) {
             if (programInfoVo.getAplyid() > 0) {
                 detail = this.reqMngService.detailPrgrmInfo(programInfoVo);
                 
                 // 프로그램 개요
                 outlineList = this.reqMngService.selectPrgrmList(programInfoVo);
             }
         }
         
         if (detail == null)
             detail = new ProgramInfoVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("prgrmList", outlineList);
         model.addAttribute("pcntstid", programInfoVo.getPcntstid());
         model.addAttribute("aplyid", programInfoVo.getAplyid());
         model.addAttribute("fldCd", programInfoVo.getFldCd());

         return "front/reqMng/prgrmInfo";
     }
     
     /**
     * 프로그램정보 등록. 
     *
     * @Title : insertPrgrmInfo
     * @Description : TODO
     * @param programInfoVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertPrgrmInfo.do")
     @ResponseBody
     public Map<String, Object> insertPrgrmInfo(@Valid ProgramInfoVo programInfoVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         programInfoVo.setUser(user);
         
         int retVal = this.reqMngService.insertPrgrmInfo(programInfoVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 전문강사확보 등록 화면 
     *
     * @Title : smrLeaderForm
     * @Description : TODO
     * @param reqUserVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/smrLeaderForm.html")
     public String smrLeaderForm(SmrLeaderVo smrLeaderVo, Model model, @UserInfo UserVo user) throws Exception {
         SmrLeaderVo detail = null;
         List<SmrLeaderJobVo> jobList = null; 
         List<SmrLeaderAcbgVo> acbgList = null; 
         List<SmrLeaderLicVo> licList = null; 
         List<SmrLeaderCarrVo> carrList = null; 
         
         if (smrLeaderVo != null) {
             if (smrLeaderVo.getAplyid() > 0) {
                 detail = this.reqMngService.detailSmrLeader(smrLeaderVo);
                 jobList = this.reqMngService.selectLeaderPlanList(smrLeaderVo);
                 acbgList = this.reqMngService.selectLeaderAbilList(smrLeaderVo);
                 licList = this.reqMngService.selectLeaderLicList(smrLeaderVo);
                 carrList = this.reqMngService.selectLeaderCarrList(smrLeaderVo);
             }
         }
         
         if (detail == null)
             detail = new SmrLeaderVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("jobList", jobList);
         model.addAttribute("acbgList", acbgList);
         model.addAttribute("licList", licList);
         model.addAttribute("carrList", carrList);
         model.addAttribute("pcntstid", smrLeaderVo.getPcntstid());
         model.addAttribute("aplyid", smrLeaderVo.getAplyid());
         model.addAttribute("fldCd", smrLeaderVo.getFldCd());

         return "front/reqMng/smrLeader";
     }
     
     /**
     * 전문강사확보 등록. 
     *
     * @Title : insertSmrLeader
     * @Description : TODO
     * @param smrLeaderVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertSmrLeader.do")
     @ResponseBody
     public Map<String, Object> insertSmrLeader(@Valid SmrLeaderVo smrLeaderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         smrLeaderVo.setUser(user);
         
         int retVal = this.reqMngService.insertSmrLeader(smrLeaderVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 안전관리 적정성 등록 화면. 
     *
     * @Title : safeMngForm
     * @Description : TODO
     * @param reqUserVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/safeMngForm.html")
     public String safeMngForm(SafetyMngVo safetyMngVo, Model model, @UserInfo UserVo user) throws Exception {
         SafetyMngVo detail = null;
         PrgrmEvlVo detail2 = null;
         
         if (safetyMngVo != null) {
             if (safetyMngVo.getAplyid() > 0) {
                 detail = this.reqMngService.detailSafetyMng(safetyMngVo);
                 detail2 = this.reqMngService.detailPrgrmEvl(safetyMngVo);
             }
         }
         
         if (detail == null)
             detail = new SafetyMngVo();
         if (detail2 == null)
             detail2 = new PrgrmEvlVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("detail2", detail2);         
         model.addAttribute("pcntstid", safetyMngVo.getPcntstid());
         model.addAttribute("aplyid", safetyMngVo.getAplyid());
         model.addAttribute("fldCd", safetyMngVo.getFldCd());
         
         return "front/reqMng/safeMng";
     }
     
     /**
     * 안전관리 적정성 등록. 
     *
     * @Title : insertSafeMng
     * @Description : TODO
     * @param safetyMngVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertSafeMng.do")
     @ResponseBody
     public Map<String, Object> insertSafeMng(@Valid SafetyMngVo safetyMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         safetyMngVo.setUser(user);
         
         int retVal = this.reqMngService.insertSafeMng(safetyMngVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 소요예산 화면. 
     *
     * @Title : budgetForm
     * @Description : TODO
     * @param budgetVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/budgetForm.html")
     public String budgetForm(BudgetVo budgetVo, Model model, @UserInfo UserVo user) throws Exception {
         if (budgetVo != null) {
             List<BudgetVo> list = this.reqMngService.selectBudgetList(budgetVo);
             
             if (list == null)
                 list = new ArrayList<BudgetVo>();            
             
             int totalAmt = (list.size() > 0 && null != list.get(0).getTotalAmt()) ? list.get(0).getTotalAmt() : 0;
             
             model.addAttribute("totalAmt", totalAmt);
             model.addAttribute("list", list);
             model.addAttribute("pcntstid", budgetVo.getPcntstid());
             model.addAttribute("aplyid", budgetVo.getAplyid());
             model.addAttribute("fldCd", budgetVo.getFldCd());
         }
         return "front/reqMng/budget";
     }
     
     /**
     * 소요예산 등록. 
     *
     * @Title : insertBudget
     * @Description : TODO
     * @param budgetVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertBudget.do")
     @ResponseBody
     public Map<String, Object> insertBudget(@Valid BudgetVo budgetVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         budgetVo.setUser(user);
         
         int retVal = this.reqMngService.insertBudget(budgetVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 운영개요 화면. 
     *
     * @Title : operForm
     * @Description : TODO
     * @param operVo
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/operForm.html")
     public String operForm(OperVo operVo, Model model, @UserInfo UserVo user) throws Exception {
         OperVo detail = null;
         List<OperVo> eduList = null; 
         List<OperRndVo> normalList = null; 
         List<OperRndVo> envList = null; 
                 
         if (operVo != null) {
             if (operVo.getAplyid() > 0) {
                 // 운영개요 상세 조회
                 detail = this.reqMngService.detailOper(operVo);
                 // 교육 주제
                 eduList = this.reqMngService.selectOperSubjectList(operVo);
                 
                 // 일반주제
                 operVo.setSbjctSeCd("219101");
                 normalList = this.reqMngService.selectOperSubjectRndList(operVo);
                 
                 // 환경현안주제
                 operVo.setSbjctSeCd("219102");
                 envList = this.reqMngService.selectOperSubjectRndList(operVo);
             }
         }
         
         if (detail == null)
             detail = new OperVo();
         
         model.addAttribute("detail", detail);
         model.addAttribute("eduList", eduList);
         model.addAttribute("normalList", normalList);
         model.addAttribute("envList", envList);
         model.addAttribute("pcntstid", operVo.getPcntstid());
         model.addAttribute("aplyid", operVo.getAplyid());
         model.addAttribute("fldCd", operVo.getFldCd());

         return "front/reqMng/oper";
     }
     
     /**
     * 운영개요 등록. 
     *
     * @Title : insertOper
     * @Description : TODO
     * @param operVo
     * @param bindingResult
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/front/bizAply/insertOper.do")
     @ResponseBody
     public Map<String, Object> insertOper(@Valid OperVo operVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         operVo.setUser(user);
         
         int retVal = this.reqMngService.updateOper(operVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }
         
         return resultMap;
     }
     
     /**
     * 신청완료 화면. 
     *
     * @Title : goMypage
     * @Description : TODO
     * @param model
     * @param user
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/front/bizAply/aplyComp.html")
     public String aplyComp(Model model, @UserInfo UserVo user) throws Exception {
         return "front/reqMng/aplyComp";
     }
}
