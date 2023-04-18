package com.kbrainc.plum.mng.dwnldDsctn.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * 다운로드 사유 등록Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntstRcptHist.service
 * - DwnldDsctnDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : DwnldDsctnDao
 * @Description : 다운로드 사유 등록Dao 인터페이스
 * @date : 2023. 04. 17.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface DwnldDsctnDao {
    /**
     * 다운로드 사유 등록
     *
     * @param dwnldDsctnVo
     * @return int
     * @throws Exception
     * @Title : insertDwnldDsctn
     * @Description : 다운로드 사유 등록
     */
    public int insertDwnldDsctn(DwnldDsctnVo dwnldDsctnVo) throws Exception;
}
