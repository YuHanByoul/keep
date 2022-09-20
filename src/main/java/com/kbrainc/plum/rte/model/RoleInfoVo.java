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
 * @Company : Copyright KBRAINC. All Rights Reserved
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
    private String comp_authar_cd;;
    /** 사이트권한적용범위코드 */
    private String site_authar_cd;

    /**
     * @Title : getRole_id
     * @Description : 역할ID를 가져온다.
     * @return String 역할ID
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * @Title : setRole_id
     * @Description : 역할ID를 셋팅한다.
     * @param roleid 역할ID
     * @return void
     */
    public void setRole_id(String roleid) {
        this.roleid = roleid;
    }

    /**
     * @Title : getRole_nm
     * @Description : 역할명을 가져온다.
     * @return String 역할명
     */
    public String geNm() {
        return nm;
    }

    /**
     * @Title : setRole_nm
     * @Description : 역할명을 셋팅한다.
     * @param nm 역할명
     * @return void
     */
    public void setNm(String nm) {
        this.nm = nm;
    }

    /**
     * @Title : getComp_authar_cd
     * @Description : 기관권한적용범위코드를 가져온다.
     * @return String 기관권한적용범위코드
     */
    public String getComp_authar_cd() {
        return comp_authar_cd;
    }

    /**
     * @Title : setComp_authar_cd
     * @Description : 기관권한적용범위코드를 셋팅온다.
     * @param comp_authar_cd 기관권한적용범위코드
     * @return void
     */
    public void setComp_authar_cd(String comp_authar_cd) {
        this.comp_authar_cd = comp_authar_cd;
    }

    /**
     * @Title : getSite_authar_cd
     * @Description : 사이트권한적용범위코드를 가져온다.
     * @return String 사이트권한적용범위코드
     */
    public String getSite_authar_cd() {
        return site_authar_cd;
    }

    /**
     * @Title : setSite_authar_cd
     * @Description : 사이트권한적용범위코드를 셋팅한다.
     * @param site_authar_cd 사이트권한적용범위코드
     * @return void
     */
    public void setSite_authar_cd(String site_authar_cd) {
        this.site_authar_cd = site_authar_cd;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

}