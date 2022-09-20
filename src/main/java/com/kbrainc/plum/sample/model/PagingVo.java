package com.kbrainc.plum.sample.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 처리 효율을 높이기 위해 한번의 쿼리로 total count를 함께 구하고자 할 때 사용.
 * 
 * @author comnic
 *
 */
@Getter
@Setter
public class PagingVo {
    protected int totalCount;
    protected String orderingField;
    protected ORDER_DIRECTION orderingDirection;

    public enum ORDER_DIRECTION {
        ASC, DESC
    }
}
