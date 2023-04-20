package com.kbrainc.plum.mng.mobileAsgsysSrng.model;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ChklstAnsVo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

/**
* 시설 Dao 클래스
*
* <pre>
* com.kbrainc.plum.mng.mobileAsgsysSrng.model
* - MobileAsgsysSrngDao.java
* </pre>
*
* @ClassName : MobileAsgsysSrngDao
* @Description : 언론보도관리 Dao 클래스
* @author : NTK
* @date : 2023. 01. 09.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface MobileAsgsysSrngDao {
    
    /**
    * 시설 목록 조회
    *
    * @Title : selectAsgsysSrngList
    * @Description : 시설 목록 조회
    * @param mobileAsgsysSrngVo 시설 객체
    * @throws Exception 예외
    * @return List<MobileAsgsysSrngVo>
    */
    public List<MobileAsgsysSrngVo> selectAsgsysSrngList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 지원단심사 상세 조회
     *
     * @Title : selectAsgsysSrngInfo
     * @Description : 지원단심사 상세 조회
     * @param mobileAsgsysSrngVo
     * @return MobileAsgsysSrngVo
     * @throws Exception
     */
    public MobileAsgsysSrngVo selectAsgsysSrngInfo(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 지원단심사 체크리스트 조회
     *
     * @Title : selectSprtgrpSrngList
     * @Description : 지원단심사 체크리스트 조회
     * @param mobileAsgsysSrngVo
     * @return List<MobileAsgsysSrngVo>
     * @throws Exception
     */
    public List<MobileAsgsysSrngVo> selectCheckList(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 지원단심사 심사의견 수정
     *
     * @Title : updateSprtgrpOpnn
     * @Description : 지원단심사 심사의견 수정
     * @param mobileAsgsysSrngVo
     * @return int
     * @throws Exception
     */
    public int updateSprtgrpOpnn(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 체크리스트 제출 수정
     *
     * @Title : updateChklstSbmsn
     * @Description : 체크리스트 제출 수정
     * @param mobileAsgsysSrngVo
     * @return int
     * @throws Exception
     */
    public int updateChklstSbmsn(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 체크리스트제출 등록
     *
     * @Title : insertChklstSbmsn
     * @Description : 체크리스트제출 등록
     * @param mobileAsgsysSrngVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertChklstSbmsn(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 체크리스트 제출 조회
     *
     * @Title : selectChkListSbmsn
     * @Description : 체크리스트 제출 조회
     * @param mobileAsgsysSrngVo
     * @return
     * @throws Exception
     * @return MobileAsgsysSrngVo
     */
    public MobileAsgsysSrngVo selectChkListSbmsn(MobileAsgsysSrngVo mobileAsgsysSrngVo) throws Exception;

    /**
     * 체크리스트답변순서목록 삭제
     *
     * @Title : deleteChklstSeOrdrAnsList
     * @Description : TODO
     * @param dsgnSrngFormVo
     * @return
     * @throws Exception
     * @return int
     */
    public int deleteChklstSeOrdrAnsList (ChklstAnsVo chklstAnsVo) throws Exception;

    /**
     * 체크리스트 key count 조회
     *
     * @Title : selectKeyCntChklstAns
     * @Description : 체크리스트 key count 조회
     * @param vo
     * @return int
     * @throws Exception
     */
    public int selectKeyCntChklstAns(ChklstAnsVo chklstAnsVo) throws Exception;

    /**
     * 체크리스트 답변 수정
     *
     * @Title : updateChklstAns
     * @Description : 체크리스트 답변 수정
     * @param chklstAnsVo
     * @return int
     * @throws Exception
     */
    public int updateChklstAns(ChklstAnsVo chklstAnsVo) throws Exception;

    /**
     * 체크리스트 답변 등록
     *
     * @Title : insertChklstAns
     * @Description : 체크리스트 답변 등록
     * @param chklstAnsVo
     * @return int
     * @throws Exception
     */
    public int insertChklstAns(ChklstAnsVo chklstAnsVo) throws Exception;

    /**
     * 체크리스트답변순서목록 등록
     *
     * @Title : insertChklstSeOrdrAnsList
     * @Description : 체크리스트답변순서 등록
     * @param dsgnSrngFormVo
     * @return
     * @throws Exception
     * @return int
     */
    public int insertChklstSeOrdrAnsList (ChklstAnsVo chklstAnsVo) throws Exception;
}
