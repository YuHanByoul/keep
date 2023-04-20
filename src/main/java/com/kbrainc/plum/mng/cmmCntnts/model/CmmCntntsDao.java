package com.kbrainc.plum.mng.cmmCntnts.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 콘텐츠 품질관리 체크리스트 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.cmmCntnts.model
* - CmmCntntsDao.java
* </pre>
**
@ClassName : CmmCntntsDao
* @Description : 콘텐츠 품질관리 체크리스트 Dao 클래스
* @author : 이한명
* @date : 2023. 4. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CmmCntntsDao {
    
    /**
    * 콘텐츠 품질관리 체크리스트 체크ID 조회
    *
    * @Title : selectCmmCntntsQlityChkId
    * @Description : 콘텐츠 품질관리 체크리스트 체크 조회
    * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return int
    */
    public int selectCmmCntntsQlityChkId(CmmCntntsVo cmmCntnts) throws Exception;
    
    /**
     * 콘텐츠 품질관리 체크리스트 상세 조회
     *
     * @Title : selectCmmCntntsQlityChkInfo
     * @Description : 콘텐츠 품질관리 체크리스트 상세 조회
     * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
     * @throws Exception 예외
     * @return CmmCntntsVo
     */
    public CmmCntntsVo selectCmmCntntsQlityChkInfo(CmmCntntsVo cmmCntnts) throws Exception;

    /**
     * 콘텐츠 품질관리 체크리스트 목록 조회
     *
     * @Title : selectCmmCntntsList
     * @Description : 콘텐츠 품질관리 체크리스트 목록 조회
     * @param cmmCntnts 콘텐츠 품질관리 체크리스트 객체
     * @throws Exception 예외
     * @return List<CmmCntntsVo>
     */
    public List<CmmCntntsVo> selectCmmCntntsQlityChklstList(CmmCntntsVo cmmCntnts) throws Exception;
    
    /**
    * 콘텐츠 품질관리 체크리스트 등록
    *
    * @Title : insertCmmCntntsQlityCeck
    * @Description : 콘텐츠 품질관리 체크리스트 등록
    * @param cmmCntntsVo 콘텐츠 품질관리 체크리스트 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertCmmCntntsQlityCeck(CmmCntntsVo cmmCntntsVo) throws Exception;

    /**
     * 콘텐츠 품질관리 체크리스트 항목 등록
     *
     * @Title : insertCmmCntntsQlityCeckArtcl
     * @Description : 콘텐츠 품질관리 체크리스트 항목 등록
     * @param cmmCntntsVo 콘텐츠 품질관리 체크리스트 객체
     * @throws Exception 예외
     * @return int
     */
    public int insertCmmCntntsQlityCeckArtcl(CmmCntntsVo cmmCntntsVo) throws Exception;

}
