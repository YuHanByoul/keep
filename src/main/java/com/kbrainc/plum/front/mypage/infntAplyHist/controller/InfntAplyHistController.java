package com.kbrainc.plum.front.mypage.infntAplyHist.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbrainc.plum.front.mypage.infntAplyHist.model.InfntAplyHistVo;
import com.kbrainc.plum.front.mypage.infntAplyHist.service.InfntAplyHistService;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnAnsVo;
import com.kbrainc.plum.front.srvy.model.SrvySbmsnVo;
import com.kbrainc.plum.front.srvy.model.SrvyVo;
import com.kbrainc.plum.front.srvy.service.SrvyService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 > 국가 환경교육 신청 이력 > 유아환경교육관
 *
 * <pre>
 * com.kbrainc.plum.front.myPage.infntAplyHist
 * - MyInfntAplyHistController.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : MyInfntAplyHistController
 * @Description : 마이페이지 > 국가 환경교육 신청 이력 > 유아환경교육관
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/front/mypage/infntAplyHist")
public class InfntAplyHistController {
    private static final String VIEW_PATH = "/front/mypage/infntAplyHist";

    @Autowired
    private ResCodeService resCodeService;

    @Resource(name = "front.infntAplyHistServiceImpl")
    private InfntAplyHistService infntAplyHistService;

    @Autowired
    private SrvyService srvyService;

    @Value("${system.portal.url}")
    private String portalUrl;

    @GetMapping("/infntAplyHistListForm.html")
    public String infntAplyHistList(InfntAplyHistVo searchVo, Model model) throws Exception {
        model.addAttribute("searchVo", searchVo);
        return VIEW_PATH + "/infntAplyHistList";
    }

    /**
     * 유아 설문 등록 화면
     *
     * @param srvyVo
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : srvySbmsnInsertPopup
     * @Description : 유아/푸름이 설문 등록 화면
     */
    @GetMapping("/srvySbmsnInsertPopup.html")
    public String srvySbmsnInsertPopup(SrvyVo srvyVo, @UserInfo UserVo user, Model model) throws Exception {
        srvyVo.setUser(user);
        SrvyVo srvyInfo = infntAplyHistService.selectSrvyInfo(srvyVo);

        model.addAttribute("qitemList", srvyService.selectQitemList(srvyVo));
        model.addAttribute("srvyInfo", srvyInfo);
        model.addAttribute("infntPrgrmAplyid", srvyVo.getInfntPrgrmAplyid());
        model.addAttribute("mvmnPrgrmAplyid", srvyVo.getMvmnPrgrmAplyid());

        return VIEW_PATH + "/srvySbmsnInsertPopup";
    }

    /**
     * 유아 설문 답변 상세 화면
     *
     * @param srvyVo
     * @param user
     * @param model
     * @return string
     * @throws Exception
     * @Title : srvySbmsnInfoPopup
     * @Description : 유아/푸름이 설문 답변 상세 화면
     */
    @GetMapping("/srvySbmsnInfoPopup.html")
    public String srvySbmsnInfoPopup(SrvyVo srvyVo, @UserInfo UserVo user, Model model) throws Exception {
        srvyVo.setUser(user);
        SrvyVo srvyInfo = infntAplyHistService.selectSrvyInfo(srvyVo);
        srvyInfo.setPrtpntNm(StringUtil.maskingName(srvyInfo.getPrtpntNm()));
        model.addAttribute("srvyInfo", srvyInfo);
        srvyVo.setSbmsnid(srvyInfo.getSbmsnid());
        model.addAttribute("ansList", infntAplyHistService.selectAnsList(srvyVo));

        return VIEW_PATH + "/srvySbmsnInfoPopup";
    }

    /**
     * 설문 제출
     *
     * @param request       HttpServletRequest 객체
     * @param srvySbmsnVo   SrvySbmsnVo 객체
     * @param bindingResult srvySbmsnVo 유효성 검증결과
     * @param user          사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     * @Title : insertTrprSrvy
     * @Description : 대상자설문 등록
     */
    @RequestMapping(value = "/insertSrvySbmsn.do")
    @ResponseBody
    public Map<String, Object> insertSrvySbmsn(HttpServletRequest request, @RequestParam("ansList") String ansList, @Valid SrvySbmsnVo srvySbmsnVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        ObjectMapper mapper = new ObjectMapper();
        List<SrvySbmsnAnsVo> srvySbmsnAnsList = mapper.readValue(ansList, new TypeReference<>() {
        });

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        srvySbmsnVo.setUser(user);
        retVal = srvyService.insertSrvySbmsn(request, srvySbmsnVo, srvySbmsnAnsList);
        if (retVal == 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "설문 제출이 이미 완료되어 제출할 수 없습니다");
        } else if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "설문 제출이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "설문 제출에 실패하였습니다");
        }
        return resultMap;
    }

    @GetMapping(value = "/selectInfntAplyHistList.do")
    @ResponseBody
    public Map<String, Object> selectInfntAplyHistList(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> result = new HashMap<>();
        infntAplyHistVo.setUser(user);
        List<InfntAplyHistVo> list = infntAplyHistService.selectInfntAplyHistList(infntAplyHistVo);
        if (list.size() > 0) {
            result.put("totalCount", list.get(0).getTotalCount());
            result.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            result.put("totalCount", 0);
        }

        result.put("list", list);

        return result;
    }

    @GetMapping("/infntAplyHistDetail.html")
    public String infntAplyHistDetail(InfntAplyHistVo infntAplyHistVo, Model model, @UserInfo UserVo user) throws Exception {
        infntAplyHistVo.setUser(user);
        InfntAplyHistVo infntAplyHistInfoVo = infntAplyHistService.selectInfntAplyHistInfo(infntAplyHistVo);
        InfntAplyHistVo infntAplyHistRegVo = infntAplyHistService.selectInfntAplyHistDetail(infntAplyHistVo);
        String eduTrgtCd = infntAplyHistService.selectEduTrgtCd(infntAplyHistVo);

        model.addAttribute("infntAplyHistInfoVo", infntAplyHistInfoVo);
        model.addAttribute("infntAplyHistRegVo", infntAplyHistRegVo);
        model.addAttribute("eduTrgtCd", eduTrgtCd);

        List<CodeInfoVo> codeInfoList = new ArrayList<>();
        for (String tc : infntAplyHistInfoVo.getTrgtCd().split(",")) {
            codeInfoList.add(resCodeService.getCodeInfo(tc));
        }
        model.addAttribute("codeInfoList", codeInfoList);
        model.addAttribute("infntAplyHistVo", infntAplyHistVo);
        return VIEW_PATH + "/infntAplyHistDetail";
    }

    @PostMapping("/updateSttsInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateSttsInfntAply(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo userVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        boolean success = false;
        infntAplyHistVo.setUser(userVo);

        if (infntAplyHistService.updateSttsInfntAply(infntAplyHistVo) > 0) {
            success = true;
            if ("180104".equals(infntAplyHistVo.getUpdCd())) {
                response.put("msg", "취소가 완료되었습니다.");
            } else if ("180105".equals(infntAplyHistVo.getUpdCd())) {
                response.put("msg", "승인거부가 완료되었습니다.");
            }
        } else if ("180104".equals(infntAplyHistVo.getUpdCd())) {
            response.put("msg", "취소가 실패하였습니다.");
        }
        if ("180105".equals(infntAplyHistVo.getUpdCd())) {
            response.put("msg", "승인거부가 실패하였습니다.");
        }
        response.put("success", success);
        return response;
    }

    /**
     * 유아환경교육관 교육 신청 이력 설문공유.
     *
     * @return
     * @return String
     * @throws Exception
     * @Title : infntAplyHistSrvySendPopup
     * @Description : 유아환경교육관 교육 신청 이력 설문공유
     */
    @RequestMapping(value = "/infntAplyHistSrvySendPopup.html")
    public String infntAplyHistSrvySendPopup(InfntAplyHistVo infntAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (infntAplyHistVo != null) {
            srvyVo.setSrvyid(infntAplyHistVo.getStdntDgstfnSrvyid());
            model.addAttribute("infntPrgrmAplyid", infntAplyHistVo.getAplyid());
            model.addAttribute("srvyDetail", infntAplyHistService.selectInfntSrvyInfo(srvyVo));
            model.addAttribute("de", infntAplyHistVo.getDe());
            model.addAttribute("portalUrl", portalUrl);
        }
        return VIEW_PATH + "/infntAplyHistSrvySendPopup";
    }

    /**
     * 유아환경교육관 교육 신청 이력 공유설문 결과.
     *
     * @return
     * @return String
     * @throws Exception
     * @Title : infntAplyHistSrvyRsltPopup
     * @Description : 유아환경교육관 교육 신청 이력 공유설문 결과
     */
    @RequestMapping(value = "/infntAplyHistSrvyRsltPopup.html")
    public String infntAplyHistSrvyRsltPopup(InfntAplyHistVo infntAplyHistVo, Model model) throws Exception {
        SrvyVo srvyVo = new SrvyVo();
        if (infntAplyHistVo != null) {
            srvyVo.setInfntPrgrmAplyid(infntAplyHistVo.getAplyid());
            srvyVo.setSrvyid(infntAplyHistVo.getStdntDgstfnSrvyid());
        }
        List<SrvyVo> srvySendList = infntAplyHistService.selectInfntSrvySendList(srvyVo);
        srvySendList.stream().forEach(srvy -> srvy.setPrtpntNm(StringUtil.maskingName(srvy.getPrtpntNm())));

        model.addAttribute("infntPrgrmAplyid", infntAplyHistVo.getAplyid());
        model.addAttribute("srvySendList", srvySendList);
        return VIEW_PATH + "/infntAplyHistSrvyRsltPopup";
    }

    @RequestMapping(value = "/updateInfntAply.do")
    @ResponseBody
    public Map<String, Object> updateInfntAply(InfntAplyHistVo infntAplyHistVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        infntAplyHistVo.setUser(user);
        retVal = infntAplyHistService.updateInfntAply(infntAplyHistVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        return resultMap;
    }

}
