package com.kbrainc.plum.mng.faq.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface FaqClDao {

    public List<FaqClVo> getList(FaqClVo param) throws Exception;

    public boolean addFaqCl(FaqClVo param);

    public boolean deleteFaqCl(FaqClVo param);

    public boolean updateFaqCl(FaqClVo param);

    public List<FaqClVo> getAllList(FaqClVo param) throws Exception;

    public boolean modifyFaqClOrdUp(FaqClVo param);

    public boolean modifyFaqClOrdByfaqid(FaqClVo param);

    public boolean modifyFaqClOrdDown(FaqClVo param);

}
