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
    public DefaultMemberInfoVo selectDefaultMemberInfo(ExprtRegisterVo exprtRegisterVo) throws Exception;

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
     * 전문가 수정
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : updateExprt
     * @Description : 전문가 수정
     */
    public int updateExprt(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 재직 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertHdof
     * @Description : 재직사항 등록
     */
    public int insertHdof(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 자격증 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertCrtfct
     * @Description : 자격증 사항 등록
     */
    public int insertCrtfct(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 경력 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertCareer
     * @Description : 경력사항 등록
     */
    public int insertCareer(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 회원테이블에 기본정보 등록
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : insertDefaultInfo
     * @Description : 회원테이블에 기본정보 등록
     */
    public int insertDefaultInfo(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 대상 등록
     *
     * @param trgtCds
     * @param user
     * @return int
     * @throws Exception
     * @Title : insertTrgtCds
     * @Description : TODO [메소드 설명]
     */
    public int insertTrgtCds(@Param("trgtCds") String[] trgtCds, @Param("user") UserVo user) throws Exception;

    /**
     * 전문가 주제 등록
     *
     * @param sbjctCds
     * @param user
     * @return int
     * @throws Exception
     * @Title : insertSbjctCds
     * @Description : TODO [메소드 설명]
     */
    public int insertSbjctCds(@Param("sbjctCds") String[] sbjctCds, @Param("user") UserVo user) throws Exception;

    /**
     * 전문자 활동 지역 등록
     *
     * @param actvtRgnCds
     * @param user
     * @return int
     * @throws Exception
     * @Title : insertActvtRgnCds
     * @Description : TODO [메소드 설명]
     */
    public int insertActvtRgnCds(@Param("actvtRgnCds") String[] actvtRgnCds, @Param("user") UserVo user) throws Exception;

    /**
     * 전문가 활동 범위 등록
     *
     * @param actvtScopeCds
     * @param user
     * @return int
     * @throws Exception
     * @Title : insertActvtScopeCds
     * @Description : TODO [메소드 설명]
     */
    public int insertActvtScopeCds(@Param("actvtScopeCds") String[] actvtScopeCds, @Param("user") UserVo user) throws Exception;

    /**
     * 전문가 대상 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteTrgtCds
     * @Description : TODO [메소드 설명]
     */
    public int deleteTrgtCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 주제 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteSbjctCds
     * @Description : TODO [메소드 설명]
     */
    public int deleteSbjctCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 활동 지역 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteActvtRgnCds
     * @Description : TODO [메소드 설명]
     */
    public int deleteActvtRgnCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 활동 범위 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteActvtScopeCds
     * @Description : TODO [메소드 설명]
     */
    public int deleteActvtScopeCds(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 임시저장된 전문가 정보 조회
     *
     * @param exprtRegisterVo
     * @return exprt register vo
     * @throws Exception
     * @Title : selectExpertRegister
     * @Description : 임시저장된 전문가 정보 조회
     */
    public ExprtRegisterVo selectExpertRegister(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 경력 목록 조회
     *
     * @param exprtRegisterVo
     * @return list
     * @throws Exception
     * @Title : selectExpertCareerList
     * @Description : 전문가 경력 목록 조회
     */
    public List<CareerVo> selectExpertCareerList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 자격 목록 조회
     *
     * @param exprtRegisterVo
     * @return list
     * @throws Exception
     * @Title : selectExpertCrtfctList
     * @Description : 전문가 자격 목록 조회
     */
    public List<CrtfctVo> selectExpertCrtfctList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 전문가 재직 목록 조회
     *
     * @param exprtRegisterVo
     * @return list
     * @throws Exception
     * @Title : selectExpertHdofList
     * @Description : 전문가 재직 목록 조회
     */
    public List<HdofVo> selectExpertHdofList(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 재직 목록 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteHdof
     * @Description : 재직 목록 삭제
     */
    public int deleteHdof(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 자격 목록 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteCrtfct
     * @Description : 자격 목록 삭제
     */
    public int deleteCrtfct(ExprtRegisterVo exprtRegisterVo) throws Exception;

    /**
     * 경력 목록 삭제
     *
     * @param exprtRegisterVo
     * @return int
     * @throws Exception
     * @Title : deleteCareer
     * @Description : 경력 목록 삭제
     */
    public int deleteCareer(ExprtRegisterVo exprtRegisterVo) throws Exception;
}
