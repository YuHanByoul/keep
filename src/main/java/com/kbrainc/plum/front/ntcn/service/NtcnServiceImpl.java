package com.kbrainc.plum.front.ntcn.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.ntcn.model.NtcnDao;
import com.kbrainc.plum.front.ntcn.model.NtcnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * PopServiceImpl
 *
 * <pre>
 * com.kbrainc.plum.mng.pop.service
 * - PopServiceImpl.java
 * </pre> 
 *
 * @ClassName : PopServiceImpl
 * @Description : PopServiceImpl
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service("front.ntcnServiceImpl")
@Alias("front.ntcnServiceImpl")
public class NtcnServiceImpl extends PlumAbstractServiceImpl implements NtcnService {

	@Resource(name = "front.ntcnDao")
	NtcnDao ntcnDao;
	
    /**
     * @Title : selectMainNtcnList
     * @Description : 메인화면 알림리스트(5개) 호출 
     * @param NtcnVo
     * @throws Exception
     * @return List<NtcnVo> 객체
     */
    public List<NtcnVo> selectMainNtcnList(NtcnVo ntcnVo) throws Exception{
        return ntcnDao.selectMainNtcnList(ntcnVo);
    }
}
