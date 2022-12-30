package com.kbrainc.plum.mng.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
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
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping(value = "/mng/site")
public class SiteController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    SiteService siteService;
    
    @Autowired
    private FileService fileService;
    
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
            
            FileVo fileVo = new FileVo();
            
            if (siteInfo.getLogoFileid() != null && !siteInfo.getLogoFileid().equals(0)) {
                fileVo.setFileid(Integer.parseInt(siteInfo.getLogoFileid().toString()));
                
                FileVo logoVo= fileService.getFileInfo(fileVo);
                
                model.addAttribute("logoFile",logoVo );
            } else {
                model.addAttribute("logoFile", null);
            }
            
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
     * @param siteVo SiteVo객체
     * @param request 요청객체
     * @return Map<String,Object> 사이트목록정보
     * @throws Exception 예외
     */
    @ResponseBody
    @RequestMapping(value = {"/siteList.do", "/siteAplyList.do"})
    public Map<String, Object> selectSiteList(SiteVo siteVo, HttpServletRequest request) throws Exception {
        if ("/mng/site/siteAplyList.do".equals(request.getRequestURI())) { // 분양사이트 조회
            siteVo.setSysKndCd("T"); // 분양 사이트
        }
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
     * @param bindingResult 유효성검증결과
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @ResponseBody
    @RequestMapping(value = {"/insertSite.do", "/insertSiteAply.do"}, method = RequestMethod.POST)
    public Map<String, Object> insertSite(@UserInfo UserVo user, @RequestBody @Valid SiteVo siteVo, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        if ("/mng/site/insertSiteAply.do".equals(request.getRequestURI())) { // 분양사이트 조회
            siteVo.setSysSeCd("U"); // 사용자 사이트
            siteVo.setSysKndCd("T"); // 분양 사이트
        }
        
        Map<String, Object> response = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            response.put("msg", fieldError.getDefaultMessage());
            return response;
        }
        
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        response.put("msg", "등록에 성공하였습니다.");
        siteVo.setRgtrid(Integer.parseInt(user.getUserid()));
        siteVo.setMdfrid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.insertSite(siteVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
            response.put("msg", "등록에 실패하였습니다.");
        } else {
            response.put("siteid", siteVo.getSiteid());
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
     * @param bindingResult 유효성검증결과
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @ResponseBody
    @RequestMapping(value = {"/updateSite.do", "/updateSiteAply.do"}, method = RequestMethod.POST)
    public Map<String, Object> updateSite(@UserInfo UserVo user, @RequestBody SiteVo siteVo, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        if ("/mng/site/updateSiteAply.do".equals(request.getRequestURI())) { // 분양사이트 조회
            siteVo.setSysSeCd("U"); // 사용자 사이트
            siteVo.setSysKndCd("T"); // 분양 사이트
        }
        
        Map<String, Object> response = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            response.put("msg", fieldError.getDefaultMessage());
            return response;
        }
        
        response.put("result", Constant.REST_API_RESULT_SUCCESS);
        response.put("msg", "수정에 성공하였습니다.");
        
        siteVo.setMdfrid(Integer.parseInt(user.getUserid()));
        Boolean res = siteService.updateSite(siteVo);
        
        if(!res) {
            response.put("result", Constant.REST_API_RESULT_FAIL);
            response.put("msg", "수정에 실패하였습니다.");
        }
        
        return response;
    }

    /**
    * 사이트 도메인 저장.
    *
    * @Title : insertSiteDomains
    * @Description : 사이트 도메인 저장.
    * @param user 사용자세션정보
    * @param siteDomainVo SiteDomainVo객체
    * @param bindingResult siteDomainVo 유효성검증결과
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
    @ResponseBody
    @RequestMapping(value = {"/insertSiteDomains.do", "/insertSiteAplyDomains.do"}, method = RequestMethod.POST)
    public Map<String, Object> insertSiteDomains(@UserInfo UserVo user, @Valid SiteDomainVo siteDomainVo, BindingResult bindingResult) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            response.put("msg", fieldError.getDefaultMessage());
            return response;
        }
        
        siteDomainVo.setUser(user);
        
        SiteDomainVo siteDomainInfo = siteService.selectSameSiteDomains(siteDomainVo);
        if (siteDomainInfo != null) {
            StringBuffer sb = new StringBuffer();
            response.put("msg", sb.append("도메인이 중복되어 저장에 실패하였습니다.\n(").append(siteDomainInfo.getDomains()).append(")").toString());
            return response;
        }
        
        int retVal = siteService.saveSiteDomain(siteDomainVo);
        
        if (retVal > 0) {
            response.put("result", Constant.REST_API_RESULT_SUCCESS);
            response.put("msg", "저장에 성공하였습니다.");
        } else {
            response.put("result", Constant.REST_API_RESULT_FAIL);
            response.put("msg", "저장에 실패하였습니다.");
        }
        
        return response;
    }
    
    /**
    * 
    * 분양사이트 관리 메인 UI
    *
    * @Title : siteAplyMngMain
    * @Description : 분양사이트 관리 메인 UI
    * @param model ModelMap객체
    * @return String 이동화면경로
    * @throws Exception 예외
    */
   @RequestMapping(value = "/siteAplyMngMain.html")
   public String siteAplyMngMain(ModelMap model) throws Exception {
       return "mng/site/siteAplyMngMain";
   }
   
   /**
    * 
    * 분양사이트 정보 수정 및 등록 폼 UI
    *
    * @Title : siteMngReg
    * @Description : 분양사이트 정보 수정 및 등록 폼 UI
    * @param model Model객체
    * @param siteid 사이트아이디
    * @return String 이동화면경로
    * @throws Exception 예외
    */
   @RequestMapping(value = "/siteAplyMngRegForm.html")
   public String siteAplyMngReg(ModelMap model, Integer siteid) throws Exception {
       logger.info("Site ID : {}", siteid);
       
       SiteVo siteInfo =  new SiteVo();
       if(siteid != null && CommonUtils.isNotEmpty(siteid.toString())) {
           siteInfo = siteService.selectSiteInfo(siteid);
           logger.info("Site Info : {}", siteInfo);
           
           FileVo fileVo = new FileVo();
           
           if (siteInfo.getLogoFileid() != null && !siteInfo.getLogoFileid().equals(0)) {
               fileVo.setFileid(Integer.parseInt(siteInfo.getLogoFileid().toString()));
               
               FileVo logoVo= fileService.getFileInfo(fileVo);
               
               model.addAttribute("logoFile",logoVo );
           } else {
               model.addAttribute("logoFile", null);
           }
           
       }
       
       model.addAttribute("siteInfo", siteInfo);
       return "mng/site/siteAplyMngRegForm";
   }
}
