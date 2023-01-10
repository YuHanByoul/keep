package com.kbrainc.plum.mng.srng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 심사 양식 VO클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngFormVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngFormVO
 * @Description : 심사 양식 VO클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class SrngFormVO extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 양식아이디 */
    private int formid;

    /** 양식_이름 */
    @NotEmpty(message = "양식이름을 입력해주세요.")
    private String formNm;

    /** 양식_설명 */
    private String formExpln;

    /** 운영_형태_코드 */
    @NotEmpty(message = "운영형태코드를 선택해주세요.")
    private String operFrmCd;

    /** 사용_여부 */
    private String useYn;

    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date mdfcnDt;

    /** 수정자아이디 */
    private int mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;

    /** 등록자아이디 */
    private int rgtrid;
}
