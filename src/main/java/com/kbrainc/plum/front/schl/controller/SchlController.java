package com.kbrainc.plum.front.schl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.schl.model.SchlVo;
import com.kbrainc.plum.front.schl.service.SchlService;

/**
* 학교정보 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.front.schl.controller
* - MemberController.java
* </pre> 
*
* @ClassName : SchlController
* @Description : 학교정보 컨트롤러 클래스.
* @author : KBRAINC
* @date : 2021. 4. 12.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Controller("front.schlController")
@Alias("front.schlController")
public class SchlController {

    @Resource(name = "front.schlServiceImpl")
    private SchlService schlService;

    
    /**
    * 회원목록 조회.
    *
    * @Title       : selectMemberList 
    * @Description : 회원목록 조회.
    * @param MemberVo MemberVo객체
    * @param user 사용자세션정보
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/schl/selectSchlList.do")
    @ResponseBody
    public Map<String, Object> selectMemberList(SchlVo schlVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SchlVo> result = null;
        
        result = schlService.selectSchlList(schlVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.size());
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
    
        return resultMap;
    }
    
    /**
     * 학교검색 팝업 호출
     *
     * @Title       : 마이페이지  
     * @Description : 마이페이지 (회원정보 수정창) 화면 이동
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/member/searchSchoolPop.html")
    public String searchSchoolPop() throws Exception {
    	return "front/member/searchSchoolPop";
    }

    
}