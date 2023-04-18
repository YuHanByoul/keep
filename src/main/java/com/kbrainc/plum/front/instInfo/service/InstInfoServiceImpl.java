package com.kbrainc.plum.front.instInfo.service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileDao;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.instInfo.model.InstInfoDao;
import com.kbrainc.plum.front.instInfo.model.InstInfoVo;
import com.kbrainc.plum.front.instInfo.model.InstPicVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 기관정보 Service 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.instInfo.service
* - InstInfoServiceImpl.java
* </pre>
*
* @ClassName : InstInfoServiceImpl
* @Description : TODO
* @author : JD
* @date : 2023. 3. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service("front.instInfoServiceImpl")
@Alias("front.instInfoServiceImpl")
public class InstInfoServiceImpl extends PlumAbstractServiceImpl implements InstInfoService{
    
    @Resource(name = "front.instInfoDao")
    private InstInfoDao instInfoDao;
    
    @Autowired
    private FileDao fileDao;
    
    /**
    * 기관정보 조회
    *
    * @Title : selectInstInfo
    * @Description : 기관정보 조회
    * @param instInfoVo
    * @throws Exception
    * @return InstInfoVo
    */
    @Override
    public InstInfoVo selectInstInfo(InstInfoVo instInfoVo) throws Exception {
        InstInfoVo instInfo = instInfoDao.selectInstInfo(instInfoVo);
        
        if(instInfo != null && Objects.nonNull(instInfo.getLogoFileid())) {
            FileVo fileVo = new FileVo();
            fileVo.setFileid(Integer.parseInt(instInfo.getLogoFileid().toString()));
            FileVo fileInfo= fileDao.getFileInfo(fileVo);
            instInfo.setFileInfo(fileInfo);
        }
        
        return instInfo;
    }
    
    /**
    * 기관정보 수정
    *
    * @Title : updateInstInfo
    * @Description : 기관정보 수정
    * @param instInfoVo
    * @throws Exception
    * @return int
    */
    @Override
    public int updateInstInfo(InstInfoVo instInfoVo) throws Exception {
        return instInfoDao.updateInstInfo(instInfoVo);
    }

    /**
    * 기관 담당자 목록 조회
    *
    * @Title : selectInstPictList
    * @Description : 기관 담당자 목록 조회
    * @param instPicVo
    * @throws Exception
    * @return List<InstPicVo>
    */
    @Override
    public List<InstPicVo> selectInstPictList(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectInstPictList(instPicVo);
    }

    /**
    * 기관 담당자 정보 조회
    *
    * @Title : selectInstPicInfo
    * @Description : 기관 담당자 정보 조회
    * @param instPicVo
    * @throws Exception
    * @return InstPicVo
    */
    @Override
    public InstPicVo selectInstPicInfo(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectInstPicInfo(instPicVo);
    }

    /**
    * 기관 담당자 등록 > 회원 목록 조회
    *
    * @Title : selectPicSearchList
    * @Description : 기관 담당자 등록 > 회원 목록 조회
    * @param instPicVo
    * @throws Exception
    * @return List<InstPicVo>
    */
    @Override
    public List<InstPicVo> selectPicSearchList(InstPicVo instPicVo) throws Exception {
        return instInfoDao.selectPicSearchList(instPicVo);
    }

    /**
    * 기관 담당자 등록
    *
    * @Title : updateRegInstPic
    * @Description : 기관 담당자 등록
    * @param instPicVo
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateRegInstPic(InstPicVo instPicVo) throws Exception {
        return instInfoDao.updateRegInstPic(instPicVo);
    }

    /**
    * 기관 담당자 해지
    *
    * @Title : updateCancelInstPic
    * @Description : 기관 담당자 해지
    * @param instPicVo
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateCancelInstPic(InstPicVo instPicVo) throws Exception {
        return instInfoDao.updateCancelInstPic(instPicVo);
    }
    
}
