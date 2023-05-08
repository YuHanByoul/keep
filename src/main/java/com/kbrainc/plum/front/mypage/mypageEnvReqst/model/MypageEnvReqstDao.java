package com.kbrainc.plum.front.mypage.mypageEnvReqst.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;

/**
 * 지역 환경교육센처 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.mypageEnvReqst.model
 * - MypageEnvReqstDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MypageEnvReqstDao
 * @Description : 지역 환경교육센처 Dao
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Mapper
public interface MypageEnvReqstDao {

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectMypageEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param mypageEnvReqstVo
     * @return list
     */
    List<EnvReqstVo> selectMypageEnvReqstList(EnvReqstVo envReqstVo);

    /**
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : selectResveEnvInfo
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public EnvReqstVo selectResveEnvInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectSpceInfo
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public EnvReqstVo selectSpceInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 사유 확인 팝업
     *
     * @Title : selectRsnInfo
     * @Description : 사유 확인 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public EnvReqstVo selectRsnInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 입금정보 팝업
     *
     * @Title : selectDpstInfo
     * @Description : 입금정보 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public EnvReqstVo selectDpstInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 후기확인 기능
     *
     * @Title : selectRwvInfo
     * @Description : 후기확인 기능
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public EnvReqstVo selectRwvInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectFclRsvtdeList
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public List<Map<String,Object>> selectFclRsvtdeList(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 예약 신청 취소 처리
     *
     * @Title : insertRsn
     * @Description : 예약 신청 취소 처리
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int insertRsn(EnvReqstVo envReqstVo) throws Exception; 

    /**
     * 후기 작성 기능
     *
     * @Title : insertRvw
     * @Description : 후기 작성 기능
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int insertRvw(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 후기 삭제 기능
     *
     * @Title : deleteRvw
     * @Description : 후기 삭제 기능
     * @param mypageEnvReqstVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteRvw(EnvReqstVo envReqstVo) throws Exception;
}
