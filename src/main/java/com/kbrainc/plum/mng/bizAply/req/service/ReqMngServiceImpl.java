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
                cell.setCellValue(StringUtil.nvl(modelVo.getSrngSttsNm() ,""));
                cell.setCellStyle(style);
                /*점수*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getFrstScr(),""));
                cell.setCellStyle(style); 
                /*심사등급*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getFirstGrd(),""));
                cell.setCellStyle(style); 
                /*심사순위*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getFirstRkng(), ""));
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
                param.setBgngDe(smrLeaderVo.getBgngDe()[i]);
                param.setEndDe(smrLeaderVo.getEndDe()[i]);
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
                result += reqMngDao.updateSrngScore(vo);
            }
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
                if ("Y".equals(reqUserVo.getSrngEndYn()) || "Y".equals(reqUserVo.getScndSrngEndYn())) {
                    paramVo.setFirstScr(vo.getFirstScr());
                    paramVo.setFirstRkng(vo.getFirstRkng());
                    paramVo.setFirstGrd(vo.getFirstGrd());
                }
                paramVo.setSrngEndYn(reqUserVo.getSrngEndYn());
                paramVo.setScndSrngEndYn(reqUserVo.getScndSrngEndYn());

                result += reqMngDao.updateSrngScore(paramVo);
            }
        }
        
        return result;
    }
}
