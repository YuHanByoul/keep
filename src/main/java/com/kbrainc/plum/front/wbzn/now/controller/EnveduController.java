package com.kbrainc.plum.front.wbzn.now.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.wbzn.now.model.BannerVo;
import com.kbrainc.plum.front.wbzn.now.model.EnveduVo;
import com.kbrainc.plum.front.wbzn.now.model.PrgrmgdVo;
import com.kbrainc.plum.front.wbzn.now.service.EnveduService;
import com.kbrainc.plum.rte.util.DateTimeUtil;

@Controller("front.enveduController")
@Alias("front.enveduController")
public class EnveduController {
    
    @Resource(name = "front.enveduServiceImpl")
    private EnveduService enveduService;
    
    @RequestMapping(value = "/front/wbzn/now/enveduListForm.html")
    public String enveduListForm(Model model, BannerVo bannerVo, EnveduVo enveduVo, PrgrmgdVo prgrmgdVo) throws Exception {
        List<BannerVo> banner = null;
        banner =  enveduService.selectBannerList(bannerVo);
        model.addAttribute("banner", banner);
        
        List<EnveduVo> enveduYr = null;
        List<EnveduVo> enveduMm = null;
        enveduYr =  enveduService.selectEnveduYrList(enveduVo);
        enveduMm =  enveduService.selectEnveduMmList(enveduVo);
        model.addAttribute("enveduYr", enveduYr);
        model.addAttribute("enveduMm", enveduMm);
        
        List<PrgrmgdVo> prgrmgdYr = null;
        List<PrgrmgdVo> prgrmgdMm = null;
        prgrmgdYr =  enveduService.selectPrgrmgdYrList(prgrmgdVo);
        prgrmgdMm =  enveduService.selectPrgrmgdMmList(prgrmgdVo);
        model.addAttribute("prgrmgdYr", prgrmgdYr);
        model.addAttribute("prgrmgdMm", prgrmgdMm);
        
        return "front/wbzn/now/enveduList";
    }
    
    @RequestMapping(value = "/front/wbzn/now/enveduDetailForm.html")
    public String enveduDetailForm(Model model, EnveduVo enveduVo) throws Exception {
        EnveduVo result = null;
        result =  enveduService.selectEnveduInfo(enveduVo);
        model.addAttribute("envedu", result);
        
        return "front/wbzn/now/enveduDetail";
    }

    @RequestMapping(value = "/front/wbzn/now/selectEnveduList.do")
    @ResponseBody
    public Map<String, Object> selectEnveduList(EnveduVo enveduVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnveduVo> result = null;
        
        result =  enveduService.selectEnveduList(enveduVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/front/wbzn/now/prgrmgdDetailForm.html")
    public String prgrmgdDetailForm(Model model, PrgrmgdVo prgrmgdVo) throws Exception {
        PrgrmgdVo result = null;
        result =  enveduService.selectPrgrmgdInfo(prgrmgdVo);
        model.addAttribute("prgrmgd", result);
        
        return "front/wbzn/now/enveduDetail";
    }
    
    @RequestMapping(value = "/front/wbzn/now/selectPrgrmgdList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PrgrmgdVo> result = null;
        
        result =  enveduService.selectPrgrmgdList(prgrmgdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
