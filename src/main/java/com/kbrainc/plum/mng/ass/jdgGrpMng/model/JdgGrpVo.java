package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class JdgGrpVo extends ParentRequestVo {
    private UserVo user;
    private Integer grpId;
    private String grpNm;
    private String useYn;
    private Integer exprtCnt;
    private String searchUseYn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDt;
    private Date mdfcnDT;
}
