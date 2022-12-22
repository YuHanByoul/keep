package com.kbrainc.plum.mng.helpDesk.model;

import lombok.Data;

/**
* 탄소중립헬프데스크 담당자Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.helpDesk.model
* - HelpDeskAnswrVo.java
* </pre>
*
* @ClassName   : HelpDeskAnswrVo
* @Description : TODO
* @author      : KBRAINC_DEV
* @date        : 2022. 12. 20.
* @Version     :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class HelpDeskAnswrManagerVo {
    /** 문의 일련번호 */
    private Integer inqryid;
    /** 담당자 일련번호 */
    private Integer userid;
    /** 담당자 이름 */
    private String nm;
}
