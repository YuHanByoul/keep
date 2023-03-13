package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 카테고리 순서 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyCtgryVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyCtgryVo
 * @Description : 커뮤니티 카테고리 순서 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CmntyCtgryVo")
public class CmntyCtgryVo extends ParentRequestVo {
    private UserVo user;

    /** 커뮤니티아이디 */
    private Integer cmntyid;
    /** 커뮤니티_게시판_템플릿아이디 */
    private Integer cmntyBbsTmplatid;
    /** 순서 */
    private Integer ordr;
    /** 게시글아이디 */
    private Integer bbsid;
    /** 게시글이름 */
    private String nm;
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
