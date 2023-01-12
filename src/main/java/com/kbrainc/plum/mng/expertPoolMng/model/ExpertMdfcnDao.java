package com.kbrainc.plum.mng.expertPoolMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 전문가 정보변경 Dao 인터페이스
 * 
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertMdfcnDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertMdfcnDao
 * @Description : 전문가 정보변경 Dao 인터페이스
 * @date : 2023. 01. 12.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ExpertMdfcnDao {
    public List<ExpertMdfcnVo> selectExpertMdfcnList(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public ExpertVo selectExpertMdfcnInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public List<ExpertCareerVo> selectExpertCareerList(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public List<ExpertCrtfctVo> selectExpertCrtfctList(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public List<ExpertHdofVo> selectExpertHdofList(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public ExpertMdfcnVo selectExpertMdfcn(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public boolean updateSttsCd(ExpertMdfcnVo expertMdfcnVo) throws Exception;


}
