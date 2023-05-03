package com.kbrainc.plum.front.mypage.seeDsgnDsctn.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.qestnr.model.QitemVo;

/**
* 사회환경교육기관 지정 관리 > 지정관리 dao
*
* <pre>
* com.kbrainc.plum.front.seeDsgnDsctn.model
* - SeeDsgnDsctnDao.java
* </pre>
*
* @ClassName : SeeDsgnDsctnDao
* @Description : 사회환경교육기관 지정 관리 > 지정관리 dao
* @author : LHM
* @date : 2023. 4. 25.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.seeDsgnDsctnDao")
public interface SeeDsgnDsctnDao {

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectSeeDsgnDsctnList
	* @Description : 지정내역 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 변경내역 목록 조회
	*
	* @Title : selectChgSeeDsgnDsctnList
	* @Description : 변경내역 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectChgSeeDsgnDsctnList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgSeeDsgnAply
	* @Description : 변경신청상세 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 운영결과 목록 조회
	*
	* @Title : selectOperRsltList
	* @Description : 운영결과 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectOperRsltList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 운영결과상세 조회
	*
	* @Title : selectOperRslt
	* @Description : 운영결과상세 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectOperRslt(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 운영결과 등록
	*
	* @Title : insertOperRslt
	* @Description : 운영결과 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertOperRslt(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 운영결과 수정
	*
	* @Title : updateOperRslt
	* @Description : 운영결과 수정
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateOperRslt(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 운영결과실적 목록 조회
	*
	* @Title : selectOperRsltPrfmncList
	* @Description : 운영결과실적 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectOperRsltPrfmncList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 이행확인 목록 조회
	*
	* @Title : selectImplmntIdntyList
	* @Description : 이행확인 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectImplmntIdntyList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 이의신청 목록 조회
	*
	* @Title : selectObjcAplyList
	* @Description : 이의신청 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectObjcAplyList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 지정프로그램 목록 조회
	*
	* @Title : dsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> dsgnPrgrmList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 이의신청 등록
	*
	* @Title : insertObjcAplyForm
	* @Description : 이의신청 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertObjcAplyForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 이의신청 수정
	*
	* @Title : updateObjcAplyForm
	* @Description : 이의신청 수정
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateObjcAplyForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;;

	/**
	* 이의신청 삭제
	*
	* @Title : deleteObjcAplyForm
	* @Description : 이의신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteObjcAplyForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 이의신청 조회
	*
	* @Title : selectObjcAply
	* @Description : 이의신청 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectObjcAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 보안요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보안요청 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectSplmntDmnd(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 보안계획 조회
	*
	* @Title : selectSplmntPlan
	* @Description : 보안계획 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectSplmntPlan(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 보안계획 등록
	*
	* @Title : insertImprvPlanForm
	* @Description : 보안계획 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertImprvPlanForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	 * 보안계획 수정
	 *
	 * @Title : updateImprvPlanForm
	 * @Description : 보안계획 수정
	 * @param seeDsgnDsctnVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateImprvPlanForm(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 파일 목록 조회
	*
	* @Title : selectFileList
	* @Description : 파일 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectFileList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 변경신청 수정
	*
	* @Title : updateChgSeeDsgnAply
	* @Description : 변경신청 수정
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	 * 변경신청 등록
	 *
	 * @Title : insertChgSeeDsgnAply
	 * @Description : 변경신청 등록
	 * @param seeDsgnDsctnVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int insertChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 변경신청 삭제
	*
	* @Title : deleteChgSeeDsgnAply
	* @Description : 변경신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return boolean
	*/
	public boolean deleteChgSeeDsgnAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : 컨설팅관리 목록 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<SeeDsgnDsctnVo> selectCnsltngMngList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* (컨설팅)신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : (컨설팅)신청정보 조회
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return SeeDsgnDsctnVo
	*/
	public SeeDsgnDsctnVo selectAplyInfo(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 컨설팅 신청 등록
	*
	* @Title : insertCnsltngAply
	* @Description : 컨설팅 신청 등록
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	 * 컨설팅 신청 수정
	 *
	 * @Title : updateCnsltngAply
	 * @Description : 컨설팅 신청 수정
	 * @param seeDsgnDsctnVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* 컨설팅신청 삭제
	*
	* @Title : deleteCnsltngAply
	* @Description : 컨설팅신청 삭제
	* @param seeDsgnDsctnVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteCnsltngAply(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

	/**
	* (컨설팅)설문 응답 목록 조회
	*
	* @Title : selectSrvyAnsList
	* @Description : (컨설팅)설문 응답 목록 조회
	* @param QitemVo
	* @return
	* @throws Exception
	* @return List<SeeDsgnDsctnVo>
	*/
	public List<QitemVo> selectSrvyAnsList(SeeDsgnDsctnVo seeDsgnDsctnVo) throws Exception;

}
