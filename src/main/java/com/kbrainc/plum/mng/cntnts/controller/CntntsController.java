package com.kbrainc.plum.mng.cntnts.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.cntnts.model.CntntsVo;
import com.kbrainc.plum.mng.cntnts.service.CntntsService;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 컨텐츠 관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.cntnts.controller
* - CntntsController.java
* </pre>
*
* @ClassName : CntntsController
* @Description : 컨텐츠 관리 컨트롤러 클래스
* @author : JD
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CntntsController {

    @Autowired
    private CntntsService cntntsService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 컨텐츠관리 목록화면 이동
    *
    * @Title : cntntsListForm
    * @Description : 컨텐츠관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/cntnts/cntntsListForm.html")
    public String cntntsListForm() throws Exception {

        return "/mng/cntnts/cntntsList";
    }
    
    /**
    * 컨텐츠 관리 게시글 등록화면 이동
    *
    * @Title : cntntsInsertForm
    * @Description : 컨텐츠 관리 게시글 등록화면 이동
    * @param model 객체
    * @param user 사용자세션 정보
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/cntnts/cntntsInsertForm.html")
    public String cntntsInsertForm(Model model, @UserInfo UserVo user) throws Exception {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        model.addAttribute("regDate", formatter.format(date));
        
        model.addAttribute("userid", user.getUserid());
        model.addAttribute("useridNm", user.getAcnt()+"("+user.getNm()+")");
        
        return "/mng/cntnts/cntntsInsertForm";
    }
    
    /**
    * 컨텐츠 관리 게시글 수정화면 이동
    *
    * @Title : cntntsUpdateForm
    * @Description : 컨텐츠 관리 게시글 수정화면 이동
    * @param model 객체
    * @param cntntsVo 객체
    * @param user 사용자세션 정보
    * @throws Exception 에외
    * @return String
    */
    @RequestMapping(value = "/mng/cntnts/cntntsUpdateForm.html")
    public String cntntsUpdateForm(Model model, CntntsVo cntntsVo, @UserInfo UserVo user) throws Exception {
        CntntsVo result = null;
        result =  cntntsService.selectCntntsInfo(cntntsVo);
        
        if(result.getPlySecnd() != null) {
            result.setPlyMinute( (result.getPlySecnd() - (result.getPlySecnd() % 60)) / 60 );
            result.setPlySecnd( result.getPlySecnd() - (result.getPlyMinute() * 60) );
        }
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        if (result.getAtchFilegrpid() != null && !result.getAtchFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(result.getAtchFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileMap",fileList );
            model.addAttribute("currentFileCnt", fileList.size());
        } else {
            model.addAttribute("fileMap", null);
            model.addAttribute("currentFileCnt", 0);
        }
        
        model.addAttribute("cntnts", result);
        
        if(cntntsVo.getRprsImgFileid() != 0 && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            fileBtn.append("<div class ='label label-inverse text-white' id='" + cntntsVo.getRprsImgFileid() + "'>");
            fileBtn.append("<a href=javascript:downloadFileByFileid('" + cntntsVo.getRprsImgFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
            fileBtn.append("<a href=javascript:fn_deleteFileList('" + cntntsVo.getRprsImgFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("fileBtn", fileBtn);
        }
        
        
        return "/mng/cntnts/cntntsUpdate";
    }
    
    /**
    * 컨텐츠 관리 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cntnts/selectCntntsList.do")
    @ResponseBody
    public Map<String, Object> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CntntsVo> result = null;
        
        result =  cntntsService.selectCntntsList(cntntsVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 컨텐츠 관리 게시글 등록
    *
    * @Title : insertCntnts
    * @Description : 컨텐츠 관리 게시글 등록
    * @param cntntsVo 객체
    * @param bindingResult 유효성검증 결과
    * @param user 사용자세션 정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cntnts/insertCntnts.do")
    @ResponseBody
    public Map<String, Object> insertCntnts(@Valid CntntsVo cntntsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        cntntsVo.setUser(user);
        
        if(cntntsVo.getPlyMinute() != null && cntntsVo.getPlySecnd() != null) {
            int playTime = cntntsVo.getPlyMinute() * 60 + cntntsVo.getPlySecnd();
            cntntsVo.setPlySecnd(playTime);    
        }else if(cntntsVo.getPlyMinute() != null && cntntsVo.getPlySecnd() == null) {
            int playTime = cntntsVo.getPlyMinute() * 60;
            cntntsVo.setPlySecnd(playTime);
        }
        
        int retVal = 0;
                
        retVal = cntntsService.insertCntnts(cntntsVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
         return resultMap;
    }
    
    /**
    * 컨텐츠 관리 게시글 수정
    *
    * @Title : updateCntnts
    * @Description : 컨텐츠 관리 게시글 수정
    * @param cntntsVo 객체
    * @param bindingResult 유효성검증 결과
    * @param user 사용자세션 정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cntnts/updateCntnts.do")
    @ResponseBody
    public Map<String, Object> updateCntnts(@Valid CntntsVo cntntsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        cntntsVo.setUser(user);
        
        if(cntntsVo.getPlyMinute() != null && cntntsVo.getPlySecnd() != null) {
            int playTime = cntntsVo.getPlyMinute() * 60 + cntntsVo.getPlySecnd();
            cntntsVo.setPlySecnd(playTime);    
        }else if(cntntsVo.getPlyMinute() != null && cntntsVo.getPlySecnd() == null) {
            int playTime = cntntsVo.getPlyMinute() * 60;
            cntntsVo.setPlySecnd(playTime);
        }

        int retVal = 0;
                
        retVal = cntntsService.updateCntnts(cntntsVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }

        return resultMap;
    }
    
    /**
    * 컨텐츠 관리 게시글 삭제
    *
    * @Title : deleteCntnts
    * @Description : 컨텐츠 관리 게시글 삭제
    * @param request 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/cntnts/deleteCntnts.do")
    @ResponseBody
    public Map<String, Object> deleteCntnts(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        String[] cntntsids = request.getParameterValues("cntntsids");
        
        retVal = cntntsService.deleteCntnts(cntntsids);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패했습니다.");
        }

        return resultMap;
    }
}
