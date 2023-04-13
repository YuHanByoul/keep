package com.kbrainc.plum.mng.pltfomImprvmPrpsl.controller;

import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslVo;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.service.PltfomImprvmPrpslService;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 알림/문의 > 고객센터 > 플랫폼개선제안 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.pltfomImprvmPrpsl.controller
 * - PltfomImprvmPrpslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPrpslController
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 컨트롤러 클래스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/pltfomImprvmPrpsl")
public class PltfomImprvmPrpslController {
    public static final String VIEW_PATH = "/mng/pltfomImprvmPrpsl";

    @Autowired
    private PltfomImprvmPrpslService pltfomImprvmPrpslService;


    /**
     * 플랫폼개선제안 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : pltfomImprvmPrpslList
     * @Description : 플랫폼개선제안 목록 화면
     */
    @GetMapping("/pltfomImprvmPrpslList.html")
    public String pltfomImprvmPrpslList() throws Exception {
        return VIEW_PATH + "/pltfomImprvmPrpslList.html";
    }

    /**
     * 플랫폼개선제안 등록/수정 화면
     *
     * @param pltfomImprvmPrpslVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : pltfomImprvmPrpslForm
     * @Description : 플랫폼개선제안 등록/수정 화면
     */
    @GetMapping("/pltfomImprvmPrpslForm.html")
    public String pltfomImprvmPrpslForm(PltfomImprvmPrpslVo pltfomImprvmPrpslVo, Model model) throws Exception {
        PltfomImprvmPrpslVo pltfomImprvmPrpsl = pltfomImprvmPrpslService.selectPltfomImprvmPrpsl(pltfomImprvmPrpslVo);
        PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAns = pltfomImprvmPrpslService.selectPltfomImprvmPrpslAns(pltfomImprvmPrpslVo);

        if (pltfomImprvmPrpslAns == null) {
            pltfomImprvmPrpslAns = new PltfomImprvmPrpslAnsVo();
        }

        model.addAttribute("pltfomImprvmPrpsl", pltfomImprvmPrpsl);
        model.addAttribute("pltfomImprvmPrpslAns", pltfomImprvmPrpslAns);

        return VIEW_PATH + "/pltfomImprvmPrpslForm.html";
    }

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPrpslAnsVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertPltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    @RequestMapping(value = "/insertPltfomImprvmPrpslAns.do")
    @ResponseBody
    public Map<String, Object> insertPltfomImprvmPrpslAns(@Valid PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAnsVo
            , BindingResult bindingResult
            , @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        pltfomImprvmPrpslAnsVo.setUser(user);

        int retVal = 0;

        retVal = pltfomImprvmPrpslService.insertPltfomImprvmPrpslAns(pltfomImprvmPrpslAnsVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    @RequestMapping(value = "/updatePltfomImprvmPrpslAns.do")
    @ResponseBody
    public Map<String, Object> updatePltfomImprvmPrpslAns(@Valid PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAnsVo, BindingResult bindingResult,
                                                          @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        pltfomImprvmPrpslAnsVo.setUser(user);
        int retVal = 0;

        retVal = pltfomImprvmPrpslService.updatePltfomImprvmPrpslAns(pltfomImprvmPrpslAnsVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }
    @PostMapping(value = "/deletePltfomImprvmPrpsl.do")
    @ResponseBody
    public Map<String, Object> deletePltfomImprvmPrpsl(PltfomImprvmPrpslVo pltfomImprvmPrpslVo, @UserInfo UserVo user) throws Exception {
        pltfomImprvmPrpslVo.setUser(user);
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = pltfomImprvmPrpslService.deletePltfomImprvmPrpsl(pltfomImprvmPrpslVo);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "삭제에 실패했습니다.");
        }

        return reseultMap;
    }


    /**
     * 플랫폼개선제안 목록 조회
     *
     * @return map
     * @throws Exception
     * @Title : selectPltfomImprvmPrpslList
     * @Description : 플랫폼개선제안 목록 조회
     */
    @GetMapping("/selectPltfomImprvmPrpslList.do")
    @ResponseBody
    public Map<String, Object> selectPltfomImprvmPrpslList(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        List<PltfomImprvmPrpslVo> list = pltfomImprvmPrpslService.selectPltfomImprvmPrpslList(pltfomImprvmPrpslVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }
}
