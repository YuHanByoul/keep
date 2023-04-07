package com.kbrainc.plum.mng.expertPoolMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

import java.util.Date;

/**
 * 전문가 후기이력 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.model
 * - ExpertReviewHistoryVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertReviewHistoryVo
 * @Description : 전문가 후기이력 Vo 클래스
 * @date : 2023. 01. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class ExpertReviewHistoryVo  extends ParentRequestVo {
    /** 요청 일련번호 */
    private Integer dmndid;
    /** 전문가 일련번호 */
    private Integer exprtid;
    /** 사용자 일련번호 */
    private Integer userid;
    /** 사용자 이름 */
    private String nm;
    /** 사용자 아이디 */
    private String acnt;
    /** 제목 */
    private String ttl;
    /** 점수 */
    private Integer scr;
    /** 우편번호 */
    private String zip;
    /** 주소 */
    private String addr;
    /** 주소 상세 */
    private String addrDtl;
    /** 기타 의견 */
    private String etcOpnn;
    /** 교육 내용 */
    private String eduCn;
    /** 교육 인원수 */
    private Integer eduNope;
    /** 등록일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    /** 수정일 */
    private Date mdfcnDt;

}
