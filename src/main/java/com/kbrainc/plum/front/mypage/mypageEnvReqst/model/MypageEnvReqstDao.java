package com.kbrainc.plum.front.mypage.mypageEnvReqst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

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
    List<MypageEnvReqstVo> selectMypageEnvReqstList(MypageEnvReqstVo mypageEnvReqstVo);

    /**
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : selectResveEnvInfo
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public MypageEnvReqstVo selectResveEnvInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectSpceInfo
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public MypageEnvReqstVo selectSpceInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 사유 확인 팝업
     *
     * @Title : selectRsnInfo
     * @Description : 사유 확인 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public MypageEnvReqstVo selectRsnInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 입금정보 팝업
     *
     * @Title : selectDpstInfo
     * @Description : 입금정보 팝업
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public MypageEnvReqstVo selectDpstInfo(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclSpceAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public int insertResveEnvFclSpceAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public int insertResveEnvFclAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAplyHstry
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public int insertResveEnvFclAplyHstry(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(공간 예약)
     *
     * @Title : insertResveEnvSpceAply
     * @Description : 환경교육시설 예약 등록(공간 예약)
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public int insertResveEnvSpceAply(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectFclRsvtdeList
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param mypageEnvReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return MypageEnvReqstVo
     */
    public List<Map<String,Object>> selectFclRsvtdeList(MypageEnvReqstVo mypageEnvReqstVo) throws Exception;
}
