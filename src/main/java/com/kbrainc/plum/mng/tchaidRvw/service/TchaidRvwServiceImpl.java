package com.kbrainc.plum.mng.tchaidRvw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.tchaidRvw.model.TchaidRvwDao;
import com.kbrainc.plum.mng.tchaidRvw.model.TchaidRvwVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 교구후기관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.tchaidRvw.service
* - TchaidRvwServiceImpl.java
* </pre>
*
* @ClassName : TchaidRvwServiceImpl
* @Description : 교구후기관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class TchaidRvwServiceImpl extends PlumAbstractServiceImpl implements TchaidRvwService{
    
    @Autowired
    private TchaidRvwDao tchaidRvwDao;
    
    /**
    * 교구후기관리 게시글 목록 조회
    *
    * @Title : selectTchaidRvwList
    * @Description : 교구후기관리 게시글 목록 조회
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return List<TchaidRvwVo>
    */
    @Override
    public List<TchaidRvwVo> selectTchaidRvwList(TchaidRvwVo tchaidRvwVo) throws Exception{
        return tchaidRvwDao.selectTchaidRvwList(tchaidRvwVo);
    }
    
    /**
    * 교구후기관리 게시글 상세정보
    *
    * @Title : selectTchaidRvwInfo
    * @Description : 교구후기관리 게시글 상세정보
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return TchaidRvwVo
    */
    @Override
    public TchaidRvwVo selectTchaidRvwInfo(TchaidRvwVo tchaidRvwVo) throws Exception {
        return tchaidRvwDao.selectTchaidRvwInfo(tchaidRvwVo);
    }
    
    /**
    * 교구후기관리 게시글 수정
    *
    * @Title : updateTchaidRvw
    * @Description : 교구후기관리 게시글 수정
    * @param tchaidRvwVo 교구후기관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int updateTchaidRvw(TchaidRvwVo tchaidRvwVo) throws Exception {
        return tchaidRvwDao.updateTchaidRvw(tchaidRvwVo);
    }
    
    /**
    * 교구후기관리 게시글 삭제
    *
    * @Title : deleteTchaidRvw
    * @Description : 교구후기관리 게시글 삭제
    * @param nscvrgids 게시글 id값
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int deleteTchaidRvw(String[] nscvrgids) throws Exception {
        return tchaidRvwDao.deleteTchaidRvw(nscvrgids);
    }
}
