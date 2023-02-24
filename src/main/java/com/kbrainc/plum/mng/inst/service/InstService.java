package com.kbrainc.plum.mng.inst.service;

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
public interface InstService {
    
    /**
    * 기관정보 목록 리스트
    *
    * @Title       : selectInstList 
    * @Description : 기관정보 목록 리스트
    * @param param InstVo instvo 객체
    * @return List<InstVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<InstVo> selectInstList(InstVo instvo) throws Exception;
    /**
     * 기관정보 저장.
     *
     * @Title       : insertInst 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertInst(InstVo instVo) throws Exception;
    
    /**
     * 기관정보 수정.
     *
     * @Title       : updateInst 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int updateInst(InstVo instVo) throws Exception;
    
    /**
     * 기관 상세 정보 조회
     *
     * @Title       : selectInstInfo 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */

    public InstVo selectInstInfo(InstVo instVo) throws Exception;
    /**
    * 기관 담당자 목록 리스트
    *
    * @Title       : selectManagerList 
    * @Description : 기관정보 목록 리스트
    * @param param InstVo instVo 객체
    * @return List<MemberVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectManagerList(InstVo instVo) throws Exception;
    /**
     * 기관 담당자 목록 선택 리스트
     *
     * @Title       : selectInstMemberList 
     * @Description : 기관 담당자 목록 선택 리스트(서태)
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MemberVo> selectInstMemberList(InstVo instVo) throws Exception;
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberListForRegister 
     * @Description : 기관정보 목록 리스트
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MemberVo> selectInstRegisterMemberList(InstVo instVo) throws Exception;
    /**
     * 기관 담당자 등록 
     *
     * @Title       : insertInstUser 
     * @Description : 기관 담당자 등록
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertInstUser(MemberVo memberVo) throws Exception;
    
    /**
     * 기관 담당자 삭제 
     *
     * @Title       : insertInstUser 
     * @Description : 기관 담당자 등록
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteInstUser(InstVo instVo) throws Exception;
    /**
     * 기관 담당자 역할 일괄 업데이트  
     *
     * @Title       : updateInstRoleAllUser 
     * @Description : 기관회원 조회
     * @param param MemberVo memberVo 객체
     * @return List<MemberVo>  기관 담당자 역할 일괄 업데이트 
     * @throws Exception 예외
     */
    public int updateInstRoleAllUser(MemberVo memberVo) throws Exception;
    /**
     * 사업자번호 중복 확인
     *
     * @Title       : checkDuplicatedBrnoYn 
     * @Description : 사업자번호 중복 확인
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public String checkDuplicatedBrnoYn(InstVo instVo) throws Exception;
    /**
    * 기관 유형 코드 목록 호출
    *
    * @Title       : selectInstTypeCdList 
    * @Description : 기관 유형 코드 목록 호출
    * @param param InstVo instVo 객체
    * @return List<Map<String,Object>> 기관정보 목록
    * @throws Exception 예외
    */
    public List<Map<String,Object>> selectInstTypeCdList(InstVo instVo) throws Exception;
    /**
    * 기관 부여 코드 업데이트 
    *
    * @Title       : updateInstNo 
    * @Description : 기관 코드 업데이트
    * @param instVo Instvo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int updateInstCd(InstVo instVo) throws Exception;

       
    
    
}