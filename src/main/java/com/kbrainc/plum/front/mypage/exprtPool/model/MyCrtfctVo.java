package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;

/**
 * 자격증 vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyCrftctVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyCrftctVo
 * @Description : 자격증 vo 클래스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MyCrtfctVo {
    /** 수정 요청 아이디 */
    private Integer mdfcnDmndId;
    /** 자격증 아이디 */
    private Integer exprtCrtfctId;
    /** 자격증 이름 */
    private String crtfctNm;
    /** 취득 기관 */
    private String acqsInst;
    /** 취득 번호 */
    private String acqsNo;
    /** 취득 등급 */
    private String acqsGrd;
    /** 취득 일자 */
    private String acqsDe;
    /** 자격증 파일아이디 */
    private Integer crtfctFileid;
    /** 순서 */
    private Integer ordr;
    /**자격증 파일정보 */
    private FileVo crtfctFile;
}
