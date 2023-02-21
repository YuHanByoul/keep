package com.kbrainc.plum.front.cntst.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import org.apache.ibatis.type.Alias;

import java.sql.Date;
import java.util.List;

@Data
@Alias("front.CntstVo")
public class CntstVo extends ParentRequestVo {

    private UserVo user;

    /** 공모전아이디*/
    private Integer cntstid;
    /** 제목*/
    private String ttl;
    /** 썸네일_파일아이디*/
    private Integer thmbnFileid;
    /** 첨부_파일그룹아이디*/
    private Integer atchFilegrpid;
    /** 신청_시작_일자*/
    private String aplyBgngDt;
    /** 신청_종료_일자*/
    private String aplyEndDt;
    /** 발표_일자*/
    private String prsntnDt;
    /** 중복_가능_여부*/
    private String dpcnPsbltyYn;
    private String dpcnUserid;
    /** 분류_코드*/
    private String clsfCd;
    /** 조회수*/
    private String hits;
    /** 안내*/
    private String gdnc;
    /** 약관*/
    private String trms;
    /** 내용*/
    private String cn;
    /** 상태 */
    private String stts;
    /** 수정_일시*/
    private Date mdfcnDt;
    /** 등록_일시*/
    private Date regDt;
    
    /** 공모 분야*/
    private String fldCd;
    private String fldMapngNm;
    
    private String aplyno;
    
    /** 이전글 다음글*/
    private String nextCntstid;
    private String nextCntstTtl;
    private String beforeCntstid;
    private String beforeCntstTtl;
    
    /** 첨부파일 관련 */
    private List<FileVo> fileList;
    private String fileIdntfcKey;

    /** 검색 영역 */
    private String searchTtl;
    private String searchStts;
    private String searchType;
}
