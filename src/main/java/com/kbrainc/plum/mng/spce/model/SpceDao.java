package com.kbrainc.plum.mng.spce.model;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 공간관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.spce.model
 * - InstDao.java
 * </pre> 
 *
 * @ClassName : SpceDao
 * @Description : 공간관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2023. 01. 10.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface SpceDao {
    /**
     * 공간정보 저장
     *
     * @Title       : insertSpce 
     * @Description : 공간정보 저장
     * @param SpceVo SpceVo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertSpce(SpceVo spceVo) throws Exception;
    /**
     * 공간예약일자 등록
     *
     * @Title       : insertSpceRsvtde 
     * @Description : 공간예약일자 등록
     * @param SpceRsvtdeVo SpceRsvtdeVo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertSpceRsvtde(SpceRsvtdeVo sceRsvtdeVo) throws Exception;
    /**
    * 공간 목록 리스트 호출 
    *
    * @Title       : selectSpceList 
    * @Description : 공간 목록 리스트 호출 
    * @param param SpceVo SpceVo 객체
    * @return List<SpceVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<SpceVo> selectSpceList(SpceVo spceVo) throws Exception;
    /**
     * 시설 리스트 호출 
     *
     * @Title       : selectSpceList 
     * @Description : 시설 리스트 호출 
     * @param param SpceVo SpceVo 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectFcltList(SpceVo spceVo) throws Exception;
    /**
    * 공간 상세정보 조회 
    *
    * @Title       : selectSpceInfo 
    * @Description : 공간 상세정보 조회 
    * @param param SpceVo SpceVo 객체
    * @return List<SpceVo> 기관정보 목록
    * @throws Exception 예외
    */
    public SpceVo selectSpceInfo(SpceVo spceVo) throws Exception;
    /**
     * 공간 정보 수정 
     *
     * @Title       : updateSpce 
     * @Description : 공간 정보 수정 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int updateSpce(SpceVo spceVo) throws Exception;
    /**
     * 공간 정보 삭제 
     *
     * @Title       : deleteSpce 
     * @Description : 공간 정보 삭제 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteSpce(SpceVo spceVo) throws Exception;
    /**
     * 공간 예약일자 정보 삭제 
     *
     * @Title       : deleteSpceRsvtde 
     * @Description : 공간 예약일자 정보 삭제 
     * @param SpceVo spceVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteSpceRsvtde(SpceVo spceVo) throws Exception;
    /**
     * 공간 예약 내역 존재 여부 확인  
     *
     * @Title       : isThereSpceRsvt 
     * @Description : 공간 예약일자 정보 삭제 
     * @param SpceVo spceVo객체
     * @return String [Y,N]
     * @throws Exception 예외
     */
    public String isThereSpceRsvt(SpceVo spceVo) throws Exception;
    /**
     * 예약일자 리스트 호출  
     *
     * @Title       : selectSpceRsvtList 
     * @Description : 예약일자 리스트 호출  
     * @param param SpceVo SpceVo 객체
     * @return List<SpceRsvtdeVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<SpceRsvtdeVo> selectSpceRsvtdeList(SpceRsvtdeVo spceRsvtdeVo) throws Exception;
    /**
     * 공간 예약일자 상태 변경 
     *
     * @Title       : updateSpceRsvtde 
     * @Description : 공간 예약일자 상태 변경  
     * @param spceRsvtdeVo spceRsvtdeVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int updateSpceRsvtde(SpceRsvtdeVo spceRsvtdeVo) throws Exception;
    /**
     * 공간 예약일자 날자별 삭제  
     *
     * @Title       : deleteSpceRsvtdeByDate 
     * @Description : 공간 예약일자 날자별 삭제   
     * @param spceRsvtdeVo spceRsvtdeVo 객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteSpceRsvtdeByDate(SpceRsvtdeVo spceRsvtdeVo) throws Exception;
    /**
     * 공간 예약 신청 리스트 호출
     *
     * @Title       : selectFclRsvtdeList 
     * @Description : 공간 예약 신청 리스트 호출
     * @param param SpceVo SpceVo 객체
     * @return List<Map<String,Object>> 기관정보 목록
     * @throws Exception 예외
     */
    public List<Map<String,Object>> selectFclRsvtdeList(SpceRsvtdeVo spceRsvtdeVo) throws Exception;
}