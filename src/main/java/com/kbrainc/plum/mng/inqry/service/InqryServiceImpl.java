package com.kbrainc.plum.mng.inqry.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.inqry.model.*;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FileDao fileDao;

    @Autowired
    private NtcnDao ntcnDao;

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
        InqryAnswrVo inqryAnswr = inqryDao.selectInqryAnswrInfo(inqryVO);

        if (inqryAnswr != null && inqryAnswr.getFilegrpid() != null && !inqryAnswr.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(inqryAnswr.getFilegrpid());
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            inqryAnswr.setFileList(fileList);
        }

        return inqryAnswr;
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
        if (inqryAnswrVO.getManagerId().length >= 1) {
            retVal += inqryDao.insertInqryManager(inqryAnswrVO);
        }

        /*if(inqryAnswrVO.getInqrySttsCd().equals("103104")) {
            insertNtcn(inqryAnswrVO);
        }*/

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
        retVal += inqryDao.deleteInqryManager(inqryAnswrVO);
        if (inqryAnswrVO.getManagerId().length >= 1) {
            retVal += inqryDao.insertInqryManager(inqryAnswrVO);
        }

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

    @Override
    public List<TelInqryVo> selectTelInqryList(TelInqryVo telInqryVo) throws Exception {
        return inqryDao.selectTelInqryList(telInqryVo);
    }

    @Override
    public TelInqryVo selectTelInqryInfo(TelInqryVo telInqryVo) throws Exception {
        return inqryDao.selectTelInqryInfo(telInqryVo);
    }

    @Override
    public int insertTelInqry(TelInqryVo telInqryVo) throws Exception {
        int retVal = 0;

        retVal += inqryDao.insertTelInqry(telInqryVo);
        if (telInqryVo.getManagerId().length >= 1) {
            retVal += inqryDao.insertTelInqryManager(telInqryVo);
        }
        return retVal;
    }

    @Override
    public int updateTelInqry(TelInqryVo telInqryVo) throws Exception {
        int retVal = 0;

        retVal += inqryDao.updateTelInqry(telInqryVo);
        retVal += inqryDao.deleteTelInqryManager(telInqryVo);
        if (telInqryVo.getManagerId().length >= 1) {
            retVal += inqryDao.insertTelInqryManager(telInqryVo);
        }
        return retVal;
    }

    @Override
    public List<PopupMemberVo> selectMemberList(TelInqryVo telInqryVo) throws Exception {
        return inqryDao.selectMemberList(telInqryVo);
    }

    @Override
    public List<ManagerVo> selectManagerList(Object inqryVo) throws Exception {
        if(inqryVo instanceof InqryVo) {
            return inqryDao.selectInqryManagerList((InqryVo)inqryVo);
        } else {
            return inqryDao.selectTelInqryManagerList((TelInqryVo)inqryVo);
        }
    }

    private int insertNtcn(InqryAnswrVo inqryAnswrVo) throws Exception {
        NtcnVo ntcnVo = new NtcnVo();
        ntcnVo.setUserid(inqryAnswrVo.getUserid());

        ntcnVo.setTtl("1:1문의 게시글에 답변 등록");
        ntcnVo.setCn("1:1문의에 남겨주신 글에 답변이 등록되었습니다.<br/>"
                + "자세한 내용은 내 문의 내역에서 확인해 주십시오.<br/>");
        ntcnVo.setMvmnurl("/front/mypage/inqry/inqryDetail.html?inqryid=" + inqryAnswrVo.getInqryid());
        ntcnVo.setKndCd("245102");
        ntcnVo.setInqYn("N");
        ntcnDao.insertNtcn(ntcnVo);

        return 1;
    }
}

