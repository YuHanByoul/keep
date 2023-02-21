package com.kbrainc.plum.front.cntst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

/**
* 공모전 Dao 클래스
*
* <pre>
* com.kbrainc.plum.front.cntst.model
* - CntstDao.java
* </pre>
*
* @ClassName : CntstDao
* @Description : 공모전 Dao 클래스
* @author : JD
* @date : 2023. 2. 14.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.cntstDao")
public interface CntstDao {

    /**
    * 공모전 목록 조회
    *
    * @Title : selectCntstList
    * @Description : 공모전 목록 조회 
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    public List<CntstVo> selectCntstList(CntstVo cntstVo) throws Exception;
    
    /**
    * 공모전 상세 조회
    *
    * @Title : selectCntstInfo
    * @Description : 공모전 상세 조회
    * @param cntstVo
    * @throws Exception
    * @return CntstVo
    */
    public CntstVo selectCntstInfo(CntstVo cntstVo) throws Exception;
    
    /**
    * 조회수 +1
    *
    * @Title : updateCntstHits
    * @Description : 조회수 +1
    * @param cntstVo
    * @throws Exception
    * @return int
    */
    public int updateCntstHits(CntstVo cntstVo) throws Exception;
    
    /**
    * 게시글 첨부파일 목록
    *
    * @Title : selectCntstFileList
    * @Description : 게시글 첨부파일 목록
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    public List<CntstVo> selectCntstFileList(CntstVo cntstVo) throws Exception;
    
    /**
    * 공모전 분야 정보
    *
    * @Title : selectCntstFldMapngInfo
    * @Description : 공모전 분야 정보
    * @param cntstVo
    * @throws Exception
    * @return List<CntstVo>
    */
    public List<CntstVo> selectCntstFldMapngInfo(CntstVo cntstVo) throws Exception;

    /**
    * 공모전 신청 유저 정보
    *
    * @Title : selectUserAplyInfo
    * @Description : 공모전 신청 유저 정보
    * @param userVo
    * @throws Exception
    * @return CntstAplyVo
    */
    public CntstAplyVo selectUserAplyInfo(UserVo userVo) throws Exception;
    
    /**
    * 공모전 신청 등록
    *
    * @Title : insertCntstAply
    * @Description : 공모전 신청 등록
    * @param cntstAplyVo
    * @throws Exception
    * @return int
    */
    public int insertCntstAply(CntstAplyVo cntstAplyVo) throws Exception;

    /**
    * 공모전 신청(환경방학 일기장 프로젝트) 등록
    *
    * @Title : insertCntstAplySchl
    * @Description : 공모전 신청(환경방학 일기장 프로젝트) 등록
    * @param cntstAplySchlVo
    * @throws Exception
    * @return int
    */
    public int insertCntstAplySchl(List<CntstAplySchlVo> cntstAplySchlVo) throws Exception;
}
