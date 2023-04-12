package com.kbrainc.plum.front.mypage.mypageEnvReqst.service;

import com.kbrainc.plum.front.mypage.mypageEnvReqst.model.MypageEnvReqstDao;
import com.kbrainc.plum.front.mypage.mypageEnvReqst.model.MypageEnvReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.mypageEnvReqst.service
 * - MypageEnvReqstServiceImpl.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MypageEnvReqstServiceImpl
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Service
public class MypageEnvReqstServiceImpl extends PlumAbstractServiceImpl implements MypageEnvReqstService {

    @Autowired
    private MypageEnvReqstDao mypageEnvReqstDao;

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectMypageEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param mypageEnvReqstVo
     * @return list
     */
    @Override
    public List<MypageEnvReqstVo> selectMypageEnvReqstList(MypageEnvReqstVo mypageEnvReqstVo) {
        return mypageEnvReqstDao.selectMypageEnvReqstList(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : selectResveEnvInfo
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public MypageEnvReqstVo selectResveEnvInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.selectResveEnvInfo(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectSpceInfo
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public MypageEnvReqstVo selectSpceInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.selectSpceInfo(mypageEnvReqstVo);
    }

    /**
     * 사유 확인 팝업
     *
     * @Title : selectRsnInfo
     * @Description : 사유 확인 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public MypageEnvReqstVo selectRsnInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.selectRsnInfo(mypageEnvReqstVo);
    }

    /**
     * 입금정보 팝업
     *
     * @Title : selectDpstInfo
     * @Description : 입금정보 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public MypageEnvReqstVo selectDpstInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.selectDpstInfo(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclSpceAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public int insertResveEnvFclSpceAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.insertResveEnvFclSpceAply(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public int insertResveEnvFclAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.insertResveEnvFclAply(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAplyHstry
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public int insertResveEnvFclAplyHstry(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.insertResveEnvFclAplyHstry(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 등록(공간 예약)
     *
     * @Title : insertResveEnvSpceAply
     * @Description : 환경교육시설 예약 등록(공간 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public int insertResveEnvSpceAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.insertResveEnvSpceAply(mypageEnvReqstVo);
    }

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectFclRsvtdeList
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public List<Map<String,Object>> selectFclRsvtdeList(MypageEnvReqstVo mypageEnvReqstVo) throws Exception {
        return mypageEnvReqstDao.selectFclRsvtdeList(mypageEnvReqstVo);
    }
}
