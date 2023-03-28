package com.kbrainc.plum.mng.lend.controller;

import com.kbrainc.plum.mng.lend.model.LendAplyVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.lend.service.LendService;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 배송 및 출고 관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.controller
 * - LendAplyDlivyController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : LendAplyDlivyController
 * @Description : 배송 및 출고 관리 Controller
 * @date : 2023. 03. 24.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller
public class LendAplyDlivyController {
    @Autowired
    private LendService lendService;

    /**
     * 배송 및 출고 관리 목록 화면
     * Title : lendAplyDlivyList
     * Description : 배송 및 출고 관리 목록 화면
     *
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/lendAplyDlivyList.html")
    public String lendAplyDlivyList(Model model) throws Exception {
        //대여 모집
        LendVo lenVo = new LendVo();
        model.addAttribute("lendLsit", lendService.selectLendRcritList(lenVo));
        //꾸러미
        PackageVo packageVo = new PackageVo();
        model.addAttribute("packageLsit", lendService.selectPackageList(packageVo));
        return "mng/lendAplyDlivy/lendAplyDlivyList";
    }

    /**
     * 배송 및 츨고 관리 목록 조회
     * Title : selectLendAplyDlivyList
     * Description : 배송 및 츨고 관리 목록 조회
     *
     * @param lendAplyVo
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/selectLendAplyDlivyList.do")
    @ResponseBody
    public Map<String, Object> selectLendAplyDlivyList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
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
     * 배송 및 출고 관리 상세 화면
     * Title : lendAplyDlivyForm
     * Description : 배송 및 출고 관리 상세 화면
     *
     * @param lendAplyVo
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/lendAplyDlivyDetailForm.html")
    public String lendAplyDlivyForm(LendAplyVo lendAplyVo,Model model) throws Exception {
        LendAplyVo resVo = lendService.selectLendAplyInfo(lendAplyVo); //신청자 정보
        model.addAttribute("lendAplyInfo",resVo);
        model.addAttribute("aplyDlivyList",lendService.selectLendAplyDlvyList(lendAplyVo)); //신청수량에 맞는 꾸러미 개체 목록

        LendVo lendVo = new LendVo();
        lendVo.setRcritid(resVo.getRcritid());
        model.addAttribute("lendInfo",lendService.selectLend(lendVo)); //꾸러미 정보
        return "mng/lendAplyDlivy/lendAplyDlivyDetailForm";
    }

    /**
     * 배송 및 츨고 관리 출고처리
     * Title : insertLendAplyDlivy
     * Description : 배송 및 츨고 관리 출고처리
     *
     * @param lendAplyVo
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/insertLendAplyDlivy.do")
    @ResponseBody
    public Map<String, Object> insertLendAplyDlivy(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        int retVal = 0;

        lendAplyVo.setUser(user);
        retVal = lendService.insertLendAplyDlivy(lendAplyVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "출고처리에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "출고처리에 실패하였습니다.");
        }
        return resultMap;
    }

    /**
     * 배송 및 츨고 관리  출고정보 수정
     * Title : updateLendAplyDlivy
     * Description : 배송 및 츨고 관리  출고정보 수정
     *
     * @param lendAplyVo
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/updateLendAplyDlivy.do")
    @ResponseBody
    public Map<String, Object> updateLendAplyDlivy(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        int retVal = 0;

        lendAplyVo.setUser(user);
        //retVal = lendService.updateLendAplyDlivy(lendAplyVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "출고정보 수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "출고정보 수정에 실패하였습니다.");
        }
        return resultMap;
    }

    /**
     * 배송 및 출고 관리 팝업 화면
     * Title : packageIndvdSearchPopup
     * Description : 배송 및 출고 관리 팝업 화면
     *
     * @param lendAplyVo
     * @param model
     * @return string
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/packageIndvdSearchPopup.html")
    public String packageIndvdSearchPopup(LendAplyVo lendAplyVo,Model model){
        model.addAttribute("params", lendAplyVo);
        return "mng/lendAplyDlivy/packageIndvdSearchPopup";
    }

    /**
     * 배송 및 츨고 관리 꾸러미 개체 조회
     * Title : selectPackageIndvdList
     * Description : 배송 및 츨고 관리 꾸러미 개체 조회
     *
     * @param packageindvdVo
     * @param user
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/lendAplyDlivy/selectPackageIndvdList.do")
    @ResponseBody
    public Map<String, Object> selectPackageIndvdList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<PackageindvdVo> result = null;
        packageindvdVo.setUser(user);

        result = lendService.searchPackageindvdList(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", result.size());
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

}
