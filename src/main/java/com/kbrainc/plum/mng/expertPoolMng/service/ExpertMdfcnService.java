package com.kbrainc.plum.mng.expertPoolMng.service;

import com.kbrainc.plum.mng.expertPoolMng.model.ExpertLogVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertMdfcnVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;

import java.util.List;

/**
 * 전문가 정보변경 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.expertPoolMng.service
 * - ExpertMdfcnService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExpertMdfcnService
 * @Description : 전문가 정보변경 서비스 인터페이스
 * @date : 2023. 01. 12.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */

public interface ExpertMdfcnService {
    public List<ExpertMdfcnVo> selectExpertMdfcnList(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public ExpertVo selectExistingExpertInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public ExpertVo selectNewExpertInfo(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public ExpertMdfcnVo selectExpertMdfcn(ExpertMdfcnVo expertMdfcnVo) throws Exception;

    public int updateStts(ExpertMdfcnVo expertMdfcnVo, ExpertLogVo expertLogVo) throws Exception;
}
