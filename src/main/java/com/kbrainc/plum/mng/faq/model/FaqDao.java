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

    public int changeFaqOrd(@Param("faqid")Integer faqid, @Param("ord")Integer ord);

    public int selectSiteid(FaqVo param);
}
