package com.kbrainc.plum.front.dsgnPrgrm.model;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;

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


}