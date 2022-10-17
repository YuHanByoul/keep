package com.kbrainc.plum.mng.faq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqVo;
import com.kbrainc.plum.mng.faq.service.FaqService;

/**
 * 
 * FAQ Controller.
 *
 * <pre>
 * com.kbrainc.plum.mng.faq.controller
 * - FaqController.java
 * </pre> 
 *
 * @ClassName : FaqController
 * @Description : FAQ Controller.
 * @author : KBRAINC
 * @date : 2021. 3. 12.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
public class FaqController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FaqService faqService;

    @RequestMapping(value = "/mng/faq/list.html")
    public String getListPage() {
        return "mng/faq/list";
    }

    @RequestMapping(value = "/mng/faq/reg.html")
    public String regFaqPage() {
        return "mng/faq/reg";
    }

    @RequestMapping(value = "/mng/faq/cl_list.html")
    public String getClListPage() {
        return "mng/faq/cl_list";
    }

    /**
     * FAQ 목록을 JSON으로 출력한다.
     *
     * @MethodName : getList
     * @Date : 2019. 5. 10.
     * @author : KBRAINC
     * @Modify :
     * @Description :
     * @param faqVoParam FaqVo 파라미터
     * @return JSON 목록
     * @throws Exception : 예외
     */
    @RequestMapping(value = "/mng/faq/faqList.do")
    @ResponseBody
    public Map<String, Object> getList(FaqVo faqVoParam) throws Exception {

        List<FaqVo> list = faqService.getList(faqVoParam);

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
     * FAQ 상세 내용을 출력한다.
     *
     * @MethodName : getFaq
     * @Date : 2019. 5. 10.
     * @author : KBRAINC
     * @Modify :
     * @Description :
     * @param faqVo :
     * @param model :
     * @return :
     * @throws Exception :
     */
    @RequestMapping(value = "/mng/faq/read.html")
    public String getFaq(FaqVo faqVo, Model model) throws Exception {

        FaqVo info = faqService.getFaq(faqVo);
        // info.setCntnts(HtmlEscape.unescapeHtml(info.getCntnts()).replaceAll("\n",
        // "<br />"));
        // info.setCntnts(info.getCntnts().replaceAll("\n", "<br />"));
        // logger.info("Data ==> " + info.getCntnts());

        model.addAttribute("info", info);

        return "mng/faq/read";
    }

    @RequestMapping(value = "/mng/faq/add.do", method = RequestMethod.POST)
    public @ResponseBody boolean insertFaq(@UserInfo UserVo user, @RequestBody FaqVo faqVo) throws Exception {
        faqVo.setReguserid(Integer.parseInt(user.getUserid()));
        // FaqVo.setCntnts(HtmlEscape.escapeHtml5(FaqVo.getCntnts()));
        faqVo.setUser(user);
        return faqService.addFaq(faqVo);
    }

    @RequestMapping(value = "/mng/faq/delete.do", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteFaq(@RequestParam(value = "faqid", required = true) int faqid) throws Exception {

        // TODO: 권한 체크 필요
        FaqVo faqVo = new FaqVo();
        faqVo.setFaqid(faqid);

        return faqService.deleteFaq(faqVo);
    }

    @RequestMapping(value = "/mng/faq/update.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaq(@UserInfo UserVo user, @RequestBody FaqVo faqVo) throws Exception {
        logger.info("[SKYJOB]FAQ Update Data : " + faqVo);

        faqVo.setUser(user);
        faqVo.setReguserid(Integer.parseInt(user.getUserid()));
        // FaqVo.setCntnts(HtmlEscape.escapeHtml5(FaqVo.getCntnts()));
        return faqService.updateFaq(faqVo);
    }

    @RequestMapping(value = "/mng/faq/faqClList.do")
    public @ResponseBody Map<String, Object> selectClList(FaqClVo faqClVoParam) throws Exception {

        List<FaqClVo> list = faqService.getClList(faqClVoParam);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    @RequestMapping(value = "/mng/faq/addCl.do", method = RequestMethod.POST)
    public @ResponseBody boolean insertFaqCl(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {

        return faqService.addFaqCl(faqClVo);
    }

    @RequestMapping(value = "/mng/faq/deleteCl.do", method = RequestMethod.DELETE)
    public @ResponseBody boolean deleteFaqCl(@RequestParam(value = "clid", required = true) int clid) throws Exception {

        // TODO: 권한 체크 필요
        FaqClVo faqClVO = new FaqClVo();
        faqClVO.setClid(clid);
        return faqService.deleteFaqCl(faqClVO);
    }

    @RequestMapping(value = "/mng/faq/updateCl.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqCl(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {
        logger.info("[SKYJOB]FAQ Update Data : " + faqClVo);
        faqClVo.setUser(user);
        return faqService.updateFaqCl(faqClVo);
    }

    @RequestMapping(value = "/mng/faq/modifyFaqOrd.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqOrd(@RequestParam(name = "mode", required = true) String mode,
            @UserInfo UserVo user, FaqVo faqVo) throws Exception {
        // logger.info("[SKYJOB]FAQ Update Data : " + faqClVo);
        if ("OD".equals(mode)) { //ORD down 
            return faqService.modifyFaqOrdDown(faqVo);
        } else { //ord up 
            return faqService.modifyFaqOrdUp(faqVo);
        }
    }

    @RequestMapping(value = "/mng/faq/modifyFaqClOrd.do", method = RequestMethod.POST)
    public @ResponseBody boolean updateFaqClOrd(@RequestParam(name = "mode", required = true) String mode,
            @UserInfo UserVo user, FaqClVo faqClVo) throws Exception {
        // logger.info("[SKYJOB]FAQ Update Data : " + faqClVo);
        if ("OD".equals(mode)) {
            return faqService.modifyFaqClOrdDown(faqClVo);
        } else {
            return faqService.modifyFaqClOrdUp(faqClVo);
        }
    }
    
    @RequestMapping(value = "/mng/faq/selectClList.do", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object>  selectClList(@UserInfo UserVo user, @RequestBody FaqClVo faqClVo) throws Exception {
        //logger.info("[SKYJOB]FAQ Update Data : " + faqClVo);
        faqClVo.setUser(user);
        
        System.out.println(faqClVo); 
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("list", faqService.getAllList(faqClVo));
        return response;
        
    }

}
