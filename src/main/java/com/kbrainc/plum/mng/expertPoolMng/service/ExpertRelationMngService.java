package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.mng.expertPoolMng.model.ExpertRelationMngDao;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertRelationVo;

import java.util.List;

/**
 * 전문가 섭외 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertRelationMngService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertRelationMngService
 * @Description : 전문가 섭외 관리 서비스 인터페이스
 * @date : 2023. 01. 05.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface ExpertRelationMngService {

    public List<ExpertRelationVo> selectExpertRelationList(ExpertRelationVo expertRelationVo) throws Exception;

    public ExpertRelationVo selectExpertRelationInfo(ExpertRelationVo expertRelationVo) throws Exception;
}
