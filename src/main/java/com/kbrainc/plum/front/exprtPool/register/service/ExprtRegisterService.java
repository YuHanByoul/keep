package com.kbrainc.plum.front.exprtPool.register.service;

import com.kbrainc.plum.front.exprtPool.register.model.DefaultMemberInfoVo;
import com.kbrainc.plum.front.exprtPool.register.model.ExprtRegisterVo;
import com.kbrainc.plum.front.exprtPool.register.model.MmbrQlfcVo;
import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

/**
 * 전문가 등재신청 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.service
 * - ExprtRegisterService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterService
 * @Description : 전문가 등재신청 서비스 인터페이스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface ExprtRegisterService {

    /**
     * 사용자 기본정보 조회
     *
     * @param exprtRegisterVo
     * @return default member info vo
     * @Title : selectDefaultMemberInfo
     * @Description : 사용자 기본정보 조회
     */
    public DefaultMemberInfoVo selectDefaultMemberInfo(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 로그인한 사용자의 전문가 상태 코드 조회
     *
     * @param user
     * @return string
     * @throws Exception
     * @Title : selectExprtStts
     * @Description : 로그인한 사용자의 전문가 상태 코드 조회
     */
    public String selectExprtStts(UserVo user) throws Exception;


    /**
     * 전문가 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertExprt
     * @Description : 전문가 등록
     */
    public int insertExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 임시저장된 전문가 정보 조회
     *
     * @param exprtRegisterVo
     * @return exprt register vo
     * @throws Exception
     * @Title : selectExprtRegister
     * @Description : 임시저장된 전문가 정보 조회
     */
    public ExprtRegisterVo selectExprtRegister(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 환경교육사 연동테이블에 있는 전문자격증 테이블 목록 조회
     *
     * @param exprtRegisterVo
     * @return list
     * @throws Exception
     * @Title : selectMmbrQlfcList
     * @Description : 환경교육사 연동테이블에 있는 전문자격증 테이블 목록 조회
     */
    public List<MmbrQlfcVo> selectMmbrQlfcList(ExprtRegisterVo exprtRegisterVo) throws Exception;
}
