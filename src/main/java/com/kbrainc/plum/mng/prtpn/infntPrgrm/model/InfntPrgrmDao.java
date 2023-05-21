package com.kbrainc.plum.mng.prtpn.infntPrgrm.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.srvy.model.SrvyVo;

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
    * 교육프로그램관리 게시글 프로그램 복사 등록
    **
    @Title : insertInfntPrgrmCopy
    * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @return
    * @throws Exception
    * @return int
    */
    public int insertInfntPrgrmCopy(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
     * 교육프로그램관리 게시글 프로그램_교육주제 복사 등록
     **
    @Title : insertInfntPrgrmCopy
     * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertInfntPrgrmClsfMapngCopy(InfntPrgrmVo infntPrgrmVo) throws Exception;
    /**
     * 교육프로그램관리 게시글 프로그램_교육대상 복사 등록
     **
    @Title : insertInfntPrgrmCopy
     * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertInfntPrgrmTrgtMapngCopy(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 프로그램_회차 복사 등록
    **
    @Title : insertInfntPrgrmTmeCopy
    * @Description : 교육프로그램관리 게시글 프로그램_회차 복사 등록
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @return
    * @throws Exception
    * @return int
    */
    public int insertInfntPrgrmTmeCopy(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
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
     * 교육프로그램관리 회차 삭제
     *
     * @Title : deleteInfntPrgrmTme
     * @Description : 교육프로그램관리 회차 삭제
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntPrgrmTme(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_신청_교육_대상 삭제
     *
     * @Title : deleteInfntPrgrmAplyEduTrgt
     * @Description : EDU_유아_프로그램_신청_교육_대상 삭제
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntPrgrmAplyEduTrgt(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_신청 삭제
     *
     * @Title : deleteInfntPrgrmAply
     * @Description : EDU_유아_프로그램_신청 삭제
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntPrgrmAply(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
    /**
     * EDU_유아_프로그램_회차_일정 삭제
     *
     * @Title : deleteInfntPrgrmTmeSchdl
     * @Description : EDU_유아_프로그램_회차_일정 삭제
     * @param infntPrgrmVo 교육프로그램관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntPrgrmTmeSchdl(InfntPrgrmVo infntPrgrmVo) throws Exception;
    
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
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    **
    @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param rcptMthdCd
    * @return
    * @throws Exception
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectPrgrmSettingList(@Param("rcptMthdCd") String rcptMthdCd) throws Exception;
    
    /**
    * 교육프로그램관리 복사대상 파일 상세조회
    *
    * @Title : selectInfntCopyPrgrmInfo
    * @Description : 교육프로그램관리 복사대상 파일 상세조회
    * @param infntPrgrmVo 교육프로그램관리 객체
    * @throws Exception 예외
    * @return InfntPrgrmVo
    */
    public InfntPrgrmVo selectInfntCopyPrgrmFileInfo(InfntPrgrmVo infntPrgrmVo) throws Exception;    
    
    /**
     * 유아환경교육(신청자) 설문지 목록 조회
     *
     * @Title : selectAplcntDgstfnSrvyList
     * @Description : 유아환경교육(신청자) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectAplcntDgstfnSrvyList() throws Exception;
    
    /**
     * 유아환경교육(학생) 설문지 목록 조회
     *
     * @Title : selectStdntDgstfnSrvyList
     * @Description : 유아환경교육(학생) 설문지 목록 조회
     * @param 
     * @throws Exception 예외
     * @return List<SrvyVo>
     */
    public List<SrvyVo> selectStdntDgstfnSrvyList() throws Exception;

    /**
     * 회차 삭제시 교육일정에 매핑된 내역이 있는지 조회
     *
     * @param tmeId
     * @return int
     * @throws Exception
     * @Title : selectEduMvmnPrgrmTmeSchdl
     * @Description : 회차 삭제시 교육일정에 매핑된 내역이 있는지 조회
     */
    public int selectEduInfntPrgrmTmeSchdl(String tmeId) throws Exception;
}
