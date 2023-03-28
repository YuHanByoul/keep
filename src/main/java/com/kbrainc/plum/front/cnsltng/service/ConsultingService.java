/**
 * 
 */
package com.kbrainc.plum.front.cnsltng.service;

import java.util.List;

import com.kbrainc.plum.front.cnsltng.model.ConsultingVo;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 컨설팅 대상내역 서비스
*
* <pre>
* com.kbrainc.plum.front.cnsltng.service
* - ConsultingService.java
* </pre> 
*
* @ClassName : ConsultingService
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface ConsultingService {

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
