package com.kbrainc.plum.front.dsgnPrgrm.service;

import java.util.List;

import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;

/**
 *
 * 지정프로그램 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.front.dsgnPrgrm.service
 * - CodeService.java
 * </pre>
 *
 * @ClassName : DsgnPrgrmService
 * @Description : 지정프로그램 서비스 인터페이스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public interface DsgnPrgrmService {

	/**
	* 지정현황 목록 조회
	*
	* @Title : selectDsgnSttusList
	* @Description : 지정현황 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectDsgnSttusList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정현황 상세 화면이동
	*
	* @Title : selectDsgnSttus
	* @Description : 지정현황 상세 화면이동
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectDsgnSttus(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 교육사진파일목록 조회
	*
	* @Title : selectEduPhotoFileList
	* @Description : 교육사진파일목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectEduPhotoFileList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 기관지정프로그램 목록 조회
	*
	* @Title : selectEduPhotoFileList
	* @Description : 기관지정프로그램 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectInstPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 프로그램 운영일정 목록조회
	*
	* @Title : selectPrgrmSchdlList
	* @Description : 프로그램 운영일정 목록조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectPrgrmSchdlList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 기관정보 조회
	*
	* @Title : selectInstInfo
	* @Description : 기관정보 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectInstInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 신청정보 조회
	*
	* @Title : selectAplyInfo
	* @Description : 신청정보 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectAplyInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정프로그램 등록
	*
	* @Title : insertPrgrmAssPrgrm
	* @Description : 지정프로그램 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertPrgrmAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 지정프로그램 수정
	 *
	 * @Title : updateAssPrgrm
	 * @Description : 지정프로그램 수정
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 프로그램우수성 등록
	*
	* @Title : insertPrgrmDstnctnForm
	* @Description : 프로그램우수성 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertPrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 대처계획 목록 조회
	*
	* @Title : selectPlanList
	* @Description : 대처계획 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectPlanList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 프로그램 평가 조회
	*
	* @Title : selectPrgrmEvlForm
	* @Description : 프로그램 평가 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectPrgrmEvlForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 컨설팅 목록 조회
	*
	* @Title : selectCsltngList
	* @Description : 컨설팅 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectCsltngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 컨설팅 등록
	*
	* @Title : insertCsltng
	* @Description : 컨설팅 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertCsltng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 신청내역 조회
	*
	* @Title : selectAplyDsctnList
	* @Description : 신청내역 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectAplyDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 체크리스트 정보 조회
	*
	* @Title : selectChkListInfo
	* @Description : 체크리스트 정보 조회
	* @param dsgnPrgrmVo
	* @throws Exception
	* @return dsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectChkListInfo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 문항 목록 조회
	*
	* @Title : selectQitemList
	* @Description : 문항 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectQitemList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

}