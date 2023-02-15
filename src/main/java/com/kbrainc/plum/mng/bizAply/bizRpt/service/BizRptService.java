package com.kbrainc.plum.mng.bizAply.bizRpt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptVo;
import com.kbrainc.plum.rte.model.UserVo;

/**
* 사업보고관리 서비스 인터페이스
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.service
* - BizRptService.java
* </pre>
*
* @ClassName : BizRptService
* @Description : 사업보고관리 서비스 인터페이스
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
public interface BizRptService {

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
	* 중간보고관리 목록 엑셀다운로드
	*
	* @Title : aplyExcelDownList
	* @Description : 중간보고관리 목록 엑셀다운로드
	* @param request
	* @param response
	* @param asgsysSrngVo
	* @return void
	* @throws Exception
	*/
	public void selectMdlRptMngListExcel(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	* 중간보고관리 상세 조회
	*
	* @Title : selectMdlRptMng
	* @Description : 중간보고관리 상세 조회
	* @param bizRptVo
	* @return
	* @return BizRptVo
	* @throws Exception
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
	* 신청상태코드 수정
	*
	* @Title : updateRptSttsCd
	* @Description : 신청상태코드 수정
	* @param bizRptVo
	* @param reportids
	* @return
	* @return int
	*/
	public int updateRptSttsCd(UserVo user, String[] reportids) throws Exception;

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

	/**
	* 보고운영목록 조회
	*
	* @Title : selectReportOperList
	* @Description : 보고운영목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectReportOperList(BizRptVo bizRptVo) throws Exception;

}
