package com.kbrainc.plum.front.mypage.exprtPool.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;

import java.util.List;

/**
 * 전문가 정보 수정Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.model
 * - MyExprtMdfcnVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtMdfcnVo
 * @Description : 전문가 정보 수정Vo 클래스
 * @date : 2023. 03. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class MyExprtMdfcnVo extends CommonExprtVo {
    private String infoMdfcnRsn;
    private Integer mdfcnDmndId;
    private String sbjctCds;
    private String actvtScopeCds;
    private String trgtCds;
    private String actvtRgnCds;
}
