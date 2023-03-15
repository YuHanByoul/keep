package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;

/**
 * 경력사항 vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyCareerVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyCareerVo
 * @Description : 경력사항 vo 클래스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MyCareerVo {
    /** 수정 요청 아이디 */
    private Integer mdfcnDmndId;
    /** 경력 아이디 */
    private Integer exprtCareerId;
    /** 분야 이름 */
    private String fldNm;
    /** 활동 시작 일자 */
    private String actvtBgngDe;
    /** 활동 종료 일자 */
    private String actvtEndDe;
    /** 활동 기간 */
    private String actvtDe;
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

    private FileVo crtfFile;

    private FileVo artClassFile;
}
