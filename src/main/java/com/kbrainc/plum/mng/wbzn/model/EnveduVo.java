package com.kbrainc.plum.mng.wbzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
public class EnveduVo extends ParentRequestVo {
    
    private UserVo user;
    private FileVo file;
    
    /** 환경교육아이디 */
    private int enveduid;
    /** 분류_코드 */
    private String clsfCd;
    /** 환경교육_구분_코드 */
    private String enveduSeCd;
    /** 연도 */
    private String yr;
    /** 분기 */
    private String qu;
    /** 월 */
    private String mm;
    /** 제목 */
    private String ttl;
    /** URL */
    private String url;
    /** 썸네일_파일아이디 */
    private int thmbnFileid;
    /** 내용_STYLE */
    private String cnStyle;
    /** 내용 */
    private String cn; 
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    private String yrMm;
    
    private String nm;
    private String searchSeCd;
    private String searchYr;
    private String searchMm;
}
