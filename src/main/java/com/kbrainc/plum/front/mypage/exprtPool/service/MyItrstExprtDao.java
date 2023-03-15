package com.kbrainc.plum.front.mypage.exprtPool.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.ExprtVo;
import com.kbrainc.plum.rte.model.UserVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 마이페이지 > 전문가 풀 관리 > 관심 전문가 Dao 인터페이스
 * 
 * <pre>
 * com.kbrainc.plum.front.mypage.exprtPool.service
 * - MyItrstExprtDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : MyItrstExprtDao
 * @Description :
 * @date : 2023. 03. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface MyItrstExprtDao {
    public List<ExprtVo> selectItrstExprtList(ExprtVo exprtVo) throws Exception;
}
