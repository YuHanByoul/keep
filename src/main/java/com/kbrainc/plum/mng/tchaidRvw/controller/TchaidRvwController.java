package com.kbrainc.plum.mng.tchaidRvw.controller;

import java.util.ArrayList;
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
import com.kbrainc.plum.mng.tchaidRvw.model.TchaidRvwVo;
import com.kbrainc.plum.mng.tchaidRvw.service.TchaidRvwService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 교구후기관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.tchaidRvw.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : TchaidRvwController
* @Description : 교구후기관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class TchaidRvwController {
    
    @Autowired
    private TchaidRvwService tchaidRvwService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 교구후기관리 리스트화면으로 이동
    *
    * @Title : tchaidRvwForm
    * @Description : 교구후기관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/tchaidRvw/tchaidRvwListForm.html")
    public String tchaidRvwListForm() throws Exception {
        return "mng/tchaidRvw/tchaidRvwList";
    }
    
    /**
    * 교구후기관리 수정화면으로 이동
    *
    * @Title : tchaidRvwUpdateForm
    * @Description : 교구후기관리 수정화면으로 이동
    * @param tchaidRvwVo 교구후기관리 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/tchaidRvw/tchaidRvwUpdateForm.html")
    public String tchaidRvwUpdateForm(TchaidRvwVo tchaidRvwVo, Model model, @UserInfo UserVo user) throws Exception {
        TchaidRvwVo result = null;
        result = tchaidRvwService.selectTchaidRvwInfo(tchaidRvwVo);
        model.addAttribute("tchaidRvw", result);
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        if (result.getRvwFilegrpid() != null && !result.getRvwFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(result.getRvwFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileMap",fileList );
            model.addAttribute("currentFileCnt", fileList.size());
        } else {
            model.addAttribute("fileMap", null);
            model.addAttribute("currentFileCnt", 0);
        }
        
        if(tchaidRvwVo.getRvwFilegrpid() != null && result.getFileIdntfcKey() != null) {
            StringBuffer fileBtn = new StringBuffer();
            fileBtn.append("<div class ='label label-inverse text-white' id='" + tchaidRvwVo.getRvwFilegrpid() + "'>");
            fileBtn.append("<a href=javascript:downloadFileByFileid('" + tchaidRvwVo.getRvwFilegrpid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
            fileBtn.append("<a href=javascript:fn_deleteFileList('" + tchaidRvwVo.getRvwFilegrpid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("fileBtn", fileBtn);
        }
        
        return "mng/tchaidRvw/tchaidRvwUpdate";
    }
   
    /**
    * 교구후기관리 리스트 기능
    *
    * @Title : selectTchaidRvwList
    * @Description : 교구후기관리 리스트 기능
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/tchaidRvw/selectTchaidRvwList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidRvwList(TchaidRvwVo tchaidRvwVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<TchaidRvwVo> result = null;
        
        result =  tchaidRvwService.selectTchaidRvwList(tchaidRvwVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 교구후기관리 수정 기능
    *
    * @Title : updateTchaidRvw
    * @Description : 교구후기관리 수정 기능
    * @param tchaidRvwVo 교구후기관리 객체
    * @param bindingResult 교구후기관리 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/tchaidRvw/updateTchaidRvw.do")
    @ResponseBody
    public Map<String, Object> updateTchaidRvw(@Valid TchaidRvwVo tchaidRvwVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        tchaidRvwVo.setUser(user);

        int retVal = 0;
                
        retVal = tchaidRvwService.updateTchaidRvw(tchaidRvwVo);
        
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
    * 교구후기관리 삭제 기능
    *
    * @Title : deleteTchaidRvw
    * @Description : 교구후기관리 글 목록 삭제 기능
    * @param request 글 id값
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/tchaidRvw/deleteTchaidRvw.do")
    @ResponseBody
    public Map<String, Object> deleteTchaidRvw(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        String[] nscvrgids = request.getParameterValues("nscvrgids");
        
        retVal = tchaidRvwService.deleteTchaidRvw(nscvrgids);
        
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
