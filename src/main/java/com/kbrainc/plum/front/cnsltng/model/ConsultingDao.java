/**
 * 
 */
package com.kbrainc.plum.front.cnsltng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 컨설팅 대상내역 DAO
*
* <pre>
* com.kbrainc.plum.front.cnsltng.model
* - ConsultingDao.java
* </pre> 
*
* @ClassName : ConsultingDao
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface ConsultingDao {

    /**
    * 컨설팅 내역 조회. 
    *
    * @Title : selectCnsltngList
    * @Description : TODO
    * @param consultingVo
    * @return
    * @throws Exception
    * @return List<ConsultingVo>
     */
    List<ConsultingVo> selectCnsltngList(ConsultingVo consultingVo) throws Exception;
}
