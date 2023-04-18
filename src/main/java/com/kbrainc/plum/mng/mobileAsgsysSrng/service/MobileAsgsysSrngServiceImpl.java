package com.kbrainc.plum.mng.mobileAsgsysSrng.service;

import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngDao;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return mobileAsgsysSrngDao.selectAsgsysSrngInfo(mobileAsgsysSrngVo);
    }

    /**
     * 지원단심사 체크리스트 조회
     *
     * @Title : selectCheckList
     * @Description : 지원단심사 체크리스트 조회
     * @param mobileAsgsysSrngVo
     * @return List<MobileAsgsysSrngVo>
     * @throws Exception
     */
    @Override
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception {
        return mobileAsgsysSrngDao.selectCheckList(mobileAsgsysSrngVo);
    }
}
