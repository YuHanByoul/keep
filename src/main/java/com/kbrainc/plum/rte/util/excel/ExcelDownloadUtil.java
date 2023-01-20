package com.kbrainc.plum.rte.util.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelDownloadUtil<T> {

    public enum CELL_ALIGN {
        LEFT, CENTER, RIGHT
    }
	private final SXSSFWorkbook workbook = new SXSSFWorkbook();

	public ExcelDownloadUtil(String[] headerLabelArr, List<T> dataList, RowMapper<T> rowMapper) throws Exception {
		Sheet sheet = workbook.createSheet("Sheet1");
		Row headerRow = sheet.createRow(0);

        //Font 설정.
        Font font = workbook.createFont();
        font.setFontName("Arial");
        //제목의 스타일 지정
        CellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        titlestyle.setAlignment(CellStyle.ALIGN_CENTER);
        titlestyle.setBorderRight(CellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderLeft(CellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderTop(CellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderBottom(CellStyle.BORDER_THIN);//얇은 테두리 설정

        titlestyle.setFont(font);

        //내용 스타일 지정
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font);

        for (int i = 0; i < headerLabelArr.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(titlestyle);
            cell.setCellValue(headerLabelArr[i]);
        }

		int rowIdx = 1;
		for (int i = 0; i < dataList.size(); i++) {
			Row row = sheet.createRow(rowIdx);
            for (int j = 0; j < headerLabelArr.length; j++) {
                Cell cell = row.createCell(i);
                cell.setCellValue("");
            }
			CellMapper cellMapper = new CellMapper(workbook, row, style, headerLabelArr.length);
			if (rowMapper.mapData(dataList.get(i), cellMapper, i)) {
				rowIdx++;
			} else {
				sheet.removeRow(row);
			}
		}

        for(int i=0;i<headerLabelArr.length;i++){
            sheet.autoSizeColumn((short)i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
        }
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void excelDownload(HttpServletResponse res, String fileName) throws IOException {
        String downFileName = fileName.replaceAll("[^ㄱ-ㅎ가-힣ㅏ-ㅣa-zA-Z_0-9.-\\[\\]]", "_") + ".xlsx";
        downFileName = new String(downFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        ExcelUtils.excelInfoSet(res, downFileName);
        //엑셀 파일을 만듬
        OutputStream fileOutput = res.getOutputStream();
		workbook.write(fileOutput);

        fileOutput.flush();
        fileOutput.close();
    }

	public void excelFile(FileOutputStream fos) throws IOException {
		workbook.write(fos);
	}
}

