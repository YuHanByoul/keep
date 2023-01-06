package com.kbrainc.plum.mng.helpDesk.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.helpDesk.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 탄소중립헬프데스크 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.helpDesk.service
 * - HelpDeskServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpDeskServiceImpl
 * @Description : 탄소중립헬프데스크 서비스 구현 클래스
 * @date : 2022. 12. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class HelpDeskServiceImpl extends PlumAbstractServiceImpl implements HelpDeskService {

    @Autowired
    private HelpDeskDao helpDeskDao;

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
    @Override
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskList(helpDeskVO);
    }

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
    @Override
    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskInfo(helpDeskVO);
    }
    
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
    @Override
    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskAnswrInfo(helpDeskVO);
    }

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
    @Override
    @Transactional
    public int insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception {
        int retVal = 0;

        if (helpDeskAnswrVo.getHelpDeskManager().length >= 1) {
            retVal += helpDeskDao.insertHelpDeskManager(helpDeskAnswrVo);
        }
        retVal += helpDeskDao.updateHelpDeskSttsCd(helpDeskAnswrVo);
        retVal += helpDeskDao.insertHelpDeskAnswr(helpDeskAnswrVo);

        return retVal;
    }

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
    @Override
    @Transactional
    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception {
        int retVal = 0;

        HelpDeskVo helpDeskVo = new HelpDeskVo();
        helpDeskVo.setInqryid(helpDeskAnswrVo.getInqryid());

        retVal += helpDeskDao.deleteHelpDeskManager(new Integer[]{helpDeskAnswrVo.getInqryid()});
        if (helpDeskAnswrVo.getHelpDeskManager().length >= 1) {
            retVal += helpDeskDao.insertHelpDeskManager(helpDeskAnswrVo);
        }
        retVal += helpDeskDao.updateHelpDeskSttsCd(helpDeskAnswrVo);
        retVal += helpDeskDao.updateHelpDeskAnswr(helpDeskAnswrVo);

        return retVal;
    }

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
    @Override
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception {
        return helpDeskDao.selectAttachFileList(fileVo);
    }

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
    @Override
    public int deleteHelpDesk(Integer[] deleteHelpDeskIds, UserVo userVo) throws Exception {
        int retVal = 0;

        retVal += helpDeskDao.deleteHelpDesk(deleteHelpDeskIds);

        return retVal;
    }

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
    @Override
    public List<HelpDeskAnswrManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception {
        return helpDeskDao.selectHelpDeskManagerList(helpDeskVo);
    }

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
    @Override
    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception {
        return helpDeskDao.selectUserList(helpDeskModalUserVo);
    }
}
