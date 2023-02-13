package com.kbrainc.plum.mng.bizAply.bizRpt.model;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.kbrainc.plum.rte.model.UserVo;

/**
* 사업보고관리 Dao 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.model
* - BizRptDao.java
* </pre>
*
* @ClassName : BizRptDao
* @Description : 사업보고관리 Dao 클래스.
* @author : kbrain
* @date : 2023. 2. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Mapper
public interface BizRptDao {

	/**
	* 중간보고관리 목록조회
	*
	* @Title : selectMdlRptMngList
	* @Description : 중간보고관리 목록조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectMdlRptMngList(BizRptVo bizRptVo) throws Exception;

	/**
	* 중간보고관리 목록조회 엑셀
	*
	* @Title : selectMdlRptMngListExcel
	* @Description : 중간보고관리 목록조회 엑셀
	* @param bizRptVo
	* @return
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectMdlRptMngListExcel(BizRptVo bizRptVo) throws Exception;

	/**
	* 중간보고관리 상세 조회
	*
	* @Title : selectMdlRptMng
	* @Description : 중간보고관리 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return BizRptVo
	*/
	public BizRptVo selectMdlRptMng(BizRptVo bizRptVo) throws Exception;

	/**
	* 중간보고제출 목록 조회
	*
	* @Title : selectMdlRptSbmsnList
	* @Description : 중간보고제출 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectMdlRptSbmsnList(BizRptVo bizRptVo) throws Exception;

	/**
	* 중간보고제출 목록 조회(엑셀)
	*
	* @Title : selectMdlRptSbmsnListExcel
	* @Description : 중간보고제출 목록 조회(엑셀)
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectMdlRptSbmsnListExcel(BizRptVo bizRptVo) throws Exception;

	/**
	* 신청상태코드 수정
	*
	* @Title : updateRptSttsCd
	* @Description : 신청상태코드 수정
	* @param user
	* @param reportids
	* @return
	* @return int
	*/
	public int updateRptSttsCd(@Param("user") UserVo user, @Param("reportids") String[] reportids) throws Exception;

	/**
	* 중간보고제출 상세 조회
	*
	* @Title : selectMdlRptSbmsnDetail
	* @Description : 중간보고제출 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return BizRptVo
	*/
	public BizRptVo selectMdlRptSbmsnDetail(BizRptVo bizRptVo) throws Exception;

}
