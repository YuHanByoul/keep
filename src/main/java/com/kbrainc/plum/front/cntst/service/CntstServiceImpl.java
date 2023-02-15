package com.kbrainc.plum.front.cntst.service;

import com.kbrainc.plum.front.cntst.model.CntstDao;
import com.kbrainc.plum.front.cntst.model.CntstVO;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 공모전 등록 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.prtpn.cntst.service
 * - CntstServiceImpl.java
 * </pre>
 *
 * @author : JBH
 * @ClassName : CntstServiceImpl
 * @Description : 공모전 등록 서비스 구현 클래스
 * @date : 2023. 01. 10.
 * @Version :
 * @Company : Copyright&copy; KBRAIN Company. All Rights Reserved
 */
@Service("front.cntstServiceImpl")
@Alias("front.cntstServiceImpl")
public class CntstServiceImpl extends PlumAbstractServiceImpl implements CntstService {

    @Resource(name = "front.cntstDao")
    CntstDao cntstDao;

    /**
     * 공모전 등록 목록 조회
     * Title : selectCntstList
     * Description : 공모전 등록 목록 조회
     *
     * @param cntstVO
     * @return list
     */
    @Override
    public List<CntstVO> selectCntstList(CntstVO cntstVO) {
        return cntstDao.selectCntstList(cntstVO);
    }
}
