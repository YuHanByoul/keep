package com.kbrainc.plum.mng.rcpmnyAfter.service;

import com.kbrainc.plum.mng.rcpmnyAfter.model.RcpmnyAfterDao;
import com.kbrainc.plum.mng.rcpmnyAfter.model.RcpmnyAfterVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<RcpmnyAfterVo> selectRcpmnyAfterList(RcpmnyAfterVo rcpmnyAfterVo) throws Exception{
        return rcpmnyAfterDao.selectRcpmnyAfterList(rcpmnyAfterVo);
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
    public RcpmnyAfterVo selectRcpmnyAfterInfo(RcpmnyAfterVo rcpmnyAfterVo) throws Exception {
        return rcpmnyAfterDao.selectRcpmnyAfterInfo(rcpmnyAfterVo);
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
    public RcpmnyAfterVo selectDsptCheckInfo(RcpmnyAfterVo rcpmnyAfterVo) throws Exception {
        return rcpmnyAfterDao.selectDsptCheckInfo(rcpmnyAfterVo);
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
    public int updateDsptCheckCancel(RcpmnyAfterVo rcpmnyAfterVo) throws Exception {
        return rcpmnyAfterDao.updateDsptCheckCancel(rcpmnyAfterVo);
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
    public int updateRefnd(RcpmnyAfterVo rcpmnyAfterVo) throws Exception {
        return rcpmnyAfterDao.updateRefnd(rcpmnyAfterVo);
    }
}
