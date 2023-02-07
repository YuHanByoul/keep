package com.kbrainc.plum.mng.envPrpsl.service;

import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslAnsVO;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslDao;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 환경교육제안 관리 ServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.mng.envPrpsl.service
 * - EnvPrpslServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvPrpslServiceImpl
 * @Description : 환경교육제안 관리 ServiceImpl
 * @date : 2023. 01. 30.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service
public class EnvPrpslServiceImpl extends PlumAbstractServiceImpl implements EnvPrpslService {
    @Autowired
    private EnvPrpslDao envPrpslDao;

    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVO
     * @return list
     */
    @Override
    public List<EnvPrpslVO> selectEnvPrpslList(EnvPrpslVO envPrpslVO) {
        return envPrpslDao.selectEnvPrpslList(envPrpslVO);
    }

    /**
     * 환경교육제안 정보 조회
     * Title : selectEnvPrpsInfo
     * Description : 환경교육제안 정보 조회
     *
     * @param envPrpslVO
     * @return env prpsl vo
     */
    @Override
    public EnvPrpslVO selectEnvPrpsInfo(EnvPrpslVO envPrpslVO) {
        return envPrpslDao.selectEnvPrpsInfo(envPrpslVO);
    }

    /**
     * 환경교육제안 답변 정보 조회
     * Title : selectEnvPrpslAnsInfo
     * Description : 환경교육제안 답변 정보 조회
     *
     * @param envPrpslAnsVO
     * @return env prpsl ans vo
     */
    @Override
    public EnvPrpslAnsVO selectEnvPrpslAnsInfo(EnvPrpslAnsVO envPrpslAnsVO) {
        return envPrpslDao.selectEnvPrpslAnsInfo(envPrpslAnsVO);
    }

    /**
     * 환경교육제안 관리 답변 등록
     * Title : insertEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 등록
     *
     * @param envPrpslAnsVO
     */
    @Override
    public boolean insertEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO) {
        boolean result = false;
        if(envPrpslDao.updatePrcsSttsCd(envPrpslAnsVO)) result = true;
        else result = false;

        if("113103".equals(envPrpslAnsVO.getPrcsSttsCd()) || "113104".equals(envPrpslAnsVO.getPrcsSttsCd())){
            if(envPrpslDao.insertEnvPrpslAnswer(envPrpslAnsVO)) result = true;
            else result = false;
        }

        return result;
    }

    /**
     * 환경교육제안 관리 답변 수정
     * Title : updateEnvPrpslAnswer
     * Description : 환경교육제안 관리 답변 수정
     *
     * @param envPrpslAnsVO
     * @return boolean
     */
    @Override
    public boolean updateEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO) {
        boolean result = false;
        if(envPrpslDao.updatePrcsSttsCd(envPrpslAnsVO) && envPrpslDao.updateEnvPrpslAnswer(envPrpslAnsVO)) result = true;
        else result = false;
        return result;
    }
}
