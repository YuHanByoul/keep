package com.kbrainc.plum.cmm.file.model;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVo {
	
	private UserVo user;
	
	private int fileid;
    private int filegrpid;
    private String file_idntfc_key;
    private String filegrp_nm;
    private String file_path;
    private String save_file_nm;
    private String orginl_file_nm;
    private String file_extsn;
    private long file_size;
    private int ord;
    private String updt_dt;
    private int updtuserid;
    private String reg_dt;
    private int reguserid;
    private int bbsid;
    
    /** 비로그인_파일다운로드_여부 */
    private String nlogin_perm_yn;

    /**
     * .
     * Desc : Constructor of FileVo.java class
     * @param filegrpid :
     * @param file_path :
     * @param save_file_nm :
     * @param orginl_file_nm :
     * @param file_extsn :
     * @param file_size :
     * @param ord :
     * @param reguserid :
     */
    public FileVo(int filegrpid, String file_path, String save_file_nm, String orginl_file_nm, String file_extsn,
            long file_size, int ord, int reguserid) {
        super();
        this.filegrpid = filegrpid;
        this.file_path = file_path;
        this.save_file_nm = save_file_nm;
        this.orginl_file_nm = orginl_file_nm;
        this.file_extsn = file_extsn;
        this.file_size = file_size;
        this.ord = ord;
        this.reguserid = reguserid;
    }

    public FileVo(int filegrpid, String file_path) {
        super();
        this.filegrpid = filegrpid;
        this.file_path = file_path;
    }

}
