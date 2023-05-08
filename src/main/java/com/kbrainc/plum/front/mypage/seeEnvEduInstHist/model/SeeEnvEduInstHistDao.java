/**
 * 
 */
package com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.bizAply.model.SupplementVo;

/**
* 마이페이지 > 사회환경교육기관 지정 관리 > 신청내역 DAO 인터페이스
*
* <pre>
* com.kbrainc.plum.front.mypage.seeEnvEduInstHist.model
* - SeeEnvEduInstHistReportDao.java
* </pre> 
*
* @ClassName : SeeEnvEduInstHistReportDao
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 24.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface SeeEnvEduInstHistDao {
    /**
    * 신청내역 목록 조회. 
    *
    * @Title : selectSeeEnvEduInstHistList
    * @Description : TODO
    * @param clclnVo
    * @return
    * @throws Exception
    * @return List<SeeEnvEduInstHistVo>
     */
    List<SeeEnvEduInstHistVo> selectSeeEnvEduInstHistList(SeeEnvEduInstHistVo clclnVo) throws Exception;
    
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
