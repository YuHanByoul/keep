package com.kbrainc.plum.mng.bizAply.bizRpt.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptDao;
import com.kbrainc.plum.mng.bizAply.bizRpt.model.BizRptVo;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestMngGrpVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 사업보고관리 서비스 구현 클래스.
*
* <pre>
* com.kbrainc.plum.mng.bizAply.bizRpt.service
* - bizRptServiceImpl.java
* </pre>
*
* @ClassName : bizRptServiceImpl
* @Description : 사업보고관리 서비스 구현 클래스.
* @author : kbrain
* @date : 2023. 2. 7.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BizRptServiceImpl extends PlumAbstractServiceImpl implements BizRptService{

	@Autowired
    private BizRptDao bizRptDao;

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
	@Override
	public List<BizRptVo> selectMdlRptMngList(BizRptVo bizRptVo) throws Exception{
		return bizRptDao.selectMdlRptMngList(bizRptVo);
	}

	/**
    * 중간보고관리 목록 엑셀다운로드
    *
    * @Title : aplyExcelDownList
    * @Description : 중간보고관리 목록 엑셀다운로드
    * @param request
    * @param response
    * @param BizRptVo
    * @return void
    * @throws Exception
    */
	@Override
	public void selectMdlRptMngListExcel(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "mdlRptSbmsnList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"사업분야"
				,"공모명"
				,"진행상태"
				,"중간보고제출기간"
				,"선정사업"
				,"제출"
				,"컨설팅 대상"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectMdlRptMngListExcel(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*사업분야*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getFldCdNm(), ""));
				cell.setCellStyle(style);
				/*공모명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPcntstNm(), ""));
				cell.setCellStyle(style);
				/*진행상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrsSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*중간보고제출기간*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getMdlReportPrd(), ""));
				cell.setCellStyle(style);
				/*선정사업*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getMdlReportPrd(), ""));
				cell.setCellStyle(style);
				/*제출*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getMdlReportPrd(), ""));
				cell.setCellStyle(style);
				/*컨설팅 대상*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getMdlReportPrd(), ""));
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
	* 중간보고제출목록 엑셀 다운로드
	*
	* @Title : selectMdlRptSbmsnListExcel
	* @Description : 중간보고제출목록 엑셀 다운로드
	* @param bizRptVo
	* @param response
	* @param request
	* @return void
	*/
	public void selectMdlRptSbmsnListExcel(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "mdlRptSbmsnList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"접수번호"
				,"제출상태"
				,"신청기관명"
				,"신청자"
				,"프로그램명"
				,"제출일시"
				,"증빙서류"
				,"컨설팅대상"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectMdlRptSbmsnListExcel(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*접수번호*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRcptno(), ""));
				cell.setCellStyle(style);
				/*제출상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getReportSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*신청기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*신청자 명(계정)*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getUserNmAcnt(), ""));
				cell.setCellStyle(style);
				/*프로그램 명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*제출일시*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(dateFormat.format(modelVo.getRegDt()), ""));
				cell.setCellStyle(style);
				/*증빙서류 여부*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getEvdncDcmntYn(), ""));
				cell.setCellStyle(style);
				/*컨설팅 대상 여부*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getCnsltngUseCnt(), ""));
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
	* 중간보고관리 상세 조회
	*
	* @Title : selectMdlRptMng
	* @Description : 중간보고관리 상세 조회
	* @param bizRptVo
	* @return
	* @return BizRptVo
	*/
	@Override
	public BizRptVo selectMdlRptMng(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectMdlRptMng(bizRptVo);
	}

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
	@Override
	public List<BizRptVo> selectMdlRptSbmsnList(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectMdlRptSbmsnList(bizRptVo);
	}

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
	@Override
	@Transactional
	public int updateRptSttsCd(UserVo user, String[] reportids) throws Exception {
		return bizRptDao.updateRptSttsCd(user, reportids);
	}

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
	@Override
	public BizRptVo selectMdlRptSbmsnDetail(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectMdlRptSbmsnDetail(bizRptVo);
	}

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
	@Override
	public List<BizRptVo> selectReportOperList(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectReportOperList(bizRptVo);
	}

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
	@Override
	public List<BizRptVo> selectSplmntDmndList(BizRptVo bizRptVo) throws Exception{
		return bizRptDao.selectSplmntDmndList(bizRptVo);
	}

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
	@Override
	public BizRptVo selectRptSplmntDmnd(BizRptVo bizRptVo) throws Exception{
		return bizRptDao.selectRptSplmntDmnd(bizRptVo);
	}

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
	@Override
	@Transactional
	public int insertRptSplmnt(BizRptVo bizRptVo) throws Exception{

		int ret = 0;
		bizRptVo.setAnsSttsCd("201101");    //답변상태코드 : 보완요청
		ret = bizRptDao.insertRptSplmnt(bizRptVo);

		bizRptVo.setReportSttsCd("203104");    //보고상태코드 : 보완요청
		ret = bizRptDao.updateReportSttsCd(bizRptVo);

		return ret ;
	}

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
	@Override
	public List<BizRptVo> selectRsltRptMngList(BizRptVo bizRptVo) throws Exception{
		return bizRptDao.selectRsltRptMngList(bizRptVo);
	}

	/**
    * 결과보고관리 목록 엑셀다운로드
    *
    * @Title : aplyExcelDownList
    * @Description : 결과보고관리 목록 엑셀다운로드
    * @param request
    * @param response
    * @param BizRptVo
    * @return void
    * @throws Exception
    */
	public void selectRsltRptMngListExcel(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "mdlRptSbmsnList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"사업분야"
				,"공모명"
				,"진행상태"
				,"결과보고제출기간"
				,"선정사업"
				,"제출"
				,"컨설팅 대상"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectRsltRptMngListExcel(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*사업분야*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getFldCdNm(), ""));
				cell.setCellStyle(style);
				/*공모명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPcntstNm(), ""));
				cell.setCellStyle(style);
				/*진행상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrsSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*결과보고제출기간*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRsltReportPrd(), ""));
				cell.setCellStyle(style);
				/*선정사업*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRsltReportPrd(), ""));
				cell.setCellStyle(style);
				/*제출*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRsltReportPrd(), ""));
				cell.setCellStyle(style);
				/*컨설팅 대상*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRsltReportPrd(), ""));
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
	* 결과보고제출목록 엑셀 다운로드
	*
	* @Title : selectRsltRptSbmsnListExcel
	* @Description : 결과보고제출목록 엑셀 다운로드
	* @param bizRptVo
	* @param response
	* @param request
	* @return void
	*/
	public void selectRsltRptSbmsnListExcel(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "mdlRptSbmsnList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"접수번호"
				,"제출상태"
				,"신청기관명"
				,"신청자"
				,"프로그램명"
				,"제출일시"
				,"증빙서류"
				,"컨설팅대상"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectRsltRptSbmsnListExcel(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*접수번호*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getRcptno(), ""));
				cell.setCellStyle(style);
				/*제출상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getReportSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*신청기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*신청자 명(계정)*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getUserNmAcnt(), ""));
				cell.setCellStyle(style);
				/*프로그램 명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*제출일시*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(dateFormat.format(modelVo.getRegDt()), ""));
				cell.setCellStyle(style);
				/*증빙서류 여부*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getEvdncDcmntYn(), ""));
				cell.setCellStyle(style);
				/*컨설팅 대상 여부*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getCnsltngUseCnt(), ""));
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
	* 결과보고관리 상세 조회
	*
	* @Title : selectRsltRptMng
	* @Description : 결과보고관리 상세 조회
	* @param bizRptVo
	* @return
	* @return BizRptVo
	*/
	@Override
	public BizRptVo selectRsltRptMng(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectRsltRptMng(bizRptVo);
	}

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
	@Override
	public List<BizRptVo> selectRsltRptSbmsnList(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectRsltRptSbmsnList(bizRptVo);
	}

	/**
	* 컨설팅관리 목록 조회
	*
	* @Title : selectCnsltngMngList
	* @Description : selectCnsltngMngList
	* @param bizRptVo
	* @return
	* @return List<BizRptVo>
	*/
	@Override
	public List<BizRptVo> selectCnsltngMngList(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectCnsltngMngList(bizRptVo);
	}

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
	@Override
	public BizRptVo selectCnsltngMng(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectCnsltngMng(bizRptVo);
	}

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
	@Override
	public int insertCnsltngMng(BizRptVo bizRptVo) throws Exception {
		int ret = 0;

		if (!StringUtil.nvl(bizRptVo.getCnsltngid()).equals("") && !StringUtil.nvl(bizRptVo.getCnsltngid()).equals(0)) {
			//컨설팅관리 수정
			ret = bizRptDao.updateCnsltngMng(bizRptVo);
		}else {
			//컨설팅관리 등록
			ret = bizRptDao.insertCnsltngMng(bizRptVo);
		}

		return ret;
	}

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
	@Override
	public List<BizRptVo> selectBizAbndMngList(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectBizAbndMngList(bizRptVo);
	}

	/**
    * 사업포기관리 목록 엑셀 다운로드
    *
    * @Title : selectBizAbndMngExcelList
    * @Description : 사업포기관리 목록 엑셀 다운로드
    * @param request
    * @param response
    * @param BizRptVo
    * @return void
    * @throws Exception
    */
	public void selectBizAbndMngExcelList(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "BizAbndMngList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);     //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);      //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);   //얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"사업분야"
				,"공모명"
				,"프로그램명/동아리명"
				,"상태"
				,"신청일"
				,"기관명"
				,"신청자"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectBizAbndMngExcelList(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*사업분야*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getFldCdNm(), ""));
				cell.setCellStyle(style);
				/*공모명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPcntstNm(), ""));
				cell.setCellStyle(style);
				/*프로그램명/동아리명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getDmndSttsCdNm(), ""));
				cell.setCellStyle(style);
				/*신청일*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getCnclDt(), ""));
				cell.setCellStyle(style);
				/*기관명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
				cell.setCellStyle(style);
				/*신청자*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getUserNmAcnt(), ""));
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
	* 사업포기관리 상세 조회
	*
	* @Title : selectBizAbndMng
	* @Description : 사업포기관리 상세 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return Object
	*/
	@Override
	public BizRptVo selectBizAbndMng(BizRptVo bizRptVo) throws Exception {
		return bizRptDao.selectBizAbndMng(bizRptVo);
	}

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
	@Override
	@Transactional
	public int updateBizAbnd(BizRptVo bizRptVo) throws Exception {
		int ret = 0;

		//사업포기 수정
		ret = bizRptDao.updateBizAbnd(bizRptVo);

		//지원 신청 사업취소여부 수정
		ret = bizRptDao.updateBsnsCnclYn(bizRptVo);

		return ret;
	}

	/**
	* 컨설팅전문가 목록 조회
	*
	* @Title : selectCnsltngExprtList
	* @Description : 컨설팅전문가 목록 조회
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	@Override
	public List<BizRptVo> selectCnsltngExprtList() throws Exception{
		return bizRptDao.selectCnsltngExprtList();
	}

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
	@Override
	public void selectCnsltngMngExcelList(BizRptVo bizRptVo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		List<BizRptVo> list = null;
		String realName = "";
		BizRptVo modelVo = null;

		realName = "cnsltngMngExcelList.xls";

		HSSFWorkbook workbook = new HSSFWorkbook();
		//Font 설정.
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		//제목의 스타일 지정
		HSSFCellStyle titlestyle = workbook.createCellStyle();
		titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
		titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
		titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정
		titlestyle.setFont(font);

		//내용 스타일 지정
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		HSSFCellStyle styleR = workbook.createCellStyle();
		styleR.setAlignment(CellStyle.ALIGN_RIGHT);
		styleR.setFont(font);

		HSSFCellStyle styleL = workbook.createCellStyle();
		styleL.setAlignment(CellStyle.ALIGN_LEFT);
		styleL.setFont(font);
		HSSFSheet sheet = null;

		sheet = workbook.createSheet("sheet1");

		String [] titleArr = {
				"컨설턴트"
				,"공모명"
				,"프로그램명"
				,"방문일시"
				,"상태"
		};

		//Row 생성
		HSSFRow row = sheet.createRow(0);
		//Cell 생성
		HSSFCell cell = null;

		ArrayList<String> titleList = new ArrayList<>();
		for (String element : titleArr) {
			titleList.add(element);
		}

		int titleCnt = 0;
		for(String title : titleList){
			cell = row.createCell(titleCnt++);
			cell.setCellValue(title);
			cell.setCellStyle(titlestyle);
		}

		list = bizRptDao.selectCnsltngMngExcelList(bizRptVo);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

		if(list != null && list.size() > 0){
			int cellnum = 0;
			for (int i=0; i<list.size();i++){
				modelVo = list.get(i);

				//타이틀이 1개 row에 write 되어있음 따라서 i+1
				row = sheet.createRow((i+1));
				cellnum = 0;

				/*컨설턴트*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getNm(), ""));
				cell.setCellStyle(style);
				/*공모명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPcntstNm(), ""));
				cell.setCellStyle(style);
				/*프로그램명*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
				cell.setCellStyle(style);
				/*방문일시*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getVstDt(), ""));
				cell.setCellStyle(style);
				/*상태*/
				cell = row.createCell(cellnum++);
				cell.setCellValue(StringUtil.nvl(modelVo.getCnsltngSttsNm(), ""));
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
	* 담당자그룹 목록 조회
	*
	* @Title : selectMngGrpList
	* @Description : 담당자그룹 목록 조회
	* @param bizRptVo
	* @return
	* @throws Exception
	* @return List<BizRptVo>
	*/
	@Override
	public List<BizRptVo> selectMngGrpList(BizRptVo bizRptVo) throws Exception{
		return bizRptDao.selectMngGrpList(bizRptVo);
	}

}
