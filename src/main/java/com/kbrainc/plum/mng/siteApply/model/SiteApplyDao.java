package com.kbrainc.plum.mng.siteApply.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.member.model.MemberVo;

@Mapper
public interface SiteApplyDao {
    
    /**
    * 사이트 신청 리스트 호출 
    *
    * @Title       : selectSiteApplyList 
    * @Description :사이트 신청 리스트 호출
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<SiteApplyVo> selectSiteApplyList(SiteApplyVo siteApplyVo) throws Exception;
    /**
     * 사이트 신청 상세정보 호출 
     *
     * @Title       : selectSiteApplyInfo 
     * @Description : 사이트 신청 상세정보 호출
     * @param SiteApplyVo SiteApplyVo객체
     * @return SiteApplyVo
     * @throws Exception 예외
     */
    public SiteApplyVo selectSiteApplyInfo(SiteApplyVo siteApplyVo) throws Exception;
    /**
     * 사이트 신청 상태 수정 
     *
     * @Title       : updateSiteApplyStatus 
     * @Description : 사이트 신청 상태 수정
     * @param SiteApplyVo SiteApplyVo객체
     * @return List<SiteApplyVo>
     * @throws Exception 예외
     */
    public int updateSiteApplyStatus(SiteApplyVo siteApplyVo) throws Exception;
    /**
    * 사이트 신청 메뉴 리스트 호출 
    *
    * @Title       : selectSiteApplyMenuList 
    * @Description :사이트 신청 메뉴 리스트 호출
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<SiteApplyMenuVo> selectSiteApplyMenuList(SiteApplyVo siteApplyVo) throws Exception;
    
}