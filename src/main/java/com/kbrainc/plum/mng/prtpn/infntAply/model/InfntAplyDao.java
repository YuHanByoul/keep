package com.kbrainc.plum.mng.prtpn.infntAply.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.mng.srvy.model.SrvyVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 유아환경교육 -> 교육신청관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.infntAply.model
* - InfntAplyDao.java
* </pre>
**
@ClassName : InfntAplyDao
* @Description : 유아환경교육 -> 교육신청관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface InfntAplyDao {
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectInfntAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyList(InfntAplyVo infntAplyVo) throws Exception;

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectInfntAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param infntAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectInfntAplyDetailList(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 게시글 등록
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAply(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
     * 교육신청관리 게시글 신청_교육주제 복사 등록
     **
    @Title : insertInfntAplyCopy
     * @Description : 교육신청관리 게시글 신청 복사 등록
     * @param infntAplyVo 교육신청관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertInfntAplyClsfMapngCopy(InfntAplyVo infntAplyVo) throws Exception;
    /**
     * 교육신청관리 게시글 신청_교육대상 복사 등록
     **
    @Title : insertInfntAplyCopy
     * @Description : 교육신청관리 게시글 신청 복사 등록
     * @param infntAplyVo 교육신청관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertInfntAplyTrgtMapngCopy(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectInfntAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return InfntAplyVo
    */
    public InfntAplyVo selectInfntAplyInfo(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 게시글 수정
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAply(InfntAplyVo infntAplyVo) throws Exception;

    /**
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdInfntAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param infntAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateSttsCdInfntAply(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertInfntAply
    * @Description : 교육신청관리 회차 등록
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertInfntAplyTme(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateInfntAply
    * @Description : 교육신청관리 회차 수정
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateInfntAplyTme(InfntAplyVo infntAplyVo) throws Exception;
 
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(InfntAplyVo infntAplyVo) throws Exception;
    /**
    * 교육주제 삭제
    **
    @Title : deleteClsfCd
    * @Description : 교육주제 삭제
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteClsfCd(InfntAplyVo infntAplyVo) throws Exception;
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(InfntAplyVo infntAplyVo) throws Exception;
    /**
    * 교육주제 등록
    **
    @Title : insertClsfCd
    * @Description : 교육주제 등록
    * @param infntAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertClsfCd(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 목록 조회
    *
    * @Title : selectInfntAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param infntAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<InfntAplyVo>
    */
    public List<InfntAplyVo> selectInfntAplyTmeList(InfntAplyVo infntAplyVo) throws Exception;    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectTmeSchdlList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param infntAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<InfntAplyVo>
     */
    public List<InfntAplyVo> selectTmeSchdlList(InfntAplyVo infntAplyVo) throws Exception;    
    
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param InfntAplyVo infntAplyVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<InfntAplyVo> selectMemberList(InfntAplyVo infntAplyVo) throws Exception;
    
    /**
    * 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    *
    * @Title : infntAplyExcelDownList
    * @Description : 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    * @param infntAplyVo
    * @return List<InfntAplyVo>
    * @throws Exception
    */
    public List<InfntAplyVo> infntAplyExcelDownList(InfntAplyVo infntAplyVo) throws Exception;
}
