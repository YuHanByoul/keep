package com.kbrainc.plum.front.intro.envEduPlcyDta.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileServiceImpl;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.BsnsOperDtaVo;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.EnvEduPlcyDtaDao;
import com.kbrainc.plum.front.intro.envEduPlcyDta.model.SpcltyDtaVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 환경교육 정책자료실 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.intro.envEduPlcyDta.service
 * - EnvEduPlcyDtaServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvEduPlcyDtaServiceImpl
 * @Description : 환경교육 정책자료실 서비스 구현 클래스
 * @date : 2023. 02. 23.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.envEduPlcyDtaService")
public class EnvEduPlcyDtaServiceImpl extends PlumAbstractServiceImpl implements EnvEduPlcyDtaService {

    @Resource(name = "front.envEduPlcyDtaDao")
    private EnvEduPlcyDtaDao envEduPlcyDtaDao;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private FileDao fileDao;

    /**
     * 사업운영자료 목록 조회
     *
     * @param bsnsOperDtaVo
     * @return list
     * @throws Exception
     * @Title : selectBsnsOperDtaList
     * @Description : 사업운영자료 목록 조회
     */
    @Override
    public List<BsnsOperDtaVo> selectBsnsOperDtaList(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        return envEduPlcyDtaDao.selectBsnsOperDtaList(bsnsOperDtaVo);
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
    @Transactional
    public BsnsOperDtaVo selectBsnsOperDta(BsnsOperDtaVo bsnsOperDtaVo) throws Exception {
        BsnsOperDtaVo bsnsOperDta = envEduPlcyDtaDao.selectBsnsOperDta(bsnsOperDtaVo);
        if (bsnsOperDta.getPdfFilegrpid() != null && !bsnsOperDta.getPdfFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(bsnsOperDta.getPdfFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            bsnsOperDta.setPdfFileList(fileList);
        }

        if (bsnsOperDta.getAtchFilegrpid() != null && !bsnsOperDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(bsnsOperDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            bsnsOperDta.setAtchFileList(fileList);
        }

        envEduPlcyDtaDao.updateBsnsOperDtaHits(bsnsOperDtaVo);

        return bsnsOperDta;
    }

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
        return envEduPlcyDtaDao.selectSpcltyDtaList(spcltyDtaVo);
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
    @Transactional
    public SpcltyDtaVo selectSpcltyDta(SpcltyDtaVo spcltyDtaVo) throws Exception {
        SpcltyDtaVo spcltyDta = envEduPlcyDtaDao.selectSpcltyDta(spcltyDtaVo);

        if (spcltyDta.getPdfFilegrpid() != null && !spcltyDta.getPdfFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(spcltyDta.getPdfFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            spcltyDta.setPdfFileList(fileList);
        }

        if (spcltyDta.getAtchFilegrpid() != null && !spcltyDta.getAtchFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(spcltyDta.getAtchFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            spcltyDta.setAtchFileList(fileList);
        }

        envEduPlcyDtaDao.updateSpcltyDtaHits(spcltyDtaVo);

        return spcltyDta;
    }

}
