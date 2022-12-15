package com.kbrainc.plum.mng.inst.service;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.cmm.file.service.FileService;
import com.kbrainc.plum.cmm.file.service.FileStorageService;
import com.kbrainc.plum.cmm.service.SmsService;
import com.kbrainc.plum.mng.inst.model.InstDao;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;
import com.kbrainc.plum.mng.member.model.ContractVo;
import com.kbrainc.plum.mng.member.model.EmailVo;
import com.kbrainc.plum.mng.member.model.LoginHistVo;
import com.kbrainc.plum.mng.member.model.MemberDao;
import com.kbrainc.plum.mng.member.model.MemberDtlVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.member.model.SmsVo;
import com.kbrainc.plum.mng.member.model.TempPwdVo;
import com.kbrainc.plum.rte.constant.Constant;
import com.kbrainc.plum.rte.exception.MailSendFailException;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.mail.model.MailRcptnVo;
import com.kbrainc.plum.rte.util.mail.model.MailVo;
import com.kbrainc.plum.rte.util.mail.service.MailService;

/**
 * 
 * 사용자관리 서비스 구현 클래스.
 *
 * <pre>
 * com.kbrainc.plum.mng.member.service
 * - MemberServiceImpl.java
 * </pre> 
 *
 * @ClassName : MemberServiceImpl
 * @Description : 사용자관리 서비스 구현 클래스.
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class InstServiceImpl extends PlumAbstractServiceImpl implements InstService {

    @Autowired
    private InstDao instDao;
    
    @Autowired
    private MemberDao memberDao;

    /*
     * @Autowired @Qualifier("MailService") private MailService mailService;
     * 
     * @Autowired private FileService fileService;
     * 
     * @Autowired private FileStorageService fileStorageService;
     * 
     * @Autowired private SmsService smsService;
     * 
     * //@Value("${front.server.host}") private String frontServerHost;
     * 
     * @Autowired private TemplateEngine templateEngine;
     */
    
    
    /**
    * 기관정보 목록 리스트
    *
    * @Title       : selectInstList 
    * @Description : 기관정보 목록 리스트
    * @param param InstVo instvo 객체
    * @return List<InstVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<InstVo> selectInstList(InstVo instVo) throws Exception{
        return instDao.selectInstList(instVo);
    }
    /**
     * 기관정보 저장.
     *
     * @Title       : insertInst 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int insertInst(InstVo instVo) throws Exception{
        return instDao.insertInst(instVo);
    }
    
    /**
     * 기관정보 수정.
     *
     * @Title       : updateInst 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int updateInst(InstVo instVo) throws Exception{
        return instDao.updateInst(instVo);
    }
    
    /**
     * 기관 상세 정보 조회
     *
     * @Title       : selectInstInfo 
     * @Description : 사용자정보 저장.
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */

    public InstVo selectInstInfo(InstVo instVo) throws Exception{
        return instDao.selectInstInfo(instVo);
    }
    /**
    * 기관 담당자 목록 리스트
    *
    * @Title       : selectManagerList 
    * @Description : 기관정보 목록 리스트
    * @param param InstVo instVo 객체
    * @return List<MemberVo> 기관정보 목록
    * @throws Exception 예외
    */
    public List<MemberVo> selectManagerList(InstVo instVo) throws Exception{
        return instDao.selectManagerList(instVo);
    }
    /**
     * 기관 담당자 목록 리스트(선택)
     *
     * @Title       : selectInstMemberList 
     * @Description : 기관 담당자 목록 리스트(선택)
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MemberVo> selectInstMemberList(InstVo instVo) throws Exception{
        return instDao.selectInstMemberList(instVo);
    };
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberListForRegister 
     * @Description : 기관정보 목록 리스트
     * @param param InstVo instVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MemberVo> selectInstRegisterMemberList(InstVo instVo) throws Exception{
        return instDao.selectInstRegisterMemberList(instVo);
    }
    
    /**
     * 기관 담당자 등록 
     *
     * @Title       : insertInstUser 
     * @Description : 기관 담당자 등록
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    @Transactional
    public int insertInstUser(MemberVo memberVo) throws Exception{
        
        int resVal = 0;
        
        //1.마스터 관리자(기존 기관 회원 존재) 여부 확인  
        List<MemberVo> userlist  = instDao.selectInstMemberList(memberVo);
        
        boolean isThereInstMasterRole = false; 
        
        for(MemberVo vo : userlist) {
            //마스터 관리자 있는지 여부 확인
            if(!CommonUtil.isEmpty(vo.getInstpicRoleCd()) & vo.getInstpicRoleCd().equals("109101") ) {
                isThereInstMasterRole = true;
            }
        }
        memberVo.setInstpicRoleCd((isThereInstMasterRole)?"109102":"109101");
        resVal = memberDao.updateInstMemberRole(memberVo);
        
        //2.분양사이트 존재 여부 확인 후 존재시 기관관리자 역할 부여
        InstVo instVo = new InstVo();
        instVo.setInstid(memberVo.getInstid());
        if(instDao.checkInstSiteRegisterYn(instVo).equals("Y")) {
            memberVo.setRoleid(5);
            resVal += memberDao.insertRoleId(memberVo);
        }
        return resVal;
    }
    
    /**
     * 기관 담당자 삭제 
     *
     * @Title       : deleteInstUser 
     * @Description : 기관 담당자 등록
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public int deleteInstUser(InstVo instVo) throws Exception{
        int resVal = 0;
        resVal = instDao.deleteInstMemberRole(instVo);
        instVo.setRoleid(5);
        resVal += instDao.deleteInstRoleUser(instVo);
       return resVal;
    }
    /**
     * 기관 담당자 역할 일괄 업데이트  
     *
     * @Title       : updateInstRoleAllUser 
     * @Description : 기관회원 조회
     * @param param MemberVo memberVo 객체
     * @return List<MemberVo>  기관 담당자 역할 일괄 업데이트 
     * @throws Exception 예외
     */
    public int updateInstRoleAllUser(MemberVo memberVo) throws Exception{
       return instDao.updateInstRoleAllUser(memberVo);
    }
    /**
     * 사업자번호 중복 확인
     *
     * @Title       : checkDuplicatedBrnoYn 
     * @Description : 사업자번호 중복 확인
     * @param InstVo instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
    public String checkDuplicatedBrnoYn(InstVo instVo) throws Exception{
        return instDao.checkDuplicatedBrnoYn(instVo);
     }
}