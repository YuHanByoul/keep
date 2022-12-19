package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface HelpDeskDao {
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception;

    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception;

    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception;

    public int insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

    public List<HelpDeskManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception;

    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception;

    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception;

    public ArrayList<String> selectUserRoleList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception;

    public int updateHelpDeskSttsCd(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    public int insertHelpDeskManager(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    public int deleteHelpDeskManager(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;
}
