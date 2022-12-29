package com.kbrainc.plum.mng.asgsysSrng.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
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
	* @return List<AsgsysSrngVo>
	* @throws Exception 예외
	*/
	 public List<AsgsysSrngVo> selectTchaidFcltList(AsgsysSrngVo asgsysSrngVo) throws Exception;

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

}