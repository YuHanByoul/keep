package com.kbrainc.plum.mng.role.model;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Getter;
import lombok.Setter;

/**
 * ROLE_MENU VO.
 *
 * @FileName : RoleMenuVo.java
 * @Project : airecruit
 * @Date : 2019. 5. 10.
 * @author : KBRAINC
 * @Version : 1.0
 * @Modify :
 * @Description :
 *
 */
@Getter
@Setter
public class RoleMenuVo extends ParentRequestVo {
    
    /** 역할아이디 */
    private String roleid;
    
    /** 메뉴아이디 */
    private String menuid;
    
    /** 수정_일시 */
    private String mdfcnDt;
    
    /** 수정자아이디 */
    private String mdfrid;
    
    /** 등록_일시 */
    private String regDt;
    
    /** 등록자_아이디 */
    private String reguserid;

    /** 메뉴 추가,삭제 용. */
    private String menuAdd;
    private String menuDel;
}
