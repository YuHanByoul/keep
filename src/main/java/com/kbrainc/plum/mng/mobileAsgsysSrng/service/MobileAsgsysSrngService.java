package com.kbrainc.plum.mng.mobileAsgsysSrng.service;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.mobileAsgsysSrng.model.MobileAsgsysSrngVo;

import java.util.List;

import javax.validation.Valid;

/**
* 시설 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.service
* - MobileAsgsysSrngService.java
* </pre>
*
* @ClassName : MobileAsgsysSrngService
* @Description : 시설 서비스 인터페이스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface MobileAsgsysSrngService {
    
    /**
    * 지원단심사 목록 조회
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
    * @Description : TODO
    * @param mobileAsgsysSrngVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateAsgsysSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;
    
    /**
    * 체크리스트 목록 조회
    *
    * @Title : selectCheckList
    * @Description : TODO
    * @param mobileAsgsysSrngVo
    * @return
    * @throws Exception
    * @return List<MobileAsgsysSrngVo>
    */
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
    * 지원담심사 등록
    *
    * @Title : insertSprtgrpSrng
    * @Description : TODO
    * @param mobileAsgsysSrngVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertSprtgrpSrng(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;
}
