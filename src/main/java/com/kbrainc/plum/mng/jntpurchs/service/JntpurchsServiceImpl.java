package com.kbrainc.plum.mng.jntpurchs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsDao;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 공동구매관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.jntpurchs.service
 * - JntpurchsServiceImpl.java
 * </pre> 
 *
 * @ClassName : JntpurchsServiceImpl
 * @Description : 공동구매관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 01. 18.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class JntpurchsServiceImpl extends PlumAbstractServiceImpl implements JntpurchsService {
    
    @Autowired
    private JntpurchsDao jntpurchsDao;
    
    /**
     * 공동구매모집 정보 등록
     *
     * @Title : insertJntpurchs 
     * @Description : 공동구매모집 정보 등록
     * @param jntpurchsVo JntpurchsVo
     * @return int insert 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertJntpurchs(JntpurchsVo jntpurchsVo) throws Exception {
        int retVal = 0;
        // 상품 등록 목록
        List<JntpurchsTchaidVo> goodsList = jntpurchsVo.getGoodsList();
        // 금액설정 등록 목록
        List<JntpurchsAmtVo> amtList = jntpurchsVo.getAmtList();
        // 1. 공동구매모집 등록
        retVal += jntpurchsDao.insertJntpurchs(jntpurchsVo);
        int jntpurchsid = jntpurchsVo.getJntpurchsid();
        // 2. 상품 등록
        if(goodsList != null && goodsList.size() > 0) {
            JntpurchsTchaidVo goods = null;
            for(int i = 0 ; i < goodsList.size() ; i++) {
                goods = goodsList.get(i);
                goods.setUser(jntpurchsVo.getUser());
                goods.setJntpurchsid(jntpurchsid);
                retVal += jntpurchsDao.insertJntpurchsGoods(goods);
            }
        }
        // 3. 금액 등록
        if(amtList != null && amtList.size() > 0) {
            JntpurchsAmtVo amt = null;
            for(int i = 0 ; i < amtList.size() ; i++) {
                amt = amtList.get(i);
                amt.setUser(jntpurchsVo.getUser());
                amt.setJntpurchsid(jntpurchsid);
                retVal += jntpurchsDao.insertJntpurchsAmt(amt);
            }
        }
        
        return retVal;
    }
     
    /**
     * 공동구매모집 목록 조회
     *
     * @Title : selectJntpurchsList
     * @Description : 공동구매모집 목록 조회
     * @param jntpurchsVo JntpurchsVo 객체
     * @return List<JntpurchsVo> 공동구매모집 목록
     * @throws Exception 예외
     */
    @Override
    public List<JntpurchsVo> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception {
        return jntpurchsDao.selectJntpurchsList(jntpurchsVo);
    }
     
    /**
     * 교구 목록 조회
     *
     * @Title : selectTchaidList
     * @Description : 교구 목록 조회
     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
     * @return List<JntpurchsTchaidVo> 교구 목록
     * @throws Exception 예외
     */
    @Override
    public List<JntpurchsTchaidVo> selectTchaidList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception {
        return jntpurchsDao.selectTchaidList(jntpurchsTchaidVo);
    }
     
    /**
     * 공동구매모집 상세 정보 조회
     *
     * @Title : selectJntpurchsList
     * @Description : 공동구매모집 상세 정보 조회
     * @param jntpurchsVo JntpurchsVo 객체
     * @return JntpurchsVo 공동구매모집 상세 정보
     * @throws Exception 예외
     */
    @Override
    public JntpurchsVo selectJntpurchsInfo(JntpurchsVo jntpurchsVo) throws Exception {
        return jntpurchsDao.selectJntpurchsInfo(jntpurchsVo);
    }
     
    /**
     * 공동구매모집 상품 목록 조회
     *
     * @Title : selectGoodsList
     * @Description : 공동구매모집 상품 목록 조회
     * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
     * @return List<JntpurchsTchaidVo> 공동구매모집 상품 목록
     * @throws Exception 예외
     */
    @Override
    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception {
        return jntpurchsDao.selectGoodsList(jntpurchsTchaidVo);
    }
     
    /**
     * 공동구매모집 수량별 가격 목록 조회
     *
     * @Title : selectGoodsList
     * @Description : 공동구매모집 수량별 가격 목록 조회
     * @param jntpurchsAmtVo JntpurchsAmtVo 객체
     * @return List<JntpurchsAmtVo> 공동구매모집 수량별 가격 목록
     * @throws Exception 예외
     */
    @Override
    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception {
        return jntpurchsDao.selectAmtList(jntpurchsAmtVo);
    }
    
    /**
     * 공동구매모집 정보 업데이트
     *
     * @Title : updateJntpurchs 
     * @Description : 공동구매모집 정보 업데이트
     * @param jntpurchsVo JntpurchsVo
     * @return int update 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int updateJntpurchs(JntpurchsVo jntpurchsVo) throws Exception {
        int retVal = 0;
        
        // 모집에 해당하는 신청건이 있는지 확인
        JntpurchsVo orderInfo = jntpurchsDao.isExistOrder(jntpurchsVo);
        if("Y".equals(orderInfo.getIsExistOrder())) {
            return -1;
        }
        // 상품 등록 목록
        List<JntpurchsTchaidVo> goodsList = jntpurchsVo.getGoodsList();
        // 금액설정 등록 목록
        List<JntpurchsAmtVo> amtList = jntpurchsVo.getAmtList();
        // 1. 공동구매모집 업데이트
        retVal += jntpurchsDao.updateJntpurchs(jntpurchsVo);
        int jntpurchsid = jntpurchsVo.getJntpurchsid();
        // 2-1. 상품 삭제
        retVal += jntpurchsDao.deleteGoods(jntpurchsVo);
        // 2-2. 상품 등록
        if(goodsList != null && goodsList.size() > 0) {
            JntpurchsTchaidVo goods = null;
            for(int i = 0 ; i < goodsList.size() ; i++) {
                goods = goodsList.get(i);
                goods.setUser(jntpurchsVo.getUser());
                goods.setJntpurchsid(jntpurchsid);
                retVal += jntpurchsDao.insertJntpurchsGoods(goods);
            }
        }
        // 3-1. 금액 삭제
        retVal += jntpurchsDao.deleteAmt(jntpurchsVo);
        // 3-2. 금액 등록
        if(amtList != null && amtList.size() > 0) {
            JntpurchsAmtVo amt = null;
            for(int i = 0 ; i < amtList.size() ; i++) {
                amt = amtList.get(i);
                amt.setUser(jntpurchsVo.getUser());
                amt.setJntpurchsid(jntpurchsid);
                retVal += jntpurchsDao.insertJntpurchsAmt(amt);
            }
        }
        
        return retVal;
    }
    
    /**
     * 공동구매모집 정보 삭제
     *
     * @Title : deleteJntpurchs 
     * @Description : 공동구매모집 정보 삭제
     * @param jntpurchsVo JntpurchsVo
     * @return int delete 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int deleteJntpurchs(JntpurchsVo jntpurchsVo) throws Exception {
        int retVal = 0;
        
        // 모집에 해당하는 신청건이 있는지 확인
        JntpurchsVo orderInfo = jntpurchsDao.isExistOrder(jntpurchsVo);
        if("Y".equals(orderInfo.getIsExistOrder())) {
            return -1;
        }
        retVal += jntpurchsDao.deleteJntpurchs(jntpurchsVo);        
        return retVal;
    }
    
    /**
     * 공동구매신청 목록 조회
     *
     * @Title : selectJntpurchsOrderList
     * @Description : 공동구매신청 목록 조회
     * @param jntpurchsOrderVo JntpurchsOrderVo 객체
     * @return List<JntpurchsOrderVo> 공동구매신청 목록
     * @throws Exception 예외
     */
    @Override
    public List<JntpurchsOrderVo> selectJntpurchsOrderList(JntpurchsOrderVo jntpurchsOrderVo) throws Exception {
        return jntpurchsDao.selectJntpurchsOrderList(jntpurchsOrderVo);
    }
    
}