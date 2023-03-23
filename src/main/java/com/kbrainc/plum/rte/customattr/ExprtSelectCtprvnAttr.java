package com.kbrainc.plum.rte.customattr;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.util.CommonUtil;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 전문가의 가능 활동 범위 및 지역을 보여주기 위한 셀렉트 박스 (시도 테이블 사용)
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - ExprtSelectCtprvnAttr.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtSelectCtprvnAttr
 * @Description : 전문가의 가능 활동 범위 및 지역을 보여주기 위한 셀렉트 박스 (시도 테이블 사용)
 * @date : 2023. 03. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public class ExprtSelectCtprvnAttr extends AbstractAttributeTagProcessor {

    private static final String ATTR_NAME = "exprt_select_ctprvn";
    private static final int PRECEDENCE = 1000;
    CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());

    public ExprtSelectCtprvnAttr(final String dialectPrefix) {
        super(TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix, // Prefix to be applied to name for matching
                null, // No tag name: match any tag name
                false, // No prefix to be applied to tag name
                ATTR_NAME, // Name of the attribute that will be matched
                true, // Apply dialect prefix to attribute name
                PRECEDENCE, // Precedence (inside dialect's precedence)
                true); // Remove the matched attribute afterwards
    }

    @Override
    protected void doProcess(ITemplateContext context
            , IProcessableElementTag tag
            , AttributeName attributeName
            , String attributeValue
            , IElementTagStructureHandler structureHandler) {
        // 기본 옵션
        @Valid
        String selectedCd = "";
        String addClass = "";
        String addStyle = "";
        String isAdmin = "";
        String applyClass = "";
        String title = "";
        String dataWidth = "";
        String firstOptTxt = "";
        boolean disabled = tag.hasAttribute("disabled");

        StringBuffer result = new StringBuffer(); // html 코드 작성용

        try {
            // prameter 유효성 체크

            if (tag.hasAttribute("selectedCd")) {
                selectedCd = tag.getAttribute("selectedCd").getValue();
            }

            if (tag.hasAttribute("firstOptTxt")) {
                firstOptTxt = tag.getAttribute("firstOptTxt").getValue();
            }
            if (tag.hasAttribute("addClass")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("addStyle")) {
                addStyle = tag.getAttribute("addStyle").getValue();
            }

            if (tag.hasAttribute("title")) {
                title = tag.getAttribute("title").getValue();
            }

            if (tag.hasAttribute("dataWidth")) {
                dataWidth = tag.getAttribute("dataWidth").getValue();
            }

            if (tag.hasAttribute("isAdmin")) {
                isAdmin = tag.getAttribute("isAdmin").getValue();
                applyClass = (isAdmin.equals("true")) ? "form-select form-select-sm form-control-sm  " : " ";
            } else {
                //사용자 class 적용 할것
                applyClass = " ";
            }

            List<Map<String, Object>> ctprvnList = commonService.selectCtprvnList();

            result.append("<select  class ='").append(applyClass).append(" ").append(addClass).append("' title='").append(title).append("' data-width='").append(dataWidth).append("'").append(tag.hasAttribute("readonly") ? " readonly" : "").append(" style='").append(addStyle).append("'  id='").append(attributeValue).append("'  name='").append(attributeValue).append("' ");
            if (tag.hasAttribute("onchange") && !tag.getAttribute("onchange").getValue().equals("")) {
                String changeFunction = tag.getAttribute("onchange").getValue();
                result.append("    onchange ='").append(changeFunction).append("()' ");
            }
            if (disabled) {
                result.append(" disabled");
            }
            result.append(">").append("\n");

            if (!firstOptTxt.equals("")) {
                result.append("<option value='' >").append(firstOptTxt).append("</option>").append("\n");
            }

            for (Map<String, Object> ctprvnInfo : ctprvnList) {
                if (selectedCd != null && !(selectedCd.trim()).equals("")
                        && ctprvnInfo.get("CTPRVN_CD").equals(selectedCd)) {
                    result.append("<option value='").append(ctprvnInfo.get("CTPRVN_CD")).append("' selected>").append(ctprvnInfo.get("CTPRVN_NM")).append("</option>")
                            .append("\n");
                } else {
                    result.append("<option value='").append(ctprvnInfo.get("CTPRVN_CD")).append("' >").append(ctprvnInfo.get("CTPRVN_NM")).append("</option>")
                            .append("\n");
                }
            }
            result.append("</select> ").append("\n");

        } catch (Exception e) {
            result.append("<p>시도 목록 조회중 에러 발생 Error 발생 </p>");
        }

        structureHandler.replaceWith(
                // structureHandler.setBody(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), false);
    }
}
