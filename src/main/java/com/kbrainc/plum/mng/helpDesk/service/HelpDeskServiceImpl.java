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
* [클래스  요약].
*
* <pre>
* com.kbrainc.plum.mng.helpDesk.service
* - HelpDeskServiceImpl.java
* </pre>
*
* @ClassName   : HelpDeskServiceImpl
* @Description : TODO
* @author      : KBRAINC_DEV
* @date        : 2022. 12. 20.
* @Version     :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class HelpDeskServiceImpl extends PlumAbstractServiceImpl implements HelpDeskService {

    @Autowired
    private HelpDeskDao helpDeskDao;
    
    /**
     * 문의 목록 조회
     *
     * @Title       : selectHelpDeskList 
     * @Description : TODO
     * @param helpDeskVO
     * @return
     * @throws Exception
     * @return List<HelpDeskVo> 
     */
    @Override
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception {
        return helpDeskDao.selectHelpDeskList(helpDeskVO);
    }

    /**
     * 문의 정보 조회
     *
     * @Title       : selectHelpDeskInfo 
     * @Description : TODO
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
    * @Description : TODO
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
    * @Description : TODO
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
    * @Description : TODO
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
        List<HelpDeskManagerVo> helpDeskManagerVos = helpDeskDao.selectHelpDeskManagerList(helpDeskVo);

        retVal += helpDeskDao.deleteHelpDeskManager(helpDeskAnswrVo);
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
    * @Description : TODO
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
    * @Description : TODO
    * @param deleteHelpDeskIds
    * @param userVo
    * @return
    * @throws Exception
    * @return int 
    */
    @Override
    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception {
        return helpDeskDao.deleteHelpDesk(deleteHelpDeskIds, userVo);
    }

    /**
    * 지정된 담당자 정보 조회
    *
    * @Title       : selectHelpDeskManagerList 
    * @Description : TODO
    * @param helpDeskVo
    * @return
    * @throws Exception
    * @return List<HelpDeskManagerVo> 
    */
    @Override
    public List<HelpDeskManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception {
        return helpDeskDao.selectHelpDeskManagerList(helpDeskVo);
    }

    /**
    * 담당자 검색모달 담당자 정보 조회
    *
    * @Title       : selectUserList 
    * @Description : TODO
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
