package com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service;

import java.util.List;

import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.model.UserVo;



/**
* 푸름이이동환경교실 -> 교육프로그램관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnPrgrm.service
* - MvmnPrgrmService.java
* </pre>
**
@ClassName : MvmnPrgrmService
* @Description : 푸름이이동환경교실 -> 교육프로그램관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MvmnPrgrmService {
    
    /**
    * 교육프로그램관리 게시글 목록 조회
    *
    * @Title : selectMvmnPrgrmList
    * @Description : 교육프로그램관리 게시글 목록 조회
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmList(MvmnPrgrmVo mvmnPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 등록
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrm(MvmnPrgrmVo mvmnPrgrm) throws Exception;

    /**
    * 교육프로그램관리 게시글 프로그램 복사 등록
    **
    @Title : insertMvmnPrgrmCopy
    * @Description : 교육프로그램관리 게시글 프로그램 복사 등록
    * @param copyPrgrmIds
    * @param userVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertMvmnPrgrmCopy(String[] copyPrgrmIds, UserVo userVo) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 상세조회
    *
    * @Title : selectMvmnPrgrmInfo
    * @Description : 교육프로그램관리 게시글 상세조회
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return MvmnPrgrmVo
    */
    public MvmnPrgrmVo selectMvmnPrgrmInfo(MvmnPrgrmVo mvmnPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 게시글 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 게시글 수정
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrm(MvmnPrgrmVo mvmnPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 회차 등록
    *
    * @Title : insertMvmnPrgrm
    * @Description : 교육프로그램관리 회차 등록
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrm) throws Exception;
    
    /**
    * 교육프로그램관리 회차 수정
    *
    * @Title : updateMvmnPrgrm
    * @Description : 교육프로그램관리 회차 수정
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnPrgrmTme(MvmnPrgrmVo mvmnPrgrm) throws Exception;    
    
    /**
    * 교육프로그램관리 회차 목록 조회
    *
    * @Title : selectMvmnPrgrmTmeList
    * @Description : 교육프로그램관리 회차 목록 조회
    * @param mvmnPrgrm 교육프로그램관리 객체
    * @throws Exception 예외
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectMvmnPrgrmTmeList(MvmnPrgrmVo mvmnPrgrm) throws Exception;    
    
    /**
    * 교육프로그램관리 프로그램 설정 리스트 조회
    **
    @Title : selectPrgrmSettingList
    * @Description : 교육프로그램관리 프로그램 설정 리스트 조회
    * @param mvmnPrgrm
    * @return
    * @throws Exception
    * @return List<MvmnPrgrmVo>
    */
    public List<MvmnPrgrmVo> selectPrgrmSettingList(String operFomCd) throws Exception;
    
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
