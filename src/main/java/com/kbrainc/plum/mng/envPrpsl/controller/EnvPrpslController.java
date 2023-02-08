package com.kbrainc.plum.mng.envPrpsl.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslAnsVO;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslVO;
import com.kbrainc.plum.mng.envPrpsl.service.EnvPrpslService;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 환경교육제안 관리 Controller
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.controller
 * - EnvPrpslController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslController
 * @Description : 환경교육제안 관리 Controller
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller
public class EnvPrpslController {
    @Autowired
    private EnvPrpslService envPrpslService;
    @Autowired
    private FileService fileService;

    /**
     * 환경교육제안 관리 목록
     * Title : envPrpslList
     * Description : 환경교육제안 관리 목록
     *
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/envPrpsl/envPrpslList.html")
    public String envPrpslList() throws Exception {
        return "mng/envPrpsl/envPrpslList";
    }

    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVO
     * @return map
     * @throws Exception
     */
    @RequestMapping(value = "/mng/envPrpsl/selectEnvPrpslList.do")
    @ResponseBody
    public Map<String, Object> selectEnvPrpslList(EnvPrpslVO envPrpslVO) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<EnvPrpslVO> result = envPrpslService.selectEnvPrpslList(envPrpslVO);

        if(result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);

        return resultMap;
    }

    /**
     * 환경교육제안 관리 상세화면
     * Title : envPrpslFrom
     * Description : 환경교육제안 관리 상세화면
     *
     * @param envPrpslVO
     * @param envPrpslAnsVO
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/mng/envPrpsl/envPrpslFrom.html")
    public String envPrpslFrom(@UserInfo UserVo user, EnvPrpslVO envPrpslVO, EnvPrpslAnsVO envPrpslAnsVO, Model model) throws Exception {
        EnvPrpslVO envPrpslInfo = envPrpslService.selectEnvPrpsInfo(envPrpslVO);
        EnvPrpslAnsVO envPrpslAnsInfo = envPrpslService.selectEnvPrpslAnsInfo(envPrpslAnsVO);

        FileVo fileVo = new FileVo();
        if (envPrpslInfo.getFilegrpid() != null && !envPrpslInfo.getFilegrpid().equals(0)) {
            fileVo.setFilegrpid(envPrpslInfo.getFilegrpid());
            ArrayList<FileVo> atchFileList= fileService.getFileList(fileVo);
            model.addAttribute("atchFileList", atchFileList);
        }

        envPrpslInfo.setUser(user);
        model.addAttribute("envPrpsInfo", envPrpslInfo == null ? new EnvPrpslVO() : envPrpslInfo);
        model.addAttribute("envPrpslAnsInfo", envPrpslAnsInfo == null ? new EnvPrpslAnsVO() : envPrpslAnsInfo);
        return "mng/envPrpsl/envPrpslFrom";
    }


    /**
     * 환경교육제안 관리 답변 등록
     * Title : insertEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 등록
     *
     * @param user
     * @param envPrpslAnsVO
     * @param bindingResult
     * @return map
     */
    @RequestMapping(value = "/mng/envPrpsl/insertEnvPrpslAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertEnvPrpslAnswer(@UserInfo UserVo user, @Valid EnvPrpslAnsVO envPrpslAnsVO, BindingResult bindingResult){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        boolean result = false;
        envPrpslAnsVO.setUser(user);
        result = envPrpslService.insertEnvPrpslAnswer(envPrpslAnsVO);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        }else{
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다.");
        }
        return resultMap;
    }

    /**
     * 환경교육제안 관리 답변 수정
     * Title : insertEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 수정
     *
     * @param user
     * @param envPrpslAnsVO
     * @param bindingResult
     * @return map
     */
    @RequestMapping(value = "/mng/envPrpsl/updateEnvPrpslAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateEnvPrpslAnswer(@UserInfo UserVo user, @Valid EnvPrpslAnsVO envPrpslAnsVO, BindingResult bindingResult){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError != null) {
                resultMap.put("msg", fieldError.getDefaultMessage());
            }
            return resultMap;
        }

        boolean result = false;
        envPrpslAnsVO.setUser(user);
        result = envPrpslService.updateEnvPrpslAnswer(envPrpslAnsVO);

        if(result){
            resultMap.put("result", Constant.REST_API_RESULT_SUCCESS);
            resultMap.put("msg", "저장에 성공하였습니다.");
        }else{
            resultMap.put("result", Constant.REST_API_RESULT_FAIL);
            resultMap.put("msg", "저장에 실패하였습니다.");
        }
        return resultMap;
    }
}
