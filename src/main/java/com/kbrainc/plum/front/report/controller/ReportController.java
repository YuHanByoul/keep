/**
 * 
 */
package com.kbrainc.plum.front.report.controller;

import java.util.ArrayList;
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
import com.kbrainc.plum.front.delvry.model.DelvryAplyVo;
import com.kbrainc.plum.front.report.model.ReportOperVo;
import com.kbrainc.plum.front.report.model.ReportVo;
import com.kbrainc.plum.front.report.service.ReportService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.front.report.controller
* - ReportController.java
* </pre> 
*
* @ClassName : ReportController
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    
    @Autowired
    private FileService fileService;


    /**
    * 사업보고관리 목록 화면. 
    *
    * @Title : reportListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/report/reportListForm.html")
    public String reportListForm() throws Exception {
        return "front/report/reportList";
    }
    
    /**
    * 사업보고관리 목록 조회. 
    *
    * @Title : selectReportList
    * @Description : TODO
    * @param reportVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/report/selectReportList.do")
    @ResponseBody
    public Map<String, Object> selectReportList(ReportVo reportVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        reportVo.setUser(user);
        List<ReportVo> result = reportService.selectReportList(reportVo);
        
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 사업보고관리 상세화면. 
    *
    * @Title : reportDetailForm
    * @Description : TODO
    * @param reportVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/report/reportDetailForm.html")
    public String reportDetailForm(ReportVo reportVo, Model model, @UserInfo UserVo user) throws Exception {
        ReportVo detail = null;
        List<ReportVo> result = null;
        
        if (reportVo != null) {
            reportVo.setUser(user);
            result = reportService.selectReportList(reportVo);                
        }
        
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
            
            // 파일 정보
            FileVo fileVo = new FileVo();
            if(detail.getAtchFilegrpid() != null && detail.getAtchFilegrpid() != 0) {
                fileVo.setFilegrpid(detail.getAtchFilegrpid());
                ArrayList<FileVo> fileList = fileService.getFileList(fileVo);
                model.addAttribute("fileList", fileList);
            }
        }
        
        ReportOperVo reportOperVo = new ReportOperVo();
        reportOperVo.setReportid(reportVo.getReportid());
        reportOperVo.setSeCd("209101");
        List<ReportOperVo> planList = reportService.selectReportOperList(reportOperVo); //계획
        reportOperVo.setSeCd("209102");
        List<ReportOperVo> list = reportService.selectReportOperList(reportOperVo); //현재
        
        model.addAttribute("detail", detail == null ? new DelvryAplyVo() : detail);
        model.addAttribute("planList", planList);
        model.addAttribute("list", list);
        
        return "front/report/reportDetail";
    }
    
    /**
    * 사업보고관리 저장. 
    *
    * @Title : saveReport
    * @Description : TODO
    * @param reportVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/report/saveReport.do")
    @ResponseBody
    public Map<String, Object> saveReport(@Valid ReportVo reportVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        reportVo.setUser(user);
        retVal = reportService.saveReport(reportVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 사업보고관리 보완요청 팝업. 
    *
    * @Title : selectSplmntPopup
    * @Description : TODO
    * @param supplementVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/report/selectSplmntPopup.html")
    public String selectSplmntPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = null;
        if (supplementVo != null) {
            detail = this.reportService.selectSplmntInfo(supplementVo);
            
            if (detail == null)
                detail = new SupplementVo();
        }
        
        model.addAttribute("detail", detail);
        
        return "front/report/detailSplmnt";
    }
    
    /**
    * 사업보고관리 보완요청 답변 등록. 
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
    @RequestMapping(value = "/front/report/updateSplmnt.do")
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
        retVal = reportService.updateSplmnt(supplementVo);
          
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "보완완료 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "보완완료에 실패했습니다.");
        }
              
        return resultMap;
    }
    
    /**
    * 컨설팅 사유 팝업 화면. 
    *
    * @Title : selectConsultingPopup
    * @Description : TODO
    * @param reportVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/report/selectConsultingPopup.html")
    public String selectConsultingPopup(ReportVo reportVo, Model model) throws Exception {
        ReportVo detail = null;
        
        if (reportVo != null) {
            detail = reportService.selectConsulting(reportVo);                
        }
        
        model.addAttribute("vstDt", detail == null ? "" : detail.getVstDt());
        model.addAttribute("cnsltngTrgtCn", detail == null ? "" : detail.getCnsltngTrgtCn());
        
        return "front/report/detailConsult";
    }
}
