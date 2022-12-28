package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.Date;

/**
 * 심사위원 그룹 전문가 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.model
 * - JdgGrpExpertVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpExpertVo
 * @Description : 심사위원 그룹 전문가 Vo 클래스
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class JdgGrpExpertVo extends ParentRequestVo {
    /**
     * 로그인 사용자 정보
     */
    private UserVo user;

    /**
     * 아이디
     */
    private String acnt;

    /**
     * 이름
     */
    private String nm;

    /**
     * 이메일
     */
    private String eml;

    /**
     * 휴대폰 번호
     */
    private String moblphon;

    /**
     * 자격증 이름
     */
    private String crtfctNm;

    /**
     * 전문분야 코드
     */
    private String sbjctCd;

    /**
     * 활동가능지역 코드
     */
    private String rgnCd;

    /**
     * 전문분야 코드 이름
     */
    private String sbjctCdNm;

    /**
     * 활동가능지역 코드 이름
     */
    private String rgnCdNm;

    /**
     * 등록일
     */
    private Date regDt;

    /**
     * 수정일
     */
    private Date mdfcnDt;

    /**
     * 검색용 아이디/이름
     */
    private String acntOrNm;

    /**
     * 일련번호
     */
    private Integer userid;

    /**
     * 소속된 심사위원 그룹 일련번호
     */
    private Integer grpId;

    /**
     * 심사위원 그룹에 추가되는 전문가 일련번호
     */
    private Integer[] insertIds;
}
