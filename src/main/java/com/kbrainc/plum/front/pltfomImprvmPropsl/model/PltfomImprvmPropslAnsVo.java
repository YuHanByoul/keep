package com.kbrainc.plum.front.pltfomImprvmPropsl.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
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
    private Integer ansid;
    private Integer prpslid;
    private String ttl;
    private String cn;
    private Integer filegrpid;
    private Date ansDt;
    private String opetrAcnt;
    private List<FileVo> fileList;
}

