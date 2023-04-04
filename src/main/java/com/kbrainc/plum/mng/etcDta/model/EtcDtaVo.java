package com.kbrainc.plum.mng.etcDta.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기타자료 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.etcDta.model
 * - EtcDtaVo.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : EtcDtaVo
 * @Description : 기타자료 Vo 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class EtcDtaVo extends ParentRequestVo {
    private UserVo user;
    /** 자료아이디 */
    private Integer dtaid;
    /** 제목 */
    private String ttl;
    /** 내용 */
    private String cn;
    /** 국가_코드 */
    private String nationCd;
    /** 국가_코드명 */
    private String nationNm;
    /** 홈페이지 */
    private String hmpg;
    /** 위치 */
    private String pstn;
    /** 연락처 */
    private String cntct;
    /** 개방시간 */
    private String opnhr;
    /** 참가비 */
    private String etrfee;
    /** 체험_프로그램 */
    private String exprnPrgrm;
    /** 저작권_코드 */
    private String cpyrhtCd;
    /** 등록일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date regDt;
    /** 등록일 */
    private String regD;
    /** 등록자 */
    private Integer rgtrid;
    /** 등록자명 */
    private String rgtrNm;
    /** 조회수 */
    private Integer hits;
    /** 첨부_파일그룹아이디 */
    private Integer atchFilegrpid;
    
    private List<FileVo> atchFileList;
    private Integer[] deleteDtaids;
    
    /** 검색 관련 */
    private String searchKeyword;
    private String searchType;    
    private String searchNationCd;
    private String searchBgngDt;
    private String searchEndDt;
}