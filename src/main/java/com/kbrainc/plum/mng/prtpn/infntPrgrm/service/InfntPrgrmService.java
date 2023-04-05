package com.kbrainc.plum.mng.prtpn.infntPrgrm.service;

import java.util.List;

import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.model.UserVo;



/**
* 유아환경교육 -> 교육프로그램관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntPrgrm.service
* - InfntPrgrmService.java
* </pre>
**
@ClassName : InfntPrgrmService
* @Description : 유아환경교육 -> 교육프로그램관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InfntPrgrmService {
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectInfntPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmList(InfntPrgrmVo infntPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrm(InfntPrgrmVo infntPrgrm) throws Exception;

    /**
    * 교육프로그램관리 게시글 프로그램 복사 등록
    **
    @Title : insertInfntPrgrmCopy
    * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
    * @param copyPrgrmIds
    * @param userVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertInfntPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectInfntPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return InfntPrgrmVo
    */
    public InfntPrgrmVo selectInfntPrgrmInfo(InfntPrgrmVo infntPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrm(InfntPrgrmVo infntPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertInfntPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntPrgrmTme(InfntPrgrmVo infntPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateInfntPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntPrgrmTme(InfntPrgrmVo infntPrgrm) throws Exception;    
    
    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectInfntPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param infntPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectInfntPrgrmTmeList(InfntPrgrmVo infntPrgrm) throws Exception;    
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    **
    @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param infntPrgrm
    * @return
    * @throws Exception
    * @return List<InfntPrgrmVo>
    */
    public List<InfntPrgrmVo> selectPrgrmSettingList(String rcptMthdCd) throws Exception;
    
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
}
