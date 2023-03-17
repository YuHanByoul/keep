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

    public String selectExprtStts(UserVo user) throws Exception;

    public int insertExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public ExprtRegisterVo selectExprtRegister(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public List<MmbrQlfcVo> selectMmbrQlfcList(ExprtRegisterVo exprtRegisterVo) throws Exception;
}
