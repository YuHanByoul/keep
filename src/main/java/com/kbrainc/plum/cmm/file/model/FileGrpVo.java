package com.kbrainc.plum.cmm.file.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileGrpVo {
    private int filegrpid;
    private String filegrp_nm;
    private String updt_dt;
    private int updtuserid;
    private String reg_dt;
    private int reguserid;
    private int bbsid;

    public FileGrpVo(int filegrpid) {
        this.filegrpid = filegrpid;
    }

    public FileGrpVo(int filegrpid, String filegrpName) {
        this.filegrpid = filegrpid;
        this.filegrp_nm = filegrpName;
    }

}
