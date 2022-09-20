package com.kbrainc.plum.mng.inqry.model;

import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * 1:1문의답변VO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.model
 * - InqryAnswrVo.java
 * </pre> 
 *
 * @ClassName : InqryAnswrVo
 * @Description : 1:1문의답변VO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class InqryAnswrVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 문의아이디 */
    private Integer inqryid;

    /** 답변아이디 */
    private Integer answrid;

    /** 제목 */
    @NotEmpty(message = "답변 제목을 입력해 주십시오.")
    @Size(max = 200, message = "답변 제목은 200자를 넘을 수 없습니다.")
    private String title;

    /** 내용 */
    @NotEmpty(message = "답변 내용을 입력해 주십시오.")
    @Size(max = 4000, message = "답변 내용은 4000자를 넘을 수 없습니다.")
    private String cntnts;

    /**작성자 아이디 */
    private Integer userid;
    
    /** 처리자아이디 */
    private Integer opetrid;

    /** 처리자_이름 */
    private String opetr_nm;

    /** 수정_일시 */
    private Date updt_dt;

    /** 수정자아이디 */
    private Integer updtuserid;

    /** 등록_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reg_dt;

    /** 등록자아이디 */
    private Integer reguserid;
}