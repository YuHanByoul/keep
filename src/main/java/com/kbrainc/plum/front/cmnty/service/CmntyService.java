package com.kbrainc.plum.front.cmnty.service;

import com.kbrainc.plum.mng.cmnty.model.CmntyVo;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 환경동아리 Service
 *
 * <pre>
 * com.kbrainc.plum.front.cmnty.service
 * - CmntyService.java
 * </pre>
 *
 * @author : KBrainc_SBD
 * @ClassName : CmntyService
 * @Description : 환경동아리 Service
 * @date : 2023. 02. 28.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Alias("front.CmntyService")
public interface CmntyService {
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
