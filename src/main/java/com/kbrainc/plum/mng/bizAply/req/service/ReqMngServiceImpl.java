/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.req.service;

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

import com.kbrainc.plum.mng.bizAply.req.model.CapabilityVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProcPlanVo;
import com.kbrainc.plum.mng.bizAply.req.model.ProgramInfoVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngDao;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.req.model.SupplementVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

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
                "접수번호"
                ,"신청상태"
                ,"신청기관명"
                ,"신청자"
                ,"프로그램명"
                ,"접수일시"
                ,"심사상태"
                ,"점수"
                ,"심사등급"
                ,"심사순위"
                ,"선정결과"
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
        
        reqUserVo.setExcelYn("Y");
        list = reqMngDao.selectReqUserList(reqUserVo);
        
        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);
                
                //타이틀이 1개 row에 write 되어있음 따라서 i+1 
                row = sheet.createRow((i+1));
                cellnum = 0;
                
                /*접수번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplyno(), ""));
                cell.setCellStyle(style);
                /*신청상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplySttsCd(), ""));
                cell.setCellStyle(style);   
                /*신청기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);
                /*신청자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getUserNm(), ""));
                cell.setCellStyle(style);
                /*프로그램명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getPrgrmNm(),""));
                cell.setCellStyle(style);
                /*접수일시*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRegDe(),""));
                cell.setCellStyle(style);
                /*심사상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo ,""));
                cell.setCellStyle(style);
                /*점수*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo,""));
                cell.setCellStyle(style); 
                /*심사등급*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo,""));
                cell.setCellStyle(style); 
                /*심사순위*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo, ""));
                cell.setCellStyle(style);  
                /*선정결과*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSlctnSttsNm(), ""));
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
    public int insertInst(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 기관역량
        result += reqMngDao.insertInst(capabilityVo);
        
        // 전년도 지원사업 프로그램 주제
        result += reqMngDao.insertInstPrgrm(capabilityVo);
        
        // 환경교육 운영성과
        result += reqMngDao.insertInstOperRslt(capabilityVo);
        
        return result;
    }

    @Override
    public int updateInst(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        // 기관역량
        result += reqMngDao.insertInst(capabilityVo);
        
        // 전년도 지원사업 프로그램 주제
        result += reqMngDao.deleteInstPrgrm(capabilityVo);
        result += reqMngDao.insertInstPrgrm(capabilityVo);
        
        // 환경교육 운영성과
        result += reqMngDao.deleteInstOperRslt(capabilityVo);
        result += reqMngDao.insertInstOperRslt(capabilityVo);
        
        return result;
    }

    @Override
    public List<CapabilityVo> selectInstPrgrmList(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstPrgrmList(capabilityVo);
    }

    @Override
    public List<CapabilityVo> selectInstOperRsltList(CapabilityVo capabilityVo) throws Exception {
        // TODO Auto-generated method stub
        return reqMngDao.selectInstOperRsltList(capabilityVo);
    }

    @Override
    public ProcPlanVo detailPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertPlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updatePlan(ProcPlanVo procPlanVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ProgramInfoVo detailPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertPrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updatePrgrmInfo(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ProgramInfoVo> selectPrgrmList(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertPrgrm(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deletePrgrm(ProgramInfoVo programInfoVo) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

}
