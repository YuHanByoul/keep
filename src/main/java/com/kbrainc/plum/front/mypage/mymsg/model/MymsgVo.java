/**
 * 
 */
package com.kbrainc.plum.front.mypage.mymsg.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;


/**
* 쪽지함 Vo 클래스
*
* <pre>
* com.kbrainc.plum.front.mypage.mymsg.model
* - MymsgVo.java
* </pre>
*
* @ClassName : MymsgVo
* @Description : 쪽지함 Vo 클래스
* @author : 이한명
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
@Alias("front.MymsgVo")
public class MymsgVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;

    /** 메시지아이디 */
    private String msgid;
    /** 사용자아이디 */
    private Integer userid;
    /** 대상아이디 */
    private Integer trgtid;
    /** 발신자아이디 */
    private Integer sendid;
    /** 발신자아이디 */
    private String maskAcnt;
    /** 발신자명 */
    private String sendNm;
    /** 수신자아이디 */
    private Integer recvid;
    /** 수신자명 */
    private String recvNm;
    /** 내용 */
    private String cn;
    /** 조회_여부 */
    private String inqYn;
    /** 사용자_삭제_여부 */
    private String userDelYn;
    /** 대상_삭제_여부 */
    private String trgtDelYn;
    /** 등록_일시 */
    private String regDt;
}
