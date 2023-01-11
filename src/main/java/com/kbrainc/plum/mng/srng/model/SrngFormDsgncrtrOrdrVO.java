package com.kbrainc.plum.mng.srng.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.Date;

/**
 * 심사 양식 지정기준 순서 VO클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.model
 * - SrngFormDsgncrtrOrdrVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngFormDsgncrtrOrdrVO
 * @Description : 심사 양식 지정기준 순서 VO클래스
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class SrngFormDsgncrtrOrdrVO extends ParentRequestVo {

    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 양식아이디 */
    private int formid;

    /** 지정기준_코드 */
    private String dsgncrtrCd;

    /** 지정기준순서 */
    private String dsgncrtrOrdr;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private int mdfrid;

    /** 등록_일시 */
    private Date regDt;

    /** 등록자아이디 */
    private int rgtrid;
}
