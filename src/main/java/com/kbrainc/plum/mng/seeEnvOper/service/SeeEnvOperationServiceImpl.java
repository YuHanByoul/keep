/**
 * 
 */
package com.kbrainc.plum.mng.seeEnvOper.service;

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
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.seeEnvOper.model.SeeEnvOperationDao;
import com.kbrainc.plum.mng.seeEnvOper.model.SeeEnvOperationVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* [클래스 요약]. 
*
* <pre>
* com.kbrainc.plum.mng.seeEnvOper.service
* - SeeEnvOperationServiceImpl.java
* </pre> 
*
* @ClassName : SeeEnvOperationServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 4. 28.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class SeeEnvOperationServiceImpl extends PlumAbstractServiceImpl implements SeeEnvOperationService {

    @Autowired
    private SeeEnvOperationDao seeEnvOperationDao;

    @Override
    public List<SeeEnvOperationVo> selectOperationList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectOperationList(seeEnvOperationVo);
    }

    @Override
    public void seeEnvOperationListExcelDownload(SeeEnvOperationVo seeEnvOperationVo, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        // TODO Auto-generated method stub
        List<SeeEnvOperationVo> list = null;
        String realName = "";
        SeeEnvOperationVo modelVo = null;    

        realName = "seeEnvOperationListExcelList.xls";
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
                "시도"
                , "지정번호"
                , "기관명"
                , "기관유형"
                , "신청자"
                , "지정상태"
                , "변경신청"
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

        seeEnvOperationVo.setExcelYn("Y");
        list = seeEnvOperationDao.selectOperationList(seeEnvOperationVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*시도*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCtprvnNm(), ""));
                cell.setCellStyle(style);
                /*지정번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDsgnno(), ""));
                cell.setCellStyle(style);
                /*지정기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);
                /*기관유형*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstTypeNm(), ""));
                cell.setCellStyle(style);
                /*신청자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntNm(), ""));
                cell.setCellStyle(style);
                /*지정상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSttsNm(), ""));
                cell.setCellStyle(style);
                /*변경신청*/
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

    @Override
    public List<SeeEnvOperationVo> selectDsgnList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectDsgnList(seeEnvOperationVo);
    }

    @Override
    public List<SeeEnvOperationVo> selectChangeList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectChangeList(seeEnvOperationVo);
    }

    @Override
    public List<SeeEnvOperationVo> selectUserList(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectUserList(seeEnvOperationVo);
    }

    @Override
    public int updateInfo(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.updateInfo(seeEnvOperationVo);
    }

    @Transactional
    @Override
    public int saveDsgn(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result += seeEnvOperationDao.updateDsgnStts(seeEnvOperationVo);
        result += seeEnvOperationDao.insertDsgnDsctn(seeEnvOperationVo);
        return result;
    }

    @Override
    public SeeEnvOperationVo selectStts(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectStts(seeEnvOperationVo);
    }

    @Override
    public int selectDsgnno(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.selectDsgnno(seeEnvOperationVo);
    }

    @Override
    public int updateChange(SeeEnvOperationVo seeEnvOperationVo) throws Exception {
        // TODO Auto-generated method stub
        return seeEnvOperationDao.updateChange(seeEnvOperationVo);
    }
}
