package com.kbrainc.plum.front.cmnty.model;

import com.kbrainc.plum.rte.model.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

;

/**
 * 환경동아리 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyDao
 * @Description : 환경동아리 Dao
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper("front.CmntyDao")
@Alias("front.CmntyDao")
public interface CmntyDao {
    /**
     * 환경동아리 목록
     * Title : selectCmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @return list
     */
    List<CmntyVo> selectCmntyList(CmntyVo cmntyVo);

    /**
     * 환경동아리 커뮤니티 정보
     * Title : selectCmntyInfo
     * Description : 환경동아리 커뮤니티 정보
     *
     * @param cmntyVo
     * @return cmnty vo
     */
    CmntyVo selectCmntyInfo(CmntyVo cmntyVo);

    /**
     * 환경동아리 가입/초대
     * Title : insertCmntyMbr
     * Description : 환경동아리 커뮤니티 가입/초대
     *
     * @param cmntyVo
     * @return boolean
     */
    boolean insertCmntyMbr(CmntyVo cmntyVo);

    /**
     * 환경동아리 가입신청취소/삭제/반려
     * Title : deleteCmntyMbr
     * Description : 환경동아리 가입신청취소/삭제/반려
     *
     * @param cmntyVo
     * @return boolean
     */
    boolean deleteCmntyMbr(CmntyVo cmntyVo);

    /**
     * 환경동아리 템플릿 조회
     * Title : selectCmntyTmplatList
     * Description : 환경동아리 템플릿 조회
     *
     * @param cmntyid
     * @return cmnty bbs tmplat vo
     */
    List<CmntyBbsTmplatVo> selectCmntyTmplatList(Integer cmntyid);

    /**
     * 환경동아리 등록
     * Title : insertCmnty
     * Description : 환경동아리 등록
     *
     * @param cmntyVo
     * @return boolean
     */
    boolean insertCmnty(CmntyVo cmntyVo);

    /**
     * 환경동아리 카테고리 순서 등록
     * Title : insertCmntyCtgry
     * Description : 환경동아리 카테고리 순서 등록
     *
     * @param cmntyVo
     * @param tmplatId
     * @param index
     * @return boolean
     */
    boolean insertCmntyCtgry(@Param("cmntyVo") CmntyVo cmntyVo, @Param("tmplatId") Integer tmplatId, @Param("index") Integer index);

    /**
     * 환경동아리 게시판 등록
     * Title : insertCmntyBbs
     * Description : 환경동아리 게시판 등록
     *
     * @param cmntyVo
     * @param tmplatId
     * @return boolean
     */
    boolean insertCmntyBbs(@Param("cmntyVo") CmntyVo cmntyVo, @Param("tmplatId") Integer tmplatId);

    /**
     * 환경동아리 카테고리 순서 조회
     * Title : selectCmntyCtgryList
     * Description : 환경동아리 카테고리 순서 조회
     *
     * @param cmntyBbsVo
     * @return list
     */
    List<CmntyCtgryVo> selectCmntyCtgryList(CmntyBbsVo cmntyBbsVo);

    /**
     * 환경동아리 게시글 목록 조회
     * Title : selectCmntyBbsList
     * Description : 환경동아리 게시글 목록 조회
     *
     * @param bbsInfo
     * @return list
     */
    List<CmntyPstVo> selectCmntyBbsList(CmntyBbsVo bbsInfo);

    /**
     * 환경동아리 커뮤니티 게시판 정보
     * Title : selectBbsInfo
     * Description : 환경동아리 커뮤니티 게시판 정보
     *
     * @param cmntyBbsVo
     * @return cmnty bbs vo
     */
    CmntyBbsVo selectBbsInfo(CmntyBbsVo cmntyBbsVo);

    /**
     * 환경동아리 회원 목록
     * Title : selectCmntyMbrList
     * Description : 환경동아리 회원 목록
     *
     * @param cmntyMbrVo
     * @return cmnty mbr vo
     */
    List<CmntyMbrVo> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo);

    /**
     * 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     * Title : updateCmntyMbr
     * Description : 환경동아리 탈퇴/권한부여/권한철회/다시초대/승인/복원
     *
     * @param cmntyVo
     * @return boolean
     */
    boolean updateCmntyMbr(CmntyVo cmntyVo);

    /**
     * 환경동아리 수정
     * Title : updateCmnty
     * Description : 환경동아리 수정
     *
     * @param cmntyVo
     * @return boolean
     */
    boolean updateCmnty(CmntyVo cmntyVo);

    /**
     * 환경동아리 게시판 수정
     * Title : updateCmntyBbs
     * Description : 환경동아리 게시판 수정
     *
     * @param cmntyVo
     * @param tmplatId
     * @param useYn
     * @return boolean
     */
    boolean updateCmntyBbs(@Param("cmntyVo") CmntyVo cmntyVo, @Param("tmplatId") Integer tmplatId, @Param("useYn") String useYn);

    /**
     * 환경동아리 회원검색
     * Title : selectMbr
     * Description : 환경동아리 회원검색
     *
     * @param userNm
     * @param cmntyid
     * @return list
     */
    List<UserVo> selectMbr(@Param("userNm") String userNm, @Param("cmntyid") String cmntyid);

    /**
     * 조회수 증가
     * Title : updatePstHitsCount
     * Description : 조회수 증가
     *
     * @param paramVo
     */
    void updatePstHitsCount(CmntyPstVo paramVo);

    /**
     * 환경동아리 게시글 정보 조회
     * Title : selectPstInfo
     * Description : 환경동아리 게시글 정보 조회
     *
     * @param paramVo
     * @return cmnty pst vo
     */
    CmntyPstVo selectPstInfo(CmntyPstVo paramVo);

    /**
     * 환경동아리 게시글 등록 처리
     * Title : insertCmntyPst
     * Description : 환경동아리 게시글 등록 처리
     *
     * @param paramVo
     * @return boolean
     */
    boolean insertCmntyPst(CmntyPstVo paramVo);

    /**
     * 환경동아리 게시글 순서 수정
     * Title : updatePstOrdElse
     * Description : 환경동아리 게시글 순서 수정
     *
     * @param paramVo
     * @return boolean
     */
    boolean updatePstOrdElse(CmntyPstVo paramVo);
}
