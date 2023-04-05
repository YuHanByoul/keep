package com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.controller
 * - EtcDtaVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EtcDtaVo
 * @Description :
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.EtcDtaVo")
public class EtcDtaVo extends ParentRequestVo {
    private Integer dtaid;

    @NotEmpty(message = "제목을 입력해 주십시오.")
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다.")
    private String ttl;

    @NotEmpty(message = "내용을 입력해 주십시오.")
    private String cn;

    /** 국가_코드 */
    private String nationCd;
    /** 국가_코드명 */
    private String nationNm;
    /** 홈페이지 */
    private String hmpg;
    /** 위치 */
    private String pstn;
    /** 연락처 */
    private String cntct;
    /** 개방시간 */
    private String opnhr;
    /** 참가비 */
    private String etrfee;
    /** 체험_프로그램 */
    private String exprnPrgrm;
    /** 저작권_코드 */
    private String cpyrhtCd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    private Integer hits;

    private Integer prevDtaid;
    private String prevDtaTtl;
    private Integer nextDtaid;
    private String nextDtaTtl;

    private String[] searchTypeCd;
    private String searchNationCd;

    private Integer atchFilegrpid;
    private List<FileVo> atchFileList;

}
