package com.kbrainc.plum.sample.service;

import java.util.List;

import com.kbrainc.plum.sample.model.PageParameter;
import com.kbrainc.plum.sample.model.TestTableVO;

public interface ExService {
    public List<TestTableVO> getList();

    public List<TestTableVO> getJqData(PageParameter params);
}
