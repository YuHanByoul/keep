package com.kbrainc.plum.mng.prtpn.cntst.controller;

import com.kbrainc.plum.mng.prtpn.cntst.model.CntstVO;
import com.kbrainc.plum.mng.prtpn.cntst.service.CntstService;
import com.kbrainc.plum.mng.prtpn.eduClssRm.model.EduClssRmVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 공모전 등록 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.controller
 * - CntstController.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstController
 * @Description : 공모전 등록 컨트롤러
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller
public class CntstController {

    @Autowired
    CntstService cntstService;

    /**
     * 공모전 등록
     * Title : cntstListForm
     * Description : 공모전 등록
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstListForm.html")
    public String cntstListForm() {
        return "mng/prtpn/cntst/cntstList";
    }

    /**
     * 공모전 목록 조회
     * Title : selectCntstList
     * Description : 공모전 목록 조회
     *
     * @param cntstVO
     * @return map
     */
    @RequestMapping(value = "/mng/prtpn/cntst/selectCntstList.do")
    @ResponseBody
    public Map<String, Object> selectCntstList(CntstVO cntstVO){
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstVO> result = null;
        result =  cntstService.selectCntstList(cntstVO);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }


    /**
     * 공모전 등록 화면
     * Title : cntstInsertForm
     * Description : 공모전 등록 화면
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstInsertForm.html")
    public String cntstInsertForm() {
        return "mng/prtpn/cntst/cntstForm";
    }


    /**
     * 공모전 수정 화면
     * Title : cntstUpdateForm
     * Description : 공모전 수정 화면
     *
     * @param cntstId
     * @param model
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntst/cntstUpdateForm.html")
    public String cntstUpdateForm(Integer cntstId, Model model) {
        CntstVO cntstInfo = cntstService.selectCntstInfo(cntstId);
        List<String> cntstFldCdList = cntstService.selectCntstFldCdList(cntstId);
        model.addAttribute("cntstInfo", cntstInfo);
        model.addAttribute("cntstFldCdList", cntstFldCdList);
        return "mng/prtpn/cntst/cntstForm";
    }

    /**
     * 공모전 등록 기능
     * Title : insertCntst
     * Description : 공모전 등록 기능
     *
     * @return string
     */
    @RequestMapping(value = "/mng/prtpn/cntst/insertCntst.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertCntst(@UserInfo UserVo user, @Valid CntstVO cntstVO, BindingResult bindingResult, String[] cntstFldCdArr){
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            response.put("success", false);
            response.put("msg", fieldError.getDefaultMessage());
            return response;
        }
        cntstVO.setUser(user);
        cntstService.insertCntst(cntstVO, cntstFldCdArr);
        response.put("success", true);
        return response;
    }

    /**
     * 공모전 수정 기능
     * Title : updateCntst
     * Description : 공모전 수정 기능
     *
     * @param user
     * @param cntstVO
     * @param bindingResult
     * @return map
     */
    @RequestMapping(value = "/mng/prtpn/cntst/updateCntst.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateCntst(@UserInfo UserVo user, @Valid CntstVO cntstVO, BindingResult bindingResult, String[] cntstFldCdArr) {
        Map<String, Object> response = new HashMap<>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            response.put("success", false);
            response.put("msg", fieldError.getDefaultMessage());
            return response;
        }
        cntstVO.setUser(user);
        cntstService.updateCntst(cntstVO, cntstFldCdArr);
        response.put("success", true);
        return response;
    }
}
