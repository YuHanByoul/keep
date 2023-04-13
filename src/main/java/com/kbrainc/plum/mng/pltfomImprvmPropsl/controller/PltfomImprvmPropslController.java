package com.kbrainc.plum.mng.pltfomImprvmPropsl.controller;

import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.service.PltfomImprvmPropslService;
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
 * com.kbrainc.plum.mng.pltfomImprvmPropsl.controller
 * - PltfomImprvmPropslController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslController
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 컨트롤러 클래스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping("/mng/pltfomImprvmPropsl")
public class PltfomImprvmPropslController {
    public static final String VIEW_PATH = "/mng/pltfomImprvmPropsl";

    @Autowired
    private PltfomImprvmPropslService pltfomImprvmPropslService;


    /**
     * 플랫폼개선제안 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : pltfomImprvmPropslList
     * @Description : 플랫폼개선제안 목록 화면
     */
    @GetMapping("/pltfomImprvmPropslList.html")
    public String pltfomImprvmPropslList() throws Exception {
        return VIEW_PATH + "/pltfomImprvmPropslList.html";
    }

    /**
     * 플랫폼개선제안 등록/수정 화면
     *
     * @param pltfomImprvmPropslVo
     * @param model
     * @return string
     * @throws Exception
     * @Title : pltfomImprvmPropslForm
     * @Description : 플랫폼개선제안 등록/수정 화면
     */
    @GetMapping("/pltfomImprvmPropslForm.html")
    public String pltfomImprvmPropslForm(PltfomImprvmPropslVo pltfomImprvmPropslVo, Model model) throws Exception {
        PltfomImprvmPropslVo pltfomImprvmPropsl = pltfomImprvmPropslService.selectPltfomImprvmPropsl(pltfomImprvmPropslVo);
        PltfomImprvmPropslAnsVo pltfomImprvmPropslAns = pltfomImprvmPropslService.selectPltfomImprvmPropslAns(pltfomImprvmPropslVo);

        if (pltfomImprvmPropslAns == null) {
            pltfomImprvmPropslAns = new PltfomImprvmPropslAnsVo();
        }

        model.addAttribute("pltfomImprvmPropsl", pltfomImprvmPropsl);
        model.addAttribute("pltfomImprvmPropslAns", pltfomImprvmPropslAns);

        return VIEW_PATH + "/pltfomImprvmPropslForm.html";
    }

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPropslAnsVo
     * @param bindingResult
     * @param user
     * @return map
     * @throws Exception
     * @Title : insertPltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    @RequestMapping(value = "/insertPltfomImprvmPrpslAns.do")
    @ResponseBody
    public Map<String, Object> insertPltfomImprvmPrpslAns(@Valid PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo
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

        pltfomImprvmPropslAnsVo.setUser(user);

        int retVal = 0;

        retVal = pltfomImprvmPropslService.insertPltfomImprvmPropslAns(pltfomImprvmPropslAnsVo);

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
    public Map<String, Object> updatePltfomImprvmPrpslAns(@Valid PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo, BindingResult bindingResult,
                                                          @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        pltfomImprvmPropslAnsVo.setUser(user);
        int retVal = 0;

        retVal = pltfomImprvmPropslService.updatePltfomImprvmPropslAns(pltfomImprvmPropslAnsVo);

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
    public Map<String, Object> deletePltfomImprvmPrpsl(PltfomImprvmPropslVo pltfomImprvmPropslVo,  @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = pltfomImprvmPropslService.deletePltfomImprvmPrpsl(pltfomImprvmPropslVo);

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
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼개선제안 목록 조회
     */
    @GetMapping("/selectPltfomImprvmPropslList.do")
    @ResponseBody
    public Map<String, Object> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        Map<String, Object> response = new HashMap<>();
        List<PltfomImprvmPropslVo> list = pltfomImprvmPropslService.selectPltfomImprvmPropslList(pltfomImprvmPropslVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }
}
