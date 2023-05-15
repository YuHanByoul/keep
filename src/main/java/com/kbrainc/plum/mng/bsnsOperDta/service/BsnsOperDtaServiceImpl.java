package com.kbrainc.plum.mng.bsnsOperDta.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.bsnsOperDta.model.BsnsOperDtaDao;
import com.kbrainc.plum.mng.bsnsOperDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 사업운영자료 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.bsnsOperDta.service
 * - BsnsOperDtaServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : BsnsOperDtaServiceImpl
 * @Description : 사업운영자료 관리 서비스 구현 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class BsnsOperDtaServiceImpl extends PlumAbstractServiceImpl implements BsnsOperDtaService {

    @Autowired
    private BsnsOperDtaDao bsnsOperDtaDao;

    @Autowired
    private CmmCntntsService cmmCntntsService;

    @Autowired
    private FileDao fileDao;

    /**
     * 사업운영자료 목록 조회
     *
     * @return list
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @Override
    public List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        return bsnsOperDtaDao.selectBsnsOperDtaList(bsnsOperDtaVo);
    }

    /**
     * 사업운영자료 상세 조회
     *
     * @param bsnsOperDtaVo
     * @return bsns oper dta vo
     * @throws Exception
     * @Title : selectBsnsOperDta
     * @Description : 사업운영자료 상세 조회
     */
    @Override
    public BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        BsnsOperDtaVo bsnsOperDta = bsnsOperDtaDao.selectBsnsOperDta(bsnsOperDtaVo);

        if(bsnsOperDta == null) bsnsOperDta = new BsnsOperDtaVo();

        if (bsnsOperDta.getPdfFileid() != null && !bsnsOperDta.getPdfFileid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(bsnsOperDta.getPdfFileid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            bsnsOperDta.setPdfFileList(fileList);
        }

        if (bsnsOperDta.getAtchFilegrpid() != null && !bsnsOperDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(bsnsOperDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            bsnsOperDta.setAtchFileList(fileList);
        }

        return bsnsOperDta;
    }

    /**
     * 사업운영자료 등록
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : insertBsnsOperDta
     * @Description : 사업운영자료 등록
     */
    @Override
    @Transactional
    public int insertBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        int retVal = 0;
        retVal += bsnsOperDtaDao.insertBsnsOperDta(bsnsOperDtaVo);
        retVal += bsnsOperDtaDao.insertBsnsOperDtaClsfMapping(bsnsOperDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(bsnsOperDtaVo.getUser());
        cmmCntntsVo.setEvntCd(bsnsOperDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(bsnsOperDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(bsnsOperDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);

        return retVal;
    }

    /**
     * 사업운영자료 수정
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : updateBsnsOperDta
     * @Description : 사업운영자료 수정
     */
    @Override
    @Transactional
    public int updateBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        int retVal = 0;

        retVal += bsnsOperDtaDao.updateBsnsOperDta(bsnsOperDtaVo);
        retVal += bsnsOperDtaDao.deleteBsnsOperDtaClsfMapping(bsnsOperDtaVo);
        retVal += bsnsOperDtaDao.insertBsnsOperDtaClsfMapping(bsnsOperDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(bsnsOperDtaVo.getUser());
        cmmCntntsVo.setEvntCd(bsnsOperDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(bsnsOperDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(bsnsOperDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);
        return retVal;
    }

    /**
     * 사업운영자료 삭제
     *
     * @param bsnsOperDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteBsnsOperDta
     * @Description : 사업운영자료 삭제
     */
    @Override
    @Transactional
    public int deleteBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        int retVal = 0;

        retVal += bsnsOperDtaDao.deleteBsnsOperDta(bsnsOperDtaVo);

        return retVal;
    }
}
