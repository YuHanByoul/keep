package com.kbrainc.plum.mng.inqry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;

import javax.validation.constraints.Size;
import java.util.Date;

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
 * @Company : Copyright KBRAIN Company. All Rights Reserved
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
    @Size(max = 200, message = "답변 제목은 200자를 넘을 수 없습니다.")
    private String title;

    /** 내용 */
    @Size(max = 4000, message = "답변 내용은 4000자를 넘을 수 없습니다.")
    private String cntnts;

    /**작성자 아이디 */
    private Integer userid;
    
    /** 처리자아이디 */
    private Integer opetrid;

    /** 처리자_이름 */
    private String opetrNm;

    /** 수정_일시 */
    private Date mdfcnDt;

    /** 수정자아이디 */
    private Integer mdfrid;

    /** 등록_일시 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    /** 등록자아이디 */
    private Integer rgtrid;

    private String inqrySttsCd;

    private String ansDe;

    private Date anscmptnDt;

    private Integer[] managerId;

    public void setRegDt(Date regDt) {
        this.regDt = regDt != null ? (Date) regDt.clone() : null;
    }
    
    public void setmdfcnDt(Date mdfcnDt) {
        this.mdfcnDt = mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }

    public Date getRegDt() {
        return regDt != null ? (Date) regDt.clone() : null;
    }

    public Date getmdfcnDt() {
        return mdfcnDt != null ? (Date) mdfcnDt.clone() : null;
    }
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   

}