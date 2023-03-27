package com.kbrainc.plum.front.clclnMng.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.clclnMng.model.ClclnDsctnVo;
import com.kbrainc.plum.front.clclnMng.model.ClclnVo;
import com.kbrainc.plum.front.clclnMng.service.ClclnService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산관리 컨트롤러 클래스 
*
* <pre>
* com.kbrainc.plum.front.clclnMng.controller
* - ClclnController.java
* </pre> 
*
* @ClassName : ClclnController
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class ClclnController {
    
    @Autowired
    private ClclnService clclnService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 정산관리 리스트화면으로 이동
    *
    * @Title : clclnMngListForm
    * @Description : 정산관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/clclnMng/clclnMngListForm.html")
    public String clclnMngListForm(Model model) throws Exception {

        return "front/clclnMng/clclnMngList";
    }
    
    /**
    * 정산관리 목록 조회. 
    *
    * @Title : selectClclnMngList
    * @Description : TODO
    * @param clclnVo
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/clclnMng/selectClclnMngList.do")
    @ResponseBody
    public Map<String, Object> selectClclnMngList(ClclnVo clclnVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ClclnVo> result = null;
        clclnVo.setUser(user);
        result =  clclnService.selectClclnList(clclnVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 정산관리 상세 탭 
    *
    * @Title : clclnMngTabForm
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnMng/clclnMngTabForm.html")
    public String clclnMngTabForm(ClclnVo clclnVo, Model model) throws Exception {
        model.addAttribute("clclnVo", clclnVo);
        return "front/clclnMng/clclnMngTab";
    }

    /**
    * 정산내역관리 탭. 
    *
    * @Title : clclnDsctnForm
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnMng/clclnDsctnForm.html")
    public String clclnDsctnForm(ClclnVo clclnVo, Model model, @UserInfo UserVo user) throws Exception {
        clclnVo.setUser(user);
        List<ClclnVo> result = clclnService.selectClclnList(clclnVo);
        ClclnVo detail = null;
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
        } else {
            detail = new ClclnVo();
        }
        
        model.addAttribute("clclnVo", clclnVo);
        model.addAttribute("detail", detail);
        model.addAttribute("clclnDsctnList", clclnService.selectClclnDsctnList(clclnVo));
        
        return "front/clclnMng/clclnDsctn";
    }
    
    /**
    * 잔액/이자관리 탭. 
    *
    * @Title : blncIntForm
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnMng/blncIntForm.html")
    public String blncIntForm(ClclnVo clclnVo, Model model, @UserInfo UserVo user) throws Exception {
        clclnVo.setUser(user);
        List<ClclnVo> result = clclnService.selectClclnList(clclnVo);
        ClclnVo detail = null;
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
        } else {
            detail = new ClclnVo();
        }
        
        model.addAttribute("clclnVo", clclnVo);
        model.addAttribute("detail", detail);
        model.addAttribute("list", clclnService.selectBlncIntList(clclnVo));
        
        return "front/clclnMng/blncInt";
    }
    
    /**
    * 정산내역관리 탭 집행 목록 조회. 
    *
    * @Title : selectClclnDsctnList
    * @Description : TODO
    * @param clclnVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/clclnMng/selectClclnDsctnList.do")
    @ResponseBody
    public Map<String, Object> selectClclnDsctnList(ClclnVo clclnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", clclnService.selectClclnDsctnList(clclnVo));
        return resultMap;
    }

    /**
    * 정산내역관리 탭 집행내역 개요서 조회. 
    *
    * @Title : selectClclnDsctnList
    * @Description : TODO
    * @param clclnVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/clclnMng/selectClclnDsctnOutlList.do")
    @ResponseBody
    public Map<String, Object> selectClclnDsctnOutlList(ClclnVo clclnVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", clclnService.selectClclnDsctnOutlList(clclnVo));
        return resultMap;
    }
    
    /**
    * 집행내역 팝업. 
    *
    * @Title : clclnDsctnPopup
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnMng/clclnDsctnPopup.html")
    public String clclnDsctnPopup(ClclnVo clclnVo, Model model) throws Exception {
        List<ClclnDsctnVo> result = clclnService.selectClclnDsctnList(clclnVo);
        ClclnDsctnVo detail = null;
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
            
            if (!StringUtil.nvl(detail.getAtchFilegrpid()).equals("") && !StringUtil.nvl(detail.getAtchFilegrpid()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detail.getAtchFilegrpid());
                model.addAttribute("fileList", fileService.getFileList(fileVo));    //파일목록
            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }
        } else {
            detail = new ClclnDsctnVo();
        }
        
        model.addAttribute("aplyid", clclnVo.getAplyid());
        model.addAttribute("seCd", clclnVo.getSeCd());
        model.addAttribute("fldCd", clclnVo.getFldCd());
        model.addAttribute("detail", detail);
        
        return "front/clclnMng/clclnDsctnPopup";
    }
    
    /**
    * 집행내역 저장. 
    *
    * @Title : saveClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/front/clclnMng/saveClclnDsctn.do")
    @ResponseBody
    public Map<String, Object> saveClclnDsctn(@Valid ClclnDsctnVo clclnDsctnVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        clclnDsctnVo.setUser(user);
        
        int retVal = clclnService.saveClclnDsctn(clclnDsctnVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }

    /**
    * 집행내역 삭제.
    * 
    * @Title : saveClclnDsctn
    * @Description : TODO
    * @param clclnDsctnVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/front/clclnMng/deleteClclnDsctn.do")
    @ResponseBody
    public Map<String, Object> deleteClclnDsctn(@Valid ClclnDsctnVo clclnDsctnVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        clclnDsctnVo.setUser(user);
        
        int retVal = clclnService.deleteClclnDsctn(clclnDsctnVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 뱅크다 팝업. 
    *
    * @Title : bankPopup
    * @Description : TODO
    * @param clclnVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/clclnMng/bankPopup.html")
    public String bankPopup(ClclnVo clclnVo, Model model) throws Exception {
        model.addAttribute("aplyid", clclnVo.getAplyid());
        return "front/clclnMng/bankPopup";
    }
    
    /**
    * 집행내역개요서 엑셀 다운로드. 
    *
    * @Title : clclnDsctnOutlExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param clclnDsctnVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/front/clclnMng/pcntst/clclnDsctnOutlExcelDownload.do")
    public void clclnDsctnOutlExcelDownload(HttpServletRequest request, HttpServletResponse response, ClclnVo clclnVo) throws Exception {
        clclnService.clclnDsctnOutlExcelDownload(clclnVo, response, request);
    }
}
