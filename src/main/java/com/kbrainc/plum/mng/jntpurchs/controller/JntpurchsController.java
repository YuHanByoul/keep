package com.kbrainc.plum.mng.jntpurchs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.mng.jntpurchs.service.JntpurchsServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.ParentRequestVo.ORDER_DIRECTION;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.excel.ExcelDownloadUtil;

/**
 * 
 * 공동구매관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.jntpurchs.controller
 * - JntpurchsController.java
 * </pre>
 *
 * @ClassName : JntpurchsController
 * @Description : 공동구매관리 Controller
 * @author : KBRAINC
 * @date : 2023. 01. 18.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class JntpurchsController {

    @Autowired
    private JntpurchsServiceImpl jntpurchsService;
    
    @Autowired
    private FileService fileService;
    
    /**
     * 공동구매모집 목록 화면
     *
     * @Title : jntpurchsListForm
     * @Description : 공동구매모집 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/jntpurchsListForm.html")
    public String jntpurchsListForm() throws Exception {
        return "mng/jntpurchs/jntpurchsList";
    }
    
    /**
     * 공동구매모집 등록 화면
     *
     * @Title : jntpurchsInsertForm
     * @Description : 공동구매모집 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/jntpurchsInsertForm.html")
    public String jntpurchsInsertForm() throws Exception {
        return "mng/jntpurchs/jntpurchsInsert";
    }
    
    /**
     * 교구 목록 팝업
     *
     * @Title : tchaidListPopup
     * @Description : 교구 목록 팝업
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/tchaidListPopup.html")
    public String tchaidListPopup() throws Exception {
        return "mng/jntpurchs/tchaidListPopup";
    }
    
    /**
     * 공동구매모집 수정 화면
     *
     * @Title : jntpurchsUpdateForm
     * @Description : 공동구매모집 수정 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/jntpurchsUpdateForm.html")
    public String jntpurchsUpdateForm(JntpurchsVo jntpurchsVo, Model model) throws Exception {
        // 공동구매모집 상세정보
        JntpurchsVo jntpurchsInfo = jntpurchsService.selectJntpurchsInfo(jntpurchsVo);
        model.addAttribute("jntpurchsInfo", jntpurchsInfo);
        // 공동구매모집 상품정보
        JntpurchsTchaidVo jntpurchsTchaidVo = new JntpurchsTchaidVo();
        jntpurchsTchaidVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
        model.addAttribute("goodsList", jntpurchsService.selectGoodsList(jntpurchsTchaidVo));
        // 공동구매모집 수량별 가격정보
        JntpurchsAmtVo jntpurchsAmtVo = new JntpurchsAmtVo();
        jntpurchsAmtVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
        model.addAttribute("amtList", jntpurchsService.selectAmtList(jntpurchsAmtVo));
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
        
        return "mng/jntpurchs/jntpurchsUpdate";
    }
    
    /**
     * 공동구매신청 목록 화면
     *
     * @Title : jntpurchsOrderListForm
     * @Description : 공동구매신청 목록 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/jntpurchsOrderListForm.html")
    public String jntpurchsOrderListForm() throws Exception {
        return "mng/jntpurchs/jntpurchsOrderList";
    }
    
    /**
     * 공동구매신청 수정 화면
     *
     * @Title : jntpurchsOrderUpdateForm
     * @Description : 공동구매신청 수정 화면
     * @return String 화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/jntpurchsOrderUpdateForm.html")
    public String jntpurchsOrderUpdateForm(JntpurchsOrderVo jntpurchsOrderVo, Model model) throws Exception {
        model.addAttribute("orderInfo", jntpurchsService.selectJntpurchsOrderInfo(jntpurchsOrderVo));
        return "mng/jntpurchs/jntpurchsOrderUpdate";
    }
    
    
    /**
     * 공동구매모집 등록
     *
     * @Title : insertJntpurchs
     * @Description : 공동구매모집 등록
     * @param jntpurchsVo JntpurchsVo 객체
     * @param bindingResult jntpurchsVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/insertJntpurchs.do")
    @ResponseBody
    public Map<String, Object> insertJntpurchs(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsVo.setUser(user);
        retVal = jntpurchsService.insertJntpurchs(jntpurchsVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
     * 공동구매모집 목록 조회
     *
     * @Title : selectJntpurchsList
     * @Description : 공동구매모집 목록 조회
     * @param jntpurchsVo JntpurchsVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/selectJntpurchsList.do")
    @ResponseBody
    public Map<String, Object> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<JntpurchsVo> result = jntpurchsService.selectJntpurchsList(jntpurchsVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 교구 목록 조회
     *
     * @Title : selectTchaidList
     * @Description : 교구 목록 조회
     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/selectTchaidList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<JntpurchsTchaidVo> result = jntpurchsService.selectTchaidList(jntpurchsTchaidVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 공동구매모집 정보 업데이트
     *
     * @Title : updateJntpurchs
     * @Description : 공동구매모집 정보 업데이트
     * @param jntpurchsVo JntpurchsVo 객체
     * @param bindingResult jntpurchsVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/updateJntpurchs.do")
    @ResponseBody
    public Map<String, Object> updateJntpurchs(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsVo.setUser(user);
        retVal = jntpurchsService.updateJntpurchs(jntpurchsVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else if(retVal == -1) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공동구매신청 이력이 있어 수정할 수 없습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
     * 공동구매 모집상태  업데이트
     *
     * @Title : updateJntpurchs
     * @Description : 공동구매 모집상태 업데이트
     * @param jntpurchsVo JntpurchsVo 객체
     * @param bindingResult jntpurchsVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/updateJntpurchsStts.do")
    @ResponseBody
    public Map<String, Object> updateJntpurchsStts(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsVo.setUser(user);
        retVal = jntpurchsService.updateJntpurchsStts(jntpurchsVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else if(retVal == -1) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공동구매신청 이력이 있어 수정할 수 없습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
     * 공동구매모집 정보 삭제
     *
     * @Title : deleteJntpurchs
     * @Description : 공동구매모집 정보 삭제
     * @param jntpurchsVo JntpurchsVo 객체
     * @param bindingResult jntpurchsVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/deleteJntpurchs.do")
    @ResponseBody
    public Map<String, Object> deleteJntpurchs(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        jntpurchsVo.setUser(user);
        retVal = jntpurchsService.deleteJntpurchs(jntpurchsVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제에 성공하였습니다");
        } else if(retVal == -1) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "공동구매신청 이력이 있어 삭제할 수 없습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
     * 공동구매신청 목록 조회
     *
     * @Title : selectJntpurchsOrderList
     * @Description : 공동구매신청 목록 조회
     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/selectJntpurchsOrderList.do")
    @ResponseBody
    public Map<String, Object> selectJntpurchsOrderList(JntpurchsOrderVo jntpurchsOrderVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<JntpurchsOrderVo> result = jntpurchsService.selectJntpurchsOrderList(jntpurchsOrderVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 공동구매신청 목록 엑셀다운로드
     *
     * @Title : downloadJntpurchsOrderListExcel
     * @Description : 공동구매신청 목록 엑셀다운로드
     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
     */
    @RequestMapping(value = "/mng/jntpurchs/downloadJntpurchsOrderListExcel.do")
    @ResponseBody
    public void downloadJntpurchsOrderListExcel(JntpurchsOrderVo jntpurchsOrderVo, HttpServletResponse response) throws Exception {
        jntpurchsOrderVo.setRowPerPage(0);
        jntpurchsOrderVo.setOrderField("REG_DT");
        jntpurchsOrderVo.setOrderDirection(ORDER_DIRECTION.desc);
        List<JntpurchsOrderVo> result = jntpurchsService.selectJntpurchsOrderList(jntpurchsOrderVo);
        ExcelDownloadUtil<JntpurchsOrderVo> excelDownloadUtil = new ExcelDownloadUtil<>(
            new String[] {"No.", "공동구매모집명", "신청자ID", "신청자(기관명)", "접수번호", "교구명", "신청 수량", "신청상태", "신청일시"}, result, (data, mapper, idx) -> {
                mapper
                    .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                    .putData(1, data.getJntpurchsNm())
                    .putData(2, data.getAcnt())
                    .putData(3, data.getNm() + "(" + data.getInstNm() + ")")
                    .putData(4, data.getOrderno())
                    .putData(5, data.getTchaidNm())
                    .putData(6, data.getQnty())
                    .putData(7, data.getSttsCdNm())
                    .putData(8, data.getRegDtStr());
                return true;
            }
        );
        excelDownloadUtil.excelDownload(response, "공동구매신청 검색결과");
    }
    
    /**
     * 공동구매신청 정보 업데이트
     *
     * @Title : updateJntpurchsOrder
     * @Description : 공동구매신청 정보 업데이트
     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
     * @param bindingResult jntpurchsVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/jntpurchs/updateJntpurchsOrder.do")
    @ResponseBody
    public Map<String, Object> updateJntpurchsOrder(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
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
        retVal = jntpurchsService.updateJntpurchsOrder(jntpurchsOrderVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
              
        return resultMap;
    }
    
}
