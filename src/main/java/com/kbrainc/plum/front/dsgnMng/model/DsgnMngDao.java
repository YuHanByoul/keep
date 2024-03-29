package com.kbrainc.plum.front.dsgnMng.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.front.cntst.model.CntstVo;
import com.kbrainc.plum.mng.qestnr.model.QitemVo;

import java.util.List;

/**
* 사용자.지정관리 dao
*
* <pre>
* com.kbrainc.plum.front.dsgnMng.model
* - DsgnMngDao.java
* </pre>
*
* @ClassName : DsgnMngDao
* @Description : 사용자.지정관리 dao
* @author : kbrain
* @date : 2023. 3. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper("front.dsgnMngDao")
public interface DsgnMngDao {

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnHstryList
	* @Description : 지정내역 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectDsgnHstryList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 변경내역 목록 조회
	*
	* @Title : selectChgDsctnList
	* @Description : 변경내역 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectChgDsctnList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgAply
	* @Description : 변경신청상세 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectChgAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 운영결과 목록 조회
	*
	* @Title : selectOperRsltList
	* @Description : 운영결과 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectOperRsltList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 운영결과상세 조회
	*
	* @Title : selectOperRslt
	* @Description : 운영결과상세 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectOperRslt(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 운영결과 등록
	*
	* @Title : insertOperRslt
	* @Description : 운영결과 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertOperRslt(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 운영결과 수정
	*
	* @Title : updateOperRslt
	* @Description : 운영결과 수정
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateOperRslt(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 운영결과실적 목록 조회
	*
	* @Title : selectOperRsltPrfmncList
	* @Description : 운영결과실적 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectOperRsltPrfmncList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 이행확인 목록 조회
	*
	* @Title : selectImplmntIdntyList
	* @Description : 이행확인 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectImplmntIdntyList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 이의신청 목록 조회
	*
	* @Title : selectObjcAplyList
	* @Description : 이의신청 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectObjcAplyList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 지정프로그램 목록 조회
	*
	* @Title : dsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> dsgnPrgrmList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 이의신청 등록
	*
	* @Title : insertObjcAplyForm
	* @Description : 이의신청 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertObjcAplyForm(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 이의신청 수정
	*
	* @Title : updateObjcAplyForm
	* @Description : 이의신청 수정
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateObjcAplyForm(DsgnMngVo dsgnMngVo) throws Exception;;

	/**
	* 이의신청 삭제
	*
	* @Title : deleteObjcAplyForm
	* @Description : 이의신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteObjcAplyForm(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 이의신청 조회
	*
	* @Title : selectObjcAply
	* @Description : 이의신청 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectObjcAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 보안요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보안요청 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectSplmntDmnd(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 보안계획 조회
	*
	* @Title : selectSplmntPlan
	* @Description : 보안계획 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectSplmntPlan(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 보안계획 등록
	*
	* @Title : insertImprvPlanForm
	* @Description : 보안계획 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertImprvPlanForm(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	 * 보안계획 수정
	 *
	 * @Title : updateImprvPlanForm
	 * @Description : 보안계획 수정
	 * @param dsgnMngVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateImprvPlanForm(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 파일 목록 조회
	*
	* @Title : selectFileList
	* @Description : 파일 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectFileList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 변경신청 수정
	*
	* @Title : updateChgAply
	* @Description : 변경신청 수정
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateChgAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	 * 변경신청 등록
	 *
	 * @Title : insertChgAply
	 * @Description : 변경신청 등록
	 * @param dsgnMngVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int insertChgAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 변경신청 삭제
	*
	* @Title : deleteChgAply
	* @Description : 변경신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return boolean
	*/
	public boolean deleteChgAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : 컨설팅관리 목록 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<DsgnMngVo> selectCnsltngMngList(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* (컨설팅)신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : (컨설팅)신청정보 조회
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return DsgnMngVo
	*/
	public DsgnMngVo selectAplyInfo(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 컨설팅 신청 등록
	*
	* @Title : insertCnsltngAply
	* @Description : 컨설팅 신청 등록
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	 * 컨설팅 신청 수정
	 *
	 * @Title : updateCnsltngAply
	 * @Description : 컨설팅 신청 수정
	 * @param dsgnMngVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* 컨설팅신청 삭제
	*
	* @Title : deleteCnsltngAply
	* @Description : 컨설팅신청 삭제
	* @param dsgnMngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteCnsltngAply(DsgnMngVo dsgnMngVo) throws Exception;

	/**
	* (컨설팅)설문 응답 목록 조회
	*
	* @Title : selectSrvyAnsList
	* @Description : (컨설팅)설문 응답 목록 조회
	* @param QitemVo
	* @return
	* @throws Exception
	* @return List<DsgnMngVo>
	*/
	public List<QitemVo> selectSrvyAnsList(DsgnMngVo dsgnMngVo) throws Exception;

}
