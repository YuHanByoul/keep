package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyMdfcnHistoryVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyMdfcnHistoryService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyMdfcnHistoryService
 * @Description : 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 서비스 인터페이스
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface MyMdfcnHistoryService {
    /**
     * 정보변경이력 목록 조회
     *
     * @param myMdfcnHistoryVo
     * @return list
     * @throws Exception
     * @Title : selectMdfcnHistoryList
     * @Description : 정보변경이력 목록 조회
     */
    public List<MyMdfcnHistoryVo> selectMdfcnHistoryList(MyMdfcnHistoryVo myMdfcnHistoryVo) throws Exception;

    public String selectMdfcnRsnByDmndId(Integer mdfcnDmndId) throws Exception;

    public String selectLogRsnByDmndId(Integer mdfcnDmndId) throws Exception;

}
