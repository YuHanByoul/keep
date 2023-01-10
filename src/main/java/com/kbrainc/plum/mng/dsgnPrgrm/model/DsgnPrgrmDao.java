package com.kbrainc.plum.mng.dsgnPrgrm.model;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

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

}