package com.kbrainc.plum.front.dsgnPrgrm.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 *
 * 지정프로그램 DAO 클래스
 *
 * <pre>
 * com.kbrainc.plum.front.dsgnPrgrm.model
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
@Mapper("front.dsgnPrgrmDao")
public interface DsgnPrgrmDao {

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
	* @return List<PrgrmSchdlVo>
	*/
	public List<DsgnPrgrmVo> selectPrgrmSchdlList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

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
	 * 신청정보 프로그램 아이디 조회
	 *
	 * @Title : getAplyInfo
	 * @Description : 신청정보 프로그램 아이디 조회
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int getPrgrmid(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

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
	 * @Title : updatePrgrmAssPrgrm
	 * @Description : 지정프로그램 수정
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updatePrgrmAssPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 프로그램 우수성 등록
	 *
	 * @Title : insertPrgrmDstnctnForm
	 * @Description : 프로그램 우수성 등록
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int insertPrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 프로그램 우수성 수정
	 *
	 * @Title : updatePrgrmDstnctnForm
	 * @Description : 프로그램 우수성 수정
	 * @param dsgnPrgrmVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updatePrgrmDstnctnForm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 프로그램 일정 등록
	 * @param dsgnPrgrmVo
	*
	* @Title : insertPrgrmSchdl
	* @Description : 프로그램 일정 등록
	* @param schdlMap
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertPrgrmSchdl(DsgnPrgrmVo dsgnPrgrmVo, @Param("schdlMap") Map<String, String> schdlMap) throws Exception;

	/**
	 * 프로그램 일정 삭제
	 *
	 * @Title : deletePrgrmSchdl
	 * @Description : 프로그램 일정 삭제
	 * @param schdlMap
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int deletePrgrmSchdl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

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
	* 컨설팅 수정
	*
	* @Title : updateCsltng
	* @Description : 컨설팅 수정
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateCsltng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	* 지정프로그램 컨설팅아이디 수정
	*
	* @Title : updateCnsltngid
	* @Description : 지정프로그램 컨설팅아이디 수정
	* @param dsgnPrgrmVo
	* @throws Exception
	* @return void
	*/
	public int updateCnsltngid(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

	/**
	 * 컨설팅아이디 조회
	 *
	 * @Title : updateCnsltngid
	 * @Description : 컨설팅아이디 조회
	 * @param dsgnPrgrmVo
	 * @throws Exception
	 * @return void
	 */
	public int selectCnsltngid(DsgnPrgrmVo dsgnPrgrmVo) throws Exception;

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

}