package com.kbrainc.plum.mng.qlityChk.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 콘텐츠 품질 체크리스트 Vo 클래스
*
* <pre>
* com.kbrainc.plum.mng.qlityChk.model
* - QlityChklstVo.java
* </pre>
*
* @ClassName : QlityChklstVo
* @Description : 콘텐츠 품질 체크리스트 Vo 클래스
* @author : JD
* @date : 2023. 4. 18.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class QlityChklstVo  extends ParentRequestVo{
    
    private UserVo user;
    
    /** 체크리스트아이디 */
    private String chklstid;
    /** 분야코드 */
    private String fldCd;
    /** 순서 */
    private String ordr;
    /** 내용 */
    private String cn;
    /** 수정_일시 */
    private String mdfcnDt;
    /** 수정자아이디 */
    private String mdfrid;
    /** 등록_일시 */
    private String regDt;
    /** 등록자아이디 */
    private String rgtrid;
    
    private Integer count;
    private String fldCdNm;
    
}
