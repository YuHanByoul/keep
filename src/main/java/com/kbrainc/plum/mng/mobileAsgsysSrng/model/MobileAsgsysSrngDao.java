package com.kbrainc.plum.mng.mobileAsgsysSrng.model;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
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
     * 지원단심사 체크리스트 조회
     *
     * @Title : selectSprtgrpSrngList
     * @Description : 지원단심사 체크리스트 조회
     * @param mobileAsgsysSrngVo
     * @return List<MobileAsgsysSrngVo>
     * @throws Exception
     */
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;
}
