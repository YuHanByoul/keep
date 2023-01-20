package com.kbrainc.plum.mng.prtpn.cntstRcptHist.service;

import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplySchlVO;
import com.kbrainc.plum.mng.prtpn.cntstRcptHist.model.CntstAplyVO;

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
     * Title : selectCntstAplyList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstAplyVO
     * @return list
     */
    List<CntstAplyVO> selectCntstAplyList(CntstAplyVO cntstAplyVO);

    /**
     * 공모전 접수 정보 조회
     * Title : selectCntstAplyInfo
     * Description : 공모전 접수 정보 조회
     *
     * @param aplyid
     * @return CntstRcptHistVO
     */
    CntstAplyVO selectCntstAplyInfo(Integer aplyid);

    /**
     * 환경방학 일기장 프로젝트 접수 정보 조회
     * Title : selectCntstAplySchlList
     * Description : 환경방학 일기장 프로젝트 접수 정보 조회
     *
     * @param aplyid
     * @return list
     */
    List<CntstAplySchlVO> selectCntstAplySchlList(Integer aplyid);
}
