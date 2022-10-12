package com.kbrainc.plum.rte.customattr;

import com.kbrainc.plum.mng.faq.model.FaqClDao;
import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.rte.util.ApplicationContextProvider;

import java.util.List;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class FaqCategoryListTagProcessor extends AbstractAttributeTagProcessor {

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
        String value = tag.getAttributeValue("value");
        if (value == null) {
            value = "0";
        }

        FaqClDao faqClDao = ApplicationContextProvider.getApplicationContext().getBean(FaqClDao.class);

        String strOpt = "";
        strOpt += "<option value=\"\">-전체-</option>";
        try {
            FaqClVo param = new FaqClVo();
            param.setUseYn("A");
            List<FaqClVo> list = faqClDao.getAllList(param);
            for (FaqClVo item : list) {

                strOpt += "<option value=\"" + item.getClid() + "\" "
                        + (item.getClid() == Integer.parseInt(value) ? "selected" : "") + ">" + item.getClNm()
                        + "</option>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        structureHandler.setBody(strOpt, false);

    }

}
