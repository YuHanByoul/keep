/**
 * 
 */
package com.kbrainc.plum.mng.eduInst.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.mng.eduInst.model.EduExprtVo;
import com.kbrainc.plum.mng.eduInst.model.EduInstVo;
import com.kbrainc.plum.mng.eduInst.model.ReqUserVo;
import com.kbrainc.plum.mng.eduInst.model.SchdlVo;
import com.kbrainc.plum.mng.eduInst.model.SeePrgrmVo;
import com.kbrainc.plum.mng.eduInst.model.SupplementVo;
import com.kbrainc.plum.mng.eduInst.service.EduInstService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstServiceImpl;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 사회환경교육기관 지정 > 신청/결과관리 컨트롤러 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.controller
* - EduInstController.java
* </pre> 
*
* @ClassName : EduInstController
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class EduInstController {

    @Autowired
    private EduInstService eduInstService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private InstServiceImpl instService;
    
    /**
    * 신청/결과관리 화면으로 이동. 
    *
    * @Title : instDsgnListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/eduInst/instDsgnListForm.html")
    public String instDsgnListForm() throws Exception {
        return "mng/eduInst/instDsgnList";
    }
    
    /**
    * 신청/결과관리 리스트 조회. 
    *
    * @Title : selectInstDsgnList
    * @Description : TODO
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/eduInst/selectInstDsgnList.do")
    @ResponseBody
    public Map<String, Object> selectInstDsgnList(EduInstVo eduInstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<EduInstVo> result = this.eduInstService.selectInstDsgnList(eduInstVo);
        
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
    * 신청/결과관리 목록 엑셀 다운로드. 
    *
    * @Title : instDsgnListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param eduInstVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/eduInst/instDsgnListExcelDownload.do")
    public void instDsgnListExcelDownload(HttpServletRequest request, HttpServletResponse response, EduInstVo eduInstVo) throws Exception {
        eduInstService.instDsgnListExcelDownload(eduInstVo, response, request);
    }
    
    /**
    * 신청/결과관리 상세 조회. 
    *
    * @Title : detailInstDsgnForm
    * @Description : TODO
    * @param eduInstVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/eduInst/instDsgnDetailForm.html")
    public String instDsgnDetailForm(EduInstVo eduInstVo, Model model) throws Exception {
        
        EduInstVo instDsgnInfoVo = new EduInstVo();


        if(null != eduInstVo.getAplyid() && 0 != eduInstVo.getAplyid()) {
            instDsgnInfoVo = eduInstService.selectInstDsgnInfo(eduInstVo);
            //첨부파일
            //if (!StringUtil.nvl(instDsgnInfoVo.getFilegrpid()).equals("") && !StringUtil.nvl(instDsgnInfoVo.getFilegrpid()).equals(0)) {
            if (instDsgnInfoVo.getFilegrpid() != null && instDsgnInfoVo.getFilegrpid() != 0) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(instDsgnInfoVo.getFilegrpid());
                model.addAttribute("fileList", fileService.getFileList(fileVo));
            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }

        }else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        InstVo instVo = new InstVo();
        model.addAttribute("typeCdList", instService.selectInstTypeCdList(instVo));
        model.addAttribute("instDsgnInfo", instDsgnInfoVo);
        
        return "mng/eduInst/instDsgnDetailForm";
    }
    
    /**
    * 지정신청 진행상태코드 수정
    *
    * @Title : updateSttsCd
    * @Description : 지정신청 진행상태코드 수정
    * @param eduInstVo
    * @param model
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/eduInst/updateSttsCd.do")
    @ResponseBody
    public Map<String, Object> updateSttsCd(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0 ;
        eduInstVo.setUser(user);
        retVal = this.eduInstService.updateSttsCd(eduInstVo);
        if (retVal >= 1) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "진행상태를 변경하였습니다.");
        } else if(retVal < 1){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "해당 하는 신청ID가 없습니다.");
        }else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "진행상태 변경이 실패 했습니다.");
        }

        return resultMap;
    }    
    
    /**
     * @Title : dsgnInfoChgPopup
     * @Description : 지정정보변경팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduInst/instDsgnInfoChgPopup.html")
    public String dsgnInfoChgPopup(EduInstVo eduInstVo, Model model) throws Exception {
        EduInstVo dsgnDsctnInfo = new EduInstVo();
        EduInstVo eduInstSelectVo = new EduInstVo();
        model.addAttribute("callSe", eduInstVo.getCallSe());
        //지정내역 조회
        List<EduInstVo> dsgnDsctnList =  this.eduInstService.selectDsgnDsctn(eduInstVo);


        //최초 지정승인
        if(dsgnDsctnList.size() == 0) {
            //지정번호 조회
            eduInstSelectVo = this.eduInstService.selectDsgnNo(eduInstVo);
            //BeanUtils.copyProperties(eduInstVo, dsgnDsctnInfo);
            dsgnDsctnInfo.setAplyid(eduInstSelectVo.getAplyid());
            dsgnDsctnInfo.setDsgnNo(eduInstSelectVo.getDsgnNo());
            dsgnDsctnInfo.setDsgnDe(eduInstSelectVo.getDsgnDe());
            dsgnDsctnInfo.setDsctnid(eduInstSelectVo.getDsctnid());
            dsgnDsctnInfo.setDsgnCnclDe(eduInstSelectVo.getDsgnCnclDe());

        }else {
            eduInstVo.setDsctnid(dsgnDsctnList.get(dsgnDsctnList.size() -1).getDsctnid());
            dsgnDsctnInfo = this.eduInstService.selectDsgnDsctnInfo(eduInstVo);
        }

        model.addAttribute("dsgnDsctnInfo", dsgnDsctnInfo);
        return "mng/eduInst/instDsgnInfoChgPopup";
    }
    
    /**
     * 지정내역 저장
     *
     * @Title       : insertDsgnDsctn
     * @Description : 지정내역 저장
     * @param EduInstVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduInst/insertDsgnDsctn.do")
    @ResponseBody
    public Map<String, Object> insertDsgnDsctn(EduInstVo eduInstVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        eduInstVo.setUser(user);

        retVal = this.eduInstService.selectDsgnNoDupChk(eduInstVo);
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "중복되는 지정번호가 존재합니다. 지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.");
            return resultMap;
        }
        retVal = this.eduInstService.insertDsgnDsctn(eduInstVo);
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
     * 지정내역 변경
     *
     * @Title       : updateDsgnDsctn
     * @Description : 지정내역 변경
     * @param EduInstVo 객체
     * @param bindingResult 유효성검증결과
     * @param user 사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/eduInst/updateDsgnDsctn.do")
    @ResponseBody
    public Map<String, Object> updateDsgnDsctn(@Valid EduInstVo eduInstVo, BindingResult bindingResult1, @UserInfo UserVo user) throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        retVal = 0;
        if(!"".equals(eduInstVo.getChkVal()) ){
            retVal = this.eduInstService.selectDsgnNoDupChk(eduInstVo);
            if(retVal > 0) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "중복되는 지정번호가 존재합니다. 지정번호를 변경 후 다시 시도(저장)하시기 바랍니다.");
            }
            return resultMap;
        }
        eduInstVo.setUser(user);
        retVal = this.eduInstService.updateDsgnDsctn(eduInstVo);
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
    * 신청정보 수정. 
    *
    * @Title : updateInstDsgnInfo
    * @Description : TODO
    * @param reqUserVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/eduInst/updateInstDsgnInfo.do")
    @ResponseBody
    public Map<String, Object> updateInstDsgnInfo(@Valid EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        int retVal = 0;
        eduInstVo.setUser(user);
        
        retVal = this.eduInstService.updateInstDsgnInfo(eduInstVo);
        
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
    * 운영계획 개요 화면 이동
    *
    * @Title : operPlanOutl
    * @Description : 운영계획 개요 화면 이동
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/operPlanOutlForm.html")
    public String operPlanOutl(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {

        EduInstVo planOutlInfo = null;

        model.addAttribute("loginUserid", user.getUserid());
        if(null != eduInstVo.getAplyid() && 0 != eduInstVo.getAplyid()) {
            planOutlInfo = eduInstService.selectOperPlan(eduInstVo);

            // 운영계획 첨부파일
            //if (!StringUtil.nvl(planOutlInfo.getAtchFilegrpid()).equals("") && !StringUtil.nvl(planOutlInfo.getAtchFilegrpid()).equals(0)) {
            if (planOutlInfo.getAtchFilegrpid() != null && planOutlInfo.getAtchFilegrpid() != 0) {                
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(planOutlInfo.getAtchFilegrpid());
                model.addAttribute("fileList", fileService.getFileList(fileVo));
            } else {
                model.addAttribute("fileList", Collections.emptyList());
            }

        }else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        model.addAttribute("sidoList", commonService.selectCtprvnList());
        model.addAttribute("planOutlInfo", planOutlInfo);

        model.addAttribute("planOutlInfo", eduInstService.selectOperPlan(eduInstVo));

        return "mng/eduInst/operPlanOutlForm";
    }

    /**
    * 운영계획 개요 등록
    *
    * @Title : insertOperPlan
    * @Description : 운영계획 개요 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/eduInst/insertOperPlan.do")
    @ResponseBody
    public Map<String, Object> insertOperPlan(@Valid EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertOperPlan(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "등록에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패하였습니다");
        }

        return resultMap;
    }

    /**
    * 운영계획개요 수정
    *
    * @Title : updateOperPlan
    * @Description : 운영계획개요 수정
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/eduInst/updateOperPlan.do")
    @ResponseBody
    public Map<String, Object> updateOperPlan(@Valid EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.updateOperPlan(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "수정에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패하였습니다");
        }

        return resultMap;
    }
    
    /**
    * 추진일정 화면 이동
    *
    * @Title : propSchdlForm
    * @Description : 추진일정 화면 이동
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/propSchdlForm.html")
    public String propSchdlForm(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        List<SchdlVo> schdlList = null;
        schdlList = eduInstService.selectSchdlList(eduInstVo);

        model.addAttribute("eduInstVo", eduInstVo);
        model.addAttribute("schdlList", schdlList);
        return "mng/eduInst/propSchdlForm";
    }

    /**
    * 추진일정 등록
    *
    * @Title : insertPropSchdl
    * @Description : 추진일정 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/eduInst/insertPropSchdl.do")
    @ResponseBody
    public Map<String, Object> insertPropSchdl(@RequestBody EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertPropSchdl(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);

            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }

        return resultMap;
    }
    
    /**
    * 교육전문인력현황 화면 이동
    *
    * @Title : mnPwrSttusForm
    * @Description : 교육전문인력현황 화면 이동
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/mnPwrSttusForm.html")
    public String mnPwrSttusForm(EduInstVo eduInstVo,Model model, @UserInfo UserVo user) throws Exception {
        List<EduExprtVo> eduExprtList = null;
        eduExprtList = eduInstService.selectEduExprtList(eduInstVo);

        for (EduExprtVo eduExprt : eduExprtList) {
            //if (!StringUtil.nvl(eduExprt.getAtchFilegrpid()).equals("") && !StringUtil.nvl(eduExprt.getAtchFilegrpid()).equals(0)) {
            if (eduExprt.getAtchFilegrpid() != null && eduExprt.getAtchFilegrpid() != 0) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(eduExprt.getAtchFilegrpid());
                eduExprt.setFileList(fileService.getFileList(fileVo));
            } else {
                eduExprt.setFileList(Collections.emptyList());
            }
        }

        model.addAttribute("eduInstVo", eduInstVo);
        model.addAttribute("eduExprtList", eduExprtList);

        return "mng/eduInst/mnPwrSttusForm";
    }

    /**
    * 교육전문인력 등록
    *
    * @Title : insertMnPwrSttus
    * @Description : 교육전문인력 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/eduInst/insertEduExprt.do")
    @ResponseBody
    public Map<String, Object> insertEduExprt(@RequestBody EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertEduExprt(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }

        return resultMap;
    }
    
    /**
    * 교육프로그램 보유현황 화면 이동
    *
    * @Title : hldngSttsForm
    * @Description : 교육프로그램 보유현황 화면 이동
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/hldngSttsForm.html")
    public String hldngSttsForm(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        List<SeePrgrmVo> seePrgrmList = null;
        seePrgrmList = eduInstService.selectSeePrgrmList(eduInstVo);

        model.addAttribute("eduInstVo", eduInstVo);
        model.addAttribute("seePrgrmList", seePrgrmList);

        return "mng/eduInst/hldngSttsForm";
    }

    /**
    * 프로그램 검색 팝업
    *
    * @Title : eduPrgrmPopup
    * @Description : 프로그램 검색 팝업
    * @param eduInstVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/eduPrgrmPopup.html")
    public String eduPrgrmPopup(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        eduInstVo.setAplcntid(user.getUserid());
        model.addAttribute("eduInstVo", eduInstVo);


        return "mng/eduInst/eduPrgrmPopup";
    }

    /**
    * 지정프로그램 목록 조회
    *
    * @Title : selectDsgnPrgrmList
    * @Description : 지정프로그램 목록 조회
    * @param eduInstVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/mng/eduInst/selectDsgnPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectDsgnPrgrmList(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<>();
        eduInstVo.setAplcntid(user.getUserid());
        List<SeePrgrmVo> list = eduInstService.selectDsgnPrgrmList(eduInstVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
            response.put("pagination", PaginationUtil.getFrontPaginationHtml(list.get(0).getTotalPage(), list.get(0).getPageNumber(), 10));
        } else {
            response.put("totalCount", 0);
        }
        response.put("list", list);
        return response;

    }


    /**
    * 교육프로그램보유현황 등록
    *
    * @Title : insertHldngStts
    * @Description : 교육프로그램보유현황 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/eduInst/insertHldngStts.do")
    @ResponseBody
    public Map<String, Object> insertHldngStts(@RequestBody EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertHldngStts(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }

        return resultMap;
    }

    /**
    * 교육시설 및 설비현황 화면 이동
    *
    * @Title : fcltSttsForm
    * @Description : 교육시설 및 설비현황 화면 이동
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/mng/eduInst/fcltSttsForm.html")
    public String fcltSttsForm(EduInstVo eduInstVo, Model model, @UserInfo UserVo user) throws Exception {
        model.addAttribute("eduInstVo", eduInstService.selectSeeFclt(eduInstVo));

        eduInstVo.setSeCd("251101");    //강의실
        model.addAttribute("lctrumList1", eduInstService.selectLctrumList(eduInstVo));
        eduInstVo.setSeCd("251102");    //실습실
        model.addAttribute("lctrumList2", eduInstService.selectLctrumList(eduInstVo));

        model.addAttribute("eqpList", eduInstService.selectFcltEqpList(eduInstVo));

        return "mng/eduInst/fcltSttsForm";
    }

    /**
    * 교육시설 및 설비현황 등록
    *
    * @Title : insertFcltStts
    * @Description : 교육시설 및 설비현황 등록
    * @param eduInstVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/mng/eduInst/insertFcltStts.do")
    public Map<String, Object> insertFcltStts(@RequestBody EduInstVo eduInstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        int retVal = 0;
        eduInstVo.setUser(user);
        retVal = eduInstService.insertFcltStts(eduInstVo);

        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("aplyid", eduInstVo.getAplyid());
            resultMap.put("msg", "저장에 성공하였습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다");
        }
        return resultMap;
    }
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectSuppList
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/eduInst/selectSuppList.do")
    @ResponseBody
    public Map<String, Object> selectSuppList(SupplementVo supplementVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SupplementVo> result = this.eduInstService.selectSplmntList(supplementVo);
        
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
    * 담당자 검색 팝업. 
    *
    * @Title : userSrchPopup
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/eduInst/userSrchPopup.html")
    public String userSrchPopup(ReqUserVo reqUserVo, Model model) throws Exception {
        model.addAttribute("instid", reqUserVo.getInstid());
        model.addAttribute("fldCd", reqUserVo.getFldCd());
        model.addAttribute("pcntstid", reqUserVo.getPcntstid());

        return "mng/eduInst/userSrchPopup";
    }

    /**
    * 담당자 조회. 
    *
    * @Title : selectUserList
    * @Description : TODO
    * @param reqUserVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/eduInst/selectUserList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(ReqUserVo reqUserVo) throws Exception {
       Map<String, Object> resultMap = new HashMap<>();
        List<ReqUserVo> result = null;

        result = eduInstService.selectUserList(reqUserVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("userList", result);

        return resultMap;
    }
    
    /**
    * 보완요청 팝업. 
    *
    * @Title : suppPopup
    * @Description : TODO
    * @param supplementVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/eduInst/suppPopup.html")
    public String suppPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = new SupplementVo();

        if (supplementVo != null) {
            if (!"201101".equals(supplementVo.getAnsSttsCd())) {
                List<SupplementVo> result = this.eduInstService.selectSplmntList(supplementVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            } else {
                detail.setSplmntid(supplementVo.getSplmntid());
                detail.setAplyid(supplementVo.getAplyid());
                detail.setAnsSttsCd(supplementVo.getAnsSttsCd());
            }
        }
        model.addAttribute("supplementVo", detail);
        model.addAttribute("aplyid", supplementVo.getAplyid());
        
        return "mng/eduInst/suppPopup";
    }
    
    /**
    * 보완요청. 
    *
    * @Title : insertSplmnt
    * @Description : TODO
    * @param supplementVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/eduInst/insertSplmnt.do")
    @ResponseBody
    public Map<String, Object> insertSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        supplementVo.setUser(user);
        
        int retVal = this.eduInstService.insertSplmnt(supplementVo);
        
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
    * 보완요청 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/eduInst/updateSplmnt.do")
    @ResponseBody
    public Map<String, Object> updateSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        supplementVo.setUser(user);
        
        int retVal = this.eduInstService.updateSplmnt(supplementVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "수정에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "수정에 실패했습니다.");
        }
        
        return resultMap;
    }
}
