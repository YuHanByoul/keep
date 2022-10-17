package com.kbrainc.plum.rte.customattr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * 
 * selectBox 속성으로 처리
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - DateAttr.java
 * </pre> 
 *
 * @ClassName : DateAttr
 * @Description : selectBox 속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class DateAttr extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateAttr.class);

    // ATTR name 설정
    private static final String ATTR_NAME = "date_pic";
    private static final int PRECEDENCE = 1000;

    /**
     * .
     * Desc : Constructor of DateAttr.java class
     * @param dialectPrefix :
     */
    public DateAttr(final String dialectPrefix) {
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
            String chkSingle = "1"; // 그룹cd
            String sId = "startDate";
            String eId = "endDate";
            String sDefault = "";
            String eDefault = "";
            String sLabel = "시작일";
            String eLabel = "종료일";
            String dForm = "yymmdd";
            String sReadonly = "";

            // prameter 유효성 체크
            if (attributeValue != null && attributeValue.equals("2")) {
                chkSingle = "2";
            }
            if (tag.hasAttribute("s_id") && !tag.getAttribute("s_id").getValue().equals("")) {
                sId = tag.getAttribute("s_id").getValue();
            }
            if (tag.hasAttribute("e_id") && !tag.getAttribute("e_id").getValue().equals("")) {
                eId = tag.getAttribute("e_id").getValue();
            }
            if (tag.hasAttribute("s_default") && !tag.getAttribute("s_default").getValue().equals("")) {
                sDefault = tag.getAttribute("s_default").getValue();
            }
            if (tag.hasAttribute("e_default") && !tag.getAttribute("e_default").getValue().equals("")) {
                eDefault = tag.getAttribute("e_default").getValue();
            }

            if (tag.hasAttribute("s_label") && !tag.getAttribute("s_label").getValue().equals("")) {
                sLabel = tag.getAttribute("s_label").getValue();
            }
            if (tag.hasAttribute("e_label") && !tag.getAttribute("e_label").getValue().equals("")) {
                eLabel = tag.getAttribute("e_label").getValue();
            }
            if (tag.hasAttribute("d_form") && !tag.getAttribute("d_form").getValue().equals("")) {
                dForm = tag.getAttribute("d_form").getValue();
            }
            
            if (tag.hasAttribute("s_readonly") && !tag.getAttribute("s_readonly").getValue().equals("")) {
                sReadonly = tag.getAttribute("s_readonly").getValue();
            }
            

            if (chkSingle.equals("2")) {
                result.append("<div class=\"form-inline\">").append("\n");
                result.append("<div class=\"form-group\">").append("\n");
            }
            result.append("<span>").append(sLabel).append(" : </span>").append("\n");

            result.append("<input type=\"text\" class=\"form-control\"  id=\"").append(sId).append("\" name=\"").append(sId).append("\"  value=\"").append(sDefault).append("\" placeholder=\"").append(sLabel).append("\" ").append(sReadonly).append(">").append("\n");

            if (chkSingle.equals("2")) {

                result.append("<span>").append(eLabel).append(" : </span>").append("\n");
                result.append("<input type=\"text\" class=\"form-control\"  id=\"").append(eId).append("\" name=\"").append(eId).append("\" value=\"").append(eDefault).append("\" placeholder=\"").append(eLabel).append("\" ").append(sReadonly).append(">").append("\n");
                result.append("</div >").append("\n");
                result.append("</div >").append("\n");
            }

            result.append("<style>").append("\n");
            result.append(".hasDatepicker{cursor: pointer;}").append("\n");
            result.append("</style>").append("\n");

            result.append("<script type=\"text/javascript\">").append("\n");
            result.append("   $(function(){").append("\n");
            result.append("       $.datepicker.setDefaults({").append("\n");
            result.append("       dateFormat : \"").append(dForm).append("\"").append("\n");
            result.append("       ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']").append("\n");
            result.append("       ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']")
                    .append("\n");
            result.append("       ,dayNamesMin: ['일','월','화','수','목','금','토']").append("\n");
            result.append("       ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']").append("\n");
            result.append("       ,showOtherMonths: true ").append("\n");
            result.append("       ,showMonthAfterYear:true").append("\n");
            result.append("       ,changeMonth: true").append("\n");
            result.append("       ,changeYear: true").append("\n");
            result.append("   });").append("\n");

            result.append("$(\"#").append(sId).append("\").datepicker();").append("\n");
            if (chkSingle.equals("2")) {
                result.append("$(\"#").append(eId).append("\").datepicker();").append("\n");
            }

            result.append("})").append("\n");
            result.append("</script>").append("\n");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("<p>에러발생 </p>");
        }
        structureHandler.replaceWith(result.toString(), true

        );

    }

}