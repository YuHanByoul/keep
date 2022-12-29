package com.kbrainc.plum.mng.expertPoolMng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 전문가 풀 관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.controller
 * - expertPoolMngController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : expertPoolMngController
 * @Description : 전문가 풀 관리 컨트롤러 클래스
 * @date : 2022. 12. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class expertPoolMngController {

    @RequestMapping("/mng/expertPoolMng/expertList.html")
    public String expertList() throws Exception {
        return "mng/expertPoolMng/expertList";
    }
}
