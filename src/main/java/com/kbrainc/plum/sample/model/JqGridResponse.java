package com.kbrainc.plum.sample.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class JqGridResponse {
    private String callback;
    private int records;
    private int page;
    private int total;
    private List<TestTableVO> rows;

    /**
     * 
     * Desc : Constructor of JqGridResponse.java class
     * @param callback :
     * @param records :
     * @param page :
     * @param total :
     * @param rows :
     */
    public JqGridResponse(String callback, int records, int page, int total, List<TestTableVO> rows) {
        super();
        this.callback = callback;
        this.records = records;
        this.page = page;
        this.total = total;
        this.rows = rows;
    }

}
