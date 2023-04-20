package com.kbrainc.plum.mng.delvry.controller;

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
import com.kbrainc.plum.mng.delvry.model.DelvryAplyComputVo;
import com.kbrainc.plum.mng.delvry.model.DelvryAplySplmntVo;
import com.kbrainc.plum.mng.delvry.model.DelvryAplyVo;
import com.kbrainc.plum.mng.delvry.model.PcntstVo;
import com.kbrainc.plum.mng.delvry.service.DelvryServiceImpl;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.ParentRequestVo.ORDER_DIRECTION;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
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
    
    @Autowired
    private FileService fileService;
    
    /**
    * 공모 목록 화면
    *
    * @Title : pcntstListForm
    * @Description : 공모 목록 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/pcntstListForm.html")
    public String pcntstListForm() throws Exception {
        return "mng/delvry/pcntstList";
    }
    
    /**
    * 교부 상세 화면
    *
    * @Title : pcntstDetailForm
    * @Description : 교부 상세 화면
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/pcntstDetailForm.html")
    public String pcntstDetailForm(PcntstVo pcntstVo, Model model) throws Exception {
        model.addAttribute("pcntstInfo", delvryService.selectPcntstInfo(pcntstVo));
        return "mng/delvry/pcntstDetail";
    }
    
    /**
    * 교부 신청 수정 화면
    *
    * @Title : delvryAplyUpdateForm
    * @Description : 교부 신청 수정 화면
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/delvryAplyUpdateForm.html")
    public String delvryAplyUpdateForm(DelvryAplyVo delvryAplyVo, Model model) throws Exception {
        DelvryAplyVo delvryInfo = delvryService.selectDelvryAplyInfo(delvryAplyVo);
        // 파일 정보
        FileVo fileVo = new FileVo();
        if(delvryInfo.getAtchFilegrpid() != null && delvryInfo.getAtchFilegrpid() != 0) {
            fileVo.setFilegrpid(delvryInfo.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileService.getFileList(fileVo);
            model.addAttribute("fileList", fileList);
        }
        model.addAttribute("delvryAplyInfo", delvryInfo);
        
        List<DelvryAplyComputVo> computList = delvryService.selectDelvryAplyComputList(delvryAplyVo);
        model.addAttribute("computList", computList);
        
        return "mng/delvry/delvryAplyUpdate";
    }
    
    /**
    * 교부 신청 보완요청 등록 팝업
    *
    * @Title : delvryAplySplmntInsertPopup
    * @Description : 교부 신청 보완요청 등록 팝업
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/delvryAplySplmntInsertPopup.html")
    public String delvryAplySplmntInsertPopup() throws Exception {
        return "mng/delvry/splmntInsertPopup";
    }
    
    /**
    * 교부 신청 보완요청 수정 팝업
    *
    * @Title : delvryAplySplmntUpdatePopup
    * @Description : 교부 신청 보완요청 수정 팝업
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/delvryAplySplmntUpdatePopup.html")
    public String delvryAplySplmntUpdatePopup(DelvryAplySplmntVo delvryAplySplmntVo, Model model) throws Exception {
        model.addAttribute("splmntInfo", delvryService.selectDelvryAplySplmntInfo(delvryAplySplmntVo));
        return "mng/delvry/splmntUpdatePopup";
    }
     
    /**
    * 교부 신청서 팝업
    *
    * @Title : delvryAplyPopup
    * @Description : 교부 신청서 팝업
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return String 화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/delvryAplyPopup.html")
    public String delvryAplyPopup(DelvryAplyVo delvryAplyVo, Model model) throws Exception {
        //model.addAttribute("splmntInfo", delvryService.selectDelvryAplySplmntInfo(delvryAplyVo));
        return "mng/delvry/delvryAplyPopup";
    }
      
    /**
    * 공모 목록 조회
    *
    * @Title : selectPcntstList
    * @Description : 공모 목록 조회
    * @param pcntstVo PcntstVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/selectPcntstList.do")
    @ResponseBody
    public Map<String, Object> selectPcntstList(PcntstVo pcntstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        pcntstVo.setOrderField("PRGRS_STTS_ORDR");
        pcntstVo.setOrderDirection(ORDER_DIRECTION.asc);
        List<PcntstVo> result = delvryService.selectPcntstList(pcntstVo);
             
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
    @RequestMapping(value = "/mng/delvry/downloadPcntstListExcel.do")
    @ResponseBody
    public void downloadPcntstListExcel(PcntstVo pcntstVo, HttpServletResponse response) throws Exception {
        pcntstVo.setRowPerPage(0);
        pcntstVo.setOrderField("PRGRS_STTS_ORDR");
        pcntstVo.setOrderDirection(ORDER_DIRECTION.asc);
        List<PcntstVo> result = delvryService.selectPcntstList(pcntstVo);
        ExcelDownloadUtil<PcntstVo> excelDownloadUtil = new ExcelDownloadUtil<>(
            new String[] {"No.", "사업분야", "공모명", "진행상태", "교부신청발표기간", "사업 선정 수", "사업비 교부 횟수"}, result, (data, mapper, idx) -> {
                mapper
                    .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                    .putData(1, data.getFldCdNm())
                    .putData(2, data.getPcntstNm())
                    .putData(3, data.getPrgrsStts())
                    .putData(4, data.getDelvryAplyPrsntnBgngDtStr() == null ? "-" : data.getDelvryAplyPrsntnBgngDtStr() + " ~ " + data.getDelvryAplyPrsntnEndDtStr())
                    .putData(5, data.getSlctnCnt())
                    .putData(6, data.getWctDelvryCnt());
                return true;
            }
        );
        excelDownloadUtil.excelDownload(response, "교부관리 검색결과");
    }
    
    /**
    * 교부신청 목록 조회
    *
    * @Title : selectDelvryAplyList
    * @Description : 교부신청 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/selectDelvryAplyList.do")
    @ResponseBody
    public Map<String, Object> selectDelvryAplyList(DelvryAplyVo delvryAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DelvryAplyVo> result = delvryService.selectDelvryAplyList(delvryAplyVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
    * 교부상태 업데이트
    *
    * @Title : updateDelvryStts
    * @Description : 교부상태 업데이트
    * @param delvryAplyVo DelvryAplyVo 객체
    * @param bindingResult delvryAplyVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/updateDelvryStts.do")
    @ResponseBody
    public Map<String, Object> updateDelvryStts(@Valid DelvryAplyVo delvryAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        delvryAplyVo.setUser(user);
        retVal = delvryService.updateDelvryStts(delvryAplyVo);
        
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "상태변경이 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "상태변경이 실패하였습니다");
        }
            
        return resultMap;
    }
    
    /**
    * 교부신청 목록 엑셀다운로드
    *
    * @Title : downloadDelvryAplyListExcel
    * @Description : 교부신청 목록 엑셀다운로드
    * @param delvryAplyVo DelvryAplyVo 객체
    */
    @RequestMapping(value = "/mng/delvry/downloadDelvryAplyListExcel.do")
    @ResponseBody
    public void downloadDelvryAplyListExcel(DelvryAplyVo delvryAplyVo, HttpServletResponse response) throws Exception {
        delvryAplyVo.setOrderField("REG_DT");
        delvryAplyVo.setOrderDirection(ORDER_DIRECTION.desc);
        List<DelvryAplyVo> result = delvryService.selectDelvryAplyList(delvryAplyVo);
        ExcelDownloadUtil<DelvryAplyVo> excelDownloadUtil = new ExcelDownloadUtil<>(
            new String[] {"No.", "접수번호", "교부상태", "신청기관명", "신청자", "프로그램명/동아리명", "교부신청일", "지원신청금액(원)"}, result, (data, mapper, idx) -> {
                mapper
                    .putData(0, data.getRowNumber(), ExcelDownloadUtil.CELL_ALIGN.CENTER)
                    .putData(1, data.getRcptno())
                    .putData(2, data.getDelvrySttsCdNm())
                    .putData(3, data.getInstNm())
                    .putData(4, data.getAplcntNm() + "(" + data.getAcnt() + ")")
                    .putData(5, data.getClubNm() == null ? data.getPrgrmNm() : data.getPrgrmNm() + " ~ " + data.getClubNm())
                    .putData(6, data.getRegDtStr())
                    .putData(7, data.getTotAmt());
                return true;
            }
        );
        excelDownloadUtil.excelDownload(response, "교부신청 검색결과");
    }
    
    /**
     * 교부 신청 정보 업데이트
     *
     * @Title : updateDelvryAply
     * @Description : 교부 신청 정보 업데이트
     * @param delvryAplyVo DelvryAplyVo 객체
     * @param bindingResult delvryAplyVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/delvry/updateDelvryAply.do")
    @ResponseBody
    public Map<String, Object> updateDelvryAply(@Valid DelvryAplyVo delvryAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        delvryAplyVo.setUser(user);
        retVal = delvryService.updateDelvryAply(delvryAplyVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 교부 신청 보완요청 목록 조회
    *
    * @Title : selectDelvryAplySplmntList
    * @Description : 교부 신청 보완요청 목록 조회
    * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/selectDelvryAplySplmntList.do")
    @ResponseBody
    public Map<String, Object> selectDelvryAplySplmntList(DelvryAplySplmntVo delvryAplySplmntVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<DelvryAplySplmntVo> result = delvryService.selectDelvryAplySplmntList(delvryAplySplmntVo);
             
        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * 교부 신청 보완요청 등록
     *
     * @Title : insertDelvryAplySplmnt
     * @Description : 교부 신청 보완요청 등록
     * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
     * @param bindingResult delvryAplySplmntVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/delvry/insertDelvryAplySplmnt.do")
    @ResponseBody
    public Map<String, Object> insertDelvryAplySplmnt(@Valid DelvryAplySplmntVo delvryAplySplmntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        delvryAplySplmntVo.setUser(user);
        retVal = delvryService.insertDelvryAplySplmnt(delvryAplySplmntVo);
          
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
     * 교부 신청 보완요청 업데이트
     *
     * @Title : updateDelvryAplySplmnt
     * @Description : 교부 신청 보완요청 업데이트
     * @param delvryAplySplmntVo DelvryAplySplmntVo 객체
     * @param bindingResult delvryAplySplmntVo 유효성 검증결과
     * @param user 사용자 세션 정보
     * @return Map<String, Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/delvry/updateDelvryAplySplmnt.do")
    @ResponseBody
    public Map<String, Object> updateDelvryAplySplmnt(@Valid DelvryAplySplmntVo delvryAplySplmntVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        delvryAplySplmntVo.setUser(user);
        retVal = delvryService.updateDelvryAplySplmnt(delvryAplySplmntVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }
              
        return resultMap;
    }
    
    /**
    * 교부 신청 파일 목록 조회
    *
    * @Title : selectDelvryAplyFileList
    * @Description : 교부 신청 파일 목록 조회
    * @param delvryAplyVo DelvryAplyVo 객체
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/mng/delvry/selectDelvryAplyFileList.do")
    @ResponseBody
    public Map<String, Object> selectDelvryAplyFileList(DelvryAplyVo delvryAplyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<FileVo> result = delvryService.selectDelvryAplyFileList(delvryAplyVo);
        if(result.size() > 0) {
            resultMap.put("totalCount", result.size());
         } else {
             resultMap.put("totalCount", 0);
         }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
}
