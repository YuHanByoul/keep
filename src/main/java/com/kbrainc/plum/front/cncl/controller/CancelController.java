package com.kbrainc.plum.front.cncl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.cncl.model.CancelVo;
import com.kbrainc.plum.front.cncl.service.CancelService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 사업포기신청 컨트롤러
*
* <pre>
* com.kbrainc.plum.front.cnsltng.controller
* - ConsultingController.java
* </pre> 
*
* @ClassName : ConsultingController
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class CancelController {
    
    @Autowired
    private CancelService cancelService;
    
    /**
    * 사업포기신청 목록 화면 
    *
    * @Title : cnsltngListForm
    * @Description : TODO
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/cncl/cancelListForm.html")
    public String cancelListForm(Model model) throws Exception {
        return "front/cncl/cancelList";
    }
    
    /**
    * 사업포기신청 목록 조회 
    *
    * @Title : selectCancelList
    * @Description : TODO
    * @param cancelVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/cncl/selectCancelList.do")
    @ResponseBody
    public Map<String, Object> selectCancelList(CancelVo cancelVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CancelVo> result = null;
        cancelVo.setUser(user);
        result =  cancelService.selectCancelList(cancelVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 사업포기신청 상세화면 
    *
    * @Title : selectCancelDetail
    * @Description : TODO
    * @param cancelVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/cncl/selectCancelDetail.html")
    public String selectCancelDetail(CancelVo cancelVo, Model model, @UserInfo UserVo user) throws Exception {
        CancelVo detail = null;
        List<CancelVo> list = null;
        cancelVo.setUser(user);
        if (cancelVo.getDmndid() > 0) {
            list = this.cancelService.selectCancelList(cancelVo);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 0)
                detail = list.get(0);
        } else {
            detail = new CancelVo();
        }
        
        model.addAttribute("detail", detail);
        model.addAttribute("list", cancelService.selectFldList(cancelVo));
        
        return "front/cncl/cancelDetail";
    }

    /**
    * 공모신청 기본정보 조회. 
    *
    * @Title : selectBaseInfo
    * @Description : TODO
    * @param cancelVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/cncl/selectBaseInfo.do")
    @ResponseBody
    public Map<String, Object> selectBaseInfo(CancelVo cancelVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        cancelVo.setUser(user);
        resultMap.put("list", cancelService.selectBaseInfo(cancelVo));
        return resultMap;
    }
    
    /**
    * 사업포기신청 저장 
    *
    * @Title : saveCancel
    * @Description : TODO
    * @param cancelVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/front/cncl/saveCancel.do")
    @ResponseBody
    public Map<String, Object> saveCancel(@Valid CancelVo cancelVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        cancelVo.setUser(user);
        
        int retVal = cancelService.saveCancel(cancelVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
}
