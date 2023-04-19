package com.kbrainc.plum.mng.dwnldDsctn.controller;

import com.kbrainc.plum.mng.dwnldDsctn.model.DwnldDsctnVo;
import com.kbrainc.plum.mng.dwnldDsctn.service.DwnldDsctnService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 다운로드 사유 등록 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller
 * - DwnldDsctnController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DwnldDsctnController
 * @Description : 다운로드 사유 등록 컨트롤러 클래스
 * @date : 2023. 04. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class DwnldDsctnController {

 @Autowired
 private DwnldDsctnService dwnldDsctnService;

    /**
     * 다운로드 사유 팝업 화면
     *
     * @return string
     * @throws Exception
     * @Title : dwnldDsctn
     * @Description : 다운로드 사유 팝업 화면
     */
    @GetMapping("/mng/dwnldDsctn/dwnldDsctnPopup.html")
    public String dwnldDsctn(DwnldDsctnVo dwnldDsctnVo, Model model) throws Exception {
        model.addAttribute("dwnldDsctnVo", dwnldDsctnVo);
        return "mng/dwnldDsctn/dwnldDsctnPopup.html";
    }

    /**
     * 다운로드 사유 등록
     *
     * @param dwnldDsctnVo
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertDwnldDsctn
     * @Description : 다운로드 사유 등록
     */
    @PostMapping("/mng/dwnldDsctn/insertDwnldDsctn.do")
    @ResponseBody
    public Map<String,Object> insertDwnldDsctn(@Valid DwnldDsctnVo dwnldDsctnVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String,Object> result = new HashMap<>();
        dwnldDsctnVo.setUser(user);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        int retVal = dwnldDsctnService.insertDwnldDsctn(dwnldDsctnVo);

        if (retVal > 0 ) {
            result.put("result", Constant.REST_API_RESULT_SUCCESS);
            result.put("msg", "등록 되었습니다.");
            result.put("dwnldDsctnVo", dwnldDsctnVo);
        } else {
            result.put("result", Constant.REST_API_RESULT_FAIL);
            result.put("msg", "등록이 실패하였습니다.");
        }

        return result;
    }
}
