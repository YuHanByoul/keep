package com.kbrainc.plum.front.envReqst.service;

import com.kbrainc.plum.front.envReqst.model.AplyRsvtdeVo;
import com.kbrainc.plum.front.envReqst.model.EnvReqstVo;
import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;

import java.util.List;
import java.util.Map;

/**
 * 지역 환경교육센처 Service
 *
 * <pre>
 * com.kbrainc.plum.front.envReqst.service
 * - EnvReqstService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnvReqstService
 * @Description : 지역 환경교육센처 Service
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface EnvReqstService {

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnvReqstList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param envReqstVo
     * @return list
     */
    List<EnvReqstVo> selectEnvReqstList(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 후기 리스트 조회
     * Title : selectResveEnvRvwList
     * Description : 환경교육시설 예약 후기 리스트 조회
     *
     * @param envReqstVo
     * @return list
     */
    List<EnvReqstVo> selectResveEnvRvwList(EnvReqstVo envReqstVo);

    /**
     * 환경교육시설 예약 상세화면으로 이동
     *
     * @Title : selectResveEnvInfo
     * @Description : 환경교육시설 예약 상세화면으로 이동
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public EnvReqstVo selectResveEnvInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectSpceInfo
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public EnvReqstVo selectSpceInfo(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclSpceAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public int insertResveEnvFclSpceAply(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAply
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public int insertResveEnvFclAply(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(시설 예약)
     *
     * @Title : insertResveEnvFclAplyHstry
     * @Description : 환경교육시설 예약 등록(시설 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public int insertResveEnvFclAplyHstry(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 등록(공간 예약)
     *
     * @Title : insertResveEnvSpceAply
     * @Description : 환경교육시설 예약 등록(공간 예약)
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public int insertResveEnvSpceAply(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 환경교육시설 예약 상세 데이터 조회
     *
     * @Title : selectFclRsvtdeList
     * @Description : 환경교육시설 예약 상세 데이터 조회
     * @param envReqstVo 환경교육시설 예약 객체
     * @throws Exception 예외
     * @return EnvReqstVo
     */
    public List<Map<String,Object>> selectFclRsvtdeList(EnvReqstVo envReqstVo) throws Exception;

    /**
     * 예약일자 리스트 호출
     *
     * @Title       : selectSpceRsvtList
     * @Description : 예약일자 리스트 호출
     * @param envReqstVo EnvReqstVo envReqstVo 객체
     * @return List<SpceRsvtdeVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<EnvReqstVo> selectSpceRsvtdeList(EnvReqstVo envReqstVo) throws Exception;
    /**
     * 신청 예약중 실시간 예약 및 운영중지 상태 유무 확인 목록 
     *
     * @Title       : selectReservedRsvtdeList
     * @Description : 신청 예약중 실시간 예약 및 운영중지 상태 유무 확인 목록 
     * @param envReqstVo EnvReqstVo EnvReqstVo 객체
     * @return List<AplyRsvtdeVo> 
     * @throws Exception 예외
     */
    public List<AplyRsvtdeVo> selectReservedRsvtdeList(EnvReqstVo envReqstVo) throws Exception;
    /**
     * 시설 공간 목록 호출  
     *
     * @Title       : selectSpceListByFcltid
     * @Description : 시설 공간 목록 호출 
     * @param SpceVo EnvReqstVo EnvReqstVo 객체
     * @return List<SpceVo> 
     * @throws Exception 예외
     */
    public List<SpceVo> selectSpceListByFcltid(SpceVo spceVo) throws Exception;
}
