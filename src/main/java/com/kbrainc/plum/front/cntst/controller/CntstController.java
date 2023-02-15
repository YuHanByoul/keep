package com.kbrainc.plum.front.cntst.controller;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.cntst.model.CntstVO;
import com.kbrainc.plum.front.cntst.service.CntstService;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.mvc.bind.annotation.UserInfo;
import com.kbrainc.plum.rte.service.ResCodeService;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 공모전 등록 컨트롤러
 *
 * <pre>
 * com.kbrainc.plum.front.cntstRcptHist.controller
 * - CntstController.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstController
 * @Description : 공모전 등록 컨트롤러
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Controller("front.cntstController")
@Alias("front.cntstController")
public class CntstController {

    @Resource(name = "front.cntstServiceImpl")
    private CntstService cntstService;

    /**
     * 공모전 등록
     * Title : cntstListForm
     * Description : 공모전 등록
     *
     * @param model
     * @return string
     * @throws Exception
     */
    @RequestMapping(value = "/front/cntst/cntstListForm.html")
    public String cntstListForm(Model model) throws Exception {
        return "front/cntst/cntstList";
    }

    /**
     * 공모전 목록 조회
     * Title : selectCntstList
     * Description : 공모전 목록 조회
     *
     * @param cntstVO
     * @return map
     */
    @RequestMapping(value = "/front/cntst/selectCntstList.do")
    @ResponseBody
    public Map<String, Object> selectCntstList(CntstVO cntstVO){
        Map<String, Object> resultMap = new HashMap<>();
        List<CntstVO> result = null;
        result =  cntstService.selectCntstList(cntstVO);
        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
        } else {
            resultMap.put("totalCount", 0);
        }
        resultMap.put("list", result);
        return resultMap;
    }
}
