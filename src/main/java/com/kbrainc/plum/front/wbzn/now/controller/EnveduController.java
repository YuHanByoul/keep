package com.kbrainc.plum.front.wbzn.now.controller;

import java.time.LocalDate;
import java.time.ZoneId;
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

/**
* 웹진 > 환경교육NOW Controller 클래스
*
* <pre>
* com.kbrainc.plum.front.wbzn.now.controller
* - EnveduController.java
* </pre>
*
* @ClassName : EnveduController
* @Description : 웹진 > 환경교육NOW Controller 클래스
* @author : JD
* @date : 2023. 2. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.enveduController")
@Alias("front.enveduController")
public class EnveduController {
    
    @Resource(name = "front.enveduServiceImpl")
    private EnveduService enveduService;
    
    /**
    * 환경교육NOW 목록 페이지
    *
    * @Title : enveduListForm
    * @Description : TODO
    * @param model
    * @param bannerVo
    * @param enveduVo
    * @param prgrmgdVo
    * @return
    * @throws Exception
    * @return String
    */
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
    
    /**
    * 환경교육NOW 상세 페이지
    *
    * @Title : enveduDetailForm
    * @Description : 환경교육NOW 상세 페이지
    * @param model
    * @param enveduVo
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/wbzn/now/enveduDetailForm.html")
    public String enveduDetailForm(Model model, EnveduVo enveduVo) throws Exception {
        EnveduVo result = null;
        result =  enveduService.selectEnveduInfo(enveduVo);
        model.addAttribute("envedu", result);
        
        return "front/wbzn/now/enveduDetail";
    }

    /**
    * 환경교육NOW 목록 조회
    *
    * @Title : selectEnveduList
    * @Description : 환경교육NOW 목록 조회
    * @param enveduVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/wbzn/now/selectEnveduList.do")
    @ResponseBody
    public Map<String, Object> selectEnveduList(EnveduVo enveduVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnveduVo> result = null;
        int nextCnt = 0;
        int prevCnt = 0;
        
        result =  enveduService.selectEnveduList(enveduVo);
        
        if(enveduVo.getCompareDate().equals("")) {
            LocalDate now = LocalDate.now();
            LocalDate.now(ZoneId.of("Asia/Seoul"));
            enveduVo.setCompareDate(now.toString());
        }
        
        nextCnt = enveduService.selectEnveduNextCount(enveduVo);
        prevCnt = enveduService.selectEnveduPrevCount(enveduVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        resultMap.put("nextCnt", nextCnt);
        resultMap.put("prevCnt", prevCnt);

        return resultMap;
    }
    
    /**
    * 환경교육NOW 프로그램 상세 페이지
    *
    * @Title : prgrmgdDetailForm
    * @Description : 환경교육NOW 프로그램 상세 페이지
    * @param model
    * @param prgrmgdVo
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/front/wbzn/now/prgrmgdDetailForm.html")
    public String prgrmgdDetailForm(Model model, PrgrmgdVo prgrmgdVo) throws Exception {
        PrgrmgdVo result = null;
        result =  enveduService.selectPrgrmgdInfo(prgrmgdVo);
        model.addAttribute("prgrmgd", result);
        
        return "front/wbzn/now/enveduDetail";
    }
    
    /**
    * 환경교육NOW 프로그램 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 환경교육NOW 프로그램 목록 조회
    * @param prgrmgdVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/wbzn/now/selectPrgrmgdList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmgdList(PrgrmgdVo prgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PrgrmgdVo> result = null;
        int nextCnt = 0;
        int prevCnt = 0;
        
        result =  enveduService.selectPrgrmgdList(prgrmgdVo);
        
        if(prgrmgdVo.getCompareDate().equals("")) {
            prgrmgdVo.setCompareDate("2023-05-25");
        }
        
        nextCnt = enveduService.selectPrgrmgdNextCount(prgrmgdVo);
        prevCnt = enveduService.selectPrgrmgdPrevCount(prgrmgdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        resultMap.put("nextCnt", nextCnt);
        resultMap.put("prevCnt", prevCnt);

        return resultMap;
    }
    
    @RequestMapping(value = "/front/wbzn/now/selectSearchMonth.do")
    @ResponseBody
    public Map<String, Object> selectSearchMonth(EnveduVo enveduVo, PrgrmgdVo prgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<EnveduVo> enveduMm = null;
        enveduMm =  enveduService.selectEnveduMmList(enveduVo);
        
        List<PrgrmgdVo> prgrmgdMm = null;
        prgrmgdMm =  enveduService.selectPrgrmgdMmList(prgrmgdVo);
        
        
        resultMap.put("enveduMm", enveduMm);
        resultMap.put("prgrmgdMm", prgrmgdMm);

        return resultMap;
    }
}
