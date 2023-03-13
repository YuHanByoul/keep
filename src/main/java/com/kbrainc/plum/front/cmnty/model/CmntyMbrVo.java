package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 회원 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyMbrVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyMbrVo
 * @Description : 커뮤니티 회원 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data()
@Alias("front.CmntyMbrVo")
public class CmntyMbrVo extends ParentRequestVo {
    private UserVo user;

    /** 커뮤니티아이디 */
    private Integer cmntyid;
    /** 사용자아이디 */
    private String userid;
    /** 멤버_상태_코드 */
    private String mbrSttsCd;
    /** 권한_코드 */
    private String authrtCd;
    /** 가입_신청_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date joinAplyDt;
    /** 가입_승인_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date joinAprvDt;
    /** 승인자_아이디 */
    private Integer autzrId;
    /** 이름  */
    private String nm;
    /** 회원 미승인 여부 */
    private String notAprvYn;
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private Integer mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private Integer rgtrid;
}
