package com.kbrainc.plum.mng.spcltyDta.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import com.kbrainc.plum.mng.spcltyDta.model.SpcltyDtaDao;
import com.kbrainc.plum.mng.spcltyDta.model.SpcltyDtaVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 전문자료 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spcltyDta.service
 * - SpcltyDtaServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpcltyDtaServiceImpl
 * @Description : 전문자료 관리 서비스 구현 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class SpcltyDtaServiceImpl extends PlumAbstractServiceImpl implements SpcltyDtaService {
    @Autowired
    private SpcltyDtaDao spcltyDtaDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private CmmCntntsService cmmCntntsService;


    /**
     * 전문자료 목록 조회
     *
     * @param spcltyDtaVo
     * @return list
     * @throws Exception
     * @Title : selectSpcltyDtaList
     * @Description : 전문자료 목록 조회
     */
    @Override
    public List<SpcltyDtaVo> selectSpcltyDtaList(SpcltyDtaVo spcltyDtaVo) throws Exception {
        return spcltyDtaDao.selectSpcltyDtaList(spcltyDtaVo);
    }

    /**
     * 전문자료 상세 조회
     *
     * @param spcltyDtaVo
     * @return spclty dta vo
     * @throws Exception
     * @Title : selectSpcltyDta
     * @Description : 전문자료 상세 조회
     */
    @Override
    public SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception {
        SpcltyDtaVo spcltyDta = spcltyDtaDao.selectSpcltyDta(spcltyDtaVo);
        if (spcltyDta == null) spcltyDta = new SpcltyDtaVo();

        if (spcltyDta.getPdfFileid() != null && !spcltyDta.getPdfFileid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(spcltyDta.getPdfFileid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            spcltyDta.setPdfFileList(fileList);
        }

        if (spcltyDta.getAtchFilegrpid() != null && !spcltyDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(spcltyDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            spcltyDta.setAtchFileList(fileList);
        }

        return spcltyDta;
    }

    /**
     * 전문자료 등록
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : insertSpcltyDta
     * @Description : 전문자료 등록
     */
    @Override
    @Transactional
    public int insertSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception {
        int retVal = 0;

        retVal += spcltyDtaDao.insertSpcltyDta(spcltyDtaVo);
        retVal += spcltyDtaDao.insertSpcltyDtaClsfMapping(spcltyDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(spcltyDtaVo.getUser());
        cmmCntntsVo.setEvntCd(spcltyDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(spcltyDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(spcltyDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);

        return retVal;
    }

    /**
     * 전문자료 수정
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : updateSpcltyDta
     * @Description : 전문자료 수정
     */
    @Override
    @Transactional
    public int updateSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception {
        int retVal = 0;

        retVal += spcltyDtaDao.updateSpcltyDta(spcltyDtaVo);
        retVal += spcltyDtaDao.deleteSpcltyDtaClsfMapping(spcltyDtaVo);
        retVal += spcltyDtaDao.insertSpcltyDtaClsfMapping(spcltyDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(spcltyDtaVo.getUser());
        cmmCntntsVo.setEvntCd(spcltyDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(spcltyDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(spcltyDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);

        return retVal;
    }

    /**
     * 전문자료 삭제
     *
     * @param spcltyDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteSpcltyDta
     * @Description : 전문자료 삭제
     */
    @Override
    @Transactional
    public int deleteSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception {
        int retVal = 0;

        retVal += spcltyDtaDao.deleteSpcltyDta(spcltyDtaVo);

        return retVal;
    }
}
