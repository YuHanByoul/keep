package com.kbrainc.plum.mng.lend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendPackageindvdChckVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.lend.service.LendService;
import com.kbrainc.plum.mng.pack.model.PackageindvdTchaidCmpstnVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.mng.pack.service.PackageService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 대여 위생 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.LendHealthController - LendHealthController.java
 * </pre>
 *
 * @ClassName : LendHealthController
 * @Description :  대여 위생 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 03. 28.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class LendHealthController {

    @Autowired
    private LendService lendService;
    
    @Autowired
    private PackageService packageService;
    
    /**
     * 위생체크 리스트 화면 
     *
     * @Title : lendAplyList
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/health/healthchckList.html")
    public String dlvyList(LendVo lendVo,Model model) throws Exception {
        return "mng/eduResrce/health/lendHealthChckList";
    }
    /**
     * 위생체크용 꾸러미 개체  목록 호출  
     *
     * @Title : selectLendAplyList
     * @Description : 위생체크용 꾸러미 개체  목록 호출 
     * @param PackageindvdVo PackageindvdVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/health/selectHealthchckPackageindvdList.do")
    @ResponseBody
    public Map<String, Object> selectLendAplyDlvyList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageindvdVo> result = null;
        packageindvdVo.setUser(user);
        
        result = lendService.selectPackageindvdListForHealthChck(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", result.size());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 위생체크 상세 화면
     *
     * @Title : healthChckDetail
     * @Description : 위생체크 상세 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/health/healthChckDetail.html")
    public String healthChckDetail(LendAplyVo lendAplyVo,PackageindvdVo PackageindvdVo ,Model model) throws Exception {
        
        model.addAttribute("packageindvdInfo", lendService.selectPackageindvdListForHealthChck(PackageindvdVo).get(0));
        List<PackageindvdTchaidCmpstnVo> tchaidlist = packageService.selectPackageindvdTchaidList(PackageindvdVo);
        model.addAttribute("tchaidlist", tchaidlist);
        
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("formid", 1);
        model.addAttribute("artclExList", lendService.selectFormExidList(paramMap));
        
        return "mng/eduResrce/health/healthChckDetail";
    }
    /**
     * 위생체크 처리   
     * 
     * @Title : healthChck
     * @Description : 위생체크 처리
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/health/healthChck.do")
    @ResponseBody
    public Map<String, Object> healthChck(@RequestBody LendPackageindvdChckVo lendPackageindvdChckVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        lendPackageindvdChckVo.setUser(user);
        resultMap = lendService.healthChckProcess(lendPackageindvdChckVo);
        return resultMap;
    }
    
}