/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvDelvry.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 사회환경교육기관 지정 > 교부관리 DAO
*
* <pre>
* com.kbrainc.plum.mng.seeEnvDelvry.model
* - SeeEnvDelvryDao.java
* </pre> 
*
* @ClassName : SeeEnvDelvryDao
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface SeeEnvDelvryDao {
    
    /**
    * 교부관리 목록 조회 
    *
    * @Title : selectDelvryList
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @return
    * @throws Exception
    * @return List<SeeEnvDelvryVo>
     */
    List<SeeEnvDelvryVo> selectDelvryList(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception;
    
    /**
    * 발급 / 재발급 
    *
    * @Title : updateIssue
    * @Description : TODO
    * @param seeEnvDelvryVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateIssue(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception;

}
