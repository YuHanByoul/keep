package com.kbrainc.plum.mng.dwnldDsctn.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 다운로드 사유Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.model
 * - DwnldDsctnVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DwnldDsctnVo
 * @Description : 다운로드 사유Vo 클래스
 * @date : 2023. 04. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class DwnldDsctnVo {
    /** 로그인 사용자 정보 */
    private UserVo user;

    /** 내역 아이디 */
    private Integer dsctnid;

    /** 파일 그룹 아이디 */
    private Integer filegrpid;

    /** 파일 아이디 */
    private Integer fileid;

    /** 파일 식별 키 */
    private String fileIdntfcKey;

    /** 사용자 아이디 */
    private Integer userid;

    /** 파일 이름 */
    private String fileNm;

    /** 등록 일시 */
    private Date regDt;

    /** 등록자 아이디 */
    private Integer rgtrid;

    private String formId;

    private String excelDownUrl;

    private String popupId;

    /** 사유 */
    @NotEmpty(message = "사유를 적지 않으면 다운로드 할 수 없습니다.")
    @Size(min = 10, max = 29, message = "다운로드 사유를 10자 이상, 30자 미만으로 작성해 주십시오.")
    private String rsn;
}
