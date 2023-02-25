package com.kbrainc.plum.front.jntpurchs.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.jntpurchs.model.JntpurchsDao;
import com.kbrainc.plum.front.jntpurchs.model.JntpurchsRvwVo;
import com.kbrainc.plum.front.jntpurchs.model.JntpurchsVo;
import com.kbrainc.plum.front.member.model.MemberVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsAmtVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsOrderVo;
import com.kbrainc.plum.mng.jntpurchs.model.JntpurchsTchaidVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 환경교육 교구 공동구매 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.front.jntpurchs.service
 * - JntpurchsServiceImpl.java
 * </pre> 
 *
 * @ClassName : JntpurchsServiceImpl
 * @Description : 환경교육 교구 공동구매 서비스 구현 
 * @author : KBRAINC
 * @date : 2023. 02. 17.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.JntpurchsServiceImpl")
@Alias("front.JntpurchsServiceImpl")
public class JntpurchsServiceImpl extends PlumAbstractServiceImpl implements JntpurchsService {
    
    @Resource(name = "front.JntpurchsDao")
    private JntpurchsDao jntpurchsDao;
    
    /**
    * 환경교육 교구 공동구매 목록 조회
    *
    * @Title : selectJntpurchsList
    * @Description : 환경교육 교구 공동구매 목록 조회
    * @param jntpurchsVo JntpurchsVo 객체
    * @return List<JntpurchsVo> 환경교육 교구 공동구매 목록
    * @throws Exception 예외
    */
    @Override
    public List<JntpurchsVo> selectJntpurchsList(JntpurchsVo jntpurchsVo) throws Exception {
        return jntpurchsDao.selectJntpurchsList(jntpurchsVo);
    }
    
    /**
    * 환경교육 교구 공동구매 상세 정보 조회
    *
    * @Title : selectJntpurchsInfo
    * @Description : 환경교육 교구 공동구매 상세 정보 조회
    * @param jntpurchsVo JntpurchsVo 객체
    * @return JntpurchsVo 환경교육 교구 공동구매 상세 정보
    * @throws Exception 예외
    */
    @Override
    public JntpurchsVo selectJntpurchsInfo(JntpurchsVo jntpurchsVo) throws Exception {
        return jntpurchsDao.selectJntpurchsInfo(jntpurchsVo);
    }
    
    /**
    * 환경교육 교구 공동구매 상품 목록 조회
    *
    * @Title : selectGoodsList
    * @Description : 환경교육 교구 공동구매 상품 목록 조회
    * @param jntpurchsTchaidVo JntpurchsTchaidVo 객체
    * @return List<JntpurchsTchaidVo> 환경교육 교구 공동구매 상품 목록
    * @throws Exception 예외
    */
    @Override
    public List<JntpurchsTchaidVo> selectGoodsList(JntpurchsTchaidVo jntpurchsTchaidVo) throws Exception {
        return jntpurchsDao.selectGoodsList(jntpurchsTchaidVo);
    }
    
    /**
    * 환경교육 교구 공동구매  수량별 가격 목록 조회
    *
    * @Title : selectGoodsList
    * @Description : 환경교육 교구 공동구매  수량별 가격 목록 조회
    * @param jntpurchsAmtVo JntpurchsAmtVo 객체
    * @return List<JntpurchsAmtVo> 환경교육 교구 공동구매  수량별 가격 목록
    * @throws Exception 예외
    */
    @Override
    public List<JntpurchsAmtVo> selectAmtList(JntpurchsAmtVo jntpurchsAmtVo) throws Exception {
        return jntpurchsDao.selectAmtList(jntpurchsAmtVo);
    }
    
    /**
    * 환경교육 교구 공동구매 후기 목록 조회
    *
    * @Title : selectRvwList
    * @Description : 환경교육 교구 공동구매  후기 목록 조회
    * @param jntpurchsRvwVo JntpurchsRvwVo 객체
    * @return List<JntpurchsRvwVo> 후기 목록
    * @throws Exception 예외
    */
    @Override
    public List<JntpurchsRvwVo> selectRvwList(JntpurchsRvwVo jntpurchsRvwVo) throws Exception {
        return jntpurchsDao.selectRvwList(jntpurchsRvwVo);
    }
    
    /**
    * 공동구매 신청 회원 정보 조회
    *
    * @Title : selectUserInfo
    * @Description : 공동구매 신청 회원 정보 조회
    * @param memberVo MemberVo 객체
    * @return MemberVo 공동구매 신청 회원 정보
    * @throws Exception 예외
    */
    @Override
    public MemberVo selectUserInfo(MemberVo memberVo) throws Exception {
        return jntpurchsDao.selectUserInfo(memberVo);
    }
    
    /**
    * 공동구매 신청 등록
    *
    * @Title : insertJntpurchsOrder 
    * @Description : 공동구매 신청 등록
    * @param jntpurchsOrderVo JntpurchsOrderVo
    * @return int insert 로우수
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int insertJntpurchsOrder(JntpurchsOrderVo jntpurchsOrderVo) throws Exception {
        int retVal = 0;
        
        // 1. 수량확인
        JntpurchsVo qntyInfo = jntpurchsDao.selectQntyInfo(jntpurchsOrderVo);
        String qntyWholLmtYn = qntyInfo.getQntyWholLmtYn();
        int qntyWhol = qntyInfo.getQntyWhol();
        String qntyLmtYn = qntyInfo.getQntyLmtYn();
        int qntyLmt = qntyInfo.getQntyLmt();
        int orderQnty = qntyInfo.getOrderQnty();
        int reqQnty = jntpurchsOrderVo.getQnty();
        
        if(qntyWholLmtYn.equals("Y")) { // 수량제한이 있는 경우
            int remainQnty = qntyWhol - orderQnty;
            if(remainQnty < reqQnty) { // 신청수량보다 남아있는 수량이 적은 경우
                return -1;
            }
        }
        if(qntyLmtYn.equals("Y") && reqQnty > qntyLmt) { // 개인 신청 수량 제한이 있고 신청수량이 제한수량보다 큰 경우
            return -2;
        }
        // 2. 신청정보 등록
        retVal += jntpurchsDao.insertJntpurchsOrder(jntpurchsOrderVo);
        int orderid = jntpurchsOrderVo.getOrderid();
        // 3. 신청 상품 등록
        List<JntpurchsTchaidVo> goodsList = jntpurchsOrderVo.getGoodsList();
        if(goodsList != null && goodsList.size() > 0) {
            JntpurchsTchaidVo goods = null;
            for(int i = 0 ; i < goodsList.size() ; i++) {
                goods = goodsList.get(i);
                goods.setUser(jntpurchsOrderVo.getUser());
                goods.setOrderid(orderid);
                retVal += jntpurchsDao.insertJntpurchsOrderGoods(goods);
            }
        }
        
        return retVal;
    }
    
}