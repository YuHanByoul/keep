package com.kbrainc.plum.rte.customattr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
 * 
 * selectBox 속성으로 처리
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - CheckCodeAttr.java
 * </pre> 
 *
 * @ClassName : CheckCodeAttr
 * @Description : selectBox 속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class CheckCodeVerticalAttr extends AbstractAttributeTagProcessor {

    // ATTR name 설정
    private static final String ATTR_NAME = "check_code_v";
    private static final int PRECEDENCE = 1000;

    ResCodeService resCode = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of CheckCodeAttr.java class
     * @param dialectPrefix : 
     */
    public CheckCodeVerticalAttr(final String dialectPrefix) {
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
            @Valid
            String grpcd = ""; // 그룹cd
            String selectedCd = "";// default cd
            String[] selectedCds = null;
            String readOnly = "";// default cd
            String[] readOnlys = null;
            String upprCd = ""; // upper Code
            String listStyle = "";
            String addClass = "";
            String addUlClass = "";
            String isAdmin = "false";

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
            }
            
            if (tag.hasAttribute("readOnly")) {
                readOnly = tag.getAttribute("readOnly").getValue();
            }
            
            if (selectedCd != null) {
                selectedCds = selectedCd.split(",");
            }
            
            if (readOnly != null) {
                readOnlys = readOnly.split(",");
            }
            
            if (tag.hasAttribute("addClass") && !tag.getAttribute("addClass").getValue().equals("")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("addUlClass") && !tag.getAttribute("addUlClass").getValue().equals("")) {
                addUlClass = tag.getAttribute("addUlClass").getValue();
            }
            
            if (tag.hasAttribute("upprCd")) {
                upprCd = tag.getAttribute("upprCd").getValue();
                codeList = resCode.getCodeList(grpcd, upprCd);
            } else {
                codeList = resCode.getCodeList(grpcd);
            }
            
            if (tag.hasAttribute("listStyle")) {
                listStyle = tag.getAttribute("listStyle").getValue();
            }
            
            if (tag.hasAttribute("isAdmin")) {
                isAdmin = tag.getAttribute("isAdmin").getValue();
            }
            
            //관리자 적용시 
            if(isAdmin.equals("true")) {
                
                //result.append("<div class=\"card-block-small p-t-0\">");
                
                result.append("<div class=\"border-checkbox-section\">");
                
                if (codeList.size() <= 0) {
                        result.append(" <p> 조회 된 코드 목록이 없습니다.</p>");
                } else {
                    int cnt = 1;
                    for (CodeInfoVo codeInfoVo : codeList) {
                        result.append("<div class=\"border-checkbox-group border-checkbox-group-inverse\">");
                        result.append("<input type='checkbox' class='border-checkbox ").append(addClass)
                        .append("'  id='").append(attributeValue).append(cnt)
                        .append("' name ='").append(attributeValue)
                        .append("' value='").append(codeInfoVo.getCd())
                        .append("' data-cd-name='").append(codeInfoVo.getCdNm())
                        .append("'");
                        if (selectedCd != null && !(selectedCd.trim()).equals("")
                                && inArray(selectedCds, codeInfoVo.getCd())) {
                            result.append(" checked ");
                        }
                        if (readOnly != null && !(readOnly.trim()).equals("")
                                && inArray(readOnlys, codeInfoVo.getCd())) {
                            result.append(" onclick='return false;' ");
                        }
                        result.append(" >");
                        result.append("<label class='form-label border-checkbox-label' for='"+attributeValue+cnt+"'>"+codeInfoVo.getCdNm()+"</label>");
                        result.append("\n");
                        result.append("</div>");
                        result.append("</br>");
                        cnt++;
                    }
                    result.append("</div>");
                    //result.append("</div>");
                }
                
            }else{//사용자 
                
                if("ul".equals(listStyle)) {
                    result.append("<ul id='" + attributeValue + "' class='" + addUlClass + "'>");
                }
                if (codeList.size() <= 0) {
                    if("ul".equals(listStyle)) {
                        result.append("<li><p> 조회 된 코드 목록이 없습니다.</p></li>");   
                    } else {
                        result.append(" <p> 조회 된 코드 목록이 없습니다.</p>");
                    }
                } else {
                    int cnt = 1;
                    for (CodeInfoVo codeInfoVo : codeList) {
                        if("ul".equals(listStyle)) {
                            result.append("<li><label>");
                        }
                        result.append("<input type='checkbox' class='").append(addClass).append("'  id='").append(attributeValue).append(cnt).append("' name ='").append(attributeValue).append(cnt).append("' value='").append(codeInfoVo.getCd()).append("' data-cd-name='").append(codeInfoVo.getCdNm()).append("'");
                        if (selectedCd != null && !(selectedCd.trim()).equals("")
                                && inArray(selectedCds, codeInfoVo.getCd())) {
                            result.append(" checked ");
                        }
                        result.append(" >").append(codeInfoVo.getCdNm()).append("\n");
                        if("ul".equals(listStyle)) {
                            result.append("</label></li>");
                        }
                        cnt++;
                    }
                }
                if("ul".equals(listStyle)) {
                    result.append("</ul>");
                }
            }
            

        } catch (NullPointerException e) {
            result.append(" <p> code=null 코드 값을 입력해 주십시오. </p>");
        } catch (Exception e) {
            result.append("<p>코드 목록 조회중 에러 발생 Error 발생 </p>");
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