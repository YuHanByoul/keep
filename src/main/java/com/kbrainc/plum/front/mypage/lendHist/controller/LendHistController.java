package com.kbrainc.plum.front.mypage.lendHist.controller; 

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
import com.kbrainc.plum.front.lend.model.LendAplyTrgtVo;
import com.kbrainc.plum.front.lend.model.LendAplyVo;
import com.kbrainc.plum.front.lend.model.LendRndVo;
import com.kbrainc.plum.front.lend.model.LendVo;
import com.kbrainc.plum.front.lend.service.LendServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
 *  교구 대여(사용자) 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.lend.controller
 * - LendController.java
 * </pre> 
 *
 * @ClassName : LendController
 * @Description : 교구 대여(사용자) 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 03. 03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.lendHistController")
@Alias("front.lendHistController")
public class LendHistController {

    @Resource(name = "front.lendServiceImpl")
    private LendServiceImpl lendService;
    
    @Autowired
    private FileService fileService;
    
    
    /**
    * 교구 대여 이력 화면  
    *
    * @Title       : LendHistList 
    * @Description : 교구 대여 이력 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/mypage/lendHist/LendHistList.html")
    public String LendHistList(LendVo lendVo,Model model) throws Exception {
        model.addAttribute("params",lendVo);
        return "front/mypage/lendHist/lendHistList";
    }
    /**
     * @Title : selectLendHistList
     * @Description : 교구 대여 이력 목록 호출
     * @param LendAplyVo
     * @param user  로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/mypage/lendHist/selectLendHistList.do")
    @ResponseBody
    public Map<String, Object> selectLendList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<LendAplyVo> result = null;
        
        if(user!=null) {
            lendAplyVo.setUser(user);
        }
        
        result = lendService.selectLendHistList(lendAplyVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination",PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
    * 교구 대여 이력 상세 화면 
    *
    * @Title       : lendHistDetail 
    * @Description : 교구 대여 이력 상세 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/mypage/lendHist/lendHistDetail.html")
    public String lendHistDetail(LendAplyVo lendAplyVo,LendVo lendVo,Model model, @UserInfo UserVo user) throws Exception {
        
        model.addAttribute("params",lendVo);
        
        if(lendAplyVo.getAplyid()==null || lendAplyVo.getAplyid()==0) {
            return "redirect:/front/mypage/lendHist/LendHistList.html";
        }
        lendAplyVo.setUser(user);
        model.addAttribute("lendInfo",lendService.selectLend(lendVo));
        model.addAttribute("lendAplyInfo",lendService.selectLendAplyInfo(lendAplyVo));
        model.addAttribute("lendAplyTrgtList",lendService.selectLendAplyTrgtHistList(lendAplyVo));
        
        return "front/mypage/lendHist/lendHistDetail";
    }
    /**
     * 교구 대여 이력 후기 작성 팝업
     *
     * @Title       : lendReplyPopup 
     * @Description : 교구 대여 이력 사유확인 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/mypage/lendHist/lendReplyPopup.html")
    public String lendReplyPopup(LendAplyVo lendAplyVo, Model model) throws Exception {
        model.addAttribute("paramAplyid",lendAplyVo.getAplyid());
        return "front/mypage/lendHist/lendReplyPopup";
    }
    /**
    * 대여 이력 후기 등록
    *
    * @Title : updateLendReply
    * @Description : 대여 이력 후기 등록
    * @param LendAplyVo lendAplyVo 객체
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/mypage/lendHist/updateLendReply.do")
    @ResponseBody
    public Map<String, Object> updateLendReply(@Valid LendAplyVo lendAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        int retVal = 0;
        lendAplyVo.setUser(user);
        
        retVal = lendService.updateLendAply(lendAplyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
              
        return resultMap;
    }
    /**
    * 교구 대여 이력 사유확인 팝업
    *
    * @Title       : lendDetail 
    * @Description : 교구 대여 이력 사유확인 팝업
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/mypage/lendHist/lendRejectReasonPopup.html")
    public String lendRejectReasonPopup(LendAplyVo lendAplyVo, Model model) throws Exception {
        model.addAttribute("lendAplyInfo",lendService.selectLendAplyInfo(lendAplyVo));
        return "front/mypage/lendHist/lendRejectReasonPopup";
    }
    /**
     * 교구 대여 이력 후기 삭제 팝업
     *
     * @Title       : deleteLendReplyPopup 
     * @Description : 교구 대여 이력 후기 삭제 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/mypage/lendHist/deleteLendReplyPopup.html")
    public String deleteLendReplyPopup(LendAplyVo lendAplyVo, Model model) throws Exception {
        
        LendAplyVo lendAplyInfo = lendService.selectLendAplyInfo(lendAplyVo);
        
        model.addAttribute("lendAplyInfo",lendAplyInfo);
        
        if(lendAplyInfo.getRvwFilegrpid() != null && lendAplyInfo.getRvwFilegrpid() != 0) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(lendAplyInfo.getRvwFilegrpid());
            ArrayList<FileVo> rvwFileList = fileService.getFileList(fileVo);
            model.addAttribute("rvwFileList", rvwFileList);
        }
        
        return "front/mypage/lendHist/lendDeleteReplyPopup";
    }
    /**
    * 대여 이력 후기 삭제
    *
    * @Title : deleteLendReply
    * @Description : 대여 이력 후기 등록
    * @param LendAplyVo lendAplyVo 객체
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/mypage/lendHist/deleteLendReply.do")
    @ResponseBody
    public Map<String, Object> deleteLendReply(@Valid LendAplyVo lendAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        int retVal = 0;
        lendAplyVo.setUser(user);
        
        retVal = lendService.deleteLendAplyRvw(lendAplyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제 실패하였습니다");
        }
        return resultMap;
    }
    
    /**
     * 대여 이력 배송정보팝업
     *
     * @Title       : lendDlvyInfoPopupt 
     * @Description : 대여 이력 배송정보팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/lendHist/lendDlvyInfoPopupt.html")
    public String lendDlvyInfoPopupt(LendAplyVo lendAplyVo, Model model) throws Exception {
        model.addAttribute("lendAplyInfo",lendService.selectLendAplyInfo(lendAplyVo));
        return "front/mypage/lendHist/lendDlvyInfoPopup";
    }
    
}


