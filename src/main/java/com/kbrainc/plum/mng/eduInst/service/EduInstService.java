/**
 * 
 */
package com.kbrainc.plum.mng.eduInst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.kbrainc.plum.mng.eduInst.model.EduExprtVo;
import com.kbrainc.plum.mng.eduInst.model.EduInstVo;
import com.kbrainc.plum.mng.eduInst.model.EqpVo;
import com.kbrainc.plum.mng.eduInst.model.LctrumVo;
import com.kbrainc.plum.mng.eduInst.model.ReqUserVo;
import com.kbrainc.plum.mng.eduInst.model.SchdlVo;
import com.kbrainc.plum.mng.eduInst.model.SeePrgrmVo;
import com.kbrainc.plum.mng.eduInst.model.SupplementVo;

/**
* 사회환경교육기관 지정 > 신청/결과관리 서비스 인터페이스. 
*
* <pre>
* com.kbrainc.plum.mng.eduInst.service
* - EduInstService.java
* </pre> 
*
* @ClassName : EduInstService
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface EduInstService {
    
    /**
    * 신청/결과관리 리스트 조회. 
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
     * 신청/결과관리 상세 조회. 
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
    * 신청/결과관리 목록 엑셀 다운로드. 
    *
    * @Title : instDsgnListExcelDownload
    * @Description : TODO
    * @param eduInstVo
    * @param response
    * @param request
    * @throws Exception
    * @return void
     */
    void instDsgnListExcelDownload(EduInstVo eduInstVo, HttpServletResponse response, HttpServletRequest request) throws Exception;
    
    /**
    * 상태코드 변경
    *
    * @Title : updateSttsCd
    * @Description : 상태코드 변경
    * @param eduInstVo
    * @throws Exception
    * @return Map<String,Object>
    */
    public int updateSttsCd(EduInstVo eduInstVo) throws Exception;

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
    public int updateInstDsgnInfo(EduInstVo eduInstVo) throws Exception;

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
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertPropSchdl(EduInstVo eduInstVo) throws Exception;
    
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
    public int insertEduExprt(@Valid EduInstVo eduInstVo) throws Exception;
    
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
    * 교육프로그램보유현황 등록
    *
    * @Title : insertHldngStts
    * @Description : 교육프로그램보유현황 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertHldngStts(EduInstVo eduInstVo) throws Exception;

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
    * 교육시설 및 설비 현황 등록
    *
    * @Title : insertFcltStts
    * @Description : 교육시설 및 설비 현황 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    public int insertFcltStts(EduInstVo eduInstVo) throws Exception;

    /**
     * 교육시설 및 설비 현황 수정
     *
     * @Title : updateFcltStts
     * @Description : 교육시설 및 설비 현황 수정
     * @param eduInstVo
     * @return
     * @throws Exception
     * @return int
     */
    public int updateFcltStts(EduInstVo eduInstVo) throws Exception;
    
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
