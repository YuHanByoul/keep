package com.kbrainc.plum.mng.prtpn.infntSchdl.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.prtpn.infntPrgrm.model.InfntPrgrmVo;
import com.kbrainc.plum.mng.prtpn.infntSchdl.model.InfntSchdlVo;



/**
* 유아환경교육 -> 교육일정관리 서비스 인터페이스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntSchdl.service
* - InfntSchdlService.java
* </pre>
**
@ClassName : InfntSchdlService
* @Description : 유아환경교육 -> 교육일정관리 서비스 인터페이스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface InfntSchdlService {
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectInfntSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param infntSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return List<InfntSchdlVo>
    */
    public List<InfntSchdlVo> selectInfntSchdlList(InfntSchdlVo infntSchdl) throws Exception;
    
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertInfntSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param infntSchdl 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntSchdl(InfntSchdlVo infntSchdl) throws Exception;
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectInfntSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param infntSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return InfntSchdlVo
    */
    public InfntSchdlVo selectInfntSchdlInfo(InfntSchdlVo infntSchdl) throws Exception;
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateInfntSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param infntSchdl 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntSchdl(InfntSchdlVo infntSchdl) throws Exception;

    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : deleteInfntSchdl
     * @Description : 교육일정관리 게시글 삭제
     * @param infntSchdl 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntSchdl(InfntSchdlVo infntSchdl) throws Exception;
    
    /**
    * 교육일정관리 교육일정 리스트 조회
    **
    @Title : selectInfntSchdlIdList
    * @Description : 교육일정관리 교육일정 리스트 조회
    * @param clssrmId
    * @return
    * @throws Exception
    * @return List<InfntSchdlVo>
    */
    public List<InfntSchdlVo> selectInfntSchdlIdList(String clssrmId) throws Exception;


}
