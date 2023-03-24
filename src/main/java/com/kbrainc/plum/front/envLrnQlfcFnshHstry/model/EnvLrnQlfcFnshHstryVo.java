package com.kbrainc.plum.front.envLrnQlfcFnshHstry.model;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.EnvLrnQlfcFnshHstryVo")
public class EnvLrnQlfcFnshHstryVo extends ParentRequestVo {
    
    private UserVo user;
    
    /** 수료정보아이디 */
    private String fnshid;
    /** 교육구분 */
    private String eduType;
    /** 교육명 */
    private String eduNm;
    /** 분류 */
    private String eduForm;
    /** 수료번호 */
    private String fnshRsltCode;
    /** 수료일 */
    private String fnshPrcsDt;
    /** 증명서 */
    private String fnshUrl;
    
    /** 교육기간 */
    private String eduDt;
    
    /** 자격명 */
    private String qlfcNm;
    /** 자격증번호 */
    private String qlfcRsltCode;
    /** 취득일 */
    private String lcncAcqsYmd;
    
    /** 취득기관 */
    private String acqsInst;
    
}