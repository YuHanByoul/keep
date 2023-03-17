package com.kbrainc.plum.front.pltfomImprvmPropsl.serivce;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslDao;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 플랫폼 개선 제안 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.serivce
 * - PltfomImprvmPropslServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslServiceImpl
 * @Description : 플랫폼 개선 제안 서비스 구현 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Alias("front.pltfomImprvmPropslService")
@Service("front.pltfomImprvmPropslService")
public class PltfomImprvmPropslServiceImpl extends PlumAbstractServiceImpl implements PltfomImprvmPropslService {

    @Resource(name = "front.pltfomImprvmPropslDao")
    private PltfomImprvmPropslDao pltfomImprvmPropslDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 플랫폼 개선 제안 생성
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 생성
     */
    @Override
    public int insertPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.insertPltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼 개선 제안 수정
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 수정
     */
    @Override
    public int updatePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.updatePltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼 개선 제안 삭제
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 삭제
     */
    @Override
    public int deletePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.deletePltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼 개선 제안 취소
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : cancelPltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 취소
     */
    @Override
    public int cancelPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.cancelPltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼 개선 제안 목록 조회
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
     * 마이페이지 > 플랫폼 개선 제안 목록 조회
     *
     * @param pltfomImprvmPropslVo
     * @return list
     * @throws Exception
     * @Title : selectMyPltfomImprvmPropslList
     * @Description : 마이페이지 > 플랫폼 개선 제안 목록 조회
     */
    @Override
    public List<PltfomImprvmPropslVo> selectMyPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.selectMyPltfomImprvmPropslList(pltfomImprvmPropslVo);
    }

    /**
     * 플랫폼 개선 제안 상세 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl vo
     * @throws Exception
     * @Title : selectPropsl
     * @Description : 플랫폼 개선 제안 상세 조회
     */
    @Override
    public PltfomImprvmPropslVo selectPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        PltfomImprvmPropslVo propsl = pltfomImprvmPropslDao.selectPropsl(pltfomImprvmPropslVo);

        if (propsl != null && propsl.getFilegrpid() != null && !propsl.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(propsl.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            propsl.setFileList(fileList);
        }

        return propsl;
    }

    /**
     * 플랫폼 개선 제안 답변 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl ans vo
     * @throws Exception
     * @Title : selectPropslAns
     * @Description : 플랫폼 개선 제안 답변 조회
     */
    @Override
    public PltfomImprvmPropslAnsVo selectPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        PltfomImprvmPropslAnsVo propslAns = pltfomImprvmPropslDao.selectPropslAns(pltfomImprvmPropslVo);

        if (propslAns != null && propslAns.getFilegrpid() != null && !propslAns.getFilegrpid().equals("0")) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(propslAns.getFilegrpid());
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            propslAns.setFileList(fileList);
        }

        return propslAns;
    }
}
