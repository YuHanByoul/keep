package com.kbrainc.plum.rte.model;

import java.io.Serializable;

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
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Data
public class SiteInfoVo extends ParentVo implements Serializable {

    private static final long serialVersionUID = -6600639997454113104L;

    // 사이트정보
    /** 사이트아이디 */
    private String siteid = null;
    /** 사이트명 */
    private String site_nm = null;
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
     * @Title : getSite_nm
     * @Description : 사이트명을 가져온다.
     * @return String 사이트명
     */
    public String getSite_nm() {
        return site_nm;
    }

    /**
     * @Title : setSite_nm
     * @Description : 사이트명을 셋팅한다.
     * @param site_nm 사이트명
     * @return void
     */
    public void setSite_nm(String site_nm) {
        this.site_nm = site_nm;
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