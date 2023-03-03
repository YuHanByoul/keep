package com.kbrainc.plum.rte.customattr;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

/**
 * 
 * Dialect 추가 클래스
 *
 * <pre>
 * com.kbrainc.plum.rte.customattr
 * - AttrConfig.java
 * </pre> 
 *
 * @ClassName : AttrConfig
 * @Description : Dialect 추가 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Component
public class AttrConfig extends AbstractProcessorDialect {
 
    /**
     * 생성자 Dialect name, Dialect prefix 등 설정.
     * Desc : Constructor of AttrConfig.java class
     */
    public AttrConfig() {
        super("kbrain Dialect", // Dialect name
                "kattr", // Dialect prefix (kattr:*)
                1000); // Dialect precedence
    }

    /**.
     * @Title : getProcessors
     * @Description : process 에 추가 구현 된 커스텀 클래스를 추가
     * @return
     */
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        // 커스텀 속성 클래스 추가
        processors.add(new CheckCodeAttr(dialectPrefix));
        processors.add(new SelectCodeAttr(dialectPrefix));
        processors.add(new SelectCodesAttr(dialectPrefix));
        processors.add(new RadioCodeAttr(dialectPrefix));
        processors.add(new ExprtRadioCodeAttr(dialectPrefix));
        processors.add(new CustomDateAttr(dialectPrefix));
        processors.add(new FaqCategoryListTagProcessor(dialectPrefix));
        processors.add(new RadioYnAttr(dialectPrefix));
        processors.add(new PaginationAttr(dialectPrefix));
        processors.add(new SelectInstAttr(dialectPrefix));
        processors.add(new SelectRgnAttr(dialectPrefix));
        processors.add(new SelectSiteAttr(dialectPrefix));
        processors.add(new SwitchYnAttr(dialectPrefix));
        processors.add(new CheckCodeVerticalAttr(dialectPrefix));
        
        return processors;
    }

}