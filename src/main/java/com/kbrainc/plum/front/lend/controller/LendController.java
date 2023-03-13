package com.kbrainc.plum.front.lend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.lend.model.LendAplyTrgtVo;
import com.kbrainc.plum.front.lend.model.LendAplyVo;
import com.kbrainc.plum.front.lend.model.LendRndVo;
import com.kbrainc.plum.front.lend.model.LendVo;
import com.kbrainc.plum.front.lend.service.LendServiceImpl;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
 * 
 *  교구 대여(사용자) 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.lend.controller
 * - LendController.java
 * </pre> 
 *
 * @ClassName : LendController
 * @Description : 교구 대여(사용자) 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 03. 03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Controller("front.lendController")
@Alias("front.lendController")
public class LendController {

    @Resource(name = "front.lendServiceImpl")
    private LendServiceImpl lendService;
    
    @Autowired
    private FileService fileService;
    
    
    /**
    * 교구 대여 모집 화면 
    *
    * @Title       : memberForm 
    * @Description : 개인회원관리 리스트화면 이동.
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/lend/LendList.html")
    public String memberForm(Model model) throws Exception {
        model.addAttribute("rankList", lendService.selectLendRankList());
        model.addAttribute("sbjctList", lendService.selectEduSbjctCdList());
        return "front/lend/lendList";
    }
    
    /**
     * @Title : selectLendList
     * @Description : 교구 대여 목록 호출
     * @param LendVo
     * @param user  로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/lend/selectLendList.do")
    @ResponseBody
    public Map<String, Object> selectLendList(LendVo lendVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<LendVo> result = null;
        
        if(user!=null) {
            lendVo.setUser(user);
        }
        
        result = lendService.selectLendList(lendVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination",PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 교구 대여 상세 화면 
    *
    * @Title       : lendDetail 
    * @Description : 교구 대여 상세 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/lend/lendDetail.html")
    public String lendDetail(LendVo lendVo,Model model, @UserInfo UserVo user) throws Exception {
        
        lendVo.setUser(user);
        LendVo resVo = lendService.selectLend(lendVo);
        model.addAttribute("lendInfo",resVo);
        
        LendRndVo lendRndVo = new LendRndVo(); 
        lendRndVo.setUser(user);
        lendRndVo.setRcritid(resVo.getRcritid());
        List<LendRndVo> rndList = lendService.selectLendRndList(lendRndVo);
        model.addAttribute("rndList",rndList);
        
        FileVo fileVo = new FileVo();
        if(resVo.getRprsImgFileid() != null && resVo.getRprsImgFileid() != 0) {
            fileVo.setFileid(resVo.getRprsImgFileid());
            FileVo rprsImgFileInfo = fileService.getFileInfo(fileVo);
            model.addAttribute("rprsImgFileInfo", rprsImgFileInfo);
        }
        if(resVo.getDtlImgFilegrpid() != null && resVo.getDtlImgFilegrpid() != 0) {
            fileVo.setFilegrpid(resVo.getDtlImgFilegrpid());
            ArrayList<FileVo> dtlImgFileList = fileService.getFileList(fileVo);
            model.addAttribute("dtlImgFileList", dtlImgFileList);
        }
        if(resVo.getMapFilegrpid() != null && resVo.getMapFilegrpid() != 0) {
            fileVo.setFilegrpid(resVo.getMapFilegrpid());
            //FileVo mapFileInfo = fileService.getFileInfo(fileVo);
            ArrayList<FileVo> mapFileInfo = fileService.getFileList(fileVo);
            model.addAttribute("mapFileInfo", mapFileInfo);
        }
        if(resVo.getEduPhotoFilegrpid() != null && resVo.getEduPhotoFilegrpid() != 0) {
            fileVo.setFilegrpid(resVo.getEduPhotoFilegrpid());
            ArrayList<FileVo> eduPhotoFileList = fileService.getFileList(fileVo);
            model.addAttribute("eduPhotoFileList", eduPhotoFileList);
        }
        return "front/lend/lendDetail";
    }
    /**
     * 교구 대여 후기 목록 호출
     * 
     * @Title : lendReplyList
     * @Description : 교구 대여 후기 목록 호출
     * @param LendVo
     * @param user  로그인사용자정보
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/front/lend/selectLendReplyList.do")
    @ResponseBody
    public Map<String, Object> lendReplyList(LendAplyVo lendAplyVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<LendAplyVo> result = null;
        
        if(user!=null) {
            lendAplyVo.setUser(user);
        }
        
        result = lendService.selectLendReplyList(lendAplyVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination",PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 12));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
     * 교구 신청 화면 
     *
     * @Title       : lendAply 
     * @Description : 교구 신청 화면
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/front/lend/lendAply.html")
    public String lendAply(LendAplyVo lendAplyVo,Model model, @UserInfo UserVo user) throws Exception {
        
        model.addAttribute("paramVo",lendAplyVo);
        
        lendAplyVo.setUser(user);
        
        String[] arr = lendAplyVo.getLendAplyTrgts();
        List<LendAplyTrgtVo> paramList = new ArrayList();
        for( String rndid : arr ) {
            LendAplyTrgtVo vo = new LendAplyTrgtVo();
            vo.setRndid(Integer.parseInt(rndid.split("/")[0]));
            paramList.add(vo);
        } 
        lendAplyVo.setLendAplyTrgtList(paramList);
        
        LendVo lendVo = new LendVo();
        lendVo.setUser(user);
        lendVo.setRcritid(lendAplyVo.getRcritid());
        LendVo resVo = lendService.selectLend(lendVo);
        //대여 모집 정보 
        model.addAttribute("lednInfo",resVo);
        //신청 차시 정보
        List<LendRndVo> resList = new ArrayList();
        resList = lendService.selectRequestLendRndList(lendAplyVo);
        for( String rndid : arr ) {
            for( LendRndVo vo : resList ) {
                if(vo.getRndid() ==Integer.parseInt(rndid.split("/")[0])) {
                    vo.setQnty(Integer.parseInt(rndid.split("/")[1]));
                }
            }
        }
        model.addAttribute("rndInfo",resList);
        //신청자 (기관) 정보
        Map<String,Object> instMap = lendService.selectRequestInstInfo(lendAplyVo);
        try {
            ResCodeService resCodeService = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());
            CodeInfoVo code = resCodeService.getCodeInfo(instMap.get("INST_TYPE_CD").toString());
            instMap.put("INST_TYPE_CD_NM", code.getCdNm());
        }catch(NoClassDefFoundError e) {
        }catch(Exception e) {
        }
        model.addAttribute("instInfo",instMap);
        return "front/lend/lendAply";
    }
    /**
    * 공동구매 신청 등록
    *
    * @Title : insertJntpurchsOrder
    * @Description : 공동구매 신청 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo 객체
    * @param bindingResult jntpurchsOrderVo 유효성 검증결과
    * @param user 사용자 세션 정보
    * @return Map<String, Object> 응답결과객체
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/lend/insertLendAply.do")
    @ResponseBody
    public Map<String, Object> insertJntpurchsOrder(@Valid LendAplyVo lendAplyVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        lendAplyVo.setUser(user);
        
        String[] arr = lendAplyVo.getLendAplyTrgts();
        List<LendAplyTrgtVo> paramList = new ArrayList();
        for( String rndid : arr ) {
            LendAplyTrgtVo vo = new LendAplyTrgtVo();
            vo.setRndid(Integer.parseInt(rndid.split("/")[0]));
            vo.setQnty(Integer.parseInt(rndid.split("/")[1]));
            paramList.add(vo);
        } 
        lendAplyVo.setLendAplyTrgtList(paramList);
        
        //재고확인 처리 할 것 
        if(lendService.checkOverStockYn(lendAplyVo).equals("Y")) {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "실시간 재고와 맞지 않는 신청이 있습니다. 다시한번 확인해주십시오.");
            return resultMap;
        }
        
        List<LendRndVo> resList = lendService.selectRequestLendRndList(lendAplyVo);
        for( LendAplyTrgtVo parmaVo : paramList ) {
            for( LendRndVo vo : resList ) {
                if(parmaVo.getRndid() == vo.getRndid()) {
                    parmaVo.setBgngDe(vo.getBgngDe());
                    parmaVo.setEndDe(vo.getEndDe());
                }
            }
        }
        lendAplyVo.setLendAplyTrgtList(paramList);
        retVal = lendService.insertLendAply(lendAplyVo);
          
        if(retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "신청이 완료되었습니다");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "신청에 실패하였습니다");
        }
              
        return resultMap;
    }
    /**
    * 교구 대여 신청 완료 화면
    *
    * @Title       : lendDetail 
    * @Description : 교구 대여 신청 완료 화면
    * @return String 이동화면경로
    * @throws Exception 예외
    */
    @RequestMapping(value = "/front/lend/lendAplyComplete.html")
    public String lendAplyComplete(LendVo lendVo,Model model) throws Exception {
        return "front/lend/lendAplyComplete";
    }
    
}


