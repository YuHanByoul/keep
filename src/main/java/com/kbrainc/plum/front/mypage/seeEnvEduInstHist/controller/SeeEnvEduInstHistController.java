package com.kbrainc.plum.front.mypage.seeEnvEduInstHist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model.SeeEnvEduInstHistVo;
import com.kbrainc.plum.front.mypage.seeEnvEduInstHist.service.SeeEnvEduInstHistService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 마이페이지 > 사회환경교육기관 지정 관리 > 신청내역 컨트롤러 클래스 
*
* <pre>
* com.kbrainc.plum.front.seeEnvEduInstHist.controller
* - SeeEnvEduInstHistController.java
* </pre> 
*
* @ClassName : SeeEnvEduInstHistController
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller
public class SeeEnvEduInstHistController {
    
    @Autowired
    private SeeEnvEduInstHistService seeEnvEduInstHistService;
    
    /**
    * 신청내역 리스트화면으로 이동
    *
    * @Title : seeEnvEduInstHistListForm
    * @Description : 신청내역 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/mypage/seeEnvEduInstHist/seeEnvEduInstHistListForm.html")
    public String seeEnvEduInstHistListForm(Model model) throws Exception {

        return "front/mypage/seeEnvEduInstHist/seeEnvEduInstHistList";
    }
    
    /**
    * 신청내역 조회
    *
    * @Title : selectSeeEnvEduInstHistList
    * @Description : 신청내역 조회
    * @Description : TODO
    * @param seeEnvEduInstHistVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/mypage/seeEnvEduInstHist/selectSeeEnvEduInstHistList.do")
    @ResponseBody
    public Map<String, Object> selectSeeEnvEduInstHistList(SeeEnvEduInstHistVo seeEnvEduInstHistVo, Model model, @UserInfo UserVo user) throws Exception {
        
        Map<String, Object> resultMap = new HashMap<>();
        List<SeeEnvEduInstHistVo> result = null;
        seeEnvEduInstHistVo.setUser(user);
        result =  seeEnvEduInstHistService.selectSeeEnvEduInstHistList(seeEnvEduInstHistVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }    
    
    /**
    * 신청내역 보완요청 팝업. 
    *
    * @Title : selectSplmntPopup
    * @Description : TODO
    * @param supplementVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/front/mypage/seeEnvEduInstHist/selectSplmntPopup.html")
    public String selectSplmntPopup(SupplementVo supplementVo, Model model) throws Exception {
        SupplementVo detail = null;
        if (supplementVo != null) {
            detail = this.seeEnvEduInstHistService.selectSplmntInfo(supplementVo);
            
            if (detail == null)
                detail = new SupplementVo();
        }
        
        model.addAttribute("detail", detail);
        model.addAttribute("sttsCd", supplementVo.getSttsCd());
        
        return "front/mypage/seeEnvEduInstHist/detailSplmnt";
    }
    
    /**
    * 신청내역 보완요청 답변 등록. 
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
    @RequestMapping(value = "/front/mypage/seeEnvEduInstHist/updateSplmnt.do")
    @ResponseBody
    public Map<String, Object> updateSplmnt(@Valid SupplementVo supplementVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
          
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
          
        int retVal = 0;
        supplementVo.setUser(user);
        retVal = seeEnvEduInstHistService.updateSplmnt(supplementVo);
          
        if (retVal > 0) {
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "보완완료 되었습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "보완완료에 실패했습니다.");
        }
              
        return resultMap;
    }
}
