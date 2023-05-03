package com.kbrainc.plum.mng.mobileAsgsysSrng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.model
* - MobileAsgsysSrngDao.java
* </pre>
*
* @ClassName : MobileAsgsysSrngDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MobileAsgsysSrngDao {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 시설 목록 조회
    * @param mobileAsgsysSrngVo 시설 객체
    * @throws Exception 예외
    * @return List<MobileAsgsysSrngVo>
    */
    public List<MobileAsgsysSrngVo> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 지원단심사 상세 조회
     *
     * @Title : selectAsgsysSrngInfo
     * @Description : 지원단심사 상세 조회
     * @param mobileAsgsysSrngVo
     * @return MobileAsgsysSrngVo
     * @throws Exception
     */
    public MobileAsgsysSrngVo selectAsgsysSrngInfo(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
    * 지원단심사 상세 수정
    *
    * @Title : updateAsgsysSrng
    * @Description : 지원단심사 상세 수정
    * @param mobileAsgsysSrngVo
    * @throws Exception
    * @return int
    */
    public int updateAsgsysSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;
    
    /**
    * 체크리스트 목록 조회
    *
    * @Title : selectCheckList
    * @Description : 체크리스트 목록 조회
    * @param mobileAsgsysSrngVo
    * @throws Exception
    * @return List<MobileAsgsysSrngVo>
    */
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
    * 지원단 심사 수정
    *
    * @Title : updateSprtgrpSrng
    * @Description : 지원단 심사 수정
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int updateSprtgrpSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 제출 등록
    *
    * @Title : insertChkLstSbmsn
    * @Description : 체크리스트 제출 등록
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int insertChkLstSbmsn(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 답변 등록
    *
    * @Title : insertChkLstAns
    * @Description : 체크리스트 답변 등록
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int insertChkLstAns(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 구분 순서 답변 등록
    *
    * @Title : insertChkLstSeOrdrAns
    * @Description : 체크리스트 구분 순서 답변 등록
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int insertChkLstSeOrdrAns(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 제출 수정
    *
    * @Title : updateChkLstSbmsn
    * @Description : 체크리스트 제출 수정
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int updateChkLstSbmsn(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 답변 수정
    *
    * @Title : updateChkLstAns
    * @Description : 체크리스트 답변 수정
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int updateChkLstAns(MobileAsgsysSrngVo mobileAsgsysSrngVo);

    /**
    * 체크리스트 구분 순서 답변 수정
    *
    * @Title : updateChkLstSeOrdrAns
    * @Description : 체크리스트 구분 순서 답변 수정
    * @param mobileAsgsysSrngVo
    * @return int
    */
    public int updateChkLstSeOrdrAns(MobileAsgsysSrngVo mobileAsgsysSrngVo);
}
