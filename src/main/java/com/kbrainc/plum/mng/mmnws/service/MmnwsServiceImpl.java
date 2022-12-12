package com.kbrainc.plum.mng.mmnws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.mmnws.model.MmnwsDao;
import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.mmnws.service
* - MmnwsServiceImpl.java
* </pre>
*
* @ClassName : MmnwsServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 05.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MmnwsServiceImpl extends PlumAbstractServiceImpl implements MmnwsService{
    
    @Autowired
    private MmnwsDao mmnwsDao;
    
    /**
    * 언론보도관리 게시글 목록 조회
    *
    * @Title : selectMmnwsList
    * @Description : 언론보도관리 게시글 목록 조회
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return List<MmnwsVo>
    */
    @Override
    public List<MmnwsVo> selectMmnwsList(MmnwsVo mmnwsVo) throws Exception{
        return mmnwsDao.selectMmnwsList(mmnwsVo);
    }
    
    /**
    * 언론보도관리 게시글 등록
    *
    * @Title : insertMmnws
    * @Description : 언론보도관리 게시글 등록
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int insertMmnws(MmnwsVo mmnwsVo) throws Exception {
        return mmnwsDao.insertMmnws(mmnwsVo);
    }
    
    /**
    * 언론보도관리 게시글 상세정보
    *
    * @Title : selectMmnwsInfo
    * @Description : 언론보도관리 게시글 상세정보
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return MmnwsVo
    */
    @Override
    public MmnwsVo selectMmnwsInfo(MmnwsVo mmnwsVo) throws Exception {
        return mmnwsDao.selectMmnwsInfo(mmnwsVo);
    }
    
    /**
    * 언론보도관리 게시글 수정
    *
    * @Title : updateMmnws
    * @Description : 언론보도관리 게시글 수정
    * @param mmnwsVo 언론보도관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int updateMmnws(MmnwsVo mmnwsVo) throws Exception {
        return mmnwsDao.updateMmnws(mmnwsVo);
    }
    
    /**
    * 언론보도관리 게시글 삭제
    *
    * @Title : deleteMmnws
    * @Description : 언론보도관리 게시글 삭제
    * @param nscvrgids 게시글 id값
    * @throws Exception 예외
    * @return int
    */
    @Override
    public int deleteMmnws(String[] nscvrgids) throws Exception {
        return mmnwsDao.deleteMmnws(nscvrgids);
    }

}
