package com.kbrainc.plum.front.wbzn.carbon.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import com.kbrainc.plum.front.wbzn.now.model.EnveduVo;
import com.kbrainc.plum.front.wbzn.now.model.PrgrmgdVo;

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
        int nextCnt = 0;
        int prevCnt = 0;
        
        result =  carbonEnveduService.selectEnveduList(carbonEnveduVo);
        
        if(carbonEnveduVo.getCompareQuarter().equals("")) {
            LocalDate now = LocalDate.now();
            LocalDate.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M"); 
            String formatedNow = now.format(formatter);
            if(Integer.parseInt(formatedNow) <= 3) {
                carbonEnveduVo.setCompareQuarter("1");                
            }else if(Integer.parseInt(formatedNow) > 3 && Integer.parseInt(formatedNow) <= 6) {
                carbonEnveduVo.setCompareQuarter("2");                
            }else if(Integer.parseInt(formatedNow) > 6 && Integer.parseInt(formatedNow) <= 9) {
                carbonEnveduVo.setCompareQuarter("3");                
            }else {
                carbonEnveduVo.setCompareQuarter("4");                
            }
            carbonEnveduVo.setCompareYear(carbonEnveduVo.getEnveduSearchYr());

        }
        
        String nextCmprQrtr = carbonEnveduVo.getCompareQuarter();
        String nextCmprYr = carbonEnveduVo.getCompareYear();
        String prevCmprQrtr = carbonEnveduVo.getCompareQuarter();
        String prevCmprYr = carbonEnveduVo.getCompareYear();
        
        if(!nextCmprQrtr.equals("5")) {
            if(!nextCmprQrtr.equals("4")) {
                carbonEnveduVo.setCompareQuarter( Integer.toString(Integer.parseInt(nextCmprQrtr) + 1) );
                nextCnt = carbonEnveduService.selectEnveduNextCount(carbonEnveduVo);
                resultMap.put("nextCnt", nextCnt);
            }else if(nextCmprQrtr.equals("4")) {
                carbonEnveduVo.setCompareQuarter( "1" );
                carbonEnveduVo.setCompareYear( Integer.toString(Integer.parseInt(nextCmprYr) + 1) );
                nextCnt = carbonEnveduService.selectEnveduNextCount(carbonEnveduVo);
                resultMap.put("nextCnt", nextCnt);
            }            
        }else {
            resultMap.put("nextCnt", 0);
        }
        
        if(!prevCmprQrtr.equals("5")) {
            if(!prevCmprQrtr.equals("4")) {
                if(prevCmprQrtr.equals("1")) {
                    carbonEnveduVo.setCompareQuarter( "4" );
                    carbonEnveduVo.setCompareYear( Integer.toString(Integer.parseInt(prevCmprYr) - 1) );
                    prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonEnveduVo);
                    resultMap.put("prevCnt", prevCnt);
                }else {
                    carbonEnveduVo.setCompareQuarter( Integer.toString(Integer.parseInt(prevCmprQrtr) - 1) );
                    prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonEnveduVo);
                    resultMap.put("prevCnt", prevCnt);
                }
            }else if(prevCmprQrtr.equals("4")) {
                carbonEnveduVo.setCompareYear( prevCmprYr );
                carbonEnveduVo.setCompareQuarter( Integer.toString(Integer.parseInt(prevCmprQrtr) - 1) );
                prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonEnveduVo);
                resultMap.put("prevCnt", prevCnt);
            }            
        }else {
            resultMap.put("prevCnt", 0);
        }
        
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
        int nextCnt = 0;
        int prevCnt = 0;
        
        result =  carbonEnveduService.selectPrgrmgdList(carbonPrgrmgdVo);
        
        if(carbonPrgrmgdVo.getCompareQuarter().equals("")) {
            LocalDate now = LocalDate.now();
            LocalDate.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M"); 
            String formatedNow = now.format(formatter);
            if(Integer.parseInt(formatedNow) <= 3) {
                carbonPrgrmgdVo.setCompareQuarter("1");                
            }else if(Integer.parseInt(formatedNow) > 3 && Integer.parseInt(formatedNow) <= 6) {
                carbonPrgrmgdVo.setCompareQuarter("2");                
            }else if(Integer.parseInt(formatedNow) > 6 && Integer.parseInt(formatedNow) <= 9) {
                carbonPrgrmgdVo.setCompareQuarter("3");                
            }else {
                carbonPrgrmgdVo.setCompareQuarter("4");                
            }
            carbonPrgrmgdVo.setCompareYear(carbonPrgrmgdVo.getPrgrmgdSearchYr());

        }
        
        String nextCmprQrtr = carbonPrgrmgdVo.getCompareQuarter();
        String nextCmprYr = carbonPrgrmgdVo.getCompareYear();
        String prevCmprQrtr = carbonPrgrmgdVo.getCompareQuarter();
        String prevCmprYr = carbonPrgrmgdVo.getCompareYear();
        
        if(!nextCmprQrtr.equals("5")) {
            if(!nextCmprQrtr.equals("4")) {
                carbonPrgrmgdVo.setCompareQuarter( Integer.toString(Integer.parseInt(nextCmprQrtr) + 1) );
                nextCnt = carbonEnveduService.selectEnveduNextCount(carbonPrgrmgdVo);
                resultMap.put("nextCnt", nextCnt);
            }else if(nextCmprQrtr.equals("4")) {
                carbonPrgrmgdVo.setCompareQuarter( "1" );
                carbonPrgrmgdVo.setCompareYear( Integer.toString(Integer.parseInt(nextCmprYr) + 1) );
                nextCnt = carbonEnveduService.selectEnveduNextCount(carbonPrgrmgdVo);
                resultMap.put("nextCnt", nextCnt);
            }            
        }else {
            resultMap.put("nextCnt", 0);
        }
        
        if(!prevCmprQrtr.equals("5")) {
            if(!prevCmprQrtr.equals("4")) {
                if(prevCmprQrtr.equals("1")) {
                    carbonPrgrmgdVo.setCompareQuarter( "4" );
                    carbonPrgrmgdVo.setCompareYear( Integer.toString(Integer.parseInt(prevCmprYr) - 1) );
                    prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonPrgrmgdVo);
                    resultMap.put("prevCnt", prevCnt);
                }else {
                    carbonPrgrmgdVo.setCompareQuarter( Integer.toString(Integer.parseInt(prevCmprQrtr) - 1) );
                    prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonPrgrmgdVo);
                    resultMap.put("prevCnt", prevCnt);
                }
            }else if(prevCmprQrtr.equals("4")) {
                carbonPrgrmgdVo.setCompareYear( prevCmprYr );
                carbonPrgrmgdVo.setCompareQuarter( Integer.toString(Integer.parseInt(prevCmprQrtr) - 1) );
                prevCnt = carbonEnveduService.selectEnveduPrevCount(carbonPrgrmgdVo);
                resultMap.put("prevCnt", prevCnt);
            }            
        }else {
            resultMap.put("prevCnt", 0);
        }
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/front/wbzn/carbon/selectSearchMonth.do")
    @ResponseBody
    public Map<String, Object> selectSearchMonth(CarbonEnveduVo carbonEnveduVo, CarbonPrgrmgdVo carbonPrgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<CarbonEnveduVo> enveduMm = null;
        enveduMm =  carbonEnveduService.selectEnveduMmList(carbonEnveduVo);
        
        List<CarbonPrgrmgdVo> prgrmgdMm = null;
        prgrmgdMm =  carbonEnveduService.selectPrgrmgdMmList(carbonPrgrmgdVo);
        
        
        resultMap.put("enveduMm", enveduMm);
        resultMap.put("prgrmgdMm", prgrmgdMm);

        return resultMap;
    }
}
