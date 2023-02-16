package com.kbrainc.plum.front.exprtPool.lctrDmnd.model;

import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 환경교육 전문가 풀 > 섭외 요청 Vo 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.model
 * - LctrDmndVo.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndVo
 * @Description : 환경교육 전문가 풀 > 섭외 요청 Vo 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.LctrDmndVo")
public class LctrDmndVo {
    private UserVo user;

    private Integer exprtid;

    @NotEmpty(message="제목을 입력해 주십시오.")
    @Size(max = 100, message="제목은 100자를 넘을 수 없습니다.")
    private String ttl;

    @NotEmpty(message="강의 시작 일시를 입력해 주십시오.")
    private String lctrBgngDt;

    @NotEmpty(message="강의 종료 일시를 입력해 주십시오.")
    private String lctrEndDt;

    private String zip;

    @NotEmpty(message="주소를 입력해 주십시오.")
    @Size(max = 200, message="주소는 200자를 넘을 수 없습니다.")
    private String addr;

    @NotEmpty(message="상세주소를 입력해 주십시오.")
    @Size(max = 400, message="상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;

    @NotEmpty(message="휴대전화를 입력해 주십시오.")
    @Size(max = 12, message="휴대전화는 12자를 넘을 수 없습니다.")
    private String moblphon;

    @NotEmpty(message="이메일을 입력해 주십시오.")
    @Size(max = 200, message="이메일은 200자를 넘을 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String eml;

    @NotEmpty(message="내용을 입력해 주십시오.")
    @Size(max = 1000, message="내용은 1000자를 넘을 수 없습니다.")
    private String dmndCn;

    private Integer filegrpid;

    @NotEmpty(message="이메일 도메인을 입력해 주십시오.")
    @Size(max = 100, message="이메일 도메인은 100자를 넘을 수 없습니다.")
    private String emailDomain;

    @NotEmpty(message="이메일 아이디를 입력해 주십시오.")
    @Size(max = 100, message="이메일 아이디는 100자를 넘을 수 없습니다.")
    private String emailLocal;

}
