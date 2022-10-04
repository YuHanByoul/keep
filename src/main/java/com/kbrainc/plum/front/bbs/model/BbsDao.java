package com.kbrainc.plum.front.bbs.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 
 * 임시회원관리 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.model
 * - BbsDao.java
 * </pre> 
 *
 * @ClassName : BbsDao
 * @Description : 임시회원관리 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper("front.bbsDao")
public interface BbsDao {

    /**
     * @Title : insertUserTemp
     * @Description :TB_USER 테이블 인서트
     * @param paramVO UserTempVo 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertBbs(BbsVo paramVO) throws Exception;

    /**
     * @Title : getBbsList
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsVo> getBbsList(BbsVo paramVO) throws Exception;

    /**
     * @Title : selectOneBbs
     * @Description : 게시판 업데이트 위한 정보 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public BbsVo selectOneBbs(BbsVo paramVO) throws Exception;

    /**
     * @Title : updateUserTemp
     * @Description :TB_USER 테이블 update
     * @param paramVO UserTempDt 타입의 인자
     * @throws Exception :
     * @return int
     **/
    public int updateBbs(BbsVo paramVO) throws Exception;

    /**
     * @Title : selectBbsbyClUseYn
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsVo> selectBbsbyClUseYn(BbsVo paramVO) throws Exception;

    /**
     * @Title : selectBbsCl
     * @Description : 게시글 종류 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsClVo> selectBbsCl(BbsClVo paramVO) throws Exception;
    
    /**
     * @Title : selectBbsClList 
     * @Description : 게시글 종류 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    
    public List<BbsClVo> selectBbsClList(BbsClVo paramVO) throws Exception;
    

    /**
     * @Title : insertUserTemp
     * @Description :TB_BBS_CL 테이블 인서트
     * @param paramVO BbsCl 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertBbsCl(BbsClVo paramVO) throws Exception;

    /**
     * @Title : updateBbsCl
     * @Description :TB_BBS_CL 테이블 update
     * @param paramVO BbsCl 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int updateBbsCl(BbsClVo paramVO) throws Exception;

    /**
     * @Title : insertPst
     * @Description :TB_PST insert
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertPst(PstVo paramVO) throws Exception;

    /**
     * @Title : selectPstList
     * @Description : 게시글 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<PstVo> selectPstList(PstVo paramVO) throws Exception;

    /**
     * @Title : modifybbsClOrdUp
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int modifybbsClOrdUp(BbsClVo paramVO) throws Exception;

    /**
     * @Title : modifybbsClOrdDown
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int modifybbsClOrdDown(BbsClVo paramVO) throws Exception;

    /**
     * @Title : modifybbsClOrdByClid
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception
     * @return int
     */
    public int modifybbsClOrdByClid(BbsClVo paramVO) throws Exception;

    /**
     * @Title : selectBbsbyClUseYn
     * @Description : 게시판 목록 가져오기
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public PstVo selectPst(PstVo paramVO) throws Exception;

    /**
     * @Title : updatePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public int updatePst(PstVo paramVO) throws Exception;

    /**
     * @Title : insertCmnt
     * @Description : 게시판 글 업데이트
     * @param paramVO CmntVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertCmnt(CmntVo paramVO) throws Exception;

    /**
     * @Title : deletePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int deletePst(PstVo paramVO) throws Exception;

    /**
     * @Title : selectCmntList
     * @Description : 댓글 목록 가져오기 
     * @param paramVO CmntVo 타입의 인자
     * @throws Exception :
     * @return List
     */
    
    public List<CmntVo> selectCmntList(CmntVo paramVO) throws Exception;
    
    
    /**
     * @Title : updateCmnt
     * @Description : 댓글 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmnt(CmntVo paramVO) throws Exception;
    
    /**
     * @Title : updateCmntReplyDelYn
     * @Description : 댓글 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmntReplyDelYn(CmntVo paramVO) throws Exception;
    
    /**
     * @Title : updateCmntOrdElse
     * @Description : 댓글 ord 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmntOrdElse(CmntVo paramVO) throws Exception;
    
    
    /**
     * @Title : updateCmntOrd
     * @Description : 댓글 ord 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmntOrd(CmntVo paramVO) throws Exception;

    /**
     * @Title : updateCmntOrd
     * @Description : 댓글 ord 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int deleteCmnt(PstVo pstVo) throws Exception;
    
    /**
     * @Title : selectTotalPstList
     * @Description : 게시글 목록 가져오기(상단고정, 핫게시글등 order 순서 포함한 리스트  )
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public List<PstVo> selectTotalPstList(BbsVo paramVO) throws Exception;
    
}