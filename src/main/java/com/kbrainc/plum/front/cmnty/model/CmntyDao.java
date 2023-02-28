package com.kbrainc.plum.front.cmnty.model;

import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import org.apache.ibatis.type.Alias;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 환경동아리 Dao
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.model
 * - CmntyDao.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyDao
 * @Description : 환경동아리 Dao
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Mapper("front.CmntyDao")
@Alias("front.CmntyDao")
public interface CmntyDao {

    /**
     * 환경동아리 목록
     * Title : selectCmntyList
     * Description : 환경동아리 목록
     *
     * @param cmntyVo
     * @return list
     */
    List<CmntyVo> selectCmntyList(CmntyVo cmntyVo);
}
