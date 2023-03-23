package com.kbrainc.plum.front.clclnMng.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.front.clclnMng.model.ClclnDao;
import com.kbrainc.plum.front.clclnMng.model.ClclnDsctnVo;
import com.kbrainc.plum.front.clclnMng.model.ClclnVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 마이페이지 > 체험환경교육 프로그램 지원관리 > 정산관리 서비스 클래스 
*
* <pre>
* com.kbrainc.plum.front.clclnMng.service
* - ClclnServiceImpl.java
* </pre> 
*
* @ClassName : ClclnServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 3. 20.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@Service
public class ClclnServiceImpl extends PlumAbstractServiceImpl implements ClclnService {
    
    @Autowired
    private ClclnDao clclnDsctnDao;
    
    /**
    * 정산관리 목록 조회. 
    *
    * @Title : selectClclnList
    * @Description : TODO
    * @param clclnVo
    * @return
    * @throws Exception
    * @return List<ClclnVo>
     */
    public List<ClclnVo> selectClclnList(ClclnVo clclnVo) throws Exception {
        return clclnDsctnDao.selectClclnList(clclnVo);
    }

    /**
     * 정산내역관리 탭 집행 목록 조회. 
     *
     * @Title : selectClclnList
     * @Description : TODO
     * @param clclnVo
     * @return
     * @throws Exception
     * @return List<ClclnDsctnVo>
      */
    public List<ClclnDsctnVo> selectClclnDsctnList(ClclnVo clclnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnList(clclnVo);
    }
    
    /**
     * 정산내역관리 탭 집행내역개요서 조회. 
     *
     * @Title : selectClclnDsctnOutlList
     * @Description : TODO
     * @param clclnVo
     * @return
     * @throws Exception
     * @return List<ClclnDsctnVo>
      */
    public List<ClclnDsctnVo> selectClclnDsctnOutlList(ClclnVo clclnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnOutlList(clclnVo);
    }

    /**
     * 잔액/이자관리 탭 조회. 
     *
     * @Title : selectBlncIntList
     * @Description : TODO
     * @param clclnVo
     * @return
     * @throws Exception
     * @return List<ClclnDsctnVo>
      */
    public List<ClclnDsctnVo> selectBlncIntList(ClclnVo clclnVo) throws Exception {
        return clclnDsctnDao.selectBlncIntList(clclnVo);
    }
    
    /**
     * 집행내역 저장. 
     *
     * @Title : saveClclnDsctn
     * @Description : TODO
     * @param clclnDsctnVo
     * @return
     * @throws Exception
     * @return int
      */
    @Override
    public int saveClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        if (clclnDsctnVo.getDsctnid() > 0) {
            result = clclnDsctnDao.updateClclnDsctn(clclnDsctnVo);
        } else {
            result = clclnDsctnDao.insertClclnDsctn(clclnDsctnVo);
        }
        return result;
    }

    /**
     * 집행내역 삭제 
     *
     * @Title : deleteClclnDsctn
     * @Description : TODO
     * @param clclnDsctnVo
     * @return
     * @throws Exception
     * @return int
      */
    @Override
    public int deleteClclnDsctn(ClclnDsctnVo clclnDsctnVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDsctnDao.deleteClclnDsctn(clclnDsctnVo);
    }

    @Override
    public void clclnDsctnOutlExcelDownload(ClclnVo clclnVo, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        // TODO Auto-generated method stub
        List<ClclnDsctnVo> list = null;
        String realName = "";
        ClclnDsctnVo modelVo = null;    

        realName = "clclnDsctnOutlExcelList.xls";
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
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
        
        sheet = workbook.createSheet("sheet1");

        String [] titleArr = {
                "항목"
                ,"세부항목"
                ,"예산액"
                ,"지출금액"
                ,"집행건수"
                ,"집행잔액"
                ,"집행비율(%)"
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
            
//            sheet.autoSizeColumn(titleCnt);
//            sheet.setColumnWidth(titleCnt, (sheet.getColumnWidth(titleCnt))+1024);
        }

        list = clclnDsctnDao.selectClclnDsctnOutlList(clclnVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                if ("1".equals(modelVo.getGroupNum()) && StringUtils.isNotEmpty(modelVo.getArtclUpperNm())) {
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum()+Integer.valueOf(modelVo.getRowspan())-1, 0, 0));
                }
                
                if (StringUtils.isEmpty(modelVo.getArtclUpperNm()) || StringUtils.isEmpty(modelVo.getArtclNm())) {
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 1));
                }
                
//                if (i+1 == list.size()) {
//                    HSSFCellStyle sumStyle = workbook.createCellStyle();
//                    sumStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//                    sumStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//                    row.setRowStyle(sumStyle);
//                }
                
                /*항목*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(StringUtils.isEmpty(modelVo.getArtclUpperNm()) ? "합계" : (StringUtils.isEmpty(modelVo.getArtclNm()) ? "소계" : modelVo.getArtclUpperNm()), ""));
                cell.setCellStyle(style);
                /*세부항목*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getArtclNm(), ""));
                cell.setCellStyle(style);   
                /*예산액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAmt(),""));
                cell.setCellStyle(styleR);
                /*지출금액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getMinusSum(),""));
                cell.setCellStyle(styleR);
                /*집행건수*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCnt(),""));
                cell.setCellStyle(styleR);
                /*집행잔액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getNowAmt(),""));
                cell.setCellStyle(styleR);
                /*집행비율(%)*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getClclnRate(),"") + "%");
                cell.setCellStyle(styleR);
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
