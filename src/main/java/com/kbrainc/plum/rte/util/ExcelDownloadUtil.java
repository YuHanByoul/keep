package com.kbrainc.plum.rte.util;

import com.kbrainc.plum.rte.util.excel.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelDownloadUtil<T> {

    public enum CELL_ALIGN {
        LEFT, CENTER, RIGHT
    }
	private final Workbook workbook = new SXSSFWorkbook();

	public ExcelDownloadUtil(String[] headerLabelArr, List<T> dataList, RowMapper<T> rowMapper) {
		Sheet sheet = workbook.createSheet("Sheet1");
		Row headerRow = sheet.createRow(0);

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

        for (int i = 0; i < headerLabelArr.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(titlestyle);
            cell.setCellValue(headerLabelArr[i]);
        }

		int rowIdx = 1;
		for (int i = 0; i < dataList.size(); i++) {
			Row row = sheet.createRow(rowIdx);
			CellMapper cellMapper = new CellMapper(workbook, row, style, headerLabelArr.length);
			if (rowMapper.mapData(dataList.get(i), cellMapper, i)) {
				rowIdx++;
			} else {
				sheet.removeRow(row);
			}
		}
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void excelDownload(HttpServletResponse res, String fileName) throws IOException {
        ExcelUtils.excelInfoSet(res, fileName);

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

class CellMapper {

    private final Workbook wb;
    private final Row row;
    private final int cellLength;
    private final CellStyle cellStyle;
    private final CellStyle cellStyleL;
    private final CellStyle cellStyleR;
    private final CellStyle cellStyleC;
    private final Map<String, SimpleDateFormat> fmtMap = new HashMap<>();

    CellMapper(Workbook wb, Row row, HSSFCellStyle cellStyle, int cellLength) {
        this.wb = wb;
        this.row = row;
        this.cellLength = cellLength;
        this.cellStyle = cellStyle;
        this.cellStyleL = wb.createCellStyle();
        this.cellStyleR = wb.createCellStyle();
        this.cellStyleC = wb.createCellStyle();

        this.cellStyleL.cloneStyleFrom(cellStyle);
        this.cellStyleR.cloneStyleFrom(cellStyle);
        this.cellStyleC.cloneStyleFrom(cellStyle);
        this.cellStyleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        this.cellStyleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        this.cellStyleC.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    }

    public CellMapper putData(int i, Integer data) {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }
    public CellMapper putData(int i, Integer data, ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data);
        return this;
    }

    public CellMapper putData(int i, Double data) {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }
    public CellMapper putData(int i, Double data, ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data);
        return this;
    }

    public CellMapper putData(int i, Long data) {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }
    public CellMapper putData(int i, Long data, ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data);
        return this;
    }

    public CellMapper putData(int i, String data) {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.CENTER);
    }
    public CellMapper putData(int i, String data, ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data);
        return this;
    }

    public CellMapper putData(int i, boolean flag) {
        return putData(i, flag, "Y", "N");
    }

    public CellMapper putData(int i, boolean flag, String strY, String strN) {
        return putData(i, flag ? strY : strN);
    }

    public CellMapper putData(int i, Date date) {
        return putData(i, date, "yyyy-MM-dd  hh:mm");
    }

    public CellMapper putData(int i, Date date, String formatPtn) {
        return putData(i, date, formatPtn, ExcelDownloadUtil.CELL_ALIGN.CENTER);
    }
    public CellMapper putData(int i, Date date, String formatPtn, ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        String val = null;
        if (date != null) {
            SimpleDateFormat format = fmtMap.get(formatPtn);
            if (format == null) {
                format = new SimpleDateFormat(formatPtn, Locale.getDefault());
                fmtMap.put(formatPtn, format);
            }
            val = format.format(date);
        }
        return putData(i, val, alignStyle);
    }

    public Cell getCell(int i) {
        return getCell(i, null);
    }
    public Cell getCell(int i, CellStyle cellStyle) {
        Cell cell = row.getCell(i);
        if (cell == null) {
            cell = row.createCell(i);
            if (i < cellLength) {
                if (cellStyle == null) {
                    cell.setCellStyle(this.cellStyle);

                } else {
                    cell.setCellStyle(cellStyle);
                }
            }
        }
        return cell;
    }

    public CellStyle getDefaultCellStyle() {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.cloneStyleFrom(this.cellStyle);
        return cellStyle;

    }
    private CellStyle getSimpleCellStyle(ExcelDownloadUtil.CELL_ALIGN alignStyle) {
        CellStyle cellStyle = this.cellStyleC;
        if (alignStyle == ExcelDownloadUtil.CELL_ALIGN.LEFT) {
            cellStyle = this.cellStyleL;
        } else if (alignStyle == ExcelDownloadUtil.CELL_ALIGN.RIGHT) {
            cellStyle = this.cellStyleR;
        } else if (alignStyle == ExcelDownloadUtil.CELL_ALIGN.CENTER) {
            cellStyle = this.cellStyleC;
        }
        return cellStyle;
    }
}

@FunctionalInterface
interface RowMapper<T> {
    boolean mapData(T data, CellMapper mapper, int idx);
}
