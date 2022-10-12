package com.kbrainc.plum.rte.model;

/**
 * 
 * 역할정보를 담는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.model
 * - RoleInfoVo.java
 * </pre> 
 *
 * @ClassName : RoleInfoVo
 * @Description : 역할정보를 담는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class RoleInfoVo extends ParentVo {

    private static final long serialVersionUID = -2981250066670198530L;
    /** 역할ID */
    private String roleid;
    /** 역할명 */
    private String nm;
    /** 역할구분코드 */
    private String se;
    /** 기관권한적용범위코드 */
    private String compAutharCd;
    /** 사이트권한적용범위코드 */
    private String siteAutharCd;

    /**
     * @Title : getRoleid
     * @Description : 역할ID를 가져온다.
     * @return String 역할ID
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @Title : setRoleid
     * @Description : 역할ID를 셋팅한다.
     * @param roleid 역할ID
     * @return void
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @Title : getNm
     * @Description : 역할명을 가져온다.
     * @return String 역할명
     */
    public String geNm() {
        return nm;
    }

    /**
     * @Title : setNm
     * @Description : 역할명을 셋팅한다.
     * @param nm 역할명
     * @return void
     */
    public void setNm(String nm) {
        this.nm = nm;
    }

    /**
     * @Title : getCompAutharCd
     * @Description : 기관권한적용범위코드를 가져온다.
     * @return String 기관권한적용범위코드
     */
    public String getCompAutharCd() {
        return compAutharCd;
    }

    /**
     * @Title : setCompAutharCd
     * @Description : 기관권한적용범위코드를 셋팅온다.
     * @param comp_authar_cd 기관권한적용범위코드
     * @return void
     */
    public void setCompAutharCd(String compAutharCd) {
        this.compAutharCd = compAutharCd;
    }

    /**
     * @Title : getSiteAutharCd
     * @Description : 사이트권한적용범위코드를 가져온다.
     * @return String 사이트권한적용범위코드
     */
    public String getSiteAutharCd() {
        return siteAutharCd;
    }

    /**
     * @Title : setSiteAutharCd
     * @Description : 사이트권한적용범위코드를 셋팅한다.
     * @param siteAutharCd 사이트권한적용범위코드
     * @return void
     */
    public void setSiteAutharCd(String siteAutharCd) {
        this.siteAutharCd = siteAutharCd;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

}