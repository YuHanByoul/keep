package com.kbrainc.plum.cmm.idntyVrfctn.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnFailVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnStartVo;
import com.kbrainc.plum.cmm.idntyVrfctn.model.IdntyVrfctnSuccessVo;

/**
 * 
 * 본인인증 관련 요청을 처리하는 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.cmm.idntyVrfctn.service
 * - IdntyVrfctnService.java
 * </pre> 
 *
 * @ClassName : idntyVrfctn
 * @Description : 본인인증 관련 요청을 처리하는 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2023. 2. 7.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface IdntyVrfctnService {
    
    /**
    * 휴대폰 본인인증 업체정보 검증.
    *
    * @Title : siteInfoVerification
    * @Description : 휴대폰 본인인증 업체정보 검증
    * @param request 요청객체
    * @param returnUrlPath 리턴URL path
    * @param errorUrlPath 에러URL path
    * @return IdntyVrfctnStartVo 검증결과 정보
    * @throws Exception 예외
    */
    public IdntyVrfctnStartVo siteInfoVerification(HttpServletRequest request, String returnUrlPath, String errorUrlPath) throws Exception;
    
    /**
    * 본인인증 수행후 성공데이터 복호화.
    *
    * @Title : decodeIdntyVrfctnSuccessData
    * @Description : 본인인증 수행후 성공데이터 복호화
    * @param session 세션객체
    * @param encodeData 본인인증결과(인코딩데이터)
    * @return IdntyVrfctnSuccessVo 본인인증 복호화 데이터
    * @throws Exception 예외
    */
    public IdntyVrfctnSuccessVo decodeIdntyVrfctnSuccessData(HttpSession session, String encodeData) throws Exception;
    
    /**
    * 본인인증 수행후 실패데이터 복호화.
    *
    * @Title : decodeIdntyVrfctnFailData
    * @Description : 본인인증 수행후 실패데이터 복호화
    * @param encodeData 본인인증결과(인코딩데이터)
    * @return IdntyVrfctnFailVo 본인인증 복호화 데이터
    * @throws Exception 예외
    */
    public IdntyVrfctnFailVo decodeIdntyVrfctnFailData(String encodeData) throws Exception;
}
