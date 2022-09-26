package com.kbrainc.plum.front.bbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.bbs.model.BbsClVo;
import com.kbrainc.plum.front.bbs.model.BbsVo;
import com.kbrainc.plum.front.bbs.model.CmntVo;
import com.kbrainc.plum.front.bbs.model.PstVo;
import com.kbrainc.plum.front.bbs.service.BbsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
 * 
 * BBS Controller
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.controller
 * - BbsController.java
 * </pre> 
 *
 * @ClassName : BbsController
 * @Description : BBS Controller
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller("front.bbsController")
@Alias("front.bbsController")
public class BbsController {

    @Resource(name = "front.bbsServiceImpl")
    private BbsServiceImpl bbsService;

    
    
}
