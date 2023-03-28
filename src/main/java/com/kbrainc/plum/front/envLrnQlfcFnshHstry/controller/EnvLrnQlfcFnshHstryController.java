package com.kbrainc.plum.front.envLrnQlfcFnshHstry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.envLrnQlfcFnshHstry.model.EnvLrnQlfcFnshHstryVo;
import com.kbrainc.plum.front.envLrnQlfcFnshHstry.service.EnvLrnQlfcFnshHstryService;
import com.kbrainc.plum.front.envWord.model.EnvWordVo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

@Controller("front.envLrnQlfcFnshHstryController")
@Alias("front.envLrnQlfcFnshHstryController")
public class EnvLrnQlfcFnshHstryController {

    @Resource(name = "front.envLrnQlfcFnshHstryServiceImpl")
    private EnvLrnQlfcFnshHstryService envLrnQlfcFnshHstryService;
    
    // 수료정보 자격정보 탭
    @RequestMapping(value="/front/envLrnQlfcFnshHstry/envLrnQlfcFnshHstryListForm.html")
    public String envLrnQlfcFnshHstryListForm() throws Exception {
        return "front/envLrnQlfcFnshHstry/envLrnQlfcFnshHstryList";
    }
    
    // 수료정보 리스트
    @RequestMapping(value="/front/envLrnQlfcFnshHstry/envLrnFnshInfoList.html")
    public String envLrnFnshInfoList() throws Exception {
        return "front/envLrnQlfcFnshHstry/envLrnFnshInfoList";
    }
    
    // 자격정보 리스트
    @RequestMapping(value="/front/envLrnQlfcFnshHstry/envLrnQlfcInfoList.html")
    public String envLrnQlfcInfoList() throws Exception {
        return "front/envLrnQlfcFnshHstry/envLrnQlfcInfoList";
    }
    
    // 수료정보 디테일
    @RequestMapping(value="/front/envLrnQlfcFnshHstry/envLrnFnshInfoDetailForm.html")
    public String envLrnFnshInfoDetailForm(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo, Model model) throws Exception {
        EnvLrnQlfcFnshHstryVo result = null;
        result = envLrnQlfcFnshHstryService.selectFnshInfoDetail(envLrnQlfcFnshHstryVo);
        model.addAttribute("fnshInfo", result);
        
        return "front/envLrnQlfcFnshHstry/envLrnFnshInfoDetail";
    }
    
    // 자격정보 디테일
    @RequestMapping(value="/front/envLrnQlfcFnshHstry/envLrnQlfcInfoDetailForm.html")
    public String envLrnQlfcInfoDetailForm(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo, Model model) throws Exception {
        EnvLrnQlfcFnshHstryVo result = null;
        result = envLrnQlfcFnshHstryService.selectQlfcInfoDetail(envLrnQlfcFnshHstryVo);
        model.addAttribute("qlfcInfo", result);
        
        return "front/envLrnQlfcFnshHstry/envLrnQlfcInfoDetail";
    }
    
    @RequestMapping(value = "/front/envLrnQlfcFnshHstry/selectFnshInfoList.do")
    @ResponseBody
    public Map<String, Object> selectFnshInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvLrnQlfcFnshHstryVo> result = null;
        
        result =  envLrnQlfcFnshHstryService.selectFnshInfoList(envLrnQlfcFnshHstryVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/front/envLrnQlfcFnshHstry/selectQlfcInfoList.do")
    @ResponseBody
    public Map<String, Object> selectQlfcInfoList(EnvLrnQlfcFnshHstryVo envLrnQlfcFnshHstryVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvLrnQlfcFnshHstryVo> result = null;
        
        result =  envLrnQlfcFnshHstryService.selectQlfcInfoList(envLrnQlfcFnshHstryVo);
        
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
