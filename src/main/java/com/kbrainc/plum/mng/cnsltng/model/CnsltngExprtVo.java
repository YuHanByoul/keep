package com.kbrainc.plum.mng.cnsltng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 * 
 * CnsltngExprtVo
 *
 * <pre>
 * com.kbrainc.plum.mng.site.model
 * - CnsltngExprtVo.java
 * </pre> 
 *
 * @ClassName : CnsltngExprtVo
 * @Description : 컨설팅 전문가 VO
 * @author : KBRAINC
 * @date : 2023. 1. 3.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CnsltngExprtVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 전문가 아이디 */
    private int userid;
    
    /** 컨설팅 아이디 */
    private Integer cnsltngid;
    
    /** 전문가 계정 */
    private String acnt;
    
    /** 전문가 이름  */
    private String nm;
    
    /** 전문가 이메일 */
    private String eml;
    
    /** 전문가 핸드폰 번호 */
    private String moblphon;
    
    /** 전문가 자격증 명 */
    private String crtfctNm;
    
    /** 전문가 분야 코드 */
    private String sbjctCd;
    
    /** 전문가 분야 명*/
    private String sbjctNm;
    
    /** 전문가 활동 지역코드 */
    private String rgnCd;
    
    /** 전문가 활동 지역명 */
    private String rgnNm;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /*****검색용 ***********************/
    /** 등록자아이디 */
    private String searchNm;
    
    /** 등록자아이디 */
    private String searchCrtfctNm;
    
    /** 등록자아이디 */
    private String searchSbjctCd;
    
    /** 등록자아이디 */
    private String searchRgnCd;
    
}
