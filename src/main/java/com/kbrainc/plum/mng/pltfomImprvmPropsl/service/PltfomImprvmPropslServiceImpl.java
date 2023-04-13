package com.kbrainc.plum.mng.pltfomImprvmPropsl.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslDao;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
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
 * com.kbrainc.plum.mng.pltfomImprvmPropsl.service
 * - PltfomImprvmPropslServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslServiceImpl
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 구현 클래스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class PltfomImprvmPropslServiceImpl extends PlumAbstractServiceImpl implements PltfomImprvmPropslService {

    @Autowired
    private PltfomImprvmPropslDao pltfomImprvmPropslDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 플랫폼개선제안 목록 조회
     *
     * @param pltfomImprvmPropslVo
     * @return list
     * @throws Exception
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    @Override
    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.selectPltfomImprvmPropslList(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼개선제안 문의 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl vo
     * @throws Exception
     * @Title : selectPltfomImprvmPropsl
     * @Description : 플랫폼개선제안 문의 조회
     */
    @Override
    public PltfomImprvmPropslVo selectPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        PltfomImprvmPropslVo pltfomImprvmPropsl = pltfomImprvmPropslDao.selectPltfomImprvmPropsl(pltfomImprvmPropslVo);

        if(pltfomImprvmPropsl != null && pltfomImprvmPropsl.getFilegrpid() != null && !pltfomImprvmPropsl.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(pltfomImprvmPropsl.getFilegrpid());
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            pltfomImprvmPropsl.setFileList(fileList);
        }

        return pltfomImprvmPropsl;
    }

    /**
     * 플랫폼개선제안 답변 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl ans vo
     * @throws Exception
     * @Title : selectPltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 조회
     */
    @Override
    public PltfomImprvmPropslAnsVo selectPltfomImprvmPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        PltfomImprvmPropslAnsVo pltfomImprvmPropslAns = pltfomImprvmPropslDao.selectPltfomImprvmPropslAns(pltfomImprvmPropslVo);

        if(pltfomImprvmPropslAns != null && pltfomImprvmPropslAns.getFilegrpid() != null && !pltfomImprvmPropslAns.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(pltfomImprvmPropslAns.getFilegrpid());
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            pltfomImprvmPropslAns.setFileList(fileList);
        }

        return pltfomImprvmPropslAns;
    }

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPropslAnsVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    @Override
    @Transactional
    public int insertPltfomImprvmPropslAns(PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo) throws Exception {
        return pltfomImprvmPropslDao.insertPltfomImprvmPropslAns(pltfomImprvmPropslAnsVo);
    }

    /**
     * 플랫폼개선제안 답변 수정
     *
     * @param pltfomImprvmPropslAnsVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 수정
     */
    @Override
    @Transactional
    public int updatePltfomImprvmPropslAns(PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo) throws Exception {
        return pltfomImprvmPropslDao.updatePltfomImprvmPropslAns(pltfomImprvmPropslAnsVo);
    }

    /**
     * 플랫폼개선제안 삭제
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 삭제
     */
    @Override
    @Transactional
    public int deletePltfomImprvmPrpsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.deletePltfomImprvmPrpsl(pltfomImprvmPropslVo);
    }
}
