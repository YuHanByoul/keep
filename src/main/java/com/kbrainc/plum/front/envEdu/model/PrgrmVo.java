/**
 * 
 */
package com.kbrainc.plum.front.envEdu.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;


/**
* 프로그램 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.envEdu.model
* - PrgrmVo.java
* </pre>
*
* @ClassName : PrgrmVo
* @Description : 프로그램 Vo 클래스
* @author : JD
* @date : 2023. 2. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.PrgrmVo")
public class PrgrmVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 프로그램아이디 */
    private Integer prgrmid;
    /** 프로그램명 */
    private String prgrmNm;
    /** 지역명 */
    private String ctprvnNm;
    /** 기업아이디 */
    private String instid;
    /** 기업명 */
    private String instNm;
    /** 교육대상 */
    private String eduTarget;
    /** 전체_월 */
    private String wholAge;
    /** 참가비 */
    private String etrfee;
    /** 지정_번호 */
    private String dsgnNo;
    /** 지정_일자 */
    private String dsgnDe;
    /** 대표자명 */
    private String rprsvNm;
    /** 기관_주소*/
    private String instAddr;
    /** 기관_상세주소*/
    private String instDtlAddr;
    /** 기관_홈페이지*/
    private String instHmpg;
    
    /** 숙박_밤(박) */
    private String styNight;
    /** 숙박_낮(일) */
    private String styDaytm;
    /** 숙박_여부 */
    private String styYn;
    /** */
    private String cdNm;
    /** 운영일정 */
    private String targetMm;
    /** 교육주제 */
    private String eduSbjctCdNm;
    /** 교육_장소 */
    private String eduPlc;
    /** 교육_인원수 */
    private String eduNope;
    /** 교육_시간 */
    private String eduTime;
    /** 유료_여부 */
    private String pchrgYn;
    /** 담당자명 */
    private String aplcntNm;
    /** 담당자_연락처 */
    private String aplcntMoblphon;
    
    /** 교육_목적*/
    private String eduPrps;
    /** 환경교육_적절성 */
    private String appro;
    /** 프로그램_우수성 */
    private String dstnctn;
    /** 교육_내용 */
    private String eduCn;
    
    /** 등록일 */
    private String regDt;

    /** 첨부파일 관련 */
    private String fileid;
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
    
    /** 필터 */
    private String searchPrgrmNm;
    private String searchInstNm;
    private String searchRgnCd;
    private String searchEduTarget;
}
