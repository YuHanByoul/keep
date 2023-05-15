package com.kbrainc.plum.mng.etcDta.service;

import java.util.ArrayList;
import java.util.List;

import com.kbrainc.plum.mng.cmmCntnts.model.CmmCntntsVo;
import com.kbrainc.plum.mng.cmmCntnts.service.CmmCntntsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.etcDta.model.EtcDtaDao;
import com.kbrainc.plum.mng.etcDta.model.EtcDtaVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 기타자료 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.etcDta.service
 * - EtcDtaServiceImpl.java
 * </pre>
 *
 * @author : 이한명
 * @ClassName : EtcDtaServiceImpl
 * @Description : 기타자료 관리 서비스 구현 클래스
 * @date : 2023. 03. 29.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class EtcDtaServiceImpl extends PlumAbstractServiceImpl implements EtcDtaService {
    @Autowired
    private EtcDtaDao etcDtaDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private CmmCntntsService cmmCntntsService;

    /**
     * 기타자료 목록 조회
     *
     * @param etcDtaVo
     * @return list
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 기타자료 목록 조회
     */
    @Override
    public List<EtcDtaVo> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception {
        return etcDtaDao.selectEtcDtaList(etcDtaVo);
    }

    /**
     * 기타자료 상세 조회
     *
     * @param etcDtaVo
     * @return etc dta vo
     * @throws Exception
     * @Title : selectEtcDta
     * @Description : 기타자료 상세 조회
     */
    @Override
    public EtcDtaVo selectEtcDta(EtcDtaVo etcDtaVo) throws Exception {
        EtcDtaVo etcDta = etcDtaDao.selectEtcDta(etcDtaVo);
        if (etcDta == null) etcDta = new EtcDtaVo();

        if (etcDta.getAtchFilegrpid() != null && !etcDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(etcDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            etcDta.setAtchFileList(fileList);
        }

        return etcDta;
    }

    /**
     * 기타자료 등록
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : insertEtcDta
     * @Description : 기타자료 등록
     */
    @Override
    @Transactional
    public int insertEtcDta(EtcDtaVo etcDtaVo) throws Exception {
        int retVal = 0;
        retVal += etcDtaDao.insertEtcDta(etcDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(etcDtaVo.getUser());
        cmmCntntsVo.setEvntCd(etcDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(etcDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(etcDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);

        return retVal;
    }

    /**
     * 기타자료 수정
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : updateEtcDta
     * @Description : 기타자료 수정
     */
    @Override
    @Transactional
    public int updateEtcDta(EtcDtaVo etcDtaVo) throws Exception {
        int retVal = 0;

        retVal += etcDtaDao.updateEtcDta(etcDtaVo);

        CmmCntntsVo cmmCntntsVo = new CmmCntntsVo();
        cmmCntntsVo.setUser(etcDtaVo.getUser());
        cmmCntntsVo.setEvntCd(etcDtaVo.getEvntCd());
        cmmCntntsVo.setTrgtCd(etcDtaVo.getTrgtCd());
        cmmCntntsVo.setCntntsid(etcDtaVo.getDtaid());
        retVal += cmmCntntsService.insertCmmCntnts(cmmCntntsVo);

        return retVal;
    }

    /**
     * 기타자료 삭제
     *
     * @param etcDtaVo
     * @return int
     * @throws Exception
     * @Title : deleteEtcDta
     * @Description : 기타자료 삭제
     */
    @Override
    @Transactional
    public int deleteEtcDta(EtcDtaVo etcDtaVo) throws Exception {
        int retVal = 0;
        retVal += etcDtaDao.deleteEtcDta(etcDtaVo);
        return retVal;
    }
}
