/**
 * 
 */
package com.kbrainc.plum.mng.eduInst.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 사회환경교육기관 지정 > 신청/결과관리 DAO 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.eduInst.model
* - EduInstDao.java
* </pre> 
*
* @ClassName : EduInstDao
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface EduInstDao {

    /**
    * 신청관리 리스트 조회. 
    *
    * @Title : selectInstDsgnList
    * @Description : TODO
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EduInstVo>
     */
    List<EduInstVo> selectInstDsgnList(EduInstVo eduInstVo) throws Exception;

    /**
     * 신청관리 상세 조회. 
     *
     * @Title : selectInstDsgnInfo
     * @Description : TODO
     * @param eduInstVo
     * @return
     * @throws Exception
     * @return EduInstVo
     */
    EduInstVo selectInstDsgnInfo(EduInstVo eduInstVo) throws Exception;

    /**
    * 지정내역 리스트 조회. 
    *
    * @Title : selectDsgnDsctn
    * @Description : TODO
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EduInstVo>
     */
    List<EduInstVo> selectDsgnDsctn(EduInstVo eduInstVo) throws Exception;

    /**
    * 상태코드 변경
    *
    * @Title : updateSttsCd
    * @Description : 상태코드 변경
    * @param eduInstVo
    * @throws Exception
    * @return Map<String,Object>
    */
    int updateSttsCd(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 지정번호 중복 조회
    *
    * @Title : selectDsgnNoDupChk
    * @Description : 지정번호 중복 조회
    * @param eduInstVo
    * @return int
    * @throws Exception
    */
    public int selectDsgnNoDupChk(EduInstVo eduInstVo) throws Exception;
    
    /**
     * 지정내역 등록
     *
     * @Title : insertDsgnDsctnSttsCd
     * @Description : 지정내역 등록
     * @param eduInstVo
     * @throws Exception
     * @return Map<String,Object>
     */
    int insertDsgnDsctnSttsCd(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 지정번호 조회
    *
    * @Title : selectDsgnNo
    * @Description : 지정번호 조회
    * @param eduInstVo
    * @return eduInstVo
    * @throws Exception
    */
    public EduInstVo selectDsgnNo(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 지정내역 상세 조회
    *
    * @Title : selectDsgnDsctnInfo
    * @Description : 지정내역 상세 조회
    * @param eduInstVo
    * @return EduInstVo
    * @throws Exception;
    */
    public EduInstVo selectDsgnDsctnInfo(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 지정내역 저장
    *
    * @Title : insertDsgnDsctn
    * @Description : 지정내역 저장
    * @param eduInstVo
    * @return int
    * @throws Exception
    */
    public int insertDsgnDsctn(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 지정내역 변경
    *
    * @Title : updateDsgnDsctn
    * @Description : 지정내역 변경
    * @param eduInstVo
    * @return int
    * @throws Exception
    */
    public int updateDsgnDsctn(EduInstVo eduInstVo) throws Exception;    

    /**
    * 신청정보 수정. 
    *
    * @Title : updateInstDsgnInfo
    * @Description : TODO
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateInstDsgnInfo(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 운영계획 조회
    *
    * @Title : selectOperPlan
    * @Description : 운영계획 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return EduInstVo
    */
    public EduInstVo selectOperPlan(EduInstVo eduInstVo) throws Exception;

    /**
    * 운영계획 등록
    *
    * @Title : insertOperPlan
    * @Description : 운영계획 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertOperPlan(EduInstVo eduInstVo) throws Exception;

    /**
    * 운영계획 수정
    *
    * @Title : updateOperPlan
    * @Description : 운영계획 수정
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int updateOperPlan(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 추진일정 목록 조회
    *
    * @Title : selectSchdlList
    * @Description : 추진일정 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SchdlVo>
    */
    public List<SchdlVo> selectSchdlList(EduInstVo eduInstVo) throws Exception;

    /**
    * 추진일정 등록
    *
    * @Title : insertPropSchdl
    * @Description : 추진일정 등록
    * @param vo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertPropSchdl(SchdlVo schdlVo) throws Exception;
    
    /**
    * 추진일정 목록 삭제
    *
    * @Title : deletePropSchdl
    * @Description : 추진일정 목록 삭제
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deletePropSchdl(EduInstVo eduInstVo) throws Exception;

    /**
    * 교육전문인력 목록 조회
    *
    * @Title : selectEduExprtList
    * @Description : 교육전문인력 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EduExprtVo>
    */
    public List<EduExprtVo> selectEduExprtList(EduInstVo eduInstVo) throws Exception;

    /**
    * 교육전문인력 등록
    *
    * @Title : insertEduExprt
    * @Description : 교육전문인력 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertEduExprt(EduExprtVo eduInstVo) throws Exception;

    /**
    * 교육전문인력 목록 삭제
    *
    * @Title : deleteEduExprtList
    * @Description : 교육전문인력 목록 삭제
    * @param eduInstVo
    * @throws Exception
    * @return void
    */
    public void deleteEduExprt(EduInstVo eduInstVo) throws Exception;
    
    /**
    * 교육프로그램 목록 조회
    *
    * @Title : selectSeePrgrmList
    * @Description : 교육프로그램 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SeePrgrmVo>
    */
    public List<SeePrgrmVo> selectSeePrgrmList(EduInstVo eduInstVo) throws Exception;

    /**
    * 교육프로그램 등록
    *
    * @Title : insertSeePrgrm
    * @Description : 교육프로그램 등록
    * @param eduInstVo
    * @throws Exception
    * @return void
    */
    public int insertSeePrgrm(SeePrgrmVo seePrgrmVo) throws Exception;

    /**
    * 교육프로그램 삭제
    *
    * @Title : deleteSeePrgrm
    * @Description : 교육프로그램 삭제
    * @param eduInstVo
    * @throws Exception
    * @return void
    */
    public void deleteSeePrgrm(EduInstVo eduInstVo) throws Exception;

    /**
    * 지정 프로그램 목록 조회
    *
    * @Title : selectDsgnPrgrmList
    * @Description : 지정 프로그램 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SeePrgrmVo>
    */
    public List<SeePrgrmVo> selectDsgnPrgrmList(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 개요 조회
    *
    * @Title : selectSeeFclt
    * @Description : 시설 개요 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return EduInstVo
    */
    public EduInstVo selectSeeFclt(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 강의실 목록 조회
    *
    * @Title : selectLctrumList
    * @Description : 시설 강의실 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<LctrumVo>
    */
    public List<LctrumVo> selectLctrumList(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 강의실 등록
    *
    * @Title : insertFcltLctrum
    * @Description : 시설 강의실 등록
    * @param vo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertFcltLctrum(LctrumVo vo) throws Exception;

    /**
    * 시설 강의시실 삭제
    *
    * @Title : deleteFcltLctrum
    * @Description : 시설 강의시실 삭제
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteFcltLctrum(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 설비 목록 조회
    *
    * @Title : selectFcltEqpList
    * @Description : 시설 설비 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EqpVo>
    */
    public List<EqpVo> selectFcltEqpList(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 설비 등록
    *
    * @Title : insertFcltEqp
    * @Description : 시설 설비 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertFcltEqp(EqpVo eduInstVo) throws Exception;

    /**
    * 시설 설비 삭제
    *
    * @Title : deleteEqp
    * @Description : 시설 설비 삭제
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int deleteFcltEqp(EduInstVo eduInstVo) throws Exception;

    /**
    * 시설 등록
    *
    * @Title : 시설 등록
    * @Description : 시설 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertSeeFclt(EduInstVo eduInstVo) throws Exception;

    /**
     * 시설 수정
     *
     * @Title : updateSeeFclt
     * @Description : 시설 수정
     * @param eduInstVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateSeeFclt(EduInstVo eduInstVo) throws Exception;    
    
    /**
     * 담당자 목록 조회. 
     *
     * @Title : selectUserList
     * @Description : TODO
     * @param reqUserVo
     * @return
     * @throws Exception
     * @return List<ReqUserVo>
      */
     List<ReqUserVo> selectUserList(ReqUserVo reqUserVo) throws Exception;
     
    
    /**
    * 보완요청 목록 조회. 
    *
    * @Title : selectSplmntList
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return List<SupplementVo>
     */
    List<SupplementVo> selectSplmntList(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청. 
    *
    * @Title : insertSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int insertSplmnt(SupplementVo supplementVo) throws Exception;
    
    /**
    * 보완요청 수정. 
    *
    * @Title : updateSplmnt
    * @Description : TODO
    * @param supplementVo
    * @return
    * @throws Exception
    * @return int
     */
    int updateSplmnt(SupplementVo supplementVo) throws Exception;
}
