package com.kbrainc.plum.front.clclnMng.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.banner.model.BannerVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.clclnMng.clclnDsctn.model
* - ClclnDsctnVo.java
* </pre>
**
@ClassName : ClclnDsctnVo
* @Description : 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리  VO 클래스
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.ClclnDsctnVo")
public class ClclnDsctnVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    private List<ClclnDsctnVo> clclnDsctnVoList;
    
    /** 내역아이디 */
    private Integer dsctnid;
    /** 분야_코드 */
    private String fldCd;
    /** 분야_코드명 */
    private String fldNm;
    /** 공모아이디 */
    private String pcntstid;
    /** 공모명 */
    private String pcntstNm;
    /** 진행상태_설명 */
    private String excclcSttsDesc;
    /** 정산내역제출기간_설명 */
    private String excclcReportDateDesc;
    /** 선정사업수 */
    private String slctnCnt;
    /** 제출상태_설명 */
    private String excclcCntDesc;
    /** 구분_코드 */
    private String seCd;
    /** 구분_코드명 */
    private String seNm;
    /** 이용_구분코드 */
    private String utztnSecd;
    /** 이용_구분코드명 */
    private String utztnSenm;
    /** 입금_구분코드 */
    private String dpstSecd;
    /** 일자 */
    private String de;
    /** 금액 */
    private String amt;
    /** 집행처 */
    private String excutTrgt;
    /** 상위 항목_코드 */
    private String artclUpperCd;
    /** 상위 항목_코드명 */
    private String artclUpperNm;
    /** 항목_코드 */
    private String artclCd;
    /** 항목_이름 */
    private String artclNm;
    /** 사용_목적 */
    private String usePrps;
    /** 내역 */
    private String dsctn;
    /** 증빙_종류_코드 */
    private String prufKndCd;
    /** 기관아이디 */
    private String instid;
    /** 사용자아이디 */
    private String userid;
    /** 신청아이디 */
    private String aplyid;  
    /** 접수번호 */
    private String rcptno;
    /** 제출_상태_코드 */
    private String sbmsnSttsCd;              
    /** 제출_상태_코드명 */
    private String sbmsnSttsNm;              
    /** 기관명 */
    private String instNm;              
    /** 신청자명 */
    private String aplcntNm;              
    /** 프로그램명 */
    private String prgrmNm;              
    /** 총지출_금액 */
    private double totAmt;              
    /** 이자_금액 */
    private double intAmt;              
    /** 캐쉬백_금액 */
    private double cashbAmt;              
    /** 기타_입금액 */
    private double etcDpstAmt;              
    /** 잔액 */
    private double blncAmt;   
    /** 첨부파일유무 */
    private String atchFileYn;              
    /** 예산액 */
    private double bgtAmt;              
    /** 지출액 */
    private double expndAmt;              
    /** 집행잔액 */
    private double implBlncAmt;              
    /** 이자+캐쉬백 금액 */
    private double intCashbAmt;              
    /** 집행잔액+이자발생 금액 */
    private double implBlncIntAmt;              
    /** 재출일시 */
    private String sbmsnDt;
    /** 집행건수 */
    private double implCnt;      
    /** 집행비율 */
    private double implRt;      
    /** 비목코드 */
    private String expitmCd;      
    /** 비목코드명 */
    private String expitmNm;      
    /** 비목_상위코드 */
    private String upprCd;      
    /** 비목_상위코드명 */
    private String upprNm;      
    /** 예산액_소계 */
    private double bgtAmtRp;      
    /** 지출액_소계 */
    private double expndAmtRp;      
    /** 집행건수_소계 */
    private double implCntRp;      
    /** 집행잔액_소계 */
    private double implBlncAmtRp;      
    /** 집행비율_소계 */
    private double implRtRp;      
    /** 예산액_합계 */
    private double bgtAmtSum;      
    /** 지출액_합계 */
    private double expndAmtSum;      
    /** 집행건수_합계 */
    private double implCntSum;      
    /** 집행잔액_합계 */
    private double implBlncAmtSum;      
    /** 집행비율_합계 */
    private double implRtSum;      
    /** 첨부파일 */
    private String atchFileid;
    /** 제출상태_완료ID */
    private String completeAplyId;
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
    
    /** 폰트 칼라 */
    private String color;
    private String minusSum;
    private String cnt;
    private String nowAmt;
    private String clclnRate;
    private String groupNum;
    private String rowspan;
    
    /** 삭제 집행내역아이디 */
    private List<String> dsctnids;
    
    /** 검색 관련 */
    private String searchType;
    private String searchKeyword;
    private String searchFldCd;
    private String searchPcntstNm;
    private String searchStartExcclcReportDt;
    private String searchEndExcclcReportDt;
    private String searchSttsCd;
    private String searchSbmsnSttsCd;
    private String searchAttchYn;
    
    /** 첨부파일 관련*/
    private Integer atchFilegrpid;
    private String atchFileIdntfcKey;
    private String atchOrginlFileNm;    
    
}
