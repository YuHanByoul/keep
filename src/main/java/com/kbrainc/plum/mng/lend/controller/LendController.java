package com.kbrainc.plum.mng.lend.controller;

import java.util.ArrayList;
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

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.lend.model.LendRndPackageindvdVo;
import com.kbrainc.plum.mng.lend.model.LendRndVo;
import com.kbrainc.plum.mng.lend.model.LendVo;
import com.kbrainc.plum.mng.lend.service.LendService;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 대여 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.lend.controller - LendController.java
 * </pre>
 *
 * @ClassName : LendController
 * @Description :  대여 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class LendController {

    @Autowired
    private LendService lendService;
    
    @Autowired
    private FileService fileService;
    
    /**
     * 대여 관리 화면
     *
     * @Title : lendList
     * @Description : 대여 관리 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/lendList.html")
    public String lendList(Model model) throws Exception {
        return "mng/lend/lendList";
    }
    
    /**
     * 대여 목록 조회
     *
     * @Title : selectLendList
     * @Description : 대여 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/selectLendList.do")
    @ResponseBody
    public Map<String, Object> selectTchaidList( LendVo lendVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<LendVo> result = null;
        lendVo.setUser(user);
        
        result = lendService.selectLendList(lendVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 대여모집 등록 폼 
     *
     * @Title : insertLendForm
     * @Description : 대여모집 등록 폼 
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/insertLendForm.html")
    public String insertLendForm(LendVo lendVo, Model model) throws Exception {
        PackageVo packageVo = new PackageVo();
        model.addAttribute("packageLsit", lendService.selectPackageList(packageVo));
        return "mng/lend/lendInsertForm";
    }
    /**
     * 대여모집 등록
     *
     * @Title : insertLend
     * @Description : 대여모집 등록
     * @param LendVo LendVo 객체
     * @param bindingResult1 LendVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/insertLend.do")
    @ResponseBody
    public Map<String, Object> insertLend(@Valid @RequestBody LendVo lendVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        lendVo.setUser(user);
        
        if(lendService.checkPackageDuplicationYn(lendVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "같은 기간에 꾸러미가 중복된 대여 모집이 있습니다.");
            return resultMap;
        }
        
        retVal = lendService.insertLend(lendVo);
        
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
     * 대여모집 상세(수정) 폼 
     *
     * @Title : updateLend
     * @Description : 대여모집 상세(수정) 폼 
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/updateLend.html")
    public String updateLend(LendVo lendVo , Model model,@UserInfo UserVo user) throws Exception {
        
        PackageVo packageVo = new PackageVo();
        model.addAttribute("packageLsit", lendService.selectPackageList(packageVo));
        
        lendVo.setUser(user);
        LendVo resVo =  lendService.selectLend(lendVo);
        model.addAttribute("resVo", resVo);
        
        FileVo fileVo = new FileVo();
        fileVo.setUser(user);
        
        //대표파일
        if (resVo.getRprsImgFileid() != null && !resVo.getRprsImgFileid().equals(0)) {
            fileVo.setFileid(Integer.parseInt(resVo.getRprsImgFileid().toString()));
            FileVo logoVo= fileService.getFileInfo(fileVo);
            model.addAttribute("logoFile",logoVo );
        } else {
            model.addAttribute("logoFile", null);
        }
        
        //상세이미지 
        if (resVo.getDtlImgFilegrpid() != null && !resVo.getDtlImgFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resVo.getDtlImgFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("dtlImgFileList",fileList );
            model.addAttribute("dtlImgFileListCnt", fileList.size());
        } else {
            model.addAttribute("dtlImgFileList", null);
            model.addAttribute("dtlImgFileListCnt", 0);
        }
        
        //지도 이미지 
        if (resVo.getMapFilegrpid() != null && !resVo.getMapFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resVo.getMapFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("mapFileList",fileList );
            model.addAttribute("mapFileListCnt", fileList.size());
        } else {
            model.addAttribute("mapFileList", null);
            model.addAttribute("mapFileListCnt", 0);
        }
        
        //교육사진 
        if (resVo.getEduPhotoFilegrpid() != null && !resVo.getEduPhotoFilegrpid().equals(0)) {
            fileVo.setFilegrpid(Integer.parseInt(resVo.getEduPhotoFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileService.getFileList(fileVo);
            model.addAttribute("eduPhotoFileList",fileList );
            model.addAttribute("eduPhotoFileListCnt", fileList.size());
        } else {
            model.addAttribute("eduPhotoFileList", null);
            model.addAttribute("eduPhotoFileListCnt", 0);
        }
        
        List<LendRndVo> lendRndList = lendService.selectLendRndList(resVo);
        for(LendRndVo vo :lendRndList) {
            List<LendRndPackageindvdVo> lendRndPackageindvdList = lendService.selectLendRndPackageindvdList(vo);
            vo.setLendRndPackageindvdVoList(lendRndPackageindvdList);
        }
        model.addAttribute("lendRndList", lendRndList);
        
        return "mng/lend/lendUpdate";
    }
    /**
     * 대여모집 수정
     *
     * @Title : insertLend
     * @Description : 대여모집 수정
     * @param LendVo LendVo 객체
     * @param bindingResult1 LendVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/updateLend.do")
    @ResponseBody
    public Map<String, Object> updateLend(@Valid @RequestBody LendVo lendVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        lendVo.setUser(user);
        
        String isThereReservation = "N";
        
        if(lendService.checkPackageDuplicationYn(lendVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "같은 기간에 꾸러미가 중복된 대여 모집이 있습니다.");
            return resultMap;
        }
        
        List<Integer> deleteIds =  new ArrayList();
        deleteIds.add(lendVo.getRcritid());
        lendVo.setDeleteIds(deleteIds);
        //대여 신청건이 존재 하는지 여부에 일정(차시)수정 가능 여부 판단   
        if(lendService.selectLendApplyYn(lendVo).equals("Y")){
            isThereReservation = "Y";
        }
        
        lendVo.setIsThereReservation(isThereReservation);
        retVal = lendService.udateLend(lendVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", (isThereReservation.equals("N"))?"저장에 성공하였습니다.":"이미 대여 신청한 이력이 있어 차시 일정을 제외한 정보만 저장되었습니다. ");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        return resultMap;
    }
    /**
     * 대여 삭제
     *
     * @Title : deleteLend
     * @Description :  대여 삭제
     * @param TchaidVo TchaidVo 객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/deleteLend.do")
    @ResponseBody
    public Map<String, Object> deleteLend(@Valid LendVo lendVo,@UserInfo UserVo user) throws Exception {
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        int retVal = 0;
        lendVo.setUser(user);
        
        //대여 신청건이 존재 하는지 여부에 따라 삭제 가능여부 판단 
        if(lendService.selectLendApplyYn(lendVo).equals("Y")){
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "이미 대여 신청 이력이 있는 대여모집이 있어 삭제 할 수 없습니다.");
            return resultMap;
        }
        
        retVal = lendService.deleteLend(lendVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "삭제 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "삭제 실패했습니다.");
        }
        
        return resultMap;
    }
    /**
     * 대여모집 꾸러미 정보 호출
     *
     * @Title : selectLendList
     * @Description : 대여모집 꾸러미 정보 호출
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/selectPackageInfo.do")
    @ResponseBody
    public Map<String, Object> selectPackageInfo(PackageVo packageVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageVo> result = null;
        packageVo.setUser(user);
        
        result = lendService.selectPackageList(packageVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 대여모집 꾸러미 검색 팝업 

     *
     * @Title : searchPackageindvdPopup
     * @Description : 대여모집 꾸러미 검색 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/searchPackageindvdPopup.html")
    public String tchaidCmpntCmpstnForm(TchaidVo tchaidVo,Model model) throws Exception {
        model.addAttribute("tchaidVo", tchaidVo);
        return "mng/lend/searchPackageindvdPopup";
    }
    /**
     * 대여모집 꾸러미 검색 목록 호출
     *
     * @Title : selectLendList
     * @Description : 대여 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/lend/selectPackageindvdList.do")
    @ResponseBody
    public Map<String, Object> selectPackageindvdList(PackageindvdVo packageindvdVo,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageindvdVo> result = null;
        packageindvdVo.setUser(user);
        
        result = lendService.selectPackageindvdList(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
       
       
    
}