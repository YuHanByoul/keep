package com.kbrainc.plum.front.inqry.service;

import javax.annotation.Resource;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.inqry.model.InqryDao;
import com.kbrainc.plum.front.inqry.model.InqryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

/**
 * 
 * 1:1문의 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.inqry.service
 * - InqryServiceImpl.java
 * </pre> 
 *
 * @ClassName : InqryServiceImpl
 * @Description : 1:1문의 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version : 
 * @Company : Copyright KBRAINC. All Rights Reserved
 */
@Service("front.inqryServiceImpl")
@Alias("front.inqryServiceImpl")
public class InqryServiceImpl extends PlumAbstractServiceImpl implements InqryService {

    @Resource(name = "front.inqryDao")
    private InqryDao inqryDao;

    /**
     * @Title : insertInqry
     * @Description : 1:1문의 등록
     * @param inqryAnswrVO 1:1문의VO 클래스
     * @throws Exception
     * @return int 등록 로우수
     */
    public int insertInqry(InqryVo inqryVO) throws Exception{
    	return inqryDao.insertInqry(inqryVO);
    };
    
}
