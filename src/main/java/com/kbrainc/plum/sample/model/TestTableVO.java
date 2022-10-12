package com.kbrainc.plum.sample.model;

import lombok.Data;

@Data
public class TestTableVO extends PagingVo {
    private int idx;
    private String title;
    private String content;
    private int regId;
    private String updateAt;
    private String createdAt;
}
