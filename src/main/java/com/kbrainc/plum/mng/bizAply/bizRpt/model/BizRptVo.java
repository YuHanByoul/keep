package com.kbrainc.plum.mng.bizAply.bizRpt.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 사업보고VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.model
* - BizRptVo.java
* </pre>
*
* @ClassName : BizRptVo
* @Description : 사업보고VO 클래스
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class BizRptVo extends ParentRequestVo {
	/** 로그인사용자정보 */
    private UserVo user;
}
