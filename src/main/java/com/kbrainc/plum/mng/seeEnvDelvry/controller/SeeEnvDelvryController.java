/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvDelvry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.seeEnvDelvry.model.SeeEnvDelvryVo;
import com.kbrainc.plum.mng.seeEnvDelvry.service.SeeEnvDelvryService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 사회환경교육기관 지정 > 교부관리 컨트롤러
*
* <pre>
* com.kbrainc.plum.mng.seeEnvDelvry.controller
* - SeeEnvDelvryController.java
* </pre> 
*
* @ClassName : SeeEnvDelvryController
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class SeeEnvDelvryController {

    @Autowired
    private SeeEnvDelvryService seeEnvDelvryService;
    
    @Autowired
    private CommonService commonService;
    
    /**
    * 교부관리 목록 화면 
    *
    * @Title : seeEnvDelvryListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/seeEnvDelvry/seeEnvDelvryListForm.html")
    public String seeEnvDelvryListForm(Model model) throws Exception {
        model.addAttribute("sidoList", commonService.selectAlowedCtprvnList());
        return "mng/seeEnvDelvry/seeEnvDelvryList";
    }
    
    /**
    * 교부관리 목록 조회 
    *
    * @Title : seeEnvDelvryList
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvDelvry/seeEnvDelvryList.do")
    @ResponseBody
    public Map<String, Object> seeEnvDelvryList(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SeeEnvDelvryVo> result = this.seeEnvDelvryService.selectDelvryList(seeEnvDelvryVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 교부관리 상세 조회 
    *
    * @Title : seeEnvDelvryDetailForm
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/seeEnvDelvry/seeEnvDelvryDetailForm.html")
    public String seeEnvDelvryDetailForm(SeeEnvDelvryVo seeEnvDelvryVo, Model model) throws Exception {
        
        SeeEnvDelvryVo detail = new SeeEnvDelvryVo();

        if (seeEnvDelvryVo != null) {
            if (seeEnvDelvryVo.getAplyid() > 0) {
                List<SeeEnvDelvryVo> result = this.seeEnvDelvryService.selectDelvryList(seeEnvDelvryVo);
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            }
        }
        
        model.addAttribute("detail", detail);
        
        return "mng/seeEnvDelvry/seeEnvDelvryDetail";
    }
    
    /**
    * 발급 / 재발급 
    *
    * @Title : updateIssue
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/seeEnvDelvry/updateIssue.do")
    @ResponseBody
    public Map<String, Object> updateIssue(@Valid SeeEnvDelvryVo seeEnvDelvryVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        seeEnvDelvryVo.setUser(user);
        int retVal = this.seeEnvDelvryService.updateIssue(seeEnvDelvryVo);
        
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
    * 교부대장 다운로드
    *
    * @Title : seeEnvDelvryListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param seeEnvDelvryVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/seeEnvDelvry/seeEnvDelvryListExcelDownload.do")
    public void seeEnvDelvryListExcelDownload(HttpServletRequest request, HttpServletResponse response, SeeEnvDelvryVo seeEnvDelvryVo) throws Exception {
        seeEnvDelvryService.seeEnvDelvryListExcelDownload(seeEnvDelvryVo, response, request);
    }
}
