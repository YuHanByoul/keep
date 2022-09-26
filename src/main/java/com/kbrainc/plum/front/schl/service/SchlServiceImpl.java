package com.kbrainc.plum.front.schl.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.schl.model.SchlDao;
import com.kbrainc.plum.front.schl.model.SchlVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 학교정보 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.front.schl.service
* - MemberService.java
* </pre> 
*
* @ClassName : MemberService
* @Description : 회원정보 서비스 구현 클래스
* @author : KBRAINC
* @date : 2021. 11. 18.
* @Version : 
* @Company : Copyright KBRAINC. All Rights Reserved 
*/

@Service("front.schlServiceImpl")
@Alias("front.schlServiceImpl")
public class SchlServiceImpl extends PlumAbstractServiceImpl implements SchlService {

    @Resource(name = "front.schlDao")
    private SchlDao SchlDao;
    
    
    /**
    *
    * 학교리스트 호출 
    *
    * @Title : selectSchlList
    * @Description : 
    * @param ShclVo ShclVo객체
    * @return List<ShclVo> 
    * @throws Exception 예외
    */
   public List<SchlVo> selectSchlList(SchlVo SchlVo) throws Exception{
	   return SchlDao.selectSchlList(SchlVo);
   };
      
}