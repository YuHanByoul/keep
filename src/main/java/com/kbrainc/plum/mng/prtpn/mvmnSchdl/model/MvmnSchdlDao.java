package com.kbrainc.plum.mng.prtpn.mvmnSchdl.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 푸름이환경이동교실 -> 교육일정관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnSchdl.model
* - MvmnSchdlDao.java
* </pre>
**
@ClassName : MvmnSchdlDao
* @Description : 푸름이환경이동교실 -> 교육일정관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MvmnSchdlDao {
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectMvmnSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return List<MvmnSchdlVo>
    */
    public List<MvmnSchdlVo> selectMvmnSchdlList(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertMvmnSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectMvmnSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return MvmnSchdlVo
    */
    public MvmnSchdlVo selectMvmnSchdlInfo(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateMvmnSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param mvmnSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception;

    
    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : deleteMvmnSchdl
     * @Description : 교육일정관리 게시글 삭제
     * @param mvmnSchdlVo 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception;

    
    /**
    * 푸름이환경이동교실_프로그램_일정_일자 삭제
    **
    @Title : deleteDeSttId
    * @Description : 푸름이환경이동교실_프로그램_일정_일자 삭제
    * @param mvmnSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteDeSttId(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    /**
    * 푸름이환경이동교실_프로그램_일정_프로그램 삭제
    **
    @Title : deletePrgrmSttId
    * @Description : 푸름이환경이동교실_프로그램_일정_프로그램 삭제
    * @param mvmnSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deletePrgrmSttId(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    /**
     * 푸름이환경이동교실_프로그램_일정_프로그램_회차 삭제
     **
     * @Title : deletePrgrmTmeSchdl
     * @Description : 푸름이환경이동교실_프로그램_일정_프로그램_회차 삭제
     * @param infntSchdlVo
     * @return
     * @throws Exception
     * @return int
     */
    public int deletePrgrmTmeSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception;        
    /**
    * 푸름이환경이동교실_프로그램_일정_일자 등록
    **
    @Title : insertDeSttId
    * @Description : 푸름이환경이동교실_프로그램_일정_일자 등록
    * @param mvmnSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertDeSttId(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    /**
    * 푸름이환경이동교실_프로그램_일정_프로그램 등록
    **
    @Title : insertPrgrmSttId
    * @Description : 푸름이환경이동교실_프로그램_일정_프로그램 등록
    * @param mvmnSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertPrgrmSttId(MvmnSchdlVo mvmnSchdlVo) throws Exception;
    /**
    * 푸름이환경이동교실_프로그램_회차_일정 등록
    **
    @Title : insertPrgrmTmeSchdl
    * @Description : 푸름이환경이동교실_프로그램_회차_일정 등록
    * @param mvmnSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertPrgrmTmeSchdl(MvmnSchdlVo mvmnSchdlVo) throws Exception;    

    /**
     * 교육일정관리 교육일정 리스트 조회
     *
     * @Title : selectMvmnSchdlIdList
     * @Description : 교육일정관리 교육일정 리스트 조회
     * @param sareaId
     * @throws Exception 예외
     * @return int
     */
    public List<MvmnSchdlVo> selectMvmnSchdlIdList(@Param("sareaId") String sareaId) throws Exception; 
    
    /**
     * 교육일정관리 신청 카운트 조회
     * @Title : selectMvmnAplyCnt
     * @Description : 교육일정관리 신청 카운트 조회
     * @param mvmnSchdlVo
     * @throws Exception 예외
     * @return int
     */
    public int selectMvmnAplyCnt(MvmnSchdlVo mvmnSchdlVo) throws Exception;

    /**
    * 프로그램 일정 일자 삭제
    *
    * @Title : deleteMvmnSchdlDe
    * @param mvmnSchdlVo
    * @return
    * @return int
    */
    public int deleteMvmnSchdlDe(MvmnSchdlVo mvmnSchdlVo);

    /**
    * 프로그램 일정 프로그램 삭제
    *
    * @Title : deleteMvmnSchdlPrgrm
    * @param mvmnSchdlVo
    * @return
    * @return int
    */
    public int deleteMvmnSchdlPrgrm(MvmnSchdlVo mvmnSchdlVo);

    /**
    * 프로그램 회차 일정 삭제
    *
    * @Title : deleteMvmnTmeSchdl
    * @param mvmnSchdlVo
    * @return
    * @return int
    */
    public int deleteMvmnTmeSchdl(MvmnSchdlVo mvmnSchdlVo);            
}
