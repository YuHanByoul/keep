package com.kbrainc.plum.mng.employ.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
public class EmployVo extends ParentRequestVo {

    private UserVo user;

    @JsonFormat(shape = JsonFormat.Shape.STRING,  pattern = "yyyy-MM-dd")
    private Date regDt;

    private String regNm;

    @NotEmpty
    @Size(max = 250, message = "제목은 250자를 넘을 수 없습니다.")
    private String title;

    @NotEmpty
    @Size(max = 4000, message = "내용은 4000자를 넘을 수 없습니다.")
    private String cntnts;

    private String siteNm;

    private String instNm;

    @NotEmpty
    private Integer siteid;

    private Integer pstid;

    private String fxdNtcYn;

    private String loginYn;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fxdNtcStrtDt;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fxdNtcEndDt;

    private String searchSite;

    private Integer bbsid;

    private String bbsClid;

    private Integer filegrpid;
}
