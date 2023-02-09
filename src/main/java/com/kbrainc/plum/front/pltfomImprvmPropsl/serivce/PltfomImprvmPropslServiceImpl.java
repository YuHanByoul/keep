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

    @Override
    public int insertPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.insertPltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    @Override
    public int updatePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.updatePltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    @Override
    public int deletePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.deletePltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    @Override
    public int cancelPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.cancelPltfomImprvmPropsl(pltfomImprvmPropslVo);
    }

    @Override
    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception {
        return pltfomImprvmPropslDao.selectPltfomImprvmPropslList(pltfomImprvmPropslVo);
    }

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
