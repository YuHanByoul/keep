package com.kbrainc.plum.front.clclnReport.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.clclnMng.model.ClclnVo;
import com.kbrainc.plum.front.clclnMng.service.ClclnService;
import com.kbrainc.plum.front.clclnReport.service.ClclnReportService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산보고관리 컨트롤러 클래스 
*
* <pre>
* com.kbrainc.plum.front.clclnReport.controller
* - ClclnReportController.java
* </pre> 
*
* @ClassName : ClclnReportController
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 23.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ClclnReportController {
    
    @Autowired
    private ClclnService clclnService;
    
    @Autowired
    private ClclnReportService clclnReportService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 정산보고관리 리스트화면으로 이동
    *
    * @Title : clclnReportListForm
    * @Description : 정산관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/clclnReport/clclnReportListForm.html")
    public String clclnReportListForm(Model model) throws Exception {

        return "front/clclnReport/clclnReportList";
    }

    /**
    * 정산보고관리 상새화면. 
    *
    * @Title : clclnReportDetailForm
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnReport/clclnReportDetailForm.html")
    public String clclnReportDetailForm(ClclnVo clclnVo, Model model, @UserInfo UserVo user) throws Exception {
        clclnVo.setUser(user);
        List<ClclnVo> result = clclnService.selectClclnList(clclnVo);
        ClclnVo detail = null;
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
            
            if (!StringUtil.nvl(detail.getBnkbFileid()).equals("") && !StringUtil.nvl(detail.getBnkbFileid()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detail.getBnkbFileid());
                model.addAttribute("bankFile", fileService.getFileList(fileVo));
            } else {
                model.addAttribute("bankFile", Collections.emptyList());
            }
            
            if (!StringUtil.nvl(detail.getExcclcAtchFilegrpid()).equals("") && !StringUtil.nvl(detail.getExcclcAtchFilegrpid()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detail.getExcclcAtchFilegrpid());
                model.addAttribute("fileList", fileService.getFileList(fileVo));
            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }
        } else {
            detail = new ClclnVo();
        }
        
        model.addAttribute("clclnVo", clclnVo);
        model.addAttribute("detail", detail);
        model.addAttribute("list", clclnService.selectClclnDsctnList(clclnVo));
        
        return "front/clclnReport/clclnReportDetail";
    }
    
    /**
    * 정산보고서 제출. 
    *
    * @Title : saveClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/front/clclnReport/updateClclnReport.do")
    @ResponseBody
    public Map<String, Object> updateClclnReport(SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        supplementVo.setUser(user);
        
        int retVal = clclnReportService.updateClclnReport(supplementVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "정산보고서가 제출되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "정산보고서 제출에 실패했습니다.");
        }
        
        return resultMap;
    }

    /**
    * 정산보고관리 보완요청 팝업. 
    *
    * @Title : selectSplmntPopup
    * @Description : TODO
    * @param supplementVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnReport/selectSplmntPopup.html")
    public String selectSplmntPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = null;
        if (supplementVo != null) {
            detail = this.clclnReportService.selectSplmntInfo(supplementVo);
            
            if (detail == null)
                detail = new SupplementVo();
        }
        
        model.addAttribute("detail", detail);
        
        return "front/clclnReport/detailSplmnt";
    }
    
    /**
    * 정산보고관리 보완요청 답변 등록. 
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
    @RequestMapping(value = "/front/clclnReport/updateSplmnt.do")
    @ResponseBody
    public Map<String, Object> updateSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        supplementVo.setUser(user);
        retVal = clclnReportService.updateSplmnt(supplementVo);
          
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "보완완료 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "보완완료에 실패했습니다.");
        }
              
        return resultMap;
    }
}
