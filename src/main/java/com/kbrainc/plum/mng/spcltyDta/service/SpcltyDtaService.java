package com.kbrainc.plum.mng.spcltyDta.service;

import com.kbrainc.plum.mng.spcltyDta.model.SpcltyDtaVo;

import java.util.List;

/**
 * 전문자료 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.spcltyDta.service
 * - SpcltyDtaService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaService
 * @Description : 전문자료 관리 서비스 인터페이스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SpcltyDtaService {
    /**
     * 전문자료 목록 조회
     *
     * @param spcltyDtaVo
     * @return list
     * @throws Exception
     * @Title : selectSpcltyDtaList
     * @Description : 전문자료 목록 조회
     */
    public List<SpcltyDtaVo> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 상세 조회
     *
     * @param spcltyDtaVo
     * @return spclty dta vo
     * @throws Exception
     * @Title : selectSpcltyDta
     * @Description : 전문자료 상세 조회
     */
    public SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 등록
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : insertSpcltyDta
     * @Description : 전문자료 등록
     */
    public int insertSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 수정
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : updateSpcltyDta
     * @Description : 전문자료 수정
     */
    public int updateSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;

    /**
     * 전문자료 삭제
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteSpcltyDta
     * @Description : 전문자료 삭제
     */
    public int deleteSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception;
}
