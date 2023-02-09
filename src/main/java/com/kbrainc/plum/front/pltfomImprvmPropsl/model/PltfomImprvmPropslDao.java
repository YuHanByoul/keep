package com.kbrainc.plum.front.pltfomImprvmPropsl.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
 * 플랫폼 개선 제안 Dao 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.model
 * - PltfomImprvmPropslDao.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslDao
 * @Description : 플랫폼 개선 제안 Dao 클래스
 * @date : 2023. 02. 08.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Mapper("front.pltfomImprvmPropslDao")
public interface PltfomImprvmPropslDao {
    public int insertPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int updatePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int deletePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int cancelPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public PltfomImprvmPropslVo selectPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public PltfomImprvmPropslAnsVo selectPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;


}
