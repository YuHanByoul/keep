package com.kbrainc.plum.mng.spbJdgs.service;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.mng.expertPoolMng.model.ExpertVo;

import java.util.List;

/**
 * 체헌환경교육 지원사업 > 심사위원 그룹 관리 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.spbJdgs.service
 * - SpbJdgsService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpbJdgsService
 * @Description : 체헌환경교육 지원사업 > 심사위원 그룹 관리 서비스 인터페이스
 * @date : 2023. 01. 18.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface SpbJdgsService {
    /**
     * 심사위원 그룹 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectSpbJdgsList
     * @Description : 심사위원 그룹 조회
     */
    public List<JdgGrpVo> selectSpbJdgsList(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹 상세 조회
     *
     * @param grpId
     * @return jdg grp vo
     * @throws Exception
     * @Title : selectSpbJdgsInfo
     * @Description : 심사위원 그룹 상세 조회
     */
    public JdgGrpVo selectSpbJdgsInfo(Integer grpId) throws Exception;


    /**
     * 그룹에 매핑된 전문가 목록 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 그룹에 매핑된 전문가 목록 조회
     */
    public List<JdgGrpExpertVo> selectExprtList(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹 등록
     *
     * @param jdgGrpVo
     * @return boolean
     * @throws Exception
     * @Title : insertSpbJdgs
     * @Description : 심사위원 그룹 등록
     */
    public boolean insertSpbJdgs(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 심사위원 그룹 수정
     *
     * @param jdgGrpVo
     * @return boolean
     * @throws Exception
     * @Title : updateSpbJdgs
     * @Description : 심사위원 그룹 수정
     */
    public boolean updateSpbJdgs(JdgGrpVo jdgGrpVo) throws Exception;

    /**
     * 전문가 추가
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : insertExprt
     * @Description : 전문가 추가
     */
    public boolean insertExprt(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;

    /**
     * 전문가 삭제
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : deleteExprt
     * @Description : 전문가 삭제
     */
    public boolean deleteExprt(JdgGrpExpertVo jdgGrpExpertVo) throws Exception;
}
