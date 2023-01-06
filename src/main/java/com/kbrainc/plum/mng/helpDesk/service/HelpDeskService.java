package com.kbrainc.plum.mng.helpDesk.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskAnswrManagerVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskAnswrVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskModalUserVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskVo;
import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

/**
 * 탄소중립헬프데스크 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.helpDesk.service
 * - HelpDeskService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpDeskService
 * @Description : 탄소중립헬프데스크 서비스 인터페이스
 * @date : 2022. 12. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface HelpDeskService {

    /**
     * 문의 목록 조회
     *
     * @param helpDeskVO
     * @return
     * @return List<HelpDeskVo>
     * @throws Exception
     * @Title : selectHelpDeskList
     * @Description : 문의 목록 조회
     */
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception;

    
    /**
     * 문의 정보 조회
     *
     * @Title       : selectHelpDeskInfo
     * @Description : 문의 정보 조회
     * @param helpDeskVO
     * @return
     * @throws Exception
     * @return HelpDeskVo
     */
    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception;

   
    /**
     * 답변 정보 조회
     *
     * @Title       : selectHelpDeskAnswrInfo
     * @Description : 답변 정보 조회
     * @param helpDeskVO
     * @return
     * @throws Exception
     * @return HelpDeskAnswrVo
     */
    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception;

    
    /**
     * 답변 등록
     *
     * @Title       : insertHelpDeskAnswr
     * @Description : 답변 등록
     * @param helpDeskAnswrVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    
    /**
     * 답변 수정
     *
     * @Title       : updateHelpDeskAnswr
     * @Description : 답변 수정
     * @param helpDeskAnswrVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    
    /**
     * 문의글 파일정보 조회
     *
     * @Title       : selectAttachFileList
     * @Description : 문의글 파일정보 조회
     * @param fileVo
     * @return
     * @throws Exception
     * @return List<FileVo>
     */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

    /**
     * 문의 삭제
     *
     * @Title       : deleteHelpDesk
     * @Description : 문의 삭제
     * @param deleteHelpDeskIds
     * @param userVo
     * @return
     * @throws Exception
     * @return int
     */
    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception;

    /**
     * 지정된 담당자 정보 조회
     *
     * @Title       : selectHelpDeskManagerList
     * @Description : 지정된 담당자 정보 조회
     * @param helpDeskVo
     * @return
     * @throws Exception
     * @return List<HelpDeskAnswrManagerVo>
     */
    public List<HelpDeskAnswrManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception;

    /**
     * 담당자 검색모달 담당자 정보 조회
     *
     * @Title       : selectUserList
     * @Description : 담당자 검색모달 담당자 정보 조회
     * @param helpDeskModalUserVo
     * @return
     * @throws Exception
     * @return List<HelpDeskModalUserVo>
     */
    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception;


}
