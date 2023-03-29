package com.kbrainc.plum.mng.map.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
public class MapVo extends ParentRequestVo {

	/** 로그인사용자정보 */
    private UserVo user;

    /** 검색.자원구분코드 */
    private String searchResrceSeCd ;

    /** 검색.지역코드*/
    private String searchCtprvnCd   ;

    /** 검색.자원 명 */
    private String searchResrceNm   ;

    /** 검색.사용여부 */
    private String searchUseYn      ;

    /** 자원 아이디 */
    private Integer resrceid;

    /** 자원 구분 코드*/
    private String resrceSeCd;

    /** 자원 구분 코드 명*/
    private String resrceSeCdNm;

    /** 자원 이름 */
    private String resrceNm;

    /** 기관 이름 */
    private String instNm;

    /** 전화 번호 */
    private String telno;

    /** 교육 대상 */
    private String eduTrgt;

    /** 교육 인원수 */
    private Integer eduNope;

    /** 교육 주제 */
    private String eduSbjct;

    /** 홈페이지 */
    private String hmpg;

    /** 주소 */
    private String addr;

    /** 주소 상세 */
    private String addrDtl;

    /** 시도 코드 */
    private String ctprvnCd;

    /** 시도명 */
    private String ctprvnNm;

    /** 사용 여부 */
    private String useYn;

    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    /** 등록자명 */
    private String rgtrNm;

    /** 결과 */
    private String result;


}
