package com.kbrainc.plum.mng.pvtEnveduGrp.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnvEduGrpVo;
import com.kbrainc.plum.mng.pvtEnveduGrp.model.PvtEnveduGrpDao;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 민간 환경교육단체 현황 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.pvtEnveduGrp.service
* - PvtEnveduGrpServiceImpl.java
* </pre>
*
* @ClassName : PvtEnveduGrpServiceImpl
* @Description : 민간 환경교육단체 현황 서비스 구현 클래스
* @author : JD
* @date : 2023. 1. 4.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class PvtEnveduGrpServiceImpl extends PlumAbstractServiceImpl implements PvtEnveduGrpService{
    
    @Autowired
    private PvtEnveduGrpDao pvtEnveduGrpDao;
    
    /**
    * 민간 환경교육단체 목록 조회
    *
    * @Title : selectPvtEnveduGrpList
    * @Description : 민간 환경교육단체 목록 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectPvtEnveduGrpList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception {
        return pvtEnveduGrpDao.selectPvtEnveduGrpList(pvtEnvEduGrpVo);
    }
    
    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param pvtEnvEduGrpVo 객체
    * @throws Exception 예외
    * @return List<PvtEnvEduGrpVo>
    */
    public List<PvtEnvEduGrpVo> selectAddrCtpvnList(PvtEnvEduGrpVo pvtEnvEduGrpVo) throws Exception {    
        return pvtEnveduGrpDao.selectAddrCtpvnList(pvtEnvEduGrpVo);
    }
    
    /**
    * 엑셀다운로드
    *
    * @Title : selectPvtEnveduGrpExcelDownload
    * @Description : 엑셀다운로드
    * @param pvtEnvEduGrpVo 객체
    * @param response 객체
    * @param request 객체
    * @throws Exception 예외
    * @return void
    */
    @Override
    public void selectPvtEnveduGrpExcelDownload(PvtEnvEduGrpVo pvtEnvEduGrpVo , HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<PvtEnvEduGrpVo> list = null;
        String realName = "";
        PvtEnvEduGrpVo modelVo = null;    

        realName = "민간환경교육센터_현황_목록.xls";
        String outputFileName = new String(realName.getBytes("KSC5601"), "8859_1");
        HSSFWorkbook workbook = new HSSFWorkbook();
        //Font 설정.
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontName(HSSFFont.FONT_ARIAL);
        titleFont.setColor(IndexedColors.WHITE.getIndex());
        
        //제목의 스타일 지정
        HSSFCellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setFillForegroundColor(HSSFColor.SEA_GREEN.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle.setFont(titleFont);
        
        //내용 스타일 지정
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        style.setFont(font);
        
        HSSFCellStyle styleR = workbook.createCellStyle();
        styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleR.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleR.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleR.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleR.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        styleR.setFont(font);
        
        HSSFCellStyle styleL = workbook.createCellStyle();
        styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleL.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleL.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleL.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        styleL.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        styleL.setFont(font);
        
        HSSFSheet sheet = null;
        
        sheet = workbook.createSheet("목록");

        String [] titleArr = {
                "No."
                ,"시도"
                ,"기관명"
                ,"기관유형"
                ,"대표자명"
                ,"전화번호"
                ,"홈페이지"
                ,"등록일"
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

        list = pvtEnveduGrpDao.selectPvtEnveduGrpExcelDownload(pvtEnvEduGrpVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*No.*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(i+1);
                cell.setCellStyle(style);
                /*시도*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRgnCd(), ""));
                cell.setCellStyle(style);
                /*기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);   
                /*기관유형*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCdNm(), ""));
                cell.setCellStyle(styleL);
                /*대표자명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRprsvNm(), ""));
                cell.setCellStyle(styleL);
                /*전화번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getTelno(), ""));
                cell.setCellStyle(styleL);
                /*홈페이지*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getHmpg(), ""));
                cell.setCellStyle(styleL);
                /*등록일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRegDt(), ""));
                cell.setCellStyle(styleL);
                
            }

            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)+2048);
            }
        }   
        
        ExcelUtils.excelInfoSet(response,outputFileName);

        //엑셀 파일을 만듬
        OutputStream fileOutput = response.getOutputStream();
 
        workbook.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();
    }
}
