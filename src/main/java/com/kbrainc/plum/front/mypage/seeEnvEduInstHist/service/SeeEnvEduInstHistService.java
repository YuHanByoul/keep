/**
 * 
 */
package com.kbrainc.plum.front.mypage.seeEnvEduInstHist.service;

import java.util.List;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;
import com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model.SeeEnvEduInstHistVo;

/**
* 마이페이지 > 사회환경교육기관 지정 관리 > 신청내역 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.front.mypage.seeEnvEduInstHist.service
* - SeeEnvEduInstHistService.java
* </pre> 
*
* @ClassName : SeeEnvEduInstHistService
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface SeeEnvEduInstHistService {

    /**
    * 신청내역 목록 조회. 
    *
    * @Title : selectSeeEnvEduInstHistList
    * @Description : TODO
    * @param seeEnvEduInstHistVo
    * @return
    * @throws Exception
    * @return List<SeeEnvEduInstHistVo>
     */
    List<SeeEnvEduInstHistVo> selectSeeEnvEduInstHistList(SeeEnvEduInstHistVo seeEnvEduInstHistVo) throws Exception;
    
    /**
    * 보완요청 상세조회 
    *
    * @Title : selectSplmntInfo
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return SupplementVo
     */
    SupplementVo selectSplmntInfo(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청 답변 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateSplmnt(SupplementVo supplementVo) throws Exception;
    
}
