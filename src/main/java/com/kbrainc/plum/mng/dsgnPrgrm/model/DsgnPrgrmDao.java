package com.kbrainc.plum.mng.dsgnPrgrm.model;

import java.util.List;

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

}