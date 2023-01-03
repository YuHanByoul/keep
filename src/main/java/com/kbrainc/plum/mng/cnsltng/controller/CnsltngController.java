package com.kbrainc.plum.mng.cnsltng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.cnsltng.service.CnsltngService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.siteApply.model.SiteApplyVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 컨설팅 관리 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.mng.consult.controller 
 * - SiteApplyController.java
 * </pre> 
 *
 * @ClassName : ConsultController
 * @Description : 사이트관리 컨트롤러.
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping
@Slf4j
public class CnsltngController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CnsltngService cnsltngService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private InstService instService;
    
    /**
     * @Title : cnsltngList
     * @Description : 컨설팅 관리 화면 이동
     * @param 
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngList.html")
    public String cnsltngList(CnsltngVo cnsltngVo, Model model) throws Exception {
        return "mng/cnsltng/cnsltngList";
    }
    
    /**
     * @Title : selectCnsltngList
     * @Description : 컨설팅 리스트 가져오기
     * @param CnsltngVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/cnsltng/selectCnsltngList.do")
    public @ResponseBody Map<String, Object> selectCnsltngList(CnsltngVo cnsltngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CnsltngVo> result = new ArrayList<CnsltngVo>();
        
        result = cnsltngService.selectCnsltngList(cnsltngVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * @Title : cnsltngDetailForm
     * @Description : 상세화면 (탭)화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngDetailForm.html")
    public String cnsltngDetailForm(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("cnsltngVo", cnsltngVo);
        return "mng/cnsltng/cnsltngDetailForm";
    }
    
    /**
     * @Title : cnsltngApplyInfo
     * @Description : 컨설팅 신청정보 상세화면 화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngApplyInfo.html")
    public String cnsltngApplyInfo(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        
        cnsltngVo.setUser(user);
        CnsltngVo resVo = new CnsltngVo();
        resVo = cnsltngService.selectCnsltngtInfo(cnsltngVo);
        model.addAttribute("cnsltngVo", resVo);
        
        InstVo instVo = new InstVo();
        instVo.setInstid(resVo.getInstid());
        model.addAttribute("instVo", instService.selectInstInfo(instVo));
        
        if (resVo.getFilegrpid() != null && !resVo.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(resVo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileList",fileList );
        } else {
            model.addAttribute("fileList", null);
        }
        
        return "mng/cnsltng/cnsltngInfoForm";
    }
    /**
     * @Title : cnsltngApplyInfo
     * @Description : 컨설팅 신청정보 상세화면 화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/consultResult.html")
    public String consultResult(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        
        cnsltngVo.setUser(user);
        return "mng/cnsltng/cnsltngResultForm";
        
    }
    /**
     * @Title : cnsltngApplyInfo
     * @Description : 컨설팅 신청정보 상세화면 화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngEval.html")
    public String cnsltngEval(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        
        cnsltngVo.setUser(user);
        

        
        //SiteApplyVo result = siteApplyService.selectSiteApplyInfo(siteApplyVo);
        //List<SiteApplyMenuVo> menuList = siteApplyService.selectSiteApplyMenuList(siteApplyVo);
        //model.addAttribute("siteApplyMenuList", menuList);
        //model.addAttribute("siteApplyVo", result);
        return "mng/cnsltng/cnsltngSurveyForm";
        
    }
    
    /**
     * @Title : updateSiteApplyStatus
     * @Description : 사이트 신청 상태 변경
     * @param SiteApplyVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/cnsltng/updateSiteApplyStatus.do")
    @ResponseBody
    public Map<String, Object> updateSiteApplyStatus(SiteApplyVo siteApplyVo, @UserInfo UserVo user) throws Exception {
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        int retVal = 0;
        siteApplyVo.setUser(user);
        //retVal = siteApplyService.updateSiteApplyStatus(siteApplyVo);
        
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "상태 변경에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "상태 변경에 실패했습니다.");
        }
        return map;
    }
    
}
