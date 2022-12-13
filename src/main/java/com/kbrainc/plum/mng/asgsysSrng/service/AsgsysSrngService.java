package com.kbrainc.plum.mng.asgsysSrng.service;

import java.util.List;
import java.util.Map;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;

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

}
