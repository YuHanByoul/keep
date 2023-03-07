package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.mypage.exprtPool.model.MyLctrDmndVo;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyMdfcnHistoryDao;
import com.kbrainc.plum.front.mypage.exprtPool.model.MyMdfcnHistoryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyMdfcnHistoryServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyMdfcnHistoryServiceImpl
 * @Description : 마이페이지 > 환경교육 전문가 풀 관리 > 정보변경이력 서비스 구현 클래스
 * @date : 2023. 03. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class MyMdfcnHistoryServiceImpl extends PlumAbstractServiceImpl implements MyMdfcnHistoryService {
    @Autowired
    private MyMdfcnHistoryDao myMdfcnHistoryDao;

    /**
     * 정보변경이력 목록 조회
     *
     * @param myMdfcnHistoryVo
     * @return list
     * @throws Exception
     * @Title : selectMdfcnHistoryList
     * @Description : 정보변경이력 목록 조회
     */
    @Override
    public List<MyMdfcnHistoryVo> selectMdfcnHistoryList(MyMdfcnHistoryVo myMdfcnHistoryVo) throws Exception {
        return myMdfcnHistoryDao.selectMdfcnHistoryList(myMdfcnHistoryVo);
    }

    @Override
    public String selectMdfcnRsnByDmndId(Integer mdfcnDmndId) throws Exception {
        return myMdfcnHistoryDao.selectMdfcnRsnByDmndId(mdfcnDmndId);
    }

    @Override
    public String selectLogRsnByDmndId(Integer mdfcnDmndId) throws Exception {
        return myMdfcnHistoryDao.selectLogRsnByDmndId(mdfcnDmndId);
    }
}
