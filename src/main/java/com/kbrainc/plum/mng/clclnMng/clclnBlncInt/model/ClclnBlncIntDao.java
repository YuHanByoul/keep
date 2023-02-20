package com.kbrainc.plum.mng.clclnMng.clclnBlncInt.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 체험환경교육 지원사업 -> 정산관리 -> 잔액및이자관리 Dao 클래스
**
<pre>
* com.kbrainc.plum.mng.clclnMng.clclnBlncInt.model
* - ClclnBlncIntDao.java
* </pre>
**
@ClassName : ClclnBlncIntDao
* @Description : 체험환경교육 지원사업 -> 정산관리 -> 잔액및이자관리 Dao 클래스
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ClclnBlncIntDao {
    
    /**
    * 잔액및이자관리 게시글 목록 조회
    *
    * @Title : selectClclnBlncIntList
    * @Description : 잔액및이자관리 게시글 목록 조회
    * @param clclnBlncIntVo 잔액및이자관리 객체
    * @throws Exception 예외
    * @return List<ClclnBlncIntVo>
    */
    public List<ClclnBlncIntVo> selectClclnBlncIntList(ClclnBlncIntVo clclnBlncIntVo) throws Exception;

    /**
     * 잔액및이자관리 게시글 상세목록 조회
     *
     * @Title : selectClclnBlncIntDetailList
     * @Description : 잔액및이자관리 게시글 상세목록 조회
     * @param clclnBlncIntVo 잔액및이자관리 객체
     * @throws Exception 예외
     * @return List<ClclnBlncIntVo>
     */
    public List<ClclnBlncIntVo> selectClclnBlncIntDetailList(ClclnBlncIntVo clclnBlncIntVo) throws Exception;

    /**
     * 잔액및이자관리 상세정보 조회
     *
     * @Title : selectClclnBlncIntDetailInfo
     * @Description : 잔액및이자관리 상세정보 조회
     * @param clclnBlncIntVo 잔액및이자관리 객체
     * @throws Exception 예외
     * @return ClclnBlncIntVo
     */
    public ClclnBlncIntVo selectClclnBlncIntDetailInfo(ClclnBlncIntVo clclnBlncIntVo) throws Exception;

    /**
     * 잔액및이자관리 상세정보 집행내역 개요서 리스트 조회
     *
     * @Title : selectClclnBlncIntDetailOutlList
     * @Description : 잔액및이자관리 상세정보 집행내역 개요서 리스트 조회
     * @param clclnBlncIntVo 잔액및이자관리 객체
     * @throws Exception 예외
     * @return List<ClclnBlncIntVo>
     */
    public List<ClclnBlncIntVo> selectClclnBlncIntDetailOutlList(ClclnBlncIntVo clclnBlncIntVo) throws Exception;

    /**
     * 잔액및이자관리 상세정보 집행상세내역 개요서 리스트 조회
     *
     * @Title : selectClclnBlncIntDetailOutlDtlList
     * @Description : 잔액및이자관리 상세정보 집행상세내역 개요서 리스트 조회
     * @param clclnBlncIntVo 잔액및이자관리 객체
     * @throws Exception 예외
     * @return List<ClclnBlncIntVo>
     */
    public List<ClclnBlncIntVo> selectClclnBlncIntDetailOutlDtlList(ClclnBlncIntVo clclnBlncIntVo) throws Exception;

    /**
    * 잔액및이자관리 상세목록 반납여부 수정
    **
    @Title : updateClclnBlncIntRturnYn
    * @Description : 잔액및이자관리 상세목록 반납여부 수정
    * @param clclnBlncIntVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateClclnBlncIntRturnYn(ClclnBlncIntVo clclnBlncIntVo) throws Exception;
    
}
