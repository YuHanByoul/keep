/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.bizAply.req.model.BudgetVo;
import com.kbrainc.plum.mng.bizAply.req.model.CapabilityResultVo;
import com.kbrainc.plum.mng.bizAply.req.model.CapabilityVo;
import com.kbrainc.plum.mng.bizAply.req.model.OperRndVo;
import com.kbrainc.plum.mng.bizAply.req.model.OperVo;
import com.kbrainc.plum.mng.bizAply.req.model.PrgrmEvlVo;
import com.kbrainc.plum.mng.bizAply.req.model.PrgrmInfoOutlineVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProcPlanVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProgramInfoVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngDao;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.model.SafetyMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderAcbgVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderCarrVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderJobVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderLicVo;
import com.kbrainc.plum.mng.bizAply.req.model.SmrLeaderVo;
import com.kbrainc.plum.mng.bizAply.req.model.SrngTabVo;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

import kr.go.onepass.client.org.apache.commons.lang.StringUtils;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 신청관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.req.service
* - ReqMngServiceImpl.java
* </pre> 
*
* @ClassName : ReqMngServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class ReqMngServiceImpl extends PlumAbstractServiceImpl implements ReqMngService {

    @Autowired
    private ReqMngDao reqMngDao;

    @Override
    public List<ReqMngVo> selectReqMngList(ReqMngVo reqMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectReqMngList(reqMngVo);
    }


    @Override
    public void reqMngListExcelDownload(ReqMngVo reqMngVo, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // TODO Auto-generated method stub
        List<ReqMngVo> list = null;
        String realName = "";
        ReqMngVo modelVo = null;    

        realName = "requestMngExcelList.xls";
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
                "사업분야"
                ,"공모명"
                ,"신청상태"
                ,"신청기간"
                ,"심사상태"
                ,"심사기간"
                ,"신청"
                ,"1차심사"
                ,"2차심사"
                ,"선정완료"
                ,"선정탈락"
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
        
        reqMngVo.setExcelYn("Y");
        list = reqMngDao.selectReqMngList(reqMngVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*사업분야*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getFldNm(), ""));
                cell.setCellStyle(style);
                /*공모명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getPcntstNm(), ""));
                cell.setCellStyle(style);   
                /*신청상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplySttsNm(), ""));
                cell.setCellStyle(style);
                /*신청기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplyDt(), ""));
                cell.setCellStyle(style);
                /*심사상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsNm(),""));
                cell.setCellStyle(style);
                /*심사기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSrngDt(),""));
                cell.setCellStyle(style);
                /*신청*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplynoCnt() ,""));
                cell.setCellStyle(style);
                /*1차심사*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getFrtSrngCnt(),""));
                cell.setCellStyle(style); 
                /*2차심사*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getScdSrngCnt(),""));
                cell.setCellStyle(style); 
                /*선정완료*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSlctnYCnt(), ""));
                cell.setCellStyle(style);  
                /*선정탈락*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSlctnNCnt(), ""));
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
    public List<ReqUserVo> selectReqUserList(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectReqUserList(reqUserVo);
    }

    @Override
    public void reqUserListExcelDownload(ReqUserVo reqUserVo, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // TODO Auto-generated method stub
        List<ReqUserVo> list = null;
        String realName = "";
        ReqUserVo modelVo = null;    

        realName = "requestUserExcelList.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        //Font 설정.
        HSSFFont font = workbook.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        //제목의 스타일 지정 (접수 정보)
        HSSFCellStyle titlestyle2 = workbook.createCellStyle();
        titlestyle2.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
        titlestyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        titlestyle.setWrapText(true);
        titlestyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle2.setFont(font);
        
        //제목의 스타일 지정 (프로그램 정보)
        HSSFCellStyle titlestyle = workbook.createCellStyle();
        titlestyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        titlestyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        titlestyle.setWrapText(true);
        titlestyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle.setFont(font);
        
        //제목의 스타일 지정 (기관 정보)
        HSSFCellStyle titlestyle3 = workbook.createCellStyle();
        titlestyle3.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titlestyle3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titlestyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        titlestyle.setWrapText(true);
        titlestyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titlestyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);    //얇은 테두리 설정
        titlestyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN);//얇은 테두리 설정
        titlestyle3.setFont(font);
        
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
        
        ArrayList<String> titleList = new ArrayList<String>();
        
        // 생애주기 : 173101
        // 학습공동체 : 173103
        String [] titleArr = {
                "연번","기관·단체명","프로그램 명","권역","지역"
                ,"신청예산","교육대상","대면/비대면","실내/실외","강의형/체험형","일회성/다회성","필수주제\n교육차시\n1회기별(탄소중립)","선택주제","선택주제\n교육차시","운영회기","회기별\n교육차시","총\n교육차시","차시별\n교육인원","총\n교육인원"
                ,"설립년도","기관유형","사업자 등록번호","대표자명","기관·단체 주소","전화번호","성명","핸드폰번호","이메일"
        };
        
        // 종교인 : 173102
        String [] titleArr2 = {
                "연번","기관·단체명","프로그램 명","종단","권역","지역"
                ,"신청예산","교육대상","대면/비대면","실내/실외","강의형/체험형","일회성/다회성","필수주제\n교육차시\n1회기별(탄소중립)","선택주제","선택주제\n교육차시","운영회기","회기별\n교육차시","총\n교육차시","차시별\n교육인원","총\n교육인원"
                ,"설립년도","기관유형","사업자 등록번호","대표자명","기관·단체 주소","전화번호","성명","핸드폰번호","이메일"
        };
        
        // 교사학습공동체 : 173105, 환경동아리 : 173104, 환경교육 우수학교 : 173107
        String [] titleArr3 = {
                "연번","기관·단체명","프로그램 명","권역","지역"
                ,"설립년도","기관유형","사업자 등록번호","대표자명","기관·단체 주소","전화번호","성명","핸드폰번호","이메일"
        };
        
        // 그린캠퍼스 : 173106
        String [] titleArr4 = {
                "연번","동아리명","권역","지역"
                ,"주소","성명","핸드폰번호","이메일"
        };
        
        //Row 생성
        HSSFRow row = sheet.createRow(0);
        HSSFRow row2 = null;
        HSSFCell cell = null;

        if ("173101".equals(reqUserVo.getFldCd()) || "173103".equals(reqUserVo.getFldCd())) {
            // 생애주기. 학습공동체
            cell = row.createCell(0);
            cell.setCellValue("접수정보");
            cell.setCellStyle(titlestyle2);
            
            cell = row.createCell(5);
            cell.setCellValue("프로그램 정보");
            cell.setCellStyle(titlestyle);
            
            cell = row.createCell(19);
            cell.setCellValue("기관 정보");
            cell.setCellStyle(titlestyle3);
            
            cell = row.createCell(27);
            cell.setCellStyle(titlestyle3);
            
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 4));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 5, 18));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 19, 27));
            
            for(int i=0;i<titleArr.length;i++){
                titleList.add(titleArr[i]);
            }
            
            row = sheet.createRow(1);
            row2 = sheet.createRow(2);
            int titleCnt = 0;
            for(String title : titleList){
                if (titleCnt >= 19 && titleCnt <= 21) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("기관·단체현황");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 19, 21));
                } else if (titleCnt >= 25) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("담당자");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 25, 27));
                } else {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue(title);
                    cell.setCellStyle(titleCnt < 5 ? titlestyle2 : (titleCnt > 18 ? titlestyle3 : titlestyle));
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum()+1, titleCnt, titleCnt));
                }
                cell = row2.createCell(titleCnt);
                cell.setCellValue(title);
                cell.setCellStyle(titleCnt < 5 ? titlestyle2 : (titleCnt > 18 ? titlestyle3 : titlestyle));
                
                titleCnt++;
            }
        } else if ("173102".equals(reqUserVo.getFldCd())) { 
            // 종교인
            cell = row.createCell(0);
            cell.setCellValue("접수정보");
            cell.setCellStyle(titlestyle2);
            
            cell = row.createCell(6);
            cell.setCellValue("프로그램 정보");
            cell.setCellStyle(titlestyle);
            
            cell = row.createCell(20);
            cell.setCellValue("기관 정보");
            cell.setCellStyle(titlestyle3);
            
            cell = row.createCell(28);
            cell.setCellStyle(titlestyle3);
            
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 6, 19));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 20, 28));
            
            for(int i=0;i<titleArr2.length;i++){
                titleList.add(titleArr2[i]);
            }
            
            row = sheet.createRow(1);
            row2 = sheet.createRow(2);
            int titleCnt = 0;
            for(String title : titleList){
                if (titleCnt >= 20 && titleCnt <= 22) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("기관·단체현황");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 20, 22));
                } else if (titleCnt >= 26) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("담당자");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 26, 28));
                } else {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue(title);
                    cell.setCellStyle(titleCnt < 6 ? titlestyle2 : (titleCnt > 19 ? titlestyle3 : titlestyle));
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum()+1, titleCnt, titleCnt));
                }
                cell = row2.createCell(titleCnt);
                cell.setCellValue(title);
                cell.setCellStyle(titleCnt < 6 ? titlestyle2 : (titleCnt > 19 ? titlestyle3 : titlestyle));
                
                titleCnt++;
            }
        } else if ("173104".equals(reqUserVo.getFldCd()) || "173105".equals(reqUserVo.getFldCd()) || "173107".equals(reqUserVo.getFldCd())) {
            // 교사학습공동체, 환경동아리, 환경교육 우수학교
            cell = row.createCell(0);
            cell.setCellValue("접수정보");
            cell.setCellStyle(titlestyle2);
            
            cell = row.createCell(5);
            cell.setCellValue("기관 정보");
            cell.setCellStyle(titlestyle3);
            
            cell = row.createCell(13);
            cell.setCellStyle(titlestyle3);
            
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 4));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 5, 13));
            
            for(int i=0;i<titleArr3.length;i++){
                titleList.add(titleArr3[i]);
            }
            
            row = sheet.createRow(1);
            row2 = sheet.createRow(2);
            int titleCnt = 0;
            for(String title : titleList){
                if (titleCnt >= 5 && titleCnt <= 7) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("기관·단체현황");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 5, 7));
                } else if (titleCnt >= 11) {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue("담당자");
                    cell.setCellStyle(titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 11, 13));
                } else {
                    cell = row.createCell(titleCnt);
                    cell.setCellValue(title);
                    cell.setCellStyle(titleCnt < 5 ? titlestyle2 : titlestyle3);
                    sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum()+1, titleCnt, titleCnt));
                }
                cell = row2.createCell(titleCnt);
                cell.setCellValue(title);
                cell.setCellStyle(titleCnt < 5 ? titlestyle2 : titlestyle3);
                
                titleCnt++;
            }
        } else {
            // 그린캠퍼스
            cell = row.createCell(0);
            cell.setCellValue("접수정보");
            cell.setCellStyle(titlestyle2);
            
            cell = row.createCell(4);
            cell.setCellValue("신청자 정보");
            cell.setCellStyle(titlestyle3);
            
            cell = row.createCell(7);
            cell.setCellStyle(titlestyle3);
            
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, 3));
            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 4, 7));
            
            for(int i=0;i<titleArr4.length;i++){
                titleList.add(titleArr4[i]);
            }
            
            row = sheet.createRow(1);
            int titleCnt = 0;
            for(String title : titleList){
                cell = row.createCell(titleCnt);
                cell.setCellValue(title);
                cell.setCellStyle(titleCnt < 4 ? titlestyle2 : titlestyle3);
                
                titleCnt++;
            }
        }

        reqUserVo.setExcelYn("Y");
        list = reqMngDao.selectReqUserList(reqUserVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                if (!"173106".equals(reqUserVo.getFldCd())) { // 그린캠퍼스가 아니면...
                    //타이틀이 3개 row에 write 되어있음 따라서 i+1 
                    row = sheet.createRow((i+3));
                } else {
                    row = sheet.createRow((i+2));
                }
                cellnum = 0;
                
                /** 접수정보 **/
                /*연번*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(String.valueOf(i+1));
                cell.setCellStyle(style);
                if (!"173106".equals(reqUserVo.getFldCd())) { // 그린캠퍼스가 아니면...
                    /*기관/단체명*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                    cell.setCellStyle(style);
                }
                /*프로그램명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(),""));
                cell.setCellStyle(style);
                if ("173102".equals(reqUserVo.getFldCd())) { // 종교인
                    /*종단*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getRelgnNm(),""));
                    cell.setCellStyle(style);
                }
                /*권역*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstSareaNm(),""));
                cell.setCellStyle(style);
                /*지역*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRgnNm(),""));
                cell.setCellStyle(style);

                if ("173101".equals(reqUserVo.getFldCd()) || "173102".equals(reqUserVo.getFldCd()) || "173103".equals(reqUserVo.getFldCd())) { // 생애주기, 종교인, 학습공동체
                    /** 프로그램 정보 **/
                    ProcPlanVo procPlanVo = new ProcPlanVo();
                    procPlanVo.setAplyid(modelVo.getAplyid());
                    procPlanVo = reqMngDao.detailPlan(procPlanVo);
                    ProgramInfoVo programInfoVo = new ProgramInfoVo();
                    programInfoVo.setAplyid(modelVo.getAplyid());
                    programInfoVo = reqMngDao.detailPrgrmInfo(programInfoVo);
                    /*신청예싼*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(procPlanVo == null ? "" : StringUtil.nvl(procPlanVo.getTotalSum(),""));
                    cell.setCellStyle(style);
                    /*교육대상*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getEduTrgt(),""));
                    cell.setCellStyle(style);
                    /*대면/비대면*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getEduMthdNm(),""));
                    cell.setCellStyle(style);
                    /*실내/실외*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getLctrPlcSeNm(),""));
                    cell.setCellStyle(style);
                    /*강의형/체험형*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getLctrFrmNm(),""));
                    cell.setCellStyle(style);
                    /*일회성/다회성*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getLctrTmeMthdNm(),""));
                    cell.setCellStyle(style);
                    /*필수주제 교육차시*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getEssntlSbjctRnd(),""));
                    cell.setCellStyle(style);
                    /*선택주제*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getChoiseSbjctNm(),""));
                    cell.setCellStyle(style);
                    /*선택주제 교육차시*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getChoiseSbjctRnd(),""));
                    cell.setCellStyle(style);
                    /*운영회기*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getOperSesn(),""));
                    cell.setCellStyle(style);
                    /*회기별 교육차시*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getSesnEduRnd(),""));
                    cell.setCellStyle(style);
                    /*총 교육차시*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getTotalEduCnt(),""));
                    cell.setCellStyle(style);
                    /*차시별 교육인원*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getRndEduNope(),""));
                    cell.setCellStyle(style);
                    /*총 교육인원*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(programInfoVo == null ? "" : StringUtil.nvl(programInfoVo.getWholEduNope(),""));
                    cell.setCellStyle(style);
                }
                
                if (!"173106".equals(reqUserVo.getFldCd())) { // 그린캠퍼스가 아니면...
                    /** 기관 정보 **/
                    /*설립연도*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue("".equals(StringUtil.nvl(modelVo.getRegDe(), "")) ? "" : modelVo.getRegDe().substring(0, 4));
                    cell.setCellStyle(style);
                    /*기관유형*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getInstTypeNm(), ""));
                    cell.setCellStyle(style);
                    /*등록번호*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getRegNo(), ""));
                    cell.setCellStyle(style);
                    /*대표자명*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getRprsvNm(), ""));
                    cell.setCellStyle(style);
                }
                /*주소*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstAddr(), "") + " " + StringUtil.nvl(modelVo.getInstAddrDtl(), ""));
                cell.setCellStyle(style);
                if (!"173106".equals(reqUserVo.getFldCd())) { // 그린캠퍼스가 아니면...
                    /*전화번호*/
                    cell = row.createCell(cellnum++);
                    cell.setCellValue(StringUtil.nvl(modelVo.getAplyInstTelno(), ""));
                    cell.setCellStyle(style);
                }
                /*성명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntNm(), ""));
                cell.setCellStyle(style);
                /*핸드폰번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntTelno(), ""));
                cell.setCellStyle(style);
                /*이메일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntEml(), ""));
                cell.setCellStyle(style);
            }

            for(int i=0;i<titleList.size();i++){
                sheet.autoSizeColumn((short)i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)+1024);
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
    public List<ReqUserVo> selectUserList(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectUserList(reqUserVo);
    }
    
    @Override
    public int updateSlctnSttsCd(String[] aplyids) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updateSlctnSttsCd(aplyids);
    }

    @Override
    public int insertReqInfo(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.insertReqInfo(reqUserVo);
    }
    
    @Override
    public int updateReqInfo(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updateReqInfo(reqUserVo);
    }

    @Override
    public List<SupplementVo> selectSplmntList(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectSplmntList(supplementVo);
    }

    @Override
    public int insertSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.insertSplmnt(supplementVo);
    }


    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updateSplmnt(supplementVo);
    }
    
    @Override
    public CapabilityVo detailInst(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailInst(capabilityVo);
    }

    @Transactional
    @Override
    @Deprecated
    public int insertInst(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 기관역량
        result += reqMngDao.insertInst(capabilityVo);
        
        // 전년도 지원사업 프로그램 주제
        result += reqMngDao.insertInstPrgrm(capabilityVo);
        
        // 환경교육 운영성과
//        result += reqMngDao.insertInstOperRslt(capabilityVo);
        
        return result;
    }

    @Transactional
    @Override
    public int updateInst(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 기관역량
        result += reqMngDao.updateInst(capabilityVo);
        
        if (CollectionUtils.isNotEmpty(capabilityVo.getPrgrmSbjctCds())) {
            // 전년도 지원사업 프로그램 주제
            result += reqMngDao.deleteInstPrgrm(capabilityVo);
            result += reqMngDao.insertInstPrgrm(capabilityVo);            
        }
        
        if (null != capabilityVo.getSeCd() && capabilityVo.getSeCd().length > 0) {
            result += reqMngDao.deleteInstOperRslt(capabilityVo);
            for (int i = 0; i < capabilityVo.getSeCd().length; i++) {
                CapabilityResultVo param = new CapabilityResultVo();
                param.setUser(capabilityVo.getUser());
                param.setAplyid(capabilityVo.getAplyid());                
                param.setSeCd(capabilityVo.getSeCd()[i]);
                param.setBsnsNm(capabilityVo.getBsnsNm()[i]);
                param.setBsnsPrd(capabilityVo.getBsnsPrd()[i]);
                param.setEduNope(capabilityVo.getEduNope()[i]);
                param.setBsnsBgt(capabilityVo.getBsnsBgt()[i]);
                param.setOperCn(capabilityVo.getOperCn()[i]);
                param.setOrdr(capabilityVo.getOrdr()[i]);
                
                result += reqMngDao.insertInstOperRslt(param);
            }
        }
        
        return result;
    }

    @Override
    public List<CapabilityVo> selectInstPrgrmList(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstPrgrmList(capabilityVo);
    }

    @Override
    public List<CapabilityResultVo> selectInstOperRsltList(CapabilityResultVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstOperRsltList(capabilityVo);
    }

    @Override
    public ProcPlanVo detailPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPlan(procPlanVo);
    }

    @Override
    @Deprecated
    public int insertPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.insertPlan(procPlanVo);
    }

    @Override
    public int updatePlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.updatePlan(procPlanVo);
    }

    @Override
    public ProgramInfoVo detailPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPrgrmInfo(programInfoVo);
    }

    @Override
    @Deprecated
    public int insertPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.insertPrgrmInfo(programInfoVo);
    }

    @Transactional
    @Override
    public int updatePrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result = reqMngDao.updatePrgrmInfo(programInfoVo);
        
        if (null != programInfoVo.getPrgrmNm() && programInfoVo.getPrgrmNm().length > 0) {
            result += reqMngDao.deletePrgrmOutline(programInfoVo);
            for (int i = 0; i < programInfoVo.getPrgrmNm().length; i++) {
                PrgrmInfoOutlineVo param = new PrgrmInfoOutlineVo();
                param.setUser(programInfoVo.getUser());
                param.setAplyid(programInfoVo.getAplyid());
                param.setOrdr(i+1);
                param.setPrgrmNm(programInfoVo.getPrgrmNm()[i]);
                param.setEduRnd(programInfoVo.getEduRnd()[i]);
                param.setEduNope(programInfoVo.getEduNope()[i]);
                
                result += reqMngDao.insertPrgrmOutline(param);
            }
        }
        
        return result;
    }

    @Override
    public List<PrgrmInfoOutlineVo> selectPrgrmList(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectPrgrmList(programInfoVo);
    }

    @Override
    public SmrLeaderVo detailSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailSmrLeader(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderJobVo> selectLeaderPlanList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderPlanList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderAcbgVo> selectLeaderAbilList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderAbilList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderLicVo> selectLeaderLicList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderLicList(smrLeaderVo);
    }


    @Override
    public List<SmrLeaderCarrVo> selectLeaderCarrList(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectLeaderCarrList(smrLeaderVo);
    }

    @Transactional
    @Override
    public int updateSmrLeader(SmrLeaderVo smrLeaderVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        result += reqMngDao.updateSmrLeaderMgt(smrLeaderVo);
        result += reqMngDao.updateSmrLeaderInfo(smrLeaderVo);
        
        if (null != smrLeaderVo.getSe() && smrLeaderVo.getSe().length > 0) {
            result += reqMngDao.deleteLeaderPlan(smrLeaderVo);   
            for (int i = 0; i < smrLeaderVo.getSe().length; i++) {
                SmrLeaderJobVo param = new SmrLeaderJobVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setSe(smrLeaderVo.getSe()[i]);
                param.setNm(smrLeaderVo.getLdrnm()[i]);
                param.setTaskCn(smrLeaderVo.getTaskCn()[i]);
                
                result += reqMngDao.insertLeaderPlan(param);
            }
        }
        
        if (null != smrLeaderVo.getSchlNm() && smrLeaderVo.getSchlNm().length > 0) {
            result += reqMngDao.deleteLeaderAbil(smrLeaderVo);   
            for (int i = 0; i < smrLeaderVo.getSchlNm().length; i++) {
                SmrLeaderAcbgVo param = new SmrLeaderAcbgVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setDgr(smrLeaderVo.getDgr()[i]);
                String de[] = smrLeaderVo.getEduDe()[i].split("~");
                if (de.length == 2) {
                    param.setBgngDe(de[0].trim());
                    param.setEndDe(de[1].trim());
                }
                param.setMjr(smrLeaderVo.getMjr()[i]);
                param.setSchlNm(smrLeaderVo.getSchlNm()[i]);
                
                result += reqMngDao.insertLeaderAbil(param);
            }
        }
        
        if (null != smrLeaderVo.getQlfcNm() && smrLeaderVo.getQlfcNm().length > 0) {
            result += reqMngDao.deleteLeaderLic(smrLeaderVo);   
            for (int i = 0; i < smrLeaderVo.getQlfcNm().length; i++) {
                SmrLeaderLicVo param = new SmrLeaderLicVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setAcqsDe(smrLeaderVo.getAcqsDe()[i]);
                param.setGrd(smrLeaderVo.getGrd()[i]);
                param.setQlfcNm(smrLeaderVo.getQlfcNm()[i]);
                param.setWrkplc(smrLeaderVo.getWrkplc()[i]);
                
                result += reqMngDao.insertLeaderLic(param);
            }
        }
        
        if (null != smrLeaderVo.getPrd() && smrLeaderVo.getPrd().length > 0) {
            result += reqMngDao.deleteLeaderCarr(smrLeaderVo);   
            for (int i = 0; i < smrLeaderVo.getPrd().length; i++) {
                SmrLeaderCarrVo param = new SmrLeaderCarrVo();
                param.setUser(smrLeaderVo.getUser());
                param.setAplyid(smrLeaderVo.getAplyid());
                param.setActvtNm(smrLeaderVo.getActvtNm()[i]);
                param.setActvtType(smrLeaderVo.getActvtType()[i]);
                param.setPrd(smrLeaderVo.getPrd()[i]);
                
                result += reqMngDao.insertLeaderCarr(param);
            }
        }
        
        return result;
    }

    @Override
    public SafetyMngVo detailSafetyMng(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailSafetyMng(safetyMngVo);
    }
    
    @Override
    public PrgrmEvlVo detailPrgrmEvl(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailPrgrmEvl(safetyMngVo);
    }

    @Transactional
    @Override
    public int updateSafetyMng(SafetyMngVo safetyMngVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        result = reqMngDao.updateSafetyMng(safetyMngVo);
        result += reqMngDao.updatePrgrmEvl(safetyMngVo);
        
        return result;
    }

    @Override
    public List<BudgetVo> selectBudgetList(BudgetVo budgetVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectBudgetList(budgetVo);
    }

    @Transactional
    @Override
    public int updateBudget(BudgetVo budgetVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        if ("173107".equals(budgetVo.getFldCd())) {
            result = reqMngDao.updateBudget(budgetVo);
        } else {
            if (budgetVo != null && budgetVo.getCodeArr() != null) {
                for (int i = 0; i < budgetVo.getCodeArr().length; i++) {
                    budgetVo.setBgtid(budgetVo.getBgtidArr()[i]);
                    budgetVo.setAmt(budgetVo.getAmtArr()[i]);
                    budgetVo.setExpitmCd(budgetVo.getCodeArr()[i]);
                    budgetVo.setDsctn(budgetVo.getDsctnArr()[i]);
                    
                    result += reqMngDao.updateBudget(budgetVo);                
                }
            }
        }
    
        return result;
    }


    @Override
    public OperVo detailOper(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailOper(operVo);
    }


    @Override
    public List<OperVo> selectOperSubjectList(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectOperSubjectList(operVo);
    }


    @Override
    public List<OperRndVo> selectOperSubjectRndList(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectOperSubjectRndList(operVo);
    }

    @Transactional
    @Override
    public int updateOper(OperVo operVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 운영개요
        result += reqMngDao.updateOper(operVo);
        
        if (CollectionUtils.isNotEmpty(operVo.getEduSbjctCds())) {
            // 교육 주제
            result += reqMngDao.deleteOperSubject(operVo);
            result += reqMngDao.insertOperSubject(operVo);            
        }
        
        if (null != operVo.getRnd() && operVo.getRnd().length > 0) {
            result += reqMngDao.deleteOperSubjectRnd(operVo);
            for (int i = 0; i < operVo.getRnd().length; i++) {
                if (operVo.getRnd()[i] != null) {
                    OperRndVo param = new OperRndVo();
                    param.setUser(operVo.getUser());
                    param.setAplyid(operVo.getAplyid());                
                    param.setEduSbjctCd(StringUtils.defaultIfEmpty(operVo.getEduSbjctCdArr()[i], ""));
                    param.setRnd(operVo.getRnd()[i]);
                    param.setSbjctSeCd(operVo.getSbjctSeCdArr()[i]);
                    
                    // 운영 차시
                    result += reqMngDao.insertOperSubjectRnd(param);                    
                }
            }
        }
        
        return result;
    }


    @Override
    public ReqUserVo detailReqUser(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailReqUser(reqUserVo);
    }

    @Override
    public List<SrngTabVo> selectSrngList(SrngTabVo srngTabVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectSrngList(srngTabVo);
    }

    @Override
    public List<SrngTabVo> detailSrngList(SrngTabVo srngTabVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.detailSrngList(srngTabVo);
    }
    
    @Override
    public List<SrngTabVo> selectSrngResult(SrngTabVo srngTabVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectSrngResult(srngTabVo);
    }

    @Override
    public List<SrngTabVo> selectSrngUserList(SrngTabVo srngTabVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectSrngUserList(srngTabVo);
    }


    @Override
    public List<ReqMngVo> selectScheduleList(ReqMngVo reqMngVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectScheduleList(reqMngVo);
    }

    @Transactional
    @Override
    public int updateSrngScore(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        if (CollectionUtils.isNotEmpty(reqUserVo.getAplyids())) {
            for (String aplyid : reqUserVo.getAplyids()) {
                ReqUserVo vo = new ReqUserVo();
                vo.setUser(reqUserVo.getUser());
                vo.setAplyid(Integer.valueOf(aplyid));
                vo.setSlctnSttsCd(reqUserVo.getSlctnSttsCd());
                vo.setScndSrngTrgtYn(reqUserVo.getScndSrngTrgtYn());
                result += reqMngDao.updateSrngScore(vo);
            }
        } else {
            result = reqMngDao.updateSrngScore(reqUserVo);
        }
        return result;
    }

    @Transactional
    @Override
    public int updateSrngEnd(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        List<ReqUserVo> list = reqMngDao.selectScoreList(reqUserVo);
        if (CollectionUtils.isNotEmpty(list)) {
            ReqUserVo paramVo = null;
            for (ReqUserVo vo : list) {
                paramVo = new ReqUserVo();
                paramVo.setUser(reqUserVo.getUser());
                paramVo.setAplyid(vo.getAplyid());
                if ("Y".equals(reqUserVo.getSrngEndYn())) { 
                    paramVo.setFirstScr(vo.getFirstScr());
                    paramVo.setFirstRkng(vo.getFirstRkng());
                    paramVo.setFirstGrd(vo.getFirstGrd());
                }
                if ("Y".equals(reqUserVo.getScndSrngEndYn())) {
                    paramVo.setScndScr(vo.getFirstScr());
                    paramVo.setScndRkng(vo.getFirstRkng());
                    paramVo.setScndGrd(vo.getFirstGrd());
                }
                paramVo.setSrngEndYn(reqUserVo.getSrngEndYn());
                paramVo.setScndSrngEndYn(reqUserVo.getScndSrngEndYn());

                result += reqMngDao.updateSrngScore(paramVo);
            }
        }
        
        return result;
    }
}
