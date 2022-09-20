package com.kbrainc.plum.mng.faq.service;

import java.util.List;

import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqVo;


public interface FaqService {
    List<FaqVo> getList(FaqVo param) throws Exception;
    
    FaqVo getFaq(FaqVo param) throws Exception;
    
    boolean addFaq(FaqVo param) throws Exception;
    
    boolean deleteFaq(FaqVo param) throws Exception;
    
    boolean updateFaq(FaqVo param) throws Exception;
    
    List<FaqClVo> getClList(FaqClVo param) throws Exception;
    
    boolean addFaqCl(FaqClVo param) throws Exception;
    
    boolean deleteFaqCl(FaqClVo param) throws Exception;
    
    boolean updateFaqCl(FaqClVo param) throws Exception;
    
    public boolean modifyFaqOrdUp(FaqVo param);
    
    public boolean modifyFaqOrdDown(FaqVo param);
    
    public boolean modifyFaqClOrdUp(FaqClVo param);
    
    public boolean modifyFaqClOrdDown(FaqClVo param);
    
    public List<FaqClVo> getAllList(FaqClVo param) throws Exception;
}
