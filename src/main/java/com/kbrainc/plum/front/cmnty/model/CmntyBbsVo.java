package com.kbrainc.plum.front.cmnty.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 커뮤니티 게시판 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyBbsVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyBbsVo
 * @Description : 커뮤니티 게시판 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data()
@Alias("front.CmntyBbsVo")
public class CmntyBbsVo extends ParentRequestVo {
    private UserVo user;

    /** 게시판아이디 */
    private Integer bbsid;
    /** 커뮤니티아이디 */
    private Integer cmntyid;
    /** 커뮤니티_게시판_템플릿아이디 */
    private Integer cmntyBbsTmplatid;
    /** 이름 */
    private String nm;
    /** 설명 */
    private String expln;
    /** 분류_코드 */
    private String clsfCd;
    /** 고정_공지_사용_여부 */
    private String fxNtcUseYn;
    /** 고정_공지_개수 */
    private Integer fxNtcCnt;
    /** 첨부파일_사용_여부 */
    private String atchfileUseYn;
    /** 첨부파일_개수 */
    private Integer atchfileCnt;
    /** 첨부파일_크기 */
    private Integer atchfileSize;
    /** 답글_사용_여부 */
    private String rplyUseYn;
    /** 댓글_사용_여부 */
    private String cmntUseYn;
    /** NEW_사용_여부 */
    private String newUseYn;
    /** NEW_표시_일수 */
    private Integer newIndictDaycnt;
    /** HOT_사용_여부 */
    private String hotUseYn;
    /** HOT_사용_기준_조회수 */
    private Integer hotUseCrtrHits;
    /** 분류_사용_여부 */
    private String clsfUseYn;
    /** 페이지_게시글_개수 */
    private Integer pagePstCnt;
    /** 비로그인_다운로드_허용_여부 */
    private String nloginDwnldPermYn;
    /** 사용_여부 */
    private String useYn;
    /** 검색 여부 */
    private String searchYn;
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
    /** 고정공지갯수 */
    private Integer curFxNtcCnt;
}
