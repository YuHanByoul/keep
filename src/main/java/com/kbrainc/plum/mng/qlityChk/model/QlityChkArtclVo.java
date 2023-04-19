package com.kbrainc.plum.mng.qlityChk.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 콘텐츠 품질 체크 항목 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.qlityChk.model
* - QlityChkArtclVo.java
* </pre>
*
* @ClassName : QlityChkArtclVo
* @Description : 콘텐츠 품질 체크 항목 Vo 클래스
* @author : JD
* @date : 2023. 4. 18.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class QlityChkArtclVo  extends ParentRequestVo{
    
    private UserVo user;
    
    /** 체크아이디 */
    private Integer ceckid;
    /** 체크리스트아이디 */
    private String[] chklstid;
    /** 분야_코드 */
    private String fldCd;
    /** 순서 */
    private String ordr;
    /** 내용 */
    private String cn;
    /** 등록_일시 */
    private String regDt;
    /** 등록자아이디 */
    private String rgtrid;
    
}
