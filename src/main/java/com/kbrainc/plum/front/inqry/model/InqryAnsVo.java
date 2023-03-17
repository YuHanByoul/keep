package com.kbrainc.plum.front.inqry.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.util.StringUtil;
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
    /** 답변 아이디 */
    private String answrid;

    /** 문의 아이디 */
    private String inqryid;

    /** 제목 */
    private String title;

    /** 내용 */
    private String cntnts;

    /** 처리자 아이디 */
    private String opetrid;

    /** 파일 그룹 아이디 */
    private String filegrpid;

    /** 답변 일시 */
    private String ansDe;

    /** 처리자 이름 */
    private String nm;

    /** 파일 목록 */
    private List<FileVo> fileList;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }
}
