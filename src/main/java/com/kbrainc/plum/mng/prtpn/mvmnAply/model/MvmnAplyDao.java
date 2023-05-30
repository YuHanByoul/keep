package com.kbrainc.plum.mng.prtpn.mvmnAply.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.cnsltng.model.CnsltngVo;
import com.kbrainc.plum.mng.inst.model.InstVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 유아환경교육 -> 교육신청관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnAply.model
* - MvmnAplyDao.java
* </pre>
**
@ClassName : MvmnAplyDao
* @Description : 유아환경교육 -> 교육신청관리 Dao 클래스
* @author : Notebiz001
* @date : 2023. 1. 5.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MvmnAplyDao {
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception;

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectMvmnAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyDetailList(MvmnAplyVo mvmnAplyVo) throws Exception;

    /**
     * 교육신청관리 게시글 신청마감 리스트 조회
     *
     * @Title : selectMvmnAplyCloseList
     * @Description : 교육신청관리 게시글 신청마감 리스트 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectMvmnAplyCloseList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 게시글 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
     * 교육신청관리 게시글 신청_교육주제 복사 등록
     **
    @Title : insertMvmnAplyCopy
     * @Description : 교육신청관리 게시글 신청 복사 등록
     * @param mvmnAplyVo 교육신청관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnAplyClsfMapngCopy(MvmnAplyVo mvmnAplyVo) throws Exception;
    /**
     * 교육신청관리 게시글 신청_교육대상 복사 등록
     **
    @Title : insertMvmnAplyCopy
     * @Description : 교육신청관리 게시글 신청 복사 등록
     * @param mvmnAplyVo 교육신청관리 객체
     * @return
     * @throws Exception
     * @return int
     */
    public int insertMvmnAplyTrgtMapngCopy(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectMvmnAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return MvmnAplyVo
    */
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 게시글 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
     * 교육신청관리 교육일정 수정
     *
     * @Title : updateMvmnAplySchdl
     * @Description : 교육신청관리 교육일정 수정
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
     public int updateMvmnAplySchdl(MvmnAplyVo mvmnAplyVo) throws Exception;

    /**
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdMvmnAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateSttsCdMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception;

    /**
     * 교육신청관리 신청마감 수정 기능
     *
     * @Title : updateMvmnAplyClose
     * @Description : 교육신청관리 신청마감 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    public int updateMvmnAplyClose(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 회차 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    public int insertMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 회차 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception;
 
    /**
    * 교육대상 삭제
    **
    @Title : deleteTrgtCd
    * @Description : 교육대상 삭제
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteTrgtCd(MvmnAplyVo mvmnAplyVo) throws Exception;
    /**
    * 교육주제 삭제
    **
    @Title : deleteClsfCd
    * @Description : 교육주제 삭제
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteClsfCd(MvmnAplyVo mvmnAplyVo) throws Exception;
    /**
    * 교육대상 등록
    **
    @Title : insertTrgtCd
    * @Description : 교육대상 등록
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertTrgtCd(MvmnAplyVo mvmnAplyVo) throws Exception;
    /**
    * 교육주제 등록
    **
    @Title : insertClsfCd
    * @Description : 교육주제 등록
    * @param mvmnAplyVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertClsfCd(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 회차 목록 조회
    *
    * @Title : selectMvmnAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception;    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectTmeSchdlList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    public List<MvmnAplyVo> selectTmeSchdlList(MvmnAplyVo mvmnAplyVo) throws Exception;    
    
    /**
     * 등록용 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param MvmnAplyVo mvmnAplyVo 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    public List<MvmnAplyVo> selectMemberList(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    /**
    * 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    *
    * @Title : mvmnAplyExcelDownList
    * @Description : 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    * @param mvmnAplyVo
    * @return List<MvmnAplyVo>
    * @throws Exception
    */
    public List<MvmnAplyVo> mvmnAplyExcelDownList(MvmnAplyVo mvmnAplyVo) throws Exception;
 
    /**
    * 교육신청관리 신청일정 등록
    *
    * @Title : insertMvmnAplySchdl
    * @Description : 교육신청관리 신청일정 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertMvmnAplySchdl(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    public MvmnAplyVo selectMvmnAplyInsertInfo(MvmnAplyVo mvmnAplyVo) throws Exception;
    
    public MvmnAplyVo selectEduYear() throws Exception;
    
}
