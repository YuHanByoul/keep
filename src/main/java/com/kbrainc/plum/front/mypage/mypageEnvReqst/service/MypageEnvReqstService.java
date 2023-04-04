package com.kbrainc.plum.front.mypage.mypageEnvReqst.service;

import com.kbrainc.plum.front.mypage.mypageEnvReqst.model.MypageEnvReqstVo;

import java.util.List;
import java.util.Map;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.mypageEnvReqst.service
 * - MypageEnvReqstService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : MypageEnvReqstService
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface MypageEnvReqstService {

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