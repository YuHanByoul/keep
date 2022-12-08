package com.kbrainc.plum.mng.role.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Getter;
import lombok.Setter;

/**
* 사용자들에게 역할을 부여하기 위한 Vo클래스.
*
* <pre>
* com.kbrainc.plum.mng.role.model
* - RoleUserVoList.java
* </pre>
*
* @ClassName : RoleUserVoList
* @Description : 사용자들에게 역할을 부여하기 위한 Vo클래스.
* @author : KBRINC
* @date : 2022. 12. 8.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Getter
@Setter
public class RoleUserVoList extends ParentRequestVo {
    
    /** RoleUserVo 목록  */
    @Valid
    @NotNull
    @Size(min = 1)
    private List<RoleUserVo> roleUserVoList;
}
