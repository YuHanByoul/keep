/**
 * 
 */
package com.kbrainc.plum.mng.eduInst.service;

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

import com.kbrainc.plum.mng.eduInst.model.EduExprtVo;
import com.kbrainc.plum.mng.eduInst.model.EduInstDao;
import com.kbrainc.plum.mng.eduInst.model.EduInstVo;
import com.kbrainc.plum.mng.eduInst.model.EqpVo;
import com.kbrainc.plum.mng.eduInst.model.LctrumVo;
import com.kbrainc.plum.mng.eduInst.model.ReqUserVo;
import com.kbrainc.plum.mng.eduInst.model.SchdlVo;
import com.kbrainc.plum.mng.eduInst.model.SeePrgrmVo;
import com.kbrainc.plum.mng.eduInst.model.SupplementVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 사회환경교육기관 지정 > 신청/결과관리 서비스 클래스. 
*
* <pre>
* com.kbrainc.plum.mng.eduInst.service
* - EduInstServiceImpl.java
* </pre> 
*
* @ClassName : EduInstServiceImpl
* @Description : TODO
* @author : LHM
* @date : 2023. 4. 26.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class EduInstServiceImpl extends PlumAbstractServiceImpl implements EduInstService {

    @Autowired
    private EduInstDao eduInstDao;

    @Override
    public List<EduInstVo> selectInstDsgnList(EduInstVo eduInstVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.selectInstDsgnList(eduInstVo);
    }

    @Override
    public EduInstVo selectInstDsgnInfo(EduInstVo eduInstVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.selectInstDsgnInfo(eduInstVo);
    }
    @Override
    public List<EduInstVo> selectDsgnDsctn(EduInstVo eduInstVo) throws Exception {
        return eduInstDao.selectDsgnDsctn(eduInstVo);
    }
    @Override
    public void instDsgnListExcelDownload(EduInstVo eduInstVo, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        // TODO Auto-generated method stub
        List<EduInstVo> list = null;
        String realName = "";
        EduInstVo modelVo = null;    

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
                "시도"
                ,"접수번호"
                ,"기관명"
                ,"기관유형"
                ,"신청자"
                ,"신청일(접수일)"
                ,"신청상태"
                ,"보완요청"
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
        
        eduInstVo.setExcelYn("Y");
        list = eduInstDao.selectInstDsgnList(eduInstVo);
        
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
                /*접수번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRcptno(), ""));
                cell.setCellStyle(style);   
                /*기관명*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);
                /*기관유형*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstTypeNm(), ""));
                cell.setCellStyle(style);
                /*신청자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplcntNm(),""));
                cell.setCellStyle(style);
                /*신청일(접수일)*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getRegDt(),""));
                cell.setCellStyle(style);
                /*신청상태*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSttsNm() ,""));
                cell.setCellStyle(style);
                /*보완요청*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAnsSttsNm(),""));
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
    
    /**
    * 상태코드 변경
    *
    * @Title : updateSttsCd
    * @Description : 상태코드 변경
    * @param eduInstVo
    * @throws Exception
    * @return Map<String,Object>
    */
    @Transactional
    @Override
    public int updateSttsCd(EduInstVo eduInstVo) throws Exception{
        int result = 0;
        // 환경_교육_기관 상태코드 변경
        result += eduInstDao.updateSttsCd(eduInstVo);
        // 지정내역 등록
        if ("248104".equals(eduInstVo.getDsctnSttsCd()) || "248105".equals(eduInstVo.getDsctnSttsCd())) {
            result += eduInstDao.insertDsgnDsctnSttsCd(eduInstVo);
        }
        return result;
    }
    
    @Override
    public EduInstVo selectDsgnNo(EduInstVo eduInstVo) throws Exception {
        return eduInstDao.selectDsgnNo(eduInstVo);
    }
    
    @Override
    public EduInstVo selectDsgnDsctnInfo(EduInstVo eduInstVo) throws Exception {
        return eduInstDao.selectDsgnDsctnInfo(eduInstVo);        
    }

    /**
    * 지정번호 중복 조회
    *
    * @Title : selectDsgnNoDupChk
    * @Description : 지정번호 중복 조회
    * @param eduInstVo
    * @return int
    * @throws Exception
    */
    @Override
    public int selectDsgnNoDupChk(EduInstVo eduInstVo) throws Exception {
        return eduInstDao.selectDsgnNoDupChk(eduInstVo);  
    }
    
    /**
    * 지정내역 저장
    *
    * @Title : insertDsgnDsctn
    * @Description : 지정내역 저장
    * @param eduInstVo
    * @throws Exception
    * @return int
    */
    @Transactional
    @Override
    public int insertDsgnDsctn(EduInstVo eduInstVo) throws Exception{
        int result = 0;
        // 환경_교육_기관 상태코드 변경
        result += eduInstDao.updateSttsCd(eduInstVo);
        result += eduInstDao.insertDsgnDsctn(eduInstVo);
        return result;
    }

    /**
     * 지정내역 변경
     *
     * @Title : updateDsgnDsctn
     * @Description : 지정내역 변경
     * @param eduInstVo
     * @throws Exception
     * @return int
     */
    @Transactional
    @Override
    public int updateDsgnDsctn(EduInstVo eduInstVo) throws Exception{
        int result = 0;
        // 환경_교육_기관 상태코드 변경
        result += eduInstDao.updateSttsCd(eduInstVo);
        result += eduInstDao.updateDsgnDsctn(eduInstVo);
        return result;
    }
    
    @Override
    public int updateInstDsgnInfo(EduInstVo eduInstVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.updateInstDsgnInfo(eduInstVo);
    }
    
    /**
    * 운영계획 조회
    *
    * @Title : selectOperPlan
    * @Description : 운영계획 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return EduInstVo
    */
    @Override
    public EduInstVo selectOperPlan(EduInstVo eduInstVo) throws Exception {
        return eduInstDao.selectOperPlan(eduInstVo);
    }

    /**
    * 운영계획 등록
    *
    * @Title : insertOperPlan
    * @Description : 운영계획 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int insertOperPlan(EduInstVo eduInstVo) throws Exception{
        int ret=0;
        ret+=eduInstDao.insertOperPlan(eduInstVo);
        return ret;
    }
    
    /**
    * 운영계획 수정
    *
    * @Title : updateOperPlan
    * @Description : 운영계획 수정
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int updateOperPlan(EduInstVo eduInstVo) throws Exception{
        int ret=0;
        ret+=eduInstDao.updateOperPlan(eduInstVo);
        return ret;
    }
    
    /**
    * 추진일정 목록 조회
    *
    * @Title : selectSchdlList
    * @Description : 추진일정 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SchdlVo>
    */
    @Override
    public List<SchdlVo> selectSchdlList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectSchdlList(eduInstVo);
    }

    /**
    * 추진일정 등록
    *
    * @Title : insertPropSchdl
    * @Description : 추진일정 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int insertPropSchdl(EduInstVo eduInstVo) throws Exception {
        int ret=0;
        List<SchdlVo> schdlList = null;

        eduInstDao.deletePropSchdl(eduInstVo);

        schdlList = eduInstVo.getSchdlList();
        if(schdlList != null && schdlList.size()> 0) {
            for(SchdlVo vo : schdlList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());

                ret+=eduInstDao.insertPropSchdl(vo);
            }
        }

        return ret;
    }
    
    /**
    * 교육전문인력 목록 조회
    *
    * @Title : selectEduExprtList
    * @Description : 교육전문인력 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EduExprtVo>
    */
    public List<EduExprtVo> selectEduExprtList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectEduExprtList(eduInstVo);
    }

    /**
    * 교육전문인력 등록
    *
    * @Title : insertEduExprt
    * @Description : 교육전문인력 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int insertEduExprt(EduInstVo eduInstVo) throws Exception{
        int ret=0;

        List<EduExprtVo> exprtList = null;

        eduInstDao.deleteEduExprt(eduInstVo);

        exprtList = eduInstVo.getEduExprtList();
        if(exprtList != null && exprtList.size()> 0) {
            for(EduExprtVo vo : exprtList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());

                ret+=eduInstDao.insertEduExprt(vo);
            }
        }

        return ret;
    }  
    
    /**
    * 교육프로그램 목록 조회
    *
    * @Title : selectSeePrgrmList
    * @Description : 교육프로그램 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SeePrgrmVo>
    */
    @Override
    public List<SeePrgrmVo> selectSeePrgrmList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectSeePrgrmList(eduInstVo);
    }

    /**
    * 지정 프로그램 목록 조회
    *
    * @Title : selectDsgnPrgrmList
    * @Description : 지정 프로그램 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<SeePrgrmVo>
    */
    @Override
    public List<SeePrgrmVo> selectDsgnPrgrmList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectDsgnPrgrmList(eduInstVo);
    }

    /**
    * 교육프로그램보유현황 등록
    *
    * @Title : insertHldngStts
    * @Description : 교육프로그램보유현황 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int insertHldngStts(EduInstVo eduInstVo) throws Exception{
        int ret=0;

        List<SeePrgrmVo> prgrmList = null;

        eduInstDao.deleteSeePrgrm(eduInstVo);

        prgrmList = eduInstVo.getSeePrgrmList();
        if(prgrmList != null && prgrmList.size()> 0) {
            for(SeePrgrmVo vo : prgrmList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());

                ret+=eduInstDao.insertSeePrgrm(vo);
            }
        }

        return ret;
    }

    /**
    * 시설 개요 조회
    *
    * @Title : selectSeeFclt
    * @Description : 시설 개요 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return EduInstVo
    */
    @Override
    public EduInstVo selectSeeFclt(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectSeeFclt(eduInstVo);
    }

    /**
    * 시설 강의실 목록 조회
    *
    * @Title : selectLctrumList
    * @Description : 시설 강의실 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<LctrumVo>
    */
    @Override
    public List<LctrumVo> selectLctrumList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectLctrumList(eduInstVo);
    }

    /**
    * 시설 설비 목록 조회
    *
    * @Title : selectFcltEqpList
    * @Description : 시설 설비 목록 조회
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return List<EqpVo>
    */
    @Override
    public List<EqpVo> selectFcltEqpList(EduInstVo eduInstVo) throws Exception{
        return eduInstDao.selectFcltEqpList(eduInstVo);
    }

    /**
    * 교육시설 및 설비 현황 등록
    *
    * @Title : insertFcltStts
    * @Description : 교육시설 및 설비 현황 등록
    * @param eduInstVo
    * @return
    * @throws Exception
    * @return int
    */
    @Override
    @Transactional
    public int insertFcltStts(EduInstVo eduInstVo) throws Exception {
        int ret=0;

        List<LctrumVo> lctrumList = null;
        List<EqpVo>    eqpList = null;

        ret+=eduInstDao.insertSeeFclt(eduInstVo);

        eduInstDao.deleteFcltLctrum(eduInstVo);
        eduInstDao.deleteFcltEqp(eduInstVo);

        lctrumList = eduInstVo.getLctrumList();
        eqpList = eduInstVo.getEqpList();

        if(lctrumList != null && lctrumList.size()> 0) {
            for(LctrumVo vo : lctrumList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());
                ret+=eduInstDao.insertFcltLctrum(vo);
            }
        }

        if(eqpList != null && eqpList.size()> 0) {
            for(EqpVo vo : eqpList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());
                ret+=eduInstDao.insertFcltEqp(vo);
            }
        }

        return ret;
    }

    /**
     * 교육시설 및 설비 현황 수정
     *
     * @Title : updateFcltStts
     * @Description : 교육시설 및 설비 현황 수정
     * @param eduInstVo
     * @return
     * @throws Exception
     * @return int
     */
    @Override
    @Transactional
    public int updateFcltStts(EduInstVo eduInstVo) throws Exception {
        int ret=0;

        List<LctrumVo> lctrumList = null;
        List<EqpVo>    eqpList = null;

        ret+=eduInstDao.updateSeeFclt(eduInstVo);

        eduInstDao.deleteFcltLctrum(eduInstVo);
        eduInstDao.deleteFcltEqp(eduInstVo);

        lctrumList = eduInstVo.getLctrumList();
        eqpList = eduInstVo.getEqpList();

        if(lctrumList != null && lctrumList.size()> 0) {
            for(LctrumVo vo : lctrumList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());
                ret+=eduInstDao.insertFcltLctrum(vo);
            }
        }

        if(eqpList != null && eqpList.size()> 0) {
            for(EqpVo vo : eqpList) {
                vo.setUser(eduInstVo.getUser());
                vo.setAplyid(eduInstVo.getAplyid());
                ret+=eduInstDao.insertFcltEqp(vo);
            }
        }

        return ret;
    }
    
    @Override
    public List<ReqUserVo> selectUserList(ReqUserVo reqUserVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.selectUserList(reqUserVo);
    }
    
    @Override
    public List<SupplementVo> selectSplmntList(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.selectSplmntList(supplementVo);
    }

    @Override
    public int insertSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.insertSplmnt(supplementVo);
    }


    @Override
    public int updateSplmnt(SupplementVo supplementVo) throws Exception {
        // TODO Auto-generated method stub
        return eduInstDao.updateSplmnt(supplementVo);
    }
}
