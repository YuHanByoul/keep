package com.kbrainc.plum.mng.srng.service;

import com.kbrainc.plum.mng.code.model.CodeVo;
import com.kbrainc.plum.mng.srng.model.SrngFormQitemMapngVO;
import com.kbrainc.plum.mng.srng.model.SrngFormVO;
import com.kbrainc.plum.mng.srng.model.SrngQitemVO;
import com.kbrainc.plum.rte.model.UserVo;

import java.util.List;

/**
 * 심사양식관리
 *
 * <pre>
 * com.kbrainc.plum.mng.srng.service
 * - SrngSerivce.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : SrngSerivce
 * @Description :
 * @date : 2023. 01. 09.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
public interface SrngSerivce {

    /**
     * 심사 문항 목록 (팝업) 조회
     * Title : selectSrngList
     * Description : 심사 문항 목록 (팝업) 조회
     *
     * @param srngQitemVO SrngQitemVO
     * @return List<SrngQitemVO>   심사 문항 목록
     * @throws Exception
     */
    List<SrngQitemVO> selectSrngList(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 정보 조회
     * Title : selectSrng
     * Description : 심사 문항 정보 조회
     *
     * @param srngQitemVO
     * @return object qitem vo
     * @throws Exception
     */
    SrngQitemVO selectSrng(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 등록
     * Title : insertSrng
     * Description : 심사 문항 등록
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    int insertSrng(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사 문항 수정
     * Title : updateSrng
     * Description : 심사 문항 수정
     *
     * @param srngQitemVO
     * @return int
     * @throws Exception
     */
    int updateSrng(SrngQitemVO srngQitemVO) throws Exception;

    /**
     * 심사양식 정보 조회
     * Title : selectSrngForm
     * Description : 심사양식 정보 조회
     *
     * @param srngFormVO
     * @return srng form vo
     */
    SrngFormVO selectSrngForm(SrngFormVO srngFormVO);

    /**
     * 심사양식 문항목록 조회
     * Title : selectSrngFormQitem
     * Description : 심사양식 문항목록 조회
     *
     * @param srngFormQitemMapngVO
     * @return srng form qitem mapng vo
     */
    List<SrngFormQitemMapngVO> selectSrngFormQitemList(SrngFormQitemMapngVO srngFormQitemMapngVO);

    /**
     * 체크리스트 구분 코드 조회
     * Title : selectChklstSeCdList
     * Description : 체크리스트 구분 코드 조회
     *
     * @param codeVo
     * @return list
     */
    List<CodeVo> selectChklstSeCdList(CodeVo codeVo);

    /**
     * 심사양식 목록 조회
     * Title : selectSrngFormList
     * Description : 심사양식 목록 조회
     *
     * @param srngFormVO
     * @return list
     */
    List<SrngFormVO> selectSrngFormList(SrngFormVO srngFormVO);

    /**
     * 심사양식 등록
     * Title : insertSrngForm
     * Description : 심사양식 등록
     *
     * @param srngFormVO
     * @return int
     */
    int insertSrngForm(SrngFormVO srngFormVO);

    /**
     * 심사양식 같은 운영형태 사용여부 개수
     * Title : checkUseYnCnt
     * Description : 심사양식 같은 운영형태 사용여부 개수
     *
     * @param srngFormVO
     * @return int
     */
    int checkUseYnCnt(SrngFormVO srngFormVO);

    /**
     * 심사양식 수정
     * Title : updateSrngForm
     * Description : 심사양식 수정
     *
     * @param srngFormVO
     * @return int
     */
    int updateSrngForm(SrngFormVO srngFormVO);


    /**
     * 심사양식 문항 목록 등록
     * Title : insertSrngFormQitem
     * Description : 심사양식 문항 목록 등록
     *
     * @param dsgncrtrCds
     * @param srngFormQitemMapngVOs
     * @param user
     * @return boolean
     */
    boolean insertSrngFormQitem(String[] dsgncrtrCds, SrngFormQitemMapngVO[] srngFormQitemMapngVOs, UserVo user);


}
