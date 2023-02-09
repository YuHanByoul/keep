package com.kbrainc.plum.mng.delvry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.delvry.model.DelvryVo;
import com.kbrainc.plum.mng.delvry.service.DelvryServiceImpl;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.rte.model.ParentRequestVo.ORDER_DIRECTION;
import com.kbrainc.plum.rte.util.excel.ExcelDownloadUtil;

/**
 * 
 * 교부관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.controller
 * - DelvryController.java
 * </pre>
 *
 * @ClassName : DelvryController
 * @Description : 교부관리 Controller
 * @author : KBRAINC
 * @date : 2023. 02. 09.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class DelvryController {
    
    @Autowired
    private DelvryServiceImpl delvryService;
    
    /**
    * 공동구매모집 목록 화면
    *
    * @Title : jntpurchsListForm
    * @Description : 공동구매모집 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/delvryListForm.html")
    public String delvryListForm() throws Exception {
        return "mng/delvry/delvryList";
    }
//    
//    /**
//     * 공동구매모집 등록 화면
//     *
//     * @Title : jntpurchsInsertForm
//     * @Description : 공동구매모집 목록 화면
//     * @return String 화면경로
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/jntpurchsInsertForm.html")
//    public String jntpurchsInsertForm() throws Exception {
//        return "mng/jntpurchs/jntpurchsInsert";
//    }
//    
//    /**
//     * 교구 목록 팝업
//     *
//     * @Title : tchaidListPopup
//     * @Description : 교구 목록 팝업
//     * @return String 화면경로
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/tchaidListPopup.html")
//    public String tchaidListPopup() throws Exception {
//        return "mng/jntpurchs/tchaidListPopup";
//    }
//    
//    /**
//     * 공동구매모집 수정 화면
//     *
//     * @Title : jntpurchsUpdateForm
//     * @Description : 공동구매모집 수정 화면
//     * @return String 화면경로
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/jntpurchsUpdateForm.html")
//    public String jntpurchsUpdateForm(JntpurchsVo jntpurchsVo, Model model) throws Exception {
//        // 공동구매모집 상세정보
//        JntpurchsVo jntpurchsInfo = jntpurchsService.selectJntpurchsInfo(jntpurchsVo);
//        model.addAttribute("jntpurchsInfo", jntpurchsInfo);
//        // 공동구매모집 상품정보
//        JntpurchsTchaidVo jntpurchsTchaidVo = new JntpurchsTchaidVo();
//        jntpurchsTchaidVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
//        model.addAttribute("goodsList", jntpurchsService.selectGoodsList(jntpurchsTchaidVo));
//        // 공동구매모집 수량별 가격정보
//        JntpurchsAmtVo jntpurchsAmtVo = new JntpurchsAmtVo();
//        jntpurchsAmtVo.setJntpurchsid(jntpurchsVo.getJntpurchsid());
//        model.addAttribute("amtList", jntpurchsService.selectAmtList(jntpurchsAmtVo));
//        // 공동구매모집 파일 정보
//        FileVo fileVo = new FileVo();
//        if(jntpurchsInfo.getRprsImgFileid() != null && jntpurchsInfo.getRprsImgFileid() != 0) {
//            fileVo.setFileid(jntpurchsInfo.getRprsImgFileid());
//            FileVo rprsImgFileInfo = fileService.getFileInfo(fileVo);
//            model.addAttribute("rprsImgFileInfo", rprsImgFileInfo);
//        }
//        if(jntpurchsInfo.getDtlImgFilegrpid() != null && jntpurchsInfo.getDtlImgFilegrpid() != 0) {
//            fileVo.setFilegrpid(jntpurchsInfo.getDtlImgFilegrpid());
//            ArrayList<FileVo> dtlImgFileList = fileService.getFileList(fileVo);
//            model.addAttribute("dtlImgFileList", dtlImgFileList);
//        }
//        if(jntpurchsInfo.getMapFilegrpid() != null && jntpurchsInfo.getMapFilegrpid() != 0) {
//            fileVo.setFilegrpid(jntpurchsInfo.getMapFilegrpid());
//            ArrayList<FileVo> mapFileList = fileService.getFileList(fileVo);
//            model.addAttribute("mapFileList", mapFileList);
//        }
//        if(jntpurchsInfo.getEduPhotoFilegrpid() != null && jntpurchsInfo.getEduPhotoFilegrpid() != 0) {
//            fileVo.setFilegrpid(jntpurchsInfo.getEduPhotoFilegrpid());
//            ArrayList<FileVo> eduPhotoFileList = fileService.getFileList(fileVo);
//            model.addAttribute("eduPhotoFileList", eduPhotoFileList);
//        }
//        
//        return "mng/jntpurchs/jntpurchsUpdate";
//    }
//    
//    /**
//     * 공동구매신청 목록 화면
//     *
//     * @Title : jntpurchsOrderListForm
//     * @Description : 공동구매신청 목록 화면
//     * @return String 화면경로
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/jntpurchsOrderListForm.html")
//    public String jntpurchsOrderListForm() throws Exception {
//        return "mng/jntpurchs/jntpurchsOrderList";
//    }
//    
//    /**
//     * 공동구매신청 수정 화면
//     *
//     * @Title : jntpurchsOrderUpdateForm
//     * @Description : 공동구매신청 수정 화면
//     * @return String 화면경로
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/jntpurchsOrderUpdateForm.html")
//    public String jntpurchsOrderUpdateForm(JntpurchsOrderVo jntpurchsOrderVo, Model model) throws Exception {
//        model.addAttribute("orderInfo", jntpurchsService.selectJntpurchsOrderInfo(jntpurchsOrderVo));
//        return "mng/jntpurchs/jntpurchsOrderUpdate";
//    }
//    
    /**
    * 교부 목록 조회
    *
    * @Title : selectDelvryList
    * @Description : 교부 목록 조회
    * @param delvryVo DelvryVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/selectDelvryList.do")
    @ResponseBody
    public Map<String, Object> selectDelvryList(DelvryVo delvryVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        delvryVo.setOrderField("PRGRS_STTS_ORDR");
        delvryVo.setOrderDirection(ORDER_DIRECTION.asc);
        List<DelvryVo> result = delvryService.selectDelvryList(delvryVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 교부 목록 엑셀다운로드
    *
    * @Title : downloadDelvryListExcel
    * @Description : 교부 목록 엑셀다운로드
    * @param delvryVo DelvryVo 객체
    */
    @RequestMapping(value = "/mng/delvry/downloadDelvryListExcel.do")
    @ResponseBody
    public void downloadDelvryListExcel(DelvryVo delvryVo, HttpServletResponse response) throws Exception {
        delvryVo.setOrderField("PRGRS_STTS_ORDR");
        delvryVo.setOrderDirection(ORDER_DIRECTION.asc);
        List<DelvryVo> result = delvryService.selectDelvryList(delvryVo);
        ExcelDownloadUtil<DelvryVo> excelDownloadUtil = new ExcelDownloadUtil<>(
            new String[] {"No.", "사업분야", "공모명", "진행상태", "교부신청발표기간", "사업 선정 수", "사업비 교부 횟수"}, result, (data, mapper, idx) -> {
                mapper
                    .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                    .putData(1, data.getFldCdNm())
                    .putData(2, data.getPcntstNm())
                    .putData(3, data.getPrgrsStts())
                    .putData(4, data.getDelvryCfmtnPrsntnBgngDtStr() + " ~ " + data.getDelvryCfmtnPrsntnEndDtStr())
                    .putData(5, data.getSlctnCnt())
                    .putData(6, data.getWctDelvryCnt());
                return true;
            }
        );
        excelDownloadUtil.excelDownload(response, "교부관리 검색결과");
    }
    
//    
//    /**
//     * 교구 목록 조회
//     *
//     * @Title : selectTchaidList
//     * @Description : 교구 목록 조회
//     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
//     * @return Map<String, Object> 응답결과객체
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/selectTchaidList.do")
//    @ResponseBody
//    public Map<String, Object> selectTchaidList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<JntpurchsTchaidVo> result = jntpurchsService.selectTchaidList(jntpurchsTchaidVo);
//             
//        if(result.size() > 0) {
//            resultMap.put("totalCount", (result.get(0).getTotalCount()));
//         } else {
//             resultMap.put("totalCount", 0);
//         }
//        resultMap.put("list", result);
//        
//        return resultMap;
//    }
//    
//    /**
//     * 공동구매모집 정보 업데이트
//     *
//     * @Title : updateJntpurchs
//     * @Description : 공동구매모집 정보 업데이트
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @param bindingResult jntpurchsVo 유효성 검증결과
//     * @param user 사용자 세션 정보
//     * @return Map<String, Object> 응답결과객체
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/updateJntpurchs.do")
//    @ResponseBody
//    public Map<String, Object> updateJntpurchs(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//          
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//          
//        int retVal = 0;
//        jntpurchsVo.setUser(user);
//        retVal = jntpurchsService.updateJntpurchs(jntpurchsVo);
//          
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "수정에 성공하였습니다");
//        } else if(retVal == -1) {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "공동구매신청 이력이 있어 수정할 수 없습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "수정에 실패하였습니다");
//        }
//              
//        return resultMap;
//    }
//    
//    /**
//     * 공동구매모집 정보 삭제
//     *
//     * @Title : deleteJntpurchs
//     * @Description : 공동구매모집 정보 삭제
//     * @param jntpurchsVo JntpurchsVo 객체
//     * @param bindingResult jntpurchsVo 유효성 검증결과
//     * @param user 사용자 세션 정보
//     * @return Map<String, Object> 응답결과객체
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/deleteJntpurchs.do")
//    @ResponseBody
//    public Map<String, Object> deleteJntpurchs(@Valid JntpurchsVo jntpurchsVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//          
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//          
//        int retVal = 0;
//        jntpurchsVo.setUser(user);
//        retVal = jntpurchsService.deleteJntpurchs(jntpurchsVo);
//          
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "삭제에 성공하였습니다");
//        } else if(retVal == -1) {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "공동구매신청 이력이 있어 삭제할 수 없습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "삭제에 실패하였습니다");
//        }
//              
//        return resultMap;
//    }
//    
//    /**
//     * 공동구매신청 목록 조회
//     *
//     * @Title : selectJntpurchsOrderList
//     * @Description : 공동구매신청 목록 조회
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
//     * @return Map<String, Object> 응답결과객체
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/selectJntpurchsOrderList.do")
//    @ResponseBody
//    public Map<String, Object> selectJntpurchsOrderList(JntpurchsOrderVo jntpurchsOrderVo) throws Exception {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<JntpurchsOrderVo> result = jntpurchsService.selectJntpurchsOrderList(jntpurchsOrderVo);
//             
//        if(result.size() > 0) {
//            resultMap.put("totalCount", (result.get(0).getTotalCount()));
//         } else {
//             resultMap.put("totalCount", 0);
//         }
//        resultMap.put("list", result);
//        
//        return resultMap;
//    }
//    
//    
//    /**
//     * 공동구매신청 정보 업데이트
//     *
//     * @Title : updateJntpurchsOrder
//     * @Description : 공동구매신청 정보 업데이트
//     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
//     * @param bindingResult jntpurchsVo 유효성 검증결과
//     * @param user 사용자 세션 정보
//     * @return Map<String, Object> 응답결과객체
//     * @throws Exception 예외
//     */
//    @RequestMapping(value = "/mng/jntpurchs/updateJntpurchsOrder.do")
//    @ResponseBody
//    public Map<String, Object> updateJntpurchsOrder(@Valid JntpurchsOrderVo jntpurchsOrderVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//          
//        if(bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if(fieldError != null) {
//                resultMap.put("msg", fieldError.getDefaultMessage());
//            }
//            return resultMap;
//        }
//          
//        int retVal = 0;
//        jntpurchsOrderVo.setUser(user);
//        retVal = jntpurchsService.updateJntpurchsOrder(jntpurchsOrderVo);
//          
//        if(retVal > 0) {
//            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
//            resultMap.put("msg", "수정에 성공하였습니다");
//        } else {
//            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
//            resultMap.put("msg", "수정에 실패하였습니다");
//        }
//              
//        return resultMap;
//    }
    
}
