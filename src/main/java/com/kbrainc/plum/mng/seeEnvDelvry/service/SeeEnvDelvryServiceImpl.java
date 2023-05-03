/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvDelvry.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.seeEnvDelvry.model.SeeEnvDelvryDao;
import com.kbrainc.plum.mng.seeEnvDelvry.model.SeeEnvDelvryVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 사회환경교육기관 지정 > 교부관리 서비스 구현체
*
* <pre>
* com.kbrainc.plum.mng.seeEnvDelvry.service
* - SeeEnvDelvryServiceImpl.java
* </pre> 
*
* @ClassName : SeeEnvDelvryServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 27.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class SeeEnvDelvryServiceImpl extends PlumAbstractServiceImpl implements SeeEnvDelvryService {

    @Autowired
    private SeeEnvDelvryDao seeEnvDelvryDao;

    @Override
    public List<SeeEnvDelvryVo> selectDelvryList(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvDelvryDao.selectDelvryList(seeEnvDelvryVo);
    }

    @Override
    public int updateIssue(SeeEnvDelvryVo seeEnvDelvryVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvDelvryDao.updateIssue(seeEnvDelvryVo);
    }
    
    @Override
    public void seeEnvDelvryListExcelDownload(SeeEnvDelvryVo seeEnvDelvryVo, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        // TODO Auto-generated method stub
        List<SeeEnvDelvryVo> list = null;
        String realName = "";
        SeeEnvDelvryVo modelVo = null;    

        realName = "seeEnvDelvryListExcelList.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        //Font 설정.
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //제목의 스타일 지정
        HSSFCellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle.setFont(font);
        titlestyle.setWrapText(true);
        
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
                "지정번호"
                , "지정기관명"
                , "법인등록번호\n(사업자등록번호)"
                , "대표자\n성명"
                , "대표자\n생년월일"
                , "담당자\n성명"
                , "담당자\n연락처"
                , "발급\n연월일"
                , "재발급"
                , "확인"
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

        seeEnvDelvryVo.setExcelYn("Y");
        list = seeEnvDelvryDao.selectDelvryList(seeEnvDelvryVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*지정번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDsgnno(), ""));
                cell.setCellStyle(style);
                /*지정기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);
                /*사업자등록번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getBrno(), ""));
                cell.setCellStyle(style);
                /*대표자성명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRprsvNm(), ""));
                cell.setCellStyle(style);
                /*대표자 생년월일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRprsvBrdt(), ""));
                cell.setCellStyle(style);
                /*담당자성명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntNm(), ""));
                cell.setCellStyle(style);
                /*담당자연락처*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntMoblphon(), ""));
                cell.setCellStyle(style);
                /*발급연월일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getIssuDe(), "-"));
                cell.setCellStyle(style);
                /*재발급연월일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getIsgnDe(), "-"));
                cell.setCellStyle(style);
                /*확인*/
                cell = row.createCell(cellnum++);
                cell.setCellValue("");
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
}
