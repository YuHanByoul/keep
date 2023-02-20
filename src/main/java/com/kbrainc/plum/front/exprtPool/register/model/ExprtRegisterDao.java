package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.Map;

/**
 * 전문가 등재신청 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.register.model
 * - ExprtRegisterDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : ExprtRegisterDao
 * @Description : 전문가 등재신청 Dao 인터페이스
 * @date : 2023. 02. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface ExprtRegisterDao {
    /**
     * 사용자 기본정보 조회
     *
     * @param exprtRegisterVo
     * @return default member info vo
     * @Title : selectDefaultMemberInfo
     * @Description : 사용자 기본정보 조회
     */
    public DefaultMemberInfoVo selectDefaultMemberInfo(ExprtRegisterVo exprtRegisterVo);

    public String selectExprtStts(UserVo user) throws Exception;
}
