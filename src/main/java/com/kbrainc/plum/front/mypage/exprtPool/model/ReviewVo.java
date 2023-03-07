package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 전문가 후기Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - ReviewVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ReviewVo
 * @Description : 전문가 후기Vo 클래스
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.ReviewVo")
public class ReviewVo {
    private UserVo user;
    private Integer dmndid;
    private Integer scr;
    private String etcOpnn;
    private Integer eduNope;
    private String eduCn;
    private String addr;
    private String addrDtl;
}
