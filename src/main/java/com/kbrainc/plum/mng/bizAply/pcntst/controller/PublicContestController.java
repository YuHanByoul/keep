/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestVo;
import com.kbrainc.plum.mng.bizAply.pcntst.service.PublicContestService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.pcntst.controller
* - PublicContestController.java
* </pre> 
*
* @ClassName : PublicContestController
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class PublicContestController {

    @Autowired
    private PublicContestService publicContestService;
    
    @Autowired
    private FileService fileService;
    
    
    /**
    * 공모관리 화면으로 이동. 
    *
    * @Title : publicContestListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/pcntst/publicContestListForm.html")
    public String publicContestListForm() throws Exception {
        return "mng/bizAply/pcntst/publicContestList";
    }
    
    /**
    * 공모관리 리스트 조회. 
    *
    * @Title : selectPublicContestList
    * @Description : TODO
    * @param publicContestVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/pcntst/selectPublicContestList.do")
    @ResponseBody
    public Map<String, Object> selectPublicContestList(PublicContestVo publicContestVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<PublicContestVo> result = this.publicContestService.selectContestList(publicContestVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", result.get(0).getTotalCount());
            resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }

    /**
    * 공모관리 상세 조회. 
    *
    * @Title : detailPublicContestForm
    * @Description : TODO
    * @param publicContestVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/pcntst/detailPublicContestForm.html")
    public String detailPublicContestForm(PublicContestVo publicContestVo, Model model) throws Exception {
        
        PublicContestVo detailPublicContestVo = new PublicContestVo();

        if (publicContestVo != null) {
            if (publicContestVo.getPcntstid() > 0) {
                List<PublicContestVo> result = this.publicContestService.selectContestList(publicContestVo);                         
                if (CollectionUtils.isNotEmpty(result)) {
                    detailPublicContestVo = result.get(0);
                }
            }
            
            if (!StringUtil.nvl(publicContestVo.getFilegrpid()).equals("") && !StringUtil.nvl(publicContestVo.getFilegrpid()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(publicContestVo.getFilegrpid());

                model.addAttribute("fileList", fileService.getFileList(fileVo));    //파일목록

            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }
        }
        
//        PublicContestVo detailPublicContestVo = this.publicContestService.detailContest(publicContestVo);
        model.addAttribute("publicContestVo", detailPublicContestVo);
        
        return "mng/bizAply/pcntst/detailPublicContestForm";
    }
    
    /**
    * 공모관리 등록. 
    *
    * @Title : insertPublicContest
    * @Description : TODO
    * @param publicContestVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/pcntst/insertPublicContest.do")
    @ResponseBody
    public Map<String, Object> insertPublicContest(@Valid PublicContestVo publicContestVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        publicContestVo.setUser(user);
        
        int retVal = this.publicContestService.insertContest(publicContestVo);
        
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
    * 공모관리 수정. 
    *
    * @Title : updatePublicContest
    * @Description : TODO
    * @param publicContestVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/pcntst/updatePublicContest.do")
    @ResponseBody
    public Map<String, Object> updatePublicContest(@Valid PublicContestVo publicContestVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        publicContestVo.setUser(user);
        
        int retVal = this.publicContestService.updateContest(publicContestVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    @RequestMapping(value="/mng/bizAply/pcntst/managerSearchPopup.html")
    public String managerSearchPopup() throws Exception {
        return "mng/bizAply/pcntst/managerSearchPopup";
    }
    
    /**
    * 공모관리 삭제. 
    *
    * @Title : deletePublicContest
    * @Description : TODO
    * @param deleteContestIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/pcntst/deletePublicContest.do")
    @ResponseBody
    public Map<String, Object> deletePublicContest(@RequestParam("deleteContestIds") Integer[] deleteContestIds, @UserInfo UserVo user) throws Exception {
       Map<String, Object> resultMap = new HashMap<String, Object>();
       
       int retVal = this.publicContestService.deleteContest(deleteContestIds);
       
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
