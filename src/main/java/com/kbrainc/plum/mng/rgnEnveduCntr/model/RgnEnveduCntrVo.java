package com.kbrainc.plum.mng.rgnEnveduCntr.model;

import java.sql.Date;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 지역환경교육센터 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.rgnEnveduCntr.model
* - RgnEnveduCntrVo.java
* </pre>
*
* @ClassName : RgnEnveduCntrVo
* @Description : 지역환경교육센터 VO 클래스
* @author : JD
* @date : 2022. 12. 30.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class RgnEnveduCntrVo extends ParentRequestVo{
        
    /** 로그인사용자정보 */
    private UserVo user;
    
    
    /** 순차아이디 */
    private int ordrid;
    /** 구분 코드 */
    private String seCd;
    /** 지역 코드 */
    private String rgnCd;
    /** 센터 이름 */
    private String cntrNm;
    /** 기관명 */
    private String instNm;
    /** 홈페이지 */
    private String hmpg;
    /** 수정 일시 */
    private Date mdfncDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록 일시 */
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;
    
    
    /** 구분 코드(공통코드) */
    private int cd;
    private String cdNm;
    
    /** 지역 코드(공통코드) */
    private int ctprvnCd;
    private String ctprvnNm;
    
}
