package com.kbrainc.plum.example.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 예제 프로그램 Vo 클래스.
*
* <pre>
* com.kbrainc.plum.example.model
* - PrgrmVo.java
* </pre>
*
* @ClassName   : PrgrmVo 
* @Description : 예제 프로그램 Vo 클래스 
* @author      : KBRAINC
* @date        : 2022. 9. 29.
* @Version     : 
* @Company     : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("example.PrgrmVo")
public class PrgrmVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;

    /** 프로그램아이디 */
    private Integer prgrmid;

    /** 이름 */
    @NotEmpty(message = "프로그램명을 입력해 주십시오.")
    @Size(max = 100, message = "프로그램명은 100자를 넘을 수 없습니다.")
    private String nm;

    /** 타입 */
    private String type;

    /** 설명 */
    @Size(max = 400, message = "프로그램설명은 400자를 넘을 수 없습니다.")
    private String dc;

    /** URL */
    @Size(max = 500, message = "URL은 500자를 넘을 수 없습니다.")
    private String url;

    /** 상위_프로그램아이디 */
    private Integer uppr_prgrmid;

    /** 상위_프로그램명 */
    private String uppr_prgrm_nm;

    /** 로그인_여부 */
    private String login_yn;

    /** 시스템_코드 */
    private String sys_cd;

    /** 순서 */
    private Integer ord;

    /** 트리_순서 */
    private String tree_ord;

    /** 트리_프로그램_이름 */
    private String tree_prgrm_nm;

    /** 트리_프로그램아이디 */
    private String tree_prgrmid;

    /** 대상프로그램아이디 */
    private Integer tprgrmid;

    /** jQuery dynatree hitMode */
    private String hitMode;

    /** upperYn */
    private String upperYn;

    /** 수정_일시 */
    private Date updt_dt;

    /** 수정자아이디 */
    private Integer updtuserid;

    /** 등록_일시 */
    private Date reg_dt;

    /** 등록자아이디 */
    private Integer reguserid;
}