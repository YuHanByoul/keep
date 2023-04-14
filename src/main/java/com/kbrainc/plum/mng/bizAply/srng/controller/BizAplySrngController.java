/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.srng.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.service.ReqMngService;
import com.kbrainc.plum.mng.bizAply.srng.model.BizAplySrngVo;
import com.kbrainc.plum.mng.bizAply.srng.service.BizAplySrngService;
import com.kbrainc.plum.mng.pop.model.PopUpNtcVo;
import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.mng.score.service.ScoreCardService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 심사관리 컨트롤러 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.srng.controller
* - BizAplySrngController.java
* </pre> 
*
* @ClassName : BizAplySrngController
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class BizAplySrngController {

    @Autowired
    private BizAplySrngService bizAplySrngService;
    
    @Autowired
    private ReqMngService reqMngService;
    
    @Autowired
    private ScoreCardService scoreCardService;
    
    @Autowired
    private FileService fileService;
    
    /**
    * 심사관리 목록 조회 화면. 
    *
    * @Title : publicContestListForm
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/srng/selectSrngListForm.html")
    public String publicContestListForm(Model model, @UserInfo UserVo user) throws Exception {
        List<BizAplySrngVo> result = bizAplySrngService.selectCnsltngExprtList();
        model.addAttribute("list", result == null ? new BizAplySrngVo() : result);
        model.addAttribute("jdgsid", user.getUserid());
        model.addAttribute("rolid" ,user.getRoleInfo().getRoleid());
        return "mng/bizAply/srng/srngList";
    }
    
    /**
    * 심사관리 목록 조회. 
    *
    * @Title : selectSrngList
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/srng/selectSrngList.do")
    @ResponseBody
    public Map<String, Object> selectSrngList(ReqMngVo reqMngVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);
        
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
    * 심사관리 목록 엑셀다운로드. 
    *
    * @Title : selectSrngListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param reqMngVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/bizAply/srng/selectSrngListExcelDownload.do")
    public void selectSrngListExcelDownload(HttpServletRequest request, HttpServletResponse response, ReqMngVo reqMngVo) throws Exception {
        bizAplySrngService.selectSrngListExcelDownload(reqMngVo, response, request);
    }
    
    /**
    * 심사관리 상세 신청자 목록 조회 화면. 
    *
    * @Title : selectSrngUserListForm
    * @Description : TODO
    * @param reqMngVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/srng/selectSrngUserListForm.html")
    public String selectSrngUserListForm(ReqMngVo reqMngVo, Model model) throws Exception {
        
        ReqMngVo detailReqMngVo = new ReqMngVo();

        if (reqMngVo != null) {
            if (reqMngVo.getPcntstid() > 0) {
                List<ReqMngVo> result = this.reqMngService.selectReqMngList(reqMngVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detailReqMngVo = result.get(0);
                }
            }
        }
        
        model.addAttribute("reqMngVo", detailReqMngVo);
        
        return "mng/bizAply/srng/srngUserList";
    }
    
    /**
    * 심사관리 신청자 목록 조회. 
    *
    * @Title : selectSrngUserList
    * @Description : TODO
    * @param reqMngVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/srng/selectSrngUserList.do")
    @ResponseBody
    public Map<String, Object> selectSrngUserList(ReqUserVo reqUserVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        reqUserVo.setUser(user);
        List<ReqUserVo> result = this.reqMngService.selectReqUserList(reqUserVo);
        
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
    * 심사관리 신청자 목록 엑셀다운로드. 
    *
    * @Title : selectSrngUserListExcelDownload
    * @Description : TODO
    * @param request
    * @param response
    * @param reqUserVo
    * @throws Exception
    * @return void
     */
    @RequestMapping(value = "/mng/bizAply/srng/selectSrngUserListExcelDownload.do")
    public void selectSrngUserListExcelDownload(HttpServletRequest request, HttpServletResponse response, ReqUserVo reqUserVo) throws Exception {
        bizAplySrngService.selectSrngUserListExcelDownload(reqUserVo, response, request);
    }
    
    /**
    * [신청자 상세정보 탭]. 
    *
    * @Title : detailSrngUserInfoTab
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/srng/detailSrngUserInfoTab.html")
    public String detailSrngUserInfoTab(ReqUserVo reqUserVo, Model model, @UserInfo UserVo user) throws Exception {
        ReqUserVo detail = new ReqUserVo();
        if (reqUserVo != null) {
            if (reqUserVo.getAplyid() > 0) {
                reqUserVo.setUser(user);
                List<ReqUserVo> result = this.reqMngService.selectReqUserList(reqUserVo);                    
                if (CollectionUtils.isNotEmpty(result)) {
                    detail = result.get(0);
                }
//                detail = this.reqMngService.detailReqUser(reqUserVo);
            }
            
            if (!StringUtil.nvl(detail.getFilegrpid2()).equals("") && !StringUtil.nvl(detail.getFilegrpid2()).equals(0)) {
                FileVo fileVo = new FileVo();
                fileVo.setFilegrpid(detail.getFilegrpid2());
                
                model.addAttribute("fileList2", fileService.getFileList(fileVo));    // 증빙서류2
                
            } else {
                model.addAttribute("fileList2", Collections.emptyList());
            }
        }
        
        model.addAttribute("detail", detail);
        model.addAttribute("srngFormidFirst", reqUserVo.getSrngFormidFirst());
        model.addAttribute("srngFormidScnd", reqUserVo.getSrngFormidScnd());
        model.addAttribute("aplyid", reqUserVo.getAplyid());
        model.addAttribute("pcntstid", reqUserVo.getPcntstid());
        model.addAttribute("onlnSrngTypeCd", reqUserVo.getOnlnSrngTypeCd());
        
        return "mng/bizAply/srng/srngTabForm";
    }
    
    /**
    * 신청자 상세정보. 
    *
    * @Title : detailSrngUserInfo
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/srng/detailSrngUserInfo.html")
    public String detailSrngUserInfo(ReqUserVo reqUserVo, Model model, @UserInfo UserVo user) throws Exception {
        BizAplySrngVo bizAplySrngVo = new BizAplySrngVo();
        bizAplySrngVo.setUser(user);
        bizAplySrngVo.setAplyid(reqUserVo.getAplyid());
        bizAplySrngVo.setFormid(Integer.valueOf(reqUserVo.getFormid()));
        BizAplySrngVo srngDetail = this.bizAplySrngService.detailBizAplySrng(bizAplySrngVo);
        
        QuestionVo questionVo = new QuestionVo();
        questionVo.setFormid(Integer.valueOf(reqUserVo.getFormid()));
        questionVo.setSbmsnid(srngDetail == null ? null : srngDetail.getSbmsnid());
        
        model.addAttribute("questionList", scoreCardService.selectQuestionList(questionVo));
        
        if (srngDetail == null) {
            srngDetail = new BizAplySrngVo();
            srngDetail.setFormid(questionVo.getFormid());
        }
        
        model.addAttribute("aplyid", reqUserVo.getAplyid());
        model.addAttribute("srngDetail", srngDetail);
        
        return "mng/bizAply/srng/detailSrngUserInfo";
    }
    
    /**
    * [2차심사]. 
    *
    * @Title : detailSrngSecond
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value="/mng/bizAply/srng/detailSrngSecond.html")
    public String detailSrngSecond(ReqUserVo reqUserVo, Model model, @UserInfo UserVo user) throws Exception {
        BizAplySrngVo bizAplySrngVo = new BizAplySrngVo();
        bizAplySrngVo.setUser(user);
        bizAplySrngVo.setAplyid(reqUserVo.getAplyid());
        bizAplySrngVo.setFormid(Integer.valueOf(reqUserVo.getFormid()));
        BizAplySrngVo srngDetail = this.bizAplySrngService.detailBizAplySrng(bizAplySrngVo);
        
        QuestionVo questionVo = new QuestionVo();
        questionVo.setFormid(Integer.valueOf(reqUserVo.getFormid()));
        questionVo.setSbmsnid(srngDetail == null ? null : srngDetail.getSbmsnid());
        
        model.addAttribute("questionList", scoreCardService.selectQuestionList(questionVo));
        
        if (srngDetail == null) {
            srngDetail = new BizAplySrngVo();
            srngDetail.setFormid(questionVo.getFormid());
        }
        
        model.addAttribute("aplyid", reqUserVo.getAplyid());
        model.addAttribute("srngDetail", srngDetail);
        
        return "mng/bizAply/srng/detailSrngSecond";
    }
    
    /**
    * [심사결과 등록]. 
    *
    * @Title : insertSrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/srng/insertSrng.do")
    @ResponseBody
    public Map<String, Object> insertSrng(@Valid BizAplySrngVo bizAplySrngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        bizAplySrngVo.setUser(user);
        
        int retVal = this.bizAplySrngService.insertBizAplySrng(bizAplySrngVo);
        
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
    * [심사결과 수정]. 
    *
    * @Title : updateSrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object>
     */
    @RequestMapping(value="/mng/bizAply/srng/updateSrng.do")
    @ResponseBody
    public Map<String, Object> updateSrng(@Valid BizAplySrngVo bizAplySrngVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }
        
        bizAplySrngVo.setUser(user);
        
        int retVal = this.bizAplySrngService.updateBizAplySrng(bizAplySrngVo);
        
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
    * 프로그램 상세보기 팝업 
    *
    * @Title : programDetailPopup
    * @Description : TODO
    * @param reqUserVo
    * @param model
    * @return
    * @throws Exception
    * @return String
     */
    @RequestMapping(value = "/mng/bizAply/srng/programDetailPopup.html")
    public String programDetailPopup(ReqUserVo reqUserVo, Model model) throws Exception {
        PopUpNtcVo paramVO = new PopUpNtcVo();
        paramVO.setTitle("프로그램 상세보기");
        model.addAttribute("item", paramVO);
        model.addAttribute("reqUserVo", reqUserVo);
        return "mng/bizAply/srng/programDetailPopup";
    }
}
