package com.kbrainc.plum.mng.mobileAsgsysSrng.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ChklstAnsVo;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngDao;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
* 언론보도관리 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.service
* - MobileAsgsysSrngServiceImpl.java
* </pre>
*
* @ClassName : MobileAsgsysSrngServiceImpl
* @Description : 언론보도관리 서비스 구현 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MobileAsgsysSrngServiceImpl extends PlumAbstractServiceImpl implements MobileAsgsysSrngService{
    
    @Autowired
    private MobileAsgsysSrngDao mobileAsgsysSrngDao;
    
    @Autowired
    private FileDao fileDao;
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 시설 목록 조회
    * @param mobileAsgsysSrngVo 시설정보 객체
    * @throws Exception 예외
    * @return List<MobileAsgsysSrngVo>
    */
    @Override
    public List<MobileAsgsysSrngVo> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception{
        return mobileAsgsysSrngDao.selectAsgsysSrngList(mobileAsgsysSrngVo);
    }

    /**
     * 지원단심사 상세 조회
     *
     * @Title : selectAsgsysSrngInfo
     * @Description : 지원단심사 상세 조회
     * @param mobileAsgsysSrngVo
     * @return MobileAsgsysSrngVo
     * @throws Exception
     */
    @Override
    public MobileAsgsysSrngVo selectAsgsysSrngInfo(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        MobileAsgsysSrngVo mobileAsgsysSrngInfo = mobileAsgsysSrngDao.selectAsgsysSrngInfo(mobileAsgsysSrngVo);
        
        if(mobileAsgsysSrngInfo != null && Objects.nonNull(mobileAsgsysSrngInfo.getEduPhotoFilegrpid())) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(mobileAsgsysSrngInfo.getEduPhotoFilegrpid().toString()));
            ArrayList<FileVo> fileInfo= fileDao.getFileList(fileVo);
            mobileAsgsysSrngInfo.setEduPhotoFileInfo(fileInfo);
        }
        
        if(mobileAsgsysSrngInfo != null && Objects.nonNull(mobileAsgsysSrngInfo.getFilegrpid())) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(mobileAsgsysSrngInfo.getFilegrpid().toString()));
            ArrayList<FileVo> fileInfo= fileDao.getFileList(fileVo);
            mobileAsgsysSrngInfo.setFileInfo(fileInfo);
        }
        
        if(mobileAsgsysSrngInfo != null && Objects.nonNull(mobileAsgsysSrngInfo.getBfrCertFilegrpid())) {
            FileVo fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(mobileAsgsysSrngInfo.getBfrCertFilegrpid().toString()));
            ArrayList<FileVo> fileInfo= fileDao.getFileList(fileVo);
            mobileAsgsysSrngInfo.setBfrCertFilegrpInfo(fileInfo);
        }
        
        return mobileAsgsysSrngInfo;
    }

    @Override
    @Transactional
    public int updateAsgsysSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        return mobileAsgsysSrngDao.updateAsgsysSrng(mobileAsgsysSrngVo);
    }
    
    @Override
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        return mobileAsgsysSrngDao.selectCheckList(mobileAsgsysSrngVo);
    }

    @Override
    @Transactional
    public int insertSprtgrpSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        int retVal = 0;
        
        // Sbmsnid(제출아이디) 지원단이 심사한 제출아이디가 없을때 등록
        if(Objects.isNull( mobileAsgsysSrngVo.getSbmsnid())) {
            retVal += mobileAsgsysSrngDao.updateSprtgrpSrng(mobileAsgsysSrngVo);     //지원단 심사 수정
            retVal += mobileAsgsysSrngDao.insertChkLstSbmsn(mobileAsgsysSrngVo);     //체크리스트 제출 등록
            retVal += mobileAsgsysSrngDao.insertChkLstAns(mobileAsgsysSrngVo);       //체크리스트 답변 등록
            retVal += mobileAsgsysSrngDao.insertChkLstSeOrdrAns(mobileAsgsysSrngVo); //체크리스트 구분 순서 답변 등록
        }else {
            retVal += mobileAsgsysSrngDao.updateSprtgrpSrng(mobileAsgsysSrngVo);     //지원단 심사 수정
            retVal += mobileAsgsysSrngDao.updateChkLstSbmsn(mobileAsgsysSrngVo);     //체크리스트 제출 수정
            retVal += mobileAsgsysSrngDao.updateChkLstAns(mobileAsgsysSrngVo);       //체크리스트 답변 수정
            retVal += mobileAsgsysSrngDao.updateChkLstSeOrdrAns(mobileAsgsysSrngVo); //체크리스트 구분 순서 답변 수정
        }
        
        return retVal;
    }
}
