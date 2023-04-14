package com.kbrainc.plum.front.prgrmCntnts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.front.prgrmCntnts.model.PrgrmCntntsVo;
import com.kbrainc.plum.front.prgrmCntnts.service.PrgrmCntntsService;
import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.code.service.CodeService;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.pagination.PaginationUtil;

@Controller
@Alias("front.prgrmCntntsController")
public class PrgrmCntntsController {
    
    @Resource(name = "front.prgrmCntntsServiceImpl")
    private PrgrmCntntsService prgrmCntntsService;
    
    @Autowired
    private ResCodeService resCodeService;
    
    @Autowired
    private CommonService commonService;
    
    @RequestMapping(value="/front/prgrmCntnts/prgrmCntntsListForm.html")
    public String prgrmCntntsListForm(String eduSbjct, String cntstType, String rgnCd,String prgrmEduTrgt, String cntntsEduTrgt, Model model) throws Exception {
        // 교육주제
        eduSbjct = "155101101";
        CodeInfoVo eduSbjctInfo = resCodeService.getCodeInfo(eduSbjct);
        model.addAttribute("mainEduSbjct", eduSbjctInfo.getUpprCd());
        model.addAttribute("subEduSbjct", eduSbjct);
        
        // 콘텐츠 유형
        cntstType = "164101104";
        CodeInfoVo cntstTypeInfo = resCodeService.getCodeInfo(cntstType);
        model.addAttribute("mainCntstType", cntstTypeInfo.getUpprCd());
        model.addAttribute("subCntstType", cntstType);
        
        // 지역코드
        model.addAttribute("eduSbjct", eduSbjct);
        model.addAttribute("sidoList", commonService.selectCtprvnList());
        
        // 교육대상(프로그램)
        model.addAttribute("prgrmEduTrgt", prgrmEduTrgt);
        
        // 교육대상(콘텐츠)
        model.addAttribute("cntntsEduTrgt", cntntsEduTrgt);
        List<CodeInfoVo> codeInfo185 = resCodeService.getCodeList("185","0");
        model.addAttribute("codeInfo185", codeInfo185);
        
        return "front/prgrmCntnts/prgrmCntntsList";
    }
    
    @RequestMapping(value="/front/prgrmCntnts/selectPrgrmCntntsList.do")
    @ResponseBody
    public Map<String, Object> selectPrgrmCntntsList(PrgrmCntntsVo prgrmCntntsVo) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<PrgrmCntntsVo> result = null;
        
        result =  prgrmCntntsService.selectPrgrmCntntsList(prgrmCntntsVo);
        
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
