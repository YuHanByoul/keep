package com.kbrainc.plum.front.cntstAplyHist.service;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.cntst.model.CntstAplySchlVo;
import com.kbrainc.plum.front.cntst.model.CntstAplyVo;
import com.kbrainc.plum.front.cntst.model.CntstDao;
import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistDao;
import com.kbrainc.plum.front.cntstAplyHist.model.CntstAplyHistVo;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
* 공모전 참여 이력 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.cntstAplyHist.service
* - CntstAplyHistServiceImpl.java
* </pre>
*
* @ClassName : CntstAplyHistServiceImpl
* @Description : 공모전 참여 이력 Service 구현 클래스
* @author : JD
* @date : 2023. 2. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.cntstAplyHistServiceImpl")
@Alias("front.cntstAplyHistServiceImpl")
public class CntstAplyHistServiceImpl extends PlumAbstractServiceImpl implements CntstAplyHistService {

    @Resource(name = "front.cntstAplyHistDao")
    CntstAplyHistDao cntstAplyHistDao;
    
    @Autowired
    private FileDao fileDao;
    
    /**
    * 공모전 참여 이력 목록 조회
    *
    * @Title : selectCntstAplyHistList
    * @Description : 공모전 참여 이력 목록 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    @Override
    public List<CntstAplyHistVo> selectCntstAplyHistList(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.selectCntstAplyHistList(cntstAplyHistVo);
    }
    
    /**
    * 공모전 참여 이력 상세정보 조회
    *
    * @Title : selectCntstAplyHistInfo
    * @Description : 공모전 참여 이력 상세정보 조회
    * @param cntsRectVo
    * @return
    * @throws Exception
    * @return CntstAplyHistVo
    */
    @Override
    public CntstAplyHistVo selectCntstAplyHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        CntstAplyHistVo cntstHist = cntstAplyHistDao.selectCntstAplyHistInfo(cntstAplyHistVo);

        if(cntstHist != null && !cntstHist.getPrdctFilegrpid().equals("0")) { FileVo
            fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(cntstHist.getPrdctFilegrpid().toString()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            cntstHist.setFileList(fileList);
        }
        
        return cntstHist;
    }

    /**
    * 공모전 참여분야 정보 조회
    *
    * @Title : selectCntstFldMapngInfo
    * @Description : 공모전 참여분야 정보 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    @Override
    public List<CntstAplyHistVo> selectCntstFldMapngInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.selectCntstFldMapngInfo(cntstAplyHistVo);
    }
    
    /**
    * 공모전 참여 이력 수정
    *
    * @Title : updateCntstAplyHist
    * @Description : 공모전 참여 이력 수정
    * @param cntstAplyHistVo
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateCntstAplyHist(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.updateCntstAplyHist(cntstAplyHistVo);
    }


    /**
    * 공모전 참여 이력(환경일기장신청) 상세정보 조회
    *
    * @Title : selectCntstAplySchlHistInfo
    * @Description : 공모전 참여 이력(환경일기장신청) 상세정보 조회
    * @param cntstAplyHistVo
    * @throws Exception
    * @return List<CntstAplyHistVo>
    */
    @Override
    public CntstAplyHistVo selectCntstAplySchlHistInfo(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        CntstAplyHistVo cntstHist = cntstAplyHistDao.selectCntstAplySchlHistInfo(cntstAplyHistVo);
        
        if(cntstHist != null && !cntstHist.getStdntFileid().equals("0")) { FileVo
            fileVo = new FileVo();
            fileVo.setFilegrpid(Integer.parseInt(cntstHist.getStdntFileid().toString()));
            ArrayList<FileVo> fileList= fileDao.getFileList(fileVo);
            cntstHist.setFileList(fileList);
        }
        
        return cntstHist;
    }

    /**
    * 공모전 참여 이력(환경일기장신청) 수정
    *
    * @Title : updateCntstAplySchlHist
    * @Description : 공모전 참여 이력(환경일기장신청) 수정
    * @param cntstAplyHistVoList
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateCntstAplySchlHist(CntstAplyHistVo cntstAplyHistVo) throws Exception {
        return cntstAplyHistDao.updateCntstAplySchlHist(cntstAplyHistVo);
    }
}
