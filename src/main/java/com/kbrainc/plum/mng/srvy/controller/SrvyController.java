package com.kbrainc.plum.mng.srvy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.mng.srvy.service.SrvyServiceImpl;

/**
 * 
 * 설문관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.controller
 * - SrvyController.java
 * </pre>
 *
 * @ClassName : SrvyController
 * @Description : 설문관리 Controller
 * @author : KBRAINC
 * @date : 2022. 12. 21.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SrvyController {

    @Autowired
    private SrvyServiceImpl srvyService;
    
    /**
     * 설문관리 화면
     *
     * @Title : srvyMng
     * @Description : 설문관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyMng.html")
    public String srvyMng() throws Exception {
        return "mng/srvy/srvyMng";
    }
    
    /**
     * 대상자설문 목록 화면
     *
     * @Title : srvyMng
     * @Description : 대상자설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/trprSrvy.html")
    public String trprSrvy() throws Exception {
        return "mng/srvy/trprSrvyList";
    }
    
    /**
     * 기관설문 목록 화면
     *
     * @Title : instSrvy
     * @Description : 기관설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/instSrvy.html")
    public String instSrvy() throws Exception {
        return "mng/srvy/instSrvyList";
    }
    
    /**
     * 컨설팅만족도설문 목록 화면
     *
     * @Title : cnsltngDgstfnSrvy
     * @Description : 컨설팅만족도설문 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/cnsltngDgstfnSrvy.html")
    public String cnsltngDgstfnSrvy() throws Exception {
        return "mng/srvy/cnsltngDgstfnSrvyList";
    }
    
    /**
     * 설문결과관리 화면
     *
     * @Title : srvyRsltMng
     * @Description : 설문결과관리 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/srvyRsltMng.html")
    public String srvyRsltMng() throws Exception {
        return "mng/srvy/srvyRsltMng";
    }
    
    
    /**
     * 대상자설문 목록 조회
     *
     * @Title : selectQestnrList
     * @Description : 대상자설문 목록 조회
     * @param qestnrVo QestnrVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/srvy/selectTrprSrvyList.do")
    @ResponseBody
    public Map<String, Object> selectTrprSrvyList(SrvyVo srvyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SrvyVo> result = srvyService.selectTrprSrvyList(srvyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
}
