package com.kbrainc.plum.mng.prtpn.infntPrgrm.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Getter;
import lombok.Setter;


/**
* 유아교육프로그램 -> 교육프로그램관리 VO List 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.model
* - InfntPrgrmVoList.java
* </pre>
**
@ClassName : InfntPrgrmVoList
* @Description : 유아교육프로그램 -> 교육프로그램관리 VO List 클래스
* @author : Notebiz001
* @date : 2023. 1. 10.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Getter
@Setter
public class InfntPrgrmVoList extends ParentRequestVo {
    
    /** InfntPrgrmVo 목록  */
    @Valid
    @NotNull
    @Size(min = 1)
    private List<InfntPrgrmVo> infntPrgrmVoList;
}
