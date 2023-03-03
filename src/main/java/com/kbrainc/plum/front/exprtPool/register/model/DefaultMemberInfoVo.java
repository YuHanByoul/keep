package com.kbrainc.plum.front.exprtPool.register.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 회원 기본정보Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - DefaultMemberInfoVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DefaultMemberInfoVo
 * @Description : 회원 기본정보Vo 클래스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.DefaultMemberInfoVo")
public class DefaultMemberInfoVo {
    private String nm;
    private String gndr;
    private String moblphon;
    private String eml;
    private String addr;
    private String addrDtl;
    private String exprtSttsCd;
    private String telno;
    private String brdt;
}
