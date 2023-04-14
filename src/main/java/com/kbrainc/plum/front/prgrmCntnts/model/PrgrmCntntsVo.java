package com.kbrainc.plum.front.prgrmCntnts.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;

import lombok.Data;

@Data
@Alias("front.PrgrmCntntsVo")
public class PrgrmCntntsVo extends ParentRequestVo {
    
    private Integer id;
    private String type;
    private Integer fileid;
    private String fileKey;
    private String eduTrgt;
    private String instNm;
    private String ttl;
    private String eduSbjct;
    private String etc1;
    private String etc2;
    private Date regDt;
    
    // filter
    // 검색 타입
    private char searchPrgrm;
    private char searchCntnts;
    // 교육주제
    private String searchMainEduSbjct;
    private String searchSubEduSbjct;
    // 콘텐츠 유형
    private String searchMainCntntsType;
    private String searchSubCntntsType;
    // 지역
    private String searchRgn;
    // 교육대상(프로그램)
    private String[] searchCntntsEduTrgt;
    // 교육대상(콘텐츠)
    private String[] searchPrgrmEduTrgt;
}
