package com.kbrainc.plum.mng.wbzn.now.banner.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
* 환경교육NOW -> 배너관리 VO 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.now.banner.model
* - EnvBannerVo.java
* </pre>
*
* @ClassName : EnvBannerVo
* @Description : 환경교육NOW -> 배너관리 VO 클래스
* @author : JD
* @date : 2022. 12. 29.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Data
public class EnvBannerVo extends ParentRequestVo {
    /** 로그인사용자정보 */
    private UserVo user;
    
    private List<EnvBannerVo> bannerVoList;
    
    /** 배너 아이디 */
    private int bannerid;
    
    /** 분류_코드 */
    private String clsfCd;
    
    /** 배너_위치_코드 */
    private String bannerPstnCd;
    
    /**  배너_제목 */
    @NotEmpty(message = "제목을 입력해주십시오.")
    private String bannerTtl;
    
    /** 배너_파일아이디 */
    private int bannerFileid;
    
    /**  배너_제목 */
    private String url;
    
    /** 순서 */
    private int ordr;

    /** 노출_여부 */
    private char expsrYn;
    
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
    
    /** 첨부파일 관련 */
    private String filegrpid;
    private String fileIdntfcKey;
    private String orginlFileNm;
}
