package com.kbrainc.plum.mng.faq.service;

import com.kbrainc.plum.mng.faq.model.FaqClDao;
import com.kbrainc.plum.mng.faq.model.FaqClVo;
import com.kbrainc.plum.mng.faq.model.FaqDao;
import com.kbrainc.plum.mng.faq.model.FaqVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public int insertFaq(FaqVo param) throws Exception {
        return faqDao.insertFaq(param);
    }

    @Override
    public int updateFaq(FaqVo param) throws Exception {
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

    @Transactional
    @Override
    public boolean updateFaqClOrdUp(FaqClVo param) {
        param.setNewOrd(param.getOrd() - 1);
        faqClDao.updateFaqClOrdUp(param);
        faqClDao.updateFaqClOrdByfaqid(param);
        return true;
    }

    @Transactional
    @Override
    public boolean updateFaqClOrdDown(FaqClVo param) {
        param.setNewOrd(param.getOrd() + 1);
        faqClDao.updateFaqClOrdDown(param);
        faqClDao.updateFaqClOrdByfaqid(param);
        return true;
    }

    @Override
    public List<FaqClVo> getAllList(FaqClVo param) throws Exception {
        return faqClDao.getAllList(param);
    }

    @Override
    @Transactional
    public boolean updateFaqOrdUp(FaqVo faqVo) {
        int ord = faqVo.getOrd();
        FaqVo updateFaq = faqDao.getUpdateFaq(faqVo);
        faqVo.setOrd(updateFaq.getOrd());
        faqDao.updateFaqOrdUp(faqVo);
        updateFaq.setOrd(ord);
        faqDao.updateFaqOrdDown(updateFaq);
        return true;
    }

    @Override
    @Transactional
    public boolean updateFaqOrdDown(FaqVo faqVo) {
        int ord = faqVo.getOrd();
        FaqVo updateFaq = faqDao.getUpdateFaq(faqVo);
        faqVo.setOrd(updateFaq.getOrd());
        faqDao.updateFaqOrdDown(faqVo);
        updateFaq.setOrd(ord);
        faqDao.updateFaqOrdUp(updateFaq);
        return true;
    }
}
