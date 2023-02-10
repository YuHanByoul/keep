/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.controller;

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
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.mng.bizAply.req.service.ReqMngService;
import com.kbrainc.plum.mng.member.model.MemberVo;
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
    public Map<String, Object> selectReqUserList(ReqUserVo reqUserVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
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

        if (reqUserVo != null) {
            if (reqUserVo.getAplyid() > 0) {
                List<ReqUserVo> result = this.reqMngService.selectReqUserList(reqUserVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detailReqUserVo = result.get(0);
                }
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
        }
        
        model.addAttribute("reqUserVo", detailReqUserVo);
        
        return "mng/bizAply/req/detailReqUserInfoTabForm";
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
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        
        return resultMap;
    }
}
