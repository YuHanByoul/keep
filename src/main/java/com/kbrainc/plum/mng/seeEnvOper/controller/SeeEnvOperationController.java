/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvOper.controller;

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
import com.kbrainc.plum.mng.seeEnvOper.model.SeeEnvOperationVo;
import com.kbrainc.plum.mng.seeEnvOper.service.SeeEnvOperationService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.seeEnvOper.controller
* - SeeEnvOperationController.java
* </pre> 
*
* @ClassName : SeeEnvOperationController
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class SeeEnvOperationController {

    @Autowired
    private SeeEnvOperationService seeEnvOperationService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CommonService commonService;
    
    /**
    * 운영관리 목록 화면
    *
    * @Title : seeEnvOperationListForm
    * @Description : TODO
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/seeEnvOperation/seeEnvOperationListForm.html")
    public String seeEnvOperationListForm(Model model) throws Exception {
        model.addAttribute("sidoList", commonService.selectAlowedCtprvnList());
        return "mng/seeEnvOperation/seeEnvOperationList";
    }
    
    /**
    * 운영관리 목록 조회 
    *
    * @Title : seeEnvOperationList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/seeEnvOperationList.do")
    @ResponseBody
    public Map<String, Object> seeEnvOperationList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectOperationList(seeEnvOperationVo);
        
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
    * 운영관리 상세 탭 화면 
    *
    * @Title : seeEnvOperationTabForm
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/seeEnvOperationTabForm.html")
    public String seeEnvOperationTabForm(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        model.addAttribute("seeEnvOperationVo", seeEnvOperationVo);
        return "mng/seeEnvOperation/seeEnvOperationTab";
    }
    
    /**
    * 운영관리 지정정보 탭 상세 화면
    *
    * @Title : seeEnvOperationDetailForm
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/seeEnvOperation/seeEnvOperationDetailForm.html")
    public String seeEnvOperationDetailForm(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        
        SeeEnvOperationVo detail = new SeeEnvOperationVo();

        if (seeEnvOperationVo != null) {
            if (seeEnvOperationVo.getAplyid() > 0) {
                List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectOperationList(seeEnvOperationVo);
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
                if (!StringUtil.nvl(detail.getAtchFilegrpid()).equals("") && !StringUtil.nvl(detail.getAtchFilegrpid()).equals(0)) {
                    FileVo fileVo = new FileVo();
                    fileVo.setFilegrpid(detail.getAtchFilegrpid());

                    model.addAttribute("fileList", fileService.getFileList(fileVo));    //파일목록

                } else {
                    model.addAttribute("fileList", Collections.emptyList());
                }
            }
        }
        
        model.addAttribute("detail", detail);
        
        return "mng/seeEnvOperation/seeEnvOperationDetail";
    }
    
    /**
    * 지정내역 조회 
    *
    * @Title : selectDsgnList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/selectDsgnList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectDsgnList(seeEnvOperationVo);

        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 변경신청 탭 화면 
    *
    * @Title : seeEnvOperationChangeForm
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/seeEnvOperationChangeForm.html")
    public String seeEnvOperationChangeForm(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        SeeEnvOperationVo detail = new SeeEnvOperationVo();

        if (seeEnvOperationVo != null) {
            if (seeEnvOperationVo.getAplyid() > 0) {
                List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectOperationList(seeEnvOperationVo);
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            }
        }
        
        model.addAttribute("detail", detail);
        model.addAttribute("seeEnvOperationVo", seeEnvOperationVo);
        
        return "mng/seeEnvOperation/seeEnvOperationChange";
    }
    
    /**
    * 변경신청 목록 조회
    *
    * @Title : selectChangeList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/selectChangeList.do")
    @ResponseBody
    public Map<String, Object> selectChangeList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectChangeList(seeEnvOperationVo);
        
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
    * 운영관리 목록 엑셀다운로드
    *
    * @Title : seeEnvOperationListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param seeEnvOperationVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/seeEnvOperation/seeEnvOperationListExcelDownload.do")
    public void seeEnvOperationListExcelDownload(HttpServletRequest request, HttpServletResponse response, SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        seeEnvOperationService.seeEnvOperationListExcelDownload(seeEnvOperationVo, response, request);
    }
    
    /**
    * 담당자 변경 팝업
    *
    * @Title : userSrchPopup
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/userSrchPopup.html")
    public String userSrchPopup(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        model.addAttribute("instid", seeEnvOperationVo.getInstid());
        model.addAttribute("aplyid", seeEnvOperationVo.getAplyid());

        return "mng/seeEnvOperation/userSrchPopup";
    }
    
    /**
    * 담당자목록 조회
    *
    * @Title : selectUserList
    * @Description : TODO
    * @param seeEnvOperationVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/selectUserList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectUserList(seeEnvOperationVo);
        
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
    * 지정정보 저장 
    *
    * @Title : updateInfo
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/updateInfo.do")
    @ResponseBody
    public Map<String, Object> updateInfo(@Valid SeeEnvOperationVo seeEnvOperationVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        seeEnvOperationVo.setUser(user);
        int retVal = this.seeEnvOperationService.updateInfo(seeEnvOperationVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 지정취소 팝업 
    *
    * @Title : cancelPopup
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/cancelPopup.html")
    public String cancelPopup(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        model.addAttribute("aplyid", seeEnvOperationVo.getAplyid());
        return "mng/seeEnvOperation/cancelPopup";
    }
    
    
    /**
     * 지정정보변경 팝업 
     *
     * @Title : cancelPopup
     * @Description : TODO
     * @param seeEnvOperationVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/insertPopup.html")
    public String insertPopup(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        SeeEnvOperationVo status = this.seeEnvOperationService.selectStts(seeEnvOperationVo);
        String sttsMsg = "";
        if (null != status) {
            sttsMsg = "248104".equals(status.getSttsCd()) ? "지정승인 (승인일 : " + status.getDsgnDe() + ")" : "지정탈락 (탈락일 : " + status.getDsgnCnclDe() + ")";
        }
        
        model.addAttribute("sttsMsg", sttsMsg);
        model.addAttribute("aplyid", seeEnvOperationVo.getAplyid());
        return "mng/seeEnvOperation/insertPopup";
    }
    
    /**
    * 지정정보 변경
    *
    * @Title : saveDsgn
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/saveDsgn.do")
    @ResponseBody
    public Map<String, Object> saveDsgn(@Valid SeeEnvOperationVo seeEnvOperationVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        int retVal = 0;
        String errMsg = "저장에 실패했습니다.";
        seeEnvOperationVo.setUser(user);
        int count = this.seeEnvOperationService.selectDsgnno(seeEnvOperationVo);
        if (count > 0) {
            errMsg = "중복되는 지정번호가 존재합니다.\r\n지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.";
        } else {
            retVal = this.seeEnvOperationService.saveDsgn(seeEnvOperationVo);
        }
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", errMsg);
        }
        
        return resultMap;
    }
    
    /**
    * 변경신청 상세 팝업 
    *
    * @Title : changePopup
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/seeEnvOperation/changePopup.html")
    public String changePopup(SeeEnvOperationVo seeEnvOperationVo, Model model) throws Exception {
        List<SeeEnvOperationVo> result = this.seeEnvOperationService.selectChangeList(seeEnvOperationVo);
        SeeEnvOperationVo detail = null;
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
            
            if (!StringUtil.nvl(detail.getAtchFilegrpid()).equals("") && !StringUtil.nvl(detail.getAtchFilegrpid()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detail.getAtchFilegrpid());

                model.addAttribute("fileList", fileService.getFileList(fileVo));    //파일목록
            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }
        } else {
            detail = new SeeEnvOperationVo();
        }
        
        model.addAttribute("detail", detail);
        
        return "mng/seeEnvOperation/changePopup";
    }
    
    /**
    * 변경신청 승인/불가 처리 
    *
    * @Title : updateChange
    * @Description : TODO
    * @param seeEnvOperationVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvOperation/updateChange.do")
    @ResponseBody
    public Map<String, Object> updateChange(@Valid SeeEnvOperationVo seeEnvOperationVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        seeEnvOperationVo.setUser(user);
        int retVal = this.seeEnvOperationService.updateChange(seeEnvOperationVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
}
