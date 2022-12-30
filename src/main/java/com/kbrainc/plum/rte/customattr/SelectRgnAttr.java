package com.kbrainc.plum.rte.customattr;

import java.util.List;
import java.util.Map;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.kbrainc.plum.cmm.service.CommonService;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
* 전체 지역목록(시군구) 셀렉트 박스.
*
* <pre>
* com.kbrainc.plum.rte.customattr
* - SelectRgnAttr.java
* </pre>
*
* @ClassName : SelectRgnAttr
* @Description : 전체 지역목록 셀렉트 박스.
* @author : KBRAINC
* @date : 2022. 12. 29.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public class SelectRgnAttr extends AbstractAttributeTagProcessor {

    // ATTR name 설정
    private static final String ATTR_NAME = "select_rgn";
    private static final int PRECEDENCE = 1000;

    CommonService commonService = (CommonService) CommonUtil.getBean("commonServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of SelectRgnAttr.java class
     * @param dialectPrefix :
     */
    public SelectRgnAttr(final String dialectPrefix) {
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

        boolean isFirstOptTxt = false;
        boolean isFirstOptVal = false;
        
        // 기본 옵션
        String selectedCd = null;
        String firstOptTxt = "";
        String firstOptVal = "";
        String addClass = "";
        String width = null;
        StringBuffer result = new StringBuffer(); // html 코드 작성용

        try {
            if (tag.hasAttribute("selectedCd")) {
                selectedCd = tag.getAttribute("selectedCd").getValue();
            }

            if (tag.hasAttribute("firstOptTxt")) {
                firstOptTxt = tag.getAttribute("firstOptTxt").getValue();
                isFirstOptTxt = true;
            }
            
            if (tag.hasAttribute("firstOptVal")) {
                firstOptVal = tag.getAttribute("firstOptVal").getValue();
                isFirstOptVal = true;
            }
            
            if (isFirstOptTxt && !isFirstOptVal) {
                result.append(" <p> isFirstOptVal=null 값을 입력해 주십시오. </p>");
                structureHandler.setBody(result.toString(), false);
            }
            
            if (isFirstOptVal && !isFirstOptTxt) {
                result.append(" <p> isFirstOptTxt=null 값을 입력해 주십시오. </p>");
                structureHandler.setBody(result.toString(), false);
            }

            if (tag.hasAttribute("addClass")) {
                addClass = tag.getAttribute("addClass").getValue();
            }

            if (tag.hasAttribute("width")) {
                width = tag.getAttribute("width").getValue();
            }

            result.append("<input type='text'").append(" selectSearchCondition='Rgn' id='").append(attributeValue).append("'").append(" name='").append(attributeValue)
            .append("'")
            .append(" style='position:absolute;width:0px;margin:0px;padding:0px;visibility:hidden;' ")
            .append(" />\n");
            result.append("<select class='").append(addClass).append("' id='selectRgn").append(attributeValue).append("' ");
            result.append(" >").append("\n");
            result.append("</select> ").append("\n");
            
            result.append("<script type='text/javascript'>\n");
            result.append("var selectRgn").append(attributeValue).append("Data = [\n");
            
            if (isFirstOptVal && isFirstOptTxt) {
                result.append(makeObj(firstOptVal, firstOptTxt, null, firstOptVal));
            }
            
            List<Map<String, Object>> rgnList = commonService.selectAllRgnList();
            
            for (Map<String, Object> inst : rgnList) {
                result.append(makeObj(String.valueOf(inst.get("RGN_CD")), (String)inst.get("CTPRVN_NM"), (String)inst.get("SIGNGU_NM"), String.valueOf(inst.get("RGN_CD"))));
            }
            
            result.append("];\n");
            
            result.append("$(document).ready(function(){\n");
            result.append("$('#").append("selectRgn").append(attributeValue).append("').select2({\n");
            result.append("    language: {\n");
            result.append("        noResults: function () {\n");
            result.append("            return '검색결과가 없습니다.';\n");
            result.append("        }\n");
            result.append("    },\n");
            result.append("    data: ").append("selectRgn").append(attributeValue).append("Data,\n");
            result.append("    templateSelection: function (item) {\n");
            result.append("        document.getElementById('").append(attributeValue).append("').value = item.value;\n");
            result.append("        return item.text;\n");
            result.append("    }\n");
            result.append("});\n");
            
            if (selectedCd != null) {
                if ("".equals(selectedCd)) {
                    selectedCd = " "; // cd값은 공백일수 없음
                }
                boolean isThereValue = false;
                for (Map<String, Object> rgn : rgnList) {
                    if((rgn.get("RGN_CD").toString()).equals(selectedCd)) {
                        isThereValue = true;
                        break;
                    }
                }
                if(isThereValue) {
                    result.append("$('#").append("selectRgn").append(attributeValue).append("').val('").append(selectedCd).append("').trigger('change');\n"); // 초기값이 있는 경우 선택
                }else {
                    result.append("$('#").append("selectRgn").append(attributeValue).append("').val(selectRgn").append(attributeValue).append("Data[0].id).trigger('change');\n"); // 초기값이 없는경우 첫번째 데이터 선택
                }
            } else {
                result.append("$('#").append("selectRgn").append(attributeValue).append("').val(selectRgn").append(attributeValue).append("Data[0].id).trigger('change');\n"); // 초기값이 없는경우 첫번째 데이터 선택
            }
            
            if (width != null) {
                result.append("$('#").append("selectRgn").append(attributeValue).append("').parent().find('span.select2').attr('style','width: ").append(width).append(" !important');\n");
            }
            
            result.append("});\n");
            
            result.append("</script>\n");

        } catch (Exception e) {
            result.append("<p>지역 목록 조회중 에러 발생 Error 발생 </p>");
        }

        structureHandler.replaceWith(result.toString(), false);

    }

    private String makeObj(String cd, String text, String text2, String value) {
        StringBuffer objStr = new StringBuffer();
        StringBuffer finalText = new StringBuffer();
        finalText.append(text);
        
        if ("".equals(cd)) {
            cd = " "; // id값은 공백일수 없음
        }
        
        if (text2 != null) {
            finalText.append(" ").append(text2);
        }
        
        objStr.append("{");
        objStr.append("id: '").append(cd).append("', ");
        objStr.append("text: '").append(finalText.toString()).append("', ");
        objStr.append("value: '").append(value).append("' ");
        objStr.append("},\n");
        return objStr.toString();
    }
}