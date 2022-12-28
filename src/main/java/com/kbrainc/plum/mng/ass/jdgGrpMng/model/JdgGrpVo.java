package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 심사위원 그룹 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.model
 * - JdgGrpVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpVo
 * @Description : 심사위원 그룹 Vo 클래스
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class JdgGrpVo extends ParentRequestVo {
    /**
     * 로그인 사용자 정보
     */
    private UserVo user;

    /**
     * 심사위원 그룹 일련번호
     */
    private Integer grpId;

    /**
     * 그룹 이름
     */
    @Size(max = 20, message = "심사위원 그룹명은 20자를 넘을 수 없습니다.")
    private String grpNm;

    /**
     * 사용 여부
     */
    private String useYn;

    /**
     * 소속된 전문가 수
     */
    private Integer exprtCnt;

    /**
     * 검색용 사용여부
     */
    private String searchUseYn;

    /**
     * 등록일
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDt;

    /**
     * 수정일
     */
    private Date mdfcnDT;

}
