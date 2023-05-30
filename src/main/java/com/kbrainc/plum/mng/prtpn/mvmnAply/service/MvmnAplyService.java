package com.kbrainc.plum.mng.prtpn.mvmnAply.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.mng.prtpn.mvmnSchdl.model.MvmnSchdlVo;
import com.kbrainc.plum.rte.model.UserVo;



/**
* 유아환경교육 -> 교육신청관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnAply.service
* - MvmnAplyService.java
* </pre>
**
@ClassName : MvmnAplyService
* @Description : 유아환경교육 -> 교육신청관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MvmnAplyService {
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAply) throws Exception;

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectMvmnAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param mvmnAply 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyDetailList(MvmnAplyVo mvmnAply) throws Exception;

    /**
     * 교육신청관리 게시글 신청마감 리스트 조회
     *
     * @Title : selectMvmnAplyCloseList
     * @Description : 교육신청관리 게시글 신청마감 리스트 조회
     * @param mvmnAply 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyCloseList(MvmnAplyVo mvmnAply) throws Exception;
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 게시글 등록
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAply(MvmnAplyVo mvmnAply) throws Exception;

    
    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectMvmnAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예외
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAply) throws Exception;
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 게시글 수정
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAply(MvmnAplyVo mvmnAply) throws Exception;

    /**
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdMvmnAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param mvmnAply 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateSttsCdMvmnAply(MvmnAplyVo mvmnAply) throws Exception;

    /**
     * 교육신청관리 신청마감 수정 기능
     *
     * @Title : updateMvmnAplyClose
     * @Description : 교육신청관리 신청마감 수정 기능
     * @param mvmnAply 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateMvmnAplyClose(MvmnAplyVo mvmnAply) throws Exception;
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 회차 등록
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAplyTme(MvmnAplyVo mvmnAply) throws Exception;
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 회차 수정
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAplyTme(MvmnAplyVo mvmnAply) throws Exception;    
    
    /**
    * 교육신청관리 회차 목록 조회
    *
    * @Title : selectMvmnAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param mvmnAply 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAply) throws Exception;    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectTmeSchdlList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param mvmnAply 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectTmeSchdlList(MvmnAplyVo mvmnAply) throws Exception;    
    
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param MvmnAplyVo mvmnAply 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MvmnAplyVo> selectMemberList(MvmnAplyVo mvmnAply) throws Exception;
    
    /**
    * 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    *
    * @Title : mvmnAplyExcelDownList
    * @Description : 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    * @param mvmnAply
    * @param response
    * @param request
    * @throws Exception
    * @return void
    */
    public void mvmnAplyExcelDownList(MvmnAplyVo mvmnAply, HttpServletResponse response, HttpServletRequest request) throws Exception;

    /**
    * insert form info
    *
    * @Title : selectMvmnAplyInsertInfo
    * @Description : insert form info
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyInsertInfo(MvmnAplyVo mvmnAplyVo) throws Exception;

    public List<MvmnAplyVo> selectEduYear() throws Exception;

    public List<MvmnAplyVo> selectDailyTmeSchdlList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    
}
