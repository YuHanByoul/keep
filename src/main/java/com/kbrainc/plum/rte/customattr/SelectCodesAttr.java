package com.kbrainc.plum.rte.customattr;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
 * 
 * select 태그속성으로 처리.
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - SelectCodesAttr.java
 * </pre> 
 *
 * @ClassName : SelectCodesAttr
 * @Description : select 태그속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class SelectCodesAttr extends AbstractAttributeTagProcessor {

    // ATTR name 설정
    private static final String ATTR_NAME = "select_codes";
    private static final int PRECEDENCE = 1000;

    ResCodeService resCode = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of SelectCodesAttr.java class
     * @param dialectPrefix :
     */
    public SelectCodesAttr(final String dialectPrefix) {
        super(TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix, // Prefix to be applied to name for matching
                null, // No tag name: match any tag name
                false, // No prefix to be applied to tag name
                ATTR_NAME, // Name of the attribute that will be matched
                true, // Apply dialect prefix to attribute name
                PRECEDENCE, // Precedence (inside dialect's precedence)
                true); // Remove the matched attribute afterwards
    }

    /**
     * 코드 그룹cd 로 depth 확인.
     *
     * @Title       : chaseDepth 
     * @Description : TODO
     * @param grpcd :
     * @return int
     * @throws Exception :
     */
    public int chaseDepth(String grpcd) throws Exception {
        int depth = 0;
        List<CodeInfoVo> checkDepth = new ArrayList<CodeInfoVo>();
        checkDepth = resCode.getCodeList(grpcd);

        while (checkDepth.size() > 0) {
            List<CodeInfoVo> returnList = new ArrayList<CodeInfoVo>();
            if (checkDepth.size() <= 0) {
            } else {
                for (CodeInfoVo codeInfoVo : checkDepth) {
                    returnList.addAll(resCode.getCodeList(grpcd, codeInfoVo.getCd()));
                }
            }
            checkDepth = returnList;
            depth++;
        }
        return depth;
    }
    
    public String getUpprCodeByRecusive(String grpCd, String upprCd, String cd) throws Exception {
        String tmpGrpCd = grpCd;
        if (!("JSSFC".equals(grpCd) || "INDUTY".equals(grpCd) || "ADDR".equals(grpCd) || "CERT".equals(grpCd) || "ABLTY".equals(grpCd))) {
            tmpGrpCd = "CODE";
        }
        CodeInfoVo codeInfoVo = resCode.getCodeInfo(grpCd + "|" + cd);
        if (codeInfoVo.getUpprCd().equals(upprCd) || codeInfoVo.getUpprCd().equals("0")) {
            return codeInfoVo.getCd();
        }
        
        return getUpprCodeByRecusive(tmpGrpCd, upprCd, codeInfoVo.getUpprCd()) + ',' + cd;
    }

    @Override
    protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        // attributeValue에 SP El표현식 사용가능 하도록 추가
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        final String attributeVal = (String) expression.execute(context);
        
        StringBuffer result = new StringBuffer();

        try {

            String grpcd = ""; // 그룹cd
            String selectedCd = "";//
            String[] selectedCds = null;
            Object[] selectedCdLists = null;
            String upprCd = ""; // default cd
            String firstOptTxt = "";
            String addClass = ""; 
            String addStyle = ""; 
            
            List<CodeInfoVo> codeList = new ArrayList<CodeInfoVo>();

            // prameter 유효성 체크
            if (tag.hasAttribute("grpCd")) {
                grpcd = tag.getAttribute("grpCd").getValue();
            } else {
                result.append(" <p> grpcd=null 코드 값을 입력해 주십시오. </p>");
                structureHandler.setBody(result.toString(), false);
            }

            if (tag.hasAttribute("selectedCd")) {
                selectedCd = tag.getAttribute("selectedCd").getValue();
                if(!"".equals(selectedCd)) {
                    selectedCds = getUpprCodeByRecusive(grpcd, upprCd, selectedCd).split(",");
                    selectedCdLists = new Object[selectedCds.length];
                    for(int i = 0; i < selectedCds.length; i++) {
                        if(i == 0) {
                            if (tag.hasAttribute("upprCd")) {
                                upprCd = tag.getAttribute("upprCd").getValue();
                                selectedCdLists[i] = resCode.getCodeList(grpcd, upprCd);
                            } else {
                                selectedCdLists[i] = resCode.getCodeList(grpcd);
                            }
                        } else {
                            selectedCdLists[i] = resCode.getCodeList(grpcd, selectedCds[i-1]);
                        }
                    }
                }
            }

            if (tag.hasAttribute("upprCd")) {
                upprCd = tag.getAttribute("upprCd").getValue();
                codeList = resCode.getCodeList(grpcd, upprCd);
            } else {
                codeList = resCode.getCodeList(grpcd);
            }

            //codeList = resCode.getCodeList(grpcd);

            firstOptTxt = "전체";
            if (tag.hasAttribute("firstOptTxt")) {
                firstOptTxt = tag.getAttribute("firstOptTxt").getValue();
            }
            
            if (tag.hasAttribute("addClass") && !tag.getAttribute("addClass").getValue().equals("")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("addStyle") && !tag.getAttribute("addStyle").getValue().equals("")) {
                addStyle = tag.getAttribute("addStyle").getValue();
            }
            
            int depth = 0;

            /** grpcd not null 이면 depth 추적 */
            depth = chaseDepth(grpcd);

            if (codeList.size() <= 0) {
                result.append(" <p> 조회 된 코드 목록이 없습니다.</p>");
            } else {

                result.append("<div class=\"form-inline\">").append("\n");
                for (int i = 1; i <= depth; i++) {
                    if(selectedCdLists != null) {
                        codeList = (List<CodeInfoVo>)selectedCdLists[i-1];
                    }
 
                    result.append("<div class=\"form-group\" style=\"margin-left: 3px;\">").append("\n");
                    result.append("<select class ='form-control " + addClass + "' style='" + addStyle + "' id='" + attributeVal + "_" + i + "' name='" + attributeVal + "_" + i
                            + "'  onchange ='setNextDepth(\"" + grpcd + "\",this.value,\"" + attributeVal + "_"
                            + (i + 1) + "\" )'> ").append("\n");
                    result.append("<option value=''>").append(firstOptTxt).append("</option>").append("\n");
                    if (codeList != null) {
                        for (CodeInfoVo codeInfoVo : codeList) {
                            if (selectedCds != null && selectedCds[i-1].equals(codeInfoVo.getCd())) {
                                result.append("<option value='").append(codeInfoVo.getCd()).append("' selected>").append(codeInfoVo.getCdNm()).append("</option>").append("\n");
                            } else {
                                result.append("<option value='").append(codeInfoVo.getCd()).append("' >").append(codeInfoVo.getCdNm()).append("</option>").append("\n");
                            }
                        }
                    }
                    result.append("</select> ").append("\n");
                    result.append("</div>");
                    codeList = null;
                }
                result.append("</div>").append("\n");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            result.append(" <p> code=null 코드 값을 입력해 주십시오. </p>");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("<p>코드 목록 조회중 에러 발생 Error 발생 </p>");
        }

        structureHandler.replaceWith(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), false);

    }

}