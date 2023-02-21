package com.kbrainc.plum.front.cntst.service;

import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntst.model.CntstAplyVo;
import com.kbrainc.plum.front.cntst.model.CntstDao;
import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
* 공모전 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.cntst.service
* - CntstServiceImpl.java
* </pre>
*
* @ClassName : CntstServiceImpl
* @Description : 공모전 Service 구현 클래스
* @author : JD
* @date : 2023. 2. 14.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.cntstServiceImpl")
@Alias("front.cntstServiceImpl")
public class CntstServiceImpl extends PlumAbstractServiceImpl implements CntstService {

    @Resource(name = "front.cntstDao")
    CntstDao cntstDao;
    
    /**
    * 공모전 목록 조회
    *
    * @Title : selectCntstList
    * @Description : 공모전 목록 조회 
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    @Override
    public List<CntstVo> selectCntstList(CntstVo cntstVo) throws Exception {
        return cntstDao.selectCntstList(cntstVo);
    }
    
    /**
    * 공모전 상세 조회
    *
    * @Title : selectCntstInfo
    * @Description : 공모전 상세 조회
    * @param cntstVo
    * @throws Exception
    * @return CntstVo
    */
    @Override
    public CntstVo selectCntstInfo(CntstVo cntstVo) throws Exception {
        return cntstDao.selectCntstInfo(cntstVo);
    }

    /**
    * 조회수 +1
    *
    * @Title : updateCntstHits
    * @Description : 조회수 +1
    * @param cntstVo
    * @throws Exception
    * @return int
    */
    @Override
    public int updateCntstHits(CntstVo cntstVo) throws Exception {
        return cntstDao.updateCntstHits(cntstVo);
    }

    /**
    * 게시글 첨부파일 목록
    *
    * @Title : selectCntstFileList
    * @Description : 게시글 첨부파일 목록
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    @Override
    public List<CntstVo> selectCntstFileList(CntstVo cntstVo) throws Exception {
        return cntstDao.selectCntstFileList(cntstVo);
    }
    
    /**
    * 공모전 분야 정보
    *
    * @Title : selectCntstFldMapngInfo
    * @Description : 공모전 분야 정보
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    @Override
    public List<CntstVo> selectCntstFldMapngInfo(CntstVo cntstVo) throws Exception {
        return cntstDao.selectCntstFldMapngInfo(cntstVo);
    }
    
    /**
    * 공모전 신청 유저 정보
    *
    * @Title : selectUserAplyInfo
    * @Description : 공모전 신청 유저 정보
    * @param userVo
    * @throws Exception
    * @return CntstAplyVo
    */
    @Override
    public CntstAplyVo selectUserAplyInfo(UserVo userVo) throws Exception {
        return cntstDao.selectUserAplyInfo(userVo);
    }
    
    /**
    * 공모전 신청 등록
    *
    * @Title : insertCntstAply
    * @Description : 공모전 신청 등록
    * @param cntstAplyVo
    * @throws Exception
    * @return int
    */
    @Override
    public int insertCntstAply(CntstAplyVo cntstAplyVo) throws Exception {
        return cntstDao.insertCntstAply(cntstAplyVo);
    }
    
    /**
    * 공모전 신청(환경방학 일기장 프로젝트) 등록
    *
    * @Title : insertCntstAplySchl
    * @Description : 공모전 신청(환경방학 일기장 프로젝트) 등록
    * @param cntstAplySchlVo
    * @throws Exception
    * @return int
    */
    @Override
    public int insertCntstAplySchl(List<CntstAplySchlVo> cntstAplySchlVo) throws Exception {
        return cntstDao.insertCntstAplySchl(cntstAplySchlVo);
    }

}
