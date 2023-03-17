package com.kbrainc.plum.front.exprtPool.register.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 환경교육사 자격증정보 연동Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - MmbrQlfcVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MmbrQlfcVo
 * @Description : 환경교육사 자격증정보 연동Vo 클래스
 * @date : 2023. 03. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.MmbrQlfcVo")
public class MmbrQlfcVo {
    /** 과정 등급 */
    private String crsGrd;

    /** 과정 등급 코드 값 */
    private String crsGrdCdv;

    /** 자격증 취득일 */
    private String lcncAcqsYmd;

    /** 자격증 번호 */
    private String qlfcRsltCode;
}
