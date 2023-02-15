package com.kbrainc.plum.front.evnt.controller;

import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.front.evnt.model.EvntVo;
import com.kbrainc.plum.front.evnt.service.EvntService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 참여신청관리 > 이벤트관리 컨트롤러 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.evnt.controller
 * - EvntController.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntController
 * @Description : 참여신청관리 > 이벤트관리 컨트롤러 클래스
 * @date : 2023. 01. 25.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Controller("front.evntController")
@Alias("front.evntntstController")
public class EvntController {
    
    @Resource(name = "front.evntServiceImpl")
    private EvntService evntService;

    /**
     * 이벤트 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : evntList
     * @Description : 이벤트 목록 화면
     */
    @GetMapping("/front/evnt/evntListForm.html")
    public String evntListForm() throws Exception {
        return "front/evnt/evntList";
    }
    
    /**
     * 이벤트 목록 화면
     *
     * @return string
     * @throws Exception
     * @Title : evntList
     * @Description : 이벤트 목록 화면
     */
    @GetMapping("/front/evnt/evntDetailForm.html")
    public String evntDetailForm(Model model, EvntVo evntVo) throws Exception {
        evntService.updateEvntHits(evntVo);
        
        EvntVo result = null;
        result =  evntService.selectEvntInfo(evntVo);
        model.addAttribute("evnt", result);
        
        List<EvntVo> file = evntService.selectEvntFileList(evntVo);
        model.addAttribute("file", file);
        
        return "front/evnt/evntDetail";
    }

    /**
     * 이벤트 목록 조회
     *
     * @param evntVo
     * @return map
     * @throws Exception
     * @Title : selectEvntList
     * @Description : 이벤트 목록 조회
     */
    @GetMapping("/front/evnt/selectEvntList.do")
    @ResponseBody
    public Map<String, Object> selectEvntList(EvntVo evntVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        List<EvntVo> result = evntService.selectEvntList(evntVo);

        if (result.size() > 0) {
            resultMap.put("totalCount", (result.get(0).getTotalCount()));
            resultMap.put("pagination", PaginationUtil.getFrontPaginationHtml(result.get(0).getTotalPage(), result.get(0).getPageNumber(), 10));
        } else {
            resultMap.put("totalCount", 0);
        }

        resultMap.put("list", result);

        return resultMap;
    }
}
