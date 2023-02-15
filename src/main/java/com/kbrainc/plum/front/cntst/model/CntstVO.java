package com.kbrainc.plum.front.cntst.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.front.cntnts.model.CntntsVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 공모전 VO
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.model
 * - CntstVO.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstVO
 * @Description : 공모전 VO
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CntstVo")
public class CntstVO extends ParentRequestVo {

    private UserVo user;

    /** 공모전아이디*/
    private Integer cntstid;

    /** 제목*/
    @NotEmpty
    @Size(max = 200, message = "제목은 200자를 넘을 수 없습니다.")
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

    /** 분류_코드*/
    private String clsfCd;

    /** 안내*/
    private String gdnc;

    /** 약관*/
    private String trms;

    /** 내용*/
    private String cn;

    /** 수정_일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;

    /** 수정자아이디*/
    private Integer mdfrid;

    /** 등록_일시*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;

    /** 등록자아이디*/
    private Integer rgtrid;



    /** 등록자이름*/
    private String rgtrNm;

    /** 등록자ID*/
    private String rgtrAcnt;

    /** 공모전접수상태*/
    private String cntstSttsCd;


    /** 검색 영역 */
    private String searchCntstClsfCd;

    private String searchCntstSttsCd;
}
