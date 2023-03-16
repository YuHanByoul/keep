package com.kbrainc.plum.front.exprtPool.register.model;

import com.kbrainc.plum.rte.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

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

    public int insertExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int updateExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertHdof(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertCrtfct(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertCareer(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertDefaultInfo(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertTrgtCds(@Param("trgtCds") String[] trgtCds, @Param("user") UserVo user) throws Exception;

    public int insertSbjctCds(@Param("sbjctCds") String[] sbjctCds, @Param("user") UserVo user) throws Exception;

    public int insertActvtRgnCds(@Param("actvtRgnCds") String[] actvtRgnCds, @Param("user") UserVo user) throws Exception;

    public int insertActvtScopeCds(@Param("actvtScopeCds") String[] actvtScopeCds, @Param("user") UserVo user) throws Exception;

    public int deleteTrgtCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteSbjctCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteActvtRgnCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteActvtScopeCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public ExprtRegisterVo selectExpertRegister(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public List<CareerVo> selectExpertCareerList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public List<CrtfctVo> selectExpertCrtfctList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public List<HdofVo> selectExpertHdofList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteHdof(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteCrtfct(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int deleteCareer(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertMdfcnExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertMdfcnHdof(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertMdfcnCrtfct(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertMdfcnCareer(ExprtRegisterVo exprtRegisterVo) throws Exception;

    public int insertMdfcnTrgtCds(@Param("mdfcnDmndId") Integer mdfcnDmndId, @Param("trgtCds") String[] trgtCds, @Param("user") UserVo user) throws Exception;

    public int insertMdfcnSbjctCds(@Param("mdfcnDmndId") Integer mdfcnDmndId, @Param("sbjctCds") String[] sbjctCds, @Param("user") UserVo user) throws Exception;

    public int insertMdfcnActvtRgnCds(@Param("mdfcnDmndId") Integer mdfcnDmndId, @Param("actvtRgnCds") String[] actvtRgnCds, @Param("user") UserVo user) throws Exception;

    public int insertMdfcnActvtScopeCds(@Param("mdfcnDmndId") Integer mdfcnDmndId, @Param("actvtScopeCds") String[] actvtScopeCds, @Param("user") UserVo user) throws Exception;
}
