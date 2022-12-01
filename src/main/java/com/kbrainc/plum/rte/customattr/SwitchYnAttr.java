package com.kbrainc.plum.rte.customattr;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

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
public class SwitchYnAttr extends AbstractAttributeTagProcessor {

    // ATTR name 설정
    private static final String ATTR_NAME = "switch_yn";
    private static final int PRECEDENCE = 1000;

    //ResCodeService resCode = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of RadioYnAttr.java class
     * @param dialectPrefix :
     */
    public SwitchYnAttr(final String dialectPrefix) {
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
            String label = "";
            String defaultVal = "N";
            String addStyle = ""; 
            String isAdmin = "";

            // prameter 유효성 체크
            if (attributeValue == null || attributeValue.equals("")) {
                attrId = "swtich_yn";
            }
            if (tag.hasAttribute("label")) {
                label = tag.getAttribute("label").getValue();
            }
            if (tag.hasAttribute("defaultVal")) {
                defaultVal = tag.getAttribute("defaultVal").getValue();
            }

            
            if (tag.hasAttribute("isAdmin")) {
                isAdmin = tag.getAttribute("isAdmin").getValue();
            }
            
            //관리자 적용시 
            if(isAdmin.equals("true")) {
                
                //Switch html 
                //result.append("<div class=\"pt-0 card-block-small\">");
                
                result.append("<input type='hidden' class='js-single' />");
                result.append("<input type='checkbox' class='js-single-small' ");
                result.append("name='"+attributeValue+"' id='"+attributeValue+"' ");
                
                if (defaultVal.equals("Y")) {
                    result.append("checked");
                }
                
                //result.append(" value='"+defaultVal+"' ");
                
                if (tag.hasAttribute("onchange") && !tag.getAttribute("onchange").getValue().equals("")) {
                    String changeFunction = tag.getAttribute("onchange").getValue();
                    result.append(" onchange ='"+changeFunction+"()'");
                }
                result.append(" />");
                result.append("&nbsp;<label for='"+attributeValue+"' class='v-middle' >" + label+"</label>");
                
                //result.append("</div>");
                
            }else {//사용자 적용시
                
            }
            
        } catch (NullPointerException e) {
            result.append("<p>NullPointerException 발생 </p>");
        } catch (Exception e) {
            result.append("<p>Error 발생 </p>");
        }
        structureHandler.replaceWith(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), true);

    }

}