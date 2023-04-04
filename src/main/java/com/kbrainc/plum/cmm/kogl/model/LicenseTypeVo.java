package com.kbrainc.plum.cmm.kogl.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 공공누리 저작권 요소VO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.cmm.kogl.model - LicenseTypeVo.java
 * </pre>
 *
 * @ClassName : SearchVo
 * @Description : 공공누리 저작권 요소VO 클래스
 * @author : KBRAINC
 * @date : 2023. 4. 03.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@AllArgsConstructor
public class LicenseTypeVo {

    /** 타입코드 */
    private String typeCd;
    
    /** 타입명 */
    private String typeNm;
    
    /** 설명 */
    private String desc;
    
    /** 이미지 경로 */
    private String imageSrc;
    
    /** 새창 링크URL */
    private String link;
}