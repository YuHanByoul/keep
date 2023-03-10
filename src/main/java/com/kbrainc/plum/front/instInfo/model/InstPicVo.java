package com.kbrainc.plum.front.instInfo.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.InstPicVo")
public class InstPicVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    private Integer userid;
    private String acnt;
    private String nm;
    private String moblphon;
    private String eml;
    private String addr;
    private String addrDtl;
    private Integer instid;
    private String instpicRoleCd;
    private String instpicRoleCdNm;
    private String sttsCd;
    
}