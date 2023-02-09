package com.kbrainc.plum.front.faq.service;

import com.kbrainc.plum.front.faq.model.FaqClVo;
import com.kbrainc.plum.front.faq.model.FaqVo;

import java.util.List;

/**
 * 자주묻는 질문 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.service
 * - FaqService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqService
 * @Description : 자주묻는 질문 서비스 인터페이스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface FaqService {
    public List<FaqVo> selectFaqList(FaqVo faqVo) throws Exception;

    public List<FaqClVo> selectFaqClList(FaqVo faqVo) throws Exception;
}
