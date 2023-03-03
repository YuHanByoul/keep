package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 재직사항정보Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - HdofVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HdofVo
 * @Description : 재직사항정보Vo 클래스
 * @date : 2023. 02. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.HdofVo")
public class HdofVo {
    private String hdofSeCd;
    private String instNm;
    private String deptNm;
    private String jbgdNm;
    private String hdofBgngDe;
    private String hdofEndDe;
    private String hdofYn;
    private Integer hdofcrtfFileid;
    private String ordr;
    private FileVo hdofCrtfFile;
}
