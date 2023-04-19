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
 * 대여 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.LendDlvyController - LendController.java
 * </pre>
 *
 * @ClassName : LendDlvyController
 * @Description :  대여 입고/위생 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 03. 20.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class LendWrhousngController {

    @Autowired
    private LendService lendService;
    
    @Autowired
    private PackageService packageService;
    
    /**
     * 출고 대여 신청 리스트 화면 
     *
     * @Title : lendAplyList
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/wrhousng/wrhousngList.html")
    public String dlvyList(LendVo lendVo,Model model) throws Exception {
        return "mng/eduResrce/wrhousng/lendWrhousngList";
    }
    /**
     * 출고 대여신청 리스트 호출  
     *
     * @Title : selectLendAplyList
     * @Description : 대여 신청관리 목록 호출 
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/wrhousng/selectPackageindvdWrhousngList.do")
    @ResponseBody
    public Map<String, Object> selectLendAplyDlvyList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<LendAplyVo> result = null;
        lendAplyVo.setUser(user);
        
        result = lendService.selectLendPackageindvdList(lendAplyVo);

        if (result.size() > 0) {
            
            resultMap.put("totalCount", result.size());
            
            if(lendAplyVo.getPackageindvdid()==null || lendAplyVo.getPackageindvdid()== 0 ) {
                resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
            } 
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 입고 관리 상세 화면
     *
     * @Title : wrhousngDetail
     * @Description : 입고 관리 상세 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/wrhousng/wrhousngDetail.html")
    public String dlvyDetailForm(LendAplyVo lendAplyVo,PackageindvdVo packageindvdVo ,Model model) throws Exception {
        List<LendAplyVo> result = null;
        result = lendService.selectLendPackageindvdList(lendAplyVo);
        model.addAttribute("packList", result);
        
        List<PackageindvdTchaidCmpstnVo> tchaidlist = packageService.selectPackageindvdTchaidList(packageindvdVo);
        model.addAttribute("tchaidlist", tchaidlist);
        
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("formid", 1);
        model.addAttribute("artclExList", lendService.selectFormExidList(paramMap));
        
        return "mng/eduResrce/wrhousng/wrhousngDetail";
    }
    /**
     * 입고 관리 입고 처리  
     * 
     * @Title : modifyPackageindvdStts
     * @Description : 출고 꾸러미 개체 등록
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/wrhousng/modifyPackageindvdStts.do")
    @ResponseBody
    public Map<String, Object> modifyPackageindvdStts(@RequestBody LendPackageindvdChckVo lendPackageindvdChckVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        lendPackageindvdChckVo.setUser(user);
        resultMap = lendService.wrhounsngChckProcess(lendPackageindvdChckVo);
        return resultMap;
    }
    
}