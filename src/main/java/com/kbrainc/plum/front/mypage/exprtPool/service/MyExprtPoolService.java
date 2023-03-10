package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.mypage.exprtPool.model.CommonExprtVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyExprtMdfcnVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyExprtVo;

/**
 * 마이페이지 > 환경교육 전문가 풀 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyExprtPoolService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyExprtPoolService
 * @Description : 마이페이지 > 환경교육 전문가 풀 서비스 인터페이스
 * @date : 2023. 03. 02.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface MyExprtPoolService {
    /**
     * 전문가 정보 조회
     *
     * @param exprtVo
     * @return my exprt vo
     * @Title : selectMyExprt
     * @Description : 전문가 정보 조회
     */
    public MyExprtVo selectMyExprt(CommonExprtVo exprtVo) throws Exception;

    /**
     * 수정 화면에서 사용되는 전문가 정보 조회
     *
     * @param myExprtMdfcnVo
     * @return my exprt mdfcn vo
     * @throws Exception
     * @Title : selectMyExprtMdfcn
     * @Description : 수정 화면에서 사용되는 전문가 정보 조회
     */
    public MyExprtMdfcnVo selectMyExprtMdfcn(CommonExprtVo myExprtMdfcnVo) throws Exception;

    /**
     * 공개 범위 및 서비스 수신 여부 변경
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : updateRlsAndRcptn
     * @Description : 공개 범위 및 서비스 수신 여부 변경
     */
    public int updateRlsAndRcptn(MyExprtVo exprtVo) throws Exception;

    /**
     * 수정 요청 등록
     *
     * @param myExprtMdfcnVo
     * @return int
     * @throws Exception
     * @Title : insertMdfcnDmnd
     * @Description : 수정 요청 등록
     */
    public int insertMdfcnDmnd(MyExprtMdfcnVo myExprtMdfcnVo) throws Exception;
}
