package com.kbrainc.plum.mng.role.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 지역 권한 설정을 위한 Vo클래스.
*
* <pre>
* com.kbrainc.plum.mng.role.model
* - RoleRgnVo.java
* </pre>
*
* @ClassName : RoleRgnVo
* @Description : 지역 권한 설정을 위한 Vo클래스.
* @author : KBRINC
* @date : 2022. 12. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class RoleRgnVo extends ParentRequestVo {
    
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 역할아이디 */
    private String roleid;
    
    /** 배정지역아이디 배열 */
    private String[] rgncds;
}
