package com.kbrainc.plum.front.mmnws.model;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 언론보도 관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.front.mmnws.model
* - MmnwsVo.java
* </pre>
*
* @ClassName : MmnwsVo
* @Description : 언론보도 관리 VO 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Data
@Alias("front.MmnwsVo")
public class MmnwsVo extends ParentRequestVo {
    
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
    private Date mdfcnDt;
    /** 수정자 아이디 */
    private int mdfrid;
    /** 등록 일시 */
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    private String nm;
    private String newMmnws;
    private String searchOxprNm;
    private String searchTtl;
}
