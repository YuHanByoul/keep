package com.kbrainc.plum.rte.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.util.csv.CSVReader;

/**
 * 
 * 엑셀관련기능을 제공하는 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util
 * - ExcelUtil.java
 * </pre> 
 *
 * @ClassName : ExcelUtil
 * @Description : 엑셀관련기능을 제공하는 클래스
 * @author : KBRAINC
 * @date : 2021. 2. 24.
 * @Version : 
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class ExcelUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    
	/**
	 * 
	 * 업로드된 엑셀파일을 리스트 객체에 담는다.
	 *
	 * @Title : getExcelPoiArrayList
	 * @Description : 업로드된 엑셀파일을 리스트 객체에 담는다.
	 * @param input
	 * @throws Exception
	 * @return ArrayList 엑셀데이터 리스트
	 */
    public static ArrayList getExcelPoiArrayList(InputStream input) throws Exception {
        XSSFRow row = null;
        XSSFCell cell = null;
        ArrayList rowList = new ArrayList();
        ArrayList cellList = null;
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        // 취득된 sheet에서 rows수 취득
        int rows = sheet.getPhysicalNumberOfRows();
        int firstCells = 0;
        for (int r = 0; r < rows; r++) {
            row = sheet.getRow(r); // row 가져오기
            if (row != null) {
                //if (r == 0) { //첫줄이 공백일 수 있어 주석처리 함. 2021.02.25 by comnic
                    firstCells = row.getPhysicalNumberOfCells();
                //}
                cellList = new ArrayList();
                // 취득된 row에서 취득대상 cell수 취득
                int cells = row.getPhysicalNumberOfCells();
                int blankCnt = 0;
                for (int c = 0; c < firstCells; c++) {
                    cell = row.getCell(c);
                    if (cell != null) {
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

                        case XSSFCell.CELL_TYPE_ERROR:
                            cellList.add(cell.getErrorCellValue());
                            blankCnt++;
                            break;

                        case XSSFCell.CELL_TYPE_NUMERIC:
                            cellList.add(new DecimalFormat("###").format(cell.getNumericCellValue()));
                            break;

                        default:
                            cellList.add("");
                            blankCnt++;
                            break;

                        }
                    } else {
                        cellList.add("");
                        blankCnt++;
                    }
                }

                // CELL이 하나라도 빈값이 아닌경우 리스트에 넣어줌
                if (blankCnt < firstCells) {
                    rowList.add(cellList);
                    // CELL이 모두 빈값인경우는 리스트에 넣지 않음
                }
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
    public static ArrayList getExcelJxlArrayList(InputStream input) throws Exception {
        ArrayList rowList = new ArrayList();
        ArrayList cellList = null;
        HSSFWorkbook work = new HSSFWorkbook(input); // 존재하는 엑셀파일 경로를 지정
        HSSFSheet sheet = work.getSheetAt(0); // 첫번째 시트를 지정합니다.
        // 취득된 sheet에서 rows수 취득
        int rows = sheet.getPhysicalNumberOfRows();
        int firstCells = 0;
        if (rows <= 0) {
            return rowList;
        }

        // 엑셀데이터를 배열에 저장
        for (int r = 0; r < rows; r++) {
            HSSFRow row = sheet.getRow(r);
            if (row != null) {
                if (r == 0) {
                    firstCells = row.getPhysicalNumberOfCells();
                }

                cellList = new ArrayList();
                int cells = row.getPhysicalNumberOfCells();
                int blankCnt = 0;

                for (int k = 0; k < firstCells; k++) {
                    HSSFCell cell = row.getCell(k);
                    if (cell != null) {
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

                        case HSSFCell.CELL_TYPE_ERROR:
                            cellList.add(cell.getErrorCellValue());
                            blankCnt++;
                            break;

                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cellList.add(new DecimalFormat("###").format(cell.getNumericCellValue()));
                            break;

                        default:
                            cellList.add("");
                            blankCnt++;
                            break;
                        }
                    } else {
                        cellList.add("");
                        blankCnt++;
                    }
                }

                // CELL이 하나라도 빈값이 아닌경우 리스트에 넣어줌
                if (blankCnt < firstCells) {
                    rowList.add(cellList);
                    // CELL이 모두 빈값인경우는 리스트에 넣지 않음
                }
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
    public static ArrayList getExcelCsvArrayList(InputStream input) throws Exception {
        ArrayList rowList = new ArrayList();
        ArrayList cellList = null;
        InputStreamReader readFile = new InputStreamReader(input);
        CSVReader reader = new CSVReader(readFile);

        String[] nextLine = reader.readNext();
        while (nextLine != null) {
            cellList = new ArrayList();
            for (String str : nextLine) {
                cellList.add(str);
            }
            rowList.add(cellList);
            nextLine = reader.readNext();
        }

        return rowList;
    }
    
    /**
     * 
     * 엑셀파일 생성. 
     * 상황에 따라 list를 각 VO등으로 변경하여 method를 만들어 사용한다.
     *
     * @Title : writeExcel
     * @Description : 엑셀 파일을 생성한다.
     * @param list 저장할 List
     * @param target
     * @param mode [xls, xlsx]
     * @throws Exception
     * @return boolean
     */
    public static boolean writeExcel(List<String> list, String target, String mode) throws Exception {
		// 워크북 생성
    	Workbook workbook = null;
    	
    	if(mode.equals("xlsx")) {
    		workbook = new XSSFWorkbook();
    	}else {
    		workbook = new HSSFWorkbook();    		
    	}

        // 워크시트 생성
    	Sheet sheet = workbook.createSheet();
        
        // 리스트의 내용을 셀에 입력한다.
        for(int i=0; i < list.size(); i++){
        	// 행 생성
        	Row row = sheet.createRow(i);
            // 셀 생성
        	Cell cell = row.createCell(0);
        	
        	// 셀에 값 입력
            cell.setCellValue(list.get(i));        	        	
        }

        // 입력된 내용 파일로 쓰기
        File file = new File(target);
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            LOGGER.error("writeExcel.FileNotFoundException.290L");
            return false;
        } catch (IOException e) {
            LOGGER.error("writeExcel.IOException.293L");
            return false;
        } finally {
            try {
                //if(workbook!=null) workbook.close();
                if(fos!=null) fos.close();
                
            } catch (IOException e) {
                LOGGER.error("writeExcel.IOException.301L");
            }
        }

    	
    	return true;
    }

    /**
     * 
     * 응답해더를 셋팅한다.
     *
     * @Title : excelInfoSet
     * @Description : 응답해더를 셋팅한다.
     * @param response
     * @param fileName
     * @return void
     */
    public static void excelInfoSet(HttpServletResponse response, String fileName) {
        response.setHeader("Content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "private");
        response.setHeader("Expires", "0");
    }

    /**
     * 
     * 응답헤더를 셋팅한다.(CSV)
     *
     * @Title : excelInfoSetCsv
     * @Description : 응답헤더를 셋팅한다.
     * @param response
     * @param fileName
     * @return void
     */
    public static void excelInfoSetCsv(HttpServletResponse response, String fileName) {
        response.setHeader("Content-type", "application/octet-stream;charset=euc-kr");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary;");
    }
}