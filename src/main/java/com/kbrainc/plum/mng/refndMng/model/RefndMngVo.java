package com.kbrainc.plum.mng.refndMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
* 시설 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.model
* - RefndMngVo.java
* </pre>
*
* @ClassName : RefndMngVo
* @Description : 시설 VO 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Data
public class RefndMngVo extends ParentRequestVo {
    
    public static String fcltVal;

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자 아이읻 */
    private String acnt;

    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자 아이디 */
    private int mdfrid;
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    /** MODE */
    private String mode;
    
    /** 첨부 파일 그룹아이디 */
    private String filegrpid;
    
    /** 시설 */
    private Integer fcltid;
    
    /** 시설 */
    @NotEmpty(message = "시설명을 입력해주십시오.")
    private String fcltNm;
    
    /** 시설번호 */
    public String fcltNo;
    
    /** 기관아이디 */
    @NotEmpty(message = "운영기관을 선택해주십시오.")
    private int instid;
    
    /** 기관아이디 */
    private String instNm;
    
    /** 우편번호 */
    private String zip;
    
    /** 주소 */
    @NotEmpty(message = "주소를 입력해주십시오.")
    private String addr;
    
    /** 주소 상세 */
    private String addrDtl;
    
    /** 지역 코드 */
    private String rgnCd;
    
    /** 신청 방법 코드 */
    @NotEmpty(message = "신청방법을 선택해주십시오.")
    private String aplyMthdCd;
    
    /** 외부 URL */
    private String extnlUrl;
    
    /** 은행코드 */
    private String bankCd;
    
    /** 계좌번호 */
    private String bacntNo;
    
    /** 예금주 이름 */
    private String dpstrNm;
    
    /** 대표 이미지 파일아이디 */
    private Integer rprsImgFileid;
    
    /** 상세 이미지 파일그룹아이디 */
    private Integer dtlImgFilegrpid;
    
    /** 안내 파일아이디 */
    private Integer gdncFileid;
    
    /** 사용 여부 */
    @Pattern(regexp="[YN]")
    private String useYn;
    
    /** 상세 내용 */
    private String dtlCn;
    
    /** 지역명 */
    private String rgnNm;
    
    /** 예약신청방법 */
    private String aplyMthdNm;
    
    /** 코드명 */
    private String cdNm;
    
    /** 보유 공간 수 */
    private Integer ownCnt;
    
    /** 이름 */
    private String nm;
    
    /** 중복 시설명 수 */
    public static Integer dupCnt;
    
    /** 검색 관련*/
    private String searchSeCd;
    private String searchKeyword;
    private String searchRgnCd;
    private String searchUseYn;
    
}
