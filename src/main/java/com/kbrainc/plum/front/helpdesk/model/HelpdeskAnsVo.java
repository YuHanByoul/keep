package com.kbrainc.plum.front.helpdesk.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.util.StringUtil;
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
    /** 답변 아이디 */
    private Integer ansid;

    /** 답변 날짜 */
    private String ansDe;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 답변자 아이디 */
    private String acnt;

    /** 답변자 이름 */
    private String nm;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 공개 여부 */
    private String rlsYn;

    /** 파일 목록 */
    private List<FileVo> fileList;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }
}
