package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.member.model.MemberVo;

/**
* 지정제심사관리 DAO클래스.
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - AsgsysSrngDao.java
* </pre>
*
* @ClassName : AsgsysSrngDao
* @Description : 지정제심사관리 DAO클래스.
* @author : kbrain
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
/**
* [클래스 요약].
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - AsgsysSrngDao.java
* </pre>
*
* @ClassName : AsgsysSrngDao
* @Description : TODO
* @author : kbrain
* @date : 2022. 12. 21.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface AsgsysSrngDao {

	/**
	* 지정신청목록 조회.
	*
	* @Title : selectDsgnAplyList
	* @Description : 지정신청목록 조회.
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception 예외
	*/
	public List<AsgsysSrngVo> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception;

    /**
    * 지정신청상세 조회.
    *
    * @Title : selectDsgnAplyDtlInfo
    * @Description : 지정신청상세 조회.
    * @param asgsysSrngVo
    * @return AsgsysSrngVo
    * @throws Exception 예외
    */
	public AsgsysSrngVo selectDsgnAplyDtlInfo(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
    * 신청정보 조회
    *
    * @Title : selectAplyInfo
    * @Description : 신청정보 조회
    * @param asgsysSrngVo
    * @return AsgsysSrngVo
    * @throws Exception 예외
    */
	public AsgsysSrngVo selectAplyInfo(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램상태코드 조회
	*
	* @Title : selectPrgrmSttsCd
	* @Description : 프로그램상태코드 조회
	* @param asgsysSrngVo
	* @throws Exception 예외
	* @return Object
	*/
	public String selectPrgrmSttsCd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램상태코드 변경
	*
	* @Title : updateSttsCd
	* @Description : 프로그램상태코드 변경
	* @param asgsysSrngVo
	* @throws Exception
	* @return Map<String,Object>
	*/
	public int updatePrgrSttsCd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지원단 캘린더 목록 조회
	*
	* @Title : selectSprtgrpClndrList
	* @Description : 지원단 캘린더 목록 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSprtgrpClndrList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 지정신청목록 엑셀다운로드
	 *
	 * @Title : selectMemberExcelList
	 * @Description : 지정신청목록 엑셀다운로드
	 * @param asgsysSrngVo
	 * @throws Exception
	 * @return List<AsgsysSrngVo> 사용자정보 목록
	 */
	public List<AsgsysSrngVo> aplyExcelDownList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 보완요청 목록조회
	*
	* @Title : selectSplmntDmndList
	* @Description : 보완요청 목록조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectSplmntDmndList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 보완요청 등록
	*
	* @Title : insertSplmntDmnd
	* @Description : 보완요청 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 보완요청 수정
	*
	* @Title : updateSplmntDmnd
	* @Description : 보완요청 수정
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 보완요청 삭제
	 *
	 * @Title : deleteSplmntDmnd
	 * @Description : 보완요청 삭제
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int deleteSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 보완요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보완요청 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo
	* @throws Exception
	*/
	public AsgsysSrngVo selectSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램 우수성 조회
	*
	* @Title : selectPrgrmDstnctn
	* @Description : 프로그램 우수성 조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	public AsgsysSrngVo selectPrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception;


	/**
	* 프로그램 운영일정 목록조회
	*
	* @Title : selectPrgrmDstnctn
	* @Description : 프로그램 운영일정 목록조회
	* @param prgrmSchdlVo
	* @return List<PrgrmSchdlVo>
	* @throws Exception
	*/
	public List<PrgrmSchdlVo> selectPrgrmSchdlList(PrgrmSchdlVo prgrmSchdlVo) throws Exception;

	/**
	* 프로그램 운영일정 수정
	*
	* @Title : updatePrgrmSchdl
	* @Description : 프로그램 운영일정 수정
	* @param prgrmSchdlVo
	* @return int
	* @throws Exception
	*/
	public int updatePrgrmSchdl(PrgrmSchdlVo prgrmSchdlVo) throws Exception;

	/**
	* 프로그램 운영일정 등록
	*
	* @Title : insertPrgrmSchdl
	* @Description : 프로그램 운영일정 등록
	* @param prgrmSchdlVo
	* @return int
	* @throws Exception
	*/
	public int insertPrgrmSchdl(PrgrmSchdlVo prgrmSchdlVo) throws Exception;

	/**
	* 프로그램 운영일정 삭제
	*
	* @Title : deletePrgrmSchdl
	* @Description : 프로그램 운영일정 삭제
	* @param prgrmSchdlVo
	* @return int
	* @throws Exception
	*/
	public int deletePrgrmSchdl(PrgrmSchdlVo prgrmSchdlVo) throws Exception;

	/**
	* 비상조치계획 목록조회
	*
	* @Title : selectEmrgcyActnPlanList
	* @Description : 비상조치계획 삭제
	* @param emrgcyActnPlanVo
	* @return int
	* @throws Exception
	*/
	public List<EmrgcyActnPlanVo> selectEmrgcyActnPlanList(EmrgcyActnPlanVo emrgcyActnPlanVo) throws Exception;

	/**
	 * 비상조치계획 등록
	 *
	 * @Title : insertEmrgcyActnPlan
	 * @Description : 비상조치계획 삭제
	 * @param emrgcyActnPlanVo
	 * @return int
	 * @throws Exception
	 */
	public int insertEmrgcyActnPlan(EmrgcyActnPlanVo emrgcyActnPlanVo) throws Exception;

	/**
	 * 비상조치계획 삭제
	 *
	 * @Title : deleteEmrgcyActnPlan
	 * @Description : 비상조치계획 삭제
	 * @param emrgcyActnPlanVo
	 * @return int
	 * @throws Exception
	 */
	public int deleteEmrgcyActnPlan(EmrgcyActnPlanVo emrgcyActnPlanVo) throws Exception;

    /**
	* 프로그램 평가 조회
	*
	* @Title : selectPrgrmEduSbjct
	* @Description : 프로그램 평가 조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	public AsgsysSrngVo selectPrgrmEvl(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램 안전관리 조회
	*
	* @Title : selectSftyMng
	* @Description : 프로그램 안전관리 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo객체
    * @throws Exception 예외
	*/
	public AsgsysSrngVo selectSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

    /**
    * @Title : dsgnSrngMainForm
    * @Description : 심사위원심사 목록조회
    * @param AsgsysSrngVo객체
    * @throws Exception 예외
    * @return List<AsgsysSrngVo>
    */
	public List<AsgsysSrngVo> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 상세 조회
	*
	* @Title : getSelectDsgnAplyInfo
	* @Description : 심사위원심사 상세 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo객체
    * @throws Exception 예외
	*/
	public AsgsysSrngVo selectJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

    /**
    * 심사위원심사 수정
    *
    * @Title       : updateJdgsSrngDetail
    * @Description : 심사위원심사 수정
	* @param asgsysSrngVo
	* @return int
    * @throws Exception 예외
    */
	public int updateJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 심사위원심사 등록
	 *
	 * @Title : insertJdgsSrngDetail
	 * @Description : 심사위원심사 등록
	 * @param asgsysSrngVo
	 * @return int
     * @throws Exception 예외
	 */
	public int insertJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원 심사 답변 등록
	*
	* @Title : insertJdgsSrngAns
	* @Description : 심사위원 심사 답변 등록
	* @param dsgnSrngFormVo
	* @return int
	* @throws Exception
	*/
	public int insertJdgsSrngAns(DsgnSrngFormVo dsgnSrngFormVo) throws Exception;

	/**
	* 심사위원 심사 답변 수정
	*
	* @Title : updateJdgsSrngAns
	* @Description : 심사위원 심사 답변 수정
	* @param dsgnSrngFormVo
	* @return int
	* @throws Exception
	*/
	public int updateJdgsSrngAns(DsgnSrngFormVo dsgnSrngFormVo) throws Exception;

	/**
	 * 심사위원 심사 답변 삭제
	 *
	 * @Title : deleteJdgsSrngAns
	 * @Description : 심사위원 심사 답변 삭제
	 * @param dsgnSrngFormVo
	 * @return int
	 * @throws Exception
	 */
	public int deleteJdgsSrngAns(DsgnSrngFormVo dsgnSrngFormVo) throws Exception;

	/**
	* 안전관리 수정
	*
	* @Title : updateSftyMng
	* @Description : 안전관리 수정
	* @param asgsysSrngVo
	* @return int
    * @throws Exception 예외
	*/
	public int updateSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 목록 엑셀 다운
	*
	* @Title : jdgsSrngListExcelDown
	* @Description : 심사위원심사 목록 엑셀 다운
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> jdgsSrngListExcelDown(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사양식 목록 조회
	*
	* @Title : selectDsgnSrgnFormList
	* @Description : 심사양식 목록 조회
	* @param DsgnSrngFormVo
	* @return
	* @throws Exception
	* @return List<DsgnSrngFormVO>
	*/
	public List<DsgnSrngFormVo> selectDsgnSrgnFormList(DsgnSrngFormVo dsgnSrngFormVo) throws Exception;

	/**
	* 프로그램 운영관리 조회
	*
	* @Title : selectPrgrmOperMng
	* @Description : 프로그램 운영관리 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo
	* @throws Exception
	*/
	public AsgsysSrngVo selectPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램 운영관리 등록
	*
	* @Title : insertPrgrmOperMng
	* @Description : 프로그램 운영관리 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지출항목 목록 조회
	*
	* @Title : selectExpndArtclList
	* @Description : 지출항목 목록 조회
	* @param ExpndArtclVo
	* @return
	* @throws Exception
	* @return List<ExpndArtclVo>
	*/
	public List<ExpndArtclVo> selectExpndArtclList(ExpndArtclVo expndArtclVo) throws Exception;

	/**
	* 지출항목 등록
	*
	* @Title : insertExpndArtcl
	* @Description : 지출항목 등록
	* @param expndArtclVo
	* @return int
	* @throws Exception
	*/
	public int insertExpndArtcl(ExpndArtclVo expndArtclVo) throws Exception;

	/**
	 * 지출항목 목록 삭제
	 *
	 * @Title : deleteExpndArtcl
	 * @Description : 지출항목 목록 삭제
	 * @param expndArtclVo
	 * @return int
	 * @throws Exception
	 */
	public int deleteExpndArtcl(ExpndArtclVo expndArtclVo) throws Exception;

	/**
	* 교구 및 시설목록 조회
	*
	* @Title : selecttchaidFcltList
	* @Description : 교구 및 시설목록 조회
	* @param asgsysSrngVo
	* @return List<TchaidFcltVo>
	* @throws Exception
	*/
	public List<TchaidFcltVo> selectTchaidFcltList(TchaidFcltVo tchaidFcltVo) throws Exception;

	/**
	* 교구 및 시설목록 등록
	*
	* @Title : insertTchaidFclt
	* @Description : 교구 및 시설목록 등록
	* @param tchaidFcltVo
	* @throws Exception
	* @return void
	*/
	public int insertTchaidFclt(TchaidFcltVo tchaidFcltVo) throws Exception;

	/**
	* 교구 및 시설목록 삭제
	*
	* @Title : deleteTchaidFclt
	* @Description : 교구 및 시설목록 삭제
	* @param TchaidFcltVo
	* @return int
	* @throws Exception
	*/
	public void deleteTchaidFclt(TchaidFcltVo tchaidFcltVo) throws Exception;

	/**
	* 프로그램운영관리 수정
	*
	* @Title : updatePrgrmOperMng
	* @Description : 프로그램운영관리 수정
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updatePrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지원단심사 목록 조회
	*
	* @Title : selectSprtgrpSrngList
	* @Description : 지원단심사 목록 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSprtgrpSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지원단심사 상세 조회
	*
	* @Title : selectSprtgrpSrng
	* @Description : 지원단심사 상세 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo
	* @throws Exception
	*/
	public AsgsysSrngVo selectSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지원단심사 체크리스트 조회
	*
	* @Title : selectSprtgrpSrngList
	* @Description : 지원단심사 체크리스트 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectCheckList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지원단심사 등록
	*
	* @Title : insertSprtgrpSrng
	* @Description : 지원단심사 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 지원단심사 심사의견 수정
	 *
	 * @Title : updateSprtgrpOpnn
	 * @Description : 지원단심사 심사의견 수정
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int updateSprtgrpOpnn(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
	 * 체크리스트 제출 수정
	 *
	 * @Title : updateChklstSbmsn
	 * @Description : 체크리스트 제출 수정
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int updateChklstSbmsn(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 지원단심사 심사 수정
	 *
	 * @Title : updateSprtgrpSrng
	 * @Description : 지원단심사 심사 수정
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int updateSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
	* 증빙서류 파일목록 조회
	*
	* @Title : selectEvdncdcmntFileList
	* @Description : 증빙서류 파일목록 조회
	* @param fileVo
	* @return List<FileVo>
	* @throws Exception
	*/
	public List<FileVo> selectEvdncDcmntFileList(FileVo fileVo) throws Exception;

	/**
	* 담당자 목록 조회
	*
	* @Title : selectjdgsPicList
	* @Description : 담당자(지원단) 목록 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSprtgrpPicList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 담당자 목록 조회
	 *
	 * @Title : selectjdgsPicList
	 * @Description : 담당자(심사위원) 목록 조회
	 * @param asgsysSrngVo
	 * @return List<AsgsysSrngVo>
	 * @throws Exception
	 */
	public List<AsgsysSrngVo> selectjdgsPicList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램우수성 저장
	*
	* @Title : insertPrgrmDstnctn
	* @Description : 프로그램우수성 저장
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertPrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 프로그램우수성 수정
	 *
	 * @Title : updatePrgrmDstnctn
	 * @Description : 프로그램우수성 수정
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int updatePrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 회원목록 조회
	*
	* @Title : selectMbrList
	* @Description : 회원목록 조회
	* @param asgsysSrngVo
	* @return List<MemberVo>
	* @throws Exception
	*/
	public List<MemberVo> selectMbrList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 담당자 변경
	*
	* @Title : updateMbr
	* @Description : 담당자 변경
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int updateMbr(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 담당자 심사위원 삭제
	 *
	 * @Title : deletePicJdgs
	 * @Description : 담당자 삭제
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int deletePicJdgs(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 담당자 지원단 삭제
	 *
	 * @Title : deletePicSprtgrp
	 * @Description : 담당자 삭제
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int deletePicSprtgrp(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 담당자 배정 등록 심사위원
	*
	* @Title : insertPicJdgs
	* @Description : 담당자 배정 등록 심사위원
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertPicJdgs(AsgsysSrngVo asgsysSrngVo)  throws Exception;

	/**
	* 담당자 배정 등록 지원단
	*
	* @Title : insertPicSprtgrp
	* @Description : 담당자 배정 등록 지원단
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertPicSprtgrp(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 점수 목록조회
	*
	* @Title : selectSrngScrList
	* @Description : 심사위원심사 점수 목록조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSrngScrList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 심사위원심사 점수 헤더 조회
	 *
	 * @Title : selectSrngScrList
	 * @Description : 심사위원심사 점수 헤더 조회
	 * @param asgsysSrngVo
	 * @return List<AsgsysSrngVo>
	 * @throws Exception
	 */
	public AsgsysSrngVo selectSrngScrHeader(AsgsysSrngVo asgsysSrngVo) throws Exception;

}
