package com.kbrainc.plum.mng.packageindvdChck.service;

import com.kbrainc.plum.mng.packageindvdChck.model.*;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 꾸러미개체 점검 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.service
 * - PackageindvdChckServiceImpl.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckServiceImpl
 * @Description : 꾸러미개체 점검 서비스 구현 클래스
 * @date : 2023. 03. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class PackageindvdChckServiceImpl extends PlumAbstractServiceImpl implements PackageindvdChckService {

    @Autowired
    private PackageindvdChckDao packageindvdChckDao;

    /**
     * 꾸러미개체 점검 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckList
     * @Description : 꾸러미개체 점검 목록 조회
     */
    @Override
    public List<PackageindvdChckVo> selectPackageindvdChckList(PackageindvdChckVo packageindvdChckVo) throws Exception {
        return packageindvdChckDao.selectPackageindvdChckList(packageindvdChckVo);
    }

    /**
     * 꾸러미개체 점검 상세 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChck
     * @Description : 꾸러미개체 점검 상세 조회
     */
    @Override
    public PackageindvdChckVo selectPackageindvdChck(PackageindvdChckVo packageindvdChckVo) throws Exception {
        return packageindvdChckDao.selectPackageindvdChck(packageindvdChckVo);
    }

    /**
     * 꾸러미개체 교구 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdTchaidList
     * @Description : 꾸러미개체 교구 목록 조회
     */
    @Override
    public List<PackageindvdTchaidVo> selectPackageindvdTchaidList(PackageindvdChckVo packageindvdChckVo) throws Exception {
        return packageindvdChckDao.selectPackageindvdTchaidList(packageindvdChckVo);
    }

    /**
     * 꾸러미개체 점검 답변 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckAnsList
     * @Description : 꾸러미개체 점검 답변 목록 조회
     */
    @Override
    public List<PackageindvdChckAnsVo> selectPackageindvdChckAnsList(PackageindvdChckVo packageindvdChckVo) throws Exception {
        return packageindvdChckDao.selectPackageindvdChckAnsList(packageindvdChckVo);
    }

    /**
     * 꾸러미개체 체크리스트 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckArtclList
     * @Description : 꾸러미개체 체크리스트 조회
     */
    @Override
    public List<PackageindvdChckArtclVo> selectPackageindvdChckArtclList(PackageindvdChckVo packageindvdChckVo) throws Exception {
        return packageindvdChckDao.selectPackageindvdChckArtclList(packageindvdChckVo);
    }
}
