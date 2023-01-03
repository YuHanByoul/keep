package com.kbrainc.plum.mng.expertPoolMng.model;

import lombok.Data;

/**
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertCareerVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertCareerVo
 * @Description : 전문가 경력사항Vo 클래스
 * @date : 2023. 01. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertCareerVo {
    /** 경력 아이디 */
    private Integer exprtCareerId;
    /** 분야 이름 */
    private String fldNm;
    /** 활동 시작 일자 */
    private String actvtBgngDe;
    /** 활동 종료 일자 */
    private String actvtEndDe;
    /** 활동 여부 */
    private String actvtYn;
    /** 활동 시간 */
    private Integer actvtHr;
    /** 확인 기관 이름 */
    private String idntyInstNm;
    /** 활동 내용 */
    private String actvtCn;
    /** 확인 일자 */
    private String idntyDe;
    /** 확인서 파일아이디 */
    private Integer crtfFileid;
    /** 정관 파일아이디 */
    private Integer artclassFileid;
    /** 순서 */
    private Integer ordr;
}
