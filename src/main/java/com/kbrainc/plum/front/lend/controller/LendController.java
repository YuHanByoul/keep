package com.kbrainc.plum.front.lend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.lend.service.LendServiceImpl;
import com.kbrainc.plum.front.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 *  교구 대여(사용자) 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.lend.controller
 * - LendController.java
 * </pre> 
 *
 * @ClassName : LendController
 * @Description : 교구 대여(사용자) 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 03. 03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.lendController")
@Alias("front.lendController")
public class LendController {

    @Resource(name = "front.lendServiceImpl")
    private LendServiceImpl lendService;
    
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림 내역 호출
     * @param NtcnVo
     * @param user  로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/lend/selectMainNtcnList.do")
    @ResponseBody
    public Map<String, Object> selectMainNtcnList(NtcnVo ntcnVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<NtcnVo> result = null;
        
        if(user!=null) {
            ntcnVo.setUser(user);
            //result = ntcnService.selectMainNtcnList(ntcnVo);
        }
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
}
