package com.kbrainc.plum.mng.pltfomImprvmPropsl.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 플랫폼 개선 제안 답변Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.pltfomImprvmPropsl.model
 * - pltfomImprvmPropslAnsVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : pltfomImprvmPropslAnsVo
 * @Description : 플랫폼 개선 제안 답변Vo 클래스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class PltfomImprvmPropslAnsVo {
    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 답변 아이디 */
    private Integer ansid;

    /** 제안 아이디 */
    private Integer prpslid;

    /** 답변자 이름 */
    private String userNm;

    /** 사용자아이디 */
    private Integer userid;

    /** 제목 */
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다.")
    private String ttl;

    /** 내용 */
    @Size(max = 4000, message = "내용은 4000자를 넘을 수 없습니다.")
    private String cn;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 답변자 */
    private String rgtrNm;
    
    /** 공개 여부 */
    private String rlsYn;

    /** 파일 목록*/
    private List<FileVo> fileList;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date ansDt;

}

