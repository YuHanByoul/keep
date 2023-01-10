package com.kbrainc.plum.mng.srng.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.Date;

/**
 * 심사 양식 문항 매핑 VO클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngFormQitemMapngVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngFormQitemMapngVO
 * @Description : 심사 양식 문항 매핑 VO클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class SrngFormQitemMapngVO extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 양식아이디 */
    private int formid;

    /** 문항아이디 */
    private int qitemid;

    /** 문항순서 */
    private String qitemOrdr;

    /** 체크리스트_구분_코드 */
    private String chklstSeCd;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private String mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private String rgtrid;
}
