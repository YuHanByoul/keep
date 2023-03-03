package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.List;

/**
 * 공통적으로 사용되는 전문가Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - CommonExprtVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : CommonExprtVo
 * @Description : 공통적으로 사용되는 전문가Vo 클래스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class CommonExprtVo {
    private UserVo user;
    private Integer userid;

    private String envEduCareerYy;
    private String envEduCareerMm;

    protected String exprtTypeCd;

    private String fldLctrYn;
    private String fldPlanngYn;
    private String fldCnsltngYn;
    private String fldEtcYn;
    private String fldEtcCn;

    List<MyHdofVo> hdofs;
    List<MyCrtfctVo> crtfcts;
    List<MyCareerVo> careers;

}
