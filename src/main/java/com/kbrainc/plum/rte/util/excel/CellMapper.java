package com.kbrainc.plum.rte.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CellMapper {

    private final Workbook wb;
    private final Row row;
    private final int cellLength;
    private final CellStyle cellStyle;
    private final CellStyle cellStyleL;
    private final CellStyle cellStyleR;
    private final CellStyle cellStyleC;
    private final Map<String, SimpleDateFormat> fmtMap = new HashMap<>();

    CellMapper(Workbook wb, Row row, CellStyle cellStyle, int cellLength) {
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

    public CellMapper putData(int i, Integer data) throws Exception {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }

    public CellMapper putData(int i, Integer data, ExcelDownloadUtil.CELL_ALIGN alignStyle) throws Exception {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data == null ? "" : data.toString());
        return this;
    }

    public CellMapper putData(int i, Double data) throws Exception {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }

    public CellMapper putData(int i, Double data, ExcelDownloadUtil.CELL_ALIGN alignStyle) throws Exception {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data == null ? "" : data.toString());
        return this;
    }

    public CellMapper putData(int i, Long data) throws Exception {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.RIGHT);
    }

    public CellMapper putData(int i, Long data, ExcelDownloadUtil.CELL_ALIGN alignStyle) throws Exception {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data == null ? "" : data.toString());
        return this;
    }

    public CellMapper putData(int i, String data) throws Exception {
        return this.putData(i, data, ExcelDownloadUtil.CELL_ALIGN.CENTER);
    }

    public CellMapper putData(int i, String data, ExcelDownloadUtil.CELL_ALIGN alignStyle) throws Exception {
        CellStyle cellStyle = getSimpleCellStyle(alignStyle);
        Cell cell = getCell(i, cellStyle);
        cell.setCellValue(data == null ? "" : data);
        return this;
    }

    public CellMapper putData(int i, boolean flag) throws Exception {
        return putData(i, flag, "Y", "N");
    }

    public CellMapper putData(int i, boolean flag, String strY, String strN) throws Exception {
        return putData(i, flag ? strY : strN);
    }

    public CellMapper putData(int i, Date date) throws Exception {
        return putData(i, date, "yyyy-MM-dd  hh:mm");
    }

    public CellMapper putData(int i, Date date, String formatPtn) throws Exception {
        return putData(i, date, formatPtn, ExcelDownloadUtil.CELL_ALIGN.CENTER);
    }

    public CellMapper putData(int i, Date date, String formatPtn, ExcelDownloadUtil.CELL_ALIGN alignStyle) throws Exception {
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

    public Cell getCell(int i) throws Exception {
        return getCell(i, null);
    }

    public Cell getCell(int i, CellStyle cellStyle) throws Exception{
        Cell cell = row.getCell(i);
        if (cell == null) {
            cell = row.createCell(i);
        }
        if (i < cellLength) {
            if (cellStyle == null) {
                cell.setCellStyle(this.cellStyle);

            } else {
                cell.setCellStyle(cellStyle);
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
