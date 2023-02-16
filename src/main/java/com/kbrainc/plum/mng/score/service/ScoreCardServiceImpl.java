/**
 * 
 */
package com.kbrainc.plum.mng.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.mng.score.model.ScoreCardDao;
import com.kbrainc.plum.mng.score.model.ScoreCardVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 심사양식 관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.score.service
* - ScoreCardServiceImpl.java
* </pre> 
*
* @ClassName : ScoreCardServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 15.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ScoreCardServiceImpl extends PlumAbstractServiceImpl implements ScoreCardService {

    @Autowired
    private ScoreCardDao scoreCardDao;

    @Override
    public List<ScoreCardVo> selectScoreCardList(ScoreCardVo scoreCardVo) throws Exception {
        // TODO Auto-generated method stub
        return scoreCardDao.selectScoreCardList(scoreCardVo);
    }

    @Override
    public int insertScoreCard(ScoreCardVo scoreCardVo) throws Exception {
        // TODO Auto-generated method stub
        return scoreCardDao.insertScoreCard(scoreCardVo);
    }

    @Override
    public int updateScoreCard(ScoreCardVo scoreCardVo) throws Exception {
        // TODO Auto-generated method stub
        return scoreCardDao.updateScoreCard(scoreCardVo);
    }

    @Override
    public List<QuestionVo> selectQuestionList(QuestionVo questionVo) throws Exception {
        // TODO Auto-generated method stub
        return scoreCardDao.selectQuestionList(questionVo);
    }

    @Transactional
    @Override
    public int saveQuestion(QuestionVo questionVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        if (questionVo.getQitemArr() != null && questionVo.getQitemArr().length > 0) {
            result = scoreCardDao.deleteQuestion(questionVo);
            for (int i = 0; i < questionVo.getQitemArr().length; i++) {
                QuestionVo param = new QuestionVo();
                param.setUser(questionVo.getUser());
                param.setFormid(questionVo.getFormid());
                param.setAltm(questionVo.getAltmArr()[i]);
                param.setOrdr(questionVo.getOrdrArr()[i]);
                param.setQitem(questionVo.getQitemArr()[i]);
                param.setQitemSeCd(questionVo.getQitemSeCdArr()[i]);
                
                result = scoreCardDao.insertQuestion(param);                
            }
        }
        return result;
    }
}
