package com.kbrainc.plum.mng.consult.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ConsultDao {
    
    /**
    * 사이트 신청 리스트 호출 
    *
    * @Title       : selectSiteApplyList 
    * @Description :사이트 신청 리스트 호출
    * @param SiteApplyVo SiteApplyVo객체
    * @return List<SiteApplyVo>
    * @throws Exception 예외
    */
    public List<ConsultVo> selectConsultList(ConsultVo consultVo) throws Exception;
    /**
     * 사이트 신청 상세정보 호출 
     *
     * @Title       : selectSiteApplyInfo 
     * @Description : 사이트 신청 상세정보 호출
     * @param SiteApplyVo SiteApplyVo객체
     * @return SiteApplyVo
     * @throws Exception 예외
     */
    public ConsultVo selectConsultInfo(ConsultVo consultVo) throws Exception;
    /**
     * 사이트 신청 상태 수정 
     *
     * @Title       : updateSiteApplyStatus 
     * @Description : 사이트 신청 상태 수정
     * @param SiteApplyVo SiteApplyVo객체
     * @return List<SiteApplyVo>
     * @throws Exception 예외
     */
    public int updateConsultStatus(ConsultVo consultVo) throws Exception;
}