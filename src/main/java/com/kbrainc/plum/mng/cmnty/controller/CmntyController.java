package com.kbrainc.plum.mng.cmnty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.cmnty.model.CmntyMbrVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyPstVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.mng.cmnty.service.CmntyServiceImpl;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
 * 
 * 설문지관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.controller
 * - CmntyController.java
 * </pre>
 *
 * @ClassName : CmntyController
 * @Description : 커뮤니티 관리 Controller
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class CmntyController {

    @Autowired
    private CmntyServiceImpl cmntyService;
    
    @Autowired
    private FileService fileService;
    
    /**
     * 커뮤니티 목록 화면
     *
     * @Title : cmntyForm
     * @Description : 커뮤니티 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyForm.html")
    public String cmntyForm() throws Exception {
        return "mng/cmnty/cmnty";
    }
    
    /**
     * 커뮤니티 상세 화면
     *
     * @Title : cmntyDetailForm
     * @Description : 커뮤니티 상세 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyDetailForm.html")
    public String cmntyDetailForm(CmntyVo cmntyVo, Model model) throws Exception {
        model.addAttribute("cmnty", cmntyService.selectCmntyInfo(cmntyVo));
        return "mng/cmnty/cmntyDetail";
    }
    
    /**
     * 커뮤니티 수정 화면
     *
     * @Title : qestnrUpdateForm
     * @Description : 커뮤니티 수정 화면
     * @param cmntyVo CmntyVo 객체
     * @param model 모델객체
     * @param user 사용자 세션 정보
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyUpdateForm.html")
    public String cmntyUpdateForm(CmntyVo cmntyVo, Model model, @UserInfo UserVo user) throws Exception {
        cmntyVo.setUser(user);
        CmntyVo resultVo = cmntyService.selectCmntyInfo(cmntyVo);
        if(resultVo.getCmntyLogoFileid() != null && resultVo.getCmntyLogoFileid() != 0) {
            FileVo fileVo = new FileVo();
            fileVo.setFileid(resultVo.getCmntyLogoFileid());
            fileVo.setUser(user);
            model.addAttribute("logoFile", fileService.getFileInfo(fileVo));
        }
        model.addAttribute("cmnty", resultVo);
        return "mng/cmnty/cmntyUpdate";
    }
    
    /**
     * 커뮤니티 회원 목록 화면
     *
     * @Title : cmntyMbrForm
     * @Description : 커뮤니티 회원 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/cmntyMbrForm.html")
    public String cmntyMbrForm() throws Exception {
        return "mng/cmnty/cmntyMbr";
    }
    
    /**
     * 커뮤니티 목록 조회
     *
     * @Title : selectCmntyList
     * @Description : 커뮤니티 목록 조회
     * @param cmntyVo CmntyVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyList(CmntyVo cmntyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyVo> result = cmntyService.selectCmntyList(cmntyVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 회원 목록 조회
     *
     * @Title : selectCmntyMbrList
     * @Description : 커뮤니티 회원 목록 조회
     * @param cmntyMbrVo CmntyMbrVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyMbrList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyMbrVo> result = cmntyService.selectCmntyMbrList(cmntyMbrVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
    /**
     * 커뮤니티 게시글 목록 조회
     *
     * @Title : selectCmntyPstList
     * @Description : 커뮤니티 게시글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/cmnty/selectCmntyPstList.do")
    @ResponseBody
    public Map<String, Object> selectCmntyPstList(CmntyPstVo cmntyPstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CmntyPstVo> result = cmntyService.selectCmntyPstList(cmntyPstVo);
                    
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
            
        return resultMap;
    }
    
}
