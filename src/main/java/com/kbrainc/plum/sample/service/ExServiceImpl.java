package com.kbrainc.plum.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.sample.model.PageParameter;
import com.kbrainc.plum.sample.model.TestTableDao;
import com.kbrainc.plum.sample.model.TestTableVO;

@Service
public class ExServiceImpl extends PlumAbstractServiceImpl implements ExService {

    @Autowired
    TestTableDao testTableDao;

    @Override
    public List<TestTableVO> getList() {
        PageParameter params = new PageParameter();
        return testTableDao.readList(params);
    }

    @Override
    public List<TestTableVO> getJqData(PageParameter params) {

        if (params.getOrderField() == null || "".equals(params.getOrderField())) {
            params.setOrderField("createdAt");
        }

        return testTableDao.readList(params);

    }

}
