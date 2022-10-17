package com.kbrainc.plum.rte.customattr;

import java.util.ArrayList;
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
 * - RadioCodeAttr.java
 * </pre> 
 *
 * @ClassName : RadioCodeAttr
 * @Description : selectBox 속성으로 처리
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class RadioCodeAttr extends AbstractAttributeTagProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RadioCodeAttr.class);

    // ATTR name 설정
    private static final String ATTR_NAME = "radio_code";
    private static final int PRECEDENCE = 1000;

    ResCodeService resCode = (ResCodeService) CommonUtil.getBean("resCodeServiceImpl", CommonUtil.getCurrentRequest());

    /**
     * .
     * Desc : Constructor of RadioCodeAttr.java class
     * @param dialectPrefix :
     */
    public RadioCodeAttr(final String dialectPrefix) {
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
            String upprCd = ""; // upper Code

            String disabled = ""; // default cd
            String[] checkDis;

            List<CodeInfoVo> codeList = new ArrayList<>();
            // List<CodeInfoVo> codeList = new ArrayList<CodeInfoVo>();

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

            if (tag.hasAttribute("disabled")) {
                disabled = tag.getAttribute("disabled").getValue();
            }

            if (tag.hasAttribute("upprCd")) {
                upprCd = tag.getAttribute("upprCd").getValue();
                codeList = resCode.getCodeList(grpcd, upprCd);
            } else {
                codeList = resCode.getCodeList(grpcd);
            }

            if (codeList.size() <= 0) {
                result.append(" <p> 조회 된 코드 목록이 없습니다.</p>");
            } else {
                int cnt = 1;
                for (CodeInfoVo codeInfoVo : codeList) {
                    result.append("<label>").append("\n");
                    result.append("<input type='radio' name = '" + attributeValue + "' id='" + attributeValue + cnt
                            + "'value='" + codeInfoVo.getCd() + "'");
                    if (selectedCd != null && !(selectedCd.trim()).equals("")
                            && codeInfoVo.getCd().equals(selectedCd)) {
                        result.append(" checked ");
                    }
                    if (disabled != null && !(disabled.trim()).equals("")) {
                        checkDis = disabled.split("\\|");
                        for (int i = 0; i < checkDis.length; i++) {
                            if (codeInfoVo.getCd().equals(checkDis[i])) {
                                result.append(" disabled  ");
                            }
                        }
                    }
                    result.append("><span>").append(codeInfoVo.getCdNm()).append("</span>\n");
                    result.append("</label>&nbsp; ");
                    cnt++;
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            result.append(" <p> code=null 코드 값을 입력해 주십시오. </p>");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("<p>코드 목록 조회중 에러 발생 Error 발생 </p>");
        }

        structureHandler.replaceWith(
                // HtmlEscape.escapeHtml5(result.toString()) , false
                result.toString(), true);

    }

}