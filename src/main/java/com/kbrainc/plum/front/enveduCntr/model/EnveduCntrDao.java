package com.kbrainc.plum.front.enveduCntr.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 지역 환경교육센처 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.enveduCntr.model
 * - EnveduCntrDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : EnveduCntrDao
 * @Description : 지역 환경교육센처 Dao
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */

@Mapper
public interface EnveduCntrDao {

    /**
     * 지역 환경교육센터 목록 조회
     * Title : selectEnveduCntrList
     * Description : 지역 환경교육센터 목록 조회
     *
     * @param enveduCntrVO
     * @return list
     */
    List<EnveduCntrVO> selectEnveduCntrList(EnveduCntrVO enveduCntrVO);
}
