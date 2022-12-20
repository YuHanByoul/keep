package com.kbrainc.plum.mng.helpDesk.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskAnswrVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskManagerVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskModalUserVo;
import com.kbrainc.plum.mng.helpDesk.model.HelpDeskVo;
import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

public interface HelpDeskService {
    /**
     * @param inqryVO 1:1문의VO 클래스
     * @return List<InqryVo> 1:1문의 목록
     * @throws Exception
     * @Title : selectInqryList
     * @Description : 1:1문의 리스트 가져옴
     */
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception;

    /**
     * @param inqryVO 1:1문의VO 클래스
     * @return InqryVo 1:1문의 정보
     * @throws Exception
     * @Title : selectInqryInfo
     * @Description : 1:1문의 정보를 가져온다.
     */
    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception;

    /**
     * @param inqryVO 1:1문의답변VO 클래스
     * @return InqryAnswrVo 1:1문의답변 정보
     * @throws Exception
     * @Title : selectInqryAnswrInfo
     * @Description : 1:1문의답변 정보를 가져온다.
     */
    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception;

    /**
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @return int 등록 로우수
     * @throws Exception
     * @Title : insertInqryAnswr
     * @Description : 1:1문의답변 등록
     */
    public int insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @return int 수정 로우수
     * @throws Exception
     * @Title : updateInqryAnswr
     * @Description : 1:1문의답변 수정
     */
    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
     * @param FileVo
     * @return FileVo
     * @throws Exception
     * @Title : selectAttachFileList
     * @Description :첨부파일 리스트 호출
     */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception;

    public List<HelpDeskManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception;

    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception;


}
