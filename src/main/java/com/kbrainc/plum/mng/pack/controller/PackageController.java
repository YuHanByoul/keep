package com.kbrainc.plum.mng.pack.controller;

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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kbrainc.plum.mng.pack.model.PackageEduTrgtVo;
import com.kbrainc.plum.mng.pack.model.PackageVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdAbnrmlVo;
import com.kbrainc.plum.mng.pack.model.PackageindvdVo;
import com.kbrainc.plum.mng.pack.service.PackageService;
import com.kbrainc.plum.mng.tchaid.model.TchaidVo;
import com.kbrainc.plum.mng.tchaid.service.TchaidService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.QRCodeView;
import com.kbrainc.plum.rte.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 꾸러미 관리 컨트롤러 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.pack.controller - PackageController.java
 * </pre>
 *
 * @ClassName : PackageController
 * @Description :  꾸러미 관리 컨트롤러 클래스.
 * @author : KBRAINC
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller
@Slf4j
public class PackageController {

    @Autowired
    private TchaidService tchaidService;
    
    @Autowired
    private PackageService packageService;
    
    /**
     *  관리 화면
     *
     * @Title : tchaidList
     * @Description : 꾸러미 관리 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageList.html")
    public String packageList(Model model) throws Exception {
        return "mng/package/packageList";
    }
    /**
     * 꾸러미 목록 조회
     *
     * @Title : selectPackageList
     * @Description : 꾸러미 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/selectPackageList.do")
    @ResponseBody
    public Map<String, Object> selectPackageList( PackageVo packageVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageVo> result = null;
        packageVo.setUser(user);
        
        result = packageService.selectPackageList(packageVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 꾸러미 등록 폼 
     *
     * @Title : packageInsertForm
     * @Description : 꾸러미 등록 폼
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageInsertForm.html")
    public String packageInsertForm(PackageVo packageVo , Model model) throws Exception {
        model.addAttribute("param", packageVo);
        
        //교육 주제 리스트 호출         
        Map<String,String> map = new HashMap();
        map.put("cdgrpid", "155");
        model.addAttribute("sbjctCdLsit", tchaidService.selectTchaidCdList(map)  );
        
        return "mng/package/packageInsertForm";
    }
    /**
     * 꾸러미 상세 (수정) 폼 
     *
     * @Title : packageInsertForm
     * @Description : 꾸러미 상세 (수정) 폼
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageDetailForm.html")
    public String packageDetailForm(PackageVo packageVo , Model model) throws Exception {
        
        model.addAttribute("param", packageVo);
        
        //교육 주제 리스트 호출         
        Map<String,String> map = new HashMap();
        map.put("cdgrpid", "155");
        model.addAttribute("sbjctCdLsit",tchaidService.selectTchaidCdList(map));
        model.addAttribute("packageVo",packageService.selectPackage(packageVo));
        model.addAttribute("mySbjctLsit",packageService.selectTchaidEduSbjctList(packageVo));
        
        List<String> trgtCds = new ArrayList();
        List<PackageEduTrgtVo> list = packageService.selectTchaidEduTrgtList(packageVo); 
        for(PackageEduTrgtVo vo :list) {
            trgtCds.add(vo.getEduTrgtCd());
        }
        model.addAttribute("mytrgtCds", String.join(",",trgtCds) );
        model.addAttribute("myTchaidLsit",packageService.selectPackageTchaidList(packageVo));
        
        return "mng/package/packageUpdateForm";
    }
    /**
     * 꾸러미 교구 검색 팝업 
     *
     * @Title : packageInsertForm
     * @Description : 꾸러미 상세 (수정) 폼
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/searchTchaidPopup.html")
    public String searchTchaidPopup(PackageVo packageVo , Model model) throws Exception {
        model.addAttribute("param", packageVo);
        return "mng/package/searchTchaidPopup";
    }
    /**
     * 교구 검색 목록 조회
     *
     * @Title : selectSearchTchaidList
     * @Description : 교구 검색 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/selectSearchTchaidList.do")
    @ResponseBody
    public Map<String, Object> selectSearchTchaidList( TchaidVo tchaidVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<TchaidVo> result = null;
        tchaidVo.setUser(user);
        
        result = tchaidService.selectTchaidList(tchaidVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 꾸러미 등록
     *
     * @Title : insertPackage
     * @Description : 꾸러미 등록
     * @param PackageVo   packageVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/insertPackage.do")
    @ResponseBody
    public Map<String, Object> insertPackage(@Valid @RequestBody PackageVo packageVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        packageVo.setUser(user);
        
        retVal = packageService.insertPackage(packageVo);
        
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
     * 꾸러미 수정
     *
     * @Title : updatePackage
     * @Description : 꾸러미 등록
     * @param PackageVo         packageVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/updatePackage.do")
    @ResponseBody
    public Map<String, Object> updatePackage(@Valid @RequestBody PackageVo packageVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        packageVo.setUser(user);
        
        retVal = packageService.updatePackage(packageVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /******************* 꾸러미 개체 관리 *******************/
    
    /**
     * 꾸러미 개체 상세 폼 
     *
     * @Title : packageInsertForm
     * @Description : 꾸러미 상세 (수정) 폼
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdDetailForm.html")
    public String packageindvdDetailForm(PackageVo packageVo , Model model) throws Exception {
        
        model.addAttribute("param", packageVo);
        model.addAttribute("packageVo",packageService.selectPackage(packageVo));
        model.addAttribute("myTchaidLsit",packageService.selectPackageTchaidList(packageVo));
        
        return "mng/package/packageindvdDetailForm";
    }
    
    /**
     * 꾸러미 개체 목록 조회
     *
     * @Title : selectPackageindvdList
     * @Description : 꾸러미 목록 조회
     * @param CmpntVo CmpntVo 객체
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/selectPackageindvdList.do")
    @ResponseBody
    public Map<String, Object> selectPackageindvdList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageindvdVo> result = null;
        packageindvdVo.setUser(user);
        
        result = packageService.selectPackageindvdList(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 꾸러미 대체 추가 팝업 
     *
     * @Title : addPackageindvdPopup
     * @Description : 꾸러미 추가 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/addPackageindvdPopup.html")
    public String addPackageindvdPopup(PackageVo packageVo , Model model) throws Exception {
        
        model.addAttribute("param", packageVo);
        model.addAttribute("packageTchaidLsit",packageService.selectPackageTchaidList(packageVo));
        return "mng/package/pacakageindvdAddPopup";
    }
    
    /**
     * 꾸러미 개체 등록
     *
     * @Title : insertPackageindvd
     * @Description : 꾸러미 등록
     * @param PackageindvdVo   PackageindvdVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/insertPackageindvd.do")
    @ResponseBody
    public Map<String, Object> insertPackage(@Valid PackageindvdVo packageindvdVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;

        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        packageindvdVo.setUser(user);
        
        retVal = packageService.insertPackageindvdByPackageid(packageindvdVo);
        
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
     * 꾸러미 개체 수정(사용여부 변경) 
     *
     * @Title : modifyPackageindvd
     * @Description : 꾸러미 개체 등록
     * @param PackageindvdVo   PackageindvdVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/modifyPackageindvd.do")
    @ResponseBody
    public Map<String, Object> modifyPackageindvd(@Valid PackageindvdVo packageindvdVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        packageindvdVo.setUser(user);
        
        retVal = packageService.updatePackageindvd(packageindvdVo);
        
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
     * 꾸러미 개체 구성 상세 폼 
     *
     * @Title : packageInsertForm
     * @Description : 꾸러미 상세 (수정) 폼
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdCmpstnDetail.html")
    public String packageindvdCmpstnDetail(PackageindvdVo ackageindvdVo , Model model) throws Exception {
        model.addAttribute("PackageindvdVo",ackageindvdVo);
        model.addAttribute("CmpstnList",packageService.selectPackageindvdTchaidList(ackageindvdVo));
        return "mng/package/packageindvdCmpstnDetail";
    }
    
    /**
     * 꾸러미 개체 입출고 내역 상세 
     *
     * @Title : packageindvdWrhousngDetail
     * @Description : 꾸러미 개체 입출고 내역 상세 
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdWrhousngDetail.html")
    public String packageindvdWrhousngDetail(PackageindvdVo ackageindvdVo , Model model) throws Exception {
        model.addAttribute("PackageindvdVo",ackageindvdVo);
        return "mng/package/packageindvdWrhousngDetail";
    }
    
    /**
     * 꾸러미 개체 이상 내역 상세 
     *
     * @Title : packageindvdAbnrml
     * @Description : 꾸러미 개체 이상 내역 상세
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdAbnrmlDetail.html")
    public String packageindvdAbnrmlDetail(PackageindvdVo ackageindvdVo , Model model) throws Exception {
        model.addAttribute("PackageindvdVo",ackageindvdVo);
        return "mng/package/packageindvdAbnrmlDetail";
    }
    
    /**
     * 꾸러미 개체 입출고 목록 조회
     *
     * @Title : selectPackageindvdList
     * @Description : 꾸러미 개체 입출고 목록 조회
     * @param PackageindvdVo PackageindvdVo 객체
     * @return List<Map<String,Object>> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdWrhousngList.do")
    @ResponseBody
    public Map<String, Object> packageindvdWrhousngList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<Map<String, Object>> result = null;
        packageindvdVo.setUser(user);
        
        result = packageService.selectPackageindvdlendList(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).get("totalcount")));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 꾸러미 개체 입출고 목록 조회
     *
     * @Title : selectPackageindvdList
     * @Description : 꾸러미 개체 입출고 목록 조회
     * @param PackageindvdVo PackageindvdVo 객체
     * @return List<Map<String,Object>> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/selectPackageindvdAbnrmlList.do")
    @ResponseBody
    public Map<String, Object> selectPackageindvdAbnrmlList(PackageindvdVo packageindvdVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        List<PackageindvdAbnrmlVo> result = null;
        packageindvdVo.setUser(user);
        
        result = packageService.selectPackageindvdAbnrmlList(packageindvdVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    /**
     * 꾸러미 개체 이상 등록 팝업 
     *
     * @Title : insertPackageindvdAbnrmPopup
     * @Description : 꾸러미 개체 이상 등록 팝업 호출
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/insertPackageindvdAbnrmPopup.html")
    public String insertPackageindvdAbnrmPopup(PackageindvdVo packageindvdVo , Model model) throws Exception {
        model.addAttribute("resVo",packageService.selectPackageindvd(packageindvdVo));
        model.addAttribute("tchaidCmpstnList",packageService.selectPackageindvdTchaidList(packageindvdVo));
        return "mng/package/pacakageAbnrmlPopup";
    }
    /**
     * 꾸러미 개체 이상 등록  
     *
     * @Title : insertPackageindvdAbnrm
     * @Description : 꾸러미 개체 이상 등록
     * @param PackageindvdVo   PackageindvdVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/insertPackageindvdAbnrm.do")
    @ResponseBody
    public Map<String, Object> insertPackageindvdAbnrm(PackageindvdVo packageindvdVo, @Valid PackageindvdAbnrmlVo packageindvdAbnrmlVo, BindingResult bindingResult1,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        if (bindingResult1.hasErrors()) {
            FieldError fieldError = bindingResult1.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        packageindvdAbnrmlVo.setUser(user);
        retVal = packageService.insertPackageindvdAbnrml(packageindvdAbnrmlVo);
        
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
     * 꾸러미 개체 이상 정상 처리 팝업  
     *
     * @Title : packageindvdNormalizePopup
     * @Description : 꾸러미 개체 이상 처리 팝업 호출
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdNormalizePopup.html")
    public String packageindvdNormalizePopup( PackageindvdAbnrmlVo packageindvdAbnrmlVo ,PackageindvdVo packageindvdVo , Model model) throws Exception {
        model.addAttribute("PackageindvdVo",packageindvdVo);
        model.addAttribute("abnrmlid",packageindvdAbnrmlVo.getAbnrmlid());
        model.addAttribute("resVo",packageService.selectPackageindvd(packageindvdVo));
        model.addAttribute("tchaidCmpstnList",packageService.selectPackageindvdTchaidList(packageindvdVo));
        return "mng/package/pacakageNormalizePopup";
    }
    /**
     * 꾸러미 개체 정상처리  
     *
     * @Title : packageindvdNormalize
     * @Description : 꾸러미 개체 정상처리
     * @param PackageindvdVo   PackageindvdVo객체
     * @param bindingResult1 SpceVo 유효성검증결과
     * @param user           사용자세션정보
     * @return Map<String,Object> 응답결과객체
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdNormalize.do")
    @ResponseBody
    public Map<String, Object> packageindvdNormalize(@RequestBody PackageindvdVo packageindvdVo ,@UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int retVal = 0;
        
        packageindvdVo.setUser(user);
        
        if (packageindvdVo.getMode().equals("cnt")  &&  packageService.selectInvtryOverYn(packageindvdVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "교구 재고를 확인하여주십시오.");
            return resultMap;
        }
        
        retVal = packageService.packageindvdNormalize(packageindvdVo);
        
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "정상처리 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "정상처리에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
     * 꾸러미 개체 QR 팝업 
     *
     * @Title : packageindvdQRPopup
     * @Description : 꾸러미 개체 QR 팝업
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/package/packageindvdQRPopup.html")
    public String packageindvdQRPopup(PackageindvdVo packageindvdVo , Model model) throws Exception {
        
        model.addAttribute("printMode",packageindvdVo.getMode());
        model.addAttribute("resVo",packageService.selectPackageindvd(packageindvdVo));
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String text = String.valueOf(packageindvdVo.getPackageindvdid());
        text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 150, 150);
        
        model.addAttribute("qrImg", "data:image/png;base64,"+QRCodeView.encodeToString(MatrixToImageWriter.toBufferedImage(bitMatrix), "png")) ;
        
        if(StringUtil.isNull(packageindvdVo.getMode()) || !packageindvdVo.getMode().equals("print")) {
            return "mng/package/pacakageQRPopup";
        }else {
            return "mng/package/printQRPopup";
        }
        
    }
    
    
    
    
}