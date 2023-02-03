package com.kbrainc.plum.front.cntnts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.front.cntnts.service.CntntsService;

/**
* 컨텐츠 관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.cntnts.controller
* - CntntsController.java
* </pre>
*
* @ClassName : CntntsController
* @Description : 컨텐츠 관리 컨트롤러 클래스
* @author : JD
* @date : 2023. 2. 2.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.cntntsController")
@Alias("front.cntntsController")
public class CntntsController {

    @Resource(name = "front.cntntsServiceImpl")
    private CntntsService cntntsService;
    
    /**
    * 컨텐츠관리 목록화면 이동
    *
    * @Title : cntntsListForm
    * @Description : 컨텐츠관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/cntnts/cntntsListForm.html")
    public String cntntsListForm() throws Exception {
        return "front/cntnts/cntntsList";
    }
    
    /**
    * 컨텐츠관리 목록화면 이동
    *
    * @Title : cntntsListForm
    * @Description : 컨텐츠관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/cntnts/cntntsDetailForm.html")
    public String cntntsDetailForm(Model model, CntntsVo cntntsVo) throws Exception {
        CntntsVo result = null;
        result =  cntntsService.selectCntntsInfo(cntntsVo);
        
        model.addAttribute("cntnts", result);
        
        return "front/cntnts/cntntsDetail";
    }
    
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/cntnts/selectCntntsList.do")
    @ResponseBody
    public Map<String, Object> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CntntsVo> result = null;
        
        result =  cntntsService.selectCntntsList(cntntsVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
