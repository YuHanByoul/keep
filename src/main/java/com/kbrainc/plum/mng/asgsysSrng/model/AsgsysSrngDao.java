package com.kbrainc.plum.mng.asgsysSrng.model;

import java.util.List;

import javax.validation.Valid;

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
	 * @Title : selectMemberExcelList
	 * @Description : 지정신청목록 엑셀다운로드
	 * @param asgsysSrngVo
	 * @throws Exception
	 * @return List<AsgsysSrngVo> 사용자정보 목록
	 */
	public List<AsgsysSrngVo> aplyExcelDownList(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
    * @throws Exception 예외
    * @return List<AsgsysSrngVo>
    */
	public List<AsgsysSrngVo> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception;

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
    * 심사위원심사 수정
    *
    * @Title       : updateJdgsSrngDetail
    * @Description : 심사위원심사 수정
	* @param asgsysSrngVo
	* @return int
    * @throws Exception 예외
    */
	public int updateJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	 * 심사위원심사 등록
	 *
	 * @Title : insertJdgsSrngDetail
	 * @Description : 심사위원심사 등록
	 * @param asgsysSrngVo
	 * @return int
     * @throws Exception 예외
	 */
	public int insertJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo);

	/**
	* 안전관리 수정
	*
	* @Title : updateSftyMng
	* @Description : 안전관리 수정
	* @param asgsysSrngVo
	* @return int
    * @throws Exception 예외
	*/
	public int updateSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 심사위원심사 목록 엑셀 다운
	*
	* @Title : jdgsSrngListExcelDown
	* @Description : 심사위원심사 목록 엑셀 다운
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> jdgsSrngListExcelDown(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램 운영관리 조회
	*
	* @Title : selectPrgrmOperMng
	* @Description : 프로그램 운영관리 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo
	* @throws Exception
	*/
	public AsgsysSrngVo selectPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 교구 및 시설목록 조회
	*
	* @Title : selecttchaidFcltList
	* @Description : 교구 및 시설목록 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selecttchaidFcltList(AsgsysSrngVo asgsysSrngVo) throws Exception;

	/**
	* 프로그램운영관리 수정
	*
	* @Title : updatePrgrmOperMng
	* @Description : 프로그램운영관리 수정
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updatePrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception;


}
