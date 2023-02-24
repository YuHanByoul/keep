package com.kbrainc.plum.front.wbzn.carbon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.wbzn.carbon.model.CarbonBannerVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonEnveduVo;
import com.kbrainc.plum.front.wbzn.carbon.model.CarbonPrgrmgdVo;
import com.kbrainc.plum.front.wbzn.carbon.service.CarbonEnveduService;

@Controller("front.carbonEnveduService")
@Alias("front.carbonEnveduService")
public class CarbonEnveduController {
    
    @Resource(name = "front.carbonEnveduServiceImpl")
    private CarbonEnveduService carbonEnveduService;
    
    @RequestMapping(value = "/front/wbzn/carbon/enveduListForm.html")
    public String enveduListForm(Model model, CarbonBannerVo carbonBannerVo, CarbonEnveduVo carbonEnveduVo, CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        List<CarbonBannerVo> banner = null;
        banner =  carbonEnveduService.selectBannerList(carbonBannerVo);
        model.addAttribute("banner", banner);
        
        List<CarbonEnveduVo> enveduYr = null;
        List<CarbonEnveduVo> enveduMm = null;
        enveduYr =  carbonEnveduService.selectEnveduYrList(carbonEnveduVo);
        enveduMm =  carbonEnveduService.selectEnveduMmList(carbonEnveduVo);
        model.addAttribute("enveduYr", enveduYr);
        model.addAttribute("enveduMm", enveduMm);
        
        List<CarbonPrgrmgdVo> prgrmgdYr = null;
        List<CarbonPrgrmgdVo> prgrmgdMm = null;
        prgrmgdYr =  carbonEnveduService.selectPrgrmgdYrList(carbonPrgrmgdVo);
        prgrmgdMm =  carbonEnveduService.selectPrgrmgdMmList(carbonPrgrmgdVo);
        model.addAttribute("prgrmgdYr", prgrmgdYr);
        model.addAttribute("prgrmgdMm", prgrmgdMm);
        
        return "front/wbzn/carbon/carbonEnveduList";
    }
    
    @RequestMapping(value = "/front/wbzn/carbon/enveduDetailForm.html")
    public String enveduDetailForm(Model model, CarbonEnveduVo carbonEnveduVo) throws Exception {
        CarbonEnveduVo result = null;
        result =  carbonEnveduService.selectEnveduInfo(carbonEnveduVo);
        model.addAttribute("envedu", result);
        
        return "front/wbzn/carbon/carbonEnveduDetail";
    }

    @RequestMapping(value = "/front/wbzn/carbon/selectEnveduList.do")
    @ResponseBody
    public Map<String, Object> selectEnveduList(CarbonEnveduVo carbonEnveduVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarbonEnveduVo> result = null;
        
        result =  carbonEnveduService.selectEnveduList(carbonEnveduVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/front/wbzn/carbon/prgrmgdDetailForm.html")
    public String prgrmgdDetailForm(Model model, CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        CarbonPrgrmgdVo result = null;
        result =  carbonEnveduService.selectPrgrmgdInfo(carbonPrgrmgdVo);
        model.addAttribute("prgrmgd", result);
        
        return "front/wbzn/carbon/carbonEnveduDetail";
    }
    
    @RequestMapping(value = "/front/wbzn/carbon/selectPrgrmgdList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmgdList(CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarbonPrgrmgdVo> result = null;
        
        result =  carbonEnveduService.selectPrgrmgdList(carbonPrgrmgdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
