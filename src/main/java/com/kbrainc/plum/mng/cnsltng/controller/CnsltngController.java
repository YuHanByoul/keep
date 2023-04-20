package com.kbrainc.plum.mng.cnsltng.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtGrpVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngResultVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.cnsltng.service.CnsltngService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.ParentRequestVo.ORDER_DIRECTION;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelDownloadUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 컨설팅 관리 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.mng.consult.controller 
 * - SiteApplyController.java
 * </pre> 
 *
 * @ClassName : ConsultController
 * @Description : 사이트관리 컨트롤러.
 * @author : KBRAINC
 * @date : 2022. 12. 20.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@RequestMapping
@Slf4j
public class CnsltngController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CnsltngService cnsltngService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private InstService instService;
    
    /**
     * @Title : cnsltngList
     * @Description : 컨설팅 관리 화면 이동
     * @param 
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngList.html")
    public String cnsltngList(CnsltngVo cnsltngVo, Model model) throws Exception {
        return "mng/cnsltng/cnsltngList";
    }
    
    /**
     * @Title : selectCnsltngList
     * @Description : 컨설팅 리스트 가져오기
     * @param CnsltngVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/cnsltng/selectCnsltngList.do")
    public @ResponseBody Map<String, Object> selectCnsltngList(CnsltngVo cnsltngVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CnsltngVo> result = new ArrayList<CnsltngVo>();
        
        result = cnsltngService.selectCnsltngList(cnsltngVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * @Title : cnsltngDetailForm
     * @Description : 상세화면 (탭)화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngDetailForm.html")
    public String cnsltngDetailForm(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("cnsltngVo", cnsltngVo);
        return "mng/cnsltng/cnsltngDetailForm";
    }
    
    /**
     * @Title : cnsltngApplyInfo
     * @Description : 컨설팅 신청정보 상세화면 화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngApplyInfo.html")
    public String cnsltngApplyInfo(CnsltngVo cnsltngVo, Model model, SrvyVo srvyVo,@UserInfo UserVo user) throws Exception {
        
        cnsltngVo.setUser(user);
        CnsltngVo resVo = new CnsltngVo();
        resVo = cnsltngService.selectCnsltngtInfo(cnsltngVo);
        model.addAttribute("cnsltngVo", resVo);
        model.addAttribute("srvyList", cnsltngService.selectCnsltngSrvyList(srvyVo));
        
        InstVo instVo = new InstVo();
        instVo.setInstid(resVo.getInstid());
        model.addAttribute("instVo", instService.selectInstInfo(instVo));
        
        model.addAttribute("cnstntList", cnsltngService.selectCnstntList(cnsltngVo));
        
        if (resVo.getFilegrpid() != null && !resVo.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(resVo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileList",fileList );
        } else {
            model.addAttribute("fileList", null);
        }
        
        return "mng/cnsltng/cnsltngInfoForm";
    }
    /**
     * @Title : cnsltngManagerPopup
     * @Description : 컨설팅 담당자 등록 모달 팝업
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngManagerPopup.html")
    public String cnsltngManagerPopup(CnsltngVo cnsltngVo, Model model, @UserInfo UserVo user) throws Exception {
        Map<String,Object> paramMap = new HashMap();
        model.addAttribute("cnsltngid", cnsltngVo.getCnsltngid());
        model.addAttribute("sidoList", cnsltngService.selectAddrCtprvnList(paramMap));
        return "mng/cnsltng/cnsltngUserSearchPopup";
    }
    
    /**
     * @Title : selectCnsltngExprtList
     * @Description : 컨설팅 전문가 리스트 가져오기
     * @param CnsltngVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/cnsltng/selectExpertList.do")
    public @ResponseBody Map<String, Object> selectCnsltngExprtList(CnsltngExprtVo cnsltngExprtVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CnsltngExprtVo> result = new ArrayList<CnsltngExprtVo>();
        
        result = cnsltngService.selectCnsltngExprtList(cnsltngExprtVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * @Title : selectJudgeList
     * @Description : 컨설팅 전문가 그룹 리스트 가져오기
     * @param PublicContestMngGrpVo
     * @throws Exception
     * @return
     */
    @RequestMapping(value = "/mng/cnsltng/selectJudgeList.do")
    public @ResponseBody Map<String, Object> selectJudgeList(CnsltngExprtGrpVo cnsltngExprtGrpVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CnsltngExprtGrpVo> result = new ArrayList<CnsltngExprtGrpVo>();
        
        result = cnsltngService.selectCnsltngExprtGrpList(cnsltngExprtGrpVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        
        return resultMap;
    }
    
    /**
     * @Title : insertConsultant
     * @Description : 사이트 신청 상태 변경
     * @param SiteApplyVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/cnsltng/insertConsultant.do")
    @ResponseBody
    public Map<String, Object> insertConsultant(CnsltngVo cnsltngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        
        int retVal = 0;
        retVal = cnsltngService.insertCnsltnt(cnsltngVo);
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }
        return map;
    }
    
    /**
     * @Title : deleteCnsltnt
     * @Description : 컨설팅 담당자 삭제 
     * @param SiteApplyVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/cnsltng/deleteConsultant.do")
    @ResponseBody
    public Map<String, Object> deleteCnsltnt(CnsltngVo cnsltngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        
        int retVal = 0;
        retVal = cnsltngService.deleteCnsltnt(cnsltngVo);
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "삭제 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "삭제 실패했습니다.");
        }
        return map;
    }
    
    /**
     * @Title : updateCnsltng
     * @Description : 컨설팅 정보 업데이트  
     * @param CnsltngVo cnsltngVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/cnsltng/updateCnsltng.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> updateCnsltng(CnsltngVo cnsltngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        
        int retVal = 0;
        
        cnsltngVo.setUser(user);
        
        retVal = cnsltngService.insertCnsltntALL(cnsltngVo);
        retVal += cnsltngService.updateCnsltng(cnsltngVo);
        
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "저장 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "저장 실패했습니다.");
        }
        return map;
    }
    
    @RequestMapping(value = "/mng/cnsltng/downloadCnsltngExcelList.do")
    public void downloadCnsltngExcelList(HttpServletRequest request, HttpServletResponse response, CnsltngVo cnsltngVo) throws Exception {
        cnsltngService.cnstlngExcelDownList(cnsltngVo, response, request);
        /*
         * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
         * Locale.getDefault()); cnsltngVo.setOrderField("REG_DT");
         * cnsltngVo.setOrderDirection(ORDER_DIRECTION.desc); List<CnsltngVo> result =
         * cnsltngService.selectCnsltngExcelList(cnsltngVo);
         * ExcelDownloadUtil<CnsltngVo> excelDownloadUtil = new ExcelDownloadUtil<>( new
         * String[] { "No." ,"프로그램명" ,"기관명" ,"컨설팅 유형" ,"전문가명" ,"진행상태" ,"신청일" ,"방문기간"} ,
         * result, (data, mapper, idx) -> { mapper .putData(0, data.getRowNumber(),
         * ExcelDownloadUtil.CELL_ALIGN.CENTER) .putData(1,
         * StringUtil.nvl(data.getPrgrm(), "")) .putData(2,
         * StringUtil.nvl(data.getInstNm(), "")) .putData(3,
         * StringUtil.nvl(data.getCnsltngKndCdNm(), "")) .putData(4,
         * StringUtil.nvl(data.getCnstntNm(), "")) .putData(5,
         * StringUtil.nvl(data.getSttsCdNm(), "")) .putData(6,
         * StringUtil.nvl(dateFormat.format(data.getRegDt()), "")) .putData(7,
         * (StringUtil.nvl(data.getVstDe(),"")=="")?
         * "-":data.getVstDe()+" "+data.getVstBgngDt()+" ~ "+data.getVstEndDt()); return
         * true; } ); excelDownloadUtil.excelDownload(response, "컨설팅 검색결과");
         */
    }
    /**
     * @Title : cnsltngResultInfo
     * @Description : 컨설팅 신청정보 상세화면 화면 이동
     * @param SiteApplyVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/consultResult.html")
    public String consultResult(CnsltngResultVo cnsltngResultVo, Model model, @UserInfo UserVo user) throws Exception {
        
        cnsltngResultVo.setUser(user);
        CnsltngResultVo resVo = cnsltngService.selectCnsltngtResult(cnsltngResultVo);
        
        if (resVo.getFilegrpid() != null && !resVo.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(resVo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("fileList",fileList );
        } else {
            model.addAttribute("fileList", null);
        }
        model.addAttribute("resultVo", resVo);
        return "mng/cnsltng/cnsltngResultForm";
    }
    /**
     * @Title : insertCnsltngResult
     * @Description : 컨설팅 결과 정보 등록  
     * @param CnsltngResultVo cnsltngResultVo
     * @param user          로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/cnsltng/insertCnsltngResult.do")
    @ResponseBody
    @Transactional
    public Map<String, Object> insertCnsltngResult(@Valid CnsltngResultVo cnsltngResultVo,BindingResult bindingResult1,CnsltngVo cnsltngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }
        
        int retVal = 0;
        cnsltngResultVo.setUser(user);
        
        if(!CommonUtil.isEmpty(cnsltngResultVo.getVstBgngDt()) &&!CommonUtil.isEmpty(cnsltngResultVo.getVstEndDt())) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault());
            cnsltngResultVo.setVstBgngDate(dtFormat.parse("1970-01-01 "+cnsltngResultVo.getVstBgngDt()));
            cnsltngResultVo.setVstEndDate(dtFormat.parse("1970-01-01 "+cnsltngResultVo.getVstEndDt()));
        }
        
        retVal = cnsltngService.mergeCnsltngRslt(cnsltngResultVo);
        
        if(cnsltngResultVo.getTmprSaveYn().equals("N")) {
            cnsltngVo.setSttsCd("131103");
        }else {
            cnsltngVo.setSttsCd("131102");
        }
        cnsltngService.updateCnsltng(cnsltngVo);
        
        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "저장 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "저장 실패했습니다.");
        }
        return map;
    }
    /**
     * @Title : cnsltngEval
     * @Description : 컨설팅 평가 탭 
     * @param CnsltngVo 인자
     * @param Model      인자
     * @throws Exception
     * @return String 이동화면경로
     */
    @RequestMapping(value = "/mng/cnsltng/cnsltngEval.html")
    public String cnsltngEval(CnsltngVo cnsltngVo, CnsltngResultVo cnsltngResultVo, Model model, @UserInfo UserVo user) throws Exception {
        
        List<Map<String,Object>> list = new ArrayList();
        List<Map<String,Object>> exlist = new ArrayList();
        
        CnsltngResultVo resVo = cnsltngService.selectCnsltngtResult(cnsltngResultVo);
        model.addAttribute("resultVo", resVo);
        
        if(resVo.getSrvyid()!=null && resVo.getSrvyid() != 0) {
            cnsltngVo.setSrvyid(resVo.getSrvyid());
            list = cnsltngService.selectCnsltngtSrvyResult(cnsltngVo);
            if(list != null && list.size() > 0 ) {
                String[] exArr = (list.get(0).get("EXSTR")).toString().split(",");
                for(String str : exArr) {
                    String[] strArr = str.split("/");
                    Map<String,Object> exMap = new HashMap();
                    exMap.put("INDEX", strArr[0]);
                    exMap.put("EX", strArr[1]);
                    exlist.add(exMap);
                }
            }
            model.addAttribute("exCnt", exlist.size());
            model.addAttribute("exlist", exlist);
            model.addAttribute("srvyList", list);
        }else {
            model.addAttribute("srvyList", null);
        }
        
        cnsltngVo.setUser(user);
        return "mng/cnsltng/cnsltngSurveyForm";
        
    }
}
