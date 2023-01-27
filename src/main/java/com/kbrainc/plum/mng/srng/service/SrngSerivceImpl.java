package com.kbrainc.plum.mng.srng.service;

import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.srng.model.SrngDao;
import com.kbrainc.plum.mng.srng.model.SrngFormQitemMapngVO;
import com.kbrainc.plum.mng.srng.model.SrngFormVO;
import com.kbrainc.plum.mng.srng.model.SrngQitemVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SrngSerivceImpl extends PlumAbstractServiceImpl implements SrngSerivce {
    @Autowired
    private SrngDao srngDao;

    /**
     * 심사 문항 목록 (팝업) 조회
     * Title : selectSrngList
     * Description : 심사 문항 목록 (팝업) 조회
     *
     * @param srngQitemVO SrngQitemVO
     * @return List<SrngQitemVO>   심사 문항 목록
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

    /**
     * 심사양식 정보 조회
     * Title : selectSrngForm
     * Description : 심사양식 정보 조회
     *
     * @param srngFormVO
     * @return srng form vo
     */
    @Override
    public SrngFormVO selectSrngForm(SrngFormVO srngFormVO) {
        return srngDao.selectSrngForm(srngFormVO);
    }

    /**
     * 심사양식 문항목록 조회
     * Title : selectSrngFormQitem
     * Description : 심사양식 문항목록 조회
     *
     * @param srngFormQitemMapngVO
     * @return srng form qitem mapng vo
     */
    @Override
    public List<SrngFormQitemMapngVO> selectSrngFormQitemList(SrngFormQitemMapngVO srngFormQitemMapngVO) {
        return srngDao.selectSrngFormQitemList(srngFormQitemMapngVO);
    }

    /**
     * 체크리스트 구분 코드 조회
     * Title : selectChklstSeCdList
     * Description : 체크리스트 구분 코드 조회
     *
     * @param codeVo
     * @return list
     */
    @Override
    public List<CodeVo> selectChklstSeCdList(CodeVo codeVo) {
        return srngDao.selectChklstSeCdList(codeVo);
    }

    /**
     * 심사양식 목록 조회
     * Title : selectSrngFormList
     * Description : 심사양식 목록 조회
     *
     * @param srngFormVO
     * @return list
     */
    @Override
    public List<SrngFormVO> selectSrngFormList(SrngFormVO srngFormVO) {
        return srngDao.selectSrngFormList(srngFormVO);
    }

    /**
     * 심사양식 등록
     * Title : insertSrngForm
     * Description : 심사양식 등록
     *
     * @param srngFormVO
     * @return int
     */
    @Override
    public int insertSrngForm(SrngFormVO srngFormVO) {
        return srngDao.insertSrngForm(srngFormVO);
    }

    /**
     * 심사양식 같은 운영형태 사용여부 개수
     * Title : checkUseYnCnt
     * Description : 심사양식 같은 운영형태 사용여부 개수
     *
     * @param srngFormVO
     * @return int
     */
    @Override
    public int checkUseYnCnt(SrngFormVO srngFormVO) {
        return srngDao.checkUseYnCnt(srngFormVO);
    }

    /**
     * 심사양식 수정
     * Title : updateSrngForm
     * Description : 심사양식 수정
     *
     * @param srngFormVO
     * @return int
     */
    @Override
    public int updateSrngForm(SrngFormVO srngFormVO) {
        return srngDao.updateSrngForm(srngFormVO);
    }

    /**
     * 심사양식 문항 목록 등록
     * Title : insertSrngFormQitem
     * Description : 심사양식 문항 목록 등록
     *
     * @param srngFormQitemMapngVO
     * @return boolean
     */
    @Override
    @Transactional
    public boolean insertSrngFormQitem(SrngFormQitemMapngVO srngFormQitemMapngVO) {
        boolean result = false;
        if(srngDao.selectSrngFormQitemOrdr(srngFormQitemMapngVO) > 0) result = srngDao.deleteSrngFormDsgncrtrCdOrdr(srngFormQitemMapngVO);
        if(srngDao.selectSrngFormQitemMang(srngFormQitemMapngVO) > 0) result = srngDao.deleteSrngFormQitem(srngFormQitemMapngVO);
        if(srngFormQitemMapngVO.getSrngFormQitemMapngVOList() != null){
            if(srngDao.insertSrngFormDsgncrtrCdOrdr(srngFormQitemMapngVO) && srngDao.insertSrngFormQitem(srngFormQitemMapngVO)) result = true;
            else result = false;
        }

        return result;
    }
}
