package com.kbrainc.plum.mng.srng.service;

import com.kbrainc.plum.mng.srng.model.SrngDao;
import com.kbrainc.plum.mng.srng.model.SrngQitemVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SrngSerivceImpl extends PlumAbstractServiceImpl implements SrngSerivce {
    @Autowired
    private SrngDao srngDao;

    /**
     * 심사 문항 목록 조회
     * Title : selectSrngList
     * Description : 심사 문항 목록 조회
     *
     * @param srngQitemVO SrngQitemVO
     * @return List<SrngQitemVO> 심사 문항 목록
     * @throws Exception
     */
    @Override
    public List<SrngQitemVO> selectSrngList(SrngQitemVO srngQitemVO) throws Exception {
        return srngDao.selectSrngList(srngQitemVO);
    }

    /**
     * 심사 문항 정보 조회
     * Title : selectSrng
     * Description : 심사 문항 정보 조회
     *
     * @param srngQitemVO
     * @return object qitem vo
     * @throws Exception
     */
    @Override
    public SrngQitemVO selectSrng(SrngQitemVO srngQitemVO) throws Exception{
        return srngDao.selectSrng(srngQitemVO);
    }

    /**
     * 심사 문항 등록
     * Title : insertSrng
     * Description : 심사 문항 등록
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    @Override
    public int insertSrng(SrngQitemVO srngQitemVO) throws Exception{
        int retVal = 0;
        retVal += srngDao.insertSrng(srngQitemVO);

        return retVal;
    }

    /**
     * 심사 문항 수정
     * Title : updateSrng
     * Description : 심사 문항 수정
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    @Override
    public int updateSrng(SrngQitemVO srngQitemVO) throws Exception {
        int retVal = 0;
        retVal += srngDao.updateSrng(srngQitemVO);

        return retVal;
    }

}
