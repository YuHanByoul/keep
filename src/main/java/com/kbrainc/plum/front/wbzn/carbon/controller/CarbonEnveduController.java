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

/**
* 웹진 > 탄소중립 환경교육 Controller 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.carbon.controller
* - CarbonEnveduController.java
* </pre>
*
* @ClassName : CarbonEnveduController
* @Description : 웹진 > 탄소중립 환경교육 Controller 클래스
* @author : JD
* @date : 2023. 2. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.carbonEnveduService")
@Alias("front.carbonEnveduService")
public class CarbonEnveduController {
    
    @Resource(name = "front.carbonEnveduServiceImpl")
    private CarbonEnveduService carbonEnveduService;
    
    /**
    * 탄소중립 환경교육 목록 페이지
    *
    * @Title : enveduListForm
    * @Description : 탄소중립 환경교육 목록 페이지
    * @param model
    * @param carbonBannerVo
    * @param carbonEnveduVo
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/wbzn/carbon/carbonEnveduList.html")
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
    
    /**
    * 탄소중립 환경교육 상세 페이지
    *
    * @Title : enveduDetailForm
    * @Description : 탄소중립 환경교육 상세 페이지
    * @param model
    * @param carbonEnveduVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/wbzn/carbon/carbonEnveduDetailForm.html")
    public String enveduDetailForm(Model model, CarbonEnveduVo carbonEnveduVo) throws Exception {
        CarbonEnveduVo result = null;
        result =  carbonEnveduService.selectEnveduInfo(carbonEnveduVo);
        model.addAttribute("envedu", result);
        
        return "front/wbzn/carbon/carbonEnveduDetail";
    }

    /**
    * 탄소중립 환경교육 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 탄소중립 환경교육 목록 조회
    * @param carbonEnveduVo
    * @throws Exception
    * @return Map<String,Object>
    */
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
    
    /**
    * 탄소중립 환경교육 프로그램 상세 페이지
    *
    * @Title : prgrmgdDetailForm
    * @Description : 탄소중립 환경교육 프로그램 상세 페이지
    * @param model
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/wbzn/carbon/prgrmgdDetailForm.html")
    public String prgrmgdDetailForm(Model model, CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        CarbonPrgrmgdVo result = null;
        result =  carbonEnveduService.selectPrgrmgdInfo(carbonPrgrmgdVo);
        model.addAttribute("prgrmgd", result);
        
        return "front/wbzn/carbon/carbonEnveduDetail";
    }
    
    /**
    * 탄소중립 환경교육 프로그램 목록
    *
    * @Title : selectPrgrmgdList
    * @Description : 탄소중립 환경교육 프로그램 목록
    * @param carbonPrgrmgdVo
    * @throws Exception
    * @return Map<String,Object>
    */
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
