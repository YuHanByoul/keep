package com.kbrainc.plum.mng.cnsltng.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * CnsltngExprtGrpVo
 *
 * <pre>
 * com.kbrainc.plum.mng.site.model
 * - CnsltngExprtVo.java
 * </pre>
 *  
 * @ClassName : CnsltngExprtGrpVo
 * @Description : 컨설팅 전문가 그룹 VO
 * @author : KBRAINC
 * @date : 2023. 1. 3.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class CnsltngExprtGrpVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 전문가 그룹 아이디 */
    private int userid;
    
    /** 전문가 그룹 아이디 */
    private int grpId;
    
    /** 전문가 그룹명 */
    private String grpNm;
    
    /** 전문가 그룹 인원수 */
    private String grpExprtCnt;
    
    /** 전문가 그룹 전문가명 */
    private String grpExprtNm;
    
    /** 전문가 그룹 전문가 아이디 */
    private String grpExprtIds;
    
    /** 사용여부 */
    private String useYn;
    
    /** 컨설팅 아이디 */
    private Integer cnsltngid;
    
    /** 심사그룹 인원 세부사항  */
    private String grpExprtStr;
    
    /** 심사그룹 인원 세부사항  */
    private List<CnsltngExprtVo> grpExprtArr;
    
    /** 등록일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String regDt;
    
    /*****검색용 ***********************/
    /** 등록자아이디 */
    private String searchGrpNm;
    
}