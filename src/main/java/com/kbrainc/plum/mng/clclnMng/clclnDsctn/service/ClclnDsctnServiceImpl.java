package com.kbrainc.plum.mng.clclnMng.clclnDsctn.service;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.mng.clclnMng.clclnDsctn.model.ClclnDsctnDao;
import com.kbrainc.plum.mng.clclnMng.clclnDsctn.model.ClclnDsctnVo;
import com.kbrainc.plum.rte.model.UserVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 컨트롤러 클래스
**
<pre>
* com.kbrainc.plum.mng.clclnMng.clclnDsctn.service
* - ClclnDsctnServiceImpl.java
* </pre>
**
@ClassName : ClclnDsctnServiceImpl
* @Description : 체험환경교육 지원사업 -> 정산관리 -> 정산내역관리 서비스 구현 클래스
* @author : 이한명
* @date : 2023. 2. 13.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ClclnDsctnServiceImpl extends PlumAbstractServiceImpl implements ClclnDsctnService{
    
    @Autowired
    private ClclnDsctnDao clclnDsctnDao;
    
    /**
    * 정산내역관리 게시글 목록 조회
    *
    * @Title : selectClclnDsctnList
    * @Description : 정산내역관리 게시글 목록 조회
    * @param clclnDsctnVo 정산내역관리 객체
    * @throws Exception 예외
    * @return List<ClclnDsctnVo>
    */
    @Override
    public List<ClclnDsctnVo> selectClclnDsctnList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnList(clclnDsctnVo);
    }
    
    /**
    * 정산내역관리 게시글 상세목록 조회
    *
    * @Title : selectClclnDsctnDetailList
    * @Description : 정산내역관리 게시글 상세목록 조회
    * @param clclnDsctnVo 정산내역관리 객체
    * @throws Exception 예외
    * @return List<ClclnDsctnVo>
    */
    @Override
    public List<ClclnDsctnVo> selectClclnDsctnDetailList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnDetailList(clclnDsctnVo);
    }

    /**
     * 정산내역관리 게시글 상세정보 조회
     *
     * @Title : selectClclnDsctnDetailInfo
     * @Description : 정산내역관리 게시글 상세정보 조회
     * @param clclnDsctnVo 정산내역관리 객체
     * @throws Exception 예외
     * @return ClclnDsctnVo
     */
    @Override
    public ClclnDsctnVo selectClclnDsctnDetailInfo(ClclnDsctnVo clclnDsctnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnDetailInfo(clclnDsctnVo);
    }

    /**
     * 정산내역관리 상세정보 집행내역 개요서 리스트 조회
     *
     * @Title : selectClclnDsctnDetailOutlList
     * @Description : 정산내역관리 상세정보 집행내역 개요서 리스트 조회
     * @param clclnDsctnVo 정산내역관리 객체
     * @throws Exception 예외
     * @return List<ClclnDsctnVo>
     */
    @Override
    public List<ClclnDsctnVo> selectClclnDsctnDetailOutlList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnDetailOutlList(clclnDsctnVo);
    }

    /**
     * 정산내역관리 상세정보 집행상세내역 개요서 리스트 조회
     *
     * @Title : selectClclnDsctnDetailOutlDtlList
     * @Description : 정산내역관리 상세정보 집행상세내역 개요서 리스트 조회
     * @param clclnDsctnVo 정산내역관리 객체
     * @throws Exception 예외
     * @return List<ClclnDsctnVo>
     */
    @Override
    public List<ClclnDsctnVo> selectClclnDsctnDetailOutlDtlList(ClclnDsctnVo clclnDsctnVo) throws Exception {
        return clclnDsctnDao.selectClclnDsctnDetailOutlDtlList(clclnDsctnVo);
    }
    
    /**
     * 정산내역관리 상세목록 제출상태 수정
     *
     * @Title : updateClclnDsctnComplete
     * @Description : 정산내역관리 상세목록 제출상태 수정
     * @param clclnDsctnVo 정산내역관리 객체
     * @throws Exception 예외
     * @return List<ClclnDsctnVo>
     */
    @Override
    @Transactional
    public int updateClclnDsctnComplete(String[] completeAplyIds, UserVo userVo) throws Exception {
        int retVal = 0;
        ClclnDsctnVo clclnDsctnVo = new ClclnDsctnVo();
        clclnDsctnVo.setUser(userVo);
        for(String completeAplyId : completeAplyIds) {
            clclnDsctnVo.setCompleteAplyId(completeAplyId);
            retVal += clclnDsctnDao.updateClclnDsctnComplete(clclnDsctnVo);
        }
        return retVal;
    }
    
    @Override
    public void clclnDsctnDetailExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ClclnDsctnVo> list = null;
        String realName = "";
        ClclnDsctnVo modelVo = null;

        realName = "정산내역제출.xls";
        String outputFileName = new String(realName.getBytes("KSC5601"), "8859_1");
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
        HSSFCellStyle styleR = workbook.createCellStyle();
        styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleR.setFont(font);

        HSSFCellStyle styleL = workbook.createCellStyle();
        styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleL.setFont(font);
        HSSFSheet sheet = null;

        sheet = workbook.createSheet("sheet1");

        String [] titleArr = {
                "No."
                ,"접수번호"
                ,"제출상태"
                ,"신청기관명"
                ,"신청자"
                ,"프로그램명"
                ,"총지출액"
                ,"이자"                
                ,"캐쉬백"
                ,"기타입금액"
                ,"집행잔액"
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

        list = clclnDsctnDao.selectClclnDsctnDetailList(clclnDsctnVo);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

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
                     
                /*접수번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRcptno(), ""));
                cell.setCellStyle(style);
                /*제출상태*/                                                                                 
                cell = row.createCell(cellnum++);                                                        
                cell.setCellValue(StringUtil.nvl(modelVo.getSbmsnSttsNm(), ""));                                
                cell.setCellStyle(style);                                                                  
                /*신청기관명*/                                                                               
                cell = row.createCell(cellnum++);                                                         
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));                               
                cell.setCellStyle(style);                                                                  
                /*신청자*/                                                                                   
                cell = row.createCell(cellnum++);                                                      
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntNm(), ""));
                cell.setCellStyle(style);
                /*프로그램명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(), ""));
                cell.setCellStyle(style);
                /*총지출액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getTotAmt(), ""));
                cell.setCellStyle(style);
                /*이자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getIntAmt(), ""));
                cell.setCellStyle(style);
                /*캐쉬백*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCashbAmt(), ""));
                cell.setCellStyle(style);
                /*기타입금액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getEtcDpstAmt(), ""));
                cell.setCellStyle(style);
                /*집행잔액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getBlncAmt(), ""));
                cell.setCellStyle(style);
            }

            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
            }
        }

        ExcelUtils.excelInfoSet(response,outputFileName);

        //엑셀 파일을 만듬
        OutputStream fileOutput = response.getOutputStream();

        workbook.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();
    }   
    @Override
    public void clclnDsctnOutlListExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ClclnDsctnVo> list = null;
        String realName = "";
        ClclnDsctnVo modelVo = null;
        
        realName = "집행내역개요서.xls";
        String outputFileName = new String(realName.getBytes("KSC5601"), "8859_1");
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
        HSSFCellStyle styleR = workbook.createCellStyle();
        styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleR.setFont(font);
        
        HSSFCellStyle styleL = workbook.createCellStyle();
        styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
                ,"집행비율"                
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
        
        list = clclnDsctnDao.selectClclnDsctnDetailOutlList(clclnDsctnVo);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1
                row = sheet.createRow((i+1));
                cellnum = 0;
                /*항목*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getUpprNm(), ""));
                cell.setCellStyle(style);
                /*세부항목*/                                                                                 
                cell = row.createCell(cellnum++);                                                        
                cell.setCellValue(StringUtil.nvl(modelVo.getExpitmNm(), ""));                                
                cell.setCellStyle(style);                                                                  
                /*예산액*/                                                                               
                cell = row.createCell(cellnum++);                                                         
                cell.setCellValue(StringUtil.nvl(modelVo.getBgtAmt(), ""));                               
                cell.setCellStyle(style);                                                                  
                /*지출금액*/                                                                                   
                cell = row.createCell(cellnum++);                                                      
                cell.setCellValue(StringUtil.nvl(modelVo.getExpndAmt(), ""));
                cell.setCellStyle(style);
                /*집행건수*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getImplCnt(), ""));
                cell.setCellStyle(style);
                /*집행잔액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getImplBlncAmt(), ""));
                cell.setCellStyle(style);
                /*집행비율*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getImplRt(), ""));
                cell.setCellStyle(style);
            }
            
            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
            }
        }
        
        ExcelUtils.excelInfoSet(response,outputFileName);
        
        //엑셀 파일을 만듬
        OutputStream fileOutput = response.getOutputStream();
        
        workbook.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();
    }
    
    @Override
    public void clclnDsctnOutlDtlListExcelDownList(ClclnDsctnVo clclnDsctnVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<ClclnDsctnVo> list = null;
        String realName = "";
        ClclnDsctnVo modelVo = null;
        
        realName = "세부집행내역서.xls";
        String outputFileName = new String(realName.getBytes("KSC5601"), "8859_1");
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
        HSSFCellStyle styleR = workbook.createCellStyle();
        styleR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleR.setFont(font);
        
        HSSFCellStyle styleL = workbook.createCellStyle();
        styleL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleL.setFont(font);
        HSSFSheet sheet = null;
        
        sheet = workbook.createSheet("sheet1");
        
        String [] titleArr = {
                "항목"
                ,"세부항목"
                ,"집행일"
                ,"구분"
                ,"내역"
                ,"사용목적"
                ,"금액"                
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
        
        list = clclnDsctnDao.selectClclnDsctnDetailOutlDtlList(clclnDsctnVo);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*항목*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getUpprNm(), ""));
                cell.setCellStyle(style);
                /*세부항목*/                                                                                 
                cell = row.createCell(cellnum++);                                                        
                cell.setCellValue(StringUtil.nvl(modelVo.getArtclNm(), ""));                                
                cell.setCellStyle(style);                                                                  
                /*집행일*/                                                                               
                cell = row.createCell(cellnum++);                                                         
                cell.setCellValue(StringUtil.nvl(modelVo.getDe(), ""));                               
                cell.setCellStyle(style);                                                                  
                /*구분*/                                                                                   
                cell = row.createCell(cellnum++);                                                      
                cell.setCellValue(StringUtil.nvl(modelVo.getSeNm(), ""));
                cell.setCellStyle(style);
                /*내역*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDsctn(), ""));
                cell.setCellStyle(style);
                /*사용목적*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getUsePrps(), ""));
                cell.setCellStyle(style);
                /*금액*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAmt(), ""));
                cell.setCellStyle(style);
            }
            
            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)+512);
            }
        }
        
        ExcelUtils.excelInfoSet(response,outputFileName);
        
        //엑셀 파일을 만듬
        OutputStream fileOutput = response.getOutputStream();
        
        workbook.write(fileOutput);
        fileOutput.flush();
        fileOutput.close();
    }   
    
    @Override
    public List<SupplementVo> selectExcclcSplmntList(SupplementVo excclcSplmntVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDsctnDao.selectExcclcSplmntList(excclcSplmntVo);
    }

    @Override
    @Transactional
    public int insertExcclcSplmnt(SupplementVo excclcSplmntVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDsctnDao.insertExcclcSplmnt(excclcSplmntVo);
    }


    @Override
    @Transactional
    public int updateExcclcSplmnt(SupplementVo excclcSplmntVo) throws Exception {
        // TODO Auto-generated method stub
        return clclnDsctnDao.updateExcclcSplmnt(excclcSplmntVo);
    }    
}
