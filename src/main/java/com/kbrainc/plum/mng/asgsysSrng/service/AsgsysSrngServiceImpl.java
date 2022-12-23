package com.kbrainc.plum.mng.asgsysSrng.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.cmm.file.model.FileVo;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngDao;
import com.kbrainc.plum.mng.asgsysSrng.model.AsgsysSrngVo;
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
			    cell.setCellValue(StringUtil.nvl(modelVo.getSttsCd(), ""));
			    cell.setCellStyle(style);
				/*신청일*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(dateFormat.format(modelVo.getAplyDt()), ""));
			    cell.setCellStyle(style);
				/*심사위원심사상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrgnSttsCd(), ""));
			    cell.setCellStyle(style);
				/*지원단심사상태*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsCd(), ""));
			    cell.setCellStyle(style);
				/*현장점검지정일시*/
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(StringUtil.nvl(modelVo.getVstDe(), ""));// TODO 시분 합치기?
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
	public List<AsgsysSrngVo> selectTchaidFcltList(AsgsysSrngVo asgsysSrngVo) throws Exception {
		return asgsysSrngDao.selectTchaidFcltList(asgsysSrngVo);
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
    	int retVal = 0;
    	retVal += asgsysSrngDao.updatePrgrmOperMng(asgsysSrngVo);
		return retVal;
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
    	ret = asgsysSrngDao.updateSprtgrpSrng(asgsysSrngVo);

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
    	return asgsysSrngDao.updateSprtgrpSrng(asgsysSrngVo);
    }

    /**
    * 담당자 목록조회
    *
    * @Title : selectPicList
    * @Description : 담당자 목록조회
    * @param asgsysSrngVo
    * @return List<AsgsysSrngVo>
    * @throws Exception
    */
    @Override
	public List<AsgsysSrngVo> selectPicList(AsgsysSrngVo asgsysSrngVo) throws Exception {
    	return asgsysSrngDao.selectPicList(asgsysSrngVo);
	}

}
