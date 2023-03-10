package com.kbrainc.plum.front.delvry.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 교부신청Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.delvry.model
 * - DelvryAplyVo.java
 * </pre> 
 *
 * @ClassName : DelvryAplyVo
 * @Description : 교부신청Vo 클래스 
 * @author : KBRAINC
 * @date : 2023. 02. 13.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.DelvryAplyVo")
public class DelvryAplyVo extends ParentRequestVo {
    
    private String searchFldCd;
    private String searchPcntstNm;
    private String searchPrgrmNm;
    private String searchDelvrySttsCd;
    
    
    /** 로그인 사용자 정보 */
    private UserVo user;
    
    /** 신청 아이디 */
    private int delvryAplyid;
    
    /** 공모 아이디 */
    private int pcntstid;
    
    /** 차수 */
    private int cycl;
    
    /** 접수번호 */
    private String rcptno;
    
    /** 교부상태 코드 */
    private String delvrySttsCd;
    
    /** 교부상태 코드명 */
    private String delvrySttsNm;
    
    /** 기관명 */
    private String instNm;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청자 아이디 */
    private String acnt;
    
    /** 사업분야 코드 */
    private String fldCd;
    
    /** 사업분야 코드명 */
    private String fldNm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 교부 신청기간 */
    private String delvryAplyDt;
    
    /** 교부 확정발표기간 */
    private String delvryCfmtnPrsntnDt;
    
    /** 사업기간 */
    private String bsnsDe;
    
    /** 버튼타입 */
    private String crudBtn;
    
    /** 보완요청아이디 */
    private Integer splmntid;
    
    /** 신청자아이디 */
    private Integer userid;
    
    /** 신청기관아이디 */
    private Integer instid;
    
    /** 공모신청아이디 */
    private Integer aplyid;
    
    /** 교부아이디 */
    private Integer delvryid;
    
    /** 신청번호 */
    private String aplyno;
    
    /** 동아리명 */
    private String clubNm;
    
    /** 신청금액 */
    private String totAmt;
    
    /** 은행코드 */
    private String bankCd;
    
    /** 은행코드명 */
    private String bankCdNm;
    
    /** 계좌번호 */
    private String bacntno;
    
    /** 예금주명 */
    private String dpstrNm;
    
    /** 업데이트 교부신청 아이디 목록 */
    private String[] updateDelvryAplyids;
    
    /** 교부 신청일 */
    private String regDtStr;
    
    /** 첨부파일 아이디 */
    private Integer atchFilegrpid;
    
    /** 산출내역 목록 */
    private List<DelvryAplyComputVo> computList;
    
    /** 첨부파일 그룹아이디 목록 */
    private String[] atchFilegrpids;
    
    /** 산출내역 json 데이터 */
    private String jsonString;
    
    /** 사업 시작 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bsnsBgngDe;
    
    /** 사업 시작 일시 정보 */
    public Date getBsnsBgngDe() {
        return bsnsBgngDe != null ? (Date) bsnsBgngDe.clone() : null;
    }
    
    public void setBsnsBgngDe(Date bsnsBgngDe) {
        this.bsnsBgngDe = bsnsBgngDe != null ? (Date) bsnsBgngDe.clone() : null;
    }
    
    /** 사업 종료 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date bsnsEndDe;
    
    /** 사업 종료 일시 정보 */
    public Date getBsnsEndDe() {
        return bsnsEndDe != null ? (Date) bsnsEndDe.clone() : null;
    }
    
    public void setBsnsEndDe(Date bsnsEndDe) {
        this.bsnsEndDe = bsnsEndDe != null ? (Date) bsnsEndDe.clone() : null;
    }
        
    /** 수정일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;
    
    /** 수정자 아이디 */
    private Integer mdfrid;
    
    /** 등록일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자 아이디 */
    private Integer rgtrid;
    
}