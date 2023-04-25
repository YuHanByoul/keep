package com.kbrainc.plum.front.envReqst.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.front.bbs.model.PstVo;
import com.kbrainc.plum.front.envReqst.model.EnvReqstDao;
import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;
import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileService fileService;
    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param envReqstVo
     * @return list
     */
    @Override
    public List<EnvReqstVo> selectEnvReqstList(EnvReqstVo envReqstVo) throws Exception{
        List<EnvReqstVo> list = envReqstDao.selectEnvReqstList(envReqstVo);

        for(EnvReqstVo vo : list) {
            FileVo fileVo = new FileVo();

            if (vo.getRprsImgFileid() != null && !vo.getRprsImgFileid().equals(0)) {
                if (vo.getRprsImgFileid() != null && !vo.getRprsImgFileid().equals(0)) {
                    fileVo.setFileid(Integer.parseInt(vo.getRprsImgFileid().toString()));
                    FileVo logoVo= fileService.getFileInfo(fileVo);
                    vo.setRprsImgFileMap(logoVo);
                } else {
                    vo.setRprsImgFileMap(null);
                }
            } else {
                vo.setRprsImgFileMap(null);
            }
        }

        return list;
    }

    /**
     * 환경교육시설 예약 후기 리스트 조회
     * Title : selectResveEnvRvwList
     * Description : 환경교육시설 예약 후기 리스트 조회
     *
     * @param envReqstVo
     * @return list
     */
    @Override
    public List<EnvReqstVo> selectResveEnvRvwList(EnvReqstVo envReqstVo) {
        return envReqstDao.selectResveEnvRvwList(envReqstVo);
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

    /**
     * 예약일자 리스트 호출
     *
     * @Title       : selectSpceRsvtList
     * @Description : 예약일자 리스트 호출
     * @param envReqstVo EnvReqstVo envReqstVo 객체
     * @return List<EnvReqstVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<EnvReqstVo> selectSpceRsvtdeList(EnvReqstVo envReqstVo) throws Exception{
        return envReqstDao.selectSpceRsvtdeList(envReqstVo);
    }
}
