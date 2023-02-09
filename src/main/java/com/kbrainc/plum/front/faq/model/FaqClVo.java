package com.kbrainc.plum.front.faq.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 자주묻는 질문 분류Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.model
 * - FaqClVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqClVo
 * @Description : 자주묻는 질문 분류Vo 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.FaqClVo")
@Data
public class FaqClVo {
    private Integer clid;
    private String clNm;
}
