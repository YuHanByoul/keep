package com.kbrainc.plum.sample.model;

import lombok.Data;

@Data
public class JqGridRequest {
    private String callback;
    private int total;
    private int page;
    private int rows;
    private String sidx;
    private String sord;

}
