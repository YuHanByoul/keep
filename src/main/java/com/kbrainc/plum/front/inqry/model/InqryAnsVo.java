package com.kbrainc.plum.front.inqry.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import lombok.Data;

import java.util.List;

/**
 * 1:1문의 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.model
 * - InqryAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryAnsVo
 * @Description : 1:1문의 답변Vo 클래스
 * @date : 2023. 02. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class InqryAnsVo {
    private String answrid;
    private String inqryid;
    private String title;
    private String cntnts;
    private String opetrid;
    private String filegrpid;
    private String ansDe;
    private String opetrAcnt;
    private List<FileVo> fileList;
}
