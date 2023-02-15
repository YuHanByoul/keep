package com.kbrainc.plum.front.cntst.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 공모전등록Dao 맵퍼 인터페이스.
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.model
 * - CntstDao.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstDao
 * @Description :
 * @date : 2023. 01. 12.
 * @Version : 공모전등록Dao 맵퍼 인터페이스.
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper("front.cntstDao")
public interface CntstDao {
    /**
     * 공모전 목록 조회
     * Title : selectCntstList
     * Description : 공모전 목록 조회
     *
     * @param cntstVO
     * @return list
     */
    List<CntstVO> selectCntstList(CntstVO cntstVO);
    
}
