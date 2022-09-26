package com.kbrainc.plum.front.community.controller;

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
@Controller("front.communityController")
@Alias("front.communityController")
public class CommunityController {


    @RequestMapping(value = "/front/community/studyDataShare.html")
    public String studyDataShare() throws Exception {
        return "front/prepareNow";
    }
    
    @RequestMapping(value = "/front/community/infoShare.html")
    public String infoShare() throws Exception {
    	return "front/prepareNow";
    }
}
