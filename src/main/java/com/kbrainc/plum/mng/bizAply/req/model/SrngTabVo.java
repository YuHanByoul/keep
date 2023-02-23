/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.model;

import lombok.Data;

/**
* 심사관리 탭 VO 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.model
* - SrngTabVo.java
* </pre> 
*
* @ClassName : SrngTabVo
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 22.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class SrngTabVo {

    /** 신청아이디 */
    private Integer aplyid;
    
    /** 심사제출아이디 */
    private Integer sbmsnid;
    
    /** 심사위원 아이디 */
    private Integer jdgsid;
    
    /** 심사표아이디 */
    private Integer formid;
    
    /** 심사차수 */
    private Integer cycl;
    
    /** 심사위원 성명 */
    private String nm;
    
    /** 점수 */
    private String scr;
    
    /** 총점 */
    private String totScr;
    
    /** 평균점수 */
    private String avgScr;
    
    /** 1차 심사 등급 */
    private String firstGrd;
    
    /** 1차 심사 순위 */
    private String firstRkng;

    /** 2차 심사 등급 */
    private String scndGrd;
    
    /** 2차 심사 순위 */
    private String scndRkng;
    
    /** 선정상태명 */
    private String slctnSttsNm;
    
    /** 2차심사 대상 여부 */
    private String scndSrngTrgtYn;
    
    /** 심사 죵료 여부 */
    private String srngEndYn;
    
    /** 심사의견 */
    private String srngOpnn;
    
    /** 의견1 */
    private String opnn1;
    
    /** 의견2 */
    private String opnn2;
    
    /** 의견3 */
    private String opnn3;
    
    /** 의견4 */
    private String opnn4;
    
    /** 심사제출일자 */
    private String sbmsnDt;
    
    /** 문항 구분 코드명 */
    private String qitemSeNm;
    
    /** 문항 */
    private String qitem;
    
    /** 문항 구분 열 병합 */
    private String colspan;
    
    /** 문항 그룹별 순번 */
    private String rowNum;
    
    /** 문항 평균 점수 */
    private String sumAvg;
    
    /** 총점 평균 */
    private String totSumAvg;
}
