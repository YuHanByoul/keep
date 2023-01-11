package com.kbrainc.plum.mng.envtcherTrnngInst.service;

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

import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstDao;
import com.kbrainc.plum.mng.envtcherTrnngInst.model.EnvtcherTrnngInstVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 환경교육사 양성기관 현황 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.envtcherTrnngInst.service
* - EnvtcherTrnngInstServiceImpl.java
* </pre>
*
* @ClassName : EnvtcherTrnngInstServiceImpl
* @Description : 환경교육사 양성기관 현황 서비스 구현 클래스
* @author : JD
* @date : 2023. 1. 6.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class EnvtcherTrnngInstServiceImpl extends PlumAbstractServiceImpl implements EnvtcherTrnngInstService{

    @Autowired
    private EnvtcherTrnngInstDao envtcherTrnngInstDao;
    
    /**
    * 환경교육사 양성기관 등록 기능
    *
    * @Title : insertEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 등록 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int insertEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.insertEnvtcherTrnngInst(envtcherTrnngInstVo);
    }

    /**
    * 환경교육사 양성기관 수정 기능
    *
    * @Title : updateEnvtcherTrnngInst
    * @Description : 환경교육사 양성기관 수정 기능
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateEnvtcherTrnngInst(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.updateEnvtcherTrnngInst(envtcherTrnngInstVo);
    }
    
    /**
    * 환경교육사 양성기관 목록 조회
    *
    * @Title : selectEnvtcherTrnngInstList
    * @Description : 환경교육사 양성기관 목록 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectEnvtcherTrnngInstList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.selectEnvtcherTrnngInstList(envtcherTrnngInstVo);
    }
    
    /**
    * 환경교육사 양성기관 상세정보 조회
    *
    * @Title : selectEnvtcherTrnngInstInfo
    * @Description : 환경교육사 양성기관 상세정보 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return EnvtcherTrnngInstVo
    */
    public EnvtcherTrnngInstVo selectEnvtcherTrnngInstInfo(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.selectEnvtcherTrnngInstInfo(envtcherTrnngInstVo);
    }
    
    /**
    * 엑셀다운로드
    *
    * @Title : envtcherTrnngInstExcelDownload
    * @Description : 엑셀다운로드
    * @param request 객체
    * @param response 객체
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return void
    */
    @Override
    public void envtcherTrnngInstExcelDownload(HttpServletRequest request, HttpServletResponse response, EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        List<EnvtcherTrnngInstVo> list = null;
        String realName = "";
        EnvtcherTrnngInstVo modelVo = null;    

        realName = "환경교육사_양성기관_현황_목록.xls";
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
                ,"시군구"
                ,"기관명"
                ,"양성기관 등급"
                ,"홈페이지"
                ,"지정일"
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

        list = envtcherTrnngInstDao.selectEnvtcherTrnngInstExcelDownload(envtcherTrnngInstVo);
        
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
                cell.setCellValue(StringUtil.nvl(modelVo.getSidoNm(), ""));
                cell.setCellStyle(style);
                /*시군구*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSignguNm(), ""));
                cell.setCellStyle(style);
                /*기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);   
                /*양성기관 등급*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getGrdCdNm(), ""));
                cell.setCellStyle(style);
                /*홈페이지*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getHmpg(), ""));
                cell.setCellStyle(styleL);
                /*지정일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDsgnDe(), ""));
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
    
    /**
    * 시도 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 시도 조회
    * @param envtcherTrnngInstVo 객체
    * @throws Exception 예외
    * @return List<EnvtcherTrnngInstVo>
    */
    public List<EnvtcherTrnngInstVo> selectAddrCtpvnList(EnvtcherTrnngInstVo envtcherTrnngInstVo) throws Exception {
        return envtcherTrnngInstDao.selectAddrCtpvnList(envtcherTrnngInstVo);
    };
}
