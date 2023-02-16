package com.kbrainc.plum.front.exprtPool.lctrDmnd.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.*;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;

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

    /**
     * 전문가 상세 조회
     *
     * @param exprtVo
     * @return exprt vo
     * @throws Exception
     * @Title : selectExprt
     * @Description : 전문가 상세 조회
     */
    public ExprtVo selectExprt(ExprtVo exprtVo) throws Exception;

    /**
     * 전문가 섭외 요청 등록
     *
     * @param lctrDmndVo
     * @return int
     * @throws Exception
     * @Title : insertLctrDmnd
     * @Description : 전문가 섭외 요청 등록
     */
    public int insertLctrDmnd(LctrDmndVo lctrDmndVo) throws Exception;

    /**
     * 관심인력 등록
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : insertLtrstExprt
     * @Description : 관심인력 등록
     */
    public int insertItrstExprt(ExprtVo exprtVo) throws Exception;

    /**
     * 이미 등록된 관심인력인지 확인
     *
     * @param exprtVo
     * @return boolean
     * @throws Exception
     * @Title : checkAlreadyRegistedInrstExprt
     * @Description : 이미 등록된 관심인력인지 확인
     */
    public boolean checkAlreadyRegistedItrstExprt(ExprtVo exprtVo) throws Exception;

    /**
     * 관심인력 삭제
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : deleteItrstExprt
     * @Description : 관심인력 삭제
     */
    public int deleteItrstExprt(ExprtVo exprtVo) throws Exception;

}
