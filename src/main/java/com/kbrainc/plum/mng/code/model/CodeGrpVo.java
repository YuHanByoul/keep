package com.kbrainc.plum.mng.code.model;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * 코드그룹VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.code.model
 * - CodeGrpVo.java
 * </pre> 
 *
 * @ClassName : CodeGrpVo
 * @Description : 코드그룹VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CodeGrpVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 코드그룹아이디 */
    @NotEmpty(message = "코드그룹아이디를 입력해 주십시오.")
    @Size(max = 3, message = "코드그룹아이디는 3자를 넘을 수 없습니다.")
    private String cdgrpid;

    /** 코드그룹이름 */
    @NotEmpty(message = "코드그룹명을 입력해 주십시오.")
    @Size(max = 200, message = "코드그룹명은 200자를 넘을 수 없습니다.")
    private String cdgrpNm;

    /** 코드그룹설명 */
    @Size(max = 200, message = "코드그룹설명은 200자를 넘을 수 없습니다.")
    private String cdgrpDc;

    /** 옵션1 */
    @Size(max = 200, message = "옵션1은 200자를 넘을 수 없습니다.")
    private String optn1;

    /** 옵션2 */
    @Size(max = 200, message = "옵션2는 200자를 넘을 수 없습니다.")
    private String optn2;

    /** 사용_여부 */
    private String useYn;

    /** 수정자아이디 */
    private Integer updtuserid;

    /** 수정자이름 */
    private String updtNm;

    /** 수정_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updtDt;
    
    public void setUpdtDt(Date updtDt) {
        this.updtDt = updtDt != null ? (Date) updtDt.clone() : null;
    }

}