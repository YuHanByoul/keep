package com.kbrainc.plum.mng.bizAply.pcntst.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngExprtVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * CnsltngExprtGrpVo
 *
 * <pre>
 * com.kbrainc.plum.mng.bizAply.pcntst.model
 * - CnsltngExprtVo.java
 * </pre>
 *  
 * @ClassName : PublicContestMngGrpVo
 * @Description : 전문가 그룹 VO
 * @author : KCS
 * @date : 2023. 1. 25.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class PublicContestMngGrpVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 전문가 그룹 아이디 */
    private int userid;
    
    /** 전문가 아이디 */
    private int jdgsid;
    
    /** 이름 */
    private String nm;
    
    /** 휴대폰 번호 */
    private String moblphon;
    
    /** 이메일 */
    private String eml;
    
    /** 계정 */
    private String acnt;
    
    /** 차수 */
    private int cycl;
    
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
    
    /**  공모아이디 */
    private Integer pcntstid;
    
    /** 심사그룹 인원 세부사항  */
    private String grpExprtStr;
    
    /** 담당자 ids */
    private List<String> pcntstids;
    
    
    /** 등록일 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private String regDt;
    
    /*****검색용 ***********************/
    /** 등록자아이디 */
    private String searchGrpNm;
    
}