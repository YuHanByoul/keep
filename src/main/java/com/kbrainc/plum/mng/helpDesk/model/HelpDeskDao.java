package com.kbrainc.plum.mng.helpDesk.model;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 탄소중립헬프데스크 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.helpDesk.model
 * - HelpDeskDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpDeskDao
 * @Description : 탄소중립헬프데스크 DAO 클래스
 * @date : 2022. 12. 20.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface HelpDeskDao {
    
    /**
    *  문의목록 조회
    *
    * @Title       : selectHelpDeskList 
    * @Description : TODO
    * @param helpDeskVO
    * @return
    * @throws Exception
    * @return List<HelpDeskVo> 
    */
    public List<HelpDeskVo> selectHelpDeskList(HelpDeskVo helpDeskVO) throws Exception;

    /**
    *  상세정보 조회
    *
    * @Title       : selectHelpDeskInfo 
    * @Description : TODO
    * @param helpDeskVO
    * @return
    * @throws Exception
    * @return HelpDeskVo 
    */
    public HelpDeskVo selectHelpDeskInfo(HelpDeskVo helpDeskVO) throws Exception;

    /**
    *  답변정보 조회
    *
    * @Title       : selectHelpDeskAnswrInfo 
    * @Description : TODO
    * @param helpDeskVO
    * @return
    * @throws Exception
    * @return HelpDeskAnswrVo 
    */
    public HelpDeskAnswrVo selectHelpDeskAnswrInfo(HelpDeskVo helpDeskVO) throws Exception;

    /**
    *  답변 등록
    *
    * @Title       : insertHelpDeskAnswr 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @return
    * @throws Exception
    * @return int 
    */
    public int insertHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
    *  답변 수정
    *
    * @Title       : updateHelpDeskAnswr 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @return
    * @throws Exception
    * @return int 
    */
    public int updateHelpDeskAnswr(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
    *  문의 파일 정보 조회
    *
    * @Title       : selectAttachFileList 
    * @Description : TODO
    * @param fileVo
    * @return
    * @throws Exception
    * @return List<FileVo> 
    */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception;

    /**
    *  지정된 담당자 정보 조회
    *
    * @Title       : selectHelpDeskManagerList 
    * @Description : TODO
    * @param helpDeskVo
    * @return
    * @throws Exception
    * @return List<HelpDeskAnswrManagerVo>
    */
    public List<HelpDeskAnswrManagerVo> selectHelpDeskManagerList(HelpDeskVo helpDeskVo) throws Exception;

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
    public int deleteHelpDesk(String[] deleteHelpDeskIds, UserVo userVo) throws Exception;

    /**
    * 담당자모달 담당자정보 조회
    *
    * @Title       : selectUserList 
    * @Description : TODO
    * @param helpDeskModalUserVo
    * @return
    * @throws Exception
    * @return List<HelpDeskModalUserVo> 
    */
    public List<HelpDeskModalUserVo> selectUserList(HelpDeskModalUserVo helpDeskModalUserVo) throws Exception;

    /**
    * 문의글 상태코드 수정
    *
    * @Title       : updateHelpDeskSttsCd 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @return
    * @throws Exception
    * @return int 
    */
    public int updateHelpDeskSttsCd(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
    * 담당자 정보 등록
    *
    * @Title       : insertHelpDeskManager 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @return
    * @throws Exception
    * @return int 
    */
    public int insertHelpDeskManager(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;

    /**
    * 담당자 정보 삭제
    *
    * @Title       : deleteHelpDeskManager 
    * @Description : TODO
    * @param helpDeskAnswrVo
    * @return
    * @throws Exception
    * @return int 
    */
    public int deleteHelpDeskManager(HelpDeskAnswrVo helpDeskAnswrVo) throws Exception;
}
