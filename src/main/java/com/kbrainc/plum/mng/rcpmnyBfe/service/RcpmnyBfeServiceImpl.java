package com.kbrainc.plum.mng.rcpmnyBfe.service;

import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeDao;
import com.kbrainc.plum.mng.rcpmnyBfe.model.RcpmnyBfeVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 입금 전 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.rcpmnyBfe.service
* - RcpmnyBfeServiceImpl.java
* </pre>
*
* @ClassName : RcpmnyBfeServiceImpl
* @Description : 입금 전 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class RcpmnyBfeServiceImpl extends PlumAbstractServiceImpl implements RcpmnyBfeService {
    
    @Autowired
    private RcpmnyBfeDao rcpmnyBfeDao;
    
    /**
    * 입금 전 목록 조회
    *
    * @Title : selectRcpmnyBfeList
    * @Description : 입금 전 목록 조회
    * @param rcpmnyBfeVo 입금 전정보 객체
    * @throws Exception 예외
    * @return List<RcpmnyBfeVo>
    */
    @Override
    public List<RcpmnyBfeVo> selectRcpmnyBfeList(RcpmnyBfeVo rcpmnyBfeVo) throws Exception{
        return rcpmnyBfeDao.selectRcpmnyBfeList(rcpmnyBfeVo);
    }

    /**
    * 입금 전 상세정보
    *
    * @Title : selectRcpmnyBfeInfo
    * @Description : 입금 전 상세정보
    * @param rcpmnyBfeVo 입금 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    @Override
    public RcpmnyBfeVo selectRcpmnyBfeInfo(RcpmnyBfeVo rcpmnyBfeVo) throws Exception {
        return rcpmnyBfeDao.selectRcpmnyBfeInfo(rcpmnyBfeVo);
    }

    /**
    * 입금 확인 전 상세정보
    *
    * @Title : selectDsptCheckInfo
    * @Description : 입금 확인 전 상세정보
    * @param rcpmnyBfeVo 입금 확인 전 객체
    * @throws Exception 예외
    * @return RcpmnyBfeVo
    */
    @Override
    public RcpmnyBfeVo selectDsptCheckInfo(RcpmnyBfeVo rcpmnyBfeVo) throws Exception {
        return rcpmnyBfeDao.selectDsptCheckInfo(rcpmnyBfeVo);
    }

    /**
     * 입금 확인 처리
     *
     * @Title : updateDsptCheck
     * @Description : 입금 확인 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int updateDsptCheck(RcpmnyBfeVo rcpmnyBfeVo) throws Exception {
        return rcpmnyBfeDao.updateDsptCheck(rcpmnyBfeVo);
    }

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateResveCancel
     * @Description : 예약 신청 취소 처리
     * @param rcpmnyBfeVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int updateResveCancel(RcpmnyBfeVo rcpmnyBfeVo) throws Exception {
        return rcpmnyBfeDao.updateResveCancel(rcpmnyBfeVo);
    }
}
