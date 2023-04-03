package com.kbrainc.plum.front.envReqst.service;

import com.kbrainc.plum.front.envReqst.model.EnvReqstDao;
import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.envReqst.service
 * - EnvReqstServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvReqstServiceImpl
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Service
public class EnvReqstServiceImpl extends PlumAbstractServiceImpl implements EnvReqstService {

    @Autowired
    private EnvReqstDao envReqstDao;

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param envReqstVo
     * @return list
     */
    @Override
    public List<EnvReqstVo> selectEnvReqstList(EnvReqstVo envReqstVo) {
        return envReqstDao.selectEnvReqstList(envReqstVo);
    }

    /**
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : selectResveEnvInfo
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public EnvReqstVo selectResveEnvInfo(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.selectResveEnvInfo(envReqstVo);
    }

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectSpceInfo
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public EnvReqstVo selectSpceInfo(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.selectSpceInfo(envReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclSpceAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public int insertResveEnvFclSpceAply(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.insertResveEnvFclSpceAply(envReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public int insertResveEnvFclAply(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.insertResveEnvFclAply(envReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAplyHstry
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public int insertResveEnvFclAplyHstry(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.insertResveEnvFclAplyHstry(envReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(공간 예약)
     *
     * @Title : insertResveEnvSpceAply
     * @Description : 환경교육시설 예약 등록(공간 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public int insertResveEnvSpceAply(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.insertResveEnvSpceAply(envReqstVo);
    }

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectFclRsvtdeList
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    @Override
    public List<Map<String,Object>> selectFclRsvtdeList(EnvReqstVo envReqstVo) throws Exception {
        return envReqstDao.selectFclRsvtdeList(envReqstVo);
    }
}
