package com.kbrainc.plum.mng.pltfomImprvmPrpsl.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslDao;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.pltfomImprvmPrpsl.service
 * - PltfomImprvmPrpslServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPrpslServiceImpl
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 구현 클래스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class PltfomImprvmPrpslServiceImpl extends PlumAbstractServiceImpl implements PltfomImprvmPrpslService {

    @Autowired
    private PltfomImprvmPrpslDao pltfomImprvmPrpslDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 플랫폼개선제안 목록 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return list
     * @throws Exception
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    @Override
    public List<PltfomImprvmPrpslVo> selectPltfomImprvmPrpslList(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception {
        return pltfomImprvmPrpslDao.selectPltfomImprvmPrpslList(pltfomImprvmPrpslVo);
    }

    /**
     * 플랫폼개선제안 문의 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return pltfom imprvm propsl vo
     * @throws Exception
     * @Title : selectPltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 문의 조회
     */
    @Override
    public PltfomImprvmPrpslVo selectPltfomImprvmPrpsl(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception {
        PltfomImprvmPrpslVo pltfomImprvmPrpsl = pltfomImprvmPrpslDao.selectPltfomImprvmPrpsl(pltfomImprvmPrpslVo);

        if (pltfomImprvmPrpsl != null && pltfomImprvmPrpsl.getFilegrpid() != null && !pltfomImprvmPrpsl.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(pltfomImprvmPrpsl.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            pltfomImprvmPrpsl.setFileList(fileList);
        }

        return pltfomImprvmPrpsl;
    }

    /**
     * 플랫폼개선제안 답변 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return pltfom imprvm propsl ans vo
     * @throws Exception
     * @Title : selectPltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 조회
     */
    @Override
    public PltfomImprvmPrpslAnsVo selectPltfomImprvmPrpslAns(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception {
        PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAns = pltfomImprvmPrpslDao.selectPltfomImprvmPrpslAns(pltfomImprvmPrpslVo);

        if (pltfomImprvmPrpslAns != null && pltfomImprvmPrpslAns.getFilegrpid() != null && !pltfomImprvmPrpslAns.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(pltfomImprvmPrpslAns.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            pltfomImprvmPrpslAns.setFileList(fileList);
        }

        return pltfomImprvmPrpslAns;
    }

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPrpslAnsVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    @Override
    @Transactional
    public int insertPltfomImprvmPrpslAns(PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAnsVo) throws Exception {
        int retVal = 0;

        retVal += pltfomImprvmPrpslDao.insertPltfomImprvmPrpslAns(pltfomImprvmPrpslAnsVo);
        retVal += pltfomImprvmPrpslDao.updatePltfomImprvmPrpslStts(pltfomImprvmPrpslAnsVo);

        return retVal;
    }

    /**
     * 플랫폼개선제안 답변 수정
     *
     * @param pltfomImprvmPrpslAnsVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 수정
     */
    @Override
    @Transactional
    public int updatePltfomImprvmPrpslAns(PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAnsVo) throws Exception {
        int retVal = 0;

        retVal += pltfomImprvmPrpslDao.updatePltfomImprvmPrpslAns(pltfomImprvmPrpslAnsVo);
        retVal += pltfomImprvmPrpslDao.updatePltfomImprvmPrpslStts(pltfomImprvmPrpslAnsVo);

        return retVal;
    }

    /**
     * 플랫폼개선제안 삭제
     *
     * @param pltfomImprvmPrpslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 삭제
     */
    @Override
    @Transactional
    public int deletePltfomImprvmPrpsl(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception {
        return pltfomImprvmPrpslDao.deletePltfomImprvmPrpsl(pltfomImprvmPrpslVo);
    }
}
