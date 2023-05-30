package com.kbrainc.plum.mng.envPrpsl.service;

import com.kbrainc.plum.cmm.service.SmsNhnService;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslAnsVO;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslDao;
import com.kbrainc.plum.mng.envPrpsl.model.EnvPrpslVO;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private NtcnDao ntcnDao;

    @Autowired
    private SmsNhnService smsNhnService;
    /**
     * 환경교육제안 관리 목록 조회
     * Title : selectEnvPrpslList
     * Description : 환경교육제안 관리 목록 조회
     *
     * @param envPrpslVO
     * @return list
     */
    @Override
    public List<EnvPrpslVO> selectEnvPrpslList(EnvPrpslVO envPrpslVO) throws Exception {
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
    public EnvPrpslVO selectEnvPrpsInfo(EnvPrpslVO envPrpslVO) throws Exception {
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
    public EnvPrpslAnsVO selectEnvPrpslAnsInfo(EnvPrpslAnsVO envPrpslAnsVO) throws Exception {
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
    @Transactional
    public boolean insertEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO) throws Exception{
        boolean result = false;
        if(envPrpslDao.updatePrcsSttsCd(envPrpslAnsVO)) result = true;
        else result = false;

        if("113103".equals(envPrpslAnsVO.getPrcsSttsCd()) || "113104".equals(envPrpslAnsVO.getPrcsSttsCd())){
            if(envPrpslDao.insertEnvPrpslAnswer(envPrpslAnsVO)) result = true;
            else result = false;
        }

        if ("113104".equals(envPrpslAnsVO.getPrcsSttsCd())) {
            EnvPrpslVO param = new EnvPrpslVO();
            param.setPrpslid(envPrpslAnsVO.getPrpslid());
            EnvPrpslVO envPrpslVO = envPrpslDao.selectEnvPrpsInfo(param);

            // 알림
            insertNtcn(envPrpslVO);

            // SMS
            String smsMsg = "[환경보전협회] 환경 교육 제안 게시글에 답변이 등록되었습니다.";
            String[] phoneList = new String[]{envPrpslVO.getMoblphon()};
            smsNhnService.sendSms(smsMsg, phoneList, "");
        }

        return result;
    }

    private int insertNtcn(EnvPrpslVO envPrpslVO) throws Exception {
        NtcnVo ntcnVo = new NtcnVo();
        ntcnVo.setUserid(envPrpslVO.getUserid());

        ntcnVo.setTtl("환경 교육 제안 게시글에 답변 등록");
        ntcnVo.setCn("환경 교육 제안에 남겨주신 글에 답변이 등록되었습니다.\r\n"
                + "자세한 내용은 내 문의 내역에서 확인해 주십시오.\r\n");
        ntcnVo.setMvmnurl("/front/mypage/inqry/envPrpslDetail.html?prpslid=" + envPrpslVO.getPrpslid());
        ntcnVo.setKndCd("245102");
        ntcnVo.setInqYn("N");

        return ntcnDao.insertNtcn(ntcnVo);
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
    @Transactional
    public boolean updateEnvPrpslAnswer(EnvPrpslAnsVO envPrpslAnsVO) throws Exception {
        boolean result = false;
        if(envPrpslDao.updatePrcsSttsCd(envPrpslAnsVO) && envPrpslDao.updateEnvPrpslAnswer(envPrpslAnsVO)) result = true;
        else result = false;
        return result;
    }
}
