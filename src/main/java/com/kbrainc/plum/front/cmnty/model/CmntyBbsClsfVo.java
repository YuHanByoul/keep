package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 게시글 분류 순서 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyBbsClsfVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyBbsClsfVo
 * @Description : 커뮤니티 게시글 분류 순서 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CmntyBbsClsfVo")
public class CmntyBbsClsfVo extends ParentRequestVo {
    private UserVo user;

    /** 게시판_분류아이디 */
    private Integer bbsClsfid;
    /** 게시판아이디 */
    private Integer bbsid;
    /** 분류_이름 */
    private String clsfNm;
    /** 순서 */
    private Integer ordr;
    /** 사용_여부 */
    private String useYn;
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private Integer mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private Integer rgtrid;
}
