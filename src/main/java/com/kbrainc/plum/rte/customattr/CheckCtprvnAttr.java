package com.kbrainc.plum.rte.customattr;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.util.CommonUtil;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 시도 체크박스 커스텀태그 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - CheckCtprvnAttr.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : CheckCtprvnAttr
 * @Description : 시도 체크박스 커스텀태그 클래스
 * @date : 2023. 03. 22.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public class CheckCtprvnAttr extends AbstractAttributeTagProcessor {
    private static final String ATTR_NAME = "check_ctprvn";
    private static final int PRECEDENCE = 1000;
    CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());

    public CheckCtprvnAttr(final String dialectPrefix) {
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
    protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
                             final AttributeName attributeName, final String attributeValue,
                             final IElementTagStructureHandler structureHandler) {
        StringBuffer result = new StringBuffer();

        try {
            // 기본 옵션
            String selectedCd = "";// default cd
            String[] selectedCds = null;
            String addClass = "";
            String isAdmin = "false";

            List<CodeInfoVo> codeList = new ArrayList<CodeInfoVo>();

            if (tag.hasAttribute("selectedCd")) {
                selectedCd = tag.getAttribute("selectedCd").getValue();
            }

            if (selectedCd != null) {
                selectedCds = selectedCd.split(",");
            }

            if (tag.hasAttribute("addClass") && !tag.getAttribute("addClass").getValue().equals("")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("isAdmin")) {
                isAdmin = tag.getAttribute("isAdmin").getValue();
            }

            List<Map<String, Object>> ctprvnList = commonService.selectAlowedCtprvnList();

            //관리자 적용시
            if (isAdmin.equals("true")) {
                result.append("<div class=\"border-checkbox-section\">");

                int cnt = 1;
                for (Map<String, Object> ctprvnInfo : ctprvnList) {
                    result.append("<div class=\"border-checkbox-group border-checkbox-group-inverse\">");
                    result.append("<input type='checkbox' class='border-checkbox ").append(addClass)
                            .append("'  id='").append(attributeValue).append(cnt)
                            .append("' name ='").append(attributeValue)
                            .append("' value='").append(ctprvnInfo.get("CTPRVN_CD"))
                            .append("' data-cd-name='").append(ctprvnInfo.get("CTPRVN_NM"))
                            .append("'");
                    if (selectedCd != null && !(selectedCd.trim()).equals("")
                            && inArray(selectedCds, String.valueOf(ctprvnInfo.get("CTPRVN_CD")))) {
                        result.append(" checked ");
                    }

                    result.append(" >");
                    result.append("<label class='form-label border-checkbox-label' for='" + attributeValue + cnt + "'>" + ctprvnInfo.get("CTPRVN_NM") + "</label>");
                    result.append("\n");
                    result.append("</div>");
                    cnt++;
                }
                result.append("</div>");
            } else {

                result.append("<div class=\"form-check-list\">\n");

                int cnt = 1;
                for (Map<String, Object> ctprvnInfo : ctprvnList) {
                    result.append("<label class=\"inp\">");
                    result.append("<input type='checkbox' class='").append(addClass).append("'  id='").append(attributeValue).append(cnt).append("' name ='").append(attributeValue).append("' value='").append(ctprvnInfo.get("CTPRVN_CD")).append("'");
                    if (selectedCd != null && !(selectedCd.trim()).equals("")
                            && inArray(selectedCds, String.valueOf(ctprvnInfo.get("CTPRVN_CD")))) {
                        result.append(" checked ");
                    }
                    result.append(" ><b>").append(ctprvnInfo.get("CTPRVN_NM")).append("</b>").append("</label>\n");
                    cnt++;
                }

                result.append("</div>\n");
            }


        } catch (Exception e) {
            result.append("<p>시도 목록 조회중 에러 발생 Error 발생 </p>");
        }


        structureHandler.replaceWith(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), true

        );
    }

    private boolean inArray(String[] arr, String value) {
        return Arrays.asList(arr).contains(value);
    }

}
