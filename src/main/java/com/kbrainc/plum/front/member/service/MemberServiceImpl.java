package com.kbrainc.plum.front.member.service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.type.Alias;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.kbrainc.plum.cmm.esylgn.model.EsylgnDao;
import com.kbrainc.plum.front.member.model.MemberAcntPswdFindVo;
import com.kbrainc.plum.front.member.model.MemberDao;
import com.kbrainc.plum.front.member.model.MemberInstSearchVo;
import com.kbrainc.plum.front.member.model.MemberInstVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 회원정보 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.front.member.service
* - MemberServiceImpl.java
* </pre> 
*
* @ClassName : MemberServiceImpl
* @Description : 회원정보 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAIN Company. All Rights Reserved 
*/
@Service("front.memberServiceImpl")
@Alias("front.memberServiceImpl")
public class MemberServiceImpl extends PlumAbstractServiceImpl implements MemberService {

    @Resource(name = "front.memberDao")
    private MemberDao memberDao;
    
    @Autowired
    private EsylgnDao esylgnDao;
    
    @Value("${kakao.restapi.key}")
    private String kakaoRestapiKey;
    
    @Value("${kakao.search.address.api.url}")
    private String kakaoSearchAddressApiUrl;
    
    /**
    * 회원 탈퇴 처리.
    *
    * @Title : withdrawalMember 
    * @Description : 회원 탈퇴 처리
    * @param user 사용자세션정보
    * @param session 세션객체
    * @return int DB변경로우수
    * @throws Exception 예외
    */
    @Transactional
    public int withdrawalMember(UserVo user, HttpSession session) throws Exception {
        int retVal = 0;
        retVal += memberDao.updateMemberDel(user);
        retVal += memberDao.deleteEsylgnByUserid(user);
        
        if (session != null) {
            session.invalidate();
        }
        return retVal;
    }
    
    /**
    *
    * ID 중복 체크
    *
    * @Title : checkDuplicationUser
    * @Description : ID 중복 체크
    * @param memberVo MemberVo객체
    * @return int 
    * @throws Exception 예외
    */
    public int checkDuplicationUser(MemberVo memberVo) throws Exception {
        return memberDao.checkDuplicationUser(memberVo);
    }
    
    /**
    *
    * 사업자등록번호 중복 체크
    *
    * @Title : checkDuplicationBrno
    * @Description : 사업자등록번호 중복 체크
    * @param memberInstVo MemberInstVo객체
    * @return MemberInstVo result와 msg리턴 
    * @throws Exception 예외
    */
    @Transactional
    public MemberInstVo checkDuplicationBrno(MemberInstVo memberInstVo) throws Exception {
        MemberInstVo result = new MemberInstVo();
        boolean isDirect = ("Y".equals(memberInstVo.getDirectYn())) ? true : false; // 직접입력여부
        boolean isPool = false; // 기관풀존재여부
        boolean isInst = false; // 기관존재여부
        String aprvSttsCd = null; // 기관상태코드
        Integer instUserid = null; // 기관담당자아이디(기관담당자 존재여부로 활용)
        
        // 기관 POOL 존재여부 조회
        MemberInstVo result1 = memberDao.selectInstPoolInfoByBrno(memberInstVo);
        
        if (result1 != null) {
            isPool = true;
        }
                
        // 기관 테이블 존재여부와 상태값, 기관담당자 존재여부 조회
        MemberInstVo result2 = memberDao.selectInstInfoByBrno(memberInstVo);
         
        if (result2 != null) {
            isInst = true;
            aprvSttsCd = result2.getAprvSttsCd(); // 신청:1, 승인:2, 거부:9
            instUserid = result2.getInstUserid();
            result.setInstid(result2.getInstid());
        }
        
        if (!isDirect && isPool && !isInst) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(!isDirect && isPool && isInst && "1".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(!isDirect && isPool && isInst && "2".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(!isDirect && isPool && isInst && "9".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } else if(!isDirect && isPool && isInst && "1".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(!isDirect && isPool && isInst && "2".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(!isDirect && isPool && isInst && "9".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } else if (isDirect && isPool && !isInst) {
            result.setResult(true);
            result.setMsg("기관정보풀에 있는 사업자등록번호입니다. 기관정보에 있는 데이터를 불러옵니다.");
            result.setDataLoad(true);
            result.setInstNm(result1.getInstNm());
            result.setInstTypeCd(result1.getInstTypeCd());
            result.setBrno(result1.getBrno());
            result.setRprsvNm(result1.getRprsvNm());
            result.setTelno(result1.getTelno());
            result.setZip(result1.getZip());
            result.setAddr(result1.getAddr());
            result.setAddrDtl(result1.getAddrDtl());
            result.setSignguCd(getSignguCdWithaddress(result1.getAddr()));
        } else if(isDirect && isPool && isInst && "1".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(isDirect && isPool && isInst && "2".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(isDirect && isPool && isInst && "9".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } else if(isDirect && isPool && isInst && "1".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(isDirect && isPool && isInst && "2".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(isDirect && isPool && isInst && "9".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } else if (isDirect && !isPool && !isInst) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(isDirect && !isPool && isInst && "1".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(isDirect && !isPool && isInst && "2".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("이미 가입된 기관정보 입니다. 해당 기관 담당자에 문의하세요.");
        } else if(isDirect && !isPool && isInst && "9".equals(aprvSttsCd) && instUserid != null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } else if(isDirect && !isPool && isInst && "1".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(isDirect && !isPool && isInst && "2".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(true);
            result.setMsg("사용가능한 사업자등록번호입니다.");
        } else if(isDirect && !isPool && isInst && "9".equals(aprvSttsCd) && instUserid == null) {
            result.setResult(false);
            result.setMsg("승인 거부된 기관정보 입니다. 담당자에 문의하세요.");
        } 
        
        return result;
    }
    
    /**
    *
    * 회원 정보 등록
    *
    * @Title : insertMember
    * @Description : 
    * @param memberVo MemberVo객체
    * @param memberInstVo MemmberInstVo객체
    * @return int 
    * @throws Exception 예외
    */
    @Transactional
    public int insertMember(MemberVo memberVo, MemberInstVo memberInstVo) throws Exception {
        int retVal = 0;
        
        if (memberVo.getUserid() == null) {
            String password = null;
            if (memberVo.getPswd() != null) {
                password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberVo.getPswd().getBytes("UTF-8")));
                memberVo.setPswd(password);
            }
    
            // 회원정보 입력
    	    retVal += memberDao.insertMember(memberVo);
        } else {
            // 회원정보 수정(동의여부, 개인정보 유효기간만 update)
            retVal += memberDao.updateMemberAgreYnAndPrvcVldty(memberVo);
        }
	    memberInstVo.setUserid(memberVo.getUserid());
	    
	    // 기관정보 입력 또는 수정
        if ("I".equals(memberVo.getType())) { // 기관회원 가입일때
            if (memberInstVo.getInstid() != null) { // 기관udpate
                retVal += memberDao.updateInst(memberInstVo);
            } else { // 기관insert
                retVal += memberDao.insertInst(memberInstVo);
                retVal += memberDao.updateInstCd(memberInstVo.getInstid());
            }
            memberVo.setInstid(memberInstVo.getInstid());
            // 기관담당자_역할_코드(마스터) update
            memberDao.updateUserInstMaster(memberVo);
        }
	    
	    // 맞춤환경정보 입력
	    if (memberVo.getEnvfldCds() != null & memberVo.getEnvfldCds().length > 0) {
	        retVal += memberDao.insertEnvfld(memberVo);
        }
	    
	    // 관심분야 입력
        if (memberVo.getItrstfldCds() != null & memberVo.getItrstfldCds().length > 0) {
            retVal += memberDao.insertItrstfld(memberVo);
        }

	    // 디지털원패스 userKey 입력
	    if (memberVo.getEsylgnVo() != null) {
	        memberVo.getEsylgnVo().setUserid(String.valueOf(memberVo.getUserid()));
	        retVal += esylgnDao.insertEsylgnUserkey(memberVo.getEsylgnVo());
	    }
	   
	    return retVal;
    }

    /**
    * ci에 해당하는 userid 조회.
    *
    * @Title : selectUseridByCI
    * @Description : ci에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByCI(MemberVo memberVo) throws Exception {
        return memberDao.selectUseridByCI(memberVo);
    }
    
    /**
    * ci에 해당하는 사용자정보 조회.
    *
    * @Title : selectUserInfoByCI
    * @Description : ci에 해당하는 사용자정보 조회
    * @param memberVo MemberVo객체
    * @return MemberVo 사용자정보
    * @throws Exception 예외
    */
    public MemberVo selectUserInfoByCI(MemberVo memberVo) throws Exception {
        return memberDao.selectUserInfoByCI(memberVo);
    }
    
    /**
    * 부모ci와 이름에 해당하는 userid 조회.
    *
    * @Title : selectUseridByParntsCIandName
    * @Description : 부모ci와 이름에 해당하는 userid 조회
    * @param memberVo MemberVo객체
    * @return String userid
    * @throws Exception 예외
    */
    public String selectUseridByParntsCIandName(MemberVo memberVo) throws Exception {
        return memberDao.selectUseridByParntsCIandName(memberVo);
    }
    
    /**
    * 기관풀 검색 리스트 조회
    *
    * @Title       : selectInstSearchList 
    * @Description : 기관풀 검색 리스트 조회
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return List<MemberInstSearchVo> 기관검색목록
    * @throws Exception 예외
    */
    public List<MemberInstSearchVo> selectInstSearchList(MemberInstSearchVo memberInstSearchVo) throws Exception {
        return memberDao.selectInstSearchList(memberInstSearchVo);
    }
    
    /**
    * 기관 정보 조회(기관풀)
    *
    * @Title       : selectInstPoolInfo 
    * @Description : 기관 정보 조회(기관풀)
    * @param memberInstSearchVo MemberInstSearchVo객체
    * @return MemberInstSearchVo 기관정보
    * @throws Exception 예외
    */
    public MemberInstSearchVo selectInstPoolInfo(MemberInstSearchVo memberInstSearchVo) throws Exception {
        return memberDao.selectInstPoolInfo(memberInstSearchVo);
    }
    
    /**
    * 주소로 시군구 코드를 가져온다(카카오 주소API).
    *
    * @Title : getSignguCdWithaddress
    * @Description : 주소로 시군구 코드를 가져온다(카카오 주소API)
    * @param addr 주소
    * @return String 시군구코드
    */
    public String getSignguCdWithaddress(String addr) {
        try { 
            HttpHeaders headers = new HttpHeaders(); 
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8"))); 
            headers.add("Authorization", String.format("KakaoAK %s", kakaoRestapiKey)); 
            String url = String.format("%s?analyze_type=exact&page=1&size=1&query=%s", kakaoSearchAddressApiUrl, addr);
             
            @SuppressWarnings("rawtypes") 
            ResponseEntity response = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity(headers), String.class); 

            JSONParser jsonParser = new JSONParser(); 
            try { 
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString()); 
                JSONArray array = (JSONArray) jsonObject.get("documents"); 
                if(array.size() != 0){ 
                    JSONObject objectDoc = (JSONObject) ((JSONObject) array.get(0)).get("address");
                    return objectDoc.get("h_code").toString().substring(0, 5);
                } 
            } catch (ParseException e) { 
                e.printStackTrace(); 
            } 
        }
        catch(RestClientException e) { 
            e.printStackTrace(); 
        } 
        
        return "";
    }
    
    /**
    * 기관 유형 코드 호출 
    *
    * @Title       : selectInstTypeCdList 
    * @Description : 기관 유형 코드 호출 
    * @param Map<String,String> 객체
    * @return List<Map<String,String>> 기관 유형 코드 목록
    * @throws Exception 예외
    */
    public List<Map<String,String>> selectInstTypeCdList() throws Exception {
        return memberDao.selectInstTypeCdList();
    }
    
    /**
    * ci에 해당하는 회원과 어린이회원의 계정정보를 조회한다.
    *
    * @Title : selectAcntFromCiList
    * @Description : ci에 해당하는 회원과 어린이회원의 계정정보를 조회한다
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return List<MemberAcntPswdFindVo> 계정 목록
    * @throws Exception 예외
    */
    public List<MemberAcntPswdFindVo> selectAcntFromCiList(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception {
        return memberDao.selectAcntFromCiList(memberAcntPswdFindVo);
    }
    
    /**
    * ci와 acnt에 해당하는 회원 정보를 조회한다.
    *
    * @Title : selectAcntFromCi
    * @Description : ci와 acnt에 해당하는 회원 정보를 조회한다.
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return MemberAcntPswdFindVo 계정 정보
    * @throws Exception 예외
    */
    public MemberAcntPswdFindVo selectAcntFromCi(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception {
        return memberDao.selectAcntFromCi(memberAcntPswdFindVo);
    }
    
    /**
    * 비밀번호 수정(비밀번호 찾기).
    *
    * @Title : updatePassword
    * @Description : 비밀번호 수정(비밀번호 찾기).
    * @param memberAcntPswdFindVo MemberAcntPswdFindVo객체
    * @return int update로우수
    * @throws Exception 예외
    */
    public int updatePassword(MemberAcntPswdFindVo memberAcntPswdFindVo) throws Exception {
        String password = null;
        password = Hex.encodeHexString(MessageDigest.getInstance("SHA3-512").digest(memberAcntPswdFindVo.getPswd().getBytes("UTF-8")));
        memberAcntPswdFindVo.setPswd(password);
        
        return memberDao.updatePassword(memberAcntPswdFindVo);
    }
    
    /**
     * 회원 상세 정보 호출 
     *
     * @Title : selectMemberInfo
     * @Description : 회원 상세 정보 호출 
     * @param memberVo MemberVo객체
     * @return MemberVo 사용자정보
     * @throws Exception 예외
     */
     public MemberVo selectMemberInfo(MemberVo memberVo) throws Exception{    
         return memberDao.selectMemberInfo(memberVo);
     }
}