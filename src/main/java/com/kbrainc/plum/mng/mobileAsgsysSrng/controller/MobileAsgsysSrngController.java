package com.kbrainc.plum.mng.mobileAsgsysSrng.controller;

import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;
import com.kbrainc.plum.mng.mobileAsgsysSrng.service.MobileAsgsysSrngService;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 시설관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : MobileAsgsysSrngController
* @Description : 시설 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class MobileAsgsysSrngController {

    @Autowired
    private MobileAsgsysSrngService mobileAsgsysSrngService;

    @Autowired
    private FileService fileService;

    @Autowired
    private InstService instService;

    @Autowired
    private SpceService spceService;

    @Autowired
    private CommonService commonService;

    /**
    * 모바일 우수프로그램 지정제사업 지원단심사 리스트화면
    *
    * @Title : mobileAsgsysSrngList
    * @Description : 모바일 우수프로그램 지정제사업 지원단심사 리스트화면
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngList.html")
    public String asgsysSrngList() throws Exception {
        return "mng/mobileAsgsysSrng/asgsysSrngList";
    }

    /**
    * 모바일 우수프로그램 지정제사업 지원단심사 리스트 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 모바일 우수프로그램 지정제사업 지원단심사 리스트 조회
    * @param mobileAsgsysSrngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/selectAsgsysSrngList.do")
    @ResponseBody
    public Map<String, Object> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo, @UserInfo UserVo user, InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MobileAsgsysSrngVo> result = null;
        mobileAsgsysSrngVo.setUser(user);
        result =  mobileAsgsysSrngService.selectAsgsysSrngList(mobileAsgsysSrngVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * 지원단심사 등록 화면 이동
     *
     * @Title       : asgsysSrngUpdate
     * @Description : 지원단심사 등록 화면 이동
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngUpdate.html")
    public String asgsysSrngUpdate(MobileAsgsysSrngVo mobileAsgsysSrngVo, Model model) throws Exception {


        //신청자와 심사위원의 체크리스 제출 ID 조회
        MobileAsgsysSrngVo mobileAsgsysSrngInfo = mobileAsgsysSrngService.selectAsgsysSrngInfo(mobileAsgsysSrngVo);

        model.addAttribute("mobileAsgsysSrngInfo", mobileAsgsysSrngInfo);
        model.addAttribute("checkList", mobileAsgsysSrngService.selectCheckList(mobileAsgsysSrngInfo));

        return "mng/mobileAsgsysSrng/asgsysSrngUpdate";
    }

    /**
     * 지원단심사 체크리스트 화면 이동
     *
     * @Title       : asgsysSrngCheckList
     * @Description : 지원단심사 체크리스트 화면 이동
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/mobileAsgsysSrng/asgsysSrngCheckList.html")
    public String asgsysSrngCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo, Model model) throws Exception {
        model.addAttribute("checkList", mobileAsgsysSrngService.selectCheckList(mobileAsgsysSrngVo));

        return "mng/mobileAsgsysSrng/asgsysSrngCheckList";
    }
}
