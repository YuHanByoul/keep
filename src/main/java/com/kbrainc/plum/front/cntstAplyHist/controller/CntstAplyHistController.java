package com.kbrainc.plum.front.cntstAplyHist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistVo;
import com.kbrainc.plum.front.cntstAplyHist.service.CntstAplyHistService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

@Controller("front.cntstAplyHistController")
@Alias("front.cntstAplyHistController")
public class CntstAplyHistController {

    @Resource(name = "front.cntstAplyHistServiceImpl")
    private CntstAplyHistService cntstAplyHistService;
    
    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping(value = "/front/cntstAplyHist/cntstAplyHistListForm.html")
    public String cntstHistListForm() throws Exception {
        return "front/cntstAplyHist/cntstAplyHistList";
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/cntstAplyHistDetailForm.html")
    public String cntstHistDetailForm(Model model, CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        CntstAplyHistVo result = null;
        cntstAplyHistVo.setUser(userVo);
        result =  cntstAplyHistService.selectCntstAplyHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplyHist", result);
        
        return "front/cntstAplyHist/cntstAplyHistDetail";
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/cntstAplyHistUpdateForm.html")
    public String cntstAplyHistUpdateForm(Model model, CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        CntstAplyHistVo result = null;
        cntstAplyHistVo.setUser(userVo);
        result =  cntstAplyHistService.selectCntstAplyHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplyHist", result);
        
        List<CntstAplyHistVo> cntstFldMapngInfo = null;
        cntstFldMapngInfo =  cntstAplyHistService.selectCntstFldMapngInfo(cntstAplyHistVo);
        model.addAttribute("fldMapngInfo", cntstFldMapngInfo);
        
        Map<String, Object> cntstFile = fileService.getConfigurationByFilegrpName("cntst_aply_prdct");
        String uploadFileExtsn = ((HashMap<String, String>) cntstFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));
        model.addAttribute("fileConfiguration", cntstFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        
        return "front/cntstAplyHist/cntstAplyHistUpdate";
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/updateCntstAplyHist.do")
    @ResponseBody
    public Map<String, Object> updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        cntstAplyHistVo.setUser(userVo);
        
        int retVal = 0;
        retVal = cntstAplyHistService.updateCntstAplyHist(cntstAplyHistVo);
        
      
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "공모전 수정이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공모전 수정에 실패했습니다.");
        }        
            
        return resultMap;
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/cntstAplySchlHistDetailForm.html")
    public String cntstAplySchlHistDetailForm(Model model, CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        CntstAplyHistVo cntstAplyHist = null;
        cntstAplyHistVo.setUser(userVo);
        cntstAplyHist =  cntstAplyHistService.selectCntstAplyHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplyHist", cntstAplyHist);
        
        List<CntstAplyHistVo> cntstAplySchlHist = null;
        cntstAplyHistVo.setUser(userVo);
        cntstAplySchlHist =  cntstAplyHistService.selectCntstAplySchlHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplySchlHist", cntstAplySchlHist);
        
        List<CntstAplyHistVo> cntstFldMapngInfo = null;
        cntstFldMapngInfo =  cntstAplyHistService.selectCntstFldMapngInfo(cntstAplyHistVo);
        model.addAttribute("fldMapngInfo", cntstFldMapngInfo);
        
        return "front/cntstAplyHist/cntstAplySchlHistDetail";
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/cntstAplySchlHistUpdateForm.html")
    public String cntstAplySchlHistUpdateForm(Model model, CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        CntstAplyHistVo cntstAplyHist = null;
        cntstAplyHistVo.setUser(userVo);
        cntstAplyHist =  cntstAplyHistService.selectCntstAplyHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplyHist", cntstAplyHist);
        
        List<CntstAplyHistVo> cntstAplySchlHist = null;
        cntstAplyHistVo.setUser(userVo);
        cntstAplySchlHist =  cntstAplyHistService.selectCntstAplySchlHistInfo(cntstAplyHistVo);
        model.addAttribute("cntstAplySchlHist", cntstAplySchlHist);
        
        List<CntstAplyHistVo> cntstFldMapngInfo = null;
        cntstFldMapngInfo =  cntstAplyHistService.selectCntstFldMapngInfo(cntstAplyHistVo);
        model.addAttribute("fldMapngInfo", cntstFldMapngInfo);
        
        Map<String, Object> cntstFile = fileService.getConfigurationByFilegrpName("cntst_aply_prdct");
        String uploadFileExtsn = ((HashMap<String, String>) cntstFile.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(stringStringEntry -> "." + stringStringEntry.getValue())
                .collect(Collectors.joining(", "));
        model.addAttribute("fileConfiguration", cntstFile);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
        
        return "front/cntstAplyHist/cntstAplySchlHistUpdate";
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/updateCntstAplySchlHist.do")
    @ResponseBody
    public Map<String, Object> updateCntstAplySchlHist(@RequestBody List<CntstAplyHistVo> cntstAplyHistVoList, CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        for(int i=0; i<cntstAplyHistVoList.size(); i++) {
            cntstAplyHistVoList.get(i).setUser(userVo);
        }
        
        int retVal = 0;
        retVal = cntstAplyHistService.updateCntstAplySchlHist(cntstAplyHistVoList);
        
      
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "공모전 수정이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공모전 수정에 실패했습니다.");
        }        
            
        return resultMap;
    }
    
    @RequestMapping(value = "/front/cntstAplyHist/selectCntstAplyHistList.do")
    @ResponseBody
    public Map<String, Object> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo, @UserInfo UserVo userVo) throws Exception{
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstAplyHistVo> result = null;
        
        cntstAplyHistVo.setUser(userVo);
        
        result =  cntstAplyHistService.selectCntstAplyHistList(cntstAplyHistVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
}
