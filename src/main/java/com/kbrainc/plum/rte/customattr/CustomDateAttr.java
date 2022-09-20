package com.kbrainc.plum.rte.customattr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * - CustomDateAttr.java
 * </pre> 
 *
 * @ClassName : CustomDateAttr
 * @Description : selectBox 속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
public class CustomDateAttr extends AbstractAttributeTagProcessor { 


    //ATTR name 설정
    private static final String ATTR_NAME = "date_pic";
    private static final int PRECEDENCE = 1000;

    /**
     * .
     * Desc : Constructor of CustomDateAttr.java class
     * @param dialectPrefix :
     */
    public CustomDateAttr(final String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                ATTR_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's precedence)
                true);             // Remove the matched attribute afterwards
    }


    @Override
    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {

        StringBuffer result = new StringBuffer();

        try {
            //기본 옵션

            String dateDefault = "";
            String dateForm = "yymmdd";
            String addClass = ""; 
            String addStyle = "";

            //prameter 유효성 체크                 

            if (tag.hasAttribute("dateForm") && !tag.getAttribute("dateForm").getValue().equals("")) {
                dateForm = tag.getAttribute("dateForm").getValue();
            }

            if (tag.hasAttribute("dateDefaultToday") && tag.getAttribute("dateDefaultToday").getValue().equals("Y")) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                dateDefault = dateFormat.format(date);
            }

            if (tag.hasAttribute("dateDefault") && !tag.getAttribute("dateDefault").getValue().equals("")) {
                dateDefault = tag.getAttribute("dateDefault").getValue();
            }

            if (tag.hasAttribute("addClass") && !tag.getAttribute("addClass").getValue().equals("")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("addStyle") && !tag.getAttribute("addStyle").getValue().equals("")) {
                addStyle = tag.getAttribute("addStyle").getValue();
            }

            result.append("<input type=\"text\" class=\"form-control  " + addClass + "\"  id=\"" + attributeValue 
                    + "\" name=\"" + attributeValue + "\"  value=\"" + dateDefault + "\"   style=\"" + addStyle + "\">")
                    .append("\n");
            result.append("<style>").append("\n");
            result.append(".hasDatepicker{cursor: pointer;}").append("\n");
            result.append("</style>").append("\n");

            result.append("<script type=\"text/javascript\">").append("\n");
            result.append("   $(function(){").append("\n");
            result.append("       $.datepicker.setDefaults({").append("\n");
            result.append("       dateFormat : \"" + dateForm + "\"").append("\n");
            result.append("       ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']").append("\n");
            result.append("       ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']").append("\n");
            result.append("       ,dayNamesMin: ['일','월','화','수','목','금','토']").append("\n");
            result.append("       ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']").append("\n");
            result.append("       ,showOtherMonths: true ").append("\n");
            result.append("       ,showMonthAfterYear:true").append("\n");
            result.append("       ,changeMonth: true").append("\n");
            result.append("       ,changeYear: true").append("\n");
            result.append("   });").append("\n");

            result.append("$(\"#" + attributeValue + "\").datepicker();").append("\n");

            result.append("})").append("\n");
            result.append("</script>").append("\n");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("<p>에러발생 </p>");
        }
        structureHandler.replaceWith(result.toString(), true);

    }


}