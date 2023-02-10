package com.kbrainc.plum.front.helpdesk.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskAnsVo;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskDao;
import com.kbrainc.plum.front.helpdesk.model.HelpdeskVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 헬프데스크 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.helpdesk.service
 * - HelpdeskServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : HelpdeskServiceImpl
 * @Description : 헬프데스크 서비스 구현 클래스
 * @date : 2023. 02. 09.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.helpdeskService")
@Alias("front.helpdeskService")
public class HelpdeskServiceImpl extends PlumAbstractServiceImpl implements HelpdeskService {

    @Resource(name = "front.helpdeskDao")
    private HelpdeskDao helpdeskDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 헬프데스크 신청 목록 조회
     *
     * @param helpdeskVo
     * @return list
     * @throws Exception
     * @Title : selectHelpdeskList
     * @Description : 헬프데스크 신청 목록 조회
     */
    @Override
    public List<HelpdeskVo> selectHelpdeskList(HelpdeskVo helpdeskVo) throws Exception {
        return helpdeskDao.selectHelpdeskList(helpdeskVo);
    }

    /**
     * 헬프데스크 상세 조회
     *
     * @param helpdeskVo
     * @return helpdesk vo
     * @throws Exception
     * @Title : selectHelpdesk
     * @Description : 헬프데스크 상세 조회
     */
    @Override
    public HelpdeskVo selectHelpdesk(HelpdeskVo helpdeskVo) throws Exception {
        HelpdeskVo helpdesk = helpdeskDao.selectHelpdesk(helpdeskVo);

        if (helpdesk != null && !helpdesk.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(helpdesk.getFilegrpid().toString()));
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            helpdesk.setFileList(fileList);
        }

        return helpdesk;
    }

    /**
     * 헬프데스크 답변 조회
     *
     * @param helpdeskVo
     * @return helpdesk ans vo
     * @throws Exception
     * @Title : selectHelpdeskAns
     * @Description : 헬프데스크 답변 조회
     */
    @Override
    public HelpdeskAnsVo selectHelpdeskAns(HelpdeskVo helpdeskVo) throws Exception {
        HelpdeskAnsVo helpdeskAns = helpdeskDao.selectHelpdeskAns(helpdeskVo);

        if (helpdeskAns != null && !helpdeskAns.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(helpdeskAns.getFilegrpid().toString()));
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            helpdeskAns.setFileList(fileList);
        }

        return helpdeskAns;
    }

    /**
     * 헬프데스크 등록
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : insertHelpdesk
     * @Description : 헬프데스크 등록
     */
    @Override
    public int insertHelpdesk(HelpdeskVo helpdeskVo) throws Exception {
        int retVal = 0;
        retVal += helpdeskDao.insertHelpdesk(helpdeskVo);
        return retVal;
    }

    /**
     * 헬프데스크 수정
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : updateHelpdesk
     * @Description : 헬프데스크 수정
     */
    @Override
    public int updateHelpdesk(HelpdeskVo helpdeskVo) throws Exception {
        int retVal = 0;
        retVal += helpdeskDao.updateHelpdesk(helpdeskVo);
        return retVal;
    }

    /**
     * 헬프데스크 삭제
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : deleteHelpdesk
     * @Description : 헬프데스크 삭제
     */
    @Override
    public int deleteHelpdesk(HelpdeskVo helpdeskVo) throws Exception {
        int retVal = 0;
        retVal += helpdeskDao.deleteHelpdesk(helpdeskVo);
        return retVal;
    }

    /**
     * 헬프데스크 취소
     *
     * @param helpdeskVo
     * @return int
     * @throws Exception
     * @Title : cancelHelpdesk
     * @Description : 헬프데스크 취소
     */
    @Override
    public int cancelHelpdesk(HelpdeskVo helpdeskVo) throws Exception {
        int retVal = 0;
        retVal += helpdeskDao.cancelHelpdesk(helpdeskVo);
        return retVal;
    }
}
