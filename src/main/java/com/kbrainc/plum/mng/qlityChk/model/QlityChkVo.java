package com.kbrainc.plum.mng.qlityChk.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 콘텐츠 품질 체크 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.qlityChk.model
* - QlityChkVo.java
* </pre>
*
* @ClassName : QlityChkVo
* @Description : 콘텐츠 품질 체크 Vo 클래스
* @author : JD
* @date : 2023. 4. 18.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class QlityChkVo  extends ParentRequestVo{
    
    private UserVo user;
    
    /** 체크아이디 */
    private Integer ceckid;
    /** 사용자아이디 */
    private String userid;
    /** 대상_코드 */
    private String trgtCd;
    /** 이벤트_코드 */
    private String evntCd;
    /** 콘텐츠아이디 */
    private String cntntsid;
    /** 수정_일시 */
    private String mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록_일시 */
    private String regDt;
    /** 등록자아이디 */
    private String rgtrid;
    
}
