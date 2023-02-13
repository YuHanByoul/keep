package com.kbrainc.plum.mng.prtpn.mvmnSchdl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.prtpn.mvmnPrgrm.model.MvmnPrgrmVo;
import com.kbrainc.plum.mng.prtpn.mvmnSchdl.model.MvmnSchdlVo;



/**
* 푸름이환경이동교실 -> 교육일정관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnSchdl.service
* - MvmnSchdlService.java
* </pre>
**
@ClassName : MvmnSchdlService
* @Description : 푸름이환경이동교실 -> 교육일정관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MvmnSchdlService {
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectMvmnSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param mvmnSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return List<MvmnSchdlVo>
    */
    public List<MvmnSchdlVo> selectMvmnSchdlList(MvmnSchdlVo mvmnSchdl) throws Exception;
    
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertMvmnSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param mvmnSchdl 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnSchdl(MvmnSchdlVo mvmnSchdl) throws Exception;
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectMvmnSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param mvmnSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return MvmnSchdlVo
    */
    public MvmnSchdlVo selectMvmnSchdlInfo(MvmnSchdlVo mvmnSchdl) throws Exception;
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateMvmnSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param mvmnSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnSchdl(MvmnSchdlVo mvmnSchdl) throws Exception;

    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : deleteMvmnSchdl
     * @Description : 교육일정관리 게시글 삭제
     * @param mvmnSchdl 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteMvmnSchdl(MvmnSchdlVo mvmnSchdl) throws Exception;
    
    /**
    * 교육일정관리 교육일정 리스트 조회
    **
    @Title : selectMvmnSchdlIdList
    * @Description : 교육일정관리 교육일정 리스트 조회
    * @param sareaId
    * @return
    * @throws Exception
    * @return List<MvmnSchdlVo>
    */
    public List<MvmnSchdlVo> selectMvmnSchdlIdList(String sareaId) throws Exception;
}
