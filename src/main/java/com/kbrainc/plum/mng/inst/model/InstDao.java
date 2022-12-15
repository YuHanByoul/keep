package com.kbrainc.plum.mng.inst.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;

/**
 * 
 * 기관관리 DAO 클래스. 
 *
 * <pre>
 * com.kbrainc.plum.mng.member.model
 * - InstDao.java
 * </pre> 
 *
 * @ClassName : InstDao
 * @Description : 기관관리 DAO 클래스. 
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface InstDao {
    
    /**
    * 기관정보 목록 리스트
    *
    * @Title       : selectInstList 
    * @Description : 기관정보 목록 리스트
    * @param param InstVo instvo 객체
    * @return List<InstVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<InstVo> selectInstList(InstVo instVo) throws Exception;
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
     * 기관 담당자 목록 리스트(선택)
     *
     * @Title       : selectInstMemberList 
     * @Description : 기관 담당자 목록 리스트(선택)
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
     * 분양사이트 존재 여부 확인
     *
     * @Title       : checkInstSiteRegisterYn 
     * @Description : 분양사이트 존재 여부 확인
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public String checkInstSiteRegisterYn(InstVo instVo) throws Exception;
    
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
    * 기관회원 조회
    *
    * @Title       : selectManagerList 
    * @Description : 기관회원 조회
    * @param param InstVo instVo 객체
    * @return List<MemberVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectInstMemberList(MemberVo memberVo) throws Exception;
    /**
     * 기관 담당자 회원정보 수정/삭제 
     *
     * @Title       : selectManagerList 
     * @Description : 기관회원 조회
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public int deleteInstMemberRole(InstVo instVo) throws Exception;
    /**
     * 기관 담당자 역할 삭제 
     *
     * @Title       : selectManagerList 
     * @Description : 기관회원 조회
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public int deleteInstRoleUser(InstVo instVo) throws Exception;
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
    
}