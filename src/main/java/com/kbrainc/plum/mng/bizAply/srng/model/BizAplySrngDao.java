/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.srng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.score.model.QuestionVo;

/**
* [심사관리 DAO 인터페이스]. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.srng.model
* - BizAplySrngDao.java
* </pre> 
*
* @ClassName : BizAplySrngDao
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface BizAplySrngDao {

    /**
    * [심사결과 조회]. 
    *
    * @Title : detailBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return BizAplySrngVo
     */
    BizAplySrngVo detailBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [심사결과 등록]. 
    *
    * @Title : insertBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [심사결과 수정]. 
    *
    * @Title : updateBizAplySrng
    * @Description : TODO
    * @param bizAplySrngVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception;
    
    /**
    * [문항별 심사결과 점수 등록]. 
    *
    * @Title : insertItem
    * @Description : TODO
    * @param questionVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertItem(QuestionVo questionVo) throws Exception;
    
    /**
    * [문항별 심사결과 점수 수정]. 
    *
    * @Title : updateItem
    * @Description : TODO
    * @param questionVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateItem(QuestionVo questionVo) throws Exception;
    
    /**
    * [심사위원 목록 조회]. 
    *
    * @Title : selectCnsltngExprtList
    * @Description : TODO
    * @return
    * @throws Exception
    * @return List<BizAplySrngVo>
     */
    List<BizAplySrngVo> selectCnsltngExprtList() throws Exception;
}
