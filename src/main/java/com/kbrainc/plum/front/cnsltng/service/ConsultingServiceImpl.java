/**
 * 
 */
package com.kbrainc.plum.front.cnsltng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.cnsltng.model.ConsultingDao;
import com.kbrainc.plum.front.cnsltng.model.ConsultingVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
* 마이페이지 > 체험환경 프로그램 지원관리 > 컨설팅 대상내역 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.front.cnsltng.service
* - ConsultingServiceImpl.java
* </pre> 
*
* @ClassName : ConsultingServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ConsultingServiceImpl extends PlumAbstractServiceImpl implements ConsultingService {

    @Autowired
    private ConsultingDao consultingDao;
    
    @Override
    public List<ConsultingVo> selectCnsltngList(ConsultingVo consultingVo) throws Exception {
        // TODO Auto-generated method stub
        return consultingDao.selectCnsltngList(consultingVo);
    }
}
