package com.kbrainc.plum.mng.blcklst.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.blcklst.service.BlcklstDsctnService;
import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;

/**
* 블랙리스트 내역 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.blcklst.controller
* - BlcklstDsctnController.java
* </pre>
*
* @ClassName : BlcklstDsctnController
* @Description : 블랙리스트 내역 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class BlcklstDsctnController {
    
    @Autowired
    private BlcklstDsctnService blcklstDsctnService;
    
    /**
    * 블랙리스트 내역 리스트화면으로 이동
    *
    * @Title : blcklstDsctnListForm
    * @Description : 블랙리스트 내역 리스트 화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/blcklist/blcklstDsctnListForm.html")
    public String blcklstDsctnListForm() throws Exception {
        return "mng/blcklst/blcklstDsctnList";
    }
    
    /**
    * 블랙리스트 내역 게시글 목록 조회
    *
    * @Title : selectBlcklstDsctnList
    * @Description : 구독자 게시글 목록 조회
    * @param selectBlcklstDsctnList 블랙리스트 내역 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/blcklst/selectBlcklstDsctnList.do")
    @ResponseBody
    public Map<String, Object> selectBlcklstDsctnList(BlcklstDsctnVo blcklstDsctnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<BlcklstDsctnVo> result = null;
        
        result =  blcklstDsctnService.selectBlcklstDsctnList(blcklstDsctnVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
