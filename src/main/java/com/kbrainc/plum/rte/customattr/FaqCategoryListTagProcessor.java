package com.kbrainc.plum.rte.customattr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.mng.faq.model.FaqClDao;
import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.rte.util.ApplicationContextProvider;

public class FaqCategoryListTagProcessor extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaqCategoryListTagProcessor.class);
    private static final String ATTR_NAME = "faq-category-list";
    private static final int PRECEDENCE = 10000;

    /**
     * .
     * Desc : Constructor of FaqCategoryListTagProcessor.java class
     * @param dialectPrefix : 
     */
    public FaqCategoryListTagProcessor(final String dialectPrefix) {
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
        structureHandler.setAttribute("name", attributeValue);
        String value = "";
        String isAdmin = "";
        String applyClass = "";
        String addClass = "";
        String addStyle ="";
        String selectedCd ="";

        StringBuilder strOpt = new StringBuilder();
        List<FaqClVo> faqClList = new ArrayList<>();

        if (tag.hasAttribute("value")) {
           value = tag.getAttribute("value").getValue();
        }

        if(tag.hasAttribute("selectedCd")) {
            selectedCd = tag.getAttribute("selectedCd").getValue();
        }
        if (tag.hasAttribute("addClass")) {
            addClass = tag.getAttribute("addClass").getValue();
        }

        if (tag.hasAttribute("addStyle")) {
            addStyle = tag.getAttribute("addStyle").getValue();
        }

        FaqClDao faqClDao = ApplicationContextProvider.getApplicationContext().getBean(FaqClDao.class);

        if (tag.hasAttribute("isAdmin")) {
            isAdmin = tag.getAttribute("isAdmin").getValue();
            applyClass = (isAdmin.equals("true"))? "form-select form-select-sm form-control-sm  ":" ";
        }else {
            //사용자 class 적용 할것
            applyClass = " ";
        }


        try {
            FaqClVo param = new FaqClVo();
            param.setUseYn("A");
            param.setClid(value.equals("") ? 0 : Integer.valueOf(value));

            List<FaqClVo> list = faqClDao.getAllList(param);
                strOpt.append("<select  class ='").append(applyClass+" ").append(addClass).append("' style='").append(addStyle).append("'  id='").append(attributeValue).append("'  name='").append(attributeValue).append("' ");
                if (tag.hasAttribute("onchange") && !tag.getAttribute("onchange").getValue().equals("")) {
                    String changeFunction = tag.getAttribute("onchange").getValue();
                    strOpt.append("    onchange ='").append(changeFunction).append("()' ");
                }
                strOpt.append(" >").append("\n");

                strOpt.append("<option value=\"\">- 전체 - </option>");

                for (FaqClVo item : list) {
                    if (value != null && !(value.trim()).equals("") && item.getClid().equals(Integer.valueOf(value))) {
                        strOpt.append("<option value='").append(item.getClid()).append("' selected>").append(item.getClNm()).append("</option>")
                                .append("\n");
                    } else {
                        strOpt.append("<option value='").append(item.getClid()).append("' >").append(item.getClNm()).append("</option>")
                                .append("\n");
                    }
                }
                strOpt.append("</select> ").append("\n");

        } catch (SQLException e) {
            LOGGER.error("doProcess.SQLException.65L");
        } catch (Exception e) {
            LOGGER.error("doProcess.Exception.65L");
        }

        structureHandler.replaceWith(
                // structureHandler.setBody(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                strOpt.toString(), false);

    }

}
