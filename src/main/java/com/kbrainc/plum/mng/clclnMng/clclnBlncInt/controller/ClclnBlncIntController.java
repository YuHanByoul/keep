package com.kbrainc.plum.mng.clclnMng.clclnBlncInt.controller;

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

import com.kbrainc.plum.mng.clclnMng.clclnBlncInt.model.ClclnBlncIntVo;
import com.kbrainc.plum.mng.clclnMng.clclnBlncInt.service.ClclnBlncIntService;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;

/**
* 체험환경교육 지원사업 -> 정산관리 -> 잔액및이자관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.clclnBlncInt.controller
* - ClclnBlncIntController.java
* </pre>
**
@ClassName : ClclnBlncIntController
* @Description : TODO
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ClclnBlncIntController {
    
    @Autowired
    private ClclnBlncIntService clclnBlncIntService;
    
    /**
    * 잔액및이자관리 리스트화면으로 이동
    *
    * @Title : clclnBlncIntListForm
    * @Description : 잔액및이자관리 리스트 화면으로 이동
    * @param model 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/clclnBlncIntListForm.html")
    public String clclnBlncIntListForm(Model model, HttpServletRequest request) throws Exception {

        return "mng/clclnMng/clclnBlncInt/clclnBlncIntList";
    }
    
    /**
    * 잔액및이자관리 목록 조회
    *
    * @Title : selectClclnBlncIntList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param clclnBlncIntVo 교육신청관리 객체
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/selectClclnBlncIntList.do")
    @ResponseBody
    public Map<String, Object> selectClclnBlncIntList(ClclnBlncIntVo clclnBlncIntVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ClclnBlncIntVo> result = null;
        result =  clclnBlncIntService.selectClclnBlncIntList(clclnBlncIntVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 잔액및이자관리 상세화면으로 이동
    **
    * @Title : clclnBlncIntDetailForm
    * @Description : 잔액및이자관리 상세화면으로 이동
    * @param clclnBlncIntVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/clclnBlncIntDetailForm.html")
    public String clclnBlncIntDetailForm(ClclnBlncIntVo clclnBlncIntVo, Model model) throws Exception {
        model.addAttribute("clclnBlncIntVo", clclnBlncIntVo);
        
        return "mng/clclnMng/clclnBlncInt/clclnBlncIntDetailForm";
    }

    /**
     * 잔액및이자관리 상세화면으로 이동
     **
     * @Title : clclnBlncIntDetailForm
     * @Description : 잔액및이자관리 상세화면으로 이동
     * @param clclnBlncIntVo
     * @param model
     * @return
     * @throws Exception
     * @return String
     */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/clclnBlncIntDetailInfoForm.html")
    public String clclnBlncIntDetailInfoForm(ClclnBlncIntVo clclnBlncIntVo, Model model) throws Exception {

        ClclnBlncIntVo result = new ClclnBlncIntVo();
        ClclnBlncIntVo calResult = new ClclnBlncIntVo();
        
        List<ClclnBlncIntVo> resultList = null;
        result = clclnBlncIntService.selectClclnBlncIntDetailInfo(clclnBlncIntVo);
        resultList = clclnBlncIntService.selectClclnBlncIntDetailOutlList(clclnBlncIntVo);
        //calResult = clclnBlncIntService.selectClclnBlncIntOutlCalculateInfo(clclnBlncIntVo);

        
        double bgtAmtSum = 0;
        double expndAmtSum = 0;
        double implCntSum = 0;
        double implBlncAmtSum = 0;
        double implRtSum = 0;
        double dsctnSum = 0;
        
        if(resultList != null && resultList.size() > 0){
            for(int i = 0; i < resultList.size(); i++) {
                bgtAmtSum += resultList.get(i).getBgtAmt();
                expndAmtSum += resultList.get(i).getExpndAmt();
                implCntSum += resultList.get(i).getImplCnt();
                implBlncAmtSum += resultList.get(i).getImplBlncAmt();
            }
            implRtSum = Math.round((expndAmtSum / bgtAmtSum) * 100); 
            
        }
        
        calResult.setBgtAmtSum(bgtAmtSum);
        calResult.setExpndAmtSum(expndAmtSum);
        calResult.setImplCntSum(implCntSum);
        calResult.setImplBlncAmtSum(implBlncAmtSum);
        calResult.setImplRtSum(implRtSum);
        

        if(result.getAtchFileIdntfcKey() != null) {
            StringBuffer atchFileBtn = new StringBuffer();
            atchFileBtn.append("<div class ='label label-inverse text-white' id='" + clclnBlncIntVo.getAtchFileid() + "'>");
            atchFileBtn.append("<a href=javascript:downloadFileByFileid('" + clclnBlncIntVo.getAtchFileid() + "','" + result.getAtchFileIdntfcKey() + "') class='text-white'>" + result.getAtchOrginlFileNm() + "&nbsp;&nbsp;</a>");
            atchFileBtn.append("<a href=javascript:fn_deleteFileList('" + clclnBlncIntVo.getAtchFileid() + "','" + result.getAtchFileIdntfcKey() + "') class='text-white'>X</a></div>");
            model.addAttribute("atchFileBtn", atchFileBtn);
        }

        model.addAttribute("clclnBlncInt", result);
        model.addAttribute("calculateInfo", calResult);
        model.addAttribute("outlList", resultList);
        model.addAttribute("dsctnSum", dsctnSum);
        
        return "mng/clclnMng/clclnBlncInt/clclnBlncIntDetailInfoForm";
    }
    
    /**
    * 잔액및이자관리 상세목록 조회
    **
    * @Title : selectClclnBlncIntDetailList
    * @Description : 잔액및이자관리 상세목록 조회
    * @param clclnBlncIntVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/selectClclnBlncIntDetailList.do")
    @ResponseBody
    public Map<String, Object> selectClclnBlncIntDetailList(ClclnBlncIntVo clclnBlncIntVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<ClclnBlncIntVo> result = null;
        result =  clclnBlncIntService.selectClclnBlncIntDetailList(clclnBlncIntVo);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
    
    /**
    * 잔액및이자관리 상세목록 반납여부 수정
    **
    @Title : updateClclnBlncIntRturnYn
    * @Description : 잔액및이자관리 상세목록 반납여부 수정
    * @param clclnBlncIntVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/mng/clclnMng/clclnBlncInt/updateClclnBlncIntRturnYn.do")
    @ResponseBody
    public Map<String, Object> updateClclnBlncIntRturnYn(@Valid ClclnBlncIntVo clclnBlncIntVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        clclnBlncIntVo.setUser(user);
        int retVal = 0;
        retVal = clclnBlncIntService.updateClclnBlncIntRturnYn(clclnBlncIntVo);
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
