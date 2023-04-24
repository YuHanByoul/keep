package com.kbrainc.plum.front.seeEnvEduInst.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 교육기관/시설 > 사회환경교육기관Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.seeEnvEduInst.model
 * - SeeEnvEduInstVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SeeEnvEduInstVo
 * @Description : 교육기관/시설 > 사회환경교육기관Vo 클래스
 * @date : 2023. 04. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.SeeEnvEduinstVo")
public class SeeEnvEduInstVo {
    /** 기관 이름 */
    private String instNm;

    /** 기관 대표번호 */
    private String telno;

    /** 기관 주소 */
    private String addr;

    /** 기관 주소 상세 */
    private String addrDtl;

    /** 기관 홈페이지 */
    private String hmpg;

    /** 기관 로고 파일 아이디 */
    private String logoFileid;

    /** 기관 로고 파일 식별 키 */
    private String fileIdntfcKey;
}
