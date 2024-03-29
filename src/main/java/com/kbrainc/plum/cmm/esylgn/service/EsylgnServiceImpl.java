package com.kbrainc.plum.cmm.esylgn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.cmm.esylgn.model.EsylgnDao;
import com.kbrainc.plum.cmm.esylgn.model.EsylgnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 간편로그인 관련 요청을 처리하는 서비스 클래스
 *
 * <pre>
 * com.kbrainc.plum.cmm.esylgn.service
 * - EsylgnServiceImpl.java
 * </pre> 
 *
 * @ClassName : CommonServiceImpl
 * @Description : 간편로그인 관련 요청을 처리하는 서비스 클래스
 * @author : KBRAINC
 * @date : 2023. 3. 31.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
public class EsylgnServiceImpl extends PlumAbstractServiceImpl implements EsylgnService {

    @Autowired
    private EsylgnDao esylgnDao;

    /**
    * 간편로그인 userkey로 조회하여 userid를 가져온다.
    *
    * @Title : selectUseridByEsylgnUserkey
    * @Description : 간편로그인 userkey로 조회하여 userid를 가져온다.
    * @param esylgnVo EsylgnVo객체
    * @return String userid값
    * @throws Exception 예외
    */
    public String selectUseridByEsylgnUserkey(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.selectUseridByEsylgnUserkey(esylgnVo);
    }
    
    /**
    * ci로 조회하여 userid와 acnt를 가져온다.
    *
    * @Title : selectUserInfoByCi
    * @Description : ci로 조회하여 userid와 acnt를 가져온다.
    * @param esylgnVo EsylgnVo객체
    * @return EsylgnVo esylgnVo객체(userid, acnt)
    * @throws Exception 예외
    */
    public EsylgnVo selectUserInfoByCi(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.selectUserInfoByCi(esylgnVo);
    }
    
    /**
    * ci에 해당하는 사용자의 userkey를 merge한다.
    *
    * @Title : mergeEsylgnUserkey
    * @Description : ci에 해당하는 사용자의 userkey를 merge한다.
    * @param esylgnVo EsylgnVo객체
    * @return int insert/update로우수
    * @throws Exception 예외
    */
    public int mergeEsylgnUserkey(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.mergeEsylgnUserkey(esylgnVo);
    }
    
    /**
    * userid에 해당하는 사용자의 userkey를 저장한다.
    *
    * @Title : insertEsylgnUserkey
    * @Description : userid에 해당하는 사용자의 userkey를 저장한다.
    * @param esylgnVo EsylgnVo객체
    * @return int insert로우수
    * @throws Exception 예외
    */
    public int insertEsylgnUserkey(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.insertEsylgnUserkey(esylgnVo);
    }
    
    /**
    * 내 userid에서 사용하는 특정 간편로그인 userkey 조회.
    *
    * @Title : selectEsylgnUserkeyByUserid
    * @Description : 내 userid에서 사용하는 특정 간편로그인 userkey 조회.
    * @param esylgnVo EsylgnVo객체
    * @return String 간편로그인 userKey
    * @throws Exception 예외
    */
    public String selectEsylgnUserkeyByUserid(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.selectEsylgnUserkeyByUserid(esylgnVo);
    }
    
    /**
    * 특정사용자의 특정간편로그인 추가 정보 조회.
    *
    * @Title : selectEsylgnAdditionalUserInfo
    * @Description : 특정사용자의 특정간편로그인 추가 정보 조회.
    * @param esylgnVo EsylgnVo객체
    * @return EsylgnVo 간편로그인 추가 사용자정보
    * @throws Exception 예외
    */
    public EsylgnVo selectEsylgnAdditionalUserInfo(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.selectEsylgnAdditionalUserInfo(esylgnVo);
    }
    
    /**
    * 특정사용자의 특정간편로그인 추가 정보 조회(by userkey).
    *
    * @Title : selectEsylgnAdditionalUserInfoByUserkey
    * @Description : 특정사용자의 특정간편로그인 추가 정보 조회(by userkey).
    * @param esylgnVo EsylgnVo객체
    * @return EsylgnVo 간편로그인 추가 사용자정보
    * @throws Exception 예외
    */
    public EsylgnVo selectEsylgnAdditionalUserInfoByUserkey(EsylgnVo esylgnVo) throws Exception {
        return esylgnDao.selectEsylgnAdditionalUserInfoByUserkey(esylgnVo);
    }
    
    /**
    * 특정 userid의 간편로그인코드에 해당하는 정보 삭제.
    *
    * @Title : deleteEsylgnByUseridAndEsylgnCd
    * @Description : 특정 userid의 간편로그인코드에 해당하는 정보 삭제
    * @param esylngVo EsylngVo객체
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteEsylgnByUseridAndEsylgnCd(EsylgnVo esylngVo) throws Exception {
        return esylgnDao.deleteEsylgnByUseridAndEsylgnCd(esylngVo);
    }
    
    /**
    * 특정 userkey의 간편로그인코드에 해당하는 정보 삭제.
    *
    * @Title : deleteEsylgnByUserkeyAndEsylgnCd
    * @Description : 특정 userkey의 간편로그인코드에 해당하는 정보 삭제
    * @param esylngVo EsylngVo객체
    * @return int delete로우수
    * @throws Exception 예외
    */
    public int deleteEsylgnByUserkeyAndEsylgnCd(EsylgnVo esylngVo) throws Exception {
        return esylgnDao.deleteEsylgnByUserkeyAndEsylgnCd(esylngVo);
    }
    
}
