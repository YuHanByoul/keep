package com.kbrainc.plum.front.msg.controller;

import com.kbrainc.plum.front.msg.model.MsgVo;
import com.kbrainc.plum.front.msg.service.MsgService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 쪽지 Controller
 *
 * <pre>
 * com.kbrainc.plum.front.msg.controller
 * - MsgController.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MsgController
 * @Description : 쪽지 Controller
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Controller
public class MsgController {
    @Autowired
    private MsgService msgService;

    /**
     * 쪽지 발송
     * Title : msgInsert
     * Description : 쪽지 발송
     *
     * @param msgVo
     * @return map
     */
    @RequestMapping(value = "/front/msg/insertMsg.do")
    @ResponseBody
    public Map<String, Object> insertMsg(@Valid MsgVo msgVo, BindingResult bindingResult, @UserInfo UserVo userVo) {
        Map<String, Object> result = new HashMap<>();

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                result.put("msg", fieldError.getDefaultMessage());
            }
            return result;
        }

        boolean success = false;
        msgVo.setUser(userVo);
        if (msgService.insertMsg(msgVo)) {
            success = true;
            result.put("msg", "쪽지를 성공적으로 발송했습니다.");
        } else
            result.put("msg", "쪽지의 발송이 실패하였습니다.");

        result.put("success", success);

        return result;
    }

    /**
     * 쪽지 발송 대상자 정보 조회
     * Title : selectTrgtInfo
     * Description : 쪽지 발송 대상자 정보 조회
     *
     * @param trgtId
     * @return map
     */
    @RequestMapping(value = "/front/msg/selectTrgtInfo.do")
    @ResponseBody
    public Map<String, Object> selectTrgtInfo(String trgtId){
        Map<String, Object> result = new HashMap<>();
        MsgVo msgInfo = msgService.selectTrgtInfo(trgtId);

        if (msgInfo != null) {
            msgInfo.setTrgtAcnt(StringUtil.maskingAccount(msgInfo.getTrgtAcnt()));
            msgInfo.setTrgtNm(StringUtil.maskingName(msgInfo.getTrgtNm()));
            result.put("data", msgInfo);
        }else {
            result.put("data",null);
        }

        return result;
    }
}
