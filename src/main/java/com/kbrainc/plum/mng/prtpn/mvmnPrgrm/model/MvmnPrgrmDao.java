package com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;

/**
* 푸름이이동환경교실 -> 교육프로그램관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model
* - MvmnPrgrmDao.java
* </pre>
**
@ClassName : MvmnPrgrmDao
* @Description : 푸름이이동환경교실 -> 교육프로그램관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MvmnPrgrmDao {
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 프로그램 복사 등록
    **
    @Title : insertMvmnPrgrmCopy
    * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @return
    * @throws Exception
    * @return int
    */
    public int insertMvmnPrgrmCopy(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
     * 교육프로그램관리 게시글 프로그램_교육주제 복사 등록
     **
    @Title : insertMvmnPrgrmCopy
     * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnPrgrmClsfMapngCopy(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    /**
     * 교육프로그램관리 게시글 프로그램_교육대상 복사 등록
     **
    @Title : insertMvmnPrgrmCopy
     * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnPrgrmTrgtMapngCopy(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 프로그램_회차 복사 등록
    **
    @Title : insertMvmnPrgrmTmeCopy
    * @Description : 교육프로그램관리 게시글 프로그램_회차 복사 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @return
    * @throws Exception
    * @return int
    */
    public int insertMvmnPrgrmTmeCopy(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectMvmnPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return MvmnPrgrmVo
    */
    public MvmnPrgrmVo selectMvmnPrgrmInfo(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrm(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
 
    /**
     * 교육프로그램관리 회차 삭제
     *
     * @Title : deleteMvmnPrgrmTme
     * @Description : 교육프로그램관리 회차 삭제
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_신청_교육_대상 삭제
     *
     * @Title : deleteMvmnPrgrmAplyEduTrgt
     * @Description : EDU_유아_프로그램_신청_교육_대상 삭제
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnPrgrmAplyEduTrgt(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_신청 삭제
     *
     * @Title : deleteMvmnPrgrmAply
     * @Description : EDU_유아_프로그램_신청 삭제
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnPrgrmAply(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_회차_일정 삭제
     *
     * @Title : deleteMvmnPrgrmTmeSchdl
     * @Description : EDU_유아_프로그램_회차_일정 삭제
     * @param mvmnPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnPrgrmTmeSchdl(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param mvmnPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    /**
    * 교육주제 삭제
    **
    @Title : deleteClsfCd
    * @Description : 교육주제 삭제
    * @param mvmnPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteClsfCd(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param mvmnPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    /**
    * 교육주제 등록
    **
    @Title : insertClsfCd
    * @Description : 교육주제 등록
    * @param mvmnPrgrmVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertClsfCd(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectMvmnPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmTmeList(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;   
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    **
    @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param rcptMthdCd
    * @return
    * @throws Exception
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectPrgrmSettingList(@Param("operFomCd") String operFomCd) throws Exception;
    
    /**
    * 교육프로그램관리 복사대상 파일 상세조회
    *
    * @Title : selectMvmnCopyPrgrmFileInfo
    * @Description : 교육프로그램관리 복사대상 파일 상세조회
    * @param mvmnPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return MvmnPrgrmVo
    */
    public MvmnPrgrmVo selectMvmnCopyPrgrmFileInfo(MvmnPrgrmVo mvmnPrgrmVo) throws Exception;    
    
    /**
     * 푸름이이동환경교실(신청자) 설문지 목록 조회
     *
     * @Title : selectAplcntDgstfnSrvyList
     * @Description : 푸름이이동환경교실(신청자) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectAplcntDgstfnSrvyList() throws Exception;
    
    /**
     * 푸름이이동환경교실(학생) 설문지 목록 조회
     *
     * @Title : selectStdntDgstfnSrvyList
     * @Description : 푸름이이동환경교실(학생) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectStdntDgstfnSrvyList() throws Exception;          
}
