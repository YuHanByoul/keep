package com.kbrainc.plum.mng.spce.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.EmailVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.SmsVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.mng.spce.model.SpceRsvtdeVo;
import com.kbrainc.plum.mng.spce.model.SpceVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;

/**
 * 
 * 사용자관리 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - MemberService.java
 * </pre> 
 *
 * @ClassName : MemberService
 * @Description : 사용자관리 서비스 인터페이스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface SpceService {
    
    
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
    public List<Map<String,Object>> selectFcltList(SpceVo SpceVo) throws Exception;       
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
    
    
}