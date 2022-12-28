package com.kbrainc.plum.mng.ass.jdgGrpMng.service;

import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpExpertVo;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpMngDao;
import com.kbrainc.plum.mng.ass.jdgGrpMng.model.JdgGrpVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


/**
 * 심사위원 그룹 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.ass.jdgGrpMng.service
 * - JdgGrpMngServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : JdgGrpMngServiceImpl
 * @Description : 심사위원 그룹 서비스 구현 클래스
 * @date : 2022. 12. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class JdgGrpMngServiceImpl extends PlumAbstractServiceImpl implements JdgGrpMngService {

    @Autowired
    private JdgGrpMngDao jdgGrpMngDao;

    /**
     * 심사위원 그룹 목록 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpList
     * @Description : 심사위원 그룹 목록 조회
     */
    @Override
    public List<JdgGrpVo> selectJdgGrpList(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpList(jdgGrpVo);
    }

    /**
     * 심사위원 그룹 정보 조회
     *
     * @param jdgGrpVo
     * @return jdg grp vo
     * @throws Exception
     * @Title : selectJdgGrpInfo
     * @Description : 심사위원 그룹 정보 조회
     */
    @Override
    public JdgGrpVo selectJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpInfo(jdgGrpVo);
    }

    /**
     * 심사위원 그룹에 매핑된 전문가 조회
     *
     * @param jdgGrpVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpExpertList
     * @Description : 심사위원 그룹에 매핑된 전문가 조회
     */
    @Override
    public List<JdgGrpExpertVo> selectJdgGrpExpertList(JdgGrpVo jdgGrpVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpExpertList(jdgGrpVo);
    }

    /**
     * 전문가 모달 > 전문가 목록 조회
     *
     * @param jdgGrpExpertVo
     * @return list
     * @throws Exception
     * @Title : selectJdgGrpMngExpertSearchList
     * @Description : 전문가 모달 > 전문가 목록 조회
     */
    @Override
    public List<JdgGrpExpertVo> selectJdgGrpMngExpertSearchList(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        return jdgGrpMngDao.selectJdgGrpMngExpertSearchList(jdgGrpExpertVo);
    }

    /**
     * 심사위원 그룹 등록
     *
     * @param jdgGrpVo
     * @return int
     * @throws Exception
     * @Title : insertJdgGrpInfo
     * @Description : 심사위원 그룹 등록
     */
    @Override
    public int insertJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        int retval = 0;

        retval += jdgGrpMngDao.insertJdgGrpInfo(jdgGrpVo);

        return retval;
    }

    /**
     * 심사위원 그룹 수정
     *
     * @param jdgGrpVo
     * @return int
     * @throws Exception
     * @Title : updateJdgGrpInfo
     * @Description : 심사위원 그룹 수정
     */
    @Override
    public int updateJdgGrpInfo(JdgGrpVo jdgGrpVo) throws Exception {
        int retval = 0;

        retval += jdgGrpMngDao.updateJdgGrpInfo(jdgGrpVo);

        return retval;
    }

    /**
     * 전문가 추가
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : insertJdgGrpExpert
     * @Description : 전문가 추가
     */
    @Override
    @Transactional
    public boolean insertJdgGrpExpert(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        List<JdgGrpExpertVo> existingExpertList = jdgGrpMngDao.selectExistingExpertList(jdgGrpExpertVo);

        Integer[] insertIds = Arrays.stream(jdgGrpExpertVo.getInsertIds())
                .filter(insertId -> existingExpertList.stream().noneMatch(expert -> insertId.equals(expert.getUserid())))
                .toArray(size -> new Integer[size]);

        jdgGrpExpertVo.setInsertIds(insertIds);
        return jdgGrpMngDao.insertJdgGrpExpert(jdgGrpExpertVo);
    }

    /**
     * 전문가 삭제
     *
     * @param jdgGrpExpertVo
     * @return boolean
     * @throws Exception
     * @Title : deleteJdgGrpExpert
     * @Description : 전문가 삭제
     */
    @Override
    public boolean deleteJdgGrpExpert(JdgGrpExpertVo jdgGrpExpertVo) throws Exception {
        return jdgGrpMngDao.deleteJdgGrpExpert(jdgGrpExpertVo);
    }
}
