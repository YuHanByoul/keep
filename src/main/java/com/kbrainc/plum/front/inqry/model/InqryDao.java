package com.kbrainc.plum.front.inqry.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 1:1문의 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.model
 * - InqryDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : InqryDao
 * @Description : 1:1문의 Dao 클래스
 * @date : 2023. 02. 03.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.inqryDao")
public interface InqryDao {
    public List<InqryVo> selectInqryList(InqryVo inqryVo) throws Exception;

    public InqryVo selectInqryDetail(InqryVo inqryVo) throws Exception;

    public int insertInqry(InqryVo inqryVo) throws Exception;

    public int updateInqry(InqryVo inqryVo) throws Exception;

    public int deleteInqry(InqryVo inqryVo) throws Exception;
}
