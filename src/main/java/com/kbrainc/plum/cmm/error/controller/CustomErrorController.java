package com.kbrainc.plum.cmm.error.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kbrainc.plum.rte.exception.PageNotFoundException;
import com.kbrainc.plum.rte.exception.controller.ExceptionHandlingController;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import com.kbrainc.plum.rte.service.ResSiteService;

/**
 * 
 * 에러상태코드에 따른 커스텀 에러페이지의 분기를 위한 커스텀 에러 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.cmm.error.controller
 * - CustomErrorController.java
 * </pre> 
 *
 * @ClassName : CustomErrorController
 * @Description : 에러상태코드에 따른 커스텀 에러페이지의 분기를 위한 커스텀 에러 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class CustomErrorController implements ErrorController {
    protected Logger logger = LoggerFactory.getLogger(CustomErrorController.class);
    
    @Autowired
    ResSiteService resSiteService;
    
    /**
     * .
     *
     * @Title       : handleError 
     * @Description : TODO
     * @param request :
     * @return String
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        ////////////////////////////////////////////////////////////////////////////////////
        // 접속사이트정보
        ////////////////////////////////////////////////////////////////////////////////////
        SiteInfoVo siteInfo = (SiteInfoVo) session.getAttribute("site");
        String dmn = request.getServerName();
        
        if (siteInfo == null || !dmn.equals(siteInfo.getDmn())) {
            siteInfo = resSiteService.getSiteInfo(dmn);

            if (siteInfo == null) {
                session.removeAttribute("site");
            }
            session.setAttribute("site", siteInfo);
        }
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) { // 접근권한없음
                return "error/error_403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) { // 페이지를 찾을수 없음
                return "error/error_404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) { // 내부서버오류
                if (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION).toString().endsWith("rejectPublicInvocations property is set to 'true'")) {

                    return "error/error_404";
                }
                return "error/error_500";
            }
        }
        return "error/error"; // 기타에러
    }

    @Override
    public String getErrorPath() {
        logger.error("Error 발생.");
        return "/error";
    }
}