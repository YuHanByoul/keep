/**
 * 
 */
package com.kbrainc.plum.mng.score.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.mng.score.model.ScoreCardVo;
import com.kbrainc.plum.mng.score.service.ScoreCardService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 심사양식 컨트롤러 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.score.controller
* - ScoreCardController.java
* </pre> 
*
* @ClassName : ScoreCardController
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class ScoreCardController {

    @Autowired
    private ScoreCardService scoreCardService;
    
    /**
    * 심사양식 목록 화면. 
    *
    * @Title : scoreCardListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/score/scoreCardListForm.html")
    public String scoreCardListForm() throws Exception {
        return "mng/score/scoreCardList";
    }
    
    /**
    * 심사양식 목록 조회. 
    *
    * @Title : selectScoreCardList
    * @Description : TODO
    * @param scoreCardVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/score/selectScoreCardList.do")
    @ResponseBody
    public Map<String, Object> selectScoreCardList(ScoreCardVo scoreCardVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<ScoreCardVo> result = this.scoreCardService.selectScoreCardList(scoreCardVo);
        
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
    * 심사양식 상세 탭 조회. 
    *
    * @Title : scoreCardTabForm
    * @Description : TODO
    * @param scoreCardVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/score/scoreCardTabForm.html")
    public String scoreCardTabForm(ScoreCardVo scoreCardVo, Model model) throws Exception {

        model.addAttribute("formid", scoreCardVo.getFormid());
        model.addAttribute("totScr", null == scoreCardVo.getTotScr() ? 0 : scoreCardVo.getTotScr());

        return "mng/score/scoreCardTabForm";
    }
    
    /**
    * 심사양식 상세 조회
    *
    * @Title : detailScoreCardForm
    * @Description : TODO
    * @param scoreCardVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/score/detailScoreCardForm.html")
    public String detailScoreCardForm(ScoreCardVo scoreCardVo, Model model) throws Exception {
        
        ScoreCardVo detail = new ScoreCardVo();

        if (scoreCardVo != null) {
            if (scoreCardVo.getFormid() > 0) {
                List<ScoreCardVo> result = this.scoreCardService.selectScoreCardList(scoreCardVo);                         
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
            }
        }
        
        model.addAttribute("detail", detail);
        
        return "mng/score/detailScoreCard";
    }
    
    /**
    * 심사양식 등록. 
    *
    * @Title : insertScoreCard
    * @Description : TODO
    * @param scoreCardVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/score/insertScoreCard.do")
    @ResponseBody
    public Map<String, Object> insertScoreCard(@Valid ScoreCardVo scoreCardVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        scoreCardVo.setUser(user);
        
        int retVal = this.scoreCardService.insertScoreCard(scoreCardVo);
        
        if (retVal > 0) {
            resultMap.put("retVal", retVal);
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "등록에 성공하였습니다.");
        } else {
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "등록에 실패했습니다.");
        }
        
        return resultMap;
    }
    
    /**
    * 심사양식 수정. 
    *
    * @Title : updateScoreCard
    * @Description : TODO
    * @param scoreCardVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/score/updateScoreCard.do")
    @ResponseBody
    public Map<String, Object> updateScoreCard(@Valid ScoreCardVo scoreCardVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        scoreCardVo.setUser(user);
        
        int retVal = this.scoreCardService.updateScoreCard(scoreCardVo);
        
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
    * 문항 목록 조회. 
    *
    * @Title : detailQuestionForm
    * @Description : TODO
    * @param qqestionVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/score/selectQuestionListForm.html")
    public String detailQuestionForm(QuestionVo qqestionVo, Model model) throws Exception {
        
        ScoreCardVo scoreCardVo = new ScoreCardVo();
        ScoreCardVo detail = new ScoreCardVo();
        scoreCardVo.setFormid(qqestionVo.getFormid());
        List<ScoreCardVo> result = this.scoreCardService.selectScoreCardList(scoreCardVo);                         
        if (CollectionUtils.isNotEmpty(result)) {
            detail = result.get(0);
        }
        
        List<QuestionVo> list = null;

        if (qqestionVo != null) {
            if (qqestionVo.getFormid() > 0) {
                list = this.scoreCardService.selectQuestionList(qqestionVo);                         
            }
        }
        
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<QuestionVo>();
            model.addAttribute("totalAltm", 0);
        } else {
            model.addAttribute("totalAltm", list.get(0).getTotalAltm());
        }
        
        model.addAttribute("formid", qqestionVo.getFormid());
        model.addAttribute("totScr", detail.getTotScr());
        model.addAttribute("list", list);
        
        return "mng/score/detailQuestion";
    }
    
    /**
    * 문항 저장. 
    *
    * @Title : saveQuestion
    * @Description : TODO
    * @param questionVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/score/saveQuestion.do")
    @ResponseBody
    public Map<String, Object> saveQuestion(@Valid QuestionVo questionVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        questionVo.setUser(user);
        
        int retVal = this.scoreCardService.saveQuestion(questionVo);
        
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
