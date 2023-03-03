package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 자격증정보Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - CrtfctVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : CrtfctVo
 * @Description : 자격증정보Vo 클래스
 * @date : 2023. 02. 21.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.CrtfctVo")
public class CrtfctVo {
    private String crtfctNm;
    private String acqsInst;
    private String acqsNo;
    private String acqsGrd;
    private String acqsDe;
    private Integer crtfctFileid;
    private String ordr;
    private FileVo crtfctFile;
}
