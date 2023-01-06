package com.kbrainc.plum.mng.cnsltng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface CnsltngDao {
    
    /**
    * 컨설팅 리스트 호출 
    *
    * @Title       : selectCnsltngList 
    * @Description :컨설팅 리스트 호출 
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<CnsltngVo> selectCnsltngList(CnsltngVo consultVo) throws Exception;
    /**
     * 컨설팅 상세정보 호출 
     *
     * @Title       : selectCnsltngtInfo 
     * @Description : 컨설팅 상세정보 호출 
     * @param SiteApplyVo SiteApplyVo객체
     * @return SiteApplyVo
     * @throws Exception 예외
     */
    public CnsltngVo selectCnsltngtInfo(CnsltngVo consultVo) throws Exception;
    /**
     * 사이트 신청 상태 수정 
     *
     * @Title       : updateSiteApplyStatus 
     * @Description : 사이트 신청 상태 수정
     * @param SiteApplyVo SiteApplyVo객체
     * @return List<SiteApplyVo>
     * @throws Exception 예외
     */
    public int updateConsultStatus(CnsltngVo consultVo) throws Exception;
}