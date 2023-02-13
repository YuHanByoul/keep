package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 전문가 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - ExprtVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtVo
 * @Description : 전문가 Vo 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.ExprtVo")
public class ExprtVo extends ParentRequestVo {
    private Integer userid;
    private String exprtTypeCd;
    private String fldLctrYn;
    private String fldPlanngYn;
    private String fldCnsltngYn;
    private String fldEtcYn;
    private String fldEtcCn;
    private String envEduCareerYy;
    private String envEduCareerMm;
    private String exprtTrgtCd;
    private String exprtSbjctCd;
    private String exprtActvtRgnCd;
    private String exprtActvtScopeCd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date mdfcnDt;
    private String nm;
    private String brdt;
    private String gndr;


}
