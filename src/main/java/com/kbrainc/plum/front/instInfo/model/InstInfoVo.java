package com.kbrainc.plum.front.instInfo.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

@Data
@Alias("front.InstInfoVo")
public class InstInfoVo extends ParentRequestVo {
    
    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 기관 아이디 */
    private Integer instid;
    
    /** 기관 이름 */
    private String instNm;
    
    /** 기관코드 */
    private String instCd;
    
    /** 기관유형코드 */
    private String instTypeCd;
    
    /** 기관유형코드명 */
    private String instTypeCdNm;
    
    /** 지역 코드 */
    private String rgnCd;
    
    /** 사업자번호 */
    private String brno;
    
    /** 사업자번호 */
    private String strBrno;
    
    /** 대표자이름 */
    private String rprsvNm;
    
    /** 전화번호 */
    private String telno;
    
    /** 팩스번호 */
    private String fxno;
    
    /** 우편번호 */
    private String zip;
    
    /** 주소 */
    private String addr;
    
    /** 주소_상세 */
    @Size(max = 400, message = "상세주소는 400자를 넘을 수 없습니다.")
    private String addrDtl;
    
    /** 홈페이지 */
    @Size(max = 100, message = "홈페이지는 100자를 넘을 수 없습니다.")
    private String hmpg;
    
    /** 로고파일 아이디 */
    private Integer logoFileid;
    
    /** 첨부 파일 그룹아이디 */
    private Integer filegrpid;
    
    /** 승인 상태 코드 */
    private String aprvSttsCd;
    
    /** 승인 상태 코드명 */
    private String aprvSttsCdNm;
    
    /**  수정_일시 */
    private Date mdfcnDt;
    
    /** 수정자아이디 */
    private int mdfrid;    
    
    /** 등록_일시 */
    private Date regDt;
    
    /** 등록자아이디 */
    private int rgtrid;
    
    private FileVo fileInfo;
    
}