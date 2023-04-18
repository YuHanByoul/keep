/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.bizAply.req.model.BudgetVo;
import com.kbrainc.plum.mng.bizAply.req.model.CapabilityResultVo;
import com.kbrainc.plum.mng.bizAply.req.model.CapabilityVo;
import com.kbrainc.plum.mng.bizAply.req.model.OperRndVo;
import com.kbrainc.plum.mng.bizAply.req.model.OperVo;
import com.kbrainc.plum.mng.bizAply.req.model.PrgrmEvlVo;
import com.kbrainc.plum.mng.bizAply.req.model.PrgrmInfoOutlineVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProcPlanVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProgramInfoVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.model.SafetyMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderAcbgVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderCarrVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderJobVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderLicVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderVo;
import com.kbrainc.plum.mng.bizAply.req.model.SrngTabVo;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.mng.bizAply.req.service.ReqMngService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.controller
* - ReqMngController.java
* </pre> 
*
* @ClassName : ReqMngController
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ReqMngController {

    @Autowired
    private ReqMngService reqMngService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CommonService commonService;
    
    /**
    * 신청관리 화면으로 이동. 
    *
    * @Title : reqMngListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/reqMngListForm.html")
    public String reqMngListForm() throws Exception {
        return "mng/bizAply/req/reqMngList";
    }
    
    /**
    * 신청관리 리스트 조회. 
    *
    * @Title : selectReqMngList
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/selectReqMngList.do")
    @ResponseBody
    public Map<String, Object> selectReqMngList(ReqMngVo reqMngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }

    /**
    * 신청관리 목록 엑셀 다운로드. 
    *
    * @Title : reqMngListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param reqMngVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/bizAply/req/reqMngListExcelDownload.do")
    public void reqMngListExcelDownload(HttpServletRequest request, HttpServletResponse response, ReqMngVo reqMngVo) throws Exception {
        reqMngService.reqMngListExcelDownload(reqMngVo, response, request);
    }
    
    /**
    * 신청관리 상세 조회. 
    *
    * @Title : detailPublicContestForm
    * @Description : TODO
    * @param reqMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailReqMngForm.html")
    public String detailPublicContestForm(ReqMngVo reqMngVo, Model model) throws Exception {
        
        ReqMngVo detailReqMngVo = new ReqMngVo();

        if (reqMngVo != null) {
            if (reqMngVo.getPcntstid() > 0) {
                List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detailReqMngVo = result.get(0);
                }
            }
        }
        
        model.addAttribute("reqMngVo", detailReqMngVo);
        
        return "mng/bizAply/req/detailReqMngForm";
    }
    
    /**
    * 신청자 목록 조회. 
    *
    * @Title : selectReqUserList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/selectReqUserList.do")
    @ResponseBody
    public Map<String, Object> selectReqUserList(ReqUserVo reqUserVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        reqUserVo.setUser(user);
        List<ReqUserVo> result = this.reqMngService.selectReqUserList(reqUserVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 신청자 목록 엑셀 다운로드. 
    *
    * @Title : reqUserListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param reqUserVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/bizAply/req/reqUserListExcelDownload.do")
    public void reqUserListExcelDownload(HttpServletRequest request, HttpServletResponse response, ReqUserVo reqUserVo) throws Exception {
        reqMngService.reqUserListExcelDownload(reqUserVo, response, request);
    }
    
    /**
    * 신청자 상세정보 탭 조회. 
    *
    * @Title : reqUserTabForm
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/bizAply/req/reqUserTabForm.html")
    public String reqUserTabForm(ReqUserVo reqUserVo, Model model) throws Exception {

        model.addAttribute("aplyid", reqUserVo.getAplyid());
        model.addAttribute("pcntstid", reqUserVo.getPcntstid());
        model.addAttribute("fldCd", reqUserVo.getFldCd());

        return "mng/bizAply/req/reqUserTab";
    }
    
    /**
    * 신청정보 탭 조회. 
    *
    * @Title : detailReqUserInfoTabForm
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailReqUserInfoTabForm.html")
    public String detailReqUserInfoTabForm(ReqUserVo reqUserVo, Model model) throws Exception {
        
        ReqUserVo detailReqUserVo = new ReqUserVo();
        String returnPage = "mng/bizAply/req/detailReqUserInfoTabForm";
        if ("173106".equals(reqUserVo.getFldCd())) {
            returnPage = "mng/bizAply/req/detailReqUserInfoTabForm2";
        }
        if ("173105".equals(reqUserVo.getFldCd())) {
            returnPage = "mng/bizAply/req/detailReqUserInfoTabForm3";
        }
        
        if (reqUserVo != null) {
            if (reqUserVo.getAplyid() > 0) {
//                List<ReqUserVo> result = this.reqMngService.selectReqUserList(reqUserVo);    
//                if (CollectionUtils.isNotEmpty(result)) {
//                    detailReqUserVo = result.get(0);
//                }
                detailReqUserVo = this.reqMngService.detailReqUser(reqUserVo);
            }
            
            if (!StringUtil.nvl(detailReqUserVo.getFilegrpid1()).equals("") && !StringUtil.nvl(detailReqUserVo.getFilegrpid1()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detailReqUserVo.getFilegrpid1());

                model.addAttribute("fileList1", fileService.getFileList(fileVo));    // 증빙서류1

            } else {
                model.addAttribute("fileList1", Collections.emptyList());
            }
            if (!StringUtil.nvl(detailReqUserVo.getFilegrpid2()).equals("") && !StringUtil.nvl(detailReqUserVo.getFilegrpid2()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detailReqUserVo.getFilegrpid2());
                
                model.addAttribute("fileList2", fileService.getFileList(fileVo));    // 증빙서류2
                
            } else {
                model.addAttribute("fileList2", Collections.emptyList());
            }
            if (!StringUtil.nvl(detailReqUserVo.getFilegrpid3()).equals("") && !StringUtil.nvl(detailReqUserVo.getFilegrpid3()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detailReqUserVo.getFilegrpid3());
                
                model.addAttribute("fileList3", fileService.getFileList(fileVo));    // 증빙서류3
                
            } else {
                model.addAttribute("fileList3", Collections.emptyList());
            }
            
            model.addAttribute("fldCd", reqUserVo.getFldCd());
        }
        
        model.addAttribute("sidoList", commonService.selectAlowedCtprvnList());
        model.addAttribute("reqUserVo", detailReqUserVo);
        model.addAttribute("popupYn", reqUserVo.getPopupYn());
        
        return returnPage;
    }
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectSuppList
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/selectSuppList.do")
    @ResponseBody
    public Map<String, Object> selectSuppList(SupplementVo supplementVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SupplementVo> result = this.reqMngService.selectSplmntList(supplementVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 신청정보 탭 수정. 
    *
    * @Title : updateReqInfo
    * @Description : TODO
    * @param reqUserVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateReqInfo.do")
    @ResponseBody
    public Map<String, Object> updateReqInfo(@Valid ReqUserVo reqUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        reqUserVo.setUser(user);
        
        int retVal = this.reqMngService.updateReqInfo(reqUserVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }

    /**
    * 담당자 검색 팝업. 
    *
    * @Title : userSrchPopup
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/bizAply/req/userSrchPopup.html")
    public String userSrchPopup(ReqUserVo reqUserVo, Model model) throws Exception {
        model.addAttribute("instid", reqUserVo.getInstid());

        return "mng/bizAply/req/userSrchPopup";
    }

    /**
    * 담당자 조회. 
    *
    * @Title : selectUserList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/bizAply/req/selectUserList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(ReqUserVo reqUserVo) throws Exception {
       Map<String, Object> resultMap = new HashMap<>();
        List<ReqUserVo> result = null;

        result = reqMngService.selectUserList(reqUserVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("userList", result);

        return resultMap;
    }
    
    /**
    * 보완요청 팝업. 
    *
    * @Title : suppPopup
    * @Description : TODO
    * @param supplementVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/bizAply/req/suppPopup.html")
    public String suppPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = new SupplementVo();

        if (supplementVo != null) {
            if (!"201101".equals(supplementVo.getAnsSttsCd())) {
                List<SupplementVo> result = this.reqMngService.selectSplmntList(supplementVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            } else {
                detail.setSplmntid(supplementVo.getSplmntid());
                detail.setAplyid(supplementVo.getAplyid());
                detail.setAnsSttsCd(supplementVo.getAnsSttsCd());
            }
        }
        model.addAttribute("supplementVo", detail);
        model.addAttribute("aplyid", supplementVo.getAplyid());
        
        return "mng/bizAply/req/suppPopup";
    }
    
    /**
    * 보완요청. 
    *
    * @Title : insertSplmnt
    * @Description : TODO
    * @param supplementVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/insertSplmnt.do")
    @ResponseBody
    public Map<String, Object> insertSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        supplementVo.setUser(user);
        
        int retVal = this.reqMngService.insertSplmnt(supplementVo);
        
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
    * 보완요청 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSplmnt.do")
    @ResponseBody
    public Map<String, Object> updateSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        supplementVo.setUser(user);
        
        int retVal = this.reqMngService.updateSplmnt(supplementVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 기관역량 탭 조회. 
    *
    * @Title : detailCapTabForm
    * @Description : TODO
    * @param capabilityVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailCapTabForm.html")
    public String detailCapTabForm(CapabilityVo capabilityVo, Model model) throws Exception {
        
        CapabilityVo detail = null;
        List<CapabilityVo> prgrmList = null; 
        List<CapabilityResultVo> eduList = null; 
        List<CapabilityResultVo> etcList = null; 
                
        if (capabilityVo != null) {
            if (capabilityVo.getAplyid() > 0) {
                // 기관역량 상세 조회
                detail = this.reqMngService.detailInst(capabilityVo);
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
        model.addAttribute("popupYn", capabilityVo.getPopupYn());
        
        return "mng/bizAply/req/detailCapTabForm";
    }
    
    /**
    * 기관역량 수정. 
    *
    * @Title : updateInst
    * @Description : TODO
    * @param capabilityVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateInst.do")
    @ResponseBody
    public Map<String, Object> updateInst(@Valid CapabilityVo capabilityVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        capabilityVo.setUser(user);
        
        int retVal = this.reqMngService.updateInst(capabilityVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 사업수행계획 탭 조회. 
    *
    * @Title : detailPlan
    * @Description : TODO
    * @param procPlanVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailPlanTabForm.html")
    public String detailPlanTabForm(ProcPlanVo procPlanVo, Model model) throws Exception {
        
        ProcPlanVo detail = null;
                
        if (procPlanVo != null) {
            if (procPlanVo.getAplyid() > 0) {
                detail = this.reqMngService.detailPlan(procPlanVo);
            }
        }
        
        if (detail == null)
            detail = new ProcPlanVo();
        
        model.addAttribute("detail", detail);
        model.addAttribute("popupYn", procPlanVo.getPopupYn());
        return "mng/bizAply/req/detailPlanTabForm";
    }
    
    /**
    * 사업수행계획 수정. 
    *
    * @Title : updatePlan
    * @Description : TODO
    * @param procPlanVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updatePlan.do")
    @ResponseBody
    public Map<String, Object> updatePlan(@Valid ProcPlanVo procPlanVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        procPlanVo.setUser(user);
        
        int retVal = this.reqMngService.updatePlan(procPlanVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 프로그램 정보 탭 조회. 
    *
    * @Title : detailPrgrmInfo
    * @Description : TODO
    * @param programInfoVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailPrgrmInfo.html")
    public String detailPrgrmInfo(ProgramInfoVo programInfoVo, Model model) throws Exception {
        
        ProgramInfoVo detail = null;
        List<ProgramInfoVo> prgrmList = null; 
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
        model.addAttribute("popupYn", programInfoVo.getPopupYn());
        return "mng/bizAply/req/detailPrgrmInfoTabForm";
    }
    
    /**
    * 프로그램 정보 탭 수정. 
    *
    * @Title : updatePrgrmInfo
    * @Description : TODO
    * @param programInfoVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updatePrgrmInfo.do")
    @ResponseBody
    public Map<String, Object> updatePrgrmInfo(@Valid ProgramInfoVo programInfoVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        programInfoVo.setUser(user);
        
        int retVal = this.reqMngService.updatePrgrmInfo(programInfoVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 전문강사 확보 탭 조회. 
    *
    * @Title : detailSmrLeader
    * @Description : TODO
    * @param smrLeaderVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailSmrLeader.html")
    public String detailSmrLeader(SmrLeaderVo smrLeaderVo, Model model) throws Exception {
        
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
        model.addAttribute("popupYn", smrLeaderVo.getPopupYn());
        return "mng/bizAply/req/detailSmrLeaderTabForm";
    }
    
    /**
    * 전문강사 확보 탭 수정. 
    *
    * @Title : updateSmrLeader
    * @Description : TODO
    * @param smrLeaderVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSmrLeader.do")
    @ResponseBody
    public Map<String, Object> updateSmrLeader(@Valid SmrLeaderVo smrLeaderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        smrLeaderVo.setUser(user);
        
        int retVal = this.reqMngService.updateSmrLeader(smrLeaderVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 안전관리 적정성 탭 조회 
    *
    * @Title : detailSafetyMng
    * @Description : TODO
    * @param safetyMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailSafetyMng.html")
    public String detailSafetyMng(SafetyMngVo safetyMngVo, Model model) throws Exception {
        
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
        model.addAttribute("popupYn", safetyMngVo.getPopupYn());
        model.addAttribute("aplyid", safetyMngVo.getAplyid());
        return "mng/bizAply/req/detailSafetyMngTabForm";
    }
    
    /**
    * 안전관리 적정성 탭 수정. 
    *
    * @Title : updateSafetyMng
    * @Description : TODO
    * @param safetyMngVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSafetyMng.do")
    @ResponseBody
    public Map<String, Object> updateSafetyMng(@Valid SafetyMngVo safetyMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        safetyMngVo.setUser(user);
        
        int retVal = this.reqMngService.updateSafetyMng(safetyMngVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 심사결과 탭 조회. 
    *
    * @Title : detailSrng
    * @Description : TODO
    * @param safetyMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailSrng.html")
    public String detailSrng(SrngTabVo srngTabVo, Model model) throws Exception {
        
        List<SrngTabVo> list_1 = null;
        List<SrngTabVo> list_2 = null;
        if (srngTabVo != null) {
            if (srngTabVo.getAplyid() > 0) {
                srngTabVo.setCycl(1);
                list_1 = this.reqMngService.selectSrngList(srngTabVo);                
                srngTabVo.setCycl(2);
                list_2 = this.reqMngService.selectSrngList(srngTabVo);                
            }
        }
        
        if (CollectionUtils.isEmpty(list_1))
            list_1 = new ArrayList<SrngTabVo>();
        if (CollectionUtils.isEmpty(list_2))
            list_2 = new ArrayList<SrngTabVo>();
        
        model.addAttribute("aplyid", srngTabVo == null ? "" : srngTabVo.getAplyid());
        model.addAttribute("list_1", list_1);
        model.addAttribute("list_2", list_2);
        
        return "mng/bizAply/req/detailSrng";
    }
    
    /**
    * 심사결과 탭 > 심사점수 상세. 
    *
    * @Title : detailSrngDtl
    * @Description : TODO
    * @param safetyMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailSrngDtl.html")
    public String detailSrngDtl(SrngTabVo srngTabVo, Model model) throws Exception {
        
        List<SrngTabVo> list_1 = null;
        List<SrngTabVo> list_2 = null;
        List<SrngTabVo> list_3 = null;
        if (srngTabVo != null) {
            if (srngTabVo.getAplyid() > 0) {
                list_1 = this.reqMngService.selectSrngList(srngTabVo);
                list_2 = this.reqMngService.detailSrngList(srngTabVo);
                list_3 = this.reqMngService.selectSrngUserList(srngTabVo);
            }
        }
        
        if (CollectionUtils.isEmpty(list_1))
            list_1 = new ArrayList<SrngTabVo>();
        if (CollectionUtils.isEmpty(list_2))
            list_2 = new ArrayList<SrngTabVo>();
        if (CollectionUtils.isEmpty(list_3))
            list_3 = new ArrayList<SrngTabVo>();
        
        model.addAttribute("aplyid", srngTabVo == null ? "" : srngTabVo.getAplyid());
        model.addAttribute("cycl", srngTabVo == null ? "" : srngTabVo.getCycl());
        model.addAttribute("list_1", list_1);
        model.addAttribute("list_2", list_2);
        model.addAttribute("list_3", list_3);
        
        return "mng/bizAply/req/detailSrngDtl";
    }
    
    /**
    * 심사보기 팝업. 
    *
    * @Title : selectSrngResult
    * @Description : TODO
    * @param srngTabVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/selectSrngResultPopup.html")
    public String selectSrngResult(SrngTabVo srngTabVo, Model model) throws Exception {
        model.addAttribute("list", this.reqMngService.selectSrngResult(srngTabVo));
        return "mng/bizAply/req/selectSrngResultPopup";
    }
    
    /**
    * 선정 미선정 업데이트. 
    *
    * @Title : updateSrng
    * @Description : TODO
    * @param safetyMngVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSrng.do")
    @ResponseBody
    public Map<String, Object> updateSrng(@Valid SafetyMngVo safetyMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        safetyMngVo.setUser(user);
        
        int retVal = this.reqMngService.updateSafetyMng(safetyMngVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 소요예산 탭 조회. 
    *
    * @Title : detailBudgetTabForm
    * @Description : TODO
    * @param safetyMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailBudgetTabForm.html")
    public String detailBudgetTabForm(BudgetVo budgetVo, Model model) throws Exception {
        
        List<BudgetVo> list = null;
        
        if (budgetVo != null) {
            if (budgetVo.getAplyid() > 0) {
                list = this.reqMngService.selectBudgetList(budgetVo);
            }
        }
        
        if (list == null)
            list = new ArrayList<BudgetVo>();            
        
        int totalAmt = list.size() > 0 && null != list.get(0).getTotalAmt() ? list.get(0).getTotalAmt() : 0;
        
        model.addAttribute("totalAmt", totalAmt);
        model.addAttribute("fldCd", budgetVo.getFldCd());
        model.addAttribute("aplyid", budgetVo.getAplyid());
        model.addAttribute("list", list);
        model.addAttribute("popupYn", budgetVo.getPopupYn());
        return "mng/bizAply/req/detailBudget";
    }
    
    /**
    * 소요예산 탭 수정. 
    *
    * @Title : updateBudget
    * @Description : TODO
    * @param budgetVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateBudget.do")
    @ResponseBody
    public Map<String, Object> updateBudget(@Valid BudgetVo budgetVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        budgetVo.setUser(user);
        
        int retVal = this.reqMngService.updateBudget(budgetVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 운영개요 탭 조회. 
    *
    * @Title : detailOper
    * @Description : TODO
    * @param operVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/detailOper.html")
    public String detailOper(OperVo operVo, Model model) throws Exception {
         
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
         model.addAttribute("popupYn", operVo.getPopupYn());
         return "mng/bizAply/req/detailOper";
    }
    
    /**
    * 운영개요 탭 수정. 
    *
    * @Title : updateOper
    * @Description : TODO
    * @param operVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateOper.do")
    @ResponseBody
    public Map<String, Object> updateOper(@Valid OperVo operVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 심사일정 캘린더 팝업 화면. 
    *
    * @Title : scheduleCalenderPopup
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/req/scheduleCalenderPopup.html")
    public String scheduleCalenderPopup() throws Exception {
        return "mng/bizAply/req/scheduleCalenderPopup";
    }
    
    /**
    * 심사일정 캘린더 > 심사일정 조회. 
    *
    * @Title : selectScheduleList
    * @Description : TODO
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/selectScheduleList.do")
    @ResponseBody
    public Map<String, Object> selectScheduleList(ReqMngVo reqMngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", this.reqMngService.selectScheduleList(reqMngVo));
        
        return resultMap;
    }
    
    /**
    * [심사정보 수정]. 
    *
    * @Title : updateSrngScore
    * @Description : TODO
    * @param reqUserVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSrngScore.do")
    @ResponseBody
    public Map<String, Object> updateSrngScore(@Valid ReqUserVo reqUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        reqUserVo.setUser(user);
        
        int retVal = this.reqMngService.updateSrngScore(reqUserVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 심사마감 / 마감취소. 
    *
    * @Title : updateSrngEnd
    * @Description : TODO
    * @param reqUserVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/req/updateSrngEnd.do")
    @ResponseBody
    public Map<String, Object> updateSrngEnd(@Valid ReqUserVo reqUserVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        reqUserVo.setUser(user);
        
        int retVal = this.reqMngService.updateSrngEnd(reqUserVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
}
