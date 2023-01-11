package com.kbrainc.plum.mng.envtcherTrnngInst.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 환경교육사 양성기관 현황 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.model
* - EnvtcherTrnngInstVo.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstVo
* @Description : 환경교육사 양성기관 현황 Vo 클래스
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class EnvtcherTrnngInstVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    
    /** 순차아이디 */
    private int ordrid;
    /** 기관아이디 */
    private int instid;
    /** 기관명 */
    private String instNm;
    /** 등급_코드 */
    private String grdCd;
    /** 등급_코드명 */
    private String grdCdNm;
    /** 지정_일자 */
    private String dsgnDe;
    /** 기관_유형_코드 */
    private String instTypeCd;
    /** 기관_유형_코드 */
    private String instTypeCdNm;
    /** 홈페이지 */
    private String hmpg;
    /** 전화번호 */
    private String telno;
    /** 팩스번호 */
    private String fxno;
    /** 우편번호 */
    private String zip;
    /** 주소 */
    private String addr;
    /** 주소_상세 */
    private String addrDtl;
    /** 시도 */
    private String sidoNm;
    /** 시군구*/
    private String signguNm;
    /** 시군구_코드 */
    private String signguCd;
    /** 설명 */
    private String expln;
    /** 썸네일_파일아이디 */
    private int thmbnFileid;
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    
    /** 지역 코드(공통코드) */
    private int ctprvnCd;
    private String ctprvnNm;
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 검색 영역*/
    private String searchSido;      //시도
    private String searchInstNm;    //기관명
    private String searchGrdCd;     //양성기관 등급
    private String startDt;         //지정일-검색시작일
    private String endDt;           //지정일-검색종료일

}
