package com.kbrainc.plum.sample.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageParameter {
    private int pageNumber;
    private int rowPerPage;
    private String orderField;
    private String orderDirection;

    /**
     * 
     * Desc : Constructor of PageParameter.java class
     * @param param :
     */
    public PageParameter(JqGridRequest param) {
        this.pageNumber = param.getPage();
        this.rowPerPage = param.getRows();
        this.orderField = param.getSidx();
        this.orderDirection = param.getSord();
    }

}
