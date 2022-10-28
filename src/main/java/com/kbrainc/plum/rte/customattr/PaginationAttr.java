package com.kbrainc.plum.rte.customattr;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
 * <pre>
 * com.zeniel.airecruit.core.tag - SelectCode.java
 * </pre>
 *
 * @ClassName : PaginationAttr
 * @Description : pagination 태그 반환
 * @author : KBRAINC
 * @date : 2020. 08. 25
 * @Version : 
 * @Company : CopyrightⒸ KBRAINC. All Rights Reserved
 *
 */
public class PaginationAttr extends AbstractAttributeTagProcessor {

    // ATTR name 설정
    private static final String ATTR_NAME = "pagination";
    private static final int PRECEDENCE = 1000;
    
    /**
     * .
     * Desc : Constructor of RadioYnAttr.java class
     * @param dialectPrefix :
     */
    public PaginationAttr(final String dialectPrefix) {
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
    	HttpServletRequest request = CommonUtil.getCurrentRequest();
        StringBuffer result = new StringBuffer();
        try {
            String listName = attributeValue;
            String onclick = "";
            int pageCount = 5;

            // prameter 유효성 체크
            if (attributeValue == null || attributeValue.equals("")) {
                listName = "list";
            }
            if (tag.hasAttribute("onclick")) {
            	onclick = tag.getAttribute("onclick").getValue();
            }
            if (tag.hasAttribute("pageCount")) {
            	pageCount = Integer.valueOf(tag.getAttribute("pageCount").getValue());
            }
            
            List<ParentRequestVo> list = (List<ParentRequestVo>)request.getAttribute("list");

			result.append("<div class=\"panel-body center-align-text\">");
			result.append("<ul class=\"pagination no-margin\">");
    		
            if (list != null && list.size() > 0) {
            	 int totalPage = list.get(0).getTotalPage();
            	 int pageNumber = list.get(0).getPageNumber();
            	 
            	 String previousPageTag	= "<li class=\"{2}\"><a href=\"javascript:{0}({1});\">&laquo;</a></li>";
                 String currentPageTag	= "<li class=\"active\"><a href=\"#\">{0}</a>";
                 String otherPageTag		= "<li><a href=\"javascript:{0}({1});\">{2}</a>";
                 String nextPageTag		= "<li class=\"{2}\"><a href=\"javascript:{0}({1});\">&raquo;</a></li>";
                 
                 if (totalPage < pageNumber) {
                	 pageNumber = totalPage;
                 }

                 int firstPageNum = ((pageNumber - 1) / pageCount) * pageCount + 1;
                 int lastPageNum = firstPageNum + pageCount - 1;

                 if (lastPageNum > totalPage) {
                	 lastPageNum = totalPage;
                 }
                	
                 if (pageNumber > 1) {
                	 result.append(MessageFormat.format(previousPageTag, new Object[] { onclick, Integer.toString(pageNumber - 1), "" }));
         		 }/* else {
         			result.append(MessageFormat.format(previousPageTag, new Object[] { "", 1, "disabled" }));
         		 }*/
         		
         		 for (int i = firstPageNum; i <= lastPageNum; i++) {
         			 if (i == pageNumber) {
         				 result.append(MessageFormat.format(currentPageTag, new Object[] { Integer.toString(i) }));
         			 } else {
         				 result.append(MessageFormat.format(otherPageTag, new Object[] { onclick, Integer.toString(i), Integer.toString(i) }));
         			 }
         		 }
         		 
         		 if (pageNumber < totalPage) {
         			 result.append(MessageFormat.format(nextPageTag, new Object[] { onclick, Integer.toString(pageNumber + 1), "" }));
         		 }/* else {
         			result.append(MessageFormat.format(nextPageTag, new Object[] { "", totalPage, "disabled" }));
         		 }*/
            }
            
            result.append("</ul>");
			result.append("</div>");

        } catch (Exception e) {
            result.append("<p>Error 발생 </p>");
        }
        structureHandler.replaceWith(result.toString(), true);
    }
}