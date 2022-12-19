package com.kbrainc.plum.mng.wbzn.carbon.opnn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.wbzn.carbon.opnn.model.CarbonOpnnVo;
import com.kbrainc.plum.mng.wbzn.carbon.opnn.service.CarbonOpnnService;
import com.kbrainc.plum.mng.wbzn.now.opnn.model.OpnnVo;
import com.kbrainc.plum.mng.wbzn.now.opnn.service.OpnnService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 탄소중립환경교육 -> 독자소리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.opnn.controller
* - OpnnController.java
* </pre>
*
* @ClassName : OpnnController
* @Description : 탄소중립환경교육 -> 독자소리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CarbonOpnnController {
    
    @Autowired
    private CarbonOpnnService OpnnService;
    
    /**
    * 독자소리 리스트화면으로 이동
    *
    * @Title : opnnForm
    * @Description : 독자소리 리스트 화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/carbon/opnn/opnnListForm.html")
    public String opnnListForm() throws Exception {
        return "mng/wbzn/carbon/opnn/opnnList";
    }
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param CarbonOpnnVo 독자소리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/carbon/Opnn/selectOpnnList.do")
    @ResponseBody
    public Map<String, Object> selectOpnnList(CarbonOpnnVo opnnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CarbonOpnnVo> result = null;
        
        result =  OpnnService.selectOpnnList(opnnVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
