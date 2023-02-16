/**
 * 
 */
package com.kbrainc.plum.mng.score.service;

import java.util.List;

import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.mng.score.model.ScoreCardVo;

/**
* 심사양식 관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.score.service
* - ScoreCardService.java
* </pre> 
*
* @ClassName : ScoreCardService
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ScoreCardService {

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
     * 문항 저장. 
     *
     * @Title : saveQuestion
     * @Description : TODO
     * @param questionVo
     * @return
     * @throws Exception
     * @return int
      */
    int saveQuestion(QuestionVo questionVo) throws Exception;
}
