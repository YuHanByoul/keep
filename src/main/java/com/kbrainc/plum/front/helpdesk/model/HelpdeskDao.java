package com.kbrainc.plum.front.helpdesk.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 헬프데스크 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.helpdesk.model
 * - HelpdeskDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpdeskDao
 * @Description : 헬프데스크 Dao 클래스
 * @date : 2023. 02. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.helpdeskDao")
public interface HelpdeskDao {
    /**
     * 헬프데스크 신청 목록 조회
     *
     * @param helpdeskVo
     * @return list
     * @throws Exception
     * @Title : selectHelpdeskList
     * @Description : 헬프데스크 신청 목록 조회
     */
    public List<HelpdeskVo> selectHelpdeskList(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 마이페이지 > 헬프데스크 신청 목록 조회
     *
     * @param helpdeskVo
     * @return list
     * @throws Exception
     * @Title : selectHelpdeskList
     * @Description : 마이페이지 > 헬프데스크 신청 목록 조회
     */
    public List<HelpdeskVo> selectMyHelpdeskList(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 상세 조회
     *
     * @param helpdeskVo
     * @return helpdesk vo
     * @throws Exception
     * @Title : selectHelpdesk
     * @Description : 헬프데스크 상세 조회
     */
    public HelpdeskVo selectHelpdesk(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 답변 조회
     *
     * @param helpdeskVo
     * @return helpdesk ans vo
     * @throws Exception
     * @Title : selectHelpdeskAns
     * @Description : 헬프데스크 답변 조회
     */
    public HelpdeskAnsVo selectHelpdeskAns(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 등록
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : insertHelpdesk
     * @Description : 헬프데스크 등록
     */
    public int insertHelpdesk(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 수정
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : updateHelpdesk
     * @Description : 헬프데스크 수정
     */
    public int updateHelpdesk(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 삭제
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : deleteHelpdesk
     * @Description : 헬프데스크 삭제
     */
    public int deleteHelpdesk(HelpdeskVo helpdeskVo) throws Exception;

    /**
     * 헬프데스크 취소
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : cancelHelpdesk
     * @Description : 헬프데스크 취소
     */
    public int cancelHelpdesk(HelpdeskVo helpdeskVo) throws Exception;



}
