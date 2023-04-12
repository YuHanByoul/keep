package com.kbrainc.plum.mng.inst.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.kbrainc.plum.mng.inst.model.InstDao;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.MemberDao;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.ntcn.model.NtcnDao;
import com.kbrainc.plum.mng.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
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
    
    @Autowired
    private NtcnDao ntcnDao;
    
    @Autowired @Qualifier("MailNhnService")
    private MailService mailService;
    
    @Autowired
    private TemplateEngine templateEngine;

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
    @Transactional
    public int insertInst(InstVo instVo) throws Exception{
        int retVal = 0;
        
        if ("2".equals(instVo.getAprvSttsCd())) { // 승인
            retVal += instDao.insertInstPool(instVo);
        }
        retVal += instDao.insertInst(instVo);
        retVal += instDao.updateInstCd(instVo);
        return retVal;
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
    @Transactional
    public int updateInst(InstVo instVo) throws Exception{
        int retVal = 0;
        
        if ("2".equals(instVo.getAprvSttsCd())) { // 승인
            retVal += instDao.insertInstPool(instVo);
            
            if(!instVo.getPreAprvSttsCd().equals("2")) {
                //승인 일경우 알림과 이메일 발송
                MemberVo memberVo = new MemberVo();
                memberVo.setInstid(instVo.getInstid());
                memberVo.setInstpicRoleCd("109101");
                List<MemberVo> list = instDao.selectInstMemberList(instVo);
                if(list.size() > 0 ) {
                    MemberVo parmaVo = new MemberVo();
                    parmaVo = list.get(0);
                    parmaVo.setUser(instVo.getUser());
                    insertNtcnForInst(parmaVo);
                    sendEmailForInst(parmaVo);
                }
            }
        }
        retVal += instDao.updateInst(instVo);
        retVal += instDao.updateInstCd(instVo);
        return retVal;
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
    /**
    * 기관 유형 코드 목록 호출
    *
    * @Title       : selectInstTypeCdList 
    * @Description : 기관 유형 코드 목록 호출
    * @param param InstVo instVo 객체
    * @return List<Map<String,Object>> 기관정보 목록
    * @throws Exception 예외
    */
    public List<Map<String,Object>> selectInstTypeCdList(InstVo instVo) throws Exception{
        return instDao.selectInstTypeCdList(instVo);
     }
    /**
    * 기관 부여 코드 업데이트 
    *
    * @Title       : updateInstNo 
    * @Description : 기관 코드 업데이트
    * @param instVo Instvo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int updateInstCd(InstVo instVo) throws Exception{
        return instDao.updateInstCd(instVo);
     }
    /**
     * 기관 승인 알림 등록 
     *
     * @Title       : insertNtcnForInst 
     * @Description : 기관 승인 알림 등록
     * @param instVo Instvo객체
     * @return int insert로우수
     * @throws Exception 예외
     */
     public int insertNtcnForInst(MemberVo memberVo) throws Exception{
             NtcnVo ntcnVo = new NtcnVo();
             ntcnVo.setUserid(memberVo.getUserid());
             ntcnVo.setTtl("기관회원 승인 완료");
             ntcnVo.setCn("기관회원 신청이 승인되었습니다.\r\n"
                     + "기관회원 권한으로 사이트를 이용할 수 있습니다.\r\n"
                     + "지금 바로 국가환경교육 통합플랫폼을 이용해보세요.");
             ntcnVo.setInqYn("N");
             ntcnVo.setKndCd("156102");
             ntcnDao.insertNtcn(ntcnVo);
         return 1;
      }
     /**
      * 기관 승인 이메일 발송 
      *
      * @Title       : sendEmailForInst 
      * @Description : 기관 승인 이메일 발송
      * @param instVo Instvo객체
      * @return int insert로우수
      * @throws Exception 예외
      */
     public int sendEmailForInst(MemberVo memberVo) throws Exception{
         
             StringBuilder contents = new StringBuilder();
             contents.append("<tr><td align=\"center\" style=\"font-family:'맑은 고딕','Malgun Gothic','돋움',dotum,sans-serif;font-size:16px;font-weight:400;font-stretch:normal;font-style:normal;line-height:1.5;letter-spacing:-1px;color:#333333;padding:0 10px;\">");
             contents.append("기관회원 신청이 승인되었습니다.<br /><br />기관회원 권한으로 사이트를 이용할 수 있습니다.<br /><br />지금 바로 국가환경교육 통합플랫폼을 이용해보세요.<br /><br />");
             contents.append("&nbsp;</td></tr><tr><td style=\"height:30px;font-size:0px;mso-line-height-rule:exactly;line-height:0px;\">&nbsp;</td></tr>");
             Context context = new Context();
             context.setVariable("title", "기관회원 승인 완료");
             context.setVariable("content", contents.toString());
             context.setVariable("portalUrl", CommonUtil.portalUrl);
             String mailContents = templateEngine.process("mail/mail_basic_template", context);
             String mailTitle = "기관회원 승인 완료";
             MailVo mailVo = new MailVo(null, memberVo.getEml(), mailTitle, mailContents, Integer.valueOf(memberVo.getUser().getUserid()), "U", memberVo.getUserid());
             Map<String, Object> resMap = mailService.sendMail(mailVo); // 이메일 발송
             String result = (String)resMap.get("result");
         
         return 1;
     }
}