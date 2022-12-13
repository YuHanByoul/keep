package com.kbrainc.plum.mng.wbzn.now.opnn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.wbzn.now.opnn.model.OpnnVo;
import com.kbrainc.plum.mng.wbzn.now.opnn.service.OpnnService;

/**
* 환경교육NOW -> 프로그램안내관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.prgrmgd.controller
* - PrgrmgdController.java
* </pre>
*
* @ClassName : PrgrmgdController
* @Description : 환경교육NOW -> 프로그램안내관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class OpnnController {
    
    @Autowired
    private OpnnService OpnnService;
    
    /**
    * 프로그램안내관리 리스트화면으로 이동
    *
    * @Title : prgrmgdForm
    * @Description : 프로그램안내관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/now/opnn/opnnForm.html")
    public String prgrmgdForm() throws Exception {        
        return "mng/wbzn/now/opnn/opnnForm";
    }
    
    /**
    * 프로그램안내관리 게시글 목록 조회
    *
    * @Title : selectPrgrmgdList
    * @Description : 프로그램안내관리 게시글 목록 조회
    * @param prgrmgdVo 프로그램안내관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/now/Opnn/selectOpnnList.do")
    @ResponseBody
    public Map<String, Object> selectOpnnList(OpnnVo prgrmgdVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<OpnnVo> result = null;
        
        result =  OpnnService.selectOpnnList(prgrmgdVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
