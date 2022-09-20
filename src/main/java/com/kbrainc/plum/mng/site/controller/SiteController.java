package com.kbrainc.plum.mng.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.site.model.SiteDomainVo;
import com.kbrainc.plum.mng.site.model.SiteVo;
import com.kbrainc.plum.mng.site.service.SiteService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResMenuService;

/**
 * 
 * 사이트관리 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.mng.site.controller
 * - SiteController.java
 * </pre> 
 *
 * @ClassName : SiteController
 * @Description : 사이트관리 컨트롤러.
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller
@RequestMapping(value = "/mng/site")
public class SiteController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    SiteService siteService;

    @Autowired
    private ResMenuService resMenuService;
    
    /**
     * 
     * 사이트 관리 메인 UI
     *
     * @Title : siteMngMain
     * @Description : TODO
     * @param model
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/siteMngMain.html")
    public String siteMngMain(ModelMap model) throws Exception {
        return "mng/site/siteMngMain";
    }
    
    /**
     * 
     * 사이트 정보 수정 및 등록 폼 UI
     *
     * @Title : siteMngReg
     * @Description : TODO
     * @param model
     * @param siteid
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/siteMngRegForm.html")
    public String siteMngReg(ModelMap model, Integer siteid) throws Exception {
        logger.info("Site ID : {}", siteid);
        
        SiteVo siteInfo =  new SiteVo();
        if(siteid != null && CommonUtils.isNotEmpty(siteid.toString())) {
            siteInfo = siteService.selectSiteInfo(siteid);
            logger.info("Site Info : {}", siteInfo);
            
        }
        
        model.addAttribute("siteInfo", siteInfo);
        return "mng/site/siteMngRegForm";
    }
    
    
    /**
     * 
     * 사이트 목록.
     *
     * @Title : selectSiteList
     * @Description : 사이트 목록.
     * @param siteVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/siteList.do")
    public Map<String, Object> selectSiteList(SiteVo siteVo) throws Exception {
        
        List<SiteVo> list = siteService.selectSiteList(siteVo);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);


        return response;
    }
    
    /**
     * 
     * 사이트 추가.
     *
     * @Title : insertSite
     * @Description : 사이트 추가.
     * @param siteVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/insertSite.do", method = RequestMethod.POST)
    public Map<String, Object> insertSite(@UserInfo UserVo user, @RequestBody SiteVo siteVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        siteVo.setReguserid(Integer.parseInt(user.getUserid()));
        siteVo.setUpdtuserid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.insertSite(siteVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        } else {
            resMenuService.putCacheForSiteid(String.valueOf(siteVo.getSiteid()));
        }
        
        return response;
    }

    /**
     * 
     * 사이트 정보 수정.
     *
     * @Title : updateSite
     * @Description : 사이트 정보 수정.
     * @param siteVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/updateSite.do", method = RequestMethod.POST)
    public Map<String, Object> updateSite(@UserInfo UserVo user, @RequestBody SiteVo siteVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        
        siteVo.setUpdtuserid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.updateSite(siteVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        
        return response;
    }
    
    /**
     * 
     * 사이트 삭제.
     *
     * @Title : deleteSite
     * @Description : 사이트 삭제.
     * @param siteid
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSite.do")
    public Map<String, Object> deleteSite(Integer siteid) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        
        Boolean res = siteService.deleteSite(siteid);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        
        return response;
    }

    /**
     * 
     * 사이트 도메인 추가.
     *
     * @Title : insertSiteDomain
     * @Description : 사이트 도메인 추가.
     * @param siteDomainVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/insertSiteDomain.do", method = RequestMethod.POST)
    public Map<String, Object> insertSiteDomain(@UserInfo UserVo user, @RequestBody SiteDomainVo siteDomainVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        
        siteDomainVo.setReguserid(Integer.parseInt(user.getUserid()));
        siteDomainVo.setUpdtuserid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.insertSiteDomain(siteDomainVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }else {
            response.put("siteid", siteDomainVo.getSiteid());
        }
        
        return response;
    }

    /**
     * 
     * 사이트 도메인 수정.
     *
     * @Title : updateSiteDomain
     * @Description : 사이트 도메인 수정.
     * @param siteDomainVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/updateSiteDomain.do", method = RequestMethod.POST)
    public Map<String, Object> updateSiteDomain(@UserInfo UserVo user, @RequestBody SiteDomainVo siteDomainVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        
        siteDomainVo.setUpdtuserid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.updateSiteDomain(siteDomainVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        
        return response;
    }

    /**
     * 
     * 사이트 도메인 삭제.
     *
     * @Title : deleteSiteDomain
     * @Description : 사이트 도메인 삭제.
     * @param siteDomainVo
     * @throws Exception
     * @return Map<String,Object>
     */
    @ResponseBody
    @RequestMapping(value = "/deleteSiteDomain.do")
    public Map<String, Object> deleteSiteDomain(@RequestBody SiteDomainVo siteDomainVo) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        
        Boolean res = siteService.deleteSiteDomain(siteDomainVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
        }
        
        return response;
    }

}
