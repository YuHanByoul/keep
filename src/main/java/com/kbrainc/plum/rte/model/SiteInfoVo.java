package com.kbrainc.plum.rte.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * 사이트의 정보를 담는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - SiteInfoVo.java
 * </pre> 
 *
 * @ClassName : SiteInfoVo
 * @Description : 사이트의 정보를 담는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class SiteInfoVo extends ParentVo implements Serializable {

    private static final long serialVersionUID = -6600639997454113104L;

    /** 사이트아이디 */
    private String siteid = null;

    /** 사이트명 */
    private String siteNm = null;
    
    /** 시스템_구분_코드 */
    private String sysSeCd = null;
    
    /** 시스템 종류 코드 */
    private String sysKndCd;
    
    /** 기관아이디 */
    private Integer instid;
    
    /** 로고 파일아이디 */
    private Integer logoFileid;
    
    /** 로고 파일 식별 키 */
    private String logoFileIdntfcKey;
    
    /** 카피라이트 */
    private String cpyrht;
    
    /** 도메인 */
    private String dmn = null;

    /**
     * @Title : getSiteid
     * @Description : 사이트아이디를 가져온다.
     * @return String 사이트아이디
     */
    public String getSiteid() {
        return siteid;
    }

    /**
     * @Title : setSiteid
     * @Description : 사이트아이디를 셋팅한다.
     * @param siteid 사이트아이디
     * @return void
     */
    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    /**
     * @Title : getSiteNm
     * @Description : 사이트명을 가져온다.
     * @return String 사이트명
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * @Title : setSiteNm
     * @Description : 사이트명을 셋팅한다.
     * @param siteNm 사이트명
     * @return void
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * @Title : getDmn
     * @Description : 도메인을 가져온다.
     * @return String 도메인
     */
    public String getDmn() {
        return dmn;
    }

    /**
     * @Title : setDmn
     * @Description : 도메인을 셋팅한다.
     * @param dmn 도메인
     * @return void
     */
    public void setDmn(String dmn) {
        this.dmn = dmn;
    }
}