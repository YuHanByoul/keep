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
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ChklstAnsVo;
import com.kbrainc.plum.mng.asgsysSrng.model.DsgnSrngFormVo;
import com.kbrainc.plum.mng.asgsysSrng.model.EmrgcyActnPlanVo;
import com.kbrainc.plum.mng.asgsysSrng.model.ExpndArtclVo;
import com.kbrainc.plum.mng.asgsysSrng.model.PrgrmSchdlVo;
import com.kbrainc.plum.mng.asgsysSrng.model.TchaidFcltVo;
import com.kbrainc.plum.mng.member.model.MemberVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;


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

		list = asgsysSrngDao.jdgsSrngListExcelDown(asgsysSrngVo);

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
			    cell.setCellValue(StringUtil.nvl(modelVo.getAplyDt(), ""));
			    cell.setCellStyle(style);
				/*심사위원심사상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrgnSttsCdNm(), ""));
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
	* 프로그램 운영일정 수정
	*
	* @Title : updatePrgrmSchdl
	* @Description : 프로그램 운영일정 수정
	* @param asgsysSrngVo
	* @return int
	* @throws Exception

	@Override
	@Transactional
	public int updatePrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.updatePrgrmSchdl(asgsysSrngVo);
	}
		*/

	/**
	* 프로그램 운영일정 등록
	*
	* @Title : insertPrgrmSchdl
	* @Description : 프로그램 운영일정 등록
	* @param asgsysSrngVo
	* @return int
	* @throws Exception

	@Override
	@Transactional
	public int insertPrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.insertPrgrmSchdl(asgsysSrngVo);
	}
	*/

	/**
	* 프로그램 운영일정 삭제
	*
	* @Title : deletePrgrmSchdl
	* @Description : 프로그램 운영일정 삭제
	* @param asgsysSrngVo
	* @return int
	* @throws Exception

	@Override
	@Transactional
	public int deletePrgrmSchdl(AsgsysSrngVo asgsysSrngVo) throws Exception{
		return asgsysSrngDao.deletePrgrmSchdl(asgsysSrngVo);
	}
	*/

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
    * @Title : dsgnSrngMainForm
    * @Description : 심사위원심사 목록조회
    * @param AsgsysSrngVo객체
    * @return Map<String,Object> 응답결과객체
    * @throws Exception 예외
    */
	@Override
	public List<AsgsysSrngVo> selectJdgsSrngList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		//
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

		list = asgsysSrngDao.selectJdgsSrngList(asgsysSrngVo);

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
			    /*심사위원명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getJdgsid(), ""));    /*todo 심사원명으로 수정*/
			    cell.setCellStyle(style);
				/*기관명*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
			    cell.setCellStyle(style);
				/*심사진행상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrgnSttsCd(), ""));
			    cell.setCellStyle(style);
				/*배정일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(dateFormat.format(modelVo.getRegDt()), ""));
			    cell.setCellStyle(style);
				/*숙박여부*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getStyYn(), ""));
			    cell.setCellStyle(style);
				/*심사일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrngDt(), ""));
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
        int retVal = 0;
        retVal += asgsysSrngDao.updateJdgsSrngDetail(asgsysSrngVo);



        return retVal;
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
    public int insertJdgsSrngDetail(@Valid AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int retVal = 0;
        retVal += asgsysSrngDao.insertJdgsSrngDetail(asgsysSrngVo);
        return retVal;
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
	public int updateSftyMng(@Valid AsgsysSrngVo asgsysSrngVo) throws Exception{
    	int retVal = 0;
    	retVal += asgsysSrngDao.updateSftyMng(asgsysSrngVo);
		return retVal;
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
	public int updatePrgrmOperMng(@Valid AsgsysSrngVo asgsysSrngVo) throws Exception {
    	int ret= 0;

    	List<ExpndArtclVo> expndLst = asgsysSrngVo.getExpndArtclLst();
    	List<TchaidFcltVo> fcltLst  = asgsysSrngVo.getTchaidFcltLst();

    	//지출항목 목록 저장
    	asgsysSrngDao.deleteExpndArtcl(expndLst.get(0));

    	for(ExpndArtclVo vo : expndLst) {
    		vo.setUser(asgsysSrngVo.getUser());
    		asgsysSrngDao.insertExpndArtcl(vo);
    	}

    	//교구 및 시설 목록 저장
    	asgsysSrngDao.deleteTchaidFclt(fcltLst.get(0));

    	for(TchaidFcltVo vo : fcltLst) {
    		vo.setUser(asgsysSrngVo.getUser());
    		asgsysSrngDao.insertTchaidFclt(vo);
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
		return asgsysSrngDao.insertPrgrmOperMng(asgsysSrngVo);
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
    	//심사결과insert asgsysSrngDao.insertSprtgrpSrng(asgsysSrngVo)
    	//심사의견 update
    	ret = asgsysSrngDao.updateSprtgrpOpnn(asgsysSrngVo);

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

    	List<ChklstAnsVo> lst = asgsysSrngVo.getAnsLst();

    	logger.info("###############################################");
    	logger.info("size : " + lst.size());
    	logger.info("###############################################");
    	logger.info("size : " + lst.get(0).getSeCd());
    	logger.info("###############################################");
    	//체크리스트 답변 수정
    	for(ChklstAnsVo vo : lst) {

    		vo.setUser(asgsysSrngVo.getUser());

    		if(1 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    			ret += asgsysSrngDao.updateChklstAns(vo);
    		}else if(0 == asgsysSrngDao.selectKeyCntChklstAns(vo)) {
    			ret += asgsysSrngDao.insertChklstAns(vo);
    		}
    	}

    	//지원단심사 저장 심사의견 수정
    	ret = asgsysSrngDao.updateSprtgrpOpnn(asgsysSrngVo);

    	//체크리스트 제출 저장
    	ret += asgsysSrngDao.updateChklstSbmsn(asgsysSrngVo);


    	//updateSprtgrpSrng
    	//sprtgrpSrng


    	//체크리스트 답변목록 저장


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
		return asgsysSrngDao.insertPrgrmDstnctn(asgsysSrngVo);
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

    	//프로그램_일정 저장*/
    	List<PrgrmSchdlVo> schdLst = asgsysSrngVo.getPrgrmSchdlLst();


    	if( 0 < schdLst.size()) {

    		asgsysSrngDao.deletePrgrmSchdl(schdLst.get(0));

    		for(PrgrmSchdlVo prgrmSchdlVo : schdLst) {

    			prgrmSchdlVo.setUser(asgsysSrngVo.getUser());
    			ret += asgsysSrngDao.insertPrgrmSchdl(prgrmSchdlVo);
    		}

    	}
    	ret = 0;

    	//비상조치계획 저장*/
    	List<EmrgcyActnPlanVo> planLst = asgsysSrngVo.getEmrgcyActnPlanLst();

    	if( 0 < planLst.size()) {

    		asgsysSrngDao.deleteEmrgcyActnPlan(planLst.get(0));

    		for(EmrgcyActnPlanVo emrgcyActnPlanVo : planLst) {

    			emrgcyActnPlanVo.setUser(asgsysSrngVo.getUser());
    			ret += asgsysSrngDao.insertEmrgcyActnPlan(emrgcyActnPlanVo);
    		}
    	}
    	ret = 0;

    	// 프로그램 우수성 수정
    	ret = asgsysSrngDao.updatePrgrmDstnctn(asgsysSrngVo);

    	return ret;
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
	public List<MemberVo> selectMbrList(AsgsysSrngVo asgsysSrngVo) throws Exception {
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
    			ret = asgsysSrngDao.insertPicJdgs(asgsysSrngVo);
    		}
    	}

    	if("sprtgrp".equals( asgsysSrngVo.getMode())) {

    		ret = asgsysSrngDao.insertPicSprtgrp(asgsysSrngVo);


    		//이전 지원단id삭제
    		if(com.kbrainc.plum.rte.util.CommonUtil.isNotEmpty(asgsysSrngVo.getBfrSprtgrpid())) {
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
//    @Override
//	public List<AsgsysSrngVo> selectSrngScrList(AsgsysSrngVo asgsysSrngVo) throws Exception{
		//return asgsysSrngDao.selectSrngScrList(asgsysSrngVo);
//	}

}
