package com.kbrainc.plum.mng.prtpn.cntstRcptHist.service;

import com.kbrainc.plum.mng.prtpn.cntst.model.CntstVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstRcptHistVO;

import java.util.List;

/**
 * 공모전 접수 이력 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.service
 * - CntstRcptHistService.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstRcptHistService
 * @Description : 공모전 접수 이력 서비스 인터페이스
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface CntstRcptHistService {

    /**
     * 공모전 접수 이력 목록 조회
     * Title : selectCntstList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstRcptHistVO
     * @return list
     */
    List<CntstRcptHistVO> selectCntstRcptHistList(CntstRcptHistVO cntstRcptHistVO);


    /**
     * 공모전 접수 정보 조회
     * Title : selectCntst
     * Description : 공모전 접수 정보 조회
     *
     * @param aplyid
     * @return CntstRcptHistVO
     */
    CntstRcptHistVO selectCntstRcptHistInfo(Integer aplyid);
}
