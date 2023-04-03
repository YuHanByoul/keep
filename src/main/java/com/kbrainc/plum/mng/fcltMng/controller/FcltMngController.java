package com.kbrainc.plum.mng.fcltMng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.kbrainc.plum.mng.fcltMng.model.FcltMngVo;
import com.kbrainc.plum.mng.fcltMng.service.FcltMngService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

/**
* 시설관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.fcltMng.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : FcltMngController
* @Description : 시설 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class FcltMngController {
    
    @Autowired
    private FcltMngService fcltMngService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private InstService instService;
    
    @Autowired
    private SpceService spceService;
    
    /**
    * 시설관리 리스트화면으로 이동
    *
    * @Title : fcltMngList
    * @Description : 시설관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/fcltMng/fcltMngList.html")
    public String fcltMngList() throws Exception {
        return "mng/fcltMng/fcltMngList";
    }
    
    /**
     * 상세(탭) 화면
     *
     * @Title       : fcltMngDetail 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/fcltMng/fcltMngDetail.html")
    public String fcltMngDetail(FcltMngVo fcltMngVo, Model model, SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        model.addAttribute("param", fcltMngVo);
        // 공간 보유개수 조회
        spceVo.setFcltid(fcltMngVo.getFcltid());
        spceVo.setUser(user);
        model.addAttribute("spceList", spceService.selectSpceList(spceVo));
        return "mng/fcltMng/fcltMngDetail";
    }
    
    /**
     * 등록 화면
     *
     * @Title       : fcltMngForm 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/fcltMng/fcltMngForm.html")
    public String fcltMngForm(FcltMngVo fcltMngVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        model.addAttribute("user", user);
        
        // 기관회원일 경우
        if( user.getInstid() != null ) {
            instVo.setInstid(user.getInstid());
            model.addAttribute("inst", instService.selectInstInfo(instVo));
        }
        return "mng/fcltMng/fcltMngForm";
    }
    
    /**
     * 공간정보 조회화면
     *
     * @Title       : fcltMngSpceList
     * @Description : 공간정보 조회화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/fcltMng/fcltMngSpceList.html")
    public String fcltMngSpceList() throws Exception {
        return "mng/fcltMng/fcltMngSpceList";
    }
    
    
    /**
    * 공간정보 조회 기능
    *
    * @Title : selectFcltMngSpceList
    * @Description : 공간정보 조회 기능
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/fcltMng/selectFcltMngSpceList.do")
    @ResponseBody
    public Map<String, Object> selectFcltMngSpceList(FcltMngVo fcltMngVo, @UserInfo UserVo user, SpceVo spceVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SpceVo> result = null;
        
        // 공간 보유개수 조회
        spceVo.setFcltid(fcltMngVo.getFcltid());
        spceVo.setUser(user);
        result = spceService.selectSpceList(spceVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/mng/fcltMng/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        
        return "mng/fcltMng/instSearchPopup";
    }
    
    /**
     * 시설 등록
     *
     * @Title : insertFcltMng
     * @Description : 시설 등록
     * @param fcltMngVo 시설 객체
     * @param bindingResult 시설 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/fcltMng/insertFcltMng.do")
     @ResponseBody
     public Map<String, Object> insertFcltMng(@Valid FcltMngVo fcltMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         fcltMngVo.setUser(user);

         // 시설명 중복확인
         int cnt = 0;
         cnt = fcltMngService.checkDuplicateFcltNm(fcltMngVo);
         FcltMngVo.dupCnt = cnt;
         if (cnt > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "시설명이 중복되었습니다.");
             
             return resultMap;
         }
         
         // 시설번호 생성        
         String fcltVal = "FA-".concat(Integer.toString(fcltMngVo.getInstid()));
         FcltMngVo.fcltVal = fcltVal;
         FcltMngVo result = fcltMngService.selectFcltNo(fcltMngVo);
         fcltMngVo.fcltNo = fcltVal.concat("-").concat(result.fcltNo);
         
         int retVal = 0;
         retVal = fcltMngService.insertFcltMng(fcltMngVo);
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
    * 시설 수정화면으로 이동
    *
    * @Title : fcltMngUpdate
    * @Description : 시설 수정화면으로 이동
    * @param fcltMngVo 시설 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/fcltMng/fcltMngUpdate.html")
    public String fcltMngUpdate(FcltMngVo fcltMngVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        
        model.addAttribute("param", fcltMngVo);
        
        fcltMngVo.setUser(user);
        FcltMngVo resultVo = fcltMngService.selectFcltMngInfo(fcltMngVo);
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        // 기관회원일 경우
        if( user.getInstid() != null ) {
            instVo.setInstid(user.getInstid());
            model.addAttribute("inst", instService.selectInstInfo(instVo));
        }
        
        // 대표 이미지
        if (resultVo.getRprsImgFileid() != null && !resultVo.getRprsImgFileid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getRprsImgFileid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("rprsImgFileMap",fileList );
            model.addAttribute("rprsCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("rprsImgFileMap", null);
            model.addAttribute("rprsCurrentFileCnt", 0);
        }

        // 상세 이미지
        if (resultVo.getDtlImgFilegrpid() != null && !resultVo.getDtlImgFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getDtlImgFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("dtlImgFileMap",fileList );
            model.addAttribute("dtlCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("dtlImgFileMap", null);
            model.addAttribute("dtlCurrentFileCnt", 0);
        }
        
        // 안내자료
        if (resultVo.getGdncFileid() != null && !resultVo.getGdncFileid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resultVo.getGdncFileid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("gdncFileMap",fileList );
            model.addAttribute("gdncCurrentFileCnt", fileList.size());
        } else {
            model.addAttribute("gdncFileMap", null);
            model.addAttribute("gdncCurrentFileCnt", 0);
        }
        
        model.addAttribute("fcltMng", resultVo);
        
        return "mng/fcltMng/fcltMngUpdate";
    }
   
    /**
    * 시설 리스트 기능
    *
    * @Title : selectFcltMngList
    * @Description : 시설 리스트 기능
    * @param fcltMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/fcltMng/selectFcltMngList.do")
    @ResponseBody
    public Map<String, Object> selectFcltMngList(FcltMngVo fcltMngVo, @UserInfo UserVo user, InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<FcltMngVo> result = null;
        
        // 기관회원일 경우
        if( user.getInstid() != null ) {
            fcltMngVo.setInstid(user.getInstid());
        }
        result =  fcltMngService.selectFcltMngList(fcltMngVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 시설 수정 기능
    *
    * @Title : updateFcltMng
    * @Description : 시설 수정 기능
    * @param fcltMngVo 시설 객체
    * @param bindingResult 시설 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/fcltMng/updateFcltMng.do")
    @ResponseBody
    public Map<String, Object> updateFcltMng(@Valid FcltMngVo fcltMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        fcltMngVo.setUser(user);

        int retVal = 0;
                
        retVal = fcltMngService.updateFcltMng(fcltMngVo);
        
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
    * 시설 삭제 기능
    *
    * @Title : deleteFcltMng
    * @Description : 시설 삭제 기능
    * @param fcltMngVo fcltMngVo
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/fcltMng/deleteFcltMng.do")
    @ResponseBody
    public Map<String, Object> deleteFcltMng(FcltMngVo fcltMngVo, SpceVo spceVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        // 시설 삭제 전, 공간부터 삭제(공간삭제 전 공간예약있는지부터 조회해서 삭제하기때문에 공간삭제 바로 호출)
        // 공간 보유개수 조회
        spceVo.setFcltid(fcltMngVo.getFcltid());
        spceVo.setUser(user);
        List<SpceVo> spceList = spceService.selectSpceList(spceVo);

        // 공간이 1개라도 있을시, 예약까지 조회
        if( spceList.size() > 0 ) {
            String[] spceids = new String[spceList.size()];
            for( int i = 0; i < spceList.size(); i++ ) {
                spceids[i] = Integer.toString(spceList.get(i).getSpceid());
            }
            
            spceVo.setSpceids(spceids);
            
            if(spceService.isThereSpceRsvt(spceVo).equals("Y")) {
                resultMap.put("result", Constant.REST_API_RESULT_FAIL);
                resultMap.put("msg", "예약 신청(내역)건이 존재하여 삭제할 수 없습니다.");
                return resultMap;
            }
            
            retVal = spceService.deleteSpce(spceVo);
        }
        
        retVal = fcltMngService.deleteFcltMng(fcltMngVo);
        
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
