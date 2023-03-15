package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

/**
 * 마이페이지 > 전문가 풀 관리 > 관심 전문가 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.controller
 * - MyItrstExprtServcice.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyItrstExprtServcice
 * @Description : 마이페이지 > 전문가 풀 관리 > 관심 전문가 서비스 인터페이스
 * @date : 2023. 03. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface MyItrstExprtServcice {
    /**
     * 관심 전문가 목록 조회
     *
     * @param user
     * @return list
     * @throws Exception
     * @Title : selectItrstExprtList
     * @Description : 관심 전문가 목록 조회
     */
    public List<ExprtVo> selectItrstExprtList(ExprtVo searchVo) throws Exception;

}
