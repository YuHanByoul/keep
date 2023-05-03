package com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 마이페이지 > 사회환경교육기관 지정 관리 > 신청내역 VO 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model
* - SeeEnvEduInstHistVo.java
* </pre> 
*
* @ClassName : SeeEnvEduInstHistVo
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.SeeEnvEduInstHistVo")
public class SeeEnvEduInstHistVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 검색 조건 */
    private String searchRcptNo;
    private String searchSttsCd;
    
    /** 신청아이디 */
    private Integer aplyid;
    
    /** 시도_코드 */
    private String ctprvnCd;
    
    /** 시도_코드명 */
    private String ctprvnNm;
    
    /** 접수번호 */
    private String rcptno;
    
    /** 상태_코드 */
    private String sttsCd;
    
    /** 상태_코드명 */
    private String sttsNm;
    
    /** 보안상태코드 */
    private String ansSttsCd;
    
    /** 보안상태코드명 */
    private String ansSttsNm;
    
    /** 보완아이디 */
    private Integer splmntid;
    
    /** 신청자아이디 */
    private Integer userid;
    
    /** 신청자명 */
    private String aplcntNm;
    
    /** 신청기관아이디 */
    private Integer instid;
    
    /** 신청기관명 */
    private String instNm;
    
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
    
    /** 등록자 이름 */
    private String rgtrNm;
}
