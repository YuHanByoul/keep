package com.kbrainc.plum.front.mmnws.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.mmnws.model.MmnwsVo;
import com.kbrainc.plum.front.mmnws.service.MmnwsService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 언론보도관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.mmnws.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : MmnwsController
* @Description : 언론보도관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.mmnwsController")
@Alias("front.mmnwsController")
public class MmnwsController {
    
    @Resource(name = "front.mmnwsServiceImpl")
    private MmnwsService mmnwsService;
    
    /**
    * 언론보도관리 리스트화면으로 이동
    *
    * @Title : mmnwsForm
    * @Description : 언론보도관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/mmnws/mmnwsListForm.html")
    public String mmnwsListForm() throws Exception {
        return "front/mmnws/mmnwsList";
    }
   
    /**
    * 언론보도관리 리스트 기능
    *
    * @Title : selectMmnwsList
    * @Description : 언론보도관리 리스트 기능
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/mmnws/selectMmnwsList.do")
    @ResponseBody
    public Map<String, Object> selectMmnwsList(MmnwsVo mmnwsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MmnwsVo> result = null;
        
        result =  mmnwsService.selectMmnwsList(mmnwsVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), result.get(0).getRowPerPage()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
}
