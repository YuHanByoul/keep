package com.kbrainc.plum.mng.helpDesk.service;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.helpDesk.model.*;
import com.kbrainc.plum.rte.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    private HelpDeskDao helpDeskDao;

    @Override
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskList(helpDeskVO);
    }

    @Override
    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskInfo(helpDeskVO);
    }

    @Override
    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskAnswrInfo(helpDeskVO);
    }


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

    @Override
    @Transactional
    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception {
        int retVal = 0;

        HelpDeskVo helpDeskVo = new HelpDeskVo();
        helpDeskVo.setInqryid(helpDeskAnswrVo.getInqryid());
        List<HelpDeskManagerVo> helpDeskManagerVos = helpDeskDao.selectHelpDeskManagerList(helpDeskVo);

        retVal += helpDeskDao.deleteHelpDeskManager(helpDeskAnswrVo);
        if (helpDeskAnswrVo.getHelpDeskManager().length >= 1) {
            retVal += helpDeskDao.insertHelpDeskManager(helpDeskAnswrVo);
        }
        retVal += helpDeskDao.updateHelpDeskSttsCd(helpDeskAnswrVo);
        retVal += helpDeskDao.updateHelpDeskAnswr(helpDeskAnswrVo);

        return retVal;
    }

    @Override
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception {
        return helpDeskDao.selectAttachFileList(fileVo);
    }

    @Override
    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception {
        return helpDeskDao.deleteHelpDesk(deleteHelpDeskIds, userVo);
    }

    @Override
    public List<HelpDeskManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception {
        return helpDeskDao.selectHelpDeskManagerList(helpDeskVo);
    }

    @Override
    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception {
        return helpDeskDao.selectUserList(helpDeskModalUserVo);
    }
}
