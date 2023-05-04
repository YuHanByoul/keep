package com.kbrainc.plum.front.mypage.mypageEnvReqst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;
import com.kbrainc.plum.front.mypage.mypageEnvReqst.model.MypageEnvReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

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
    
    @Autowired
    private ResveReqstDao resveReqstDao;

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectMypageEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param mypageEnvReqstVo
     * @return list
     */
    @Override
    public List<EnvReqstVo> selectMypageEnvReqstList(EnvReqstVo envReqstVo) {
        return mypageEnvReqstDao.selectMypageEnvReqstList(envReqstVo);
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
    public EnvReqstVo selectResveEnvInfo(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectResveEnvInfo(envReqstVo);
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
    public EnvReqstVo selectSpceInfo(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectSpceInfo(envReqstVo);
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
    public EnvReqstVo selectRsnInfo(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectRsnInfo(envReqstVo);
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
    public EnvReqstVo selectDpstInfo(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectDpstInfo(envReqstVo);
    }

    /**
     * 후기확인 기능
     *
     * @Title : selectRwvInfo
     * @Description : 후기확인 기능
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    @Override
    public EnvReqstVo selectRwvInfo(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectRwvInfo(envReqstVo);
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
    public List<Map<String,Object>> selectFclRsvtdeList(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.selectFclRsvtdeList(envReqstVo);
    }

    /**
     * 예약 신청 취소 처리
     *
     * @Title : insertRsn
     * @Description : 예약 신청 취소 처리
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional
    public int insertRsn(EnvReqstVo envReqstVo) throws Exception {
        
        int resInt = 0;
        resInt += mypageEnvReqstDao.insertRsn(envReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(envReqstVo.getAplyid());
        paramVo.setUser(envReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }

    /**
     * 후기 작성
     *
     * @Title : insertRvw
     * @Description : 후기 작성
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int insertRvw(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.insertRvw(envReqstVo);
    }

    /**
     * 후기 삭제
     *
     * @Title : deleteRvw
     * @Description : 후기 삭제
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int deleteRvw(EnvReqstVo envReqstVo) throws Exception {
        return mypageEnvReqstDao.deleteRvw(envReqstVo);
    }
}
