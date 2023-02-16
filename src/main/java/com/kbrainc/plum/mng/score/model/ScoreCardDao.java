/**
 * 
 */
package com.kbrainc.plum.mng.score.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 심사양식 관리 DAO 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.score.model
* - ScoreCardDao.java
* </pre> 
*
* @ClassName : ScoreCardDao
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ScoreCardDao {
    
    
    /**
    * 심사양식 관리 목록 조회. 
    *
    * @Title : selectScoreCardList
    * @Description : TODO
    * @param scoreCardVo
    * @return
    * @throws Exception
    * @return List<ScoreCardVo>
     */
    List<ScoreCardVo> selectScoreCardList(ScoreCardVo scoreCardVo) throws Exception;
    
    /**
    * 심사양식 등록. 
    *
    * @Title : insertScoreCard
    * @Description : TODO
    * @param scoreCardVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertScoreCard(ScoreCardVo scoreCardVo) throws Exception;
    
    /**
    * 심사양식 수정. 
    *
    * @Title : updateScoreCard
    * @Description : TODO
    * @param scoreCardVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateScoreCard(ScoreCardVo scoreCardVo) throws Exception;
    
    /**
    * 문항 목록 조회. 
    *
    * @Title : selectQuestionList
    * @Description : TODO
    * @param questionVo
    * @return
    * @throws Exception
    * @return List<QuestionVo>
     */
    List<QuestionVo> selectQuestionList(QuestionVo questionVo) throws Exception;
    
    /**
    * 문항 삭제. 
    *
    * @Title : deleteQuestion
    * @Description : TODO
    * @param questionVo
    * @return
    * @throws Exception
    * @return int
     */
    int deleteQuestion(QuestionVo questionVo) throws Exception;
    
    /**
    * 문항 등록. 
    *
    * @Title : insertQuestion
    * @Description : TODO
    * @param questionVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertQuestion(QuestionVo questionVo) throws Exception;
}
