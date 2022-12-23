package com.kbrainc.plum.mng.inqry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class TelInqryVo extends ParentRequestVo {
    private String clsfCdGrpid = "122";

    private String sttsCdGrpid = "123";

    private UserVo user;

    private Integer telinqryid;

    private String clsfCd;

    private String clsfCdNm;

    private Integer userid;

    private Integer picid;

    private String picNm;

    @NotEmpty
    @Size(max = 20, message = "회원명은 20자를 넘을 수 없습니다.")
    private String userNm;

    @Size(max = 40, message = "연락처는 40자를 넘을 수 없습니다.")
    private String cntct;

    @Size(max = 200, message = "이메일은 200자를 넘을 수 없습니다.")
    @Email(message = "이메일 형식이 올바르지않습니다.")
    private String eml;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss")
    private Date rcptDt;

    private String sttsCd;

    private String sttsCdNm;

    @Size(max = 600, message = "문의 내용은 600자를 넘을 수 없습니다.")
    private String cn;

    @Size(max = 600, message = "답변 내용은 600자를 넘을 수 없습니다.")
    private String ans;

    private Date mdfcnDt;

    private Integer mdfrid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDt;

    private Integer rgtrid;

    private String ansDe;

    private Date anscmptnDt;

    private String oldSttsCd;

    private Integer[] managerId;

    private String assignYn;

    private String searchInst;

    private String searchInst2;
}
