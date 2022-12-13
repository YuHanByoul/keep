package com.kbrainc.plum.mng.inqry.service;

import java.util.List;

import com.kbrainc.plum.rte.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.inqry.model.InqryAnswrVo;
import com.kbrainc.plum.mng.inqry.model.InqryDao;
import com.kbrainc.plum.mng.inqry.model.InqryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 1:1문의 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.inqry.service
 * - InqryServiceImpl.java
 * </pre> 
 *
 * @ClassName : InqryServiceImpl
 * @Description : 1:1문의 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("mng.inqry.inqryService")
public class InqryServiceImpl extends PlumAbstractServiceImpl implements InqryService {

    @Autowired
    private InqryDao inqryDao;

    /**
     * @Title : selectInqryList
     * @Description : 1:1문의 리스트 가져옴
     * @param inqryVO 1:1문의VO 클래스
     * @throws Exception
     * @return List<InqryVo> 1:1문의 목록
     */
    @Override
    public List<InqryVo> selectInqryList(InqryVo inqryVO) throws Exception {
        return inqryDao.selectInqryList(inqryVO);
    }

    /**
     * @Title : selectInqryInfo
     * @Description : 1:1문의 정보를 가져온다.
     * @param inqryVO 1:1문의VO 클래스
     * @throws Exception
     * @return InqryVo 1:1문의 정보
     */
    @Override
    public InqryVo selectInqryInfo(InqryVo inqryVO) throws Exception {
        return inqryDao.selectInqryInfo(inqryVO);
    }

    /**
     * @Title : selectInqryAnswrInfo
     * @Description : 1:1문의답변 정보를 가져온다.
     * @param inqryVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return InqryAnswrVo 1:1문의답변 정보
     */
    @Override
    public InqryAnswrVo selectInqryAnswrInfo(InqryVo inqryVO) throws Exception {
        return inqryDao.selectInqryAnswrInfo(inqryVO);
    }

    /**
     * @Title : insertInqryAnswr
     * @Description : 1:1문의답변 등록
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return int 등록 로우수
     */
    @Override
    @Transactional
    public int insertInqryAnswr(InqryAnswrVo inqryAnswrVO) throws Exception {
        int retVal = 0;

        retVal += inqryDao.insertInqryAnswr(inqryAnswrVO);
        retVal += inqryDao.updateInqrySttsCd(inqryAnswrVO);

        return retVal;
    }

    /**
     * @Title : updateInqryAnswr
     * @Description : 1:1문의답변 수정
     * @param inqryAnswrVO 1:1문의답변VO 클래스
     * @throws Exception
     * @return int 수정 로우수
     */
    @Override
    @Transactional
    public int updateInqryAnswr(InqryAnswrVo inqryAnswrVO) throws Exception {
        int retVal = 0;

        retVal += inqryDao.updateInqryAnswr(inqryAnswrVO);
        retVal += inqryDao.updateInqrySttsCd(inqryAnswrVO);

        return retVal;
    }

    /**
     * @Title : selectAttachFileList
     * @Description :첨부파일 리스트 호출
     * @param FileVo
     * @throws Exception
     * @return FileVo
     */
    public List<FileVo> selectAttachFileList(FileVo fileVo) throws Exception{
        return inqryDao.selectAttachFileList(fileVo);
    }

    @Override
    @Transactional
    public int deleteInqryInfo(String[] deleteInqryIds, UserVo userVo) throws Exception {
        return inqryDao.deleteInqryInfo(deleteInqryIds, userVo);
    }
}
