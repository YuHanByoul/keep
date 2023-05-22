package com.kbrainc.plum.mng.prtpn.eduSarea.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.mng.qestnr.model.QitemExVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 유아권역 -> 권역관리 VO 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.eduSarea.model
* - EduSareaVo.java
* </pre>
**
@ClassName : EduSareaVo
* @Description : 유아권역 -> 권역관리  VO 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class EduSareaVo extends ParentRequestVo {
    
    /** 로그인 사용자 정보*/
    private UserVo user;
    
    /** 권역아이디 */
    private int sareaId;
    /** 권역_이름 */
    private String sareaNm;
    /** 기관아이디 */
    private String cnsgnInstId;
    /** 기관명 */
    private String cnsgnInstNm;
    /** 설명 */
    private String expln;
    /** 신청_제한_횟수 */
    private String aplyLmtCnt;
    /** 지역명 */
    private String ctprvnNm;
    /** 사용_여부 */
    private String useYn;
    /** 지역코드 */
    private String ctprvnCd;
    /** 지역코드 */
    private String ctprvnGrp;
    /** 지역코드 저장용 */
    private List<String> ctprvnCds = new ArrayList<>();
    /** 시군구코드 */
    private String signguCd;
    /** 시군구코드명 */
    private String signguNm;
    /** 시간구분코드 */
    private String hrSeCd;
    /** 세부지역설정 목록 */
    private List<EduSareaVo> signguList;

    
    /** 수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    /** 수정자아이디 */
    private int mdfrid;
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    /** 등록자아이디 */
    private int rgtrid;    
    
    /** 작성자 */
    private String nm;
    

}
