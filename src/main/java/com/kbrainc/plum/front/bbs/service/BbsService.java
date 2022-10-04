package com.kbrainc.plum.front.bbs.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.front.bbs.model.BbsClVo;
import com.kbrainc.plum.front.bbs.model.BbsVo;
import com.kbrainc.plum.front.bbs.model.CmntVo;
import com.kbrainc.plum.front.bbs.model.PstVo;

/**
 * 
 * 회원관리 임시 서비스 인터페이스.
 *
 * <pre>
 * com.kbrainc.plum.front.bbs.service
 * - BbsService.java
 * </pre> 
 *
 * @ClassName : BbsService
 * @Description : 회원관리 임시 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface BbsService {

    /**
     * @Title : insertBbs
     * @Description :TB_bbs 테이블 인서트
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return String
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
     * @Title : selectBbsbyClUseYn
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsVo> selectBbsbyClUseYn(BbsVo paramVO) throws Exception;

    /**
     * @Title : selectBbsCl
     * @Description : 게시판 목록 가져오기
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
    public void modifybbsClOrdUp(BbsClVo paramVO) throws Exception;

    /**
     * @Title : modifybbsClOrdDown
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public void modifybbsClOrdDown(BbsClVo paramVO) throws Exception;

    /**
     * @Title : selectOneBbs
     * @Description : 게시물 조회
     * @param paramVO PstVO paramVO
     * @throws Exception :
     * @return Map<String,Object>
     */
    public Map<String, Object> selectPst(PstVo paramVO) throws Exception;

    /**
     * @Title : updatePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public int updatePst(PstVo paramVO) throws Exception;

    /**
     * @Title : deletePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public int deletePst(PstVo paramVO) throws Exception;
    
    /**
     * @Title : updateCmnt
     * @Description : 댓글 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmnt(CmntVo paramVO) throws Exception;
    
    
    /**
     * @Title : updateCmntOrd
     * @Description : 댓글 ord 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int insertReply(CmntVo paramVO) throws Exception;
    
    

}