package com.kbrainc.plum.mng.rgnEnveduCntr.service;

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

import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrDao;
import com.kbrainc.plum.mng.rgnEnveduCntr.model.RgnEnveduCntrVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 지역환경교육센터 서비스 구현 클래스
*
* <pre>
* com.kbrainc.plum.mng.rgnEnveduCntr.service
* - RgnEnveduCntrServiceImpl.java
* </pre>
*
* @ClassName : RgnEnveduCntrServiceImpl
* @Description : 지역환경교육센터 서비스 구현 클래스
* @author : JD
* @date : 2022. 12. 30.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class RgnEnveduCntrServiceImpl extends PlumAbstractServiceImpl implements RgnEnveduCntrService{
    
    @Autowired
    private RgnEnveduCntrDao rgnEnveduCntrDao;
    
    /**
    * 지역환경교육센터 목록 조회
    *
    * @Title : selectRgnEnveduCntrList
    * @Description : 지역환경교육센터 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return Map<String,Object>
    */
    public List<RgnEnveduCntrVo> selectRgnEnveduCntrList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {    
        return rgnEnveduCntrDao.selectRgnEnveduCntrList(rgnEnveduCntrVo);
    }
    
    /**
    * 구분코드(공통코드) 목록 조회
    *
    * @Title : selectCmmCdList
    * @Description : 구분 코드(공통코드) 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    public List<RgnEnveduCntrVo> selectCmmCdList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {    
        return rgnEnveduCntrDao.selectCmmCdList(rgnEnveduCntrVo);
    }
    
    /**
    * 지역코드 목록 조회
    *
    * @Title : selectAddrCtpvnList
    * @Description : 지역코드 목록 조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    public List<RgnEnveduCntrVo> selectAddrCtpvnList(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {    
        return rgnEnveduCntrDao.selectAddrCtpvnList(rgnEnveduCntrVo);
    }
    
    /**
    * 지역환경교육센터 엑셀다운로드
    *
    * @Title : selectRgnEnveduCntrExcelDownload
    * @Description : 지역환경교육센터 엑셀다운로드
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return List<EnveduCntrVo>
    */
    @Override
    public void selectRgnEnveduCntrExcelDownload(RgnEnveduCntrVo rgnEnveduCntrVo , HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<RgnEnveduCntrVo> list = null;
        String realName = "";
        RgnEnveduCntrVo modelVo = null;    

        realName = "지역환경교육센터_현황_목록.xls";
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
                ,"구분"
                ,"시도"
                ,"센터명"
                ,"기관명"
                ,"홈페이지"
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

        list = rgnEnveduCntrDao.selectRgnEnveduCntrExcelDownload(rgnEnveduCntrVo);
        
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
                /*구분*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSeCd(), ""));
                cell.setCellStyle(style);
                /*시도*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRgnCd(), ""));
                cell.setCellStyle(style);   
                /*센터명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCntrNm(), ""));
                cell.setCellStyle(styleL);
                /*기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(styleL);
                /*홈페이지*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getHmpg(), ""));
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
    * 지역환경교육센터 등록
    *
    * @Title : insertRgnEnveduCntr
    * @Description : 지역환경교육센터 등록
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 에외
    * @return int
    */
    public int insertRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        return rgnEnveduCntrDao.insertRgnEnveduCntr(rgnEnveduCntrVo);
    }
    
    /**
    * 지역환경교육센터 상세조회
    *
    * @Title : selectRgnEnveduCntrInfo
    * @Description : 지역환경교육센터 상세조회
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return EnveduCntrVo
    */
    public RgnEnveduCntrVo selectRgnEnveduCntrInfo(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        return rgnEnveduCntrDao.selectRgnEnveduCntrInfo(rgnEnveduCntrVo);
    }
    
    /**
    * 지역환경교육센터 삭제
    *
    * @Title : deleteRgnEnveduCntr
    * @Description : 지역환경교육센터 삭제
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return int
    */
    public int deleteRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        return rgnEnveduCntrDao.deleteRgnEnveduCntr(rgnEnveduCntrVo);
    }
    
    /**
    * 지역환경교육센터 수정
    *
    * @Title : updateRgnEnveduCntr
    * @Description : 지역환경교육센터 수정
    * @param rgnEnveduCntrVo 지역환경교육센터 객체
    * @throws Exception 예외
    * @return int
    */
    public int updateRgnEnveduCntr(RgnEnveduCntrVo rgnEnveduCntrVo) throws Exception {
        return rgnEnveduCntrDao.updateRgnEnveduCntr(rgnEnveduCntrVo);
    }
    
}
