package com.kbrainc.plum.mng.faq.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqVo;


public interface FaqService {
    List<FaqVo> getList(FaqVo param) throws Exception;
    
    FaqVo getFaq(FaqVo param) throws Exception;
    
    int insertFaq(FaqVo param) throws Exception;

    int updateFaq(FaqVo param) throws Exception;
    
    List<FaqClVo> getClList(FaqClVo param) throws Exception;
    
    boolean addFaqCl(FaqClVo param) throws Exception;
    
    boolean deleteFaqCl(FaqClVo param) throws Exception;
    
    boolean updateFaqCl(FaqClVo param) throws Exception;

    public boolean updateFaqOrd(Map<String,Object> faqInfo);

    public boolean updateFaqClOrdUp(FaqClVo param);
    
    public boolean updateFaqClOrdDown(FaqClVo param);
    
    public List<FaqClVo> getAllList(FaqClVo param) throws Exception;
}
