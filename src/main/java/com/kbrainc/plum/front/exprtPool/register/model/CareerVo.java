package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 경력사항정보Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - CareerVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : CareerVo
 * @Description : 경력사항정보Vo 클래스
 * @date : 2023. 02. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CareerVo")
public class CareerVo {
    private String fldNm;
    private String actvtBgngDe;
    private String actvtEndDe;
    private String actvtYn;
    private String actvtHr;
    private String idntyInstNm;
    private String actvtCn;
    private String idntyDe;
    private Integer crtfFileid;
    private Integer artclassFileid;
    private String ordr;
    private FileVo crtfFile;
    private FileVo artClassFile;
}
