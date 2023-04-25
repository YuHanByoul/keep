package com.kbrainc.plum.mng.rcpmnyAfter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.rcpmnyAfter.model.RcpmnyAfterDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstDao;
import com.kbrainc.plum.mng.resveReqst.model.ResveReqstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 입금 후 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyAfter.service
* - RcpmnyAfterServiceImpl.java
* </pre>
*
* @ClassName : RcpmnyAfterServiceImpl
* @Description : 입금 후 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class RcpmnyAfterServiceImpl extends PlumAbstractServiceImpl implements RcpmnyAfterService {
    
    @Autowired
    private RcpmnyAfterDao rcpmnyAfterDao;
    
    @Autowired
    private ResveReqstDao resveReqstDao;
    
    /**
    * 입금 후 목록 조회
    *
    * @Title : selectRcpmnyAfterList
    * @Description : 입금 후 목록 조회
    * @param rcpmnyAfterVo 입금 후정보 객체
    * @throws Exception 예외
    * @return List<RcpmnyAfterVo>
    */
    @Override
    public List<ResveReqstVo> selectRcpmnyAfterList(ResveReqstVo resveReqstVo) throws Exception{
        return rcpmnyAfterDao.selectRcpmnyAfterList(resveReqstVo);
    }

    /**
    * 입금 후 상세정보
    *
    * @Title : selectRcpmnyAfterInfo
    * @Description : 입금 후 상세정보
    * @param rcpmnyAfterVo 입금 후 객체
    * @throws Exception 예외
    * @return RcpmnyAfterVo
    */
    @Override
    public ResveReqstVo selectRcpmnyAfterInfo(ResveReqstVo resveReqstVo) throws Exception {
        return rcpmnyAfterDao.selectRcpmnyAfterInfo(resveReqstVo);
    }

    /**
    * 입금 확인 전 상세정보
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
    * @param rcpmnyAfterVo 입금 확인 전 객체
    * @throws Exception 예외
    * @return RcpmnyAfterVo
    */
    @Override
    public ResveReqstVo selectDsptCheckInfo(ResveReqstVo resveReqstVo) throws Exception {
        return rcpmnyAfterDao.selectDsptCheckInfo(resveReqstVo);
    }

    /**
     * 입금 확인 취소 처리
     *
     * @Title : updateDsptCheckCancel
     * @Description : 입금 확인 취소 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional
    public int updateDsptCheckCancel(ResveReqstVo resveReqstVo) throws Exception {
        
        int resInt = 0 ;
        resInt += rcpmnyAfterDao.updateDsptCheckCancel(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyids(resveReqstVo.getAplyids());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }

    /**
     * 환불요청 처리
     *
     * @Title : updateRefnd
     * @Description : 환불요청 처리
     * @param rcpmnyAfterVo 입금 후 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional
    public int updateRefnd(ResveReqstVo resveReqstVo) throws Exception {
        
        int resInt = 0 ;
        resInt += rcpmnyAfterDao.updateRefnd(resveReqstVo);
        ResveReqstVo paramVo = new ResveReqstVo();
        // 상태변경이력 추가
        paramVo.setAplyid(resveReqstVo.getAplyid());
        paramVo.setUser(resveReqstVo.getUser());
        resInt += resveReqstDao.insertHstry(paramVo);
        return resInt;
    }
}
