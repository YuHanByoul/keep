package com.kbrainc.plum.mng.ass.jdgGrpMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 심사위원 그룹 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.model
 * - JdgGrpMngDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpMngDao
 * @Description : 심사위원 그룹 Dao 클래스
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface JdgGrpMngDao {
    /**
     * 심사위원 그룹 목록 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpList
     * @Description : 심사위원 그룹 목록 조회
     */
    public List<JdgGrpVo> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹 정보 조회
     *
     * @param jdgGrpVo
     * @return jdg grp vo
     * @throws Exception
     * @Title : selectJdgGrpInfo
     * @Description : 심사위원 그룹 정보 조회
     */
    public JdgGrpVo selectJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹에 매핑된 전문가 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpExpertList
     * @Description : 심사위원 그룹에 매핑된 전문가 조회
     */
    public List<JdgGrpExpertVo> selectJdgGrpExpertList(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 전문가 모달 > 전문가 목록 조회
     *
     * @param jdgGrpExpertVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpMngExpertSearchList
     * @Description : 전문가 모달 > 전문가 목록 조회
     */
    public List<JdgGrpExpertVo> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    /**
     * 심사위원 그룹 등록
     *
     * @param jdgGrpVo
     * @return int
     * @throws Exception
     * @Title : insertJdgGrpInfo
     * @Description : 심사위원 그룹 등록
     */
    public int insertJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹 수정
     *
     * @param jdgGrpVo
     * @return int
     * @throws Exception
     * @Title : updateJdgGrpInfo
     * @Description : 심사위원 그룹 수정
     */
    public int updateJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 전문가 추가
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : insertJdgGrpExpert
     * @Description : 전문가 추가
     */
    public boolean insertJdgGrpExpert(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    /**
     * 전문가 삭제
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : deleteJdgGrpExpert
     * @Description : 전문가 삭제
     */
    public boolean deleteJdgGrpExpert(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    /**
     * 그룹에 속한 전문가 목록 조회
     *
     * @param jdgGrpExpertVo
     * @return list
     * @throws Exception
     * @Title : selectExistingExpertList
     * @Description : 그룹에 속한 전문가 목록 조회
     */
    public List<JdgGrpExpertVo> selectExistingExpertList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;


}
