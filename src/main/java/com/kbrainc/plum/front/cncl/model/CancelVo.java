/**
 * 
 */
package com.kbrainc.plum.front.cncl.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 사업포기신청 VO 클래스
*
* <pre>
* com.kbrainc.plum.front.cncl.model
* - CancelVo.java
* </pre> 
*
* @ClassName : CancelVo
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class CancelVo extends ParentRequestVo {

    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 검색 조건 */
    private String searchFldCd;
    private String searchPcntstNm;
    private String searchPrgrmNm;
    private String searchSttsCd;
    
    /** 사업포기신청 아이디 */
    private Integer dmndid;
    
    /** 사업분야 */
    private String fldNm;
    
    /** 공모명 */
    private String pcntstNm;
    
    /** 프로그램명 */
    private String prgrmNm;
    
    /** 공모신청아이디 */
    private String aplyid;
    
    /** 사업분야 */
    private String fldCd;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 신청자아이디 */
    private Integer userid;
    
    /** 기관명 */
    private String instNm;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 은행코드 */
    private String bankCd;
    
    /** 계좌번호 */
    private String bacntno;
    
    /** 예금주명 */
    private String dpstrNm;
    
    /** 반환시작일 */
    private String rturnBgngDe;
    
    /** 반환종료일 */
    private String rturnEndDe;
    
    /** 사업포기신청상태 */
    private String dmndSttsCd;
    
    /** 사업포기신청상태명 */
    private String dmndSttsNm;
    
    /** 취소일시 */
    private String cnclDt;
    
    /** 내용 */
    private String cn;
    
    /** 공모신청서 상태값 */
    private String aplySttsCd;
    
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
