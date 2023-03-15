package com.kbrainc.plum.front.lend.model;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.CodeInfoVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.ResCodeService;
import com.kbrainc.plum.rte.util.CommonUtil;

import lombok.Data;

/**
 *
 * 알림 VO
 *  
 * <pre>
 * com.kbrainc.plum.fornt.lend.model
 * - LendAplyTrgtVo.java
 * </pre> 
 *
 * @ClassName : LendAplyTrgtVo
 * @Description : 대여 신청 대상 VO
 * @author : KBRAINC
 * @date : 2023. 03. 07.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
@Alias("front.lendAplyTrgtVo")
public class LendAplyTrgtVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 대여 신청 아이디 */
    private Integer aplyid;
    
    /** 대여 차시 아이디 */
    private Integer rndid;
    
    /** 수량 */
    private Integer qnty;
    
    /** 대여 시작일   */
    @Size(max = 10, message = "대여 시작일은 10자를 넘을 수 없습니다.") 
    private String bgngDe;
    
    /** 대여 종료일*/
    @Size(max = 10, message = "대여 종료일은 10자를 넘을 수 없습니다.") 
    private String endDe;
    
    /** 등록_일시 */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date regDt;
    
    /** 등록자아이디 */
    private Integer rgtrid;
    
    /** 꾸러미 개체 명 */
    private String packageindvdNm;
    
    /** 꾸러미 개체 번호  */
    private String indvdno;
    
    /** 연체 여부 */
    private String isLateYn;
    
    /** 출고 처리 일시  */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dlivyPrcsDt;
    
    /** 입고 처리 일시  */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date wrhousngPrcsDt;
    
}