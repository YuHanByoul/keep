package com.kbrainc.plum.front.etc.controller;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 커뮤니티, 학생정보관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.controller
 * - BbsController.java
 * </pre> 
 *
 * @ClassName : CommunityController
 * @Description : CommunityController 커뮤니티, 학생정보관리 화면이동만 핸들링
 * @author : KBRAINC
 * @date : 2021. 11. 24.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.etcController")
@Alias("front.etcController")
public class EtcController {

    @RequestMapping(value = "/front/intro/introduceHJ.html")
    public String classStastics() throws Exception {
    	return "front/prepareNow";
    }
    
    
    
}