package com.kbrainc.plum.mng.pltfomImprvmPropsl.service;


import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPropsl.model.PltfomImprvmPropslVo;

import java.util.List;

/**
 * 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.pltfomImprvmPropsl.service
 * - PltfomImprvmPropslService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPropslService
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 인터페이스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface PltfomImprvmPropslService {

    /**
     * 플랫폼개선제안 목록 조회
     *
     * @param pltfomImprvmPropslVo
     * @return list
     * @throws Exception
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;


    /**
     * 플랫폼개선제안 문의 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl vo
     * @throws Exception
     * @Title : selectPltfomImprvmPropsl
     * @Description : 플랫폼개선제안 문의 조회
     */
    public PltfomImprvmPropslVo selectPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl ans vo
     * @throws Exception
     * @Title : selectPltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 조회
     */
    public PltfomImprvmPropslAnsVo selectPltfomImprvmPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPropslAnsVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    public int insertPltfomImprvmPropslAns(PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 수정
     *
     * @param pltfomImprvmPropslAnsVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPropslAns
     * @Description : 플랫폼개선제안 답변 수정
     */
    public int updatePltfomImprvmPropslAns(PltfomImprvmPropslAnsVo pltfomImprvmPropslAnsVo) throws Exception;

    /**
     * 플랫폼개선제안 삭제
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 삭제
     */
    public int deletePltfomImprvmPrpsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;
}
