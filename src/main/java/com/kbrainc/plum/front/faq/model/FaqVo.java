package com.kbrainc.plum.front.faq.model;

import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslDao;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.SiteInfoVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.annotation.Resource;

/**
 * 자주묻는 질문 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.faq.model
 * - FaqVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : FaqVo
 * @Description : 자주묻는 질문Vo 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.FaqVo")
@Data
public class FaqVo extends ParentRequestVo {
    private SiteInfoVo site;
    private Integer faqid;
    private Integer siteid;
    private Integer clid;
    private String title;
    private String cntnts;
    private int ord;
    private String clNm;
}
