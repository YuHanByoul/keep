package com.kbrainc.plum.mng.bizAply.bizRpt.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	* 보고제출 목록 조회(엑셀)
	*
	* @Title : selectRptSbmsnListExcel
	* @Description : 보고제출 목록 조회(엑셀)
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectRptSbmsnListExcel(BizRptVo bizRptVo) throws Exception;

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
	* 보고상태코드 수정
	*
	* @Title : updateReportSttsCd
	* @Description : 보고상태코드 수정
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateReportSttsCd(BizRptVo bizRptVo) throws Exception;

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
	* 결과보고관리 목록 엑셀다운로드
	*
	* @Title : selectRsltRptMngListExcel
	* @Description : 결과보고관리 목록 엑셀다운로드
	* @param BizAbndVo
	* @return void
	* @throws Exception
	*/
	public List<BizRptVo> selectRsltRptMngListExcel(BizRptVo bizRptVo) throws Exception;

	/**
	 * 결과보고제출 목록 엑셀다운로드
	 *
	 * @Title : selectRsltRptSbmsnListExcel
	 * @Description : 결과보고제출 목록 엑셀다운로드
	 * @param BizRptVo
	 * @return void
	 * @throws Exception
	 */
	public List<BizRptVo> selectRsltRptSbmsnListExcel(BizRptVo bizRptVo) throws Exception;

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
	* 결과보고제출 목록 조회
	*
	* @Title : selectRsltRptSbmsnList
	* @Description : 결과보고제출 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectRsltRptSbmsnList(BizRptVo bizRptVo) throws Exception;

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
	 * 컨설팅관리 수정
	 *
	 * @Title : updateCnsltngMng
	 * @Description : 컨설팅관리 수정
	 * @param bizRptVo
	 * @return
	 * @throws Exception
	 * @return int
	 */
	public int updateCnsltngMng(BizRptVo bizRptVo) throws Exception;

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
	* 사업포기관리목록 엑셀 다운로드
	*
	* @Title : selectBizAbndMngExcelList
	* @Description : 사업포기관리목록 엑셀 다운로드
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectBizAbndMngExcelList(BizRptVo bizRptVo) throws Exception;

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
	* 사업취소여부 수정
	*
	* @Title : updateBsnsCnclYn
	* @Description : 사업취소여부 수정
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateBsnsCnclYn(BizRptVo bizRptVo) throws Exception;

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
	* @return
	* @return List<BizRptVo>
	*/
	public List<BizRptVo> selectCnsltngMngExcelList(BizRptVo bizRptVo) throws Exception;

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
	* 컨설팅대상내용 수정
	*
	* @Title : updateTrgtCn
	* @Description : 컨설팅대상내용 수정
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return int
	*/
	public int updateTrgtCn(BizRptVo bizRptVo) throws Exception;

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

	public void deleteCnsltngMng(BizRptVo bizRptVo) throws Exception;

}
