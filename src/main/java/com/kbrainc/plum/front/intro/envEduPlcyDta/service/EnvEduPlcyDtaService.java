package com.kbrainc.plum.front.intro.envEduPlcyDta.service;

import com.kbrainc.plum.front.intro.envEduPlcyDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.SpcltyDtaVo;

import java.util.List;

/**
 * 환경교육 정책자료실 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.service
 * - EnvEduPlcyDtaService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyDtaService
 * @Description : 환경교육 정책자료실 서비스 인터페이스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface EnvEduPlcyDtaService {
    /**
     * 사업운영자료 목록 조회
     *
     * @param bsnsOperDtaVo
     * @return list
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    public List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 상세 조회
     *
     * @param bsnsOperDtaVo
     * @return bsns oper dta vo
     * @throws Exception
     * @Title : selectBsnsOperDta
     * @Description : 사업운영자료 상세 조회
     */
    public BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

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


}
