package com.kbrainc.plum.mng.lend.model;

import java.util.Date;
import java.util.List;

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
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.lend.model
* - LendRndVo.java
* </pre>   
*
* @ClassName   : LendRndVo 
* @Description : TODO 
* @author      : KBRAINC
* @date        : 2023.02.20
* @Version     : 
* @Company     : CopyrightⒸ KBRAINC. All Rights Reserved
*/
@Data
public class LendRndVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 차시  아이디 */
    private Integer rndid;
    
    /** 대여  아이디 */
    private Integer rcritid;
    
    /** 순서 */
    private Integer ordr;
    
    /** 대여 시작일   */
    @Size(max = 10, message = "대여 시작일은 10자를 넘을 수 없습니다.") 
    private String bgngDe;
    
    /** 대여 종료일*/
    @Size(max = 10, message = "대여 종료일은 10자를 넘을 수 없습니다.") 
    private String endDe;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    /** 등록자아이디 */
    private String rgtrAcnt;
    
    /**  수정_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;
    
    /** 상세용 대여 차시 개체 리스트  */
    private List<LendRndPackageindvdVo> lendRndPackageindvdVoList ;
    
    /** 등록용 대여차시꾸러미개체 아이디(s) */
    private String [] lendRndPackageindvdids ;
    
    /** 삭제용   */
    private List<Integer> deleteIds ;
    
    
    
    
}