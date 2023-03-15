package com.kbrainc.plum.front.lend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.front.lend.model.LendAplyTrgtVo;
import com.kbrainc.plum.front.lend.model.LendAplyVo;
import com.kbrainc.plum.front.lend.model.LendDao;
import com.kbrainc.plum.front.lend.model.LendRndVo;
import com.kbrainc.plum.front.lend.model.LendVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;

/**
 * 
 * 교구 대여 (사용자) 서비스    
 *
 * <pre>
 * com.kbrainc.plum.front.lend.service
 * - LendServiceImpl.java
 * </pre> 
 *
 * @ClassName : LendServiceImpl
 * @Description :교구 대여 (사용자) 서비스
 * @author : KBRAINC
 * @date : 2021. 03.03.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("front.lendServiceImpl")
@Alias("front.lendServiceImpl")
public class LendServiceImpl extends PlumAbstractServiceImpl implements LendService { 

	@Resource(name = "front.lendDao")
	LendDao lendDao;
	
    /**
     * @Title : insertLendAply
     * @Description : 대여 신청 등록 
     * @param LendAplyVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
	@Transactional
    public int insertLendAply(LendAplyVo lendAplyVo) throws Exception{
	    int resInt = 0;
	    resInt += lendDao.insertLendAply(lendAplyVo);
	    //resInt += lendDao.insertLendAplyTrgt(lendAplyVo);
        return resInt;
    }
    /**
     * @Title : insertLendAplyTrgt
     * @Description : 대여 신청 대상 등록 
     * @param LendAplyVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public int insertLendAplyTrgt(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.insertLendAply(lendAplyVo);
    }
    /**
     * @Title : selectLendRankList
     * @Description : 대여 신청 대상 등록 
     * @throws Exception
     * @return List<Map<String,Object>> 객체
     */
    public List<Map<String,Object>> selectLendRankList() throws Exception{
        return lendDao.selectLendRankList();
    }
    /**
     * @Title : selectLendList
     * @Description : 대여 목록 호출  
     * @param LendVo
     * @throws Exception
     * @return List<LendVo> 객체
     */
    public List<LendVo> selectLendList(LendVo lendVo) throws Exception{
        return lendDao.selectLendList(lendVo);
    }
    /**
     * @Title : selectLend
     * @Description : 대여 상세  
     * @param LendVo
     * @throws Exception
     * @return LendVo 객체
     */
    public LendVo selectLend(LendVo lendVo) throws Exception{
        return lendDao.selectLend(lendVo);
    }
    /**
     * @Title : selectLendRndList
     * @Description : 대여 차시 리스트 호출  
     * @param LendRndVo
     * @throws Exception
     * @return List<LendRndVo> 객체
     */
    public List<LendRndVo> selectLendRndList(LendRndVo lendRndVo) throws Exception{
        return lendDao.selectLendRndList(lendRndVo);
    }
    /**
     * @Title : selectLendRndList
     * @Description : 대여 후기 목록 호출  
     * @param LendAplyVo
     * @throws Exception
     * @return List<LendAplyVo> 객체
     */
    public List<LendAplyVo> selectLendReplyList(LendAplyVo lendAplyVo) throws Exception{
        
        List<LendAplyVo> list = lendDao.selectLendReplyList(lendAplyVo);
        for(LendAplyVo vo :list) {
            vo.setRgtrAcnt(CommonUtil.maskingText(vo.getRgtrAcnt()));
            vo.setRgtrNm(CommonUtil.maskingText(vo.getRgtrNm()));
        }
        return list;
    }
    /**
     * @Title : selectEduSbjctCdList
     * @Description : 교육 주제 코드 리스트 호출   
     * @throws Exception
     * @return List<Map<String,Object>> 객체
     */
    public List<Map<String,Object>> selectEduSbjctCdList() throws Exception{
        return lendDao.selectEduSbjctCdList();
    }
    /**
     * @Title : selectRequestLendRndList
     * @Description : 신청 대여 차시 정보 호출  
     * @param LendAplyVo
     * @throws Exception
     * @return List<LendAplyVo> 객체
     */
    public LendRndVo selectRequestLendRndList(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectRequestLendRndList(lendAplyVo);
    }
    /**
     * @Title : selectEduSbjctCdList
     * @Description : 신청 기관 정보 호출
     * @param LendAplyVo
     * @throws Exception
     * @return List<Map<String,Object>> 객체
     */
    public Map<String,Object> selectRequestInstInfo(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectRequestInstInfo(lendAplyVo);
    }
    /**
     * @Title : checkOverStockYn
     * @Description : 신청 차시중 재고 이상여부 확인
     * @param LendAplyVo
     * @throws Exception
     * @return String 객체
     */
    public String checkOverStockYn(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.checkOverStockYn(lendAplyVo);
    }
    /**
     * @Title : checkLimitOverYn
     * @Description : 신청 차시 및 수량 제한 여부 확인
     * @param LendAplyVo
     * @throws Exception
     * @return String 객체
     */
    public Map<String,Object> checkLimitOverYn(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.checkLimitOverYn(lendAplyVo);
    }
    
    /******************* 대여 이력 ************************/
    /**
     * @Title : selectLendHistList
     * @Description : 대여 이력 리스트 호출 
     * @param LendAplyVo
     * @throws Exception
     * @return String 객체
     */
    public List<LendAplyVo> selectLendHistList(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendHistList(lendAplyVo);
    }
    /**
     * @Title : selectLendAplyInfo
     * @Description : 대여 이력 상세 정보 호출 
     * @param LendAplyVo
     * @throws Exception
     * @return LendAplyVo
     */
    public LendAplyVo selectLendAplyInfo(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendAplyInfo(lendAplyVo);
    }
    /**
     * @Title : selectLendAplyTrgtHistList
     * @Description : 대여 이력 출고 리스트 호출 
     * @param LendAplyVo
     * @throws Exception
     * @return List<LendAplyTrgtVo>
     */
    public List<LendAplyTrgtVo> selectLendAplyTrgtHistList(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.selectLendAplyTrgtHistList(lendAplyVo);
    }
    /**
     * @Title : updateLendAply
     * @Description : 대여 신청 수정 
     * @param LendAplyVo
     * @throws Exception
     * @return int 객체
     */
    public int updateLendAply(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.updateLendAply(lendAplyVo);
    }
    /**
     * @Title : deleteLendAplyRvw
     * @Description : 대여 신청 후기 삭제  
     * @param LendAplyVo
     * @throws Exception
     * @return int 객체
     */
    public int deleteLendAplyRvw(LendAplyVo lendAplyVo) throws Exception{
        return lendDao.deleteLendAplyRvw(lendAplyVo);
    }
}
