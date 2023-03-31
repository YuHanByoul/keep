package com.kbrainc.plum.mng.bsnsOperDta.service;

import com.kbrainc.plum.mng.bsnsOperDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.mng.inst.model.InstVo;

import java.util.List;

/**
 * 사업운영자료 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.bsnsOperDta.service
 * - BsnsOperDtaService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaService
 * @Description : 사업운영자료 관리 서비스 인터페이스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface BsnsOperDtaService {
    /**
     * 사업운영자료 목록 조회
     *
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
     * 사업운영자료 등록
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : insertBsnsOperDta
     * @Description : 사업운영자료 등록
     */
    public int insertBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 수정
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : updateBsnsOperDta
     * @Description : 사업운영자료 수정
     */
    public int updateBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

    /**
     * 사업운영자료 삭제
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteBsnsOperDta
     * @Description : 사업운영자료 삭제
     */
    public int deleteBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception;

}
