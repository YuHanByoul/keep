package com.kbrainc.plum.mng.faq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.faq.model.FaqClDao;
import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqDao;
import com.kbrainc.plum.mng.faq.model.FaqVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

@Service
public class FaqServiceImpl extends PlumAbstractServiceImpl implements FaqService {

    @Autowired
    FaqDao faqDao;

    @Autowired
    FaqClDao faqClDao;

    @Override
    public List<FaqVo> getList(FaqVo param) throws Exception {
        return faqDao.getList(param);
    }

    @Override
    public FaqVo getFaq(FaqVo param) throws Exception {
        return faqDao.getFaq(param);
    }

    @Override
    public boolean addFaq(FaqVo param) throws Exception {
        return faqDao.addFaq(param);
    }

    @Override
    public boolean deleteFaq(FaqVo param) throws Exception {
        return faqDao.deleteFaq(param);
    }

    @Override
    public boolean updateFaq(FaqVo param) throws Exception {
        return faqDao.updateFaq(param);
    }

    @Override
    public List<FaqClVo> getClList(FaqClVo param) throws Exception {
        return faqClDao.getList(param);
    }

    @Override
    public boolean addFaqCl(FaqClVo param) throws Exception {
        return faqClDao.addFaqCl(param);
    }

    @Override
    public boolean deleteFaqCl(FaqClVo param) throws Exception {
        return faqClDao.deleteFaqCl(param);
    }

    @Override
    public boolean updateFaqCl(FaqClVo param) throws Exception {
        return faqClDao.updateFaqCl(param);
    }

   // @Transactional
    @Override
    public boolean modifyFaqOrdUp(FaqVo param) {
        param.setNewOrd(param.getOrd() - 1);
        faqDao.modifyFaqOrdUp(param);
        faqDao.modifyFaqOrdByfaqid(param);
        return true;
    }

   // @Transactional
    @Override
    public boolean modifyFaqOrdDown(FaqVo param) {
        param.setNewOrd(param.getOrd() + 1);
        faqDao.modifyFaqOrdDown(param);
        faqDao.modifyFaqOrdByfaqid(param);
        return true;
    }

    @Transactional
    @Override
    public boolean modifyFaqClOrdUp(FaqClVo param) {
        param.setNewOrd(param.getOrd() - 1);
        faqClDao.modifyFaqClOrdUp(param);
        faqClDao.modifyFaqClOrdByfaqid(param);
        return true; 
    }

    @Transactional
    @Override
    public boolean modifyFaqClOrdDown(FaqClVo param) {
        param.setNewOrd(param.getOrd() + 1);
        faqClDao.modifyFaqClOrdDown(param);
        faqClDao.modifyFaqClOrdByfaqid(param);
        return true;
    }
    
    @Override
    public List<FaqClVo> getAllList(FaqClVo param) throws Exception{
    	return faqClDao.getAllList(param);
    }
    

}
