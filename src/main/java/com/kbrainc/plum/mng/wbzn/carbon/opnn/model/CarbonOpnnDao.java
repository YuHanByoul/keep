package com.kbrainc.plum.mng.wbzn.carbon.opnn.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.mmnws.model.MmnwsVo;
import com.kbrainc.plum.mng.wbzn.now.prgrmgd.model.PrgrmgdVo;

/**
* 탄소중립환경교육 -> 독자소리 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.opnn.model
* - OpnnDao.java
* </pre>
*
* @ClassName : CarbonOpnnDao
* @Description : 탄소중립환경교육 -> 독자소리 Dao 클래스
* @author : JD
* @date : 2022. 12. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface CarbonOpnnDao {
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param carbonOpnnVo 독자소리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonOpnnVo> selectOpnnList(CarbonOpnnVo carbonOpnnVo) throws Exception;
}
