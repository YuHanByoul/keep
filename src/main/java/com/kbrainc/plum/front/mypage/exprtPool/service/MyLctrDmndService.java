package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndVo;

import java.util.List;

/**
 * 마이페이지 > 전문가 요청 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyLctrDmndService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyLctrDmndService
 * @Description : 마이페이지 > 전문가 요청 관리 서비스 인터페이스
 * @date : 2023. 03. 06.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface MyLctrDmndService {
    /**
     * 강의 요청서 목록 조회
     *
     * @param myLctrDmndVo
     * @return list
     * @throws Exception
     * @Title : selectLctrDmndList
     * @Description : 강의 요청서 목록 조회
     */
    public List<MyLctrDmndVo> selectLctrDmndList(MyLctrDmndVo myLctrDmndVo) throws Exception;

    /**
     * 강의 요청서 상세 조회
     *
     * @param myLctrDmndVo
     * @return my lctr dmnd vo
     * @throws Exception
     * @Title : selectLctrDmnd
     * @Description : 강의 요청서 상세 조회
     */
    public MyLctrDmndVo selectLctrDmnd(MyLctrDmndVo myLctrDmndVo) throws Exception;

    /**
     * 강의 요청서 상태 변경
     *
     * @param myLctrDmndVo
     * @return int
     * @throws Exception
     * @Title : updateStatus
     * @Description : 강의 요청서 상태 변경
     */
    public int updateStatus(MyLctrDmndVo myLctrDmndVo) throws Exception;

}
