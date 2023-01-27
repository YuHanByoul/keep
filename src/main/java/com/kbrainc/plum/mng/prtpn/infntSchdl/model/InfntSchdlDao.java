package com.kbrainc.plum.mng.prtpn.infntSchdl.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;

/**
* 유아환경교육 -> 교육일정관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntSchdl.model
* - InfntSchdlDao.java
* </pre>
**
@ClassName : InfntSchdlDao
* @Description : 유아환경교육 -> 교육일정관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface InfntSchdlDao {
    
    /**
    * 교육일정관리 게시글 목록 조회
    *
    * @Title : selectInfntSchdlList
    * @Description : 교육일정관리 게시글 목록 조회
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return List<InfntSchdlVo>
    */
    public List<InfntSchdlVo> selectInfntSchdlList(InfntSchdlVo infntSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 등록
    *
    * @Title : insertInfntSchdl
    * @Description : 교육일정관리 게시글 등록
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 상세조회
    *
    * @Title : selectInfntSchdlInfo
    * @Description : 교육일정관리 게시글 상세조회
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return InfntSchdlVo
    */
    public InfntSchdlVo selectInfntSchdlInfo(InfntSchdlVo infntSchdlVo) throws Exception;
    
    /**
    * 교육일정관리 게시글 수정
    *
    * @Title : updateInfntSchdl
    * @Description : 교육일정관리 게시글 수정
    * @param infntSchdlVo 교육일정관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception;

    
    /**
     * 교육일정관리 게시글 삭제
     *
     * @Title : deleteInfntSchdl
     * @Description : 교육일정관리 게시글 삭제
     * @param infntSchdlVo 교육일정관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int deleteInfntSchdl(InfntSchdlVo infntSchdlVo) throws Exception;

    
    /**
    * EDU_유아_프로그램_일정_일자 삭제
    **
    @Title : deleteDeSttId
    * @Description : EDU_유아_프로그램_일정_일자 삭제
    * @param infntSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteDeSttId(InfntSchdlVo infntSchdlVo) throws Exception;
    /**
    * EDU_유아_프로그램_일정_프로그램 삭제
    **
    @Title : deletePrgrmSttId
    * @Description : EDU_유아_프로그램_일정_프로그램 삭제
    * @param infntSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deletePrgrmSttId(InfntSchdlVo infntSchdlVo) throws Exception;
    /**
    * EDU_유아_프로그램_일정_일자 등록
    **
    @Title : insertDeSttId
    * @Description : EDU_유아_프로그램_일정_일자 등록
    * @param infntSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertDeSttId(InfntSchdlVo infntSchdlVo) throws Exception;
    /**
    * EDU_유아_프로그램_일정_프로그램 등록
    **
    @Title : insertPrgrmSttId
    * @Description : EDU_유아_프로그램_일정_프로그램 등록
    * @param infntSchdlVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertPrgrmSttId(InfntSchdlVo infntSchdlVo) throws Exception;
    
}
