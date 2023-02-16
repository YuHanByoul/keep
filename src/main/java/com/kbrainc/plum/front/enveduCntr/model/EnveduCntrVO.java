package com.kbrainc.plum.front.enveduCntr.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.sql.Date;

/**
 * 지역 환경교육센처 Vo
 *
 * <pre>
 * com.kbrainc.plum.front.enveduCntr.model
 * - EnveduCntrVO.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnveduCntrVO
 * @Description : 지역 환경교육센처 Vo
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Data
public class EnveduCntrVO extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    /** 순차아이디 */
    private Integer ordrid;
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
    private Integer mdfrid;
    /** 등록 일시 */
    private Date regDt;
    /** 등록자아이디 */
    private Integer rgtrid;
    /** 기관 개수 */
    private Integer cntrCnt;
    
    /** 기관 전화번호 */
    private String telno;
    /** 기관 주소 */
    private String addr;
    /** 기관 주소상세 */
    private String addrDtl;
    /** 기관 로고파일아이디 */
    private String logoFileid;
    /** 기관 파일식별키 */
    private String fileIdntfcKey;
    
    
}
