package com.kbrainc.plum.front.jntpurchs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kbrainc.plum.cmm.file.service.FileService;
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
    private FileService fileService;
    
    /**
    * 환경교육 교구 공동구매 목록 화면
    *
    * @Title : jntpurchsListForm
    * @Description : 환경교육 교구 공동구매 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/jntpurchs/jntpurchsListForm.html")
    public String jntpurchsListForm() throws Exception {
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
    @RequestMapping(value = "/mng/jntpurchs/selectUserInfo.do")
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
    @RequestMapping(value = "/mng/jntpurchs/insertJntpurchsOrder.do")
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
            resultMap.put("msg", "신청수량이 잔여수량보다 많아 신청할 수 없습니다");
        } else if(retVal == -2) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "1인당 신청 가능 수량을 초과하여 신청할 수 없습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "신청에 실패하였습니다");
        }
              
        return resultMap;
    }
    
}