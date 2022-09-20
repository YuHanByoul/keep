package com.kbrainc.plum.mng.faq.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface FaqDao {
    public List<FaqVo> getList(FaqVo param);

    public FaqVo getFaq(FaqVo param);

    public boolean addFaq(FaqVo param);

    public boolean deleteFaq(FaqVo param);

    public boolean updateFaq(FaqVo param);

    public boolean modifyFaqOrdUp(FaqVo param);

    public boolean modifyFaqOrdByfaqid(FaqVo param);

    public boolean modifyFaqOrdDown(FaqVo param);

}
