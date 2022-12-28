package com.kbrainc.plum.mng.blcklst.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.member.model.BlcklstDsctnVo;

/**
* 블랙리스트 내역 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.blcklst.model
* - BlcklstDsctnDao.java
* </pre>
*
* @ClassName : BlcklstDsctnDao
* @Description : 블랙리스트 내역 Dao 클래스
* @author : JD
* @date : 2022. 12. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface BlcklstDsctnDao {
    
    /**
    * 블랙리스트 내역 게시글 목록 조회
    *
    * @Title : selectBlcklstDsctnList
    * @Description : 블랙리스트 내역 게시글 목록 조회
    * @param BlcklstDsctnVo 블랙리스트 내역 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<BlcklstDsctnVo> selectBlcklstDsctnList(BlcklstDsctnVo blcklstDsctnVo) throws Exception;
}
