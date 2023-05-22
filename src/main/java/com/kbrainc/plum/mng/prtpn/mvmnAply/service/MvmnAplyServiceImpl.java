package com.kbrainc.plum.mng.prtpn.mvmnAply.service;

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

import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyDao;
import com.kbrainc.plum.mng.prtpn.mvmnAply.model.MvmnAplyVo;
import com.kbrainc.plum.rte.service.PlumAbstractServiceImpl;
import com.kbrainc.plum.rte.util.StringUtil;
import com.kbrainc.plum.rte.util.excel.ExcelUtils;

/**
* 유아환경교육 -> 교육신청관리 서비스 구현 클래스
**
<pre>
* com.kbrainc.plum.mng.prtpn.mvmnAply.service
* - MvmnAplyServiceImpl.java
* </pre>
**
@ClassName : MvmnAplyServiceImpl
* @Description : 유아환경교육 -> 교육신청관리 서비스 구현 클래스
* @author : Notebiz001
* @date : 2023. 1. 9.
* @Version :
* @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
*/
@Service
public class MvmnAplyServiceImpl extends PlumAbstractServiceImpl implements MvmnAplyService{
    
    @Autowired
    private MvmnAplyDao mvmnAplyDao;
    
    /**
    * 교육신청관리 게시글 목록 조회
    *
    * @Title : selectMvmnAplyList
    * @Description : 교육신청관리 게시글 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    @Override
    public List<MvmnAplyVo> selectMvmnAplyList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyList(mvmnAplyVo);
    }

    /**
     * 교육신청관리 게시글 상세목록 조회
     *
     * @Title : selectMvmnAplyDetailList
     * @Description : 교육신청관리 게시글 상세목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    @Override
    public List<MvmnAplyVo> selectMvmnAplyDetailList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyDetailList(mvmnAplyVo);
    }

    /**
     * 교육신청관리 게시글 신청마감 리스트 조회
     *
     * @Title : selectMvmnAplyCloseList
     * @Description : 교육신청관리 게시글 신청마감 리스트 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    @Override
    public List<MvmnAplyVo> selectMvmnAplyCloseList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyCloseList(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 게시글 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyDao.insertMvmnAply(mvmnAplyVo);
        retVal += mvmnAplyDao.insertMvmnAplySchdl(mvmnAplyVo);
        if(mvmnAplyVo.getTrgtCds()!=null & mvmnAplyVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyDao.insertTrgtCd(mvmnAplyVo);
        }
        
        //retVal += mvmnAplyDao.insertMvmnAplyTme(mvmnAplyVo);
        
        return retVal;
    }

    /**
    * 교육신청관리 게시글 상세조회
    *
    * @Title : selectMvmnAplyInfo
    * @Description : 교육신청관리 게시글 상세조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return MvmnAplyVo
    */
    @Override
    public MvmnAplyVo selectMvmnAplyInfo(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.selectMvmnAplyInfo(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 게시글 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 게시글 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        int retVal = 0;
        retVal += mvmnAplyDao.updateMvmnAply(mvmnAplyVo);
        retVal += mvmnAplyDao.updateMvmnAplySchdl(mvmnAplyVo);
        
        mvmnAplyDao.deleteTrgtCd(mvmnAplyVo);

        if(mvmnAplyVo.getTrgtCds()!=null & mvmnAplyVo.getTrgtCds().length > 0) {
            retVal += mvmnAplyDao.insertTrgtCd(mvmnAplyVo);
        }

        return retVal;
    }

    /**
     * 교육신청관리 교육신청자 신청상태 수정 기능
     *
     * @Title : updateSttsCdMvmnAply
     * @Description : 교육신청관리 교육신청자 신청상태 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional    
    public int updateSttsCdMvmnAply(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.updateSttsCdMvmnAply(mvmnAplyVo);
    }

    /**
     * 교육신청관리 신청마감 수정 기능
     *
     * @Title : updateMvmnAplyClose
     * @Description : 교육신청관리 신청마감 수정 기능
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return int
     */
    @Override
    @Transactional    
    public int updateMvmnAplyClose(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.updateMvmnAplyClose(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 회차 등록
    *
    * @Title : insertMvmnAply
    * @Description : 교육신청관리 회차 등록
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예회
    * @return int
    */
    @Override
    @Transactional    
    public int insertMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.insertMvmnAplyTme(mvmnAplyVo);
    }
    
    /**
    * 교육신청관리 회차 수정
    *
    * @Title : updateMvmnAply
    * @Description : 교육신청관리 회차 수정
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return int
    */
    @Override
    @Transactional    
    public int updateMvmnAplyTme(MvmnAplyVo mvmnAplyVo) throws Exception{
        return mvmnAplyDao.updateMvmnAplyTme(mvmnAplyVo);
    }    
    
    /**
    * 교육신청관리 회차 조회
    *
    * @Title : selectMvmnAplyTmeList
    * @Description : 교육신청관리 회차 목록 조회
    * @param mvmnAplyVo 교육신청관리 객체
    * @throws Exception 예외
    * @return List<MvmnAplyVo>
    */
    @Override
    public List<MvmnAplyVo> selectMvmnAplyTmeList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectMvmnAplyTmeList(mvmnAplyVo);
    }    

    /**
     * 교육신청관리 회차별 교육일자 목록 조회
     *
     * @Title : selectMvmnAplyTmeList
     * @Description : 교육신청관리 회차별 교육일자 목록 조회
     * @param mvmnAplyVo 교육신청관리 객체
     * @throws Exception 예외
     * @return List<MvmnAplyVo>
     */
    @Override
    public List<MvmnAplyVo> selectTmeSchdlList(MvmnAplyVo mvmnAplyVo) throws Exception {
        return mvmnAplyDao.selectTmeSchdlList(mvmnAplyVo);
    }
    
    /**
     * 회원정보 조회
     *
     * @Title       : selectMemberList 
     * @Description : 기관정보 목록 리스트
     * @param param MvmnAplyVo mvmnAply 객체
     * @return List<MemberVo> 기관정보 목록
     * @throws Exception 예외
     */
    @Override
    public List<MvmnAplyVo> selectMemberList(MvmnAplyVo mvmnAply) throws Exception{
        return mvmnAplyDao.selectMemberList(mvmnAply);
    }
    
    /**
    * 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    *
    * @Title : mvmnAplyExcelDownList
    * @Description : 교육신청관리 교육신청자 검색결과 엑셀 다운로드
    * @param mvmnAply
    * @param response
    * @param request
    * @throws Exception
    * @return void
    */
    @Override
    public void mvmnAplyExcelDownList(MvmnAplyVo mvmnAplyVo, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<MvmnAplyVo> list = null;
        String realName = "";
        MvmnAplyVo modelVo = null;

        realName = "mvmnAplyList.xls";
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
                "교육일자"
                ,"회차"
                ,"신청상태"
                ,"접수일자"
                ,"신청지역"
                ,"신청기관"
                ,"신청자"
                ,"신청인원"                
                ,"휴대폰번호"
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

        list = mvmnAplyDao.mvmnAplyExcelDownList(mvmnAplyVo);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault());

        if(list != null && list.size() > 0){
            int cellnum = 0;
            for (int i=0; i<list.size();i++){
                modelVo = list.get(i);

                //타이틀이 1개 row에 write 되어있음 따라서 i+1
                row = sheet.createRow((i+1));
                cellnum = 0;

                /*교육일자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getDe(), ""));
                cell.setCellStyle(style);
                /*회차*/                                                                                 
                cell = row.createCell(cellnum++);                                                        
                cell.setCellValue(StringUtil.nvl(modelVo.getTmeNm(), ""));                                
                cell.setCellStyle(style);                                                                  
                /*신청상태*/                                                                               
                cell = row.createCell(cellnum++);                                                         
                cell.setCellValue(StringUtil.nvl(modelVo.getSttsNm(), ""));                               
                cell.setCellStyle(style);                                                                  
                /*접수일자*/                                                                                   
                cell = row.createCell(cellnum++);                                                      
                cell.setCellValue(StringUtil.nvl(modelVo.getRcptDt(), ""));
                cell.setCellStyle(style);
                /*신청지역*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getSignguDesc(), ""));
                cell.setCellStyle(style);
                /*신청기관*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getInstNm(), ""));
                cell.setCellStyle(style);
                /*신청자*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getAplyUserNm(), ""));
                cell.setCellStyle(style);
                /*신청인원*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getEduNope(), ""));
                cell.setCellStyle(style);
                /*휴대폰번호*/
                cell = row.createCell(cellnum++);
                cell.setCellValue(StringUtil.nvl(modelVo.getMoblphon(), ""));
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
}
