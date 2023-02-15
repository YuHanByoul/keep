package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 환경교육 전문가 풀 > 섭외 요청 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - LctrDmndVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndVo
 * @Description : 환경교육 전문가 풀 > 섭외 요청 Vo 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.LctrDmndVo")
public class LctrDmndVo {
    private UserVo user;
}
