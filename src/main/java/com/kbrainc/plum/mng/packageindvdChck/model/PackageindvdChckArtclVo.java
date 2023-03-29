package com.kbrainc.plum.mng.packageindvdChck.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 체크리스트 항목
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.model
 * - PackageindvdChckArtclVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckArtclVo
 * @Description : 체크리스트 항목
 * @date : 2023. 03. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
public class PackageindvdChckArtclVo {
    private Integer artclid;
    private Integer formid;
    private Integer ordr;
    private String cn;
    private String exCn;
    private List<String> artclExList = new ArrayList<>();
    private List<Integer> checkedExList = new ArrayList<>();
    private List<String> actnMttrList = new ArrayList<>();
}
