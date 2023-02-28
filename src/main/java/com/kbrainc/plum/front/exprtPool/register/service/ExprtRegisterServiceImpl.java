package com.kbrainc.plum.front.exprtPool.register.service;

import com.kbrainc.plum.front.exprtPool.register.model.DefaultMemberInfoVo;
import com.kbrainc.plum.front.exprtPool.register.model.ExprtRegisterDao;
import com.kbrainc.plum.front.exprtPool.register.model.ExprtRegisterVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 전문가 등재신청 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.service
 * - ExprtRegisterServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterServiceImpl
 * @Description : 전문가 등재신청 서비스 구현 클래스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
@RequiredArgsConstructor
public class ExprtRegisterServiceImpl extends PlumAbstractServiceImpl implements ExprtRegisterService {

    private final ExprtRegisterDao exprtRegisterDao;

    /**
     * 사용자 기본정보 조회
     *
     * @param exprtRegisterVo
     * @return default member info vo
     * @Title : selectDefaultMemberInfo
     * @Description : 사용자 기본정보 조회
     */
    public DefaultMemberInfoVo selectDefaultMemberInfo(ExprtRegisterVo exprtRegisterVo) throws Exception{
        return exprtRegisterDao.selectDefaultMemberInfo(exprtRegisterVo);
    }

    @Override
    public String selectExprtStts(UserVo user) throws Exception {
        return exprtRegisterDao.selectExprtStts(user);

    }
}