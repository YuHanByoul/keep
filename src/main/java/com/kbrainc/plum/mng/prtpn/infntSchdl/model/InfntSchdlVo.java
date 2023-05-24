package com.kbrainc.plum.mng.prtpn.infntSchdl.model;

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
* 유아교육프로그램 -> 교육프로그램관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.model
* - InfntPrgrmVo.java
* </pre>
**
@ClassName : InfntPrgrmVo
* @Description : 유아교육프로그램 -> 교육프로그램관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class InfntSchdlVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private List<InfntSchdlVo> infntPrgrmVoList;
    
    /** 프로그램아이디 */
    private Integer prgrmId;
    /** 교육관아이디 */
    private Integer clssrmId;    
    /** 프로그램_이름 */
    private String prgrmNm;
    /** 교육관_이름 */
    private String clssrmNm;
    /** 접수_방법_코드 */
    private String rcptMthdCd;
    /** 접수_방법_코드명 */
    private String rcptMthdNm;
    
    /** 교육_일자 */
    private String de;
    
    /** 프로그램아이디(조회용) */
    private String prgrmIds;    

    /** 교육_일정_관리_BORDER_COLOR */
    private String borderColor;
    /** 교육_일정_관리_BACKGROUND_COLOR */
    private String backgroundColor;
    /** 교육_일정_관리_TEXT_COLOR */
    private String textColor;
    
    /** 년월 */
    private String ym;    
    /** 교육_일정_명 */
    private String schdlNm;
    /** 프로그램_일정아이디 */
    private String prgrmSchdlid;
    
    
    /** 교육일자 설정 */
    @NotEmpty(message = "교육일자 설정을 선택해주십시오.")
    private String [] deSttIds;
    /** 교육프로그램 설정 */
    @NotEmpty(message = "교육프로그램 설정을 선택해주십시오.")
    private String [] prgrmSttIds;

    private String eduTypeCd;
    
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
    
    /** 작성자 */
    private String nm;
    
    /** 검색 관련 */
    private String searchClssrmId;
    private String searchSchdlId;
    
}
