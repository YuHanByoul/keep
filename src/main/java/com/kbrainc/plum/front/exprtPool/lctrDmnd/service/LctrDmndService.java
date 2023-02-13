package com.kbrainc.plum.front.exprtPool.lctrDmnd.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;

import java.util.List;

/**
 * 환경교육 전문가 풀 > 섭외 요청 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.service
 * - LctrDmndService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndService
 * @Description : 환경교육 전문가 풀 > 섭외 요청 서비스 인터페이스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface LctrDmndService {
    /**
     * 전문가 목록 조회
     *
     * @param searchVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    public List<ExprtVo> selectExprtList(ExprtVo searchVo) throws Exception;

}
