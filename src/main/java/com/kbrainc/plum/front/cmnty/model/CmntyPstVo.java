package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 게시글 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyPstVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyPstVo
 * @Description : 커뮤니티 게시글 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data()
@Alias("front.CmntyPstVo")
public class CmntyPstVo extends ParentRequestVo {
    private UserVo user;

    /** 게시글아이디 */
    private Integer pstid;
    /** 게시판아이디 */
    private Integer bbsid;
    /** 게시판_분류아이디 */
    private Integer bbsClsfid;
    /** 제목 */
    private String ttl;
    /** 내용 */
    private String cn;
    /** 부모_게시글아이디 */
    private Integer parntsPstid;
    /** 그룹 */
    private Integer grp;
    /** 깊이 */
    private Integer dpth;
    /** 정렬순서 */
    private Integer sortordr;
    /** 조회수 */
    private Integer hits;
    /** 고정_공지_여부 */
    private String fxNtcYn;
    /** 고정_공지_시작_일시 */
    private String fxNtcBgngDt;
    /** 고정_공지_종료_일시 */
    private String fxNtcEndDt;
    /** 로그인_여부 */
    private String lgnYn;
    /** 파일그룹아이디 */
    private Integer filegrpid;
    /** 삭제_여부 */
    private String delYn;
    private String hotYn;
    private String newYn;
    private String fxNtcUseYn;
    private String cmntUseYn;
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private Integer mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private String rgtrid;
    /** 등록자이름 */
    private String rgtrNm;
    private String nextPstid;
    private String nextTitle;
    private String prevPstid;
    private String prevTitle;
    /** 고정공지순서 */
    private Integer fixorder;

}
