package com.kbrainc.plum.mng.dsgnPrgrm.service;

import java.util.List;

import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;

/**
 *
 * 지정프로그램 서비스 인터페이스
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.service
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
	* 지정프로그램 목록 조회
	*
	* @Title : selectDsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	public List<DsgnPrgrmVo> selectDsgnPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnDsctnList
	* @Description : 지정내역 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @return List<DsgnPrgrmVo>
	*/
	public List<DsgnPrgrmVo> selectDsgnDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

}