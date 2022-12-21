package com.kbrainc.plum.mng.cmnty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyCmntVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyCtgryVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyDao;
import com.kbrainc.plum.mng.cmnty.model.CmntyMbrVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyPstVo;
import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 커뮤니티 관리 서비스 구현
 *
 * <pre>
 * com.kbrainc.plum.mng.cmnty.service
 * - QestnrServiceImpl.java
 * </pre> 
 *
 * @ClassName : CmntyServiceImpl
 * @Description : 커뮤니티 관리 서비스 구현 
 * @author : KBRAINC
 * @date : 2022. 12. 14.
 * @Version : 
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class CmntyServiceImpl extends PlumAbstractServiceImpl implements CmntyService {
    
    @Autowired
    private CmntyDao cmntyDao;
    
    @Autowired
    private FileDao fileDao;
    
    /**
     * 커뮤니티 목록 조회
     *
     * @Title : selectCmntyrList
     * @Description : 커뮤니티 목록 조회
     * @param cmntyVo CmntyVo 객체
     * @return List<CmntyVo> 커뮤니티 목록
     * @throws Exception 예외
     */
    @Override
    public List<CmntyVo> selectCmntyList(CmntyVo cmntyVo) throws Exception {
        return cmntyDao.selectCmntyList(cmntyVo);
    }
    
    /**
     * 커뮤니티 정보 조회
     *
     * @Title : selectCmntyInfo
     * @Description : 커뮤니티 정보 조회
     * @param cmntyVo CmntyVo 객체
     * @return Map<String, Object> 객체
     * @throws Exception 예외
     */
    @Override
    public Map<String, Object> selectCmntyInfo(CmntyVo cmntyVo) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        CmntyVo cmntyInfo = cmntyDao.selectCmntyInfo(cmntyVo);
        resultMap.put("cmntyInfo", cmntyInfo);
        if(cmntyInfo.getCmntyLogoFileid() != null && cmntyInfo.getCmntyLogoFileid() != 0) {
            FileVo fileVo = new FileVo();
            fileVo.setFileid(cmntyInfo.getCmntyLogoFileid());
            resultMap.put("fileInfo", fileDao.getFileInfo(fileVo));
        } else {
            resultMap.put("fileInfo", null);
        }
        
        return resultMap;
    }
    
    /**
     * 커뮤니티 게시판 템플릿 목록 조회
     *
     * @Title : selectCmntyCtgryList
     * @Description : 커뮤니티 게시판 템플릿 목록 조회
     * @param cmntyCtgryVo CmntyCtgryVo 객체
     * @return List<CmntyCtgryVo> 커뮤니티 게시판 템플릿 목록
     * @throws Exception 예외
     */
    @Override
    public List<CmntyCtgryVo> selectCmntyCtgryList(CmntyCtgryVo cmntyCtgryVo) throws Exception {
        return cmntyDao.selectCmntyCtgryList(cmntyCtgryVo);
    }
    
    /**
     * 커뮤니티 회원 목록 조회
     *
     * @Title : selectCmntyMbrList
     * @Description : 커뮤니티 회원 목록 조회
     * @param cmntyMbrVo CmntyMbrVo 객체
     * @return List<CmntyMbrVo> 커뮤니티 회원 목록
     * @throws Exception 예외
     */
    @Override
    public List<CmntyMbrVo> selectCmntyMbrList(CmntyMbrVo cmntyMbrVo) throws Exception {
        return cmntyDao.selectCmntyMbrList(cmntyMbrVo);
    }
    
    /**
     * 커뮤니티 게시글 목록 조회
     *
     * @Title : selectCmntyPstList
     * @Description : 커뮤니티 게시글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return List<CmntyPstVo> 커뮤니티 게시글 목록
     * @throws Exception 예외
     */
    public List<CmntyPstVo> selectCmntyPstList(CmntyPstVo cmntyPstVo) throws Exception {
        return cmntyDao.selectCmntyPstList(cmntyPstVo);
    }
    
    /**
     * 커뮤니티 게시글 정보 조회
     *
     * @Title : selectCmntyPstInfo
     * @Description : 커뮤니티 게시글 정보 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return Map<String, Object> 객체
     * @throws Exception 예외
     */
    @Override
    public Map<String, Object> selectCmntyPstInfo(CmntyPstVo cmntyPstVo) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        CmntyPstVo cmntyPstInfo = cmntyDao.selectCmntyPstInfo(cmntyPstVo);
        resultMap.put("cmntyPstInfo", cmntyPstInfo);
        if(cmntyPstInfo.getFilegrpid() != null && cmntyPstInfo.getFilegrpid() != 0) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(cmntyPstInfo.getFilegrpid());
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            resultMap.put("fileList", fileList);
            resultMap.put("fileCnt", fileList.size());
        } else {
            resultMap.put("fileList", null);
        }
        
        return resultMap;
    }
    
    /**
     * 커뮤니티 게시글 삭제
     *
     * @Title : deleteCmntyPst
     * @Description : 커뮤니티 게시글 삭제
     * @param cmntyPstVo CmntyPstVo 객체
     * @return int delete 로우수
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int deleteCmntyPst(CmntyPstVo cmntyPstVo) throws Exception {
        int retVal = 0;
        retVal = cmntyDao.deleteCmntyPst(cmntyPstVo);
        
        return retVal;
    }
    
    /**
     * 커뮤니티 댓글 목록 조회
     *
     * @Title : selectCmntyCmntList
     * @Description : 커뮤니티 댓글 목록 조회
     * @param cmntyPstVo CmntyPstVo 객체
     * @return List<CmntyCmntVo> 커뮤니티 댓글 목록
     * @throws Exception 예외
     */
    public List<CmntyCmntVo> selectCmntyCmntList(CmntyPstVo cmntyPstVo) throws Exception {
        return cmntyDao.selectCmntyCmntList(cmntyPstVo);
    }
    
}