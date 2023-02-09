package com.kbrainc.plum.front.pltfomImprvmPropsl.serivce;

import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.front.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;

import java.util.List;
import java.util.Map;

/**
 * 플랫폼 개선 제안 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.pltfomImprvmPropsl.serivce
 * - PltfomImprvmPropslService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslService
 * @Description : 플랫폼 개선 제안 서비스 인터페이스
 * @date : 2023. 02. 07.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface PltfomImprvmPropslService {
    public int insertPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int updatePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int deletePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public int cancelPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public PltfomImprvmPropslVo selectPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    public PltfomImprvmPropslAnsVo selectPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;
}
