package com.kbrainc.plum.mng.prtpn.infntPrgrm.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;

/**
* 유아환경교육 -> 교육프로그램관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.model
* - InfntPrgrmDao.java
* </pre>
**
@ClassName : InfntPrgrmDao
* @Description : 유아환경교육 -> 교육프로그램관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface InfntPrgrmDao {
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmList(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectInfntPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return InfntPrgrmVo
    */
    public InfntPrgrmVo selectInfntPrgrmInfo(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrm(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception;
 
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param infntPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(InfntPrgrmVo infntPrgrmVo) throws Exception;
    /**
    * 교육주제 삭제
    **
    @Title : deleteClsfCd
    * @Description : 교육주제 삭제
    * @param infntPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteClsfCd(InfntPrgrmVo infntPrgrmVo) throws Exception;
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param infntPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(InfntPrgrmVo infntPrgrmVo) throws Exception;
    /**
    * 교육주제 등록
    **
    @Title : insertClsfCd
    * @Description : 교육주제 등록
    * @param infntPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertClsfCd(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectInfntPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrmVo) throws Exception;    
}
