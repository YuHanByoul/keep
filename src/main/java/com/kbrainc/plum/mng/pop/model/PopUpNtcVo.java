package com.kbrainc.plum.mng.pop.model;/** **/

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 
 * 팝업 공지 VO 
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.model
 * - PopUpNtcVo.java
 * </pre> 
 *
 * @ClassName : PopUpNtcVo
 * @Description : PopUpNtcVo
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class PopUpNtcVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** **/

    /** 팝업공지아이디**/
    private int popupntcid;
    
    /** 사이트 아이디*/
    private Integer siteid;
    
    /** 제목*/
    @NotEmpty(message = "제목을 입력해주십시오.")
    @Size(max = 500, message = "제목은 100자를 넘을 수 없습니다.")
    private String title;
    
    /** 내용*/
    @NotEmpty(message = "내용을 입력해주십시오.")
    @Size(max = 20000, message = "내용은 20000자를 넘을 수 없습니다.")
    private String cntnts;
    
    
    /** 노출 위치 코드 */
    @Size(max = 20, message = "노출 위치 코드는 20000자를 넘을 수 없습니다.")
    private String expsrLcCd;
    
    /** 노출 위치 코드 */
    private Integer menuid;
    
    /** 로그인_필요_여부 */
    @Pattern(regexp="[YN]")    
    private String loginNeedYn;
    
    /** 팝업_유형_코드 */
    @Size(max = 20, message = "팝업 유형 코드는 20000자를 넘을 수 없습니다.")
    private String popupTypeCd;
    
    /** 시작일자 */
    private String strtDt;
    
    /** 종료일자 */
    private String endDt;
    
    /** 가로_크기 */
    private Integer widthSize;
    
    /** 세로_크기 */
    private Integer vrticlSize;
    
    /**TOP_위치*/
    private Integer topLc;
    
    /**LEFT_위치*/
    private Integer leftLc;
    
    /**공지_대상_코드*/
    @Size(max = 20, message = "팝업 유형 코드는 20000자를 넘을 수 없습니다.")
    private String ntcTrgtCd;
    
    /**공지_대상_역할아이디*/
    private Integer ntcTrgtRoleid;
    
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

    /*******검색용 추가*******/
    /*** 검색용 기간상태 코드****/
    private String searchTermSttsCd;
    
    /*** 검색용 사이트 코드****/
    private String serachSiteid;
    
    /*** 검색 노출 위치 코드****/
    private String searchExpsrLcCd;
    
    /*** 메뉴명 ****/
    private String menuNm;
    
    /*** 수정명 ****/
    private String updNm;
    
    /*** 역할명 ****/
    private String roleNm;
    
    /*** 진행상태 ****/
    private String popSts;
    
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user){
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }
    public UserVo getUser(){
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return  clone;
    }   


}