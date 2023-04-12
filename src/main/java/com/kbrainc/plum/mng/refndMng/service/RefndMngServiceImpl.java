package com.kbrainc.plum.mng.refndMng.service;

import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.mng.refndMng.model.RefndMngDao;
import com.kbrainc.plum.mng.refndMng.model.RefndMngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.refndMng.service
* - RefndMngServiceImpl.java
* </pre>
*
* @ClassName : RefndMngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class RefndMngServiceImpl extends PlumAbstractServiceImpl implements RefndMngService {
    
    @Autowired
    private RefndMngDao refndMngDao;
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectRefndMngList
    * @Description : 시설 목록 조회
    * @param refndMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<RefndMngVo> selectRefndMngList(RefndMngVo refndMngVo) throws Exception{
        return refndMngDao.selectRefndMngList(refndMngVo);
    }

    /**
    * 시설 목록 조회
    *
    * @Title : selectRefndMngCompleteList
    * @Description : 시설 목록 조회
    * @param refndMngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<RefndMngVo>
    */
    @Override
    public List<RefndMngVo> selectRefndMngCompleteList(RefndMngVo refndMngVo) throws Exception{
        return refndMngDao.selectRefndMngCompleteList(refndMngVo);
    }

    
    /**
    * 시설 상세정보
    *
    * @Title : selectRefndMngInfo
    * @Description : 시설 상세정보
    * @param refndMngVo 시설 객체
    * @throws Exception 예외
    * @return RefndMngVo
    */
    @Override
    public RefndMngVo selectRefndMngInfo(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.selectRefndMngInfo(refndMngVo);
    }

    /**
     * 입금 확인 처리
     *
     * @Title : updateRefndComplete
     * @Description : 입금 확인 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int updateRefndComplete(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.updateRefndComplete(refndMngVo);
    }

    /**
     * 예약 신청 취소 처리
     *
     * @Title : updateRefndCancel
     * @Description : 예약 신청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int updateRefndCancel(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.updateRefndCancel(refndMngVo);
    }

    /**
     * 환불완료취소 처리
     *
     * @Title : updateRefndRollback
     * @Description : 예약 신청 취소 처리
     * @param refndMngVo 입금 전 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    public int updateRefndRollback(RefndMngVo refndMngVo) throws Exception {
        return refndMngDao.updateRefndRollback(refndMngVo);
    }
}
