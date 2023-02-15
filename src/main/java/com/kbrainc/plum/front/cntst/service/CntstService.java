package com.kbrainc.plum.front.cntst.service;

import com.kbrainc.plum.front.cntst.model.CntstVO;

import java.util.List;

/**
 * 공모전 등록 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.service
 * - CntstService.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstService
 * @Description : 공모전 등록 서비스 인터페이스
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface CntstService {


    /**
     * 공모전 등록 목록 조회
     * Title : selectCntstList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstVO
     * @return list
     */
    List<CntstVO> selectCntstList(CntstVO cntstVO);
}
