package com.kbrainc.plum.front.instInfo.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 기관 담당자 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.instInfo.model
* - InstPicVo.java
* </pre>
*
* @ClassName : InstPicVo
* @Description : 기관 담당자 Vo 클래스
* @author : JD
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.InstPicVo")
public class InstPicVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 사용자아이디 */
    private Integer userid;
    
    /** 계정 */
    private String acnt;
    
    /** 이름 */
    private String nm;
    
    /** 휴대폰번호 */
    private String moblphon;
    
    /** 이메일 */
    private String eml;
    
    /** 주소 */
    private String addr;
    
    /** 주소_상세 */
    private String addrDtl;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 기관담당자_역할_코드 */
    private String instpicRoleCd;
    
    /** 기관담당자_역할_코드명 */
    private String instpicRoleCdNm;
    
    /** 상태 */
    private String sttsCd;
    
}