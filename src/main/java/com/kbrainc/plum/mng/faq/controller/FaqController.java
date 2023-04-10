package com.kbrainc.plum.mng.faq.controller;

import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqVo;
import com.kbrainc.plum.mng.faq.service.FaqService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FAQ Controller.
 *
 * <pre>
 * com.kbrainc.plum.mng.faq.controller
 * - FaqController.java
 * </pre>
 *
 * @author : KBRAINC
 * @ClassName : FaqController
 * @Description : FAQ Controller.
 * @date : 2021. 3. 12.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class FaqController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaqService faqService;

    @RequestMapping(value = "/mng/faq/faqForm.html")
    public String getListPage(@UserInfo UserVo user) {
        return "mng/faq/faqForm";
    }

    @RequestMapping(value = "/mng/faq/faqClForm.html")
    public String getClListPage() {
        return "mng/faq/faqClForm";
    }

    /**
     * FAQ 목록을 JSON으로 출력한다.
     *
     * @param faqVoParam FaqVo 파라미터
     * @return JSON 목록
     * @throws Exception : 예외
     * @MethodName : getList
     * @Date : 2019. 5. 10.
     * @author : KBRAINC
     * @Modify :
     * @Description :
     */
    @RequestMapping(value = "/mng/faq/selectFaqList.do")
    @ResponseBody
    public Map<String, Object> selectFaqList(FaqVo faqVoParam, @UserInfo UserVo user) throws Exception {

        faqVoParam.setUser(user);
        List<FaqVo> list = faqService.getList(faqVoParam);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
     * FAQ 상세 내용을 출력한다.
     *
     * @param faqVo :
     * @param model :
     * @return :
     * @throws Exception :
     * @MethodName : getFaq
     * @Date : 2019. 5. 10.
     * @author : KBRAINC
     * @Modify :
     * @Description :
     */
    @RequestMapping(value = "/mng/faq/faqDetailForm.html")
    public String faqDetailForm(FaqVo faqVo, Model model) throws Exception {
        FaqVo faqInfo = faqService.getFaq(faqVo);
        if (faqInfo == null) {
            faqInfo = new FaqVo();
            faqInfo.setSiteid(faqVo.getSiteid());
        }

        model.addAttribute("faqInfo", faqInfo);
        return "mng/faq/faqDetailForm.html";
    }

    @RequestMapping(value = "/mng/faq/insertFaq.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertFaq(@Valid FaqVo faqVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        faqVo.setUser(user);

        int retVal = 0;
        retVal += faqService.insertFaq(faqVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        return resultMap;
    }

    @RequestMapping(value = "/mng/faq/updateFaq.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateFaq(@Valid FaqVo faqVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        faqVo.setUser(user);

        int retVal = 0;
        retVal += faqService.updateFaq(faqVo);

        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        return resultMap;
    }

    @RequestMapping(value = "/mng/faq/selectFaqClList.do")
    public @ResponseBody Map<String, Object> selectClList(FaqClVo faqClVoParam, @UserInfo UserVo user) throws Exception {

        faqClVoParam.setUser(user);
        List<FaqClVo> list = faqService.getClList(faqClVoParam);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping(value = "/mng/faq/insertCl.do", method = RequestMethod.POST)
    @ResponseBody
    public boolean insertFaqCl(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {
        return faqService.addFaqCl(faqClVo);
    }

    @RequestMapping(value = "/mng/faq/updateCl.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqCl(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {
        faqClVo.setUser(user);
        return faqService.updateFaqCl(faqClVo);
    }

    @RequestMapping(value = "/mng/faq/updateFaqOrd.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqOrd(@RequestBody FaqVo faqVo, @UserInfo UserVo user) throws Exception {
        if ("OD".equals(faqVo.getMode())) {
            return faqService.updateFaqOrdDown(faqVo);
        } else {
            return faqService.updateFaqOrdUp(faqVo);
        }
    }

    @RequestMapping(value = "/mng/faq/updateFaqClOrd.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqClOrd(@RequestParam(name = "mode", required = true) String mode,
                                                @UserInfo UserVo user, FaqClVo faqClVo) throws Exception {
        if ("OD".equals(mode)) {
            return faqService.updateFaqClOrdDown(faqClVo);
        } else {
            return faqService.updateFaqClOrdUp(faqClVo);
        }
    }

    @RequestMapping(value = "/mng/faq/selectClList.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectClList(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {
        faqClVo.setUser(user);

        Map<String, Object> response = new HashMap<String, Object>();
        response.put("list", faqService.getAllList(faqClVo));
        return response;

    }

}
