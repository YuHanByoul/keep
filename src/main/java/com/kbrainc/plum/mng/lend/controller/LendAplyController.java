package com.kbrainc.plum.mng.lend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.lend.service.LendService;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 대여 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.controller - LendController.java
 * </pre>
 *
 * @ClassName : LendController
 * @Description :  대여 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class LendAplyController {

    @Autowired
    private LendService lendService;
    
    @Autowired
    private FileService fileService;
    
    /**
     * 대여 신청관리
     *
     * @Title : lendAplyList
     * @Description : 대여 신청관리
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/lendAplyList.html")
    public String lendList(LendAplyVo lendAplyVo,Model model) throws Exception {
        //대여 모집
        LendVo lenVo = new LendVo();
        model.addAttribute("lendLsit", lendService.selectLendRcritList(lenVo));
        //꾸러미
        PackageVo packageVo = new PackageVo();
        model.addAttribute("packageLsit", lendService.selectPackageList(packageVo));
        return "mng/lendAply/lendAplyList";
    }
    
    /**
     * 대여 신청관리 목록 호출 
     *
     * @Title : selectLendAplyList
     * @Description : 대여 신청관리 목록 호출 
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/selectLendAplyList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<LendAplyVo> result = null;
        lendAplyVo.setUser(user);
        
        result = lendService.selectLendAplyList(lendAplyVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 대여 신청관리 상세 화면
     *
     * @Title : lendAplyDetailForm
     * @Description : 대여 신청관리 상세 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/lendAplyDetailForm.html")
    public String lendAplyDetailForm(LendAplyVo lendAplyVo,Model model) throws Exception {
        model.addAttribute("lendAplyInfo", lendService.selectLendAplyInfo(lendAplyVo));
        return "mng/lendAply/lendAplyDetailForm";
    }
    /**
     * 대여 신청 상태 수정
     *
     * @Title : updateLendAplyStts
     * @Description : 대여 신청 상태 수정
     * @param LendVo LendVo 객체
     * @param bindingResult1 LendVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/updateLendAplyStts.do")
    @ResponseBody
    public Map<String, Object> updateLendAplyStts(LendAplyVo lendAplyVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        lendAplyVo.setUser(user);
        
        Map<String,String> checkMap = null;
        String updateMsg = "";
        
        if (lendAplyVo.getSttsCd().equals("231102")) {
            checkMap = lendService.checkRndStockOver(lendAplyVo);
            if(checkMap != null && checkMap.get("isOverRequestCntPerTotalRndstock").equals("Y")) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                //배정수량을 초과하는 신청이 있습니다.
                resultMap.put("msg", "차시당 총 신청수량이 총 배정수량을 초과합니다. 확인해주십시오.");
                return resultMap;
            }
            if(checkMap != null && checkMap.get("isOverInstRequestcntPerRndstock").equals("Y")) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                //배정수량을 초과하는 신청이 있습니다.
                resultMap.put("msg", "신청수량이 재고수량을 초과하는 신청이 있습니다.");
                return resultMap;
            }
            if(checkMap != null && checkMap.get("isOverInstRequestcntPerRndLimit").equals("Y")) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                //배정수량을 초과하는 신청이 있습니다.
                resultMap.put("msg", "신청수량과 이미 승인된 수량이 배정수량을 초과하는 신청이 있습니다.");
                return resultMap;
            }
        }
        updateMsg = (lendAplyVo.getSttsCd().equals("231101"))? "대여신청"
                :(lendAplyVo.getSttsCd().equals("231102"))?"대여승인"
                :(lendAplyVo.getSttsCd().equals("231103"))?"대여불가":"신청취소";
        
        retVal = lendService.updateLendAplyStts(lendAplyVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", lendAplyVo.getAplyids().size()+"건중 "+retVal+"건 "+updateMsg+" 처리하였습니다.");
            
        }else if(lendAplyVo.getAplyids().size() > 0 && retVal == 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", lendAplyVo.getAplyids().size()+"건중 "+retVal+"건 "+updateMsg+" 처리하였습니다.");
        }else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        return resultMap;
    }
    /**
     * 대여 신청 정보업데이트
     * 
     * @Title : updateLendAply
     * @Description : 대여 신청 정보업데이트
     * @param LendAplyVo lendAplyVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/updateLendAply.do")
    @ResponseBody
    public Map<String, Object> updateLendAply(LendAplyVo lendAplyVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        lendAplyVo.setUser(user);
        
        //retVal = lendService.udateLend(LendVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
          
    /**
     * 대여 신청관리 상세 화면
     *
     * @Title : lendAplyDetailForm
     * @Description : 대여 신청관리 상세 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lendAply/rejectLendAplyPopup.html")
    public String rejectLendAplyPopup(LendAplyVo lendAplyVo,Model model) throws Exception {
        model.addAttribute("lendAplyInfo", lendService.selectLendAplyInfo(lendAplyVo));
        return "mng/lendAply/rejectLendAplyPopup";
    }
       
    
}