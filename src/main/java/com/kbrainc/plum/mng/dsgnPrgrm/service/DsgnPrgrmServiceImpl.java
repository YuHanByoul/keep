package com.kbrainc.plum.mng.dsgnPrgrm.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.dsgnPrgrm.controller.DsgnPrgrmController;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmDao;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmObjcVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.dsgnPrgrm.model.OperPrfmncVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 지정프로그램 서비스 구현 클래스
 *
 * <pre>
 * com.kbrainc.plum.mng.dsgnPrgrm.service
 * - DsgnPrgrmServiceImpl.java
 * </pre>
 *
 * @ClassName : DsgnPrgrmServiceImpl
 * @Description : 지정프로그램 서비스 구현 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 26.
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
@Service
@Slf4j
public class DsgnPrgrmServiceImpl extends PlumAbstractServiceImpl implements DsgnPrgrmService {

    @Autowired
    private DsgnPrgrmDao dsgnPrgrmDao;

    @Autowired
    private AsgsysSrngDao asgSrngDao;

    /**
    * 지정번호 조회
    *
    * @Title : selectDsgnNo
    * @Description : 지정번호 조회
    * @param dsgnPrgrmVo
    * @return dsgnPrgrmVo
    * @throws Exception
    */
    @Override
    public DsgnPrgrmVo selectDsgnNo(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnNo(dsgnPrgrmVo);
	}

    /**
    * 지정내역조회
    *
    * @Title : selectDsgnHstry
    * @Description : 지정내역조회
    * @param dsgnPrgrmVo
    * @return List<DsgnPrgrmVo>
    * @throws Exception
    */
    @Override
    public List<DsgnPrgrmVo> selectDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
    	return dsgnPrgrmDao.selectDsgnHstry(dsgnPrgrmVo);

	}

	/**
	* 지정프로그램 목록 조회
	*
	* @Title : selectDsgnPrgrmList
	* @Description : 지정프로그램 목록 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectDsgnPrgrmList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnPrgrmList(dsgnPrgrmVo);
	}

	/**
	* 지정내역 목록 조회
	*
	* @Title : selectDsgnDsctnList
	* @Description : 지정내역 목록 조회
	* @param dsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectDsgnDsctnList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnDsctnList(dsgnPrgrmVo);
	}

	/**
	 * 이의신청 목록 조회
	 *
	 * @param dsgnPrgrmVo
	 * @return list
	 * @throws Exception
	 * @Title : selectObjcList
	 * @Description : 이의신청 목록 조회
	 */
	@Override
	public List<DsgnPrgrmObjcVo> selectObjcList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectObjcList(dsgnPrgrmVo);
	}

	/**
	 * 이의신청 정보 조회
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return DsgnPrgrmObjcVo
	 * @throws Exception
	 * @Title : selectObjcInfo
	 * @Description : 이의신청 정보 조회
	 */
	@Override
	public DsgnPrgrmObjcVo selectObjcInfo(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception {
		return dsgnPrgrmDao.selectObjcInfo(dsgnPrgrmObjcVo);
	}

	/**
	 * 이의신청 답변 등록
	 *
	 * @param dsgnPrgrmObjcVo
	 * @return int
	 * @throws Exception
	 * @Title : insertObjcAns
	 * @Description : 이의신청 답변 등록
	 */
	@Override
	public int insertObjcAns(DsgnPrgrmObjcVo dsgnPrgrmObjcVo) throws Exception {
		return dsgnPrgrmDao.insertObjcAns(dsgnPrgrmObjcVo);
	}
	/**
	* 지정내역 저장
	*
	* @Title : insertDsgnHstry
	* @Description : 지정내역 저장
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int insertDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {

		int ret=0;

		//차수 조회
//		List<DsgnPrgrmVo> rsltCyclList = dsgnPrgrmDao.selectOperRsltCyclList(dsgnPrgrmVo);
//		if( rsltCyclList == null  ) {
			//운영결과 차수 생성
			DsgnPrgrmVo prgrmInfo =  dsgnPrgrmDao.selectPrgrm(dsgnPrgrmVo);

			String dateFormat = "yyyy";
			String cyclBangDe = "";
			String cyclEndDe ="";
			DateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();  // 현재 시간정보 가지고오기
			sdf.format(cal.getTime());

			//1차
			cyclBangDe = sdf.format(cal.getTime())+"-01-01" ;
			cyclEndDe = sdf.format(cal.getTime())+"-12-31" ;

			prgrmInfo.setCycl("1");
			prgrmInfo.setSbmsnBgngDe(cyclBangDe);
			prgrmInfo.setSbmsnEndDe(cyclEndDe);
			prgrmInfo.setCycl("1");
			cyclEndDe = sdf.format(cal.getTime()) ;
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);

			//2차
			cal.add(Calendar.YEAR, 1);  //현재 시간에 + 1년

			cyclBangDe = sdf.format(cal.getTime())+"-01-01" ;
			cyclEndDe = sdf.format(cal.getTime())+"-12-31" ;

			prgrmInfo.setSbmsnBgngDe(cyclBangDe);
			prgrmInfo.setSbmsnEndDe(cyclEndDe);
			prgrmInfo.setCycl("2");
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);

			//3차
			cal.add(Calendar.YEAR, 1);  //현재 시간에 + 1년
			cyclBangDe = sdf.format(cal.getTime())+"-01-01" ;
			cyclEndDe = sdf.format(cal.getTime())+"-12-31" ;

			prgrmInfo.setSbmsnBgngDe(cyclBangDe);
			prgrmInfo.setSbmsnEndDe(cyclEndDe);
			prgrmInfo.setCycl("3");
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);
//		}

		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

		//지정 이력 insert
		ret += dsgnPrgrmDao.insertDsgnHstry(dsgnPrgrmVo);

		//지정 프로그램 update
		asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());
		//지정상태코드 - 지정승인
		if("132101".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111111");  //상태코드 지정승인
		}
		//지정상태코드 - 지정탈락
		else if("132102".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111112");  //상태코드 지정탈락
		}

		ret += asgSrngDao.updatePrgrSttsCd(asgsysSrngVo);

		return ret;
	}

	/**
	* 지정프로그램 상세 조회
	*
	* @Title : selectDsgnAplyDtlInfo
	* @Description : 지정프로그램 상세 조회
	* @param dsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	@Override
	public DsgnPrgrmVo selectDsgnPrgrm(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnPrgrm(dsgnPrgrmVo);
	}

	/**
	* 지정내역 변경
	*
	* @Title : updateDsgnHstry
	* @Description : 지정내역 변경
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateDsgnHstry(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		int ret=0;

		//차수 조회
		List<DsgnPrgrmVo> rsltCyclList = dsgnPrgrmDao.selectOperRsltCyclList(dsgnPrgrmVo);
		if( rsltCyclList == null) {
			//운영결과 차수 생성
			DsgnPrgrmVo prgrmInfo =  dsgnPrgrmDao.selectPrgrm(dsgnPrgrmVo);

			String dateFormat = "yyyy-MM-dd";
			String cyclBangDe = "";
			String cyclEndDe ="";
			DateFormat sdf = new SimpleDateFormat(dateFormat);
			Calendar cal = Calendar.getInstance();  // 현재 시간정보 가지고오기
			sdf.format(cal.getTime());

			//1차
			prgrmInfo.setCycl("1");
			cal.getActualMinimum(Calendar.DAY_OF_YEAR);  //초일
			cyclBangDe = sdf.format(cal.getTime()) ;

			cal.getActualMaximum(Calendar.DAY_OF_YEAR);  //말일
			cyclEndDe = sdf.format(cal.getTime()) ;
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);

			//2차
			cal.add(Calendar.YEAR, 1);  //현재 시간에 + 1년
			cal.getActualMinimum(Calendar.DAY_OF_YEAR);  //초일
			cyclBangDe = sdf.format(cal.getTime()) ;

			cal.getActualMaximum(Calendar.DAY_OF_YEAR);  //말일
			cyclEndDe = sdf.format(cal.getTime()) ;

			prgrmInfo.setCycl("2");
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);

			//3차
			cal.add(Calendar.YEAR, 1);  //현재 시간에 + 1년
			cal.getActualMinimum(Calendar.DAY_OF_YEAR);  //초일
			cyclBangDe = sdf.format(cal.getTime()) ;

			cal.getActualMaximum(Calendar.DAY_OF_YEAR);  //말일
			cyclEndDe = sdf.format(cal.getTime()) ;
			prgrmInfo.setCycl("3");
			ret+=dsgnPrgrmDao.insertOperRsltCyCl(prgrmInfo);
		}


		AsgsysSrngVo asgsysSrngVo = new AsgsysSrngVo();

		//지정 이력 update
		ret += dsgnPrgrmDao.updateDsgnHstry(dsgnPrgrmVo);
		//지정 프로그램 update
		asgsysSrngVo.setPrgrmid(dsgnPrgrmVo.getPrgrmid());

		//지정상태코드 - 지정승인
		if("132101".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111111");  //상태코드 지정승인
		}
		//지정상태코드 - 지정탈락
		else if("132102".equals(dsgnPrgrmVo.getSttsCd())){
			asgsysSrngVo.setSttsCd("111112");  //상태코드 지정탈락
		}

		ret += asgSrngDao.updatePrgrSttsCd(asgsysSrngVo);
		return ret;
	}

	/**
	* 변경신청(탭) 목록 조회
	*
	* @Title : selectChgAply
	* @Description : 변경신청(탭) 목록 조회
	* @param DsgnPrgrmVo
	* @return List<DsgnPrgrmVo>
	* @throws Exception
	*/
	@Override
	public List<DsgnPrgrmVo> selectChgAplyList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectChgAplyList(dsgnPrgrmVo);

	}

	/**
	* 변경신청(탭) 등록
	*
	* @Title : insertChgAply
	* @Description : 변경신청(탭) 등록
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int insertChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao
				.insertChgAply(dsgnPrgrmVo);
	}

	/**
	* 변경신청(탭) 수정
	*
	* @Title : updateChgAply
	* @Description : 변경신청(탭) 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	public int updateChgAply(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateChgAply(dsgnPrgrmVo);
	}

	/**
	* 변경신청상세 조회
	*
	* @Title : selectChgAplyDtl
	* @Description : 변경신청상세 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return Object
	*/
	@Override
	public DsgnPrgrmVo selectChgAplyDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		 return dsgnPrgrmDao.selectChgAplyDtl(dsgnPrgrmVo);
	}

	/**
	* 변경신청상태 수정
	*
	* @Title : updateChgAplyStts
	* @Description : 변경신청상태 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateChgAplyStts(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateChgAplyStts(dsgnPrgrmVo);
	}

	/**
	 * 운영결과 목록 조회
	 *
	 * @Title : selectOperRsltList
	 * @Description : 운영결과 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public List<DsgnPrgrmVo> selectOperRsltList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectOperRsltList(dsgnPrgrmVo);
	}

	/**
	 * 운영결과 차수 조회
	 *
	 * @Title : selectOperRsltCycl
	 * @Description : 운영결과 차수 조회
	 * @param dsgnPrgrmVo
	 * @return DsgnPrgrmVo
	 * @throws Exception
	 */
	@Override
	public DsgnPrgrmVo selectOperRsltCycl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectOperRsltCycl(dsgnPrgrmVo);
	}

	/**
	 * (운영결과서)제출기간 수정
	 *
	 * @Title : updateSbmsnPrd
	 * @Description : (운영결과서)제출기간 수정
	 * @param dsgnPrgrmVo
	 * @return int
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int updateSbmsnPrd(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateSbmsnPrd(dsgnPrgrmVo);
	}

	/**
	* 운영결과 상세 조회
	*
	* @Title : selectOperRsltDetail
	* @Description : 운영결과 상세 조회
	* @param DsgnPrgrmVo
	* @return DsgnPrgrmVo
	* @throws Exception
	*/
	@Override
	public DsgnPrgrmVo selectOperRsltDetail(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectOperRsltDetail(dsgnPrgrmVo);
	}

	/**
	* 운영결과 수정
	*
	* @Title : updateOperRslt
	* @Description : 운영결과 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		int ret=0;

		if(dsgnPrgrmVo.getRsltCyclid() != null && dsgnPrgrmVo.getRsltCyclid() !=0 ) {
			ret+=dsgnPrgrmDao.updateOperRslt(dsgnPrgrmVo);
		}else {
			ret+=dsgnPrgrmDao.insertOperRslt(dsgnPrgrmVo);
		}

		List<OperPrfmncVo> prfmncLst = dsgnPrgrmVo.getOperPrfmncLst();
		dsgnPrgrmDao.deleteOperPrfmnc(dsgnPrgrmVo);

		if(prfmncLst != null && prfmncLst.size() > 0) {
			for(OperPrfmncVo vo : prfmncLst) {
				vo.setUser(dsgnPrgrmVo.getUser());
				vo.setCyclid(dsgnPrgrmVo.getCyclid());
				ret += dsgnPrgrmDao.insertOperPrfmnc(vo);
			}
		}

		return ret;
	}

	/**
	* 운영결과 삭제
	*
	* @Title : delteOperRslt
	* @Description : 운영결과 삭제
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int deleteOperRslt(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
			return dsgnPrgrmDao.deleteOperRslt(dsgnPrgrmVo);
	}

	/**
	 * 이행확인심사 목록 조회
	 *
	 * @Title : selectImplmntIdntySrngList
	 * @Description : 이행확인심사 목록 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public List<DsgnPrgrmVo> selectImplmntIdntySrngList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectImplmntIdntySrngList(dsgnPrgrmVo);
	}

	/**
	 * 이행확인심사(탭) 상세 조회
	 *
	 * @Title : selectImplmntIdntySrng
	 * @Description : 이행확인심사 상세 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public DsgnPrgrmVo selectImplmntIdntySrng(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectImplmntIdntySrng(dsgnPrgrmVo);
	}

	/**
	 * 이행확인심사 상세 조회
	 *
	 * @Title : selectImplmntIdntySrng
	 * @Description : 이행확인심사 상세 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public DsgnPrgrmVo selectImplmntIdntySrngDtl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectImplmntIdntySrngDtl(dsgnPrgrmVo);
	}



	/**
	 * 보완 요청 조회
	 *
	 * @Title : selectSplmntDmnd
	 * @Description : 보완 요청 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public DsgnPrgrmVo selectSplmntDmnd(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectSplmntDmnd(dsgnPrgrmVo);
	}

	/**
	 * 보완 계획 조회
	 *
	 * @Title : selectSplmntPlan
	 * @Description : 보완 계획 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public DsgnPrgrmVo selectSplmntPlan(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectSplmntPlan(dsgnPrgrmVo);
	}

	/**
	* 보완계획서 수정
	*
	* @Title : updateScrtyImprvPlanln
	* @Description : 보완계획서 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateScrtyImprvPlanln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.updateScrtyImprvPlanln(dsgnPrgrmVo);
	}

	/**
	 * 결과보고서 조회
	 *
	 * @Title : selectRsltRptln
	 * @Description : 결과보고서 조회
	 * @param dsgnPrgrmVo
	 * @return List<DsgnPrgrmVo>
	 * @throws Exception;
	 */
	@Override
	public DsgnPrgrmVo selectRsltRptln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception{
		return dsgnPrgrmDao.selectRsltRptln(dsgnPrgrmVo);
	}

	/**
	* 보완요청 수정
	*
	* @Title : updateSplmntImprv
	* @Description : 보완요청 수정
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateSplmntImprv(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.updateSplmntImprv (dsgnPrgrmVo);
	}

	/**
	* 결과보고서 수정
	*
	* @Title : updateRsltRptln
	* @Description : 결과보고서 수정
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int updateRsltRptln(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.updateRsltRptln (dsgnPrgrmVo);
	}

	/**
	* 지정번호 중복 조회
	*
	* @Title : selectDsgnNoDupChk
	* @Description : 지정번호 중복 조회
	* @param dsgnPrgrmVo
	* @return int
	* @throws Exception
	*/
	@Override
	public int selectDsgnNoDupChk(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnNoDupChk (dsgnPrgrmVo);

	}

	/**
	* 지정프로그램 개요 조회
	*
	* @Title : selectDsgnOutl
	* @Description : 지정프로그램 개요 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return DsgnPrgrmVo
	*/
	@Override
	public DsgnPrgrmVo selectDsgnOutl(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectDsgnOutl(dsgnPrgrmVo);
	}

	/**
	* 심사일정캘린더 목록 조회
	*
	* @Title : selectSprtgrpClndrList
	* @Description : 심사일정캘린더 목록 조회
	* @param dsgnPrgrmVo
	* @return
	* @throws Exception
	* @return List<DsgnPrgrmVo>
	*/
	@Override
	public List<DsgnPrgrmVo> selectSprtgrpClndrList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		return dsgnPrgrmDao.selectSprtgrpClndrList(dsgnPrgrmVo);
	}

	public List<DsgnPrgrmVo> selectDsgnPrgrmExcelDownList(DsgnPrgrmVo dsgnPrgrmVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void selectDsgnPrgrmExcelDownList(DsgnPrgrmVo dsgnPrgrmVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<DsgnPrgrmVo> list = null;
		String realName = "";
		DsgnPrgrmVo modelVo = null;

		realName = "jdgsSrngList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				 "차수"
				,"지정번호"
				,"프로그램명"
				,"기관명"
				,"지정상태"
				,"변경신청"
				,"운영결과"
				,"이행심사"
				,"이의신청"
				,"지정기간"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<String>();
		for(int i=0;i<titleArr.length;i++){
			titleList.add(titleArr[i]);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = dsgnPrgrmDao.selectDsgnPrgrmExcelDownList(dsgnPrgrmVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;


				/*차수*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getDsgnCycl(), ""));
				cell.setCellStyle(style);
				/*지정번호*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getDsgnNo(), ""));
				cell.setCellStyle(style);
				/*프로그램명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*지정상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*변경신청*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getChgAplyCnt(), ""));
				cell.setCellStyle(style);
				/*운영결과*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getOperRsltCnt(), ""));
				cell.setCellStyle(style);
				/*이행심사*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getImplmntIdntyCnt(), ""));
				cell.setCellStyle(style);
				/*이의신청*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getObjcInfoCnt(), ""));
				cell.setCellStyle(style);
				/*지정기간*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getDsgnPrd(), ""));
				cell.setCellStyle(style);

			}

			for(int i=0;i<titleList.size();i++){
				sheet.autoSizeColumn((short)i);
				sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
			}
		}

		ExcelUtils.excelInfoSet(response,realName);

		//엑셀 파일을 만듬
		OutputStream fileOutput = response.getOutputStream();

		workbook.write(fileOutput);
		fileOutput.flush();
		fileOutput.close();
	}

}
