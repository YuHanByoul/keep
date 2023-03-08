/**
 * 
 */
package com.kbrainc.plum.front.mypage.ntcnHist.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;


/**
* 알림 내역 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.ntcnHist.model
* - MvmnVo.java
* </pre>
*
* @ClassName : NtcnHistVo
* @Description : 알림 내역 Vo 클래스
* @author : 이한명
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.NtcnHistVo")
public class NtcnHistVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    /** 알림아이디 */
    private Integer ntcnid;
    /** 사용자 아이디 */
    private Integer userid;
    /** 종류 코드*/
    private String kndCd;
    /** 종류 코드명*/
    private String kndCdNm;
    /** 제목 */
    private String ttl;
    /** 알림내용 */
    private String cn;
    /** 이동URL */
    private String mvmnurl;
    /** 이동URL */
    private String inqYn;
    /** 등록 일자 */
    private Date regDt;
    /** 알림 등록 일자 */
    private String ntcnDt;
    /** 순번 */
    private Integer rnum;
    /** 년월 */
    private String ym;
    
}
