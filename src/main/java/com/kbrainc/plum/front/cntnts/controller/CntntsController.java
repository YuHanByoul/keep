package com.kbrainc.plum.front.cntnts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.front.cntnts.service.CntntsService;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

/**
* 컨텐츠 컨트롤러 클래스
*
* <pre>
* com.kbrainc.plum.front.cntnts.controller
* - CntntsController.java
* </pre>
*
* @ClassName : CntntsController
* @Description : 콘텐츠 컨트롤러 클래스
* @author : JD
* @date : 2023. 2. 2.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Controller("front.cntntsController")
@Alias("front.cntntsController")
public class CntntsController {

    @Resource(name = "front.cntntsServiceImpl")
    private CntntsService cntntsService;
    
    @Autowired
    private CodeService codeService;
    
    /**
    * 콘텐츠 게시글 목록 화면 이동
    *
    * @Title : cntntsListForm
    * @Description : 컨텐츠관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/cntnts/cntntsListForm.html")
    public String cntntsListForm(Model model) throws Exception {
        model.addAttribute("menuPrgrmCntntsCd", "all");
        
        return "front/cntnts/cntntsList";
    }
    
    /**
    * 콘텐츠 게시글 목록 화면 이동
    *
    * @Title : cntntsListForm
    * @Description : 컨텐츠관리 목록화면 이동
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/cntnts/{eduSbjctCd}/cntntsListForm.html")
    public String cntntsListForm(@PathVariable String eduSbjctCd, Model model, CodeVo codeVo) throws Exception {
        codeVo.setCd(eduSbjctCd);
        CodeVo codeInfo = codeService.selectCodeInfo(codeVo);
        
        model.addAttribute("mainEduSbjctCd", codeInfo.getUpprCd());
        model.addAttribute("eduSbjctCd", eduSbjctCd);
        model.addAttribute("menuPrgrmCntntsCd", null);
        
        return "front/cntnts/cntntsList";
    }
    
    
    /**
    * 콘텐츠 상세 페이지 이동
    *
    * @Title : cntntsDetailForm
    * @Description : 콘텐츠 상세 페이지 이동
    * @param model 객체
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return String
    */
    @RequestMapping(value = "/front/cntnts/cntntsDetailForm.html")
    public String cntntsDetailForm(CntntsVo cntntsVo, Model model, @RequestParam(value="eduSbjctCd", required=false)  String eduSbjctCd) throws Exception {
        cntntsService.updateCntntsHits(cntntsVo); 
        
        CntntsVo result = null;
        result =  cntntsService.selectCntntsInfo(cntntsVo);
        model.addAttribute("cntnts", result);
        
        List<CntntsVo> file = cntntsService.selectCntntsFileList(cntntsVo);
        //System.out.println(Objects.isNull(file.get(0)));
        if(!Objects.isNull(file.get(0))) {
            model.addAttribute("file", file);            
        }
        
        model.addAttribute("eduSbjctCd", eduSbjctCd);
        model.addAttribute("menuPrgrmCntntsCd", eduSbjctCd);
        
        return "front/cntnts/cntntsDetail";
    }
    
    
    /**
    * 콘텐츠 게시글 목록 조회
    *
    * @Title : selectCntntsList
    * @Description : 컨텐츠 관리 게시글 목록 조회
    * @param cntntsVo 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    @RequestMapping(value = "/front/cntnts/selectCntntsList.do")
    @ResponseBody
    public Map<String, Object> selectCntntsList(CntntsVo cntntsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<CntntsVo> result = null;
        
        result =  cntntsService.selectCntntsList(cntntsVo);
        
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
