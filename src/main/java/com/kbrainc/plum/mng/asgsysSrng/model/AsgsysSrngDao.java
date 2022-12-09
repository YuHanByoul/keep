package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
* 지정제심사관리 DAO클래스.
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.model
* - AsgsysSrngDao.java
* </pre>
*
* @ClassName : AsgsysSrngDao
* @Description : 지정제심사관리 DAO클래스.
* @author : kbrain
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface AsgsysSrngDao {

	/**
	 * 지정신청목록 조회.
	 *
	 * @Title : selectDsgnAplyList
	 * @Description : 지정신청목록 조회.
	 * @param asgsysSrngVo
	 * @return List<AsgsysSrngVo>
	 * @throws Exception 예외
	 */
	public List<AsgsysSrngVo> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 지정신청상세 조회.
	 *
	 * @Title : selectDsgnAplyDtlInfo
	 * @Description : 지정신청상세 조회.
	 * @param asgsysSrngVo
	 * @return AsgsysSrngVo
	 * @throws Exception 예외
	 */
	public AsgsysSrngVo selectDsgnAplyDtlInfo(AsgsysSrngVo asgsysSrngVo) throws Exception;

}
