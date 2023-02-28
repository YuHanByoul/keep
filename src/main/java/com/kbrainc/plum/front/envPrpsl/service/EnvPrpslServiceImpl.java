package com.kbrainc.plum.front.envPrpsl.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslAnsVo;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslDao;
import com.kbrainc.plum.front.envPrpsl.model.EnvPrpslVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 환경교육 제안 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.envPrpsl.service
 * - EnvPrpslServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : EnvPrpslServiceImpl
 * @Description : 환경교육 제안 서비스 구현 클래스
 * @date : 2023. 02. 10.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.envPrpslService")
@Alias("front.envPrpslService")
public class EnvPrpslServiceImpl extends PlumAbstractServiceImpl implements EnvPrpslSerivce {

    @Resource(name = "front.envPrpslDao")
    private EnvPrpslDao envPrpslDao;

    @Autowired
    private FileDao fileDao;

    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVo
     * @return list
     */
    @Override
    public List<EnvPrpslVo> selectEnvPrpslList(EnvPrpslVo envPrpslVo) throws Exception {
        return envPrpslDao.selectEnvPrpslList(envPrpslVo);
    }

    /**
     * 마이페이지 > 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVo
     * @return list
     * @throws Exception
     * @Title : selectMyEnvPrpslList
     * @Description : 마이페이지 > 환경교육제안 관리 목록 조회
     */
    @Override
    public List<EnvPrpslVo> selectMyEnvPrpslList(EnvPrpslVo envPrpslVo) throws Exception {
        return envPrpslDao.selectMyEnvPrpslList(envPrpslVo);
    }

    /**
     * 환경교육제안 정보 조회
     * Title : selectEnvPrpsl
     * Description : 환경교육제안 정보 조회
     *
     * @param envPrpslVo
     * @return env prpsl vo
     */
    @Override
    public EnvPrpslVo selectEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception {
        EnvPrpslVo envPrpsl = envPrpslDao.selectEnvPrps(envPrpslVo);

        if (envPrpsl != null && envPrpsl.getFilegrpid() != null && !envPrpsl.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(envPrpsl.getFilegrpid().toString()));
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            envPrpsl.setFileList(fileList);
        }
        return envPrpsl;
    }

    /**
     * 환경교육제안 답변 정보 조회
     * Title : selectEnvPrpslAnsInfo
     * Description : 환경교육제안 답변 정보 조회
     *
     * @param envPrpslVo
     * @return env prpsl ans vo
     */
    @Override
    public EnvPrpslAnsVo selectEnvPrpslAns(EnvPrpslVo envPrpslVo) throws Exception {
        EnvPrpslAnsVo envPrpslAns = envPrpslDao.selectEnvPrpslAns(envPrpslVo);

        if (envPrpslAns != null && envPrpslAns.getFilegrpid() != null && !envPrpslAns.getFilegrpid().equals(0)) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(envPrpslAns.getFilegrpid().toString()));
            ArrayList<FileVo> fileList = fileDao.getFileList(fileVo);
            envPrpslAns.setFileList(fileList);
        }
        return envPrpslAns;
    }

    /**
     * 환경교육제안 등록
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : insertEnvPrpsl
     * @Description : 환경교육제안 등록
     */
    public int insertEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception {
        int retVal = 0;
        retVal += envPrpslDao.insertEnvPrpsl(envPrpslVo);
        return retVal;
    }

    /**
     * 환경교육제안 수정
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : updateEnvPrpsl
     * @Description : 환경교육제안 수정
     */
    public int updateEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception {
        int retVal = 0;
        retVal += envPrpslDao.updateEnvPrpsl(envPrpslVo);
        return retVal;
    }

    /**
     * 환경교육제안 삭제
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : deleteEnvPrpsl
     * @Description : 환경교육제안 삭제
     */
    public int deleteEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception {
        int retVal = 0;
        retVal += envPrpslDao.deleteEnvPrpsl(envPrpslVo);
        return retVal;
    }

    /**
     * 환경교육제안 취소
     *
     * @param envPrpslVo
     * @return int
     * @throws Exception
     * @Title : cancelEnvPrpsl
     * @Description : 환경교육제안 취소
     */
    public int cancelEnvPrpsl(EnvPrpslVo envPrpslVo) throws Exception {
        int retVal = 0;
        retVal += envPrpslDao.cancelEnvPrpsl(envPrpslVo);
        return retVal;
    }
}
