package com.kbrainc.plum.mng.cmmCntnts.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 유아교육신청 -> 교육신청관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.model
* - InfntAplyVo.java
* </pre>
**
@ClassName : InfntAplyVo
* @Description : 유아교육신청 -> 교육신청관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CmmCntntsVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private String trgtCd;
    private String evntCd;
    private String fldCd;
    private String fldNm;
    private String ordr;
    private String cn;
    private String colspan;
    private String chkYn;
    
    private Integer cntntsid;
    private Integer checkid;
    private Integer chklstid;

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
    

}
