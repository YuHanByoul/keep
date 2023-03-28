package com.kbrainc.plum.mng.packageindvdChck.service;

import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckAnsVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckArtclVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdChckVo;
import com.kbrainc.plum.mng.packageindvdChck.model.PackageindvdTchaidVo;

import java.util.List;

/**
 * 꾸러미개체 점검 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageindvdChck.service
 * - PackageindvdChckService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageindvdChckService
 * @Description : 꾸러미개체 점검 서비스 인터페이스
 * @date : 2023. 03. 24.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface PackageindvdChckService {
    /**
     * 꾸러미개체 점검 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckList
     * @Description : 꾸러미개체 점검 목록 조회
     */
    public List<PackageindvdChckVo> selectPackageindvdChckList(PackageindvdChckVo packageindvdChckVo) throws Exception;

    /**
     * 꾸러미개체 점검 상세 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChck
     * @Description : 꾸러미개체 점검 상세 조회
     */
    public PackageindvdChckVo selectPackageindvdChck(PackageindvdChckVo packageindvdChckVo) throws Exception;

    public List<PackageindvdTchaidVo> selectPackageindvdTchaidList(PackageindvdChckVo packageindvdChckVo) throws Exception;

    public List<PackageindvdChckAnsVo> selectPackageindvdChckAnsList(PackageindvdChckVo packageindvdChckVo) throws Exception;

    public List<PackageindvdChckArtclVo> selectPackageindvdChckArtclList(PackageindvdChckVo packageindvdChckVo) throws Exception;

}
