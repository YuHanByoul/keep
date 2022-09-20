package com.kbrainc.plum.rte.customattr;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * customAttr 코드 정보 받기 위한 컨트롤러.
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - CustomCodeController.java
 * </pre> 
 *
 * @ClassName : CustomCodeController
 * @Description : customAttr 코드 정보 받기 위한 컨트롤러
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Controller
public class CustomCodeController {

    @Resource
    ResCodeServiceImpl resCode;

    @RequestMapping(value = "/mng/customAttr/grpCodeList.do")
    public @ResponseBody String customAttrGetGrpCodeList(@RequestParam Map<String, Object> commandMap)
            throws Exception {

        List<CodeInfoVo> list = resCode.getCodeList(commandMap.get("grpcd").toString(),
                commandMap.get("upprcd").toString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonRes = gson.toJson(list);

        return jsonRes;
    }

}
