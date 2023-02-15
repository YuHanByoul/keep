/**
 * 
 */
package com.kbrainc.plum.front.envEdu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.envEdu.model.PrgrmVo;
import com.kbrainc.plum.front.envEdu.service.PrgrmService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 프로그램 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.envEdu.controller
* - PrgrmController.java
* </pre>
*
* @ClassName : PrgrmController
* @Description : TODO
* @author : JD
* @date : 2023. 2. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.prgrmController")
@Alias("front.prgrmController")
public class PrgrmController {

    @Resource(name = "front.prgrmServiceImpl")
    private PrgrmService PrgrmService;
    
    /**
    * 프로그램 게시글 목록 화면 이동
    *
    * @Title : prgrmDetailForm
    * @Description : 프로그램 게시글 목록 화면 이동
    * @param prgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/envEdu/prgrmListForm.html")
    public String prgrmListForm() throws Exception {
        return "front/envEdu/prgrmList";
    }
     
    /**
    * 프로그램 게시글 상세 화면 이동
    *
    * @Title : prgrmDetailForm
    * @Description : 프로그램 게시글 상세 화면 이동
    * @param prgrmVo
    * @param model
    * @return
    * @throws Exception
    * @return String
    */
    @RequestMapping(value="/front/envEdu/prgrmDetailForm.html")
    public String prgrmDetailForm(PrgrmVo prgrmVo, Model model) throws Exception {
        PrgrmVo prgrm = null;
        prgrm = PrgrmService.selectPrgrmInfo(prgrmVo);
        model.addAttribute("prgrm", prgrm);
        prgrmVo.setInstid(prgrm.getInstid());
        prgrmVo.setPrgrmid(prgrm.getPrgrmid());
        
        List<PrgrmVo> eduPhotoFileList = null;
        eduPhotoFileList = PrgrmService.selectEduPhotoFileList(prgrmVo);
        model.addAttribute("eduPhotoFileList", eduPhotoFileList);
        
        List<PrgrmVo> prgrmList = null;
        prgrmList = PrgrmService.selectInstPrgrmList(prgrmVo);
        if(prgrmList.size() <= 0) {
            model.addAttribute("prgrmList", "null");
        }else {
            model.addAttribute("prgrmList", prgrmList);
        }

        return "front/envEdu/prgrmDetail";
    }
      
    /**
    * 프로그램 게시글 목록 조회
    *
    * @Title : selectPrgrmList
    * @Description : 프로그램 게시글 목록 조회
    * @param prgrmVo
    * @return
    * @throws Exception
    * @return Map<String,Object>
    */
    @RequestMapping(value="/front/envEdu/selectPrgrmList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmList(PrgrmVo prgrmVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PrgrmVo> result = null;
        
        result =  PrgrmService.selectPrgrmList(prgrmVo);
        
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
