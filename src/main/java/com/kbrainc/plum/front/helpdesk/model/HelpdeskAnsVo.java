package com.kbrainc.plum.front.helpdesk.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 헬프데스크 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.helpdesk.model
 * - HelpdeskAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpdeskAnsVo
 * @Description : 헬프데스크 답변Vo 클래스
 * @date : 2023. 02. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.HelpdeskAnsVo")
@Data
public class HelpdeskAnsVo {
    private Integer ansid;
    private String ansDe;
    private String ttl;
    private String cn;
    private String acnt;
    private Integer filegrpid;
    private String rlsYn;
    private List<FileVo> fileList;

}
