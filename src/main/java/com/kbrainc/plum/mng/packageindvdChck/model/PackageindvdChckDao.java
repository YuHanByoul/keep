package com.kbrainc.plum.mng.packageindvdChck.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 꾸러미개체 점검 Dao 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.packageIndvdChck.model
 * - PackageIndvdChckDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PackageIndvdChckDao
 * @Description : 꾸러미개체 점검 Dao 인터페이스
 * @date : 2023. 03. 27.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface PackageindvdChckDao {
    /**
     * 꾸러미개체 점검 목록 조회
     *
     * @param packageIndvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageIndvdChckList
     * @Description : 꾸러미개체 점검 목록 조회
     */
    public List<PackageindvdChckVo> selectPackageindvdChckList(PackageindvdChckVo packageIndvdChckVo) throws Exception;

    /**
     * 꾸러미개체 점검 상세 조회
     *
     * @param packageIndvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageIndvdChck
     * @Description : 꾸러미개체 점검 상세 조회
     */
    public PackageindvdChckVo selectPackageindvdChck(PackageindvdChckVo packageIndvdChckVo) throws Exception;

    /**
     * 꾸러미개체 교구 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdTchaidList
     * @Description : 꾸러미개체 교구 목록 조회
     */
    List<PackageindvdTchaidVo> selectPackageindvdTchaidList(PackageindvdChckVo packageindvdChckVo) throws Exception;

    /**
     * 꾸러미개체 점검 답변 목록 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckAnsList
     * @Description : 꾸러미개체 점검 답변 목록 조회
     */
    List<PackageindvdChckAnsVo> selectPackageindvdChckAnsList(PackageindvdChckVo packageindvdChckVo) throws Exception;

    /**
     * 꾸러미개체 체크리스트 조회
     *
     * @param packageindvdChckVo
     * @return list
     * @throws Exception
     * @Title : selectPackageindvdChckArtclList
     * @Description : 꾸러미개체 체크리스트 조회
     */
    List<PackageindvdChckArtclVo> selectPackageindvdChckArtclList(PackageindvdChckVo packageindvdChckVo) throws Exception;
}
