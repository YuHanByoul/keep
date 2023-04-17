package com.kbrainc.plum.front.jntpurchs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.jntpurchs.model.JntpurchsRvwVo;
import com.kbrainc.plum.front.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.front.jntpurchs.service.JntpurchsServiceImpl;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
 * 환경교육 교구 공동구매 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.controller
 * - JntpurchsController.java
 * </pre>
 *
 * @ClassName : JntpurchsController
 * @Description : 환경교육 교구 공동구매 Controller
 * @author : KBRAINC
 * @date : 2023. 02. 17.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.JntpurchsController")
@Alias("front.JntpurchsController")
public class JntpurchsController {

    @Resource(name = "front.JntpurchsServiceImpl")
    private JntpurchsServiceImpl jntpurchsService;
    
    @Autowired
    private FileServiceImpl fileService;
    
    /**
    * 환경교육 교구 공동구매 목록 화면
    *
    * @Title : jntpurchsListForm
    * @Description : 환경교육 교구 공동구매 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsListForm.html")
    public String jntpurchsListForm(JntpurchsVo jntpurchsVo, Model model) throws Exception {
        Map<String, Object> searchInfo = new HashMap<String, Object>();
        searchInfo.put("searchJntpurchsNm", jntpurchsVo.getSearchJntpurchsNm());
        searchInfo.put("searchSttsCd", jntpurchsVo.getSearchSttsCd());
        searchInfo.put("pageNumber", jntpurchsVo.getPageNumber());
        model.addAttribute("searchInfo", searchInfo);
        
        return "front/jntpurchs/jntpurchsList";
    }
    
    /**
    * 환경교육 교구 공동구매 상세 화면
    *
    * @Title : jntpurchsDetailForm
    * @Description : 환경교육 교구 공동구매 상세 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsDetailForm.html")
    public String jntpurchsDetailForm(JntpurchsVo jntpurchsVo, Model model) throws Exception {
         // 공동구매모집 상세정보
         JntpurchsVo jntpurchsInfo = jntpurchsService.selectJntpurchsInfo(jntpurchsVo);
         model.addAttribute("jntpurchsInfo", jntpurchsInfo);
         // 공동구매모집 파일 정보
         FileVo fileVo = new FileVo();
         if(jntpurchsInfo.getRprsImgFileid() != null && jntpurchsInfo.getRprsImgFileid() != 0) {
             fileVo.setFileid(jntpurchsInfo.getRprsImgFileid());
             FileVo rprsImgFileInfo = fileService.getFileInfo(fileVo);
             model.addAttribute("rprsImgFileInfo", rprsImgFileInfo);
         }
         if(jntpurchsInfo.getDtlImgFilegrpid() != null && jntpurchsInfo.getDtlImgFilegrpid() != 0) {
             fileVo.setFilegrpid(jntpurchsInfo.getDtlImgFilegrpid());
             ArrayList<FileVo> dtlImgFileList = fileService.getFileList(fileVo);
             model.addAttribute("dtlImgFileList", dtlImgFileList);
         }
         if(jntpurchsInfo.getMapFilegrpid() != null && jntpurchsInfo.getMapFilegrpid() != 0) {
             fileVo.setFileid(jntpurchsInfo.getMapFilegrpid());
             FileVo mapFileInfo = fileService.getFileInfo(fileVo);
             model.addAttribute("mapFileInfo", mapFileInfo);
         }
         if(jntpurchsInfo.getEduPhotoFilegrpid() != null && jntpurchsInfo.getEduPhotoFilegrpid() != 0) {
             fileVo.setFilegrpid(jntpurchsInfo.getEduPhotoFilegrpid());
             ArrayList<FileVo> eduPhotoFileList = fileService.getFileList(fileVo);
             model.addAttribute("eduPhotoFileList", eduPhotoFileList);
         }
         
         // 수량별 가격정보 목록
         JntpurchsAmtVo jntpurchsAmtVo = new JntpurchsAmtVo();
         jntpurchsAmtVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
         model.addAttribute("amtList", jntpurchsService.selectAmtList(jntpurchsAmtVo));
         // 상품 목록
         JntpurchsTchaidVo jntpurchsTchaidVo = new JntpurchsTchaidVo();
         jntpurchsTchaidVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
         model.addAttribute("goodsList", jntpurchsService.selectGoodsList(jntpurchsTchaidVo));
         // 후기 목록
         JntpurchsRvwVo jntpurchsRvwVo = new JntpurchsRvwVo();
         jntpurchsRvwVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
         model.addAttribute("rvwList", jntpurchsService.selectRvwList(jntpurchsRvwVo));
         
         return "/front/jntpurchs/jntpurchsDetail";
     }
     
    /**
    * 환경교육 교구 공동구매 신청 완료 화면
    *
    * @Title : jntpurchsListForm
    * @Description : 환경교육 교구 공동구매 신청 완료 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsOrderComplete.html")
    public String jntpurchsOrderComplete() throws Exception {
        return "front/jntpurchs/jntpurchsOrderComplete";
    }
    
    /**
    * 공동구매 신청 이력 화면
    *
    * @Title : jntpurchsOrderHstryListForm
    * @Description : 공동구매 신청 이력 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsOrderHstryListForm.html")
    public String jntpurchsOrderHstryListForm() throws Exception {
        return "front/jntpurchs/jntpurchsOrderHstryList";
    }
    
    /**
    * 공동구매 신청 상세 화면
    *
    * @Title : jntpurchsOrderHstryDetailForm
    * @Description : 공동구매 신청 상세 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsOrderHstryDetailForm.html")
    public String jntpurchsOrderHstryDetailForm(JntpurchsOrderVo jntpurchsOrderVo, Model model) throws Exception {
        JntpurchsOrderVo orderInfo = jntpurchsService.selectJntpurchsOrderInfo(jntpurchsOrderVo);
        model.addAttribute("orderInfo", orderInfo);
        // 상품 목록
        JntpurchsTchaidVo jntpurchsTchaidVo = new JntpurchsTchaidVo();
        jntpurchsTchaidVo.setJntpurchsid(orderInfo.getJntpurchsid());
        model.addAttribute("goodsList", jntpurchsService.selectGoodsList(jntpurchsTchaidVo));
        
        return "front/jntpurchs/jntpurchsOrderHstryDetail";
    }
    
    /**
    * 후기 등록 팝업
    *
    * @Title : rvwInsertPopup
    * @Description : 후기 등록 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/rvwInsertPopup.html")
    public String rvwInsertPopup(Model model) throws Exception {
        Map<String, Object> fileConfig = fileService.getConfigurationByFilegrpName("jntpurchsRvwfile");
        String uploadFileExtsn = ((HashMap<String, String>) fileConfig.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnName -> "." + fileExtsnName.getValue())
                .collect(Collectors.joining(", "));
         
        model.addAttribute("fileConfig", fileConfig);
        model.addAttribute("acceptUploadFileExt", uploadFileExtsn);
         
         
        return "front/jntpurchs/rvwInsertPopup";
    }
     
    /**
    * 후기 정보 팝업
    *
    * @Title : rvwUpdatePopup
    * @Description : 후기 정보 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/rvwUpdatePopup.html")
    public String rvwUpdatePopup(JntpurchsOrderVo jntpurchsOrderVo, Model model) throws Exception {
        Map<String, Object> fileConfig = fileService.getConfigurationByFilegrpName("jntpurchsRvwfile");
        String uploadFileExtsn = ((HashMap<String, String>) fileConfig.get("uploadFileExtsn"))
                .entrySet()
                .stream()
                .map(fileExtsnName -> "." + fileExtsnName.getValue())
                .collect(Collectors.joining(", "));
          
        JntpurchsOrderVo rvwInfo = jntpurchsService.selectJntpurchsOrderRvwInfo(jntpurchsOrderVo);
        model.addAttribute("rvwInfo", rvwInfo);
        if(rvwInfo.getRvwFilegrpid() != null && rvwInfo.getRvwFilegrpid() != 0) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(rvwInfo.getRvwFilegrpid());
            ArrayList<FileVo> rvwFileList = fileService.getFileList(fileVo);
            model.addAttribute("rvwFileList", rvwFileList);
        }
          
        return "front/jntpurchs/rvwUpdatePopup";
    }
      
    
    /**
    * 환경교육 교구 공동구매 목록 조회
    *
    * @Title : selectJntpurchsList
    * @Description : 환경교육 교구 공동구매 목록 조회
    * @param jntpurchsVo JntpurchsVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value="/front/jntpurchs/selectJntpurchsList.do")
    @ResponseBody
    public Map<String, Object> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<JntpurchsVo> result = null;
        
        result = jntpurchsService.selectJntpurchsList(jntpurchsVo);
        
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
     * 공동구매 신청 회원 정보 조회
     *
     * @Title : selectUserInfo
     * @Description : 공동구매신청 목록 조회
     * @param memberVo MemberVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/jntpurchs/selectUserInfo.do")
    @ResponseBody
    public Map<String, Object> selectUserInfo(MemberVo memberVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        memberVo.setUser(user);
        MemberVo result = jntpurchsService.selectUserInfo(memberVo);
        resultMap.put("userInfo", result);
        
        return resultMap;
    }
    
    /**
    * 공동구매 신청 등록
    *
    * @Title : insertJntpurchsOrder
    * @Description : 공동구매 신청 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @param bindingResult jntpurchsOrderVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/insertJntpurchsOrder.do")
    @ResponseBody
    public Map<String, Object> insertJntpurchsOrder(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsOrderVo.setUser(user);
        retVal = jntpurchsService.insertJntpurchsOrder(jntpurchsOrderVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "신청이 완료되었습니다");
        } else if(retVal == -1) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "잔여수량이 부족합니다");
        } else if(retVal == -2) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "1인당 신청 가능 수량을 초과하여 신청할 수 없습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "신청에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 환경교육 교구 관리 공동구매 신청 이력 목록 조회
    *
    * @Title : selectjntpurchsOrderHstryList
    * @Description : 환경교육 교구 관리 공동구매 신청 이력 목록 조회
    * @param jntpurchsVo JntpurchsOrderVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value="/front/jntpurchs/selectJntpurchsOrderHstryList.do")
    @ResponseBody
    public Map<String, Object> selectjntpurchsOrderHstryList(JntpurchsOrderVo jntpurchsOrderVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<JntpurchsOrderVo> result = null;
        
        jntpurchsOrderVo.setUser(user);
        result = jntpurchsService.selectjntpurchsOrderHstryList(jntpurchsOrderVo);
        
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
    * 공동구매 신청 취소
    *
    * @Title : deleteJntpurchsOrder
    * @Description : 공동구매 신청 취소
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @param bindingResult jntpurchsOrderVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/deleteJntpurchsOrder.do")
    @ResponseBody
    public Map<String, Object> deleteJntpurchsOrder(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsOrderVo.setUser(user);
        retVal = jntpurchsService.deleteJntpurchsOrder(jntpurchsOrderVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "신청취소가 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "신청취소가 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 공동구매 후기 등록
    *
    * @Title : insertJntpurchsOrderRvw
    * @Description : 공동구매 후기 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @param bindingResult jntpurchsOrderVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/insertJntpurchsOrderRvw.do")
    @ResponseBody
    public Map<String, Object> insertJntpurchsOrderRvw(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsOrderVo.setUser(user);
        retVal = jntpurchsService.insertJntpurchsOrderRvw(jntpurchsOrderVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "후기등록이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "후기등록에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 공동구매 후기 삭제
    *
    * @Title : deleteJntpurchsOrderRvw
    * @Description : 공동구매 후기 삭제
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @param bindingResult jntpurchsOrderVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/deleteJntpurchsOrderRvw.do")
    @ResponseBody
    public Map<String, Object> deleteJntpurchsOrderRvw(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsOrderVo.setUser(user);
        retVal = jntpurchsService.deleteJntpurchsOrderRvw(jntpurchsOrderVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "후기삭제가 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "후기삭제가 실패하였습니다");
        }
              
        return resultMap;
    }
    
}