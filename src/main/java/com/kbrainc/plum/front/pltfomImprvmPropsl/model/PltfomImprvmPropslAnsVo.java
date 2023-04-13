package com.kbrainc.plum.front.pltfomImprvmPropsl.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.util.StringUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * 플랫폼 개선 제안 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.model
 * - pltfomImprvmPropslAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : pltfomImprvmPropslAnsVo
 * @Description : 플랫폼 개선 제안 답변Vo 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.PltfomImprvmPropslAnsVo")
@Data
public class PltfomImprvmPropslAnsVo {
    /** 답변 아이디 */
    private Integer ansid;

    /** 제안 아이디 */
    private Integer prpslid;

    /** 답변자 이름 */
    private String nm;

    /** 제목 */
    private String ttl;

    /** 내용 */
    private String cn;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 답변 일시 */
    private Date ansDt;

    /** 파일 목록*/
    private List<FileVo> fileList;

    /** 공개 여부 */
    private String rlsYn;

    public void setNm(String nm) {
        this.nm = StringUtil.maskingName(nm);
    }
}

