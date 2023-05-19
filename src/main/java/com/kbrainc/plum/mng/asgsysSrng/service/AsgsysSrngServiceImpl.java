package com.kbrainc.plum.mng.asgsysSrng.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.error.controller.CustomErrorController;
import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.front.dsgnPrgrm.model.DsgnPrgrmVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AcbgVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.CareerVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ChklstAnsVo;
import com.kbrainc.plum.mng.asgsysSrng.model.DsgnSrngFormVo;
import com.kbrainc.plum.mng.asgsysSrng.model.EmrgcyActnPlanVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
import com.kbrainc.plum.mng.asgsysSrng.model.LdrVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
import com.kbrainc.plum.mng.asgsysSrng.model.QlfcVo;
import com.kbrainc.plum.mng.asgsysSrng.model.TchaidFcltVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.CommonUtil;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

import lombok.extern.slf4j.Slf4j;


/**
* 지정제사업관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.asgsysSrng.service
* - AsgsysSrngServiceImpl.java
* </pre>
*
* @ClassName : AsgsysSrngServiceImpl
* @Description : 지정제사업관리 서비스 구현 클래스.
* @author : kbrain
* @date : 2022. 12. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/

@Service
@Slf4j
public class AsgsysSrngServiceImpl extends PlumAbstractServiceImpl implements AsgsysSrngService{


	@Autowired
    private AsgsysSrngDao asgsysSrngDao;

	protected Logger logger = LoggerFactory.getLogger(CustomErrorController.class);

    /**
    * 지정신청 목록 조회.
    *
    * @Title       : selectDsgnAplyList
    * @Description : 지정신청 목록 조회.
    * @param memberVo AsgsysAplyVo객체
    * @return List<AsgsysSrngVo> 심사신청정보 목록
    * @throws Exception 예외
    */
    @Override
	public List<AsgsysSrngVo> selectDsgnAplyList(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectDsgnAplyList(asgsysSrngVo);
	}

	/**
	* 지정신청 상세 조회.
	*
	* @Title : getSelectDsgnAplyInfo
	* @Description : 지정신청 상세 조회.
	* @param asgsysSrngVo
	* @return AsgsysSrngVo AsgsysSrngVo객체
    * @throws Exception 예외
	*/
    @Override
	public AsgsysSrngVo selectDsgnAplyDtlInfo(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectDsgnAplyDtlInfo(asgsysSrngVo);
	}

	/**
     * 신청정보 조회
     *
     * @Title : selectAplyInfo
     * @Description : 신청정보 조회
     * @param asgsysSrngVo
     * @return AsgsysSrngVo
     * @throws Exception 예외
     */
    @Override
 	public AsgsysSrngVo selectAplyInfo(AsgsysSrngVo asgsysSrngVo) throws Exception{
    	return asgsysSrngDao.selectAplyInfo(asgsysSrngVo);
    }

	/**
	* 기관정보 조회
	*
	* @Title : selectInstInfo
	* @Description : 기관정보 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return AsgsysSrngVo
	*/
    @Override
	public AsgsysSrngVo selectInstInfo(AsgsysSrngVo asgsysSrngVo) throws Exception{
    	return asgsysSrngDao.selectInstInfo(asgsysSrngVo);
    }


	/**
	* 프로그램상태코드 조회
	*
	* @Title : selectPrgrmSttsCd
	* @Description : 프로그램상태코드 조회
	* @param asgsysSrngVo
    * @throws Exception 예외
	* @return Object
	*/
    @Override
	public String selectPrgrmSttsCd(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectPrgrmSttsCd(asgsysSrngVo);
	}

	/**
	* 프로그램상태코드 변경
	*
	* @Title : updateSttsCd
	* @Description : 프로그램상태코드 변경
	* @param asgsysSrngVo
	* @throws Exception
	* @return Map<String,Object>
	*/
	@Override
	@Transactional
	public int updatePrgrSttsCd(AsgsysSrngVo asgsysSrngVo) throws Exception {
		int updateCnt = asgsysSrngDao.updatePrgrSttsCd(asgsysSrngVo);
		return updateCnt;
	}

	/**
	* 지원단 캘린더 목록 조회
	*
	* @Title : selectSprtgrpClndrList
	* @Description : 지원단 캘린더 목록 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
	public List<AsgsysSrngVo> selectSprtgrpClndrList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSprtgrpClndrList(asgsysSrngVo);
	}

	/**
	* 지정신청목록 엑셀다운로드
	*
	* @Title : aplyExcelDownList
	* @Description : 지정신청목록 엑셀다운로드
	* @param memberVo
	* @param response
	* @param request
	* @throws Exception
	* @return void
	*/
	@Override
	public void aplyExcelDownList(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<AsgsysSrngVo> list = null;
		String realName = "";
		AsgsysSrngVo modelVo = null;

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
				"프로그램명"
				,"기관명"
				,"진행상태"
				,"신청일"
				,"심사위원심사상태"
				,"지원단심사상태"
				,"현장점검지정일시"
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

		list = asgsysSrngDao.aplyExcelDownList(asgsysSrngVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*프로그램명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*진행상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*신청일*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getAplyDe(), ""));
				cell.setCellStyle(style);
				/*심사위원심사상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*지원단심사상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*현장점검지정일시*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getVstDe(), ""));
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

	/**
	 * 총평 엑셀다운로드
	 *
	 * @Title : gnrlrvwExcelDownList
	 * @Description : 총평 엑셀다운로드
	 * @param memberVo
	 * @param response
	 * @param request
	 * @throws Exception
	 * @return void
	 */
	@Override
	public void gnrlrvwExcelDownList(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<AsgsysSrngVo> list = null;
		String realName = "";
		AsgsysSrngVo modelVo = null;

		realName = "srngGnrlrvwList.xls";

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
				"프로그램명"
				,"기관명"
				,"신청일"
				,"현장점검결과"
				,"최종심사평"
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

		//list = asgsysSrngDao.selectSprtgrpSrngListExcelDown(asgsysSrngVo);
		list = asgsysSrngDao.gnrlrvwExcelDownList(asgsysSrngVo);    //지원단 총평 엑셀 다운 조회


		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*프로그램명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*신청일*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getAplyDe(), ""));
				cell.setCellStyle(style);
				/*현장점검결과*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getSrngOpnn(), ""));
				cell.setCellStyle(style);
				/*최종 심사평*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getChklstRsltCn(), ""));
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

    /**
    * 증빙서류 파일목록 조회
    *
    * @Title : selectEvdncdcmntFileList
    * @Description : 증빙서류 파일목록 조회
    * @param fileVo
    * @throws Exception
    * @return List<FileVo>
    */
    @Override
	public List<FileVo> selectEvdncDcmntFileList(FileVo fileVo) throws Exception{
		return asgsysSrngDao.selectEvdncDcmntFileList(fileVo);
	}

	/**
	* 보완요청 목록조회
	*
	* @Title : selectSplmntDmndList
	* @Description : 보완요청 목록조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectSplmntDmndList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSplmntDmndList(asgsysSrngVo);
	}

	/**
	 * 보완요청 등록
	 *
	 * @Title : insertSplmntDmnd
	 * @Description : 보완요청 등록
	 * @param asgsysSrngVo
	 * @return int
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int insertSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception{
		int ret=0;
		ret = asgsysSrngDao.insertSplmntDmnd(asgsysSrngVo);
		ret = 0;
		ret = asgsysSrngDao.updatePrgrSttsCd(asgsysSrngVo);
		return ret;
	}

	/**
	* 보완요청 수정
	*
	* @Title : updateSplmntDmnd
	* @Description : 보완요청 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	@Override
	@Transactional
	public int updateSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception{
		int ret=0;
		ret = asgsysSrngDao.updateSplmntDmnd(asgsysSrngVo);
		ret = 0;
		ret = asgsysSrngDao.updatePrgrSttsCd(asgsysSrngVo);

		return ret;
	}

	/**
	* 보완요청 삭제
	*
	* @Title : deleteSplmntDmnd
	* @Description : 보완요청 삭제
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int deleteSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.deleteSplmntDmnd(asgsysSrngVo);
	}

	/**
	* 보완요청 조회
	*
	* @Title : selectSplmntDmnd
	* @Description : 보완요청 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo
	* @throws Exception
	*/
	@Override
	public AsgsysSrngVo selectSplmntDmnd(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSplmntDmnd(asgsysSrngVo);

	}

	/**
	* 프로그램 우수성 조회
	*
	* @Title : selectPrgrmDstnctn
	* @Description : 프로그램 우수성 조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	@Override
	public AsgsysSrngVo selectPrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectPrgrmDstnctn(asgsysSrngVo);
	}

	/**
	* 프로그램 운영일정 목록조회
	*
	* @Title : selectPrgrmDstnctn
	* @Description : 프로그램 운영일정 목록조회
	* @param prgrmSchdlVo
	* @return PrgrmSchdlVo
	* @throws Exception
	*/
	@Override
	public List<PrgrmSchdlVo> selectPrgrmSchdlList(PrgrmSchdlVo prgrmSchdlVo) throws Exception{
		return asgsysSrngDao.selectPrgrmSchdlList(prgrmSchdlVo);
	}

	/**
	* 프로그램 평가 조회
	*
	* @Title : selectPrgrmEduSbjct
	* @Description : 프로그램 평가 조회
	* @param asgsysSrngVo
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	@Override
	public AsgsysSrngVo selectPrgrmEvl(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectPrgrmEvl(asgsysSrngVo);
	}

	/**
	* 프로그램 평가 수정
	*
	* @Title : updatePrgrmEvl
	* @Description : 프로그램 평가 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
	@Override
	public int updatePrgrmEvl(AsgsysSrngVo asgsysSrngVo) throws Exception{
		AsgsysSrngVo dtlVo = asgsysSrngDao.selectPrgrmEvl(asgsysSrngVo);
		int ret=0;
		if(CommonUtil.isEmpty(dtlVo.getEvlid())){
			ret = asgsysSrngDao.insertPrgrmEvl(asgsysSrngVo);
		}else {
			ret = asgsysSrngDao.updatePrgrmEvl(asgsysSrngVo);
		}
		return ret;
	}

	/**
	* 책임개발자 목록 조회
	*
	* @Title : selectLdrList
	* @Description : 책임개발자 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectLdrList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectLdrList(asgsysSrngVo);
	}
	/**
	* 책임개발자 이력 조회
	*
	* @Title : selectSnrstfdvlprHstry
	* @Description : 책임개발자 이력 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	@Override
	public AsgsysSrngVo selectSnrstfdvlprHstry(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSnrstfdvlprHstry(asgsysSrngVo);
	}

	/**
	* 책임개발자 학력사항 목록조회
	*
	* @Title : selectSnrstfdvlprAcbgList
	* @Description : 책임개발자 학력사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectSnrstfdvlprAcbgList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSnrstfdvlprAcbgList(asgsysSrngVo);
	}

	/**
	* 책임개발자 자격사항 목록조회
	*
	* @Title : selectSnrstfdvlprQlfcList
	* @Description : 책임개발자 자격사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectSnrstfdvlprQlfcList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSnrstfdvlprQlfcList(asgsysSrngVo);
	}

	/**
	* 책임개발자 경력사항 목록조회
	*
	* @Title : selectSnrstfdvlprCareerList
	* @Description : 책임개발자 경력사항 목록조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectSnrstfdvlprCareerList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSnrstfdvlprCareerList(asgsysSrngVo);
	}


	/**
	* 지도자 자격 및 배치 등록
	*
	* @Title : insertLdrQlfcForm
	* @Description : 지도자 자격 및 배치 등록
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return int
	*/
	@Override
	@Transactional
	public int insertLdrQlfcForm(AsgsysSrngVo asgsysSrngVo) throws Exception{
		int ret=0;

		//지도자 목록
		List<LdrVo> ldrList = asgsysSrngVo.getLdrLst();

		ret += asgsysSrngDao.deleteLdrList(asgsysSrngVo);
		for(LdrVo vo : ldrList) {
			vo.setUser(asgsysSrngVo.getUser());
			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
			ret += asgsysSrngDao.insertLdr(vo);
		}

		//이력
		if(CommonUtil.isEmpty(asgsysSrngVo.getHstryid())){
			ret += asgsysSrngDao.insertSnrstfdvlprHstry(asgsysSrngVo);
		}else {
			ret += asgsysSrngDao.updateSnrstfdvlprHstry(asgsysSrngVo);
		}

		//학력 목록
		List<AcbgVo> acbgList = asgsysSrngVo.getAcbgLst();

		ret += asgsysSrngDao.deleteAcbgList(asgsysSrngVo);
		for(AcbgVo vo : acbgList) {
			vo.setUser(asgsysSrngVo.getUser());
			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
			ret += asgsysSrngDao.insertAcbg(vo);
		}

		//경력 목록
		List<CareerVo> careerList = asgsysSrngVo.getCareerLst();

		ret += asgsysSrngDao.deleteCareerList(asgsysSrngVo);
		for(CareerVo vo : careerList) {
			vo.setUser(asgsysSrngVo.getUser());
			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
			ret += asgsysSrngDao.insertCareer(vo);
		}

		//자격 목록
		List<QlfcVo> qlfcList = asgsysSrngVo.getQlfcLst();

		ret += asgsysSrngDao.deleteQlfcList(asgsysSrngVo);
		for(QlfcVo vo : qlfcList) {
			vo.setUser(asgsysSrngVo.getUser());
			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
			ret += asgsysSrngDao.insertQlfc(vo);
		}

		return ret;
	}

	/**
	* 프로그램 안전관리 조회
	*
	* @Title : selectSftyMng
	* @Description : 프로그램 안전관리 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo객체
    * @throws Exception 예외
	*/
	@Override
	public AsgsysSrngVo selectSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSftyMng(asgsysSrngVo);
	}

	/**
	* 체크리스트 form 조회
	*
	* @Title : selectAssChklstForm
	* @Description : 체크리스트 form 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return AsgsysSrngVo
	*/
	@Override
	public AsgsysSrngVo selectAssChklstForm(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectAssChklstForm(asgsysSrngVo);
	}

    /**
    * @Title : dsgnSrngMainForm
    * @Description : 심사위원심사 목록조회
    * @param AsgsysSrngVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
	@Override
	public List<AsgsysSrngVo> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectJdgsSrngList(asgsysSrngVo);
	}

	/**
	* 심사위원심사 엑셀 다운로드
	*
	* @Title : jdgsSrngListExcelDown
	* @Description : 심사위원심사 엑셀 다운로드
	* @param memberVo
	* @param response
	* @param request
	* @throws Exception
	* @return void
	*/
	@Override
	public void jdgsSrngListExcelDown(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<AsgsysSrngVo> list = null;
		String realName = "";
		AsgsysSrngVo modelVo = null;

		realName = "jdgsSrngMainList.xls";
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
				"프로그램명"
				,"심사위원"
				,"기관명"
				,"심사진행상태"
				,"배정일"
				,"숙박여부"
				,"심사일"
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

		list = asgsysSrngDao.selectJdgsSrngListExcelDown(asgsysSrngVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
			    row = sheet.createRow((i+1));
			    cellnum = 0;

			    /*프로그램명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
			    cell.setCellStyle(style);
			    /*심사위원명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getJdgsNm(), ""));
			    cell.setCellStyle(style);
				/*기관명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
			    cell.setCellStyle(style);
				/*심사진행상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsCdNm(), ""));
			    cell.setCellStyle(style);
				/*배정일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getAltmntDe(), ""));
			    cell.setCellStyle(style);
				/*숙박여부*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getStyYnStr(), ""));
			    cell.setCellStyle(style);
				/*심사일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrngDe(), ""));
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

	/**
	* 심사위원심사 상세 조회
	*
	* @Title : getSelectDsgnAplyInfo
	* @Description : 심사위원심사 상세 조회
	* @param asgsysSrngVo
	* @return AsgsysSrngVo객체
    * @throws Exception 예외
	*/
    @Override
	public AsgsysSrngVo selectJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectJdgsSrngDetail(asgsysSrngVo);
	}

	/**
	* 심사양식 목록 조회
	*
	* @Title : selectDsgnSrgnFormList
	* @Description : 심사양식 목록 조회
	* @param DsgnSrngFormVO
	* @return
	* @throws Exception
	* @return List<DsgnSrngFormVO>
	*/
	public List<DsgnSrngFormVo> selectDsgnSrgnFormList(DsgnSrngFormVo dsgnSrngFormVo) throws Exception{
		return asgsysSrngDao.selectDsgnSrgnFormList(dsgnSrngFormVo);
	}

    /**
     * 심사위원심사 수정
     *
     * @Title       : insertJdgsSrngDetail
     * @Description : 심사위원심사 수정
     * @param memberVo MemberVo , TeacherVo TeacherVo객체
     * @param asgsysSrngVo
     * @return int
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int updateJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret = 0;

    	List<DsgnSrngFormVo> dsgnSrngFormLst = asgsysSrngVo.getDsgnSrngFormLst();

    	asgsysSrngDao.deleteJdgsSrngAns(dsgnSrngFormLst.get(0));
    	asgsysSrngDao.deleteJdgsSrngOrdrAns(dsgnSrngFormLst.get(0));

    	if( 0 < dsgnSrngFormLst.size()) {

    		for(DsgnSrngFormVo dsgnSrngFormVo : dsgnSrngFormLst) {
    			dsgnSrngFormVo.setUser(asgsysSrngVo.getUser());
				ret += asgsysSrngDao.insertJdgsSrngAns(dsgnSrngFormVo);    //심사 답변 저장
				ret += asgsysSrngDao.insertJdgsSrngOrdrAns(dsgnSrngFormVo);    //심사 순서 답변 저장
    		}
    	}

    	ret += asgsysSrngDao.updateJdgsSrngDetail(asgsysSrngVo);    //심사위원심사제출 수정

        return ret;
	}

    /**
    * 심사위원심사 등록
    *
    * @Title : insertJdgsSrngDetail
    * @Description : 심사위원심사 등록
    * @param asgsysSrngVo
    * @return int
    * @throws Exception 예외
    */
    @Override
    @Transactional
    public int insertJdgsSrngDetail(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret = 0;
    	AsgsysSrngVo sbmsnVo =null;

    	ret += asgsysSrngDao.insertJdgsSrngDetail(asgsysSrngVo);

    	sbmsnVo = asgsysSrngDao.selectJdgsSrngDetail(asgsysSrngVo);

    	List<DsgnSrngFormVo> dsgnSrngFormLst = asgsysSrngVo.getDsgnSrngFormLst();

    	if( 0 < dsgnSrngFormLst.size()) {
    		for(DsgnSrngFormVo dsgnSrngFormVo : dsgnSrngFormLst) {
    			dsgnSrngFormVo.setUser(asgsysSrngVo.getUser());
    			dsgnSrngFormVo.setSbmsnid(sbmsnVo.getSbmsnid());
				ret += asgsysSrngDao.insertJdgsSrngAns(dsgnSrngFormVo);    //심사 답변 저장
				ret += asgsysSrngDao.insertJdgsSrngOrdrAns(dsgnSrngFormVo);    //심사 순서 답변 저장
    		}
    	}

        return ret;
	}

    /**
    * 안전관리 수정
    *
    * @Title : updateSftyMng
    * @Description : 안전관리 수정
    * @param asgsysSrngVo
    * @return int
    * @throws Exception 예외
    */
    @Override
    @Transactional
	public int updateSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception{
    	int ret = 0;
    	int allSum=0;
    	int ordrAns=1;
    	int ordr=1;
    	String preSeCd="";
    	AsgsysSrngVo chkInfo = null;
    	List<AsgsysSrngVo> qitemList = null;
    	List<AsgsysSrngVo> ansList = null;
    	AsgsysSrngVo sbmsnInfo = new AsgsysSrngVo();
    	ChklstAnsVo delVo = null;


    	if(null != asgsysSrngVo.getSftyMngId() && 0 != asgsysSrngVo.getSftyMngId()){
    		ret = asgsysSrngDao.updateSftyMng(asgsysSrngVo);
    	}else {
    		ret = asgsysSrngDao.insertSftyMng(asgsysSrngVo);
    	}

    	//사전관리인증여부 Y
    	if("Y".equals(asgsysSrngVo.getBfrCertYn())) {

    		chkInfo = asgsysSrngDao.selectChkListInfo(asgsysSrngVo);

    		//체크리스트 제출 등록
    		asgsysSrngVo.setSbmsnSttsCd("128102");    /*제출상태 제출*/
    		asgsysSrngVo.setChklstid(chkInfo.getChklstid());    /*체크리스트id*/

    		if(chkInfo.getSbmsnid() == null || chkInfo.getSbmsnid() == 0){
    			ret += asgsysSrngDao.insertChklstSbmsn(asgsysSrngVo);
    		}

    		sbmsnInfo = asgsysSrngDao.selectChkListSbmsn(asgsysSrngVo);

    		asgsysSrngVo.setSbmsnid(sbmsnInfo.getSbmsnid());

    		//delete 자가진단 답변
    		delVo = new ChklstAnsVo();
    		delVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    		asgsysSrngDao.deleteChklstAns(delVo);
    		//delete 자가진단 답변 순서
    		asgsysSrngDao.deleteChklstSeOrdrAnsList(delVo);

    		qitemList = asgsysSrngDao.selectQitemList(asgsysSrngVo);
    		for(AsgsysSrngVo vo :  qitemList) {

    			if("3".equals(vo.getLv())) {

    				ChklstAnsVo ansVo = new ChklstAnsVo();
    				ansVo.setUser(asgsysSrngVo.getUser());
    				ansVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    				ansVo.setQitemid(vo.getQitemid());
    				ansVo.setSeCd(vo.getDp2());
    				ansVo.setCn(vo.getCn());
    				ansVo.setIdntyMttr(vo.getCn());
    				ansVo.setScr(Integer.parseInt(vo.getAltm()));
    				allSum+=ansVo.getScr();
    				ansVo.setOrdr(ordrAns);
    				ordrAns++;
    				//답변등록
    				ret += asgsysSrngDao.insertChklstAns(ansVo);

    				ChklstAnsVo ordrAnsVo = new ChklstAnsVo();
    				if(!vo.getDp2().equals(preSeCd)) {
    					ordrAnsVo.setUser(asgsysSrngVo.getUser());
    					ordrAnsVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    					ordrAnsVo.setSeCd(vo.getDp2());
    					ordrAnsVo.setOrdr(ordr);
    					ordrAnsVo.setUser(vo.getUser());
    					//답변순서 등록
    					ret+=asgsysSrngDao.insertChklstSeOrdrAnsList(ordrAnsVo);
    					ordr++;
    					preSeCd=ordrAnsVo.getSeCd();
    				}
    			}
    		}

    		//제출 점수 update
    		asgsysSrngVo.setScr(allSum);
    		ret+=asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);
    	}

    	return ret;
	}

    /**
     * 안전관리 등록
     *
     * @Title : insertSftyMng
     * @Description : 안전관리 등록
     * @param asgsysSrngVo
     * @return int
     * @throws Exception 예외
     */
    @Override
    @Transactional
    public int insertSftyMng(AsgsysSrngVo asgsysSrngVo) throws Exception{
    	int ret = 0;
    	int allSum=0;
    	int ordrAns=1;
    	int ordr=1;
    	String preSeCd="";
    	AsgsysSrngVo chkInfo = null;
    	List<AsgsysSrngVo> qitemList = null;
    	List<AsgsysSrngVo> ansList = null;
    	AsgsysSrngVo sbmsnInfo = new AsgsysSrngVo();
    	ChklstAnsVo delVo = null;


    	if(null != asgsysSrngVo.getSftyMngId() && 0 != asgsysSrngVo.getSftyMngId()){
    		ret = asgsysSrngDao.updateSftyMng(asgsysSrngVo);
    	}else {
    		ret = asgsysSrngDao.insertSftyMng(asgsysSrngVo);
    	}

    	//사전관리인증여부 Y
    	if("Y".equals(asgsysSrngVo.getBfrCertYn())) {

    		chkInfo = asgsysSrngDao.selectChkListInfo(asgsysSrngVo);

    		//체크리스트 제출 등록
    		asgsysSrngVo.setSbmsnSttsCd("128102");    /*제출상태 제출*/
    		asgsysSrngVo.setChklstid(chkInfo.getChklstid());    /*체크리스트id*/

    		if(chkInfo.getSbmsnid() == null || chkInfo.getSbmsnid() == 0){
    			ret += asgsysSrngDao.insertChklstSbmsn(asgsysSrngVo);
    		}

    		sbmsnInfo = asgsysSrngDao.selectChkListSbmsn(asgsysSrngVo);

    		asgsysSrngVo.setSbmsnid(sbmsnInfo.getSbmsnid());

    		//delete 자가진단 답변
    		delVo = new ChklstAnsVo();
    		delVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    		asgsysSrngDao.deleteChklstAns(delVo);
    		//delete 자가진단 답변 순서
    		asgsysSrngDao.deleteChklstSeOrdrAnsList(delVo);

    		qitemList = asgsysSrngDao.selectQitemList(asgsysSrngVo);
    		for(AsgsysSrngVo vo :  qitemList) {

    			if("3".equals(vo.getLv())) {

    				ChklstAnsVo ansVo = new ChklstAnsVo();
    				ansVo.setUser(asgsysSrngVo.getUser());
    				ansVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    				ansVo.setQitemid(vo.getQitemid());
    				ansVo.setSeCd(vo.getDp2());
    				ansVo.setCn(vo.getCn());
    				ansVo.setIdntyMttr(vo.getCn());
    				ansVo.setScr(Integer.parseInt(vo.getAltm()));
    				allSum+=ansVo.getScr();
    				ansVo.setOrdr(ordrAns);
    				ordrAns++;
    				//답변등록
    				ret += asgsysSrngDao.insertChklstAns(ansVo);

    				ChklstAnsVo ordrAnsVo = new ChklstAnsVo();
    				if(!vo.getDp2().equals(preSeCd)) {
    					ordrAnsVo.setUser(asgsysSrngVo.getUser());
    					ordrAnsVo.setSbmsnid(asgsysSrngVo.getSbmsnid());
    					ordrAnsVo.setSeCd(vo.getDp2());
    					ordrAnsVo.setOrdr(ordr);
    					ordrAnsVo.setUser(vo.getUser());
    					//답변순서 등록
    					ret+=asgsysSrngDao.insertChklstSeOrdrAnsList(ordrAnsVo);
    					ordr++;
    					preSeCd=ordrAnsVo.getSeCd();
    				}
    			}
    		}

    		//제출 점수 update
    		asgsysSrngVo.setScr(allSum);
    		ret+=asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);
    	}

    	return ret;
    }

    /**
     * 프로그램운영 관리 조회
     *
     * @Title : selectPrgrmOperMng
     * @Description : 프로그램운영 관리 조회
     * @param asgsysSrngVo
     * @return AsgsysSrngVo객체
     * @throws Exception 예외
     */
    @Override
	public AsgsysSrngVo selectPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	return asgsysSrngDao.selectPrgrmOperMng(asgsysSrngVo);
	}

    /**
    * 교구 및 시설목록 조회
    *
    * @Title       : selectDsgnAplyList
    * @Description : 교구 및 시설목록 조회
    * @param memberVo AsgsysAplyVo객체
    * @return List<AsgsysSrngVo>
    * @throws Exception 예외
    */
    @Override
    public List<TchaidFcltVo> selectTchaidFcltList(TchaidFcltVo tchaidFcltVo) throws Exception {
    	return asgsysSrngDao.selectTchaidFcltList(tchaidFcltVo);
	}

    /**
    * 프로그램운영관리 수정
    *
    * @Title : updatePrgrmOperMng
    * @Description : 프로그램운영관리 수정
    * @param asgsysSrngVo
    * @return int
    * @throws Exception
    */
    @Override
    @Transactional
	public int updatePrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret= 0;

    	List<ExpndArtclVo> expndLst = asgsysSrngVo.getExpndArtclLst();
    	List<TchaidFcltVo> fcltLst  = asgsysSrngVo.getTchaidFcltLst();

    	//지출항목 목록 저장
    	ret+=asgsysSrngDao.deleteExpndArtcl(asgsysSrngVo);

    	if(expndLst !=null && expndLst.size() > 0) {
    		for(ExpndArtclVo vo : expndLst) {
    			vo.setUser(asgsysSrngVo.getUser());
    			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
    			ret+=asgsysSrngDao.insertExpndArtcl(vo);
    		}
    	}

    	//교구 및 시설 목록 저장
    	asgsysSrngDao.deleteTchaidFclt(asgsysSrngVo);

    	if(fcltLst !=null && fcltLst.size() >0) {
	    	for(TchaidFcltVo vo : fcltLst) {
	    		vo.setUser(asgsysSrngVo.getUser());
	    		vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
	    		ret+=asgsysSrngDao.insertTchaidFclt(vo);
	    	}
    	}
    	ret = asgsysSrngDao.updatePrgrmOperMng(asgsysSrngVo);

		return ret;
	}

    /**
    * 프로그램운영관리 등록
    *
    * @Title : insertPrgrmOperMng
    * @Description : 프로그램운영관리 등록
    * @param asgsysSrngVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
	public int insertPrgrmOperMng(AsgsysSrngVo asgsysSrngVo) throws Exception{

    	int ret= 0;

    	List<ExpndArtclVo> expndLst = asgsysSrngVo.getExpndArtclLst();
    	List<TchaidFcltVo> fcltLst  = asgsysSrngVo.getTchaidFcltLst();

    	if(CommonUtil.isEmpty(asgsysSrngVo.getOperMngPrgrmid())) {
    		ret +=asgsysSrngDao.insertPrgrmOperMng(asgsysSrngVo);
    	}else {
    		ret += asgsysSrngDao.updatePrgrmOperMng(asgsysSrngVo);
    	}

    	//지출항목 목록 저장
    	asgsysSrngDao.deleteExpndArtcl(asgsysSrngVo);

    	if(expndLst !=null && expndLst.size() > 0) {
    		for(ExpndArtclVo vo : expndLst) {
    			vo.setUser(asgsysSrngVo.getUser());
    			vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
    			ret+=asgsysSrngDao.insertExpndArtcl(vo);
    		}
    	}

    	//교구 및 시설 목록 저장
    	asgsysSrngDao.deleteTchaidFclt(asgsysSrngVo);

    	if(fcltLst !=null && fcltLst.size() >0) {
	    	for(TchaidFcltVo vo : fcltLst) {
	    		vo.setUser(asgsysSrngVo.getUser());
	    		vo.setPrgrmid(asgsysSrngVo.getPrgrmid());
	    		ret+=asgsysSrngDao.insertTchaidFclt(vo);
	    	}
    	}

		return ret;
	}

    /**
	* 지출항목 목록 조회
	*
	* @Title : selectExpndArtclList
	* @Description : 지출항목 목록 조회
	* @param ExpndArtclVo
	* @return
	* @throws Exception
	* @return List<ExpndArtclVo>
	*/
    @Override
	public List<ExpndArtclVo> selectExpndArtclList(ExpndArtclVo expndArtclVo) throws Exception{
		return asgsysSrngDao.selectExpndArtclList(expndArtclVo);
	}

    /**
    * 지원단심사 목록 조회
    *
    * @Title : selectSprtgrpSrngList
    * @Description : 지원단심사 목록 조회
    * @param asgsysSrngVo
    * @return List<AsgsysSrngVo>
    * @throws Exception
    */
    @Override
    public List<AsgsysSrngVo> selectSprtgrpSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	return asgsysSrngDao.selectSprtgrpSrngList(asgsysSrngVo);
	}

    @Override
	public void selectSprtgrpSrngListExcelDown(AsgsysSrngVo asgsysSrngVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<AsgsysSrngVo> list = null;
		String realName = "";
		AsgsysSrngVo modelVo = null;

		realName = "jdgsSrngMainList.xls";
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
				 "프로그램명"
				,"기관명"
				,"심사진행상태"
				,"배정일"
				,"숙박여부"
				,"현장점검 지정 일시"
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

		list = asgsysSrngDao.selectSprtgrpSrngListExcelDown(asgsysSrngVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
			    row = sheet.createRow((i+1));
			    cellnum = 0;

			    /*프로그램명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
			    cell.setCellStyle(style);
				/*기관명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
			    cell.setCellStyle(style);
				/*심사진행상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsCdNm(), ""));
			    cell.setCellStyle(style);
				/*배정일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getAltmntDe(), ""));
			    cell.setCellStyle(style);
				/*숙박여부*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getStyYnStr(), ""));
			    cell.setCellStyle(style);
				/*현장점검일시*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getVstDt(), ""));
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


    /**
    * 지원단심사 상세 조회
    *
    * @Title : selectSprtgrpSrng
    * @Description : 지원단심사 상세 조회
    * @param asgsysSrngVo
    * @return AsgsysSrngVo
    * @throws Exception
    */
    @Override
    public AsgsysSrngVo selectSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectSprtgrpSrng(asgsysSrngVo);
	}

	/**
	* 지원단심사 체크리스트 조회
	*
	* @Title : selectSprtgrpSrngList
	* @Description : 지원단심사 체크리스트 조회
	* @param asgsysSrngVo
	* @return List<AsgsysSrngVo>
	* @throws Exception
	*/
    @Override
	public List<AsgsysSrngVo> selectCheckList(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectCheckList(asgsysSrngVo);
	}

    @Override
    @Transactional
    public int updateAssChklst(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret=0;
    	String preSeCd="";
    	int ordrAns=1;
    	ChklstAnsVo ordrAnsVo = new ChklstAnsVo();

    	if (asgsysSrngVo.getSbmsnid() != null && !asgsysSrngVo.getSbmsnid().equals(0)) {
    		//체크리스트 제출 수정
    		ret = asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);

    	}else {
    		//체크리스트 제출 등록
    		ret = asgsysSrngDao.insertChklstSbmsn(asgsysSrngVo);
    	}

    	AsgsysSrngVo sbmsnInfo = asgsysSrngDao.selectChkListSbmsn(asgsysSrngVo);

    	//체크리스트 답변 수정
    	List<ChklstAnsVo> lst = asgsysSrngVo.getAnsLst();

    	ordrAnsVo.setSbmsnid(sbmsnInfo.getSbmsnid());
    	asgsysSrngDao.deleteChklstSeOrdrAnsList(ordrAnsVo);

    	for(ChklstAnsVo vo : lst) {

    		vo.setUser(asgsysSrngVo.getUser());
    		vo.setSbmsnid(sbmsnInfo.getSbmsnid());

    		if(1 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    			ret += asgsysSrngDao.updateChklstAns(vo);
    		}else if(0 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    			ret += asgsysSrngDao.insertChklstAns(vo);
    		}

    		if(!vo.getSeCd().equals(preSeCd)) {
    			ordrAnsVo.setSbmsnid(vo.getSbmsnid());
    			ordrAnsVo.setSeCd(vo.getSeCd());
    			ordrAnsVo.setOrdr(ordrAns);
    			ordrAnsVo.setUser(vo.getUser());
    			ret+=asgsysSrngDao.insertChklstSeOrdrAnsList(ordrAnsVo);
    			ordrAns++;
    			preSeCd=vo.getSeCd();
    		}
    	}

    	//지정프로그램 상태코드 수정 : 신청완료
		asgsysSrngVo.setSttsCd("111102");
		ret+=asgsysSrngDao.updatePrgrSttsCd(asgsysSrngVo);

		return ret;
	}

    /**
    * 지원단심사 등록
    *
    * @Title : insertSprtgrpSrng
    * @Description : 지원단심사 등록
    * @param asgsysSrngVo
    * @return int
    * @throws Exception
    */
    @Override
    @Transactional
	public int insertSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception {

    	int ret=0;
    	String preSeCd="";
    	int ordrAns=1;
    	ChklstAnsVo ordrAnsVo = new ChklstAnsVo();

    	//지원단심사 수정
    	ret += asgsysSrngDao.updateSprtgrpOpnn(asgsysSrngVo);

    	if (asgsysSrngVo.getSbmsnid() != null && !asgsysSrngVo.getSbmsnid().equals(0)) {
    		//체크리스트 제출 수정
    		ret = asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);
    	}else {
    		//체크리스트 제출 등록
    		ret = asgsysSrngDao.insertChklstSbmsn(asgsysSrngVo);
    	}

    	AsgsysSrngVo sbmsnInfo = asgsysSrngDao.selectChkListSbmsn(asgsysSrngVo);

    	//체크리스트 답변 수정
    	List<ChklstAnsVo> lst = asgsysSrngVo.getAnsLst();

    	//체크리스트 답변 순서 삭제
    	ordrAnsVo.setSbmsnid(sbmsnInfo.getSbmsnid());
    	asgsysSrngDao.deleteChklstSeOrdrAnsList(ordrAnsVo);

    	if(lst.size() > 0 ) {

    		for(ChklstAnsVo vo : lst) {

    			vo.setUser(asgsysSrngVo.getUser());
    			vo.setSbmsnid(sbmsnInfo.getSbmsnid());

    			if(1 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    				ret += asgsysSrngDao.updateChklstAns(vo);
    			}else if(0 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    				ret += asgsysSrngDao.insertChklstAns(vo);
    			}
    			//체크리스트 답변 순서 등록
    			if(!vo.getSeCd().equals(preSeCd)) {
        			ordrAnsVo.setSbmsnid(sbmsnInfo.getSbmsnid());
        			ordrAnsVo.setSeCd(vo.getSeCd());
        			ordrAnsVo.setOrdr(ordrAns);
        			ordrAnsVo.setUser(vo.getUser());
        			ret+=asgsysSrngDao.insertChklstSeOrdrAnsList(ordrAnsVo);
        			ordrAns++;
        			preSeCd=vo.getSeCd();
        		}
    		}
    	}

		return ret;
	}

    /**
     * 지원단심사 수정
     *
     * @Title : updateSprtgrpSrng
     * @Description : 지원단심사 등록
     * @param asgsysSrngVo
     * @return int
     * @throws Exception
     */
    @Override
    @Transactional
    public int updateSprtgrpSrng(AsgsysSrngVo asgsysSrngVo) throws Exception {

    	int ret=0;

    	//지원단심사 수정
    	ret += asgsysSrngDao.updateSprtgrpOpnn(asgsysSrngVo);

    	//체크리스트 제출 저장
    	ret += asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);

    	//체크리스트 답변 수정
    	List<ChklstAnsVo> lst = asgsysSrngVo.getAnsLst();
    	if(lst.size() > 0 ) {
    		//체크리스트 답변 순서 삭제
    		//ret += asgsysSrngDao.deleteChklstSeOrdrAnsList(lst.get(0)); todo

	    	for(ChklstAnsVo vo : lst) {

	    		vo.setUser(asgsysSrngVo.getUser());

	    		if(1 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
	    			ret += asgsysSrngDao.updateChklstAns(vo);
	    		}else if(0 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
	    			ret += asgsysSrngDao.insertChklstAns(vo);
	    		}

	    		//체크리스트 답변 순서 등록 todo
	    		//ret += asgsysSrngDao.insertChklstSeOrdrAnsList(vo);

	    	}

    	}

    	return ret;
    }

    /**
    * 담당자(지원단) 목록조회
    *
    * @Title : selectPicList
    * @Description : 담당자(지원단) 목록조회
    * @param asgsysSrngVo
    * @return List<AsgsysSrngVo>
    * @throws Exception
    */
    @Override
	public List<AsgsysSrngVo> selectSprtgrpPicList(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	return asgsysSrngDao.selectSprtgrpPicList(asgsysSrngVo);
	}

    /**
    * 담당자(심사위원) 목록조회
    *
    * @Title : selectPicList
    * @Description : 담당자(심사위원) 목록조회
    * @param asgsysSrngVo
    * @return List<AsgsysSrngVo>
    * @throws Exception
    */
    @Override
	public List<AsgsysSrngVo> selectjdgsPicList(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	return asgsysSrngDao.selectjdgsPicList(asgsysSrngVo);
	}

	/**
	* 프로그램우수성 등록
	*
	* @Title : insertPrgrmDstnctn
	* @Description : 프로그램우수성 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception
	*/
    @Override
    @Transactional
	public int insertPrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret=0;
    	Integer chklstid =null;
    	AsgsysSrngVo assPrgrmVo =null;

    	//숙박여부,운영형태코드로 체크리스트 가 정해진경우
    	if(null != asgsysSrngVo.getChkOperFrmCd()){
    		chklstid = asgsysSrngDao.getCheckListId(asgsysSrngVo);
    	}
    	if(null != chklstid ) {
    		asgsysSrngVo.setChklstid(chklstid);
    	}

    	//PRGRMID 없는 경우
    	if(asgsysSrngVo.getPrgrmid()==null || asgsysSrngVo.getPrgrmid()==0) {
    		//ASS프로그램 등록
    		ret+=asgsysSrngDao.insertAssPrgrm(asgsysSrngVo);
    	}else {
        	//ASS프로그램 수정
        	assPrgrmVo = asgsysSrngDao.selectPrgrm(asgsysSrngVo);
        	assPrgrmVo.setChklstid(chklstid);

        	assPrgrmVo.setPrgrmNm(asgsysSrngVo.getPrgrmNm());
        	assPrgrmVo.setCnsltngPrgrsYn(asgsysSrngVo.getCnsltngPrgrsYn());
        	assPrgrmVo.setCnsltngid(asgsysSrngVo.getCnsltngid());
        	ret += asgsysSrngDao.updatePrgrm(assPrgrmVo);
    	}


    	//프로그램 우수성 등록
    	ret += asgsysSrngDao.insertPrgrmDstnctn(asgsysSrngVo);

    	//프로그램_일정 저장*/
    	List<PrgrmSchdlVo> schdLst = asgsysSrngVo.getPrgrmSchdlLst();

    	if( schdLst != null && 0 < schdLst.size()) {

    		asgsysSrngDao.deletePrgrmSchdl(schdLst.get(0));

    		for(PrgrmSchdlVo prgrmSchdlVo : schdLst) {

    			prgrmSchdlVo.setUser(asgsysSrngVo.getUser());
    			prgrmSchdlVo.setPrgrmid(asgsysSrngVo.getPrgrmid());
    			ret += asgsysSrngDao.insertPrgrmSchdl(prgrmSchdlVo);
    		}
    	}

    	//비상조치계획 저장*/
    	List<EmrgcyActnPlanVo> planLst = asgsysSrngVo.getEmrgcyActnPlanLst();

    	if( planLst != null && 0 < planLst.size()) {
    		asgsysSrngDao.deleteEmrgcyActnPlan(planLst.get(0));

    		for(EmrgcyActnPlanVo emrgcyActnPlanVo : planLst) {

    			emrgcyActnPlanVo.setUser(asgsysSrngVo.getUser());
    			emrgcyActnPlanVo.setPrgrmid(asgsysSrngVo.getPrgrmid());
    			ret += asgsysSrngDao.insertEmrgcyActnPlan(emrgcyActnPlanVo);
    		}
    	}

    	//교육주제 저장
    	if(CommonUtil.isNotEmpty(asgsysSrngVo.getEduSbjctCdLst())){

    		String [] eduSbjctCdArr = asgsysSrngVo.getEduSbjctCdLst().split(",");

    		asgsysSrngDao.deleteEduSbjct(asgsysSrngVo);

    		ret += asgsysSrngDao.insertEduSbjct(asgsysSrngVo, eduSbjctCdArr, asgsysSrngVo.getUser());
    	}

		return ret;
	}

	/**
	* 비상조치계획 목록조회
	*
	* @Title : selectEmrgcyActnPlanList
	* @Description : 비상조치계획 삭제
	* @param emrgcyActnPlanVo
	* @return int
	* @throws Exception
	*/
    @Override
	public List<EmrgcyActnPlanVo> selectEmrgcyActnPlanList(EmrgcyActnPlanVo emrgcyActnPlanVo) throws Exception{
		return asgsysSrngDao.selectEmrgcyActnPlanList(emrgcyActnPlanVo);
	}

    /**
     * 프로그램우수성 수정
     *
     * @Title : updatePrgrmDstnctn
     * @Description : 프로그램우수성 수정
     * @param asgsysSrngVo
     * @return int
     * @throws Exception
     */
    @Override
    @Transactional
    public int updatePrgrmDstnctn(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret=0;
    	Integer chklstid=null;
    	AsgsysSrngVo assPrgrmVo = null;
    	//프로그램_일정 저장*/
    	List<PrgrmSchdlVo> schdLst = asgsysSrngVo.getPrgrmSchdlLst();

    	//지정프로그램 수정

    	assPrgrmVo = asgsysSrngDao.selectPrgrm(asgsysSrngVo);

    	//숙박여부,운영형태코드로 체크리스트 가 정해진경우

    	if(null != asgsysSrngVo.getChkOperFrmCd()){
    		chklstid = asgsysSrngDao.getCheckListId(asgsysSrngVo);
    	}
    	if(null != chklstid ) {
    		assPrgrmVo.setChklstid(chklstid);
    	}

    	assPrgrmVo.setPrgrmNm(asgsysSrngVo.getPrgrmNm());
    	assPrgrmVo.setCnsltngPrgrsYn(asgsysSrngVo.getCnsltngPrgrsYn());
    	assPrgrmVo.setCnsltngKndCd(asgsysSrngVo.getCnsltngKndCd());
    	assPrgrmVo.setCnsltngid(asgsysSrngVo.getCnsltngid());
    	ret += asgsysSrngDao.updatePrgrm(assPrgrmVo);

    	if( schdLst != null && 0 < schdLst.size()) {

    		asgsysSrngDao.deletePrgrmSchdl(schdLst.get(0));

    		for(PrgrmSchdlVo prgrmSchdlVo : schdLst) {

    			prgrmSchdlVo.setUser(asgsysSrngVo.getUser());
    			ret += asgsysSrngDao.insertPrgrmSchdl(prgrmSchdlVo);
    		}
    	}

    	List<EmrgcyActnPlanVo> planLst = asgsysSrngVo.getEmrgcyActnPlanLst();

    	if( planLst != null && 0 < planLst.size()) {

    		asgsysSrngDao.deleteEmrgcyActnPlan(planLst.get(0));

    		for(EmrgcyActnPlanVo emrgcyActnPlanVo : planLst) {

    			emrgcyActnPlanVo.setUser(asgsysSrngVo.getUser());
    			ret += asgsysSrngDao.insertEmrgcyActnPlan(emrgcyActnPlanVo);
    		}
    	}

    	//교육주제 저장
    	if(CommonUtil.isNotEmpty(asgsysSrngVo.getEduSbjctCdLst())){

    		String [] eduSbjctCdArr = asgsysSrngVo.getEduSbjctCdLst().split(",");

    		asgsysSrngDao.deleteEduSbjct(asgsysSrngVo);

    		ret += asgsysSrngDao.insertEduSbjct(asgsysSrngVo, eduSbjctCdArr, asgsysSrngVo.getUser());
    	}
    	// 프로그램 우수성 수정
    	ret += asgsysSrngDao.updatePrgrmDstnctn(asgsysSrngVo);

    	return ret;
    }

    /**
    * 교육사진파일그룹아이디 수정
    *
    * @Title : updateEduPhotoFilegrpid
    * @Description : 교육사진파일그룹아이디 수정
    * @param asgsysSrngVo
    * @return
    * @return int
    */
    @Override
    @Transactional
    public int updateEduPhotoFilegrpid(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.updateEduPhotoFilegrpid(asgsysSrngVo);
	}

    /**
    * 회원목록 조회
    *
    * @Title : selectMbrList
    * @Description : 회원목록 조회
    * @param asgsysSrngVo
    * @return List<MemberVo>
    * @throws Exception
    */
    @Override
	public List<AsgsysSrngVo> selectMbrList(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectMbrList(asgsysSrngVo);

	}

    /**
    * 담당자 변경
    *
    * @Title : updateMbr
    * @Description : 담당자 변경
    * @param asgsysSrngVo
    * @return int
    * @throws Exception
    */
    @Override
    @Transactional
	public int updateMbr(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.updateMbr(asgsysSrngVo);
	}

    /**
     * 담당자 배정 삭제
     *
     * @Title : deletePicInfo
     * @Description : 담당자 삭제
     * @param asgsysSrngVo
     * @return int
     * @throws Exception
     */
     @Override
     @Transactional
	public int deletePicInfo(AsgsysSrngVo asgsysSrngVo) throws Exception{
    	 int ret=0;

    	 if("jdgs".equals(asgsysSrngVo.getMode())) ret = asgsysSrngDao.deletePicJdgs(asgsysSrngVo);
    	 if("sprtgrp".equals( asgsysSrngVo.getMode())) ret = asgsysSrngDao.deletePicSprtgrp(asgsysSrngVo);

    	 return ret;
	}

    /**
    * 담당자 배정 등록
    *
    * @Title : insertPicInfo
    * @Description : 담당자 배정 등록
    * @param asgsysSrngVo
    * @return int
    * @throws Exception
    */
    @Override
    @Transactional
	public int insertPicInfo(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret=0;

    	if("jdgs".equals(asgsysSrngVo.getMode())) {
    		List<String> tokens = Arrays.asList(asgsysSrngVo.getJdgsid().split("\\s*,\\s*"));

    		for(int i=0; i < tokens.size(); i++) {
    			asgsysSrngVo.setJdgsid(tokens.get(i));
    			asgsysSrngVo.setSrngSttsCd("114102");   //배정전->심사전
    			ret = asgsysSrngDao.insertPicJdgs(asgsysSrngVo);
    		}
    	}

    	if("sprtgrp".equals( asgsysSrngVo.getMode())) {

    		asgsysSrngVo.setSrngSttsCd("114102");    //배정전->심사전
    		ret = asgsysSrngDao.insertPicSprtgrp(asgsysSrngVo);


    		//이전 지원단id삭제
    		if(CommonUtil.isNotEmpty(asgsysSrngVo.getBfrSprtgrpid())) {
    			ret=0;
    			asgsysSrngVo.setSprtgrpid(asgsysSrngVo.getBfrSprtgrpid());
    			ret = asgsysSrngDao.deletePicSprtgrp(asgsysSrngVo);
    		}
    	}

    	return ret;
	}

    /**
    * 심사점수 목록 조회
    *
    * @Title : selectSrngScrList
    * @Description : 심사점수 목록조회
    * @param asgsysSrngVo
    * @return List<AsgsysSrngVo>
    * @throws Exception
    */
    @Override
	public List<AsgsysSrngVo> selectSrngScrList(AsgsysSrngVo asgsysSrngVo) throws Exception{

		return asgsysSrngDao.selectSrngScrList(asgsysSrngVo);
	}

    /**
     * 심사점수 목록 헤더 조회
     *
     * @Title : selectSrngScrList
     * @Description : 심사점수 목록조회
     * @param asgsysSrngVo
     * @return List<AsgsysSrngVo>
     * @throws Exception
     */
    @Override
    public AsgsysSrngVo selectSrngScrHeader(AsgsysSrngVo asgsysSrngVo) throws Exception{

    	return asgsysSrngDao.selectSrngScrHeader(asgsysSrngVo);
    }

	/**
	* 심사양식 목록 조회
	*
	* @Title : selectSrngFormQitemList
	* @Description : 심사양식 목록 조회
	* @param dsgnSrngFormVo
	* @return
	* @throws Exception
	* @return List<DsgnSrngFormVo>
	*/
    @Override
	public List<DsgnSrngFormVo> selectSrngFormQitemList(DsgnSrngFormVo dsgnSrngFormVo) throws Exception{
		return asgsysSrngDao.selectSrngFormQitemList(dsgnSrngFormVo);
	}

    @Override
	public List<AsgsysSrngVo> selectSrngFormList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSrngFormList(asgsysSrngVo);
	}

	/**
	* 전문가(심사위원) 목록 조회
	*
	* @Title : selectjdgsList
	* @Description : 전문가(심사위원) 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
    @Override
	public List<AsgsysSrngVo> selectjdgsList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectjdgsList(asgsysSrngVo);
	}

	/**
	* 심사 항목 목록 조회
	*
	* @Title : selectSrngQitemList
	* @Description : 심사 항목 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
    @Override
	public List<AsgsysSrngVo> selectSrngQitemList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSrngQitemList(asgsysSrngVo);
	}

    /**
    * 컨설팅 목록조회
    *
    * @Title : selectCsltngList
    * @Description : 컨설팅 목록조회
    * @param asgsysSrngVo
    * @return
    * @throws Exception
    * @return List<DsgnPrgrmVo>
    */
    @Override
	public List<AsgsysSrngVo> selectCsltngList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectCsltngList(asgsysSrngVo);
	}

	public int insertDsgnPrgrm(@Valid AsgsysSrngVo asgsysSrngVo) {
		//지정이력생성
		//운영결과 차수 생성 3년치
		//지정프로그램 상태 업데이트

		return 0;
	}

	/**
	* 심사점수 목록 조회
	*
	* @Title : selectSrngAnsList
	* @Description : 심사점수 목록 조회
	* @param asgsysSrngVo
	* @return
	* @throws Exception
	* @return List<AsgsysSrngVo>
	*/
	@Override
	public List<AsgsysSrngVo> selectSrngAnsList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.selectSrngAnsList(asgsysSrngVo);
	}

}
