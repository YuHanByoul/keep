package com.kbrainc.plum.cmm.file.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileGrpVo {
    private int filegrpid;
    private String filegrpNm;
    private String mdfcnDt;
    private Integer mdfrid;
    private String regDt;
    private Integer rgtrid;
    private int bbsid;

    public FileGrpVo(int filegrpid) {
        this.filegrpid = filegrpid;
    }

    public FileGrpVo(int filegrpid, String filegrpName) {
        this.filegrpid = filegrpid;
        this.filegrpNm = filegrpName;
    }

}
