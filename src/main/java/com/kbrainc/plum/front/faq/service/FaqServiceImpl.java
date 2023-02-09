package com.kbrainc.plum.front.faq.service;

import com.kbrainc.plum.front.faq.model.FaqClVo;
import com.kbrainc.plum.front.faq.model.FaqDao;
import com.kbrainc.plum.front.faq.model.FaqVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 자주묻는 질문 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.service
 * - FaqServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqServiceImpl
 * @Description : 자주묻는 질문 서비스 구현 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

@Service("front.faqService")
@Alias("front.faqService")
public class FaqServiceImpl extends PlumAbstractServiceImpl implements FaqService{

    @Resource(name = "front.faqDao")
    private FaqDao faqDao;

    @Override
    public List<FaqVo> selectFaqList(FaqVo faqVo) throws Exception {
        return faqDao.selectFaqList(faqVo);
    }

    @Override
    public List<FaqClVo> selectFaqClList(FaqVo faqVo) throws Exception {
        return faqDao.selectFaqClList(faqVo);
    }
}
