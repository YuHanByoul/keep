package com.kbrainc.plum.mng.asgsysSrng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.DsgnSrngFormVo;
import com.kbrainc.plum.mng.asgsysSrng.model.EmrgcyActnPlanVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
import com.kbrainc.plum.mng.asgsysSrng.model.TchaidFcltVo;
import com.kbrainc.plum.mng.member.model.MemberVo;

/**
* 지정제심사관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.service
* - AsgsysSrngService.java
* </pre>
*
* @ClassName : AsgsysSrngService
* @Description : 지정제심사관리 서비스 인터페이스
* @author : kbrain
* @date : 2022. 12. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface AsgsysSrngService {

	/**
	 * 지정신청 목록 조회.
	 *
	 * @Title : selectDsgnAplyList
	 * @Description : 지정신청 목록 조회.
	 * @param asgsysAplyVo
	 * @throws Exception
	 * @return List<AsgsysSrngVo>
	 */
	public List<AsgsysSrngVo> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 지정신청 상세 조회.
	 *
	 * @Title : selectDsgnAplyDetail
	 * @Description : 지정신청 상세 조회.
	 * @param asgsysAplyVo
	 * @throws Exception
	 * @return AsgsysSrngVo
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
	* @Title : aplyExcelDownList
	* @Description : 지정신청목록 엑셀다운로드
	* @param memberVo
	* @param response
	* @param request
	* @throws Exception
	* @return void
	*/
	public void aplyExcelDownList(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	 * 총평 엑셀다운로드
	 *
	 * @Title : gnrlrvwExcelDownList
	 * @Description : 총평 엑셀다운로드
	 * @param memberVo
	 * @param response
	 * @param request
	 * @throws Exception
	 * @return void
	 */
	void gnrlrvwExcelDownList(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception;

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
	* @return int
	* @throws Exception
	*/
	public int updateSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
	* @return PrgrmSchdlVo
	* @throws Exception
	*/
	public List<PrgrmSchdlVo> selectPrgrmSchdlList(PrgrmSchdlVo prgrmSchdlVo) throws Exception;

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
	* 프로그램 운영일정 수정
	*
	* @Title : updatePrgrmSchdl
	* @Description : 프로그램 운영일정 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	public int updatePrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception;
	 */

	/**
	* 프로그램 운영일정 등록
	*
	* @Title : insertPrgrmSchdl
	* @Description : 프로그램 운영일정 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	public int insertPrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception;
	 */

	/**
	* 프로그램 운영일정 삭제
	*
	* @Title : deletePrgrmSchdl
	* @Description : 프로그램 운영일정 삭제
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	public int deletePrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception;
	 */

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
	* 프로그램 평가 수정
	*
	* @Title : updatePrgrmEvl
	* @Description : 프로그램 평가 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int updatePrgrmEvl(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 책임개발자 목록 조회
	*
	* @Title : selectLdrList
	* @Description : 책임개발자 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectLdrList(AsgsysSrngVo asgsysSrngVo) throws Exception;
	/**
	* 책임개발자 이력 조회
	*
	* @Title : selectSnrstfdvlprHstry
	* @Description : 책임개발자 이력 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	public AsgsysSrngVo selectSnrstfdvlprHstry(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 책임개발자 학력사항 목록조회
	*
	* @Title : selectSnrstfdvlprAcbgList
	* @Description : 책임개발자 학력사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectSnrstfdvlprAcbgList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 책임개발자 자격사항 목록조회
	*
	* @Title : selectSnrstfdvlprQlfcList
	* @Description : 책임개발자 자격사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectSnrstfdvlprQlfcList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 책임개발자 경력사항 목록조회
	*
	* @Title : selectSnrstfdvlprCareerList
	* @Description : 책임개발자 경력사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectSnrstfdvlprCareerList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 지도자 자격 및 배치 등록
	*
	* @Title : insertLdrQlfcForm
	* @Description : 지도자 자격 및 배치 등록
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertLdrQlfcForm(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
	public List<AsgsysSrngVo> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 엑셀 다운로드
	*
	* @Title : jdgsSrngMainExcelDownList
	* @Description : 심사위원심사 엑셀 다운로드
	* @param memberVo
	* @param response
	* @param request
	* @throws Exception
	* @return void
	*/
	public void jdgsSrngListExcelDown(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception;

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
	 * 심사위원심사 등록
	 *
	 * @Title       : insertJdgsSrngDetail
	 * @Description : 회원등록
	 * @param memberVo MemberVo , TeacherVo TeacherVo객체
	 * @param user 사용자세션정보
	 * @return Map<String,Object> 응답결과객체
	 * @throws Exception 예외
	 */
	public int updateJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* updateSftyMng
	*
	* @Title : updateSftyMng
	* @Description : 안전관리 수정
	* @param asgsysSrngVo
	* @return int
	*/
	public int updateSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 안전관리 등록
	 *
	 * @Title : insertSftyMng
	 * @Description : 안전관리 등록
	 * @param asgsysSrngVo
	 * @return int
	 */
	public int insertSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 등록
	*
	* @Title : insertJdgsSrngDetail
	* @Description : 심사위원심사 등록
	* @param asgsysSrngVo
	* @return
	* @return int
	*/
	public int insertJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 프로그램운영 관리 조회
	 *
	 * @Title : selectPrgrmOperMng
	 * @Description : 프로그램운영 관리 조회
	 * @param asgsysSrngVo
	 * @return AsgsysSrngVo객체
	 * @throws Exception 예외
	 */
	public AsgsysSrngVo selectPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 교구 및 시설목록 조회
	*
	* @Title       : selectDsgnAplyList
	* @Description : 교구 및 시설목록 조회
	* @param memberVo AsgsysAplyVo객체
	* @return List<TchaidFcltVo>
	* @throws Exception 예외
	*/
	 public List<TchaidFcltVo> selectTchaidFcltList(TchaidFcltVo tchaidFcltVo) throws Exception;

	/**
	* 프로그램운영관리 수정
	*
	* @Title : updatePrgrmOperMng
	* @Description : 프로그램운영관리 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int updatePrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램운영관리 등록
	*
	* @Title : insertPrgrmOperMng
	* @Description : 프로그램운영관리 등록
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
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
	* 지원단심사 목록 조회
	*
	* @Title : selectSprtgrpSrngList
	* @Description : 지원단심사 목록 조회
	* @param asgsysSrngVo
	* @return List<MemberVo>
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
	* 체크치스트 수정
	*
	* @Title : updateAssChklst
	* @Description : 체크치스트 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int updateAssChklst(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
	 * 지원단심사 수정
	 *
	 * @Title : updateSprtgrpSrng
	 * @Description : 지원단심사 등록
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int updateSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 증빙서류 파일목록 조회
	*
	* @Title : selectEvdncdcmntFileList
	* @Description : 증빙서류 파일목록 조회
	* @param fileVo
	* @return Object
	* @throws Exception
	*/
	public List<FileVo> selectEvdncDcmntFileList(FileVo fileVo) throws Exception;

	/**
	* 담당자(지원단) 목록조회
	*
	* @Title : selectPicList
	* @Description : 담당자(지원단) 목록조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSprtgrpPicList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 담당자(심사위원) 목록조회
	*
	* @Title : selectPicList
	* @Description : 담당자(심사위원) 목록조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectjdgsPicList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램우수성 등록
	*
	* @Title : insertPrgrmDstnctn
	* @Description : 프로그램우수성 등록
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
	 * 담당자 삭제
	 *
	 * @Title : deletePicInfo
	 * @Description : 담당자 삭제
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	public int deletePicInfo(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 담당자 배정 등록
	*
	* @Title : insertPicInfo
	* @Description : 담당자 배정 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	public int insertPicInfo(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사점수 목록 조회
	*
	* @Title : selectSrngScrList
	* @Description : 심사점수 목록조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSrngScrList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 심사점수 목록 헤더 조회
	 *
	 * @Title : selectSrngScrHeader
	 * @Description : 심사점수 목록조회
	 * @param asgsysSrngVo
	 * @return List<AsgsysSrngVo>
	 * @throws Exception
	 */
	 public AsgsysSrngVo selectSrngScrHeader(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 교육사진파일그룹아이디 수정
	*
	* @Title : updateEduPhotoFilegrpid
	* @Description : 교육사진파일그룹아이디 수정
	* @param asgsysSrngVo
	* @return
	* @return int
	*/
	public int updateEduPhotoFilegrpid(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사양식 목록 조회
	*
	* @Title : selectSrngFormQitemList
	* @Description : 심사양식 목록 조회
	* @param dsgnSrngFormVo
	* @return
	* @throws Exception
	* @return List<DsgnSrngFormVo>
	*/
	public List<DsgnSrngFormVo> selectSrngFormQitemList(DsgnSrngFormVo dsgnSrngFormVo) throws Exception;

	public List<AsgsysSrngVo> selectSrngFormList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 전문가(심사위원) 목록 조회
	*
	* @Title : selectjdgsList
	* @Description : 전문가(심사위원) 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectjdgsList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사 항목 목록 조회
	*
	* @Title : selectSrngQitemList
	* @Description : 심사 항목 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	public List<AsgsysSrngVo> selectSrngQitemList(AsgsysSrngVo asgsysSrngVo) throws Exception;


}
