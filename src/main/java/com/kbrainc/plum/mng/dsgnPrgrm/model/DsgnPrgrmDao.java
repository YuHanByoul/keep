package com.kbrainc.plum.mng.dsgnPrgrm.model;

import java.util.List;

import javax.validation.Valid;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 *
 * 지정프로그램 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.model
 * - CodeDao.java
 * </pre>
 *
 * @ClassName : DsgnPrgrm
 * @Description : 지정프로그램 DAO 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Mapper
public interface DsgnPrgrmDao {

	/**
	* 지정번호 중복 조회
	*
	* @Title : selectDsgnNoDupChk
	* @Description : 지정번호 중복 조회
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int selectDsgnNoDupChk(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정번호 조회
	*
	* @Title : selectDsgnNo
	* @Description : 지정번호 조회
	* @param dsgnPrgrmVo
	* @return dsgnPrgrmVo
	* @throws Exception
	*/
	public DsgnPrgrmVo selectDsgnNo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정내역 조회
	*
	* @Title : selectDsgnHstry
	* @Description : 지정내역 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	public List<DsgnPrgrmVo> selectDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo)throws Exception;

	/**
	* 지정프로그램 목록 조회
	 *
	 * @Title : selectDsgnPrgrmList
	 * @Description : 지정프로그램 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<AsgsysSrngVo>
	 * @throws Exception
	 */
	public List<DsgnPrgrmVo> selectDsgnPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnDsctnList
	* @Description : 지정내역 목록조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
ㅊ
	*/
	public List<DsgnPrgrmVo> selectDsgnDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 이의신청 목록 조회
	 *
	 * @param dsgnPrgrmVo
	 * @return list
	 * @throws Exception
	 * @Title : selectObjcList
	 * @Description : 이의신청 목록 조회
	 */
	public List<DsgnPrgrmObjcVo> selectObjcList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 이의신청 정보 조회
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return DsgnPrgrmObjcVo
	 * @throws Exception
	 * @Title : selectObjcInfo
	 * @Description : 이의신청 정보 조회
	 */
	public DsgnPrgrmObjcVo selectObjcInfo(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception;

	/**
	 * 이의신청 답변 등록
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return int
	 * @throws Exception
	 * @Title : insertObjcAns
	 * @Description : 이의신청 답변 등록
	 */
	public int insertObjcAns(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception;

	/**
	* 지정내역 저장
	*
	* @Title : insertDsgnHstry
	* @Description : 지정내역 저장
	* @param dsgnPrgrmVo
	* @return int
    * @throws Exception;
	*/
	public int insertDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정프로그램 상세 조회
	*
	* @Title : selectDsgnPrgrm
	* @Description : 지정프로그램 상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception;
	*/
	public DsgnPrgrmVo selectDsgnPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정내역 변경
	*
	* @Title : updateDsgnHstry
	* @Description : 지정내역 변경
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	*
	*
	* @Title : 변경신청(탭) 조회
	* @Description : 변경신청(탭) 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	public List<DsgnPrgrmVo> selectChgAplyList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 변경신청(탭) 등록
	*
	* @Title : insertChgAply
	*
	* @Description : 변경신청(탭) 등록
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int insertChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 변경신청(탭) 등록
	*
	* @Title : updateChgAply
	* @Description : 변경신청(탭) 수정
	* @param dsgnPrgrmVo
	* @return
	* @return int
	*/
	public int updateChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgAplyDtl
	* @Description : 변경신청상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	public DsgnPrgrmVo selectChgAplyDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 변경신청상태 수정
	*
	* @Title : updateChgAplyStts
	* @Description : 변경신청상태 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateChgAplyStts(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 운영결과 목록 조회
	 *
	 * @Title : selectOperRsltList
	 * @Description : 운영결과 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public List<DsgnPrgrmVo> selectOperRsltList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * (운영결과서)제출기간 수정
	 *
	 * @Title : updateSbmsnPrd
	 * @Description : (운영결과서)제출기간 수정
	 * @param dsgnPrgrmVo
	 * @return int
	 * @throws Exception
	 */
	public int updateSbmsnPrd(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 운영결과 차수 조회
	 *
	 * @Title : selectOperRsltCycl
	 * @Description : 운영결과 차수 조회
	 * @param dsgnPrgrmVo
	 * @return DsgnPrgrmVo
	 * @throws Exception
	 */
	public DsgnPrgrmVo selectOperRsltCycl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 운영결과 상세 조회
	*
	* @Title : selectOperRsltDetail
	* @Description : 운영결과 상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	public DsgnPrgrmVo selectOperRsltDetail(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 운영결과 등록
	*
	* @Title : insertOperRslt
	* @Description : 운영결과 등록
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 운영결과 수정
	*
	* @Title : updateOperRslt
	* @Description : 운영결과 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 운영결과 삭제
	*
	* @Title : delteOperRslt
	* @Description : 운영결과 삭제
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int deleteOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 운영실적 등록
	*
	* @Title : insertOperPrfmnc
	* @Description : 운영실적 등록
	* @param vo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertOperPrfmnc(OperPrfmncVo operPrfmncVo) throws Exception;

	/**
	* 운영실적 삭제
	*
	* @Title : deleteOperPrfmnc
	* @Description : 운영실적 삭제
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteOperPrfmnc(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 이행확인심사 조회
	*
	* @Title : selectOperRsltDetail
	* @Description : 이행확인심사 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	public DsgnPrgrmVo selectimplmntIdntySrng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 이행확인심사 목록 조회
	 *
	 * @Title : selectImplmntIdntySrngList
	 * @Description : 이행확인심사 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public List<DsgnPrgrmVo> selectImplmntIdntySrngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 이행확인심사(탭) 상세 조회
	 *
	 * @Title : selectImplmntIdntySrng
	 * @Description : 이행확인심사 상세 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public DsgnPrgrmVo selectImplmntIdntySrng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 이행확인심사 상세 조회
	 *
	 * @Title : selectImplmntIdntySrngDtl
	 * @Description : 이행확인심사 상세 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public DsgnPrgrmVo selectImplmntIdntySrngDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 보완 요청 조회
	 *
	 * @Title : selectSplmntDmnd
	 * @Description : 보완 요청 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public DsgnPrgrmVo selectSplmntDmnd(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 보완 계획 조회
	 *
	 * @Title : selectSplmntPlan
	 * @Description : 보완 계획 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public DsgnPrgrmVo selectSplmntPlan(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 결과보고서 조회
	 *
	 * @Title : selectRsltRptln
	 * @Description : 결과보고서 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	public DsgnPrgrmVo selectRsltRptln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 보완요청 수정
	*
	* @Title : updateSplmntImprv
	* @Description : 보완요청 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateSplmntImprv(@Valid DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 결과보고서 수정
	*
	* @Title : updateRsltRptln
	* @Description : 결과보고서 수정
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateRsltRptln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 보완계획서 수정
	*
	* @Title : updateScrtyImprvPlanln
	* @Description : 보완계획서 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateScrtyImprvPlanln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정프로그램 개요 조회
	*
	* @Title : selectDsgnOutl
	* @Description : 지정프로그램 개요 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	public DsgnPrgrmVo selectDsgnOutl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 심사일정캘린더 목록 조회
	*
	* @Title : selectSprtgrpClndrList
	* @Description : 심사일정캘린더 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectSprtgrpClndrList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;



}