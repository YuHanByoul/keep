package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.StringUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 게시글 댓글 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyCmntVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyCmntVo
 * @Description : 커뮤니티 게시글 댓글 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data()
@Alias("front.CmntyCmntVo")
public class CmntyCmntVo extends ParentRequestVo {
    private UserVo user;

    /** 댓글아이디 */
    private Integer cmntid;
    /** 게시글아이디 */
    private Integer pstid;
    /** 게시판아이디 */
    private Integer bbsid;
    /** 내용 */
    private String cn;
    /** 부모_댓글아이디 */
    private Integer parntsCmntid;
    /** 댓글_그룹 */
    private Integer cmntGrp;
    /** 깊이 */
    private Integer dpth;
    /** 정렬순서 */
    private Integer sortordr;
    /** 공개_여부 */
    private String rlsYn;
    /** 삭제_여부 */
    private String delYn;
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
    /** 등록자이름 */
    private String rgtrNm;
    /** 등록자계정명 */
    private String rgtrAcnt;
    /** 댓글의 답글 개수 */
    private Integer cmntReplyCnt;

    public void setRgtrNm(String rgtrNm) {
        this.rgtrNm = StringUtil.maskingName(rgtrNm);
    }

    public void setRgtrAcnt(String rgtrAcnt) {
        this.rgtrAcnt = StringUtil.maskingAccount(rgtrAcnt);
    }
}
