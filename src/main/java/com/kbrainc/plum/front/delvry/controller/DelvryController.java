package com.kbrainc.plum.front.delvry.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyComputVo;
import com.kbrainc.plum.front.delvry.model.DelvryAplyVo;
import com.kbrainc.plum.front.delvry.service.DelvryService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
 * 교부관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.delvry.controller
 * - DelvryController.java
 * </pre>
 *
 * @ClassName : DelvryController
 * @Description : 교부관리 Controller
 * @author : KCS
 * @date : 2023. 03. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.delvryController")
@Alias("front.delvryController")
public class DelvryController {
    
    @Resource(name = "front.delvryService")
    private DelvryService delvryService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 교부 신청 목록 화면
    *
    * @Title : delvryAplyListForm
    * @Description : 교부 신청 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/delvry/delvryAplyListForm.html")
    public String delvryAplyListForm() throws Exception {
        return "front/delvry/delvryList";
    }
    
    /**
     * 교부신청 목록 조회
     *
     * @Title : selectDelvryAplyList
     * @Description : 교부신청 목록 조회
     * @param delvryAplyVo DelvryAplyVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/delvry/selectDelvryAplyList.do")
    @ResponseBody
    public Map<String, Object> selectDelvryAplyList(DelvryAplyVo delvryAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        delvryAplyVo.setUser(user);
        List<DelvryAplyVo> result = delvryService.selectDelvryAplyList(delvryAplyVo);
        
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 교부 신청 상세 화면
    *
    * @Title : delvryAplyDetailForm
    * @Description : 교부 신청 상세 화면
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/delvry/delvryAplyDetailForm.html")
    public String delvryAplyDetailForm(DelvryAplyVo delvryAplyVo, Model model, @UserInfo UserVo user) throws Exception {
        DelvryAplyVo detail = null;
        List<DelvryAplyVo> result = null;
        
        if (delvryAplyVo != null) {
            if (delvryAplyVo.getDelvryid() > 0) {
                delvryAplyVo.setUser(user);
                result = delvryService.selectDelvryAplyList(delvryAplyVo);                
            }
        }
        
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
            
            // 파일 정보
            FileVo fileVo = new FileVo();
            if(detail.getAtchFilegrpid() != null && detail.getAtchFilegrpid() != 0) {
                fileVo.setFilegrpid(detail.getAtchFilegrpid());
                ArrayList<FileVo> fileList = fileService.getFileList(fileVo);
                model.addAttribute("fileList", fileList);
            }
        }
        
        model.addAttribute("detail", detail == null ? new DelvryAplyVo() : detail);
        
        List<DelvryAplyComputVo> computList = delvryService.selectDelvryAplyComputList(delvryAplyVo);
        model.addAttribute("computList", computList);
        
        return "front/delvry/delvryAplyDetail";
    }
    
    /**
     * 교부 신청 정보 저장
     *
     * @Title : saveDelvryAply
     * @Description : 교부 신청 정보 저장
     * @param delvryAplyVo DelvryAplyVo 객체
     * @param bindingResult delvryAplyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/delvry/saveDelvryAply.do")
    @ResponseBody
    public Map<String, Object> saveDelvryAply(@Valid DelvryAplyVo delvryAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        delvryAplyVo.setUser(user);
        retVal = delvryService.saveDelvryAply(delvryAplyVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 보완요청 팝업
    *
    * @Title : delvryAplySplmntPopup
    * @Description : 보완요청 팝업
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/delvry/delvryAplySplmntPopup.html")
    public String delvryAplySplmntPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = null;
        if (supplementVo != null) {
            detail = this.delvryService.selectDelvryAplySplmntInfo(supplementVo);
            
            if (detail == null)
                detail = new SupplementVo();            
            
        }
        model.addAttribute("detail", detail);
        model.addAttribute("delvryid", supplementVo.getDelvryid());
        model.addAttribute("fldCd", supplementVo.getFldCd());
        return "front/delvry/detailSplmnt";
    }
    
    /**
     * 교부 신청 보완요청 업데이트
     *
     * @Title : updateDelvryAplySplmnt
     * @Description : 교부 신청 보완요청 업데이트
     * @param SupplementVo
     * @param bindingResult delvryAplySplmntVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/delvry/updateDelvryAplySplmnt.do")
    @ResponseBody
    public Map<String, Object> updateDelvryAplySplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        supplementVo.setUser(user);
        retVal = delvryService.updateDelvryAplySplmnt(supplementVo);
          
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "보완완료 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "보완완료에 실패했습니다.");
        }
              
        return resultMap;
    }
}
