/**
 * 
 */
package com.kbrainc.plum.mng.bizAply.pcntst.service;

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
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestDao;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestMngGrpVo;
import com.kbrainc.plum.mng.bizAply.pcntst.model.PublicContestVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 체험환경교육 지원사업 > 사업신청 관리 > 공모관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.bizAply.sprtBizPcntst.service
* - PublicContestServiceImpl.java
* </pre> 
*
* @ClassName : PublicContestServiceImpl
* @Description : TODO
* @author : KCS
* @date : 2023. 1. 12.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class PublicContestServiceImpl extends PlumAbstractServiceImpl implements PublicContestService {

    @Autowired
    private PublicContestDao publicContestDao;

    @Override
    public List<PublicContestVo> selectContestList(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.selectContestList(publicContestVo);
    }

    @Override
    public List<PublicContestMngGrpVo> selectMngList(PublicContestMngGrpVo publicContestMngGrpVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.selectMngList(publicContestMngGrpVo);
    }
    
    @Override
    public List<EgovMap> selectEvalSheetList(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.selectEvalSheetList(publicContestVo);
    }
    
    @Override
    public int insertContest(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        int returnVal = publicContestDao.insertContest(publicContestVo);
        
        if (publicContestVo.getPcntstids1() != null) {
            if (publicContestVo.getPcntstids1().size() > 0) {
                PublicContestMngGrpVo publicContestMngGrpVo = new PublicContestMngGrpVo();
                publicContestMngGrpVo.setUser(publicContestVo.getUser());
                publicContestMngGrpVo.setPcntstid(publicContestVo.getPcntstid());
                publicContestMngGrpVo.setCycl(1);
                publicContestMngGrpVo.setPcntstids(publicContestVo.getPcntstids1());
                
                publicContestDao.insertMng(publicContestMngGrpVo);                            
            }
        }
        
        if (publicContestVo.getPcntstids2() != null) {
            if (publicContestVo.getPcntstids2().size() > 0) {
                PublicContestMngGrpVo publicContestMngGrpVo = new PublicContestMngGrpVo();
                publicContestMngGrpVo.setUser(publicContestVo.getUser());
                publicContestMngGrpVo.setPcntstid(publicContestVo.getPcntstid());
                publicContestMngGrpVo.setCycl(2);
                publicContestMngGrpVo.setPcntstids(publicContestVo.getPcntstids2());
                
                publicContestDao.insertMng(publicContestMngGrpVo);                            
            }
        }
        
        return returnVal;
    }

    @Override
    public int updateContest(PublicContestVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        int returnVal = publicContestDao.updateContest(publicContestVo);
        
        if (publicContestVo.getPcntstids1() != null) {
            if (publicContestVo.getPcntstids1().size() > 0) {
                PublicContestMngGrpVo publicContestMngGrpVo = new PublicContestMngGrpVo();
                publicContestMngGrpVo.setUser(publicContestVo.getUser());
                publicContestMngGrpVo.setPcntstid(publicContestVo.getPcntstid());
                publicContestMngGrpVo.setCycl(1);
                publicContestMngGrpVo.setPcntstids(publicContestVo.getPcntstids1());
                
                publicContestDao.deleteMng(publicContestMngGrpVo);
                publicContestDao.insertMng(publicContestMngGrpVo);                            
            }
        }
        
        if (publicContestVo.getPcntstids2() != null) {
            if (publicContestVo.getPcntstids2().size() > 0) {
                PublicContestMngGrpVo publicContestMngGrpVo = new PublicContestMngGrpVo();
                publicContestMngGrpVo.setUser(publicContestVo.getUser());
                publicContestMngGrpVo.setPcntstid(publicContestVo.getPcntstid());
                publicContestMngGrpVo.setCycl(2);
                publicContestMngGrpVo.setPcntstids(publicContestVo.getPcntstids2());
                
                publicContestDao.deleteMng(publicContestMngGrpVo);
                publicContestDao.insertMng(publicContestMngGrpVo);                            
            }
        }
        
        return returnVal;
    }

    @Override
    public void publicContestListExcelDownload(PublicContestVo publicContestVo, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        // TODO Auto-generated method stub
        List<PublicContestVo> list = null;
        String realName = "";
        PublicContestVo modelVo = null;    

        realName = "publicContestExcelList.xls";
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
                ,"신청대상"
                ,"진행상태"
                ,"신청기간"
                ,"사업기간"
                ,"등록일"
                ,"심사기간"
                ,"교부신청기간"
                ,"교부신청기간(2차)"
                ,"교부확정발표기간"
                ,"자급집행기간"
                ,"자급집행기간(2차)"
                ,"중간보고기간"
                ,"결과보고기간"
                ,"정산보고기간"
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

        list = publicContestDao.publicContestListExcelDownload(publicContestVo);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
        
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
                /*신청대상*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getTrgtNm(), ""));
                cell.setCellStyle(style);
                /*진행상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getStatus(), ""));
                cell.setCellStyle(style);
                /*신청기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplyDt(), ""));
                cell.setCellStyle(style);
                /*사업기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getBsnsDe(),""));
                cell.setCellStyle(style);
                /*등록일*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(dateFormat.format(modelVo.getRegDt()) ,""));
                cell.setCellStyle(style);
                /*심사기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSrngDt(),"-"));
                cell.setCellStyle(style); 
                /*교부신청기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDelvryAplyDtFirst(),"-"));
                cell.setCellStyle(style); 
                /*교부신청기간(2차)*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDelvryAplyDtScnd(), "-"));
                cell.setCellStyle(style);  
                /*교부확정발표기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDelvryCfmtnPrsntnDt(), "-"));
                cell.setCellStyle(style);  
                /*자급집행기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCptalExcutDtFirst(), "-"));
                cell.setCellStyle(style);  
                /*자급집행기간(2차)*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getCptalExcutDtScnd(), "-"));
                cell.setCellStyle(style);  
                /*중간보고기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getMdlReportDt(), "-"));
                cell.setCellStyle(style);  
                /*결과보고기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRsltReportDt(), "-"));
                cell.setCellStyle(style);  
                /*정산보고기간*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getExcclcReportDt(), "-"));
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
    public List<PublicContestMngGrpVo> selectMngGrpList(PublicContestMngGrpVo publicContestVo) throws Exception {
        // TODO Auto-generated method stub
        return publicContestDao.selectMngGrpList(publicContestVo);
    }
}
