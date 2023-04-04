package com.kbrainc.plum.front.search.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.Alias;

import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;

import lombok.Data;

/**
 * 통합검색VO 클래스.
 *
 * <pre>
 * com.kbrainc.plum.front.search.model - SearchVo.java
 * </pre>
 *
 * @ClassName : SearchVo
 * @Description : 통합검색Vo 클래스
 * @author : KBRAINC
 * @date : 2023. 4. 03.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Data
public class SearchVo extends ParentRequestVo {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 검색 대상 그룹 */
    private String gtype;
    
    /** 검색 개체 그룹 */
    private String etype;
    
    /** 검색어 */
    private String keyword;
    
    /** 교육주제코드 */
    private String sbjctCd;
    
    /** 교육대상코드 */
    private String trgtCd;
    
    /** 교육유형코드 */
    private String typeCd;
    
    /** 지역코드 */
    private String rgnCd;
    
    /** 검색제외단어 */
    private String keywordNot;
    
    /** 정렬순서 */
    private String chkSort;
    
    /** 검색기간 */
    private String dateFilter;
    
    /** 현재 페이지 */
    private String page;
    
    /** 페이지 노출 수 */
    private String nget;
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }

    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return clone;
    }
}