package com.kbrainc.plum.mng.helpDesk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import lombok.Data;

import java.util.Date;

/**
* 탄소중립헬프데스크 문의글Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.helpDesk.model
* - HelpDeskVo.java
* </pre>
*
* @ClassName   : HelpDeskVo
* @Description : TODO
* @author      : KBRAINC_DEV
* @date        : 2022. 12. 20.
* @Version     :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class HelpDeskVo extends ParentRequestVo {
    /** 분류코드 그룹아이디 */
    private String clsfCdgrpid = "112";
    /** 상태코드 그룹아이디 */
    private String sttsCdgrpid = "113";
    /** 문의 일련번호 */
    private Integer inqryid;
    /** 분류코드 */
    private String clsfCd;
    /** 분류명 */
    private String clsfNm;
    /** 상태코드 */
    private String sttsCd;
    /** 상태코드명 */
    private String sttsCdNm;
    /** 제목 */
    private String ttl;
    /** 내용 */
    private String cn;
    /** 파일그룹아이디 */
    private Integer filegrpid;
    /** 기관명 */
    private String instNm;
    /** 작성자명 */
    private String nm;
    /** 작성자아이디 */
    private String acnt;
    /** 작성일 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;
    /** 처리자명 */
    private String prcrNm;

}
