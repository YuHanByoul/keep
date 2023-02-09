package com.kbrainc.plum.front.envWord.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.envWord.model.EnvWordVo;
import com.kbrainc.plum.front.envWord.service.EnvWordService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 환경교육용어사전 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.envWord.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : EnvWordController
* @Description : 환경교육용어사전 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.envWordController")
@Alias("front.envWordController")
public class EnvWordController {
    
    @Resource(name = "front.envWordServiceImpl")
    private EnvWordService envWordService;
    
    /**
    * 환경교육용어사전 리스트화면으로 이동
    *
    * @Title : envWordForm
    * @Description : 환경교육용어사전 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/envWord/envWordListForm.html")
    public String envWordListForm() throws Exception {
        return "front/envWord/envWordList";
    }
    
    /**
    * 환경교육용어사전 수정화면으로 이동
    *
    * @Title : envWordUpdateForm
    * @Description : 환경교육용어사전 수정화면으로 이동
    * @param envWordVo 환경교육용어사전 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/envWord/envWordDetailForm.html")
    public String envWordUpdateForm(EnvWordVo envWordVo, Model model) throws Exception {
        model.addAttribute("envWord", envWordService.selectEnvWordInfo(envWordVo));
        
        return "front/envWord/envWordDetail";
    }
   
    /**
    * 환경교육용어사전 리스트 기능
    *
    * @Title : selectEnvWordList
    * @Description : 환경교육용어사전 리스트 기능
    * @param envWordVo 환경교육용어사전 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/envWord/selectEnvWordList.do")
    @ResponseBody
    public Map<String, Object> selectEnvWordList(EnvWordVo envWordVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvWordVo> result = null;
        
        result =  envWordService.selectEnvWordList(envWordVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
}
