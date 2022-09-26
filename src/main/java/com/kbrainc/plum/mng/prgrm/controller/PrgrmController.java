package com.kbrainc.plum.mng.prgrm.controller;

import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.prgrm.model.PrgrmVo;
import com.kbrainc.plum.mng.prgrm.service.PrgrmService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.lib.tree.TreeItem;
import com.kbrainc.plum.rte.lib.tree.TreeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 
* 프로그램 관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.prgrm.controller
* - PrgrmController.java
* </pre> 
*
* @ClassName : PrgrmController
* @Description : 프로그램 관리 컨트롤러
* @author : KBRAINC
* @date : 2021. 2. 26.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved
*/
@Controller
public class PrgrmController {

    @Resource(name = "mng.prgrm.prgrmService")
    private PrgrmService prgrmService;

    /**
    * @Title : prgrmMgntForm
    * @Description : 프로그램 관리 페이지
    * @throws Exception 예외
    * @return String 이동화면경로
    */
    @RequestMapping(value = "/mng/prgrm/prgrmMgntForm.html")
    public String prgrmMgntForm() throws Exception {
        return "mng/prgrm/prgrmMgntForm";
    }

    /**
    * @Title : selectPrgrmTreeList
    * @Description : 프로그램 트리
    * @return List<TreeItem> 프로그램 트리 정보 목록
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/prgrm/selectPrgrmTreeList.do")
    @ResponseBody
    public List<TreeItem> selectPrgrmTreeList() throws Exception {
        TreeItem rootPrgrm = new TreeItem();
        rootPrgrm.setPkey("");
        rootPrgrm.setKey("0");
        rootPrgrm.setTitle("프로그램");

        List<TreeItem> treeItemList = prgrmService.selectPrgrmTreeList();
        treeItemList.add(0, rootPrgrm);
        treeItemList = TreeUtil.reformatTreeList(treeItemList);
        return treeItemList;
    }

    /**
    * @Title : selectPrgrmView
    * @Description : 프로그램 상세
    * @param prgrm 프로그램VO 클래스
    * @return PrgrmVo 프로그램VO 클래스
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/prgrm/selectPrgrmView.do")
    @ResponseBody
    public PrgrmVo selectPrgrmView(PrgrmVo prgrm) throws Exception {
        PrgrmVo data = prgrmService.selectPrgrmView(prgrm.getPrgrmid());
        return data;
    }

    /**
    * @Title : savePrgrm
    * @Description : 프로그램 저장
    * @param prgrm         프로그램VO 클래스
    * @param bindingResult 유효성검증결과
    * @param user          로그인사용자정보
    * @return Map<String, Object> 프로그램 저장 결과
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/prgrm/savePrgrm.do")
    @ResponseBody
    public Map<String, Object> savePrgrm(@Valid PrgrmVo prgrm, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            map.put("msg", fieldError.getDefaultMessage());
            return map;
        }

        prgrm.setUser(user);
        String msg = "";
        int retVal = 0;
        String mode = null; // I:등록, U:수정

        if (prgrm.getPrgrmid() != null) {
            retVal = prgrmService.updatePrgrm(prgrm);
            msg = "수정";// 수정
            mode = "U";
        } else {
            retVal = prgrmService.insertPrgrm(prgrm);
            msg = "등록";// 등록
            mode = "I";
        }

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            if ("U".equals(mode)) {
                msg = "수정에 성공하였습니다.";
            } else {
                msg = "등록에 성공하였습니다.";
            }
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            if ("U".equals(mode)) {
                msg = "수정에 실패하였습니다.";
            } else {
                msg = "등록에 실패했습니다.";
            }
        }
        map.put("msg", msg);
        return map;
    }

    /**
    * @Title : prgrmTreeReorder
    * @Description : 프로그램tree 순서 변경
    * @param prgrm 프로그램VO 클래스
    * @param user  로그인사용자정보
    * @return Map<String, String> 프로그램tree 순서 변경 결과
    * @throws Exception 예외
    */
    @RequestMapping("/mng/prgrm/prgrmTreeReorder.do")
    @ResponseBody
    public Map<String, String> prgrmTreeReorder(PrgrmVo prgrm, @UserInfo UserVo user) throws Exception {
        prgrm.setUser(user);
        Map<String, String> map = new HashMap<String, String>();
        int retVal = prgrmService.updatePrgrmTreeReorder(prgrm);
        if (retVal > 0) {
            map.put("msg", "저장에 성공하였습니다.");
        } else {
            map.put("msg", "저장에 실패하였습니다");
        }
        return map;
    }

    /**
    * @Title : deletePrgrm
    * @Description : 프로그램 정보 삭제
    * @param prgrm 프로그램VO 클래스
    * @return Map<String, String> 프로그램 정보 삭제 결과
    * @throws Exception 예외
    */
    @RequestMapping("/mng/prgrm/deletePrgrm.do")
    @ResponseBody
    public Map<String, String> deletePrgrm(PrgrmVo prgrm) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        int retVal = prgrmService.deletePrgrm(prgrm);
        if (retVal > 0) {
            map.put("msg", "삭제에 성공하였습니다.");
        } else {
            map.put("msg", "삭제에 실패하였습니다.");
        }
        return map;
    }
}