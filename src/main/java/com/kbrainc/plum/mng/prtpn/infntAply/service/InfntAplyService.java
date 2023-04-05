package com.kbrainc.plum.mng.prtpn.infntAply.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.prtpn.infntAply.model.InfntAplyVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;



/**
* 유아환경교육 -> 교육신청관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.service
* - InfntAplyService.java
* </pre>
**
@ClassName : InfntAplyService
* @Description : 유아환경교육 -> 교육신청관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InfntAplyService {
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyList(InfntAplyVo infntAply) throws Exception;

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectInfntAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param infntAply 교육신청관리 객체
     * @throws Exception 예외
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectInfntAplyDetailList(InfntAplyVo infntAply) throws Exception;
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 게시글 등록
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAply(InfntAplyVo infntAply) throws Exception;

    
    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectInfntAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예외
    * @return InfntAplyVo
    */
    public InfntAplyVo selectInfntAplyInfo(InfntAplyVo infntAply) throws Exception;
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 게시글 수정
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAply(InfntAplyVo infntAply) throws Exception;

    /**
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdInfntAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param infntAply 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateSttsCdInfntAply(InfntAplyVo infntAply) throws Exception;
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 회차 등록
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAplyTme(InfntAplyVo infntAply) throws Exception;
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 회차 수정
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAplyTme(InfntAplyVo infntAply) throws Exception;    
    
    /**
    * 교육신청관리 회차 목록 조회
    *
    * @Title : selectInfntAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param infntAply 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyTmeList(InfntAplyVo infntAply) throws Exception;    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectTmeSchdlList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param infntAply 교육신청관리 객체
     * @throws Exception 예외
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectTmeSchdlList(InfntAplyVo infntAply) throws Exception;    
    
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param InfntAplyVo infntAply 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<InfntAplyVo> selectMemberList(InfntAplyVo infntAply) throws Exception;
    
    /**
    * 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    *
    * @Title : infntAplyExcelDownList
    * @Description : 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    * @param infntAply
    * @param response
    * @param request
    * @throws Exception
    * @return void
    */
    public void infntAplyExcelDownList(InfntAplyVo infntAply, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
}
