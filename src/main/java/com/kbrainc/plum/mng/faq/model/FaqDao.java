package com.kbrainc.plum.mng.faq.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface FaqDao {
    public List<FaqVo> getList(FaqVo param);

    public FaqVo getFaq(FaqVo param);

    public int insertFaq(FaqVo param);

    public int updateFaq(FaqVo param);

    public FaqVo getUpdateFaq(FaqVo faqVo);

    public boolean updateFaqOrdUp(FaqVo faqVo);

    public boolean updateFaqOrdDown(FaqVo faqVo);
}
