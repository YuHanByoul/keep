package com.kbrainc.plum.mng.tchaidRvw.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 교구후기 관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.model
* - MmnwsVo.java
* </pre>
*
* @ClassName : MmnwsVo
* @Description : 교구후기 관리 VO 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Data
public class TchaidRvwVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 보도아이디 */
    private int nscvrgid;
    /** 분류 코드 */
    /** C : 센터소식, E : 환경교육소식*/
    @NotEmpty(message = "분류코드를 입력해주십시오.")
    private String clsfCd;
    /** 언론사 이름 */
    @NotEmpty(message = "언론사를 입력해주십시오.")
    private String oxprNm;
    /** 제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String ttl;
    /** url */
    @NotEmpty(message = "링크주소를 입력해주십시오.")
    private String url;
    /** 작성 일자 */
    private String wrtDe;
    /** 수정 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자 아이디 */
    private int mdfrid;
    /** 등록 일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    private String nm;
    private String searchCd;
}
