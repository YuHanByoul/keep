/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.srng.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbrainc.plum.mng.bizAply.req.model.ReqMngDao;
import com.kbrainc.plum.mng.bizAply.req.model.ReqMngVo;
import com.kbrainc.plum.mng.bizAply.req.model.ReqUserVo;
import com.kbrainc.plum.mng.bizAply.srng.model.BizAplySrngDao;
import com.kbrainc.plum.mng.bizAply.srng.model.BizAplySrngVo;
import com.kbrainc.plum.mng.score.model.QuestionVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* [심사관리 서비스 클래스]. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.srng.service
* - BizAplySrngServiceImpl.java
* </pre> 
*
* @ClassName : BizAplySrngServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 2. 16.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class BizAplySrngServiceImpl extends PlumAbstractServiceImpl implements BizAplySrngService {

    @Autowired
    private BizAplySrngDao bizAplySrngDao;
    
    @Autowired
    private ReqMngDao reqMngDao;

    @Override
    public void selectSrngListExcelDownload(ReqMngVo reqMngVo, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // TODO Auto-generated method stub
        List<ReqMngVo> list = null;
        String realName = "";
        ReqMngVo modelVo = null;    

        realName = "srngExcelList.xls";
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
                ,"심사상태"
                ,"심사기간"
                ,"신청"
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
    public void selectSrngUserListExcelDownload(ReqUserVo reqUserVo, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
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
                ,"심사상태"
                ,"신청기관명"
                ,"신청자"
                ,"프로그램명"
                ,"접수일시"
                ,"1차 점수"
                ,"심사등급"
                ,"심사순위"
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
                cell.setCellValue("");
                cell.setCellStyle(style);
                /*점수*/
                cell = row.createCell(cellnum++);
                cell.setCellValue("");
                cell.setCellStyle(style); 
                /*심사등급*/
                cell = row.createCell(cellnum++);
                cell.setCellValue("");
                cell.setCellStyle(style); 
                /*심사순위*/
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
    public BizAplySrngVo detailBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception {
        // TODO Auto-generated method stub
        return bizAplySrngDao.detailBizAplySrng(bizAplySrngVo);
    }

    @Transactional
    @Override
    public int insertBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        result = bizAplySrngDao.insertBizAplySrng(bizAplySrngVo);
        String data = bizAplySrngVo.getJsonString();
        if (StringUtils.isNotBlank(data)) {
            JSONParser json = new JSONParser();
            JSONObject jsonobj = (JSONObject)json.parse(data);
            
            JSONArray dataArray = (JSONArray)jsonobj.get("data");
            if (CollectionUtils.isNotEmpty(dataArray)) {
                QuestionVo questionVo = new QuestionVo();
                questionVo.setUser(bizAplySrngVo.getUser());
                questionVo.setSbmsnid(bizAplySrngVo.getSbmsnid());
                
                for(int i = 0; i < dataArray.size(); i++){
                    JSONObject jsonObj = (JSONObject) dataArray.get(i);
                    String qitemid = jsonObj.get("qitemid") != null ? jsonObj.get("qitemid").toString().trim() : "";
                    String scr = jsonObj.get("scr") != null ? jsonObj.get("scr").toString().trim() : "";
                    questionVo.setQitemid(Integer.valueOf(qitemid));
                    questionVo.setScr(Integer.valueOf(scr));
                    
                    bizAplySrngDao.insertItem(questionVo);
                }
            }
        }
        
        return result;
    }

    @Transactional
    @Override
    public int updateBizAplySrng(BizAplySrngVo bizAplySrngVo) throws Exception {
        // TODO Auto-generated method stub
        int result = 0;
        
        result = bizAplySrngDao.updateBizAplySrng(bizAplySrngVo);
        String data = bizAplySrngVo.getJsonString();
        if (StringUtils.isNotBlank(data)) {
            JSONParser json = new JSONParser();
            JSONObject jsonobj = (JSONObject)json.parse(data);
            
            JSONArray dataArray = (JSONArray)jsonobj.get("data");
            if (CollectionUtils.isNotEmpty(dataArray)) {
                QuestionVo questionVo = new QuestionVo();
                questionVo.setUser(bizAplySrngVo.getUser());
                for(int i = 0; i < dataArray.size(); i++){
                    JSONObject jsonObj = (JSONObject) dataArray.get(i);
                    String qitemid = jsonObj.get("qitemid") != null ? jsonObj.get("qitemid").toString().trim() : "";
                    String scr = jsonObj.get("scr") != null ? jsonObj.get("scr").toString().trim() : "";
                    questionVo.setSbmsnid(bizAplySrngVo.getSbmsnid());
                    questionVo.setQitemid(Integer.valueOf(qitemid));
                    questionVo.setScr(Integer.valueOf(scr));
                    
                    bizAplySrngDao.updateItem(questionVo);
                }
            }
        }
        
        return result;
    }

    @Override
    public List<BizAplySrngVo> selectCnsltngExprtList() throws Exception {
        // TODO Auto-generated method stub
        return bizAplySrngDao.selectCnsltngExprtList();
    }
}
