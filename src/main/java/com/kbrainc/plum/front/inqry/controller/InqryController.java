package com.kbrainc.plum.front.inqry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.front.inqry.service.InqryService;

/**
 * 
 * 1:1문의 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.controller
 * - InqryController.java
 * </pre> 
 *
 * @ClassName : InqryController
 * @Description : 1:1문의 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller("front.inqryController")
@Alias("front.inqryController")
public class InqryController {

    @Resource(name = "front.inqryServiceImpl")
    private InqryService inqryService;
    
    /**
     * @Title : registerInquryForm
     * @Description : 1:1문의 화면 이동
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/front/inqry/InquryForm.html")
    public String registerInquryForm() throws Exception {
        return "front/inqry/qna";
    }
    
    /**
     * 문의 등록
     *
     * @Title       : insertInqury 
     * @Description : 문의 등록
     * @param MemberVo MemberVo , TeacherVo TeacherVo객체
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/inqury/insertInqury.do")
    @ResponseBody
    public Map<String, Object> insertInqury(InqryVo InqryVo) throws Exception {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	int checkDuplicationID = inqryService.insertInqry(InqryVo);
    	
    	if(checkDuplicationID > 0) {
    		resultMap.put("result", true);
    	}else {
    		resultMap.put("result", false);
    	}
    	return resultMap;
    }
    
    
    
    
    
    


}