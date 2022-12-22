package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.ArrayList;

/**
* 담당자 검색 모달Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.helpDesk.model
* - HelpDeskModalUserVo.java
* </pre>
*
* @ClassName   : HelpDeskModalUserVo
* @Description : TODO
* @author      : KBRAINC_DEV
* @date        : 2022. 12. 20.
* @Version     :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class HelpDeskModalUserVo extends ParentRequestVo {
    /** 로그인 사용자 정보 */
    private UserVo user;
    /** 담당자 이름  */
    private String nm;
    /** 담당자 아이디 */
    private String acnt;
    /** 담당자 기관 일련번호*/
    private Integer instid;
    /** 담당자 기관명 */
    private String instNm;
    /** 담당자 일련번호 */
    private Integer userid;
    /** 담당자 역할 정보  */
    private String roleNm;
    /** 기관 검색 정보 */
    private String searchInst;
}
