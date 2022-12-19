package com.kbrainc.plum.mng.wbzn.now.reader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.wbzn.now.reader.model.ReaderVo;
import com.kbrainc.plum.mng.wbzn.now.reader.service.ReaderService;

/**
* 환경교육NOW -> 구독자 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.reader.controller
* - ReaderController.java
* </pre>
*
* @ClassName : ReaderController
* @Description : 환경교육NOW -> 구독자 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ReaderController {
    
    @Autowired
    private ReaderService readerService;
    
    /**
    * 구독자 리스트화면으로 이동
    *
    * @Title : readerForm
    * @Description : 구독자 리스트 화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/wbzn/now/reader/readerListForm.html")
    public String readerForm() throws Exception {
        return "mng/wbzn/now/reader/readerList";
    }
    
    /**
    * 구독자 게시글 목록 조회
    *
    * @Title : selectReaderList
    * @Description : 구독자 게시글 목록 조회
    * @param CarbonReaderVo 구독자 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/wbzn/now/reader/selectReaderList.do")
    @ResponseBody
    public Map<String, Object> selectReaderList(ReaderVo readerVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReaderVo> result = null;
        
        result =  readerService.selectReaderList(readerVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
