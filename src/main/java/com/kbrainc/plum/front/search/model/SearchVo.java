package com.kbrainc.plum.front.search.model;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

import com.kbrainc.plum.rte.model.ParentRequestVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.util.StringUtil;

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
public class SearchVo extends ParentRequestVo implements Serializable {

    /** 로그인사용자정보 */
    private UserVo user;
    
    /** 검색 대상 그룹 */
    private String gtype;
    
    /** 검색어 */
    private String keyword = "";
    
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
    
    /** 검색기간 시작일 */
    private String startDt;
    
    /** 검색기간 종료일 */
    private String endDt;
    
    /** 검색기간 필터 */
    private String dateFilter;
    
    /** 현재 페이지 */
    private String page;
    
    /** 페이지 노출 수 */
    private String nget;

    public void setStartDt(String startDt) {
        this.startDt = startDt;
        if (!"".equals(startDt) && !"".equals(endDt)) {
            this.dateFilter = String.format("%s,%s", startDt.replaceAll("-", ""), endDt.replaceAll("-", ""));
        }
    }

    /**
    * 상세검색 여부 반환.
    *
    * @Title : getDetailSearch
    * @Description : 상세검색 여부 반환
    * @return boolean 상세검색 여부
    */
    public boolean getDetailSearch() {
        if (!"".equals(StringUtil.nvl(sbjctCd))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(trgtCd))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(typeCd))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(rgnCd))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(keywordNot))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(startDt))) {
            return true;
        } else if(!"".equals(StringUtil.nvl(endDt))) {
            return true;
        }
        
        return false;
    }
    
    /** 로그인사용자정보 */
    public void setUser(UserVo user) {
        UserVo clone = (UserVo) SerializationUtils.clone(user);
        this.user = clone;
    }

    public UserVo getUser() {
        UserVo clone = (UserVo) SerializationUtils.clone(this.user);
        return clone;
    }

    public SearchVo clone() {
       SearchVo clone = (SearchVo) SerializationUtils.clone(this);
       return clone;
    }
}