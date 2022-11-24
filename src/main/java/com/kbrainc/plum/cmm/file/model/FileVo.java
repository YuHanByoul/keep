package com.kbrainc.plum.cmm.file.model;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVo {
	
	private UserVo user;
	
	private int fileid;
    private int filegrpid;
    private String fileIdntfcKey;
    private String filegrpNm;
    private String filePath;
    private String saveFileNm;
    private String orginlFileNm;
    private String fileExtsn;
    private long fileSize;
    private int ord;
    private String updtDt;
    private int updtuserid;
    private String regDt;
    private int reguserid;
    private int bbsid;
    
    /** 비로그인_파일다운로드_여부 */
    private String nloginDwnldPermYn;

    /**
     * .
     * Desc : Constructor of FileVo.java class
     * @param filegrpid :
     * @param filePath :
     * @param saveFileNm :
     * @param orginlFileNm :
     * @param fileExtsn :
     * @param fileSize :
     * @param ord :
     * @param reguserid :
     */
    public FileVo(int filegrpid, String filePath, String saveFileNm, String orginlFileNm, String fileExtsn,
            long fileSize, int ord, int reguserid) {
        super();
        this.filegrpid = filegrpid;
        this.filePath = filePath;
        this.saveFileNm = saveFileNm;
        this.orginlFileNm = orginlFileNm;
        this.fileExtsn = fileExtsn;
        this.fileSize = fileSize;
        this.ord = ord;
        this.reguserid = reguserid;
    }

    public FileVo(int filegrpid, String filePath) {
        super();
        this.filegrpid = filegrpid;
        this.filePath = filePath;
    }
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   


}
