package com.kbrainc.plum.front.faq.model;

import org.apache.ibatis.type.Alias;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 자주묻는 질문 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.model
 * - FaqDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqDao
 * @Description : 자주묻는 질문 Dao 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.faqDao")
@Alias("front.faqDao")
public interface FaqDao {
    public List<FaqVo> selectFaqList(FaqVo faqVo) throws Exception;

    public List<FaqClVo> selectFaqClList(FaqVo faqVo) throws Exception;

}
