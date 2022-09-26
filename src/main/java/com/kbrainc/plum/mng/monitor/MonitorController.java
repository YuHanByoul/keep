package com.kbrainc.plum.mng.monitor;

import com.kbrainc.plum.rte.security.ReloadableChannelProcessingFilterMetadataSource;
import com.kbrainc.plum.rte.security.ReloadableFilterSecurityInterceptorMetadataSource;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.service.ResMenuService;
import com.kbrainc.plum.rte.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 메모리DB를 리로딩하는 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.mng.monitor
 * - MonitorController.java
 * </pre> 
 *
 * @ClassName : MonitorController
 * @Description : 메모리DB를 리로딩하는 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class MonitorController {

    @Autowired
    ResMenuService resMenuService;

    @Autowired
    ResCodeService resCodeService;

    @Autowired
    ReloadableChannelProcessingFilterMetadataSource reloadableChannelProcessingFilterMetadataSource;

    @Autowired
    ReloadableFilterSecurityInterceptorMetadataSource reloadableFilterSecurityInterceptorMetadataSource;

    /**
     * @Title : monitorForm
     * @Description : 모니터 페이지
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/monitor/monitorForm.html")
    public String monitorForm() throws Exception {
        return "mng/monitor/monitorForm";
    }

    /**
     * @Title : reloadMenuInfo
     * @Description : 메뉴정보를 리로드 한다
     * @throws Exception
     * @return String 리턴값없음
     */
    @RequestMapping(value = "/mng/monitor/reloadMenuInfo.do")
    @ResponseBody
    public String reloadMenuInfo() throws Exception {
        resMenuService.makeTreeMenuInfo();
        return "";
    }

    /**
     * @Title : reloadCodeInfo
     * @Description : 코드정보를 리로드 한다.
     * @throws Exception
     * @return String 리턴값없음
     */
    @RequestMapping(value = "/mng/monitor/reloadCodeInfo.do")
    @ResponseBody
    public String reloadCodeInfo() throws Exception {
        resCodeService.reloadCodeInfo();
        return "";
    }

    /**
     * @Title : reloadSecurityMetadataSource
     * @Description : 시큐리티 메타데이터소스를 리로드 한다.
     * @throws Exception
     * @return String 리턴값없음
     */
    @RequestMapping(value = "/mng/monitor/reloadSecurityMetadataSource.do")
    @ResponseBody
    public String reloadSecurityMetadataSource() throws Exception {
        reloadableChannelProcessingFilterMetadataSource.reload();
        reloadableFilterSecurityInterceptorMetadataSource.reload();
        return "";
    }

    /**
     * @Title : reloadMenuInfoReflect
     * @Description : 모든 서버 반영(메뉴정보)
     * @throws Exception
     * @return Map 호출에 실패한 host목록(,)로 구분
     */
    @RequestMapping("/mng/monitor/reloadMenuInfoReflect.do")
    @ResponseBody
    public Map reloadMenuInfoReflect() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", CommonUtil.allWasUrlCaller("/mng/monitor/reloadMenuInfo.do"));
        return map;
    }

    /**
     * @Title : reloadCodeInfoReflect
     * @Description : 모든 서버 반영(코드정보)
     * @throws Exception
     * @return Map 호출에 실패한 host목록(,)로 구분
     */
    @RequestMapping("/mng/monitor/reloadCodeInfoReflect.do")
    @ResponseBody
    public Map reloadCodeInfoReflect() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", CommonUtil.allWasUrlCaller("/mng/monitor/reloadCodeInfo.do"));
        return map;
    }

    /**
     * @Title : reloadSecurityMetadataSourceReflect
     * @Description : 모든 서버 반영(시큐리티 메타데이터소스)
     * @throws Exception
     * @return Map 호출에 실패한 host목록(,)로 구분
     */
    @RequestMapping("/mng/monitor/reloadSecurityMetadataSourceReflect.do")
    @ResponseBody
    public Map reloadSecurityMetadataSourceReflect() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", CommonUtil.allWasUrlCaller("/mng/monitor/reloadSecurityMetadataSource.do"));
        return map;
    }
}