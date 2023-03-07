package com.kbrainc.plum.front.ntcn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.kbrainc.plum.front.ntcn.model.NtcnVo;
import com.kbrainc.plum.front.ntcn.service.NtcnServiceImpl;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 * 사용자 알림 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.ntcn.controller
 * - NtcnController.java
 * </pre> 
 *
 * @ClassName : PopController
 * @Description : 사용자 알림
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.ntcnController")
@Alias("front.ntcnController")
public class NtcnController {

    @Resource(name = "front.ntcnServiceImpl")
    private NtcnServiceImpl ntcnService;
    
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림 내역 호출
     * @param NtcnVo
     * @param user  로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/ntcn/selectMainNtcnList.do")
    @ResponseBody
    public Map<String, Object> selectMainNtcnList(NtcnVo ntcnVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<NtcnVo> result = null;
        
        if(user!=null) {
            ntcnVo.setUser(user);
            result = ntcnService.selectMainNtcnList(ntcnVo);
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
