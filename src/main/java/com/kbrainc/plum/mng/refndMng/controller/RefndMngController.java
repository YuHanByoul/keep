package com.kbrainc.plum.mng.refndMng.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.mng.refndMng.service.RefndMngService;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.mng.spce.service.SpceService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 시설관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : RefndMngController
* @Description : 시설 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class RefndMngController {
    
    @Autowired
    private RefndMngService refndMngService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private InstService instService;
    
    @Autowired
    private SpceService spceService;
    
    /**
    * 시설관리 리스트화면으로 이동
    *
    * @Title : refndMngList
    * @Description : 시설관리 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/refndMng/refndMngList.html")
    public String refndMngList() throws Exception {
        return "mng/refndMng/refndMngList";
    }
    
    /**
     * 상세(탭) 화면
     *
     * @Title       : refndMngDetail 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndMngDetail.html")
    public String refndMngDetail(RefndMngVo refndMngVo, Model model, SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        model.addAttribute("param", refndMngVo);
        // 공간 보유개수 조회
        spceVo.setFcltid(refndMngVo.getFcltid());
        spceVo.setUser(user);
        model.addAttribute("spceList", spceService.selectSpceList(spceVo));
        return "mng/refndMng/refndMngDetail";
    }
    
    /**
     * 등록 화면
     *
     * @Title       : refndMngForm 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndMngForm.html")
    public String refndMngForm(RefndMngVo refndMngVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        model.addAttribute("user", user);
        
        // 기관회원일 경우
        if( user.getInstid() != null ) {
            instVo.setInstid(user.getInstid());
            model.addAttribute("inst", instService.selectInstInfo(instVo));
        }
        return "mng/refndMng/refndMngForm";
    }
    
    /**
     * 공간정보 조회화면
     *
     * @Title       : refndMngSpceList
     * @Description : 공간정보 조회화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/refndMng/refndMngSpceList.html")
    public String refndMngSpceList() throws Exception {
        return "mng/refndMng/refndMngSpceList";
    }
    
    
    /**
    * 공간정보 조회 기능
    *
    * @Title : selectRefndMngSpceList
    * @Description : 공간정보 조회 기능
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/selectRefndMngSpceList.do")
    @ResponseBody
    public Map<String, Object> selectRefndMngSpceList(RefndMngVo refndMngVo, @UserInfo UserVo user, SpceVo spceVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<SpceVo> result = null;
        
        // 공간 보유개수 조회
        spceVo.setFcltid(refndMngVo.getFcltid());
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
    
    @RequestMapping(value = "/mng/refndMng/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        
        return "mng/refndMng/instSearchPopup";
    }
    
    /**
     * 시설 등록
     *
     * @Title : insertRefndMng
     * @Description : 시설 등록
     * @param refndMngVo 시설 객체
     * @param bindingResult 시설 유효성 검증결과
     * @param user 사용자 세션정보
     * @throws Exception 예외
     * @return Map<String,Object>
     */
     @RequestMapping(value = "/mng/refndMng/insertRefndMng.do")
     @ResponseBody
     public Map<String, Object> insertRefndMng(@Valid RefndMngVo refndMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
         Map<String, Object> resultMap = new HashMap<String, Object>();
         
         if (bindingResult.hasErrors()) {
             FieldError fieldError = bindingResult.getFieldError();
             if (fieldError != null) {
                 resultMap.put("msg", fieldError.getDefaultMessage());
             }
             return resultMap;
         }
         
         refndMngVo.setUser(user);

         // 시설명 중복확인
         int cnt = 0;
         cnt = refndMngService.checkDuplicateFcltNm(refndMngVo);
         RefndMngVo.dupCnt = cnt;
         if (cnt > 0) {
             resultMap.put("result", Constant.REST_API_RESULT_FAIL);
             resultMap.put("msg", "시설명이 중복되었습니다.");
             
             return resultMap;
         }
         
         // 시설번호 생성        
         String fcltVal = "FA-".concat(Integer.toString(refndMngVo.getInstid()));
         RefndMngVo.fcltVal = fcltVal;
         RefndMngVo result = refndMngService.selectFcltNo(refndMngVo);
         refndMngVo.fcltNo = fcltVal.concat("-").concat(result.fcltNo);
         
         int retVal = 0;
         retVal = refndMngService.insertRefndMng(refndMngVo);
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
    * @Title : refndMngUpdate
    * @Description : 시설 수정화면으로 이동
    * @param refndMngVo 시설 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/refndMng/refndMngUpdate.html")
    public String refndMngUpdate(RefndMngVo refndMngVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        
        model.addAttribute("param", refndMngVo);
        
        refndMngVo.setUser(user);
        RefndMngVo resultVo = refndMngService.selectRefndMngInfo(refndMngVo);
        
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
        
        model.addAttribute("refndMng", resultVo);
        
        return "mng/refndMng/refndMngUpdate";
    }
   
    /**
    * 시설 리스트 기능
    *
    * @Title : selectRefndMngList
    * @Description : 시설 리스트 기능
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/selectRefndMngList.do")
    @ResponseBody
    public Map<String, Object> selectRefndMngList(RefndMngVo refndMngVo, @UserInfo UserVo user, InstVo instVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<RefndMngVo> result = null;
        
        // 기관회원일 경우
        if( user.getInstid() != null ) {
            refndMngVo.setInstid(user.getInstid());
        }
        result =  refndMngService.selectRefndMngList(refndMngVo);
        
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
    * @Title : updateRefndMng
    * @Description : 시설 수정 기능
    * @param refndMngVo 시설 객체
    * @param bindingResult 시설 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/updateRefndMng.do")
    @ResponseBody
    public Map<String, Object> updateRefndMng(@Valid RefndMngVo refndMngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        refndMngVo.setUser(user);

        int retVal = 0;
                
        retVal = refndMngService.updateRefndMng(refndMngVo);
        
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
    * @Title : deleteRefndMng
    * @Description : 시설 삭제 기능
    * @param refndMngVo refndMngVo
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/refndMng/deleteRefndMng.do")
    @ResponseBody
    public Map<String, Object> deleteRefndMng(RefndMngVo refndMngVo, SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        // 시설 삭제 전, 공간부터 삭제(공간삭제 전 공간예약있는지부터 조회해서 삭제하기때문에 공간삭제 바로 호출)
        // 공간 보유개수 조회
        spceVo.setFcltid(refndMngVo.getFcltid());
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
        
        retVal = refndMngService.deleteRefndMng(refndMngVo);
        
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
