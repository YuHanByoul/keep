package com.kbrainc.plum.mng.resveReqst.controller;

import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.inst.service.InstService;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.mng.resveReqst.service.ResveReqstService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 시설관리 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.resveReqst.contoller
* - BsnsCmmnController.java
* </pre>
*
* @ClassName : ResveReqstController
* @Description : 시설 컨트롤러 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ResveReqstController {
    
    @Autowired
    private ResveReqstService resveReqstService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private InstService instService;
    
    @Autowired
    private SpceService spceService;
    
    /**
    * 예약신청 리스트화면으로 이동
    *
    * @Title : resveReqstList
    * @Description : 예약신청 리스트화면으로 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/resveReqst/resveReqstList.html")
    public String resveReqstList() throws Exception {
        return "mng/resveReqst/resveReqstList";
    }
    
    /**
     * 상세(탭) 화면
     *
     * @Title       : resveReqstDetail 
     * @Description : 등록화면 이동.
     * @param model 모델객체
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/resveReqst/resveReqstDetail.html")
    public String resveReqstDetail(ResveReqstVo resveReqstVo, Model model, SpceVo spceVo, @UserInfo UserVo user) throws Exception {
        model.addAttribute("param", resveReqstVo);
        return "mng/resveReqst/resveReqstDetail";
    }
    
    /**
     * 상태변경이력 조회화면
     *
     * @Title       : resveReqstHstryList
     * @Description : 상태변경이력 조회화면 이동.
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/resveReqst/resveReqstHstryList.html")
    public String resveReqstHstryList() throws Exception {
        return "mng/resveReqst/resveReqstHstryList";
    }


    /**
     * 상태변경이력 리스트 기능
     *
     * @Title : selectResveReqstList
     * @Description : 상태변경이력 리스트 기능
     * @param resveReqstVo 시설 객체
     * @throws Exception 예외
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/resveReqst/selectResveReqstHstryList.do")
    @ResponseBody
    public Map<String, Object> selectResveReqstHstryList(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ResveReqstVo> result = null;

        result =  resveReqstService.selectResveReqstHstryList(resveReqstVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    @RequestMapping(value = "/mng/resveReqst/instSearchPopup.html")
    public String instSearchPopup() throws Exception {
        
        return "resveCancelCheckPopup";
    }
    
    /**
    * 예약신청 수정화면으로 이동
    *
    * @Title : resveReqstUpdate
    * @Description : 예약신청 수정화면으로 이동
    * @param resveReqstVo 시설 객체
    * @param model model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/resveReqst/resveReqstUpdate.html")
    public String resveReqstUpdate(ResveReqstVo resveReqstVo, Model model, @UserInfo UserVo user, InstVo instVo) throws Exception {
        
        model.addAttribute("param", resveReqstVo);

        resveReqstVo.setUser(user);
        ResveReqstVo resultVo = resveReqstService.selectResveReqstInfo(resveReqstVo);

        model.addAttribute("resveReqst", resultVo);

        return "mng/resveReqst/resveReqstUpdate";
    }
   
    /**
    * 예약내역 리스트 기능
    *
    * @Title : selectResveReqstList
    * @Description : 예약내역 리스트 기능
    * @param resveReqstVo 예약내역 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/resveReqst/selectResveReqstList.do")
    @ResponseBody
    public Map<String, Object> selectResveReqstList(ResveReqstVo resveReqstVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ResveReqstVo> result = null;

        result =  resveReqstService.selectResveReqstList(resveReqstVo);
        
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }
    
    /**
    * 예약내역 수정
    *
    * @Title : updateResveReqst
    * @Description : 예약내역 수정
    * @param resveReqstVo 예약내역 객체
    * @param bindingResult 예약내역 데이터 유효성 검증결과
    * @param user 사용자 세션정보
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/resveReqst/updateResveReqst.do")
    @ResponseBody
    public Map<String, Object> updateResveReqst(@Valid ResveReqstVo resveReqstVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        resveReqstVo.setUser(user);

        int retVal = 0;
                
        retVal = resveReqstService.updateResveReqst(resveReqstVo);
        
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
     * @Title : resveCnclRsnPopup
     * @Description : 상태변경이력 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/resveReqst/resveCnclRsnPopup.html")
    public String resveCnclRsnPopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("hstryid", resveReqstVo.getHstryid());
        ResveReqstVo resultVo = resveReqstService.selectHstryInfo(resveReqstVo);

        model.addAttribute("resveReqst", resultVo);
        return "mng/resveReqst/resveCnclRsnPopup";
    }

    /**
     * @Title : dsptCheckPopup
     * @Description : 입금 확인 팝업
     * @throws Exception :
     * @return String 이동화면경로
     * @throws Exception 예외
     */
    @RequestMapping(value = "/mng/resveReqst/dsptCheckPopup.html")
    public String dsptCheckPopup(ResveReqstVo resveReqstVo, Model model) throws Exception {
        model.addAttribute("hstryid", resveReqstVo.getHstryid());
        ResveReqstVo resultVo = resveReqstService.selectHstryInfo(resveReqstVo);

        model.addAttribute("resveReqst", resultVo);
        return "mng/resveReqst/dsptCheckPopup";
    }
}
