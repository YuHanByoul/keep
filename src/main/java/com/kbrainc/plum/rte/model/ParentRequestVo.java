package com.kbrainc.plum.rte.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentRequestVo {
    protected int totalCount;
    protected int totalPage;
    protected int rowNumber;
    
    public static final ORDER_DIRECTION ORDER_DIRECTION_ASC = ORDER_DIRECTION.asc;
    public static final ORDER_DIRECTION ORDER_DIRECTION_DESC = ORDER_DIRECTION.desc;
    
    public int pageNumber;
    public int rowPerPage;
    public int pageOffset;
    public String orderField;
    public ORDER_DIRECTION orderDirection;
    
    protected String searchType;
    protected String searchKeyword; 
    
    public enum ORDER_DIRECTION {
        asc, desc
    }
    
    public ParentRequestVo() {
        this.chkParams();
    }
    
    public void setRowPerPage(int rowPerPage) {
        this.pageOffset = (this.pageNumber - 1) * this.rowPerPage;
        this.rowPerPage = rowPerPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageOffset = (this.pageNumber - 1) * this.rowPerPage;
    }

    /**
     * .
     *
     * @Title       : chkParams 
     * @Description : TODO
     */
    public void chkParams() {
        if (this.pageNumber == 0) {
            this.pageNumber = 1;
        }
        
        if (this.rowPerPage == 0) {
            this.rowPerPage = 10;
        }

        if (this.orderField == null || "".equals(this.orderField)) {
            this.orderField = "UPDT_DT";
        }
        
        if (this.orderDirection == null) {
            this.orderDirection = ORDER_DIRECTION.desc;
        }
        
    }
}
