package com.kbrainc.plum.front.envtcherTrnngInst.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 환경교육사 양성기관 현황 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.envtcherTrnngInst.model
 * - EnvtcherTrnngInstVo.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvtcherTrnngInstVo
 * @Description : 환경교육사 양성기관 현황 Vo
 * @date : 2023. 02. 14.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Alias("front.EnvtcherTrnngInstVo")
@Data
public class EnvtcherTrnngInstVo extends ParentRequestVo {
    /** 기관명 */
    private String instNm;
    /** 등급 */
    private String grdcdNm;
    /** 홈페이지 주소 */
    private String hmpg;
    /** 팩스번호 */
    private String fxno;
    /** 전화번호 */
    private String telno;
    /** 썸네일 */
    private Integer logoFileid;
    /** 기관 주소 */
    private String addr;
    /** 기관 설명 */
    private String expln;
    /** 파일 키 */
    private String fileIdntfcKey;
}
