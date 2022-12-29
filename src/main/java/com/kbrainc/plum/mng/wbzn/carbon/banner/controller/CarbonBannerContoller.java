package com.kbrainc.plum.mng.wbzn.carbon.banner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.wbzn.carbon.banner.model.CarbonBannerVo;
import com.kbrainc.plum.mng.wbzn.carbon.banner.service.CarbonBannerService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 탄소중립환경교육 -> 배너관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.banner.controller
* - CarbonBannerContoller.java
* </pre>
*
* @ClassName : CarbonBannerContoller
* @Description : 탄소중립환경교육 -> 배너관리 컨트롤러 클래스
* @author : JD
* @date : 2022. 12. 29.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class CarbonBannerContoller {
    
    @Autowired
    private CarbonBannerService bannerService;
    
    /**
     * 배너관리 리스트화면으로 이동
     *
     * @Title : bannerListForm
     * @Description : 배너관리 리스트화면으로 이동
     * @throws Exception 예외
     * @return String
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/bannerListForm.html")
     public String bannerListForm() throws Exception {
         return "/mng/wbzn/carbon/banner/bannerList";
     }
     
     /**
     * 배너관리 등록화면으로 이동
     *
     * @Title : bannerInsertPopup
     * @Description : 배너관리 등록화면으로 이동
     * @throws Exception 예외
     * @return String
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/bannerInsertPopup.html")
     public String bannerInsertPopup() throws Exception {
         return "/mng/wbzn/carbon/banner/bannerInsertPopup";
     }

     /**
     * 배너관리 수정화면으로 이동
     *
     * @Title : bannerUpdatePopup
     * @Description : 배너관리 수정화면으로 이동
     * @param bannerVo 배너관리 객체
     * @param model 객체
     * @throws Exception 예외
     * @return String
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/bannerUpdatePopup.html")
     public String bannerUpdatePopup(CarbonBannerVo bannerVo, Model model) throws Exception {
         CarbonBannerVo result = null;
         result = bannerService.selectBannerInfo(bannerVo);
         model.addAttribute("banner", result);
         
         if(result.getBannerFileid() != 0 && result.getFileIdntfcKey() != null) {
             StringBuffer fileBtn = new StringBuffer();
             fileBtn.append("<div class ='label label-inverse text-white' id='" + result.getBannerFileid() + "'>");
             fileBtn.append("<a href=javascript:downloadFileByFileid('" + result.getBannerFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>" + result.getOrginlFileNm() + "&nbsp;&nbsp;</a>");
             fileBtn.append("<a href=javascript:fn_deleteFileList('" + result.getBannerFileid() + "','" + result.getFileIdntfcKey() + "') class='text-white'>X</a></div>");
             model.addAttribute("fileBtn", fileBtn);
         }
         
         return "/mng/wbzn/carbon/banner/bannerUpdatePopup";
     }
     
     /**
     * 배너관리 배너, 노출배너 목록 조회
     *
     * @Title : selectBannerList
     * @Description : 배너관리 배너, 노출배너 목록 조회
     * @param bannerVo 배너관리 객체
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/selectBannerList.do")
     @ResponseBody
     public Map<String, Object> selectBannerList(CarbonBannerVo bannerVo) throws Exception {
         Map<String, Object> resultMap = new HashMap<>();
         List<CarbonBannerVo> result = null;
         List<CarbonBannerVo> expsrBannerList = null;
         
         result =  bannerService.selectBannerList(bannerVo);
         expsrBannerList =  bannerService.selectExpsrBannerList(bannerVo);
         
         if (result.size() > 0) {
             resultMap.put("totalCount", (result.get(0).getTotalCount()));
             resultMap.put("pagination",PaginationUtil.getPagingHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
         } else {
             resultMap.put("totalCount", 0);
         }
         resultMap.put("list", result);
         resultMap.put("expsrBannerList", expsrBannerList);

         return resultMap;
     }
     
     /**
     * 배너관리 배너 등록 기능
     *
     * @Title : insertBanner
     * @Description : 배너관리 배너 등록 기능
     * @param bannerVo 배너관리 객체
     * @param bindingResult 배너관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/insertBanner.do")
     @ResponseBody
     public Map<String, Object> insertBanner(@Valid CarbonBannerVo bannerVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         bannerVo.setUser(user);

         int retVal = 0;
                 
         retVal = bannerService.insertBanner(bannerVo);
         
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
     * 배너관리 배너 수정 기능
     *
     * @Title : updateBanner
     * @Description : 배너관리 배너 수정 기능
     * @param bannerVo 배너관리 객체
     * @param bindingResult 배너관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/updateBanner.do")
     @ResponseBody
     public Map<String, Object> updateBanner(@Valid CarbonBannerVo bannerVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         bannerVo.setUser(user);

         int retVal = 0;
                 
         retVal = bannerService.updateBanner(bannerVo);
         
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
     * 배너관리 배너 삭제 기능
     *
     * @Title : deleteBanner
     * @Description : 배너관리 배너 삭제 기능
     * @param bannerVo 배너관리 객체
     * @param bindingResult 배너관리 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/deleteBanner.do")
     @ResponseBody
     public Map<String, Object> deleteBanner(@Valid CarbonBannerVo bannerVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         bannerVo.setUser(user);

         int retVal = 0;
                 
         retVal = bannerService.deleteBanner(bannerVo);
         
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
     * 배너목록에서 노출배너로 등록
     *
     * @Title : updateExpsrBanner
     * @Description : 배너목록에서 노출배너로 등록
     * @param bannerVo 배너관리 객체
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/updateExpsrBanner.do")
     @ResponseBody
     public Map<String, Object> updateExpsrBanner(@RequestBody CarbonBannerVo bannerVo, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         bannerVo.setUser(user);

         int retVal = 0;
                 
         retVal = bannerService.updateExpsrBanner(bannerVo);
         
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
     * 노출배너 목록 순서정렬
     *
     * @Title : updateExpsrBannerSort
     * @Description : 노출배너 목록 순서정렬
     * @param bannerVo 배너관리 객체
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/wbzn/carbon/banner/updateExpsrBannerSort.do")
     @ResponseBody
     public Map<String, Object> updateExpsrBannerSort(@RequestBody CarbonBannerVo bannerVo, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         bannerVo.setUser(user);

         int retVal = 0;
         
         retVal = bannerService.updateExpsrBannerSort(bannerVo);
         
         if (retVal > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
             resultMap.put("msg", "등록에 성공하였습니다.");
         } else {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "등록에 실패했습니다.");
         }

         return resultMap;
     }

}
