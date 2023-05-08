package com.kbrainc.plum.front.cntst.controller;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntst.model.CntstAplyVo;
import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.front.cntst.service.CntstService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 공모전 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.cntstRcptHist.controller
 * - CntstController.java
 * </pre>
 *
 * @author : JD
 * @ClassName : CntstController
 * @Description : 공모전 컨트롤러
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller("front.cntstController")
@Alias("front.cntstController")
public class CntstController {

    @Resource(name = "front.cntstServiceImpl")
    private CntstService cntstService;
    
    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping(value = "/front/cntst/cntstListForm.html")
    public String cntstListForm(Model model) throws Exception {
        return "front/cntst/cntstList";
    }
    
    
    /**
    * 공모전 상세 페이지
    *
    * @Title : cntstDetailForm
    * @Description : 공모전 상세 페이지
    * @param model
    * @param cntstVo
    * @param userVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/cntst/cntstDetailForm.html")
    public String cntstDetailForm(Model model, CntstVo cntstVo, @UserInfo UserVo userVo) throws Exception {
        cntstService.updateCntstHits(cntstVo);
        
        CntstVo result = null;
        cntstVo.setUser(userVo);
        result =  cntstService.selectCntstInfo(cntstVo);
        model.addAttribute("cntst", result);
        
        List<CntstVo> file = cntstService.selectCntstFileList(cntstVo);
        if(file.get(0) != null) {
            model.addAttribute("file", file);
        }else {
            model.addAttribute("file", "Empty");
        }
        
        return "front/cntst/cntstDetail";
    }
    
    /**
    * 공모전 신청 페이지
    *
    * @Title : cntstAplyForm
    * @Description : 공모전 신청 페이지
    * @param model
    * @param cntstVo
    * @param userVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/cntst/cntstAplyForm.html")
    public String cntstAplyForm(Model model, CntstVo cntstVo, @UserInfo UserVo userVo) throws Exception {
        CntstVo result = null;
        cntstVo.setUser(userVo);
        result =  cntstService.selectCntstInfo(cntstVo);
        model.addAttribute("cntst", result);
        
        CntstAplyVo userAplyInfo = cntstService.selectUserAplyInfo(userVo);
        userAplyInfo.setCntstid(cntstVo.getCntstid());
        model.addAttribute("userAplyInfo", userAplyInfo);
        
        List<CntstVo> cntstFldMapngInfo = null;
        cntstFldMapngInfo =  cntstService.selectCntstFldMapngInfo(cntstVo);
        model.addAttribute("fldMapngInfo", cntstFldMapngInfo);
        
        Map<String, Object> cntstFile = fileService.getConfigurationByFilegrpName("cntst_aply_prdct");
        String uploadFileExtsn = ((HashMap<String, String>) cntstFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));
        model.addAttribute("fileConfiguration", cntstFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        
        return "front/cntst/cntstAplyForm";
    }
    
    /**
    * 공모전 신청 페이지(환경일기장신청)
    *
    * @Title : diaryCntstAplyForm
    * @Description : 공모전 신청 페이지(환경일기장신청)
    * @param model
    * @param cntstVo
    * @param userVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/cntst/cntstAplySchlForm.html")
    public String diaryCntstAplyForm(Model model, CntstVo cntstVo, @UserInfo UserVo userVo) throws Exception {
        CntstVo result = null;
        cntstVo.setUser(userVo);
        result =  cntstService.selectCntstInfo(cntstVo);
        model.addAttribute("cntst", result);
        
        CntstAplyVo userAplyInfo = cntstService.selectUserAplyInfo(userVo);
        userAplyInfo.setCntstid(cntstVo.getCntstid());
        model.addAttribute("userAplyInfo", userAplyInfo);
        
        List<CntstVo> cntstFldMapngInfo = null;
        cntstFldMapngInfo =  cntstService.selectCntstFldMapngInfo(cntstVo);
        model.addAttribute("fldMapngInfo", cntstFldMapngInfo);
        
        Map<String, Object> cntstFile = fileService.getConfigurationByFilegrpName("cntst_aply_shcl");
        String uploadFileExtsn = ((HashMap<String, String>) cntstFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));
        model.addAttribute("fileConfiguration", cntstFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        
        return "front/cntst/cntstAplySchlForm";
    }

    /**
    * 공모전 목록 조회 기능
    *
    * @Title : selectCntstList
    * @Description : 공모전 목록 조회 기능
    * @param cntstVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/cntst/selectCntstList.do")
    @ResponseBody
    public Map<String, Object> selectCntstList(CntstVo cntstVo) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstVo> result = null;
        
        result =  cntstService.selectCntstList(cntstVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 공모전 신청 기능
    *
    * @Title : insertCntstAply
    * @Description : 공모전 신청 기능
    * @param cntstVo
    * @param cntstAplyVo
    * @param bindingResult
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/cntst/insertCntstAply.do")
    @ResponseBody
    public Map<String, Object> insertCntstAply(CntstVo cntstVo, @Valid CntstAplyVo cntstAplyVo, BindingResult bindingResult, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        if(cntstVo.getDpcnPsbltyYn().equals("N") && cntstVo.getDpcnUserid() != "") {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "중복참여가 불가능한 공모전입니다.");
            return resultMap;
        }else {
            if(cntstAplyVo.getAplyno().equals("")) {
                String yyMMdd = DateTimeFormatter.ofPattern("yyMMdd").format(LocalDate.now());
                cntstAplyVo.setAplyno("CT" + yyMMdd + cntstAplyVo.getCntstid() + "0001");
            }else{
                String serial = cntstAplyVo.getAplyno();
                int serialFomat = Integer.parseInt(serial);
                serialFomat++;
                
                String yyMMdd = DateTimeFormatter.ofPattern("yyMMdd").format(LocalDate.now());
                cntstAplyVo.setAplyno("CT" + yyMMdd + cntstAplyVo.getCntstid() + String.format("%04d", serialFomat));
            }
            
            cntstAplyVo.setUser(userVo);
            cntstAplyVo.setRprsvMoblphon(cntstAplyVo.getRprsvMoblphon().replaceAll("[^0-9]", ""));
            
            retVal = cntstService.insertCntstAply(cntstAplyVo); 
        }
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "공모전 접수가 완료되었습니다");
            resultMap.put("aplyid", cntstAplyVo.getAplyid());
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공모전 접수에 실패했습니다.");
        }
         return resultMap;
    }
    
    /**
    * 공모전 신청 기능(환경일기장신청)
    *
    * @Title : insertCntstAplySchl
    * @Description : 공모전 신청 기능(환경일기장신청)
    * @param cntstAplySchlVo
    * @param cntstVo
    * @param userVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/cntst/insertCntstAplySchl.do")
    @ResponseBody
    public Map<String, Object> insertCntstAplySchl(@RequestBody List<CntstAplySchlVo> cntstAplySchlVo, CntstVo cntstVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        for(int i=0; i<cntstAplySchlVo.size(); i++) {
            cntstAplySchlVo.get(i).setUser(userVo);
        }
        
        int retVal = 0;
        retVal = cntstService.insertCntstAplySchl(cntstAplySchlVo);
        
      
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "공모전 접수가 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공모전 접수에 실패했습니다.");
        }        
            
        return resultMap;
    }
}
