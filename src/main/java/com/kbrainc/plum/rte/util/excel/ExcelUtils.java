package com.kbrainc.plum.rte.util.excel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kbrainc.plum.rte.util.StringUtil;

import liquibase.util.csv.CSVReader;

/**
 * 
 * 엑셀관련기능을 제공하는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - ExcelUtils.java
 * </pre> 
 *
 * @ClassName : ExcelUtils
 * @Description : 엑셀관련기능을 제공하는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 24.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class ExcelUtils {
	/**
	 * 업로드된 엑셀파일을 리스트 객체에 담는다.
	 * @param input
	 * @return
	 */
	public static ArrayList getExcelPoiArrayList(InputStream input) throws Exception{
		XSSFRow row = null;
		XSSFCell cell = null;
		ArrayList rowList = new ArrayList();
		ArrayList cellList =null;
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheetAt(0);
		//취득된 sheet에서 rows수 취득
		int rows = sheet.getPhysicalNumberOfRows();
		int firstCells = 0;
		for(int r=0; r<rows; r++){
			row = sheet.getRow(r); // row 가져오기
			if (row != null) {				
				
				if(r == 0){
					firstCells = row.getPhysicalNumberOfCells();
				}
				cellList = new ArrayList();
				//취득된 row에서 취득대상 cell수 취득
				int cells = row.getPhysicalNumberOfCells();
				int blankCnt = 0;
				for (int c = 0; c < firstCells; c++) {
					cell = row.getCell(c);
					if(cell != null){
						switch (cell.getCellType()) {
				         
				          case XSSFCell.CELL_TYPE_FORMULA:
				        	  cellList.add(cell.getNumericCellValue());
				              break;
				                
				          case XSSFCell.CELL_TYPE_STRING:
				        	  cellList.add(cell.getStringCellValue());
				              break;
				                
				          case XSSFCell.CELL_TYPE_BLANK:
				        	  cellList.add(cell.getStringCellValue());
				        	  blankCnt++;
				              break;
				                
				          case XSSFCell.CELL_TYPE_ERROR :
				        	  cellList.add( cell.getErrorCellValue());
				        	  blankCnt++;
				              break;	
				              
				          case XSSFCell.CELL_TYPE_NUMERIC :
							  cellList.add( new DecimalFormat("#.#####").format(cell.getNumericCellValue()));
				        	  //cellList.add( new DecimalFormat("###").format(cell.getNumericCellValue()));
				              break;
				          
				          case XSSFCell.CELL_TYPE_BOOLEAN :
				        	  cellList.add(String.valueOf((cell.getBooleanCellValue())));
				        	  break; 
				          default:
				        	  cellList.add("");
				        	  blankCnt++;
				        	  break;
				          }
						
					}else{
						cellList.add("");
						blankCnt++;
					}
				}
				
				//CELL이 하나라도 빈값이 아닌경우 리스트에 넣어줌
				if(blankCnt < firstCells){
					rowList.add(cellList);
					
				//CELL이 모두 빈값인경우는 리스트에 넣지 않음
				} else {}
				
			}
			
		}
		
		return rowList;
	}
	
    /**
     * 
     * 업로드된 엑셀파일을 리스트 객체에 담는다.
     *
     * @Title : getExcelJxlArrayList
     * @Description : 업로드된 엑셀파일을 리스트 객체에 담는다.
     * @param input
     * @throws Exception
     * @return ArrayList 엑셀데이터 리스트
     */
	public static ArrayList getExcelJxlArrayList(InputStream input) throws Exception{
		
		ArrayList rowList = new ArrayList();
		ArrayList cellList = null;
		HSSFWorkbook work = new HSSFWorkbook(input); // 존재하는 엑셀파일 경로를 지정
		HSSFSheet sheet = work.getSheetAt(0); // 첫번째 시트를 지정합니다.
		//취득된 sheet에서 rows수 취득
		int rows = sheet.getPhysicalNumberOfRows();
		int firstCells = 0;		
		int firstCells2 = 0;		
		if (rows <= 0) {
			throw new Exception("Read 할 데이터가 엑셀에 존재하지 않습니다.");
		}
		
		// 엑셀데이터를 배열에 저장
		for (int r = 0; r < rows; r++) {
			HSSFRow row = sheet.getRow(r);
			 if(row != null){
				 
				 if(r == 0){
					firstCells = row.getPhysicalNumberOfCells();
					firstCells2 = row.getLastCellNum();
					
					/* 병합 된 셀이 있는 경우 getPhysicalNumberOfCells는 하나로 인식되는 현상이 발생*/
					if(firstCells2 > firstCells){
						firstCells = firstCells2;
					}
				}
				
				cellList = new ArrayList();
				int cells = row.getPhysicalNumberOfCells();
				int blankCnt = 0;
				
				for (int k = 0; k < firstCells; k++) {
					HSSFCell cell = row.getCell(k);
					if(cell !=null){
						switch (cell.getCellType()) {
				         
				          case HSSFCell.CELL_TYPE_FORMULA:
				        	  cellList.add(cell.getNumericCellValue());
				              break;
				                
				          case HSSFCell.CELL_TYPE_STRING:
				        	  cellList.add(cell.getStringCellValue());
				              break;
				                
				          case HSSFCell.CELL_TYPE_BLANK:
				        	  cellList.add(cell.getStringCellValue());
				        	  blankCnt++;
				              break;
				                
				          case HSSFCell.CELL_TYPE_ERROR :
				        	  cellList.add(cell.getErrorCellValue());
				        	  blankCnt++;
				              break;
				         
				          case HSSFCell.CELL_TYPE_NUMERIC :
				        	  cellList.add( new DecimalFormat("###").format(cell.getNumericCellValue()));
				              break;
				              
				          case HSSFCell.CELL_TYPE_BOOLEAN :
				        	  cellList.add(String.valueOf((cell.getBooleanCellValue())));
				        	  break; 
				              
				          default:
				        	  cellList.add("");
				        	  blankCnt++;
				        	  break;
				          }
					}else{
						cellList.add("");
						blankCnt++;
					}
					
				}
				
				//CELL이 하나라도 빈값이 아닌경우 리스트에 넣어줌
				if(blankCnt < firstCells){
					rowList.add(cellList);
					
				//CELL이 모두 빈값인경우는 리스트에 넣지 않음
				} else {}
				
			 }
		}		
		
		return rowList;
	}
	
    /**
     * 
     * 업로드된 엑셀파일을 리스트 객체에 담는다.
     *
     * @Title : getExcelCsvArrayList
     * @Description : 업로드된 엑셀파일을 리스트 객체에 담는다.
     * @param input
     * @throws Exception
     * @return ArrayList 엑셀데이터 리스트
     */
	public static ArrayList getExcelCsvArrayList(InputStream input) throws Exception{
		ArrayList rowList = new ArrayList();
		ArrayList cellList = null;
		InputStreamReader readFile = new InputStreamReader(input);
		CSVReader reader = new CSVReader(readFile);

		String[] nextLine = reader.readNext();
		while ( nextLine != null){
			cellList = new ArrayList();
			for ( String str : nextLine ){				
				cellList.add(str);
				
			}
			rowList.add(cellList);
			nextLine = reader.readNext();
		}
		
		return rowList;
	}
	
	public static void excelInfoSet(HttpServletResponse response, String fileName){
		String tmpFileName = fileName;
		if(!"".equals(StringUtil.nvl(tmpFileName, ""))){
			long today = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			String inputFileName = ""; 
			String inputFileExt = ""; 
			String[] fileNameArray = tmpFileName.split("\\.");

			if(fileNameArray.length >= 2){
				inputFileName = fileNameArray[0]; 
				inputFileExt = fileNameArray[1];

				tmpFileName = inputFileName + "_" + sdf.format(today) + "." + inputFileExt;
			}
		}
		
		response.setHeader("Content-type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+tmpFileName);
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "private");
		response.setHeader("Expires", "0");
	}
	
	public static void excelInfoSetCsv(HttpServletResponse response, String fileName){
		String tmpFileName = fileName;
		if(!"".equals(StringUtil.nvl(tmpFileName, ""))){
			long today = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
			String inputFileName = ""; 
			String inputFileExt = ""; 
			String[] fileNameArray = tmpFileName.split("\\.");
			
			if(fileNameArray.length >= 2){
				inputFileName = fileNameArray[0]; 
				inputFileExt = fileNameArray[1];

				tmpFileName = inputFileName + "_" + sdf.format(today) + "." + inputFileExt;
			}
		}
	
		response.setHeader("Content-type", "application/octet-stream;charset=euc-kr");
		response.setHeader("Content-Disposition", "attachment; filename="+tmpFileName);
		response.setHeader("Content-Transfer-Encoding", "binary;");
	}
}
