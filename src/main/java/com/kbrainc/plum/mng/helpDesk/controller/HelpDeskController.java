package com.kbrainc.plum.mng.helpDesk.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskAnswrVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskAnswrManagerVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskModalUserVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskVo;
import com.kbrainc.plum.mng.helpDesk.service.HelpDeskService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 탄소중립헬프데스크 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.mng.helpDesk.controller
* - HelpDeskController.java
* </pre>
*
* @ClassName   : HelpDeskController
* @Description : TODO
* @author      : KBRAINC_DEV
* @date        : 2022. 12. 20.
* @Version     :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller
public class HelpDeskController {

    @Autowired
    private HelpDeskService helpDeskService;


    /**
    * 문의 목록화면 이동
    *
    * @Title       : helpDeskForm 
    * @Description : TODO
    * @return
    * @throws Exception
    * @return String 
    */
    @RequestMapping("/mng/helpDesk/helpDeskForm.html")
    public String helpDeskForm() throws Exception {
        return "mng/helpDesk/helpDeskForm";
    }

    /**
    * 문의 목록 조회
    *
    * @Title       : selectHelpDeskList 
    * @Description : TODO
    * @param helpDeskVo
    * @return
    * @throws Exception
    * @return Map<String,Object> 
    */
    @RequestMapping(value = "/mng/helpDesk/selectHelpDeskList.do")
    @ResponseBody
    public Map<String, Object> selectHelpDeskList(HelpDeskVo helpDeskVo, @UserInfo UserVo user) throws Exception {
        helpDeskVo.setUser(user);
        List<HelpDeskVo> list = helpDeskService.selectHelpDeskList(helpDeskVo);

        Map<String, Object> response = new HashMap<String, Object>();

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }

    /**
    * 문의 상세화면 이동
    *
    * @Title       : helpDeskDetailForm 
    * @Description : TODO
    * @param helpDeskVo
    * @param model
    * @return
    * @throws Exception
    * @return String 
    */
    @RequestMapping(value = "/mng/helpDesk/helpDeskDetailForm.html")
    public String helpDeskDetailForm(HelpDeskVo helpDeskVo, Model model) throws Exception {

        HelpDeskVo helpDeskInfo = helpDeskService.selectHelpDeskInfo(helpDeskVo);
        HelpDeskAnswrVo helpDeskAnswrInfo = helpDeskService.selectHelpDeskAnswrInfo(helpDeskVo);
        List<HelpDeskAnswrManagerVo> helpDeskManagerInfo = helpDeskService.selectHelpDeskManagerList(helpDeskVo);

        if (helpDeskAnswrInfo == null) {
            helpDeskAnswrInfo = new HelpDeskAnswrVo();
        }
        model.addAttribute("helpDeskInfo", helpDeskInfo);
        model.addAttribute("helpDeskAnswrInfo", helpDeskAnswrInfo);
        model.addAttribute("helpDeskManagerInfo", helpDeskManagerInfo);

        if (!StringUtil.nvl(helpDeskInfo.getFilegrpid()).equals("") && !StringUtil.nvl(helpDeskInfo.getFilegrpid()).equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(helpDeskInfo.getFilegrpid());
            model.addAttribute("fileList", helpDeskService.selectAttachFileList(fileVo));
        } else {
            model.addAttribute("fileList", Collections.emptyList());
        }

        return "mng/helpDesk/helpDeskDetailForm";
    }

    /**
    * 문의 삭제
    *
    * @Title       : deleteHelpDesk 
    * @Description : TODO
    * @param deleteHelpDeskIds
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object> 
    */
    @RequestMapping(value = "/mng/helpDesk/deleteHelpDesk.do")
    @ResponseBody
    public Map<String, Object> deleteHelpDesk(@RequestParam("deleteHelpDeskIds") String[] deleteHelpDeskIds, @UserInfo UserVo user) throws Exception {
        Map<String, Object> reseultMap = new HashMap<>();
        int retVal = 0;

        retVal = helpDeskService.deleteHelpDesk(deleteHelpDeskIds, user);

        if (retVal > 0) {
            reseultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            reseultMap.put("msg", "삭제에 성공하였습니다.");
        } else {
            reseultMap.put("result", Constant.REST_API_RESULT_FAIL);
            reseultMap.put("msg", "삭제에 실패했습니다.");
        }

        return reseultMap;
    }

    /**
    * 답변 등록
    *
    * @Title       : insertHelpDeskAnswr 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object> 
    */
    @RequestMapping(value = "/mng/helpDesk/insertHelpDeskAnswr.do")
    @ResponseBody
    public Map<String, Object> insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        helpDeskAnswrVo.setUser(user);

        int retVal = 0;

        retVal = helpDeskService.insertHelpDeskAnswr(helpDeskAnswrVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "등록에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "등록에 실패했습니다.");
        }

        return map;
    }

    /**
    * 답변 수정
    *
    * @Title       : updateHelpDeskAnswr 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @param bindingResult
    * @param user
    * @return
    * @throws Exception
    * @return Map<String,Object> 
    */
    @RequestMapping(value = "/mng/helpDesk/updateHelpDeskAnswr.do")
    @ResponseBody
    public Map<String, Object> updateHelpDeskAnswr(@Valid HelpDeskAnswrVo helpDeskAnswrVo, BindingResult bindingResult, @UserInfo UserVo user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                map.put("msg", fieldError.getDefaultMessage());
            }
            return map;
        }

        helpDeskAnswrVo.setUser(user);
        int retVal = 0;

        retVal = helpDeskService.updateHelpDeskAnswr(helpDeskAnswrVo);

        if (retVal > 0) {
            map.put("result", Constant.REST_API_RESULT_SUCCESS);
            map.put("msg", "수정에 성공하였습니다.");
        } else {
            map.put("result", Constant.REST_API_RESULT_FAIL);
            map.put("msg", "수정에 실패했습니다.");
        }

        return map;
    }

    /**
    * 답변 모달화면
    *
    * @Title       : helpDeskManagerSearchModal 
    * @Description : TODO
    * @param model
    * @param user
    * @return
    * @throws Exception
    * @return String 
    */
    @RequestMapping(value = "/mng/helpDesk/helpDeskManagerSearchModal.html")
    public String helpDeskManagerSearchModal(Model model, @UserInfo UserVo user) throws Exception {

        return "mng/helpDesk/helpDeskManagerSearchModal";
    }

    /**
     * 모달 회원 검색
     *
     * @Title       : selectUserList
     * @Description : TODO
     * @param helpManagerVo
     * @param user
     * @return
     * @throws Exception
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/mng/helpDesk/selectUserInfoList.do")
    @ResponseBody
    public Map<String, Object> selectUserList(HelpDeskModalUserVo helpManagerVo, @UserInfo UserVo user) throws Exception {
        Map<String, Object> response = new HashMap<String, Object>();

        helpManagerVo.setUser(user);
        List<HelpDeskModalUserVo> list = helpDeskService.selectUserList(helpManagerVo);

        if (list.size() > 0) {
            response.put("totalCount", (list.get(0).getTotalCount()));
        } else {
            response.put("totalCount", 0);
        }

        response.put("list", list);

        return response;
    }


}
