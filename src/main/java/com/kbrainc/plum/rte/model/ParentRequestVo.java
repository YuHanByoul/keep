package com.kbrainc.plum.rte.model;

import com.kbrainc.plum.rte.util.StringUtil;

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
    
    /** Mybatis bind변수로 searchInstValue 미사용시 에러 방지를 위해 선언. 기관 검색시 사용하는 변수. */
    protected String searchInstValue;
    /** Mybatis bind변수로 searchSiteValue 미사용시 에러 방지를 위해 선언. 사이트 검색시 사용하는 변수. */
    protected String searchSiteValue;
    /** Mybatis bind변수로 searchSiteSysSeCd 미사용시 에러 방지를 위해 선언. 사이트 검색시 사용하는 변수(시스템_구분_코드). */
    protected String searchSiteSysSeCd;
    
    public enum ORDER_DIRECTION {
        asc, desc
    }
    
    public ParentRequestVo() {
        this.chkParams();
    }
    
    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
        this.pageOffset = (this.pageNumber - 1) * this.rowPerPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageOffset = (this.pageNumber - 1) * this.rowPerPage;
    }
    
    public void setOrderField(String orderField) {
        if (StringUtil.isSqlInjectionSafe(orderField)) {
            this.orderField = orderField;
        }
    }

    /**
     * .
     * @throws Exception 
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
            this.orderField = "MDFCN_DT";
        }
        
        if (this.orderDirection == null) {
            this.orderDirection = ORDER_DIRECTION.desc;
        }
        
    }
}
