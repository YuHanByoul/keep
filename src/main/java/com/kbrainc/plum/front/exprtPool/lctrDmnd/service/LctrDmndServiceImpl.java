package com.kbrainc.plum.front.exprtPool.lctrDmnd.service;

import com.kbrainc.plum.front.exprtPool.lctrDmnd.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 환경교육 전문가 풀 > 섭외 요청 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.exprtPool.lctrDmnd.service
 * - LctrDmndServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : LctrDmndServiceImpl
 * @Description : 환경교육 전문가 풀 > 섭외 요청 서비스 구현 클래스
 * @date : 2023. 02. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service("front.lctrDmndService")
@Alias("front.lctrDmndService")
public class LctrDmndServiceImpl extends PlumAbstractServiceImpl implements LctrDmndService {

    @Resource(name = "front.lctrDmndDao")
    private LctrDmndDao lctrDmndDao;

    /**
     * 전문가 목록 조회
     *
     * @param searchVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 전문가 목록 조회
     */
    @Override
    public List<ExprtVo> selectExprtList(ExprtVo searchVo) throws Exception {
        return lctrDmndDao.selectExprtList(searchVo);
    }

    /**
     * 전문가 상세 조회
     *
     * @param exprtVo
     * @return exprt vo
     * @throws Exception
     * @Title : selectExprt
     * @Description : 전문가 상세 조회
     */
    @Override
    public ExprtVo selectExprt(ExprtVo exprtVo) throws Exception {
        ExprtVo exprt = lctrDmndDao.selectExprt(exprtVo);

        if(exprt.getQlfcRlsYn().equals("Y")) {
            List<ExprtCrtfctVo> exprtCrtfctList = lctrDmndDao.selectExprtCrtfctList(exprtVo);
            exprt.setExprtCrtfctList(exprtCrtfctList);
        }

        if(exprt.getHdofRlsYn().equals("Y")) {
            List<ExprtHdofVo> exprtHdofList = lctrDmndDao.selectExprtHdofList(exprtVo);
            exprt.setExprtHdofList(exprtHdofList);
        }

        if(exprt.getCareerRlsYn().equals("Y")) {
            List<ExprtCareerVo> exprtCareerList = lctrDmndDao.selectExprtCareerList(exprtVo);
            exprt.setExprtCareerList(exprtCareerList);
        }

        return exprt;
    }

    @Override
    public int insertLctrDmnd(LctrDmndVo lctrDmndVo) throws Exception {
        return lctrDmndDao.insertLctrDmnd(lctrDmndVo);
    }

    /**
     * 관심인력 등록
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : insertLtrstExprt
     * @Description : 관심인력 등록
     */
    public int insertItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.insertItrstExprt(exprtVo);
    }

    /**
     * 이미 등록된 관심인력인지 확인
     *
     * @param exprtVo
     * @return boolean
     * @throws Exception
     * @Title : checkAlreadyRegistedInrstExprt
     * @Description : 이미 등록된 관심인력인지 확인
     */
    @Override
    public boolean checkAlreadyRegistedItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.checkAlreadyRegistedItrstExprt(exprtVo) > 0 ;
    }

    /**
     * 관심인력 삭제
     *
     * @param exprtVo
     * @return int
     * @throws Exception
     * @Title : deleteItrstExprt
     * @Description : 관심인력 삭제
     */
    @Override
    public int deleteItrstExprt(ExprtVo exprtVo) throws Exception {
        return lctrDmndDao.deleteItrstExprt(exprtVo);
    }
}
