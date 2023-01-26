package com.kbrainc.plum.mng.evnt.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 참여신청관리 > 이벤트Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.evt.model
 * - EvntVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EvntVo
 * @Description : 참여신청관리 > 이벤트Vo 클래스
 * @date : 2023. 01. 26.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@NoArgsConstructor
public class EvntVo extends ParentRequestVo {
    private UserVo user;

    private Integer evntid;

    @NotEmpty
    private String ttl;

    @NotEmpty
    private String cn;

    @NotEmpty
    private Date bgngDe;

    @NotEmpty
    private Date endDe;

    @NotEmpty
    private Date prsntnDe;

    @NotEmpty
    private String aplyUrl;

    private Integer hits;

    private Integer filegrpid;

    private String delYn;

    private Date mdfcnDt;

    private Date regDt;

    private Integer mdfrid;

    private Integer rgtrid;

    private String nm;

}
