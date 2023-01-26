package com.kbrainc.plum.mng.expertPoolMng.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;

/**
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertCrtfctVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertCrtfctVo
 * @Description : 전문가 자격증사항Vo 클래스
 * @date : 2023. 01. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertCrtfctVo {
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
