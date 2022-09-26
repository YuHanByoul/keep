package com.kbrainc.plum.rte.customattr;

import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

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
 * - RadioYnAttr.java
 * </pre> 
 *
 * @ClassName : RadioYnAttr
 * @Description : selectBox 속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class RadioYnAttr extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadioYnAttr.class);

    // ATTR name 설정
    private static final String ATTR_NAME = "radio_yn";
    private static final int PRECEDENCE = 1000;

    ResCodeService resCode = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of RadioYnAttr.java class
     * @param dialectPrefix :
     */
    public RadioYnAttr(final String dialectPrefix) {
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
            String attrId = attributeValue;
            String label1 = "Y";
            String label2 = "N";
            String defaultVal = "Y";
            String addStyle = ""; 

            // prameter 유효성 체크
            if (attributeValue.equals(null) || attributeValue.equals("")) {
                attrId = "radio_";
            }
            if (tag.hasAttribute("label1")) {
                label1 = tag.getAttribute("label1").getValue();
            }
            if (tag.hasAttribute("label2")) {
                label2 = tag.getAttribute("label2").getValue();
            }
            if (tag.hasAttribute("defaultVal")) {
                defaultVal = tag.getAttribute("defaultVal").getValue();
            }
            if (tag.hasAttribute("addStyle") && !tag.getAttribute("addStyle").getValue().equals("")) {
                addStyle = tag.getAttribute("addStyle").getValue();
            }

            result.append("<label class=\"radio-inline\" style=\"" + addStyle + "\">").append("\n");
            result.append(
                    "<input type='radio' name = '" + attributeValue + "' id='" + attributeValue + "1" + "'value='Y'");
            if (defaultVal.equals("Y")) {
                result.append("checked");
            }
            if (tag.hasAttribute("onchange") && !tag.getAttribute("onchange").getValue().equals("")) {
                String changeFunction = tag.getAttribute("onchange").getValue().toString();
                result.append("    onchange ='" + changeFunction + "()' ");
            }
            
            result.append(">" + label1).append("\n");
            result.append("</label>");

            result.append("<label class=\"radio-inline\" style=\"" + addStyle + "\">").append("\n");
            result.append(
                    "<input type='radio' name = '" + attributeValue + "' id='" + attributeValue + "2" + "'value='N'");
            if (defaultVal.equals("N")) {
                result.append("checked");
            }
            if (tag.hasAttribute("onchange") && !tag.getAttribute("onchange").getValue().equals("")) {
                String changeFunction = tag.getAttribute("onchange").getValue().toString();
                result.append("    onchange ='" + changeFunction + "()' ");
            }
            
            result.append(">" + label2).append("\n");
            result.append("</label>");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("<p>Error 발생 </p>");
        }
        structureHandler.replaceWith(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), true);

    }

}