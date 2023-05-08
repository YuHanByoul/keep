/**
 * 
 */
package com.kbrainc.plum.mng.envEdu.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.kbrainc.plum.mng.envEdu.model.PrgrmMngVo;
import com.kbrainc.plum.mng.envEdu.service.PrgrmMngService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 우수환경교육 프로그램 관리 컨트롤러. 
*
* <pre>
* com.kbrainc.plum.mng.envEdu.controller
* - PrgrmMngController.java
* </pre> 
*
* @ClassName : PrgrmMngController
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 3.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class PrgrmMngController {

    @Autowired
    private PrgrmMngService prgrmMngService;
    
    @Autowired
    private FileService fileService;
    
    /**
     * 우수환경교육 프로그램 관리 화면으로 이동. 
     *
     * @Title : reqMngListForm
     * @Description : TODO
     * @return
     * @throws Exception
     * @return String
      */
     @RequestMapping(value="/mng/envEdu/prgrmMngListForm.html")
     public String prgrmMngListForm() throws Exception {
         return "mng/envEdu/prgrmMngList";
     }
     
     /**
     * 우수환경교육 프로그램 관리 리스트 조회. 
     *
     * @Title : selectPrgrmMngList
     * @Description : TODO
     * @param prgrmMngVo
     * @return
     * @throws Exception
     * @return Map<String,Object>
      */
     @RequestMapping(value="/mng/envEdu/selectPrgrmMngList.do")
     @ResponseBody
     public Map<String, Object> selectPrgrmMngList(PrgrmMngVo prgrmMngVo) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         List<PrgrmMngVo> result = this.prgrmMngService.selectPrgrmMngList(prgrmMngVo);
         
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
      * 우수환경교육 프로그램 관리 상세 조회. 
      *
      * @Title : detailPrgrmMngForm
      * @Description : TODO
      * @param prgrmMngVo
      * @param model
      * @return
      * @throws Exception
      * @return String
       */
      @RequestMapping(value="/mng/envEdu/detailPrgrmMngForm.html")
      public String detailPrgrmMngForm(PrgrmMngVo prgrmMngVo, Model model) throws Exception {
          
          PrgrmMngVo detailPrgrmMngVo = new PrgrmMngVo();

          if (prgrmMngVo != null) {
              if (prgrmMngVo.getPrgrmid() > 0) {
                  List<PrgrmMngVo> result = this.prgrmMngService.selectPrgrmMngList(prgrmMngVo);                         
                  if (CollectionUtils.isNotEmpty(result)) {
                      detailPrgrmMngVo = result.get(0);
                      // 조회수 증가
                      this.prgrmMngService.updateHits(detailPrgrmMngVo);
                  }
                  
                  if (null != detailPrgrmMngVo) {
                      if (!StringUtil.nvl(detailPrgrmMngVo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(detailPrgrmMngVo.getAtchFilegrpid()).equals(0)) {
                          FileVo fileVo = new FileVo();
                          fileVo.setFilegrpid(detailPrgrmMngVo.getAtchFilegrpid());
                          
                          model.addAttribute("atchFileList", fileService.getFileList(fileVo));    //파일목록
                          
                      } else {
                          model.addAttribute("atchFileList", Collections.emptyList());
                      }
                      
                      if(detailPrgrmMngVo.getRprsImgFileid() != 0 && detailPrgrmMngVo.getFileIdntfcKey() != null) {
                          String fileBtn = "";
                          fileBtn += "<div class ='label label-inverse text-white' id='" +detailPrgrmMngVo.getRprsImgFileid() + "'>";
                          fileBtn += "<a href=javascript:downloadFileByFileid('" + detailPrgrmMngVo.getRprsImgFileid() + "','" + detailPrgrmMngVo.getFileIdntfcKey() + "') class='text-white'>" + detailPrgrmMngVo.getOrginlFileNm() + "&nbsp;&nbsp;</a>";
                          fileBtn += "<a href=javascript:fn_deleteMainFile('" + detailPrgrmMngVo.getRprsImgFileid() + "','" + detailPrgrmMngVo.getFileIdntfcKey() + "') class='text-white'>X</a></div>";
                          model.addAttribute("fileBtn", fileBtn);
                      }
                  }
              }
          }
          
          model.addAttribute("prgrmMngVo", detailPrgrmMngVo);
          
          return "mng/envEdu/detailPrgrmMngForm";
      }
      
      /**
      * 우수환경교육 프로그램 관리 등록. 
      *
      * @Title : insertPrgrmMng
      * @Description : TODO
      * @param prgrmMngVo
      * @param bindingResult
      * @param user
      * @return
      * @throws Exception
      * @return Map<String,Object>
       */
      @RequestMapping(value="/mng/envEdu/insertPrgrmMng.do")
      @ResponseBody
      public Map<String, Object> insertPrgrmMng(@Valid PrgrmMngVo prgrmMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
          Map<String, Object> resultMap = new HashMap<String, Object>();
          
          if (bindingResult.hasErrors()) {
              FieldError fieldError = bindingResult.getFieldError();
              if (fieldError != null) {
                  resultMap.put("msg", fieldError.getDefaultMessage());
              }
              return resultMap;
          }
          
          prgrmMngVo.setUser(user);
          
          int retVal = this.prgrmMngService.insertPrgrmMng(prgrmMngVo);
          
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
      * 우수환경교육 프로그램 관리 수정. 
      *
      * @Title : updatePrgrmMng
      * @Description : TODO
      * @param prgrmMngVo
      * @param bindingResult
      * @param user
      * @return
      * @throws Exception
      * @return Map<String,Object>
       */
      @RequestMapping(value="/mng/envEdu/updatePrgrmMng.do")
      @ResponseBody
      public Map<String, Object> updatePrgrmMng(@Valid PrgrmMngVo prgrmMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
          Map<String, Object> resultMap = new HashMap<String, Object>();
          
          if (bindingResult.hasErrors()) {
              FieldError fieldError = bindingResult.getFieldError();
              if (fieldError != null) {
                  resultMap.put("msg", fieldError.getDefaultMessage());
              }
              return resultMap;
          }
          
          prgrmMngVo.setUser(user);
          
          int retVal = this.prgrmMngService.updatePrgrmMng(prgrmMngVo);
          
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
      * 우수환경교육 프로그램 관리 삭제. 
      *
      * @Title : deletePrgrmMng
      * @Description : TODO
      * @param request
      * @return
      * @throws Exception
      * @return Map<String,Object>
       */
      @RequestMapping(value = "/mng/envEdu/deletePrgrmMng.do")
      @ResponseBody
      public Map<String, Object> deletePrgrmMng(HttpServletRequest request) throws Exception {
          Map<String, Object> resultMap = new HashMap<String, Object>();
          int retVal = 0;
           
          String[] prgrmids = request.getParameterValues("prgrmids");
           
          retVal = this.prgrmMngService.deletePrgrmMng(prgrmids);
           
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
