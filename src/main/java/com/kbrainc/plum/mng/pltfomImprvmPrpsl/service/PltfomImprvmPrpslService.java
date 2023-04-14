package com.kbrainc.plum.mng.pltfomImprvmPrpsl.service;


import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslAnsVo;
import com.kbrainc.plum.mng.pltfomImprvmPrpsl.model.PltfomImprvmPrpslVo;

import java.util.List;

/**
 * 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.pltfomImprvmPrpsl.service
 * - PltfomImprvmPrpslService.java
 * </pre>
 *
 * @author : KBRAINC_DEV
 * @ClassName : PltfomImprvmPrpslService
 * @Description : 알림/문의 > 고객센터 > 플랫폼개선제안 서비스 인터페이스
 * @date : 2023. 04. 13.
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
public interface PltfomImprvmPrpslService {

    /**
     * 플랫폼개선제안 목록 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return list
     * @throws Exception
     * @Title : selectPltfomImprvmPrpslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    public List<PltfomImprvmPrpslVo> selectPltfomImprvmPrpslList(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception;


    /**
     * 플랫폼개선제안 문의 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return pltfom imprvm prpsl vo
     * @throws Exception
     * @Title : selectPltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 문의 조회
     */
    public PltfomImprvmPrpslVo selectPltfomImprvmPrpsl(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 조회
     *
     * @param pltfomImprvmPrpslVo
     * @return pltfom imprvm prpsl ans vo
     * @throws Exception
     * @Title : selectPltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 조회
     */
    public PltfomImprvmPrpslAnsVo selectPltfomImprvmPrpslAns(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 등록
     *
     * @param pltfomImprvmPropslAnsVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 등록
     */
    public int insertPltfomImprvmPrpslAns(PltfomImprvmPrpslAnsVo pltfomImprvmPropslAnsVo) throws Exception;

    /**
     * 플랫폼개선제안 답변 수정
     *
     * @param pltfomImprvmPrpslAnsVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPrpslAns
     * @Description : 플랫폼개선제안 답변 수정
     */
    public int updatePltfomImprvmPrpslAns(PltfomImprvmPrpslAnsVo pltfomImprvmPrpslAnsVo) throws Exception;

    /**
     * 플랫폼개선제안 삭제
     *
     * @param pltfomImprvmPrpslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPrpsl
     * @Description : 플랫폼개선제안 삭제
     */
    public int deletePltfomImprvmPrpsl(PltfomImprvmPrpslVo pltfomImprvmPrpslVo) throws Exception;
}
