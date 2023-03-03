package com.kbrainc.plum.front.cmnty.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 커뮤니티 게시판 템플릿 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyBbsTmplatVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyBbsTmplatVo
 * @Description : 커뮤니티 게시판 템플릿 Vo
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Data()
@Alias("front.CmntyBbsTmplatVo")
public class CmntyBbsTmplatVo extends ParentRequestVo {
    /** 커뮤니티_게시판_템플릿아이디 */
    private Integer cmntyBbsTmplatid;
    /** 템플릿_이름 */
    private String tmplatNm;
    /** 필수_여부 */
    private String essntlYn;
    /** 순서 */
    private Integer ordr;
}
