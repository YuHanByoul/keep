package com.kbrainc.plum.mng.wbzn.carbon.opnn.service;

import java.util.List;

import com.kbrainc.plum.mng.wbzn.carbon.opnn.model.CarbonOpnnVo;
import com.kbrainc.plum.mng.wbzn.now.opnn.model.OpnnVo;

/**
* 탄소중립환경교육 -> 독자소리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.wbzn.carbon.opnn.service
* - OpnnService.java
* </pre>
*
* @ClassName : CarbonOpnnService
* @Description : 탄소중립환경교육 -> 독자소리 서비스 인터페이스
* @author : JD
* @date : 2022. 12. 19.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface CarbonOpnnService {
    
    /**
    * 독자소리 게시글 목록 조회
    *
    * @Title : selectOpnnList
    * @Description : 독자소리 게시글 목록 조회
    * @param CarbonOpnnVo 독자소리 객체
    * @throws Exception 예외
    * @return List<EnveduVo>
    */
    public List<CarbonOpnnVo> selectOpnnList(CarbonOpnnVo opnnVo) throws Exception;
}
