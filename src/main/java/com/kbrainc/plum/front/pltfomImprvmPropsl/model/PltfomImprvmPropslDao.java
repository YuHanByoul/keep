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
    /**
     * 플랫폼 개선 제안 생성
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : insertPltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 생성
     */
    public int insertPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 수정
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : updatePltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 수정
     */
    public int updatePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 삭제
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : deletePltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 삭제
     */
    public int deletePltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 취소
     *
     * @param pltfomImprvmPropslVo
     * @return int
     * @throws Exception
     * @Title : cancelPltfomImprvmPropsl
     * @Description : 플랫폼 개선 제안 취소
     */
    public int cancelPltfomImprvmPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 목록 조회
     *
     * @param pltfomImprvmPropslVo
     * @return list
     * @throws Exception
     * @Title : selectPltfomImprvmPropslList
     * @Description : 플랫폼 개선 제안 목록 조회
     */
    public List<PltfomImprvmPropslVo> selectPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 마이페이지 > 플랫폼 개선 제안 목록 조회
     *
     * @param pltfomImprvmPropslVo
     * @return list
     * @throws Exception
     * @Title : selectMyPltfomImprvmPropslList
     * @Description : 마이페이지 > 플랫폼 개선 제안 목록 조회
     */
    public List<PltfomImprvmPropslVo> selectMyPltfomImprvmPropslList(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 상세 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl vo
     * @throws Exception
     * @Title : selectPropsl
     * @Description : 플랫폼 개선 제안 상세 조회
     */
    public PltfomImprvmPropslVo selectPropsl(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;

    /**
     * 플랫폼 개선 제안 답변 조회
     *
     * @param pltfomImprvmPropslVo
     * @return pltfom imprvm propsl ans vo
     * @throws Exception
     * @Title : selectPropslAns
     * @Description : 플랫폼 개선 제안 답변 조회
     */
    public PltfomImprvmPropslAnsVo selectPropslAns(PltfomImprvmPropslVo pltfomImprvmPropslVo) throws Exception;


}
