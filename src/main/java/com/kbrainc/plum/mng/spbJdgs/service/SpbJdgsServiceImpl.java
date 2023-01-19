package com.kbrainc.plum.mng.spbJdgs.service;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.mng.spbJdgs.model.SpbJdgsDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 체험환경교육 지원사업 > 심사위원 그룹 관리 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.spbJdgs.service
 * - SpbJdgsServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : SpbJdgsServiceImpl
 * @Description : 체험환경교육 지원사업 > 심사위원 그룹 관리 서비스 구현 클래스
 * @date : 2023. 01. 18.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class SpbJdgsServiceImpl extends PlumAbstractServiceImpl implements SpbJdgsService {

    @Autowired
    private SpbJdgsDao spbJdgsDao;

    /**
     * 심사위원 그룹 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectSpbJdgsList
     * @Description : 심사위원 그룹 조회
     */
    @Override
    public List<JdgGrpVo> selectSpbJdgsList(JdgGrpVo jdgGrpVo) throws Exception {
        return spbJdgsDao.selectSpbJdgsList(jdgGrpVo);
    }

    /**
     * 심사위원 그룹 상세 조회
     *
     * @param grpId
     * @return jdg grp vo
     * @throws Exception
     * @Title : selectSpbJdgsInfo
     * @Description : 심사위원 그룹 상세 조회
     */
    @Override
    public JdgGrpVo selectSpbJdgsInfo(Integer grpId) throws Exception {
        return spbJdgsDao.selectSpbJdgsInfo(grpId);
    }

    /**
     * 그룹에 매핑된 전문가 목록 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectExprtList
     * @Description : 그룹에 매핑된 전문가 목록 조회
     */
    @Override
    public List<JdgGrpExpertVo> selectExprtList(JdgGrpVo jdgGrpVo) throws Exception {
        return spbJdgsDao.selectExprtList(jdgGrpVo);
    }

    /**
     * 심사위원 그룹 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectSpbJdgsList
     * @Description : 심사위원 그룹 조회
     */
    @Override
    public boolean insertSpbJdgs(JdgGrpVo jdgGrpVo) throws Exception {
        return spbJdgsDao.insertSpbJdgs(jdgGrpVo);
    }

    /**
     * 심사위원 그룹 상세 조회
     *
     * @param grpId
     * @return jdg grp vo
     * @throws Exception
     * @Title : selectSpbJdgsInfo
     * @Description : 심사위원 그룹 상세 조회
     */
    @Override
    public boolean updateSpbJdgs(JdgGrpVo jdgGrpVo) throws Exception {
        return spbJdgsDao.updateSpbJdgs(jdgGrpVo);
    }

    /**
     * 전문가 추가
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : insertExprt
     * @Description : 전문가 추가
     */
    @Override
    public boolean insertExprt(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        List<JdgGrpExpertVo> existingExpertList = spbJdgsDao.selectExistingExprtList(jdgGrpExpertVo);

        Integer[] insertIds = Arrays.stream(jdgGrpExpertVo.getInsertIds())
                .filter(insertId -> existingExpertList.stream().noneMatch(expert -> insertId.equals(expert.getUserid())))
                .toArray(size -> new Integer[size]);

        if(insertIds.length == 0) return true;
        else {
            jdgGrpExpertVo.setInsertIds(insertIds);
            return spbJdgsDao.insertExprt(jdgGrpExpertVo);
        }
    }

    /**
     * 전문가 삭제
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : deleteExprt
     * @Description : 전문가 삭제
     */
    @Override
    public boolean deleteExprt(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        return spbJdgsDao.deleteExprt(jdgGrpExpertVo);
    }
}
