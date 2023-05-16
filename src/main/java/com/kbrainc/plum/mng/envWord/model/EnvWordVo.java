package com.kbrainc.plum.mng.envWord.model;



import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 환경교육용어사전 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.model
* - MmnwsVo.java
* </pre>
*
* @ClassName : MmnwsVo
* @Description : 환경교육용어사전 VO 클래스
* @author : JD
* @date : 2022. 12. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Data
public class EnvWordVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 용어아이디 */
    private int wordid;
    private String[] wordids;
    /** 용어 */
    @NotEmpty(message = "용어를 입력해주십시오.")
    private String word;
    /** 용어_영어 */
    @NotEmpty(message = "영문을 입력해주십시오.")
    private String wordEngl;
    /** 내용_요약 */
    @NotEmpty(message = "요약정보를 입력해주십시오.")
    private String cnSumry;
    /** 내용 */
    @NotEmpty(message = "내용을 입력해주십시오.")
    private String cn;
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
    /** 삭제여부 */
    private char delYn;
    
    private String evntCd;
    private String trgtCd;
    
    private String rgtridNm;
    
    private String searchEngl;
    private String searchBgngDt;
    private String searchEndDt;
}
