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
	* 보고제출 목록 조회
	*
	* @Title : selectRptSbmsnList
	* @Description : 보고제출 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectRptSbmsnList(BizRptVo bizRptVo) throws Exception;

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

	/**
	* 보완요청 목록 조회
	*
	* @Title : selectSplmntDmndList
	* @Description : 보완 요청 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectSplmntDmndList(BizRptVo bizRptVo) throws Exception;

	/**
	* 보완요청 상세 조회
	*
	* @Title : getRptSplmntDmnd
	* @Description : 보완요청 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return BizRptVo
	*/
	public BizRptVo selectRptSplmntDmnd(BizRptVo bizRptVo) throws Exception;

	/**
	* 보고보완 등록
	*
	* @Title : insertRptSplmnt
	* @Description : 보고보완등록
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertRptSplmnt(BizRptVo bizRptVo) throws Exception;

	/**
	* 결과보고관리 목록조회
	*
	* @Title : selectRsltRptMngList
	* @Description : 결과보고관리 목록조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectRsltRptMngList(BizRptVo bizRptVo) throws Exception;

	/**
	* 결과보고관리 상세 조회
	*
	* @Title : selectRsltRptMng
	* @Description : 결과보고관리 상세 조회
	* @param bizRptVo
	* @return
	* @return BizRptVo
	*/
	public BizRptVo selectRsltRptMng(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : selectCnsltngMngList
	* @param bizRptVo
	* @return
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectCnsltngMngList(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅관리 상세 조회
	*
	* @Title : selectCnsltngMng
	* @Description : 컨설팅관리 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return BizRptVo
	*/
	public BizRptVo selectCnsltngMng(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅관리 등록
	*
	* @Title : insertCnsltngMng
	* @Description : 컨설팅관리 등록
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertCnsltngMng(BizRptVo bizRptVo) throws Exception;

	/**
	* 사업포기관리 목록 조회
	*
	* @Title : selectBizAbndMngList
	* @Description : 사업포기관리 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectBizAbndMngList(BizRptVo bizRptVo) throws Exception;

	/**
	* 사업포기관리 상세 조회
	*
	* @Title : selectBizAbndMng
	* @Description : 사업포기관리 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return Object
	*/
	public BizRptVo selectBizAbndMng(BizRptVo bizRptVo) throws Exception;

	/**
	* 사업포기 수정
	*
	* @Title : updateBizAbnd
	* @Description : 사업포기 수정
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateBizAbnd(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅전문가 목록 조회
	*
	* @Title : selectCnsltngExprtList
	* @Description : 컨설팅전문가 목록 조회
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectCnsltngExprtList() throws Exception;

	/**
	* 컨설팅관리 목록 엑셀다운로드
	*
	* @Title : selectCnsltngMngExcelList
	* @Description : 컨설팅관리 목록 엑셀다운로드
	* @param bizRptVo
	* @param response
	* @param request
	* @throws Exception
	* @return void
	*/
	public void selectCnsltngMngExcelList(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	* 담당자그룹 목록 조회
	*
	* @Title : selectMngGrpList
	* @Description : 담당자그룹 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectMngGrpList(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅대상 등록
	*
	* @Title : insertCnsltngTrgt
	* @Description : 컨설팅대상 등록
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int insertCnsltngTrgt(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅 대상 내용 조회
	*
	* @Title : selectCnsltngTrgtCn
	* @Description : 컨설팅 대상 내용 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return BizRptVo
	*/
	public BizRptVo selectCnsltngTrgtCn(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설턴트 목록 조회
	*
	* @Title : selectCnstntList
	* @Description : 컨설턴트 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectCnstntList(BizRptVo bizRptVo) throws Exception;

	/**
	* 컨설팅 대상 삭제(취소)
	*
	* @Title : deleteCnsltngTrgt
	* @Description : 컨설팅 대상 삭제(취소)
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int deleteCnsltngTrgt(BizRptVo bizRptVo) throws Exception;

    /**
    * 사업보고 제출하지 않은 접수번호 조회. 
    *
    * @Title : selectAplyList
    * @Description : TODO
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return List<BizRptVo>
     */
    public List<BizRptVo> selectAplyList(BizRptVo bizRptVo) throws Exception;
    
    /**
    * 사업보고 등록 
    *
    * @Title : insertReport
    * @Description : TODO
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return int
     */
    public int insertReport(BizRptVo bizRptVo) throws Exception;
    
    /**
    * 사업포기신청 등록화면 접수번호 목록 조회. 
    *
    * @Title : selectAllAplyList
    * @Description : TODO
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return List<BizRptVo>
     */
    public List<BizRptVo> selectAllAplyList(BizRptVo bizRptVo) throws Exception;
    
    /**
    * 사업포기신청 등록. 
    *
    * @Title : insertCancel
    * @Description : TODO
    * @param bizRptVo
    * @return
    * @throws Exception
    * @return int
     */
    public int insertCancel(BizRptVo bizRptVo) throws Exception;
}
