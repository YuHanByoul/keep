package com.kbrainc.plum.sample.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface TestTableDao {
    public List<TestTableVO> readList(PageParameter p);
}
