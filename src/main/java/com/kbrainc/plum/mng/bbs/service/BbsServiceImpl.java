package com.kbrainc.plum.mng.bbs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.bbs.model.BbsClVo;
import com.kbrainc.plum.mng.bbs.model.BbsDao;
import com.kbrainc.plum.mng.bbs.model.BbsVo;
import com.kbrainc.plum.mng.bbs.model.CmntVo;
import com.kbrainc.plum.mng.bbs.model.PstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * <pre>
 * com.kbrainc.plum.mng.bbs.service - MenuServiceImpl.java
 * </pre>
 *
 * @ClassName : UserTempServiceImpl
 * @Description : 메뉴관리 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 1.
 * @Version : 1.0
 * @Company : CopyrightⒸ KBRAINC. All Rights Reserved
 *
 */
@Service
public class BbsServiceImpl extends PlumAbstractServiceImpl implements BbsService {

    @Autowired
    BbsDao bbsDao;

    @Autowired
    private FileDao fileDao;

    /**
     * @Title : insertBbs
     * @Description :TB_USER 테이블 인서트
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return String
     */
    @Override
    public int insertBbs(BbsVo paramVO) throws Exception {
        return bbsDao.insertBbs(paramVO);

    }

    /**
     * @Title : getBbsList
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    @Override
    public List<BbsVo> getBbsList(BbsVo paramVO) throws Exception {
        return bbsDao.getBbsList(paramVO);
    }

    /**
     * @Title : selectOneBbs
     * @Description : 게시판 업데이트 위한 정보 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public BbsVo selectOneBbs(BbsVo paramVO) throws Exception {
        return bbsDao.selectOneBbs(paramVO);
    }

    /**
     * @Title : updateBbs
     * @Description : BbsVO bbs 테이블 update
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return int
     **/
    public int updateBbs(BbsVo paramVO) throws Exception {
        return bbsDao.updateBbs(paramVO);
    }

    /**
     * @Title : selectBbsbyClUseYn
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsVo> selectBbsbyClUseYn(BbsVo paramVO) throws Exception {
        return bbsDao.selectBbsbyClUseYn(paramVO);
    }
    
     /**
     * @Title : selectBbsClList 
     * @Description : 게시글 종류 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    
    public List<BbsClVo> selectBbsClList(BbsClVo paramVO) throws Exception{
        return bbsDao.selectBbsClList(paramVO);
    }

    /**
     * @Title : selectBbsCl
     * @Description : 게시판 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<BbsClVo> selectBbsCl(BbsClVo paramVO) throws Exception {
        return bbsDao.selectBbsCl(paramVO);
    }

    /**
     * @Title : insertUserTemp
     * @Description :TB_BBS_CL 테이블 인서트
     * @param paramVO BbsCl 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertBbsCl(BbsClVo paramVO) throws Exception {
        return bbsDao.insertBbsCl(paramVO);
    }

    /**
     * @Title : updateBbsCl
     * @Description :TB_BBS_CL 테이블 update
     * @param paramVO BbsCl 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int updateBbsCl(BbsClVo paramVO) throws Exception {
        return bbsDao.updateBbsCl(paramVO);
    }

    /**
     * @Title : insertPst
     * @Description :TB_PST insert
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertPst(PstVo paramVO) throws Exception {
        return bbsDao.insertPst(paramVO);
    }

    /**
     * @Title : selectPstList
     * @Description : 게시글 목록 가져오기
     * @param paramVO BbsVO 타입의 인자
     * @throws Exception :
     * @return List
     */

    public List<PstVo> selectPstList(PstVo paramVO) throws Exception {
        return bbsDao.selectPstList(paramVO);
    }

    /**
     * @Title : modifybbsClOrdUp
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NOT_SUPPORTED)
    public void modifybbsClOrdUp(BbsClVo paramVO) throws Exception {
        int newOrd = paramVO.getOrd() - 1;
        paramVO.setOrd(newOrd);
        bbsDao.modifybbsClOrdDown(paramVO);
        bbsDao.modifybbsClOrdByClid(paramVO);
    }

    /**
     * @Title : modifybbsClOrdDown
     * @Description :ord 변경
     * @param paramVO BbsClVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NOT_SUPPORTED)
    public void modifybbsClOrdDown(BbsClVo paramVO) throws Exception {
        int newOrd = paramVO.getOrd() + 1;
        paramVO.setOrd(newOrd);
        bbsDao.modifybbsClOrdUp(paramVO);
        bbsDao.modifybbsClOrdByClid(paramVO);
    }

    /**
     * @Title : selectOneBbs
     * @Description : 게시물 조회
     * @param paramVO PstVO paramVO
     * @throws Exception :
     * @return Map<String,Object>
     */
    public Map<String, Object> selectPst(PstVo paramVO) throws Exception {

        Map<String, Object> resultMap = new HashMap();
        
        PstVo pstVo = bbsDao.selectPst(paramVO);
        
        if (pstVo.getFilegrpid() != null && !pstVo.getFilegrpid().equals(0)) {
        	
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(pstVo.getFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            resultMap.put("fileMap",fileList );
            resultMap.put("currentFileCnt", fileList.size());
        } else {
            resultMap.put("fileMap", null);
            resultMap.put("currentFileCnt", 0);
        }
        
        
        resultMap.put("paramMap", pstVo);
        return resultMap;
    };

    /**
     * @Title : updatePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public int updatePst(PstVo paramVO) throws Exception {
        return bbsDao.updatePst(paramVO);
    }

    /**
     * @Title : updatePst
     * @Description : 게시판 글 업데이트
     * @param fileId PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    public boolean deleteFileByfileId(int fileId) throws Exception {
        FileVo fileVo = new FileVo();
        fileVo.setFileid(fileId);
        return fileDao.deleteFile(fileVo);
    }

    /**
     * @Title : insertCmnt
     * @Description : 게시판 글 업데이트
     * @param paramVO CmntVO 타입의 인자
     * @throws Exception :
     * @return int
     */
    public int insertCmnt(CmntVo paramVO) throws Exception {
        return bbsDao.insertCmnt(paramVO);
    }

    /**
     * @Title : deletePst
     * @Description : 게시판 글 업데이트
     * @param paramVO PstVO 타입의 인자
     * @throws Exception :
     * @return List
     */
    @Transactional
    public int deletePst(PstVo paramVO) throws Exception {
    	int resInt = 0;
    	resInt = bbsDao.deleteCmnt(paramVO);
    	resInt += bbsDao.deletePst(paramVO);
        return resInt; 
    }
    
    
    /**
     * @Title : selectCmntList
     * @Description : 댓글 목록 가져오기 
     * @param paramVO CmntVo 타입의 인자
     * @throws Exception :
     * @return List
     */
    public List<CmntVo> selectCmntList(CmntVo paramVO) throws Exception{
    	
    	List<CmntVo> list= bbsDao.selectCmntList(paramVO);
    	if(list.size()>0) {
    		for(CmntVo vo : list) {
    			if(vo.getDpth() > 0) {
    				String padding ="";		
    				String reStr ="";		
    				for(int i=0 ; i < vo.getDpth() ; i++) {
    			       padding += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    			       reStr   += "re:";
    				}
    				vo.setPaddingStr(padding);
    				vo.setDpthStr(reStr);
    			}else {
    				vo.setDpthStr("");
    			}
    		}
    	}
    	return list;
    }
        
    /**
     * @Title : updateCmnt
     * @Description : 댓글 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmnt(CmntVo paramVO) throws Exception{
    	return bbsDao.updateCmnt(paramVO);
    }
    
    /**
     * @Title : updateCmntReplyDelYn
     * @Description : 댓글 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int updateCmntReplyDelYn(CmntVo paramVO) throws Exception{
    	return bbsDao.updateCmntReplyDelYn(paramVO);
    }
    
    
    
    /**
     * @Title : updateCmntOrd
     * @Description : 댓글 ord 업데이트
     * @param paramVO CmntVo 타입의 인자
     * @return List
     * @throws Exception :
     */
    public int insertReply(CmntVo paramVO) throws Exception{
    	
    	bbsDao.updateCmntOrdElse(paramVO);
    	paramVO.setDpth(paramVO.getDpth()+1);
    	System.out.println(paramVO);
    	
      return bbsDao.insertCmnt(paramVO);     	
    }
    
    /**
     * @Title : selectReplyPstList
     * @Description : 게시글 답글 목록 가져오기
     * @param paramVO PstVo 타입의 인자
     * @throws Exception :
     * @return List
     */
    public List<PstVo> selectReplyPstList(PstVo paramVO) throws Exception{
    	
        Map<String, Object> resultMap = new HashMap();
        
        PstVo pstVo = bbsDao.selectPst(paramVO);
        
        List<PstVo> list = bbsDao.selectReplyPstList(paramVO);
        
        for(PstVo vo : list) {
        	
        	if (vo.getFilegrpid() != null && !vo.getFilegrpid().equals(0)) {
        		
        		FileVo fileVo = new FileVo();
        		fileVo.setFilegrpid(Integer.parseInt(vo.getFilegrpid().toString()));
        		ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
        		
        		vo.setFileMap(fileList);
        		vo.setCurrentFileCnt(fileList.size());
        	} else {
        		vo.setFileMap(null);
        		vo.setCurrentFileCnt(0);
        	}
        }
        
    	return list;
    }
}
