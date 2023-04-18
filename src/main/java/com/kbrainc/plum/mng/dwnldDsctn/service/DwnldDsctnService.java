package com.kbrainc.plum.mng.dwnldDsctn.service;

import com.kbrainc.plum.mng.dwnldDsctn.model.DwnldDsctnVo;

/**
 * 다운로드 사유 등록 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.service
 * - DwnldDsctnService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DwnldDsctnService
 * @Description : 다운로드 사유 등록 서비스 인터페이스
 * @date : 2023. 04. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface DwnldDsctnService {
    /**
     * 다운로드 사유 등록
     *
     * @param dwnldDsctnVo
     * @return int
     * @throws Exception
     * @Title : insertDwnldDsctn
     * @Description : 다운로드 사유 등록
     */
    public int insertDwnldDsctn(DwnldDsctnVo dwnldDsctnVo) throws Exception;

}
