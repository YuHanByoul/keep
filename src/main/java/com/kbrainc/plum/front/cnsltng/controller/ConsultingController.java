package com.kbrainc.plum.front.cnsltng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.cnsltng.model.ConsultingVo;
import com.kbrainc.plum.front.cnsltng.service.ConsultingService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 컨설팅 대상내역 컨트롤러
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
public class ConsultingController {
    
    @Autowired
    private ConsultingService consultingService;
    
    
    /**
    * 컨설팅 내역 화면. 
    *
    * @Title : clclnReportListForm
    * @Description : TODO
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/cnsltng/cnsltngListForm.html")
    public String cnsltngListForm(Model model, ConsultingVo consultingVo) throws Exception {
        model.addAttribute("reqMngVo", consultingVo);
        return "front/cnsltng/cnsltngList";
    }

    /**
    * 컨설팅 내역 조회 
    *
    * @Title : selectCnsltngList
    * @Description : TODO
    * @param consultingVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/cnsltng/selectCnsltngList.do")
    @ResponseBody
    public Map<String, Object> selectCnsltngList(ConsultingVo consultingVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ConsultingVo> result = null;
        consultingVo.setUser(user);
        result =  consultingService.selectCnsltngList(consultingVo);
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
    * 컨설팅 내역 팝업 
    *
    * @Title : cnsltngPopup
    * @Description : TODO
    * @param consultingVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/cnsltng/cnsltngPopup.html")
    public String cnsltngPopup(ConsultingVo consultingVo, Model model, @UserInfo UserVo user) throws Exception {
        ConsultingVo detail = null;
        consultingVo.setUser(user);
        List<ConsultingVo> list = this.consultingService.selectCnsltngList(consultingVo);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 0)
                detail = list.get(0);
        } else {
            detail = new ConsultingVo();
        }
        
        model.addAttribute("detail", detail);
        
        return "front/cnsltng/cnsltngPopup";
    }
}
