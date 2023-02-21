package com.kbrainc.plum.front.msg.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

/**
 * 쪽지 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.msg.model
 * - MsgVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MsgVo
 * @Description : 쪽지 Vo
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
public class MsgVo extends ParentRequestVo {

    private UserVo user;

    /** 메시지아이디 */
    private String msgid;

    /** 사용자아이디 */
    private String userid;

    /** 대상아이디 */
    private String trgtid;

    /** 내용 */
    private String cn;

    /** 조회_여부 */
    private String inqYn;

    /** 사용자_삭제_여부 */
    private String userDelYn;

    /** 대상_삭제_여부 */
    private String trgtDelYn;

    /** 수정_일시 */
    private String mdfcnDt;

    /** 수정자아이디 */
    private String mdfrid;

    /** 등록_일시 */
    private String regDt;

    /** 등록자아이디 */
    private String rgtrid;

    /** 대상자 계정 */
    private String trgtAcnt;

    /** 대상자 이름 */
    private String trgtNm;

}
