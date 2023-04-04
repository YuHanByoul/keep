package com.kbrainc.plum.front.intro.envEduPlcyEtcDta.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model.EnvEduPlcyEtcDtaDao;
import com.kbrainc.plum.front.intro.envEduPlcyEtcDta.model.EtcDtaVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 환경교육 정책자료실 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyEtcDta.service
 * - EnvEduPlcyEtcDtaServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyEtcDtaServiceImpl
 * @Description : 환경교육 정책자료실 서비스 구현 클래스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.envEduPlcyEtcDtaService")
public class EnvEduPlcyEtcDtaServiceImpl extends PlumAbstractServiceImpl implements EnvEduPlcyEtcDtaService {

    @Resource(name = "front.envEduPlcyEtcDtaDao")
    private EnvEduPlcyEtcDtaDao envEduPlcyEtcDtaDao;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private FileDao fileDao;

    /**
     * 사업운영자료 목록 조회
     *
     * @param etcDtaVo
     * @return list
     * @throws Exception
     * @Title : selectEtcDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @Override
    public List<EtcDtaVo> selectEtcDtaList(EtcDtaVo etcDtaVo) throws Exception {
        return envEduPlcyEtcDtaDao.selectEtcDtaList(etcDtaVo);
    }

    /**
     * 사업운영자료 상세 조회
     *
     * @param etcDtaVo
     * @return bsns oper dta vo
     * @throws Exception
     * @Title : selectEtcDta
     * @Description : 사업운영자료 상세 조회
     */
    @Override
    @Transactional
    public EtcDtaVo selectEtcDta(EtcDtaVo etcDtaVo) throws Exception {
        EtcDtaVo etcDta = envEduPlcyEtcDtaDao.selectEtcDta(etcDtaVo);

        if (etcDta.getAtchFilegrpid() != null && !etcDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(etcDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            etcDta.setAtchFileList(fileList);
        }

        envEduPlcyEtcDtaDao.updateEtcDtaHits(etcDtaVo);

        return etcDta;
    }


}
